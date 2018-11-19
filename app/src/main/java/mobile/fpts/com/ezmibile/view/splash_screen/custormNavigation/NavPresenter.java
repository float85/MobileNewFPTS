package mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.Group;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuBigGroup;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuCategory;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuCategoryChild;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.ErrorApp;
import mobile.fpts.com.ezmibile.util.Language;


/**
 * Created by HoaDT  on 4/17/2018.
 */

public class NavPresenter {

    private INavView iNav_view;
    private AppDatabase database;
    Context context;

    List<MenuCategory> menuCategoryList;
    List<MenuCategoryChild> menuCategoryChildList;

    public NavPresenter(Context context, INavView iNav_view) {
        this.context = context;
        this.iNav_view = iNav_view;
        database = Room.databaseBuilder(context, AppDatabase.class, Define.DATABASE_APP_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        initilizeData();
        getData();

        menuCategoryList = database.menuCategoryDao().getAllMenuCategory();
        menuCategoryChildList = database.menuCategoryChildDao().getAllMenuCategoryChild();
    }

    private void getData() {

        Define.LANGUAGE_APP language = Language.getLanguage();
        SparseArray<MenuBigGroup> bigGroupSparseArray = new SparseArray<>();
        //account
        bigGroupSparseArray.append(bigGroupSparseArray.size(), new MenuBigGroup("", new SparseArray<>()));
        //favorites
        List<MenuCategoryChild> childIsFavorite = database.menuCategoryChildDao().getAllMenuCategoryChildIsFavorite();
        List<MenuCategory> favorites = database.menuCategoryDao().getAllFravorite_MenuCategory();
        for (int i = 0; i < childIsFavorite.size(); i++) {
            MenuCategory category = new MenuCategory(childIsFavorite.get(i).getName(), childIsFavorite.get(i).getName_en(),
                    0, childIsFavorite.get(i).getTypeFragment(), childIsFavorite.get(i).isFavorite());
            category.setId(childIsFavorite.get(i).getId());
            favorites.add(category);
        }
        SparseArray<Group> groups = new SparseArray<>();
        for (int j = 0; j < favorites.size(); j++) {
            MenuCategory category = favorites.get(j);
            List<MenuCategoryChild> childList = database.menuCategoryChildDao().getAllMenuCategoryChildByCategoryId(category.getId());
//            SparseArray<MenuCategoryChild> childSparseArray = new SparseArray<>();
//            for (int i = 0; i < childList.size(); i++) {
//                childSparseArray.append(i, childList.get(i));
//            }
            groups.append(j, new Group(category, childList));
        }
        bigGroupSparseArray.append(bigGroupSparseArray.size(), new MenuBigGroup(language == Define.LANGUAGE_APP.LANGUAGE_EN
                ? "Favorites" : "Yêu thích", groups));
        //normal
        List<List<MenuCategory>> lists = Arrays.asList(
                database.menuCategoryDao().getAllMenuCategory(Define.TYPE_DATABASE_FEATURES),
                database.menuCategoryDao().getAllMenuCategory(Define.TYPE_DATABASE_MEMBER),
                database.menuCategoryDao().getAllMenuCategory(Define.TYPE_DATABASE_UTILS),
                database.menuCategoryDao().getAllMenuCategory(Define.TYPE_DATABASE_HELP),
                database.menuCategoryDao().getAllMenuCategory(Define.TYPE_DATABASE_SETTING));
        List<MenuCategory> titles = database.menuCategoryDao().getAllTitle_MenuCategory();

        for (int i = 0; i < lists.size(); i++) {
            List<MenuCategory> categories = lists.get(i);
            groups = new SparseArray<>();

            for (int j = 0; j < categories.size(); j++) {
                MenuCategory category = categories.get(j);
                List<MenuCategoryChild> childList = database.menuCategoryChildDao().getAllMenuCategoryChildByCategoryId(category.getId());
                groups.append(j, new Group(category, childList));
            }

            bigGroupSparseArray.append(bigGroupSparseArray.size(),
                    new MenuBigGroup((language == Define.LANGUAGE_APP.LANGUAGE_EN
                            ? titles.get(i + 1).getName_en() : titles.get(i + 1).getName()), groups));
        }
        if (iNav_view != null) {
            iNav_view.onDisplay(bigGroupSparseArray);
        }
    }

    public void onSetting() {
        //lấy ra list category
        //nếu List child category: size > 0: add từng phần tử vào list category trên
        List<MenuCategory> result = new ArrayList<>();
        List<MenuCategory> categories = database.menuCategoryDao().getAllCategorySetting_MenuCategory();
        for (int i = 0; i < categories.size(); i++) {
            List<MenuCategoryChild> children = database.menuCategoryChildDao().getAllMenuCategoryChild(categories.get(i).getId());
            if (children.size() > 0) {
                for (int j = 0; j < children.size(); j++) {
                    result.add(new MenuCategory(children.get(j).getName(), children.get(j).getName_en(), 0, children.get(j).getTypeFragment(), children.get(j).isFavorite()));
                }
            } else {
                result.add(categories.get(i));
            }
        }
        if (iNav_view != null && result.size() > 0) {
            iNav_view.onSetting(result);
        }
    }

    public void onUpdateNav(List<MenuCategory> categories) {
        if (categories == null || categories.size() == 0) {
            return;
        }
        //tách categories và childcategory
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() > 0) {
                database.menuCategoryDao().updateMenuCategory(categories.get(i).getId(), categories.get(i).isFavorite());
                categories.remove(i);
                i--;
            }
        }
        //phần category child
        List<MenuCategoryChild> menuCategoryChildren = database.menuCategoryChildDao().getAllMenuCategoryChild();
        for (int i = 0; i < categories.size(); i++) {
            database.menuCategoryChildDao().updateMenuCategoryChild(menuCategoryChildren.get(i).getId(), categories.get(i).isFavorite());
        }
        getData();
    }

    /**
     * @param categoryId
     */
    public void onClickListener(int categoryId, int typeGroup, int typeCategory) {
        if (typeGroup == Define.TYPE_DATABASE_MEMBER) {
            //CHECK LOGIN
            SharedPreferences preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
            if (preferences.getBoolean(Define.SHARED_PREFRENCES_IS_LOGIN, false)) {

            } else
                iNav_view.onError(ErrorApp.ERROR_REQUEST_LOGIN);
            return;
        }
        if (typeCategory == Define.TYPE_MENU_CATEGORY) {
            for (int i = 0; i < menuCategoryList.size(); i++)
                if (menuCategoryList.get(i).getId() == categoryId)
                    iNav_view.onSetFragment(menuCategoryList.get(i).getTypeFragment());

        } else if (typeCategory == Define.TYPE_MENU_CATEGORYCHILD) {
            for (int i = 0; i < menuCategoryChildList.size(); i++)
                if (menuCategoryChildList.get(i).getCategoryId() == categoryId)
                    iNav_view.onSetFragment(menuCategoryChildList.get(i).getTypeFragment());

        }
    }

    private void initilizeData() {
        Log.w("NavPresenter", "initilizeData: " + database.menuCategoryDao().getAllMenuCategory().size() + " "
                + database.menuCategoryChildDao().getAllMenuCategoryChild().size());
        if (database.menuCategoryDao().getAll() == null || database.menuCategoryDao().getAll().size() == 0) {
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Yêu thích", "Favorites", Define.TYPE_DATABASE_TITLE, 0, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Tính năng", "Features", Define.TYPE_DATABASE_TITLE, 0, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Thành viên", "Member", Define.TYPE_DATABASE_TITLE, 0, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Tiện ích", "Utils", Define.TYPE_DATABASE_TITLE, 0, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Trợ giúp", "Help", Define.TYPE_DATABASE_TITLE, 0, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Thiết lập", "Settings", Define.TYPE_DATABASE_TITLE, 0, false));

            database.menuCategoryDao().addMenuCategory(new MenuCategory("Trang chủ", "Home", Define.TYPE_DATABASE_FEATURES, Define.TYPE_MENU_HOME, false));
//            database.dao().addMenuCategory(new MenuCategory("Tổng quan thị trường", "MarketData Overview", Define.TYPE_DATABASE_FEATURES, Define.TYPE_MENU_MARKET_OVERRVIEW, false));
//            database.dao().addMenuCategory(new MenuCategory("Thống kê thị trường", "StatisticData", Define.TYPE_DATABASE_FEATURES, Define.TYPE_MENU_MARKET_STATISTICS, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Bảng giá", "Watchlist", Define.TYPE_DATABASE_FEATURES, Define.TYPE_MENU_WATCHLIST, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Bảng giá Phái sinh", "Derived pricing", Define.TYPE_DATABASE_FEATURES, Define.MENU_BANG_GIA_PHAI_SINH, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Tin tức", "News", Define.TYPE_DATABASE_FEATURES, Define.TYPE_MENU_NEWS, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Lịch sự kiện", "Events", Define.TYPE_DATABASE_FEATURES, Define.TYPE_MENU_EVENTS, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Biểu đồ", "Chart", Define.TYPE_DATABASE_FEATURES, Define.TYPE_MENU_CHART, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Chỉ số thế giới", "World Indexes", Define.TYPE_DATABASE_FEATURES, Define.TYPE_MENU_WORLD_INDEX, false));

            database.menuCategoryDao().addMenuCategory(new MenuCategory("Đặt lệnh", "Place Orders", Define.TYPE_DATABASE_MEMBER, Define.MENU_DAT_LENH, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Báo cáo giao dịch", "Transaction Report", Define.TYPE_DATABASE_MEMBER, Define.MENU_BAO_CAO_GIAO_DICH, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Chuyển tiền", "Money Transfer", Define.TYPE_DATABASE_MEMBER, Define.MENU_CHUYEN_TIEN, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Bán lô lẻ", "Bán lô lẻ", Define.TYPE_DATABASE_MEMBER, Define.MENU_BAN_LO_LE, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Thực hiện quyền", "Thực hiện quyền", Define.TYPE_DATABASE_MEMBER, Define.MENU_THUC_HIEN_QUYEN, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Stoploss", "Stoploss", Define.TYPE_DATABASE_MEMBER, Define.MENU_STOPLOSS, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Ký quỹ", "Margin", Define.TYPE_DATABASE_MEMBER, Define.MENU_KY_QUY, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Lưu ký chứng khoán", "Lưu ký chứng khoán", Define.TYPE_DATABASE_MEMBER, Define.MENU_LUU_KY_CHUNG_KHOAN, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Ứng trước", "Ứng trước", Define.TYPE_DATABASE_MEMBER, Define.MENU_KY_QUY_UNG_TRUOC, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Báo cáo tài sản", "Báo cáo tài sản", Define.TYPE_DATABASE_MEMBER, Define.MENU_BAO_CAO_TAI_SAN, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Tra cứu phí lưu ký", "Tra cứu phí lưu ký", Define.TYPE_DATABASE_MEMBER, Define.MENU_TRA_CUU_PHI_LUU_KY, false));

            database.menuCategoryDao().addMenuCategory(new MenuCategory("FPTS Nhận định", "FPTS Nhận định", Define.TYPE_DATABASE_UTILS, Define.MENU_FPTS_NHAN_DINH, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Thị trường tài chính", "Financial MarketData", Define.TYPE_DATABASE_UTILS, Define.MENU_THI_TRUONG_TAI_CHINH, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Thông báo từ FPTS", "FPTS's Notify", Define.TYPE_DATABASE_UTILS, Define.MENU_THONG_BAO_TU_FPTS, false));

//            database.menuCategoryDao().addMenuCategory(new Category("Ngôn ngữ", TYPE_DATABASE_SETTING, false));
//            database.menuCategoryDao().addMenuCategory(new Category("Ghi nhớ tài khoản", TYPE_DATABASE_SETTING, false));
//            database.menuCategoryDao().addMenuCategory(new Category("Thay đổi màu nền", TYPE_DATABASE_SETTING, false));

            database.menuCategoryDao().addMenuCategory(new MenuCategory("Mở tài khoản", "Create Account", Define.TYPE_DATABASE_HELP, Define.MENU_MO_TAI_KHOAN, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Liên hệ", "Contact", Define.TYPE_DATABASE_HELP, Define.MENU_LIEN_HE, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Góp ý", "Feedback", Define.TYPE_DATABASE_HELP, Define.MENU_GOP_Y, false));
            database.menuCategoryDao().addMenuCategory(new MenuCategory("Hướng dẫn sử dụng", "Guide", Define.TYPE_DATABASE_HELP, Define.MENU_HUONG_DAN_SU_DUNG, false));

            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Tra cứu Số dư", "Tra cứu Số dư", database.menuCategoryDao().getMenuCategoryId("Báo cáo giao dịch"), Define.MENU_BAO_CAO_GIAO_DICH_CHILD_TRA_CUU_SO_DU, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Lệnh chờ khớp", "Lệnh chờ khớp", database.menuCategoryDao().getMenuCategoryId("Báo cáo giao dịch"), Define.MENU_BAO_CAO_GIAO_DICH_CHILD_LENH_CHO_KHOP, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("KQ khớp lệnh", "KQ khớp lệnh", database.menuCategoryDao().getMenuCategoryId("Báo cáo giao dịch"), Define.MENU_BAO_CAO_GIAO_DICH_CHILD_KQ_KHOP_LENH, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Lệnh trong ngày", "Lệnh trong ngày", database.menuCategoryDao().getMenuCategoryId("Báo cáo giao dịch"), Define.MENU_BAO_CAO_GIAO_DICH_CHILD_LENH_TRONG_NGAY, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Chờ thanh toán", "Chờ thanh toán", database.menuCategoryDao().getMenuCategoryId("Báo cáo giao dịch"), Define.MENU_BAO_CAO_GIAO_DICH_CHILD_CHO_THANH_TOAN, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Lập lệnh", "Lập lệnh", database.menuCategoryDao().getMenuCategoryId("Chuyển tiền"), Define.MENU_CHUYEN_TIEN_CHILD_LAP_LENH, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Mẫu chuyển tiền", "Mẫu chuyển tiền", database.menuCategoryDao().getMenuCategoryId("Chuyển tiền"), Define.MENU_CHUYEN_TIEN_CHILD_MAU_CHUYEN_TIEN, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Lịch sử chuyển tiền", "Lịch sử chuyển tiền", database.menuCategoryDao().getMenuCategoryId("Chuyển tiền"), Define.MENU_CHUYEN_TIEN_CHILD_LICH_SU_CHUYEN_TIEN, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Lập lệnh", "Lập lệnh", database.menuCategoryDao().getMenuCategoryId("Bán lô lẻ"), Define.MENU_BAN_LO_LE_CHILD_LAP_LENH, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Lịch sử bán", "Lịch sử bán", database.menuCategoryDao().getMenuCategoryId("Bán lô lẻ"), Define.MENU_BAN_LO_LE_CHILD_LICH_SU_BAN, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Lập lệnh", "Lập lệnh", database.menuCategoryDao().getMenuCategoryId("Stoploss"), Define.MENU_STOPLOSS_CHILD_LAP_LENH, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Lịch sử đặt tiền", "Lịch sử đặt tiền", database.menuCategoryDao().getMenuCategoryId("Stoploss"), Define.MENU_STOPLOSS_CHILD_LICH_SU_DAT_TIEN, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Danh sách hợp đồng", "Danh sách hợp đồng", database.menuCategoryDao().getMenuCategoryId("Ký quỹ"), Define.MENU_KY_QUY_CHILD_DANH_SACH_HOP_DONG, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Cầm cố", "Cầm cố", database.menuCategoryDao().getMenuCategoryId("Ký quỹ"), Define.MENU_KY_QUY_CHILD_CAM_CO, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Trả tiền hợp đồng", "Trả tiền hợp đồng", database.menuCategoryDao().getMenuCategoryId("Ký quỹ"), Define.MENU_KY_QUY_CHILD_TRA_TIEN_HOP_DONG, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Gia hạn", "Gia hạn", database.menuCategoryDao().getMenuCategoryId("Ký quỹ"), Define.MENU_KY_QUY_CHILD_GIA_HAN, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Thay đổi hạn mức", "Thay đổi hạn mức", database.menuCategoryDao().getMenuCategoryId("Ký quỹ"), Define.MENU_KY_QUY_CHILD_THAY_DOI_HAN_MUC, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Ứng tiền cố tức", "Ứng tiền cố tức", database.menuCategoryDao().getMenuCategoryId("Ứng trước"), Define.MENU_KY_QUY_UNG_TRUOC_CHILD_UNG_TIEN_CO_TUC, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Lịch sử ứng trước", "History", database.menuCategoryDao().getMenuCategoryId("Ứng trước"), Define.MENU_KY_QUY_UNG_TRUOC_CHILD_LICH_SU_UNG_TRUOC, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Thị trường", "MarketData", database.menuCategoryDao().getMenuCategoryId("FPTS Nhận định"), Define.MENU_FPTS_NHAN_DINH_CHILD_THI_TRUONG, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Doanh nghiệp", "Doanh nghiệp", database.menuCategoryDao().getMenuCategoryId("FPTS Nhận định"), Define.MENU_FPTS_NHAN_DINH_CHILD_DOANHNGHIEP, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Ngành", "Ngành", database.menuCategoryDao().getMenuCategoryId("FPTS Nhận định"), Define.MENU_FPTS_NHAN_DINH_CHILD_NGANH, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Bản tin FPTS", "Bản tin FPTS", database.menuCategoryDao().getMenuCategoryId("FPTS Nhận định"), Define.MENU_FPTS_NHAN_DINH_CHILD_BAN_TIN_FPTS, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Tỉ giá", "Tỉ giá", database.menuCategoryDao().getMenuCategoryId("Thị trường tài chính"), Define.MENU_THI_TRUONG_TAI_CHINH_CHILD_TI_GIA, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Lãi suất", "Lãi suất", database.menuCategoryDao().getMenuCategoryId("Thị trường tài chính"), Define.MENU_THI_TRUONG_TAI_CHINH_CHILD_LAI_SUAT, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Giá vàng", "Giá vàng", database.menuCategoryDao().getMenuCategoryId("Thị trường tài chính"), Define.MENU_THI_TRUONG_TAI_CHINH_CHILD_GIA_VANG, false));
            database.menuCategoryChildDao().addMenuCategoryChild(new MenuCategoryChild("Giá dầu", "Giá dầu", database.menuCategoryDao().getMenuCategoryId("Thị trường tài chính"), Define.MENU_THI_TRUONG_TAI_CHINH_CHILD_GIA_DAU, false));
        }
    }
}
