package mobile.fpts.com.ezmibile.util;

public class Define {
    /**
     * 029588 và 100998
     * fpts12345/fpts123456
     */
    // TODO: TamHV 6/20/2018 checkupdate
    public static final String PARAM_PRODUCT_TYPE3 = "MA_A";
    public final static String WS_SOAP_METHOD_CHECK_VERSION = "Check_Version";
    public static final String PARAM_VERSION_NAME = "verName";
    public static final String PARAM_PRODUCTTYPE = "productType";
    public final static String WS_SOAP_NAMESPACE = "http://fpts.com.vn/EzGateway/";
    public static final String PARAM_CHECKVERSION_RESULT = "Check_VersionResult";

    // TODO: TamHV 6/27/2018 PACKAGENAME trong update
    public static final String PACKAGENAME = "hoadt.mobile.fpts.com.ezmibile";

    // TODO: TamHV 6/20/2018 GATEWAY THAT
    public final static String WS_URL = "https://eztrade.fpts.com.vn/Gateway5/ezGateway.asmx";
    public final static String CONN_HOST = "eztrade.fpts.com.vn";


    // TODO: TamHV 6/21/2018 GATEWAY TEST
//    public final static String WS_URL = "http://liveprice3.fpts.com.vn/gatewaytest/ezGateway.asmx";
//    public final static String CONN_HOST = "liveprice3.fpts.com.vn";

    //------------------------------------>LANGUAGE<----------------------------------------------------
    public enum LANGUAGE_APP {
        LANGUAGE_EN,
        LANGUAGE_VI
    }

    public static final String DATABASE_APP_NAME = "DATABASE_APP_NAME";
    public static final String SHARED_PREFRENCES_APP = "SHARED_PREFRENCES_APP";
    public static final String SHARED_PREFRENCES_LANGUAGE = "SHARED_PREFRENCES_LANGUAGE";
    // TODO: TamHV 6/27/2018
    public static final String SHARED_PREFRENCES_USERNAME = "SHARED_PREFRENCES_USERNAME";
    public static final String SHARED_PREFRENCES_PASSWORD = "SHARED_PREFRENCES_PASSWORD";
    public static final String SHARED_PREFRENCES_MODE_THEME = "SHARED_PREFRENCES_MODE_THEME";
    public static final String SHARED_PREFRENCES_IS_LOGIN = "SHARED_PREFRENCES_IS_LOGIN";

    //Stock Exchange
    public static final String STOCK_ORDER_EXCHANGE_HSX = "HSX";
    public static final String STOCK_ORDER_EXCHANGE_HNX = "HNX";

    //------------------------------------>MENU<----------------------------------------------------
    public enum NAVIGATION_BOTTOM_VIEW_TAB {
        OVERVIEW,
        PLACING_ORDER,
        PROPERTY,
        EDIT_DELETE,
        MONEY_TRANSFER,
    }

    //-------------------------TYPE PRICE CHANGE ---------------------------------------------------------
    public enum TYPE_CHANGE_APP {
        UP_CEILING,
        UP,
        DOWN,
        DOWN_FLOOR,
        NO_CHANGE
    }

    //     User - Menu
    public static final int TYPE_MENU_CATEGORY = 2001;
    public static final int TYPE_MENU_CATEGORYCHILD = 2002;
    //MENU
    public static final int TYPE_MENU_SEARCH = 2008;
    public static final int TYPE_MENU_SORT = 2009;
    public static final int TYPE_MENU_DETAIL_PAGE = 3000;
    public static final int TYPE_MENU_HOME = 3001;
    public static final int TYPE_MENU_MARKET_OVERRVIEW = 3002;
    public static final int TYPE_MENU_MARKET_STATISTICS = 3003;
    public static final int TYPE_MENU_WATCHLIST = 3004;
    public static final int MENU_BANG_GIA_PHAI_SINH = 3005;
    public static final int TYPE_MENU_NEWS = 3006;
    public static final int TYPE_MENU_EVENTS = 3007;
    public static final int TYPE_MENU_CHART = 3008;
    public static final int TYPE_MENU_WORLD_INDEX = 3009;


    public static final int MENU_DAT_LENH = 3010;
    public static final int MENU_BAO_CAO_GIAO_DICH = 3011;
    public static final int MENU_BAO_CAO_GIAO_DICH_CHILD_TRA_CUU_SO_DU = 3012;
    public static final int MENU_BAO_CAO_GIAO_DICH_CHILD_LENH_CHO_KHOP = 3013;
    public static final int MENU_BAO_CAO_GIAO_DICH_CHILD_KQ_KHOP_LENH = 3014;
    public static final int MENU_BAO_CAO_GIAO_DICH_CHILD_LENH_TRONG_NGAY = 3015;
    public static final int MENU_BAO_CAO_GIAO_DICH_CHILD_CHO_THANH_TOAN = 3016;
    public static final int MENU_CHUYEN_TIEN = 3017;
    public static final int MENU_CHUYEN_TIEN_CHILD_LAP_LENH = 3018;
    public static final int MENU_CHUYEN_TIEN_CHILD_MAU_CHUYEN_TIEN = 3019;
    public static final int MENU_CHUYEN_TIEN_CHILD_LICH_SU_CHUYEN_TIEN = 3020;
    public static final int MENU_BAN_LO_LE = 3021;
    public static final int MENU_BAN_LO_LE_CHILD_LAP_LENH = 3022;
    public static final int MENU_BAN_LO_LE_CHILD_LICH_SU_BAN = 3023;

    public static final int MENU_THUC_HIEN_QUYEN = 3024;
    public static final int MENU_STOPLOSS = 3025;
    public static final int MENU_STOPLOSS_CHILD_LAP_LENH = 3026;
    public static final int MENU_STOPLOSS_CHILD_LICH_SU_DAT_TIEN = 3027;
    public static final int MENU_KY_QUY = 3028;
    public static final int MENU_KY_QUY_CHILD_DANH_SACH_HOP_DONG = 3029;
    public static final int MENU_KY_QUY_CHILD_CAM_CO = 3030;
    public static final int MENU_KY_QUY_CHILD_TRA_TIEN_HOP_DONG = 3031;
    public static final int MENU_KY_QUY_CHILD_GIA_HAN = 3032;
    public static final int MENU_KY_QUY_CHILD_THAY_DOI_HAN_MUC = 3033;
    public static final int MENU_LUU_KY_CHUNG_KHOAN = 3034;
    public static final int MENU_KY_QUY_UNG_TRUOC = 3035;
    public static final int MENU_KY_QUY_UNG_TRUOC_CHILD_UNG_TIEN_CO_TUC = 3036;
    public static final int MENU_KY_QUY_UNG_TRUOC_CHILD_LICH_SU_UNG_TRUOC = 3037;
    public static final int MENU_BAO_CAO_TAI_SAN = 3038;
    public static final int MENU_TRA_CUU_PHI_LUU_KY = 3039;
    public static final int MENU_FPTS_NHAN_DINH = 3040;
    public static final int MENU_FPTS_NHAN_DINH_CHILD_THI_TRUONG = 3041;
    public static final int MENU_FPTS_NHAN_DINH_CHILD_DOANHNGHIEP = 3042;
    public static final int MENU_FPTS_NHAN_DINH_CHILD_NGANH = 3043;
    public static final int MENU_FPTS_NHAN_DINH_CHILD_BAN_TIN_FPTS = 3044;
    public static final int MENU_THI_TRUONG_TAI_CHINH = 3045;
    public static final int MENU_THI_TRUONG_TAI_CHINH_CHILD_TI_GIA = 3046;
    public static final int MENU_THI_TRUONG_TAI_CHINH_CHILD_LAI_SUAT = 3047;
    public static final int MENU_THI_TRUONG_TAI_CHINH_CHILD_GIA_VANG = 3048;
    public static final int MENU_THI_TRUONG_TAI_CHINH_CHILD_GIA_DAU = 3049;
    public static final int MENU_THONG_BAO_TU_FPTS = 3050;
    public static final int MENU_MO_TAI_KHOAN = 3051;
    public static final int MENU_LIEN_HE = 3052;
    public static final int MENU_GOP_Y = 3053;
    public static final int MENU_HUONG_DAN_SU_DUNG = 3054;

    //      Database
    public static final int TYPE_HEADER_ACCOUNT = 1001;
    public static final int TYPE_DATABASE_FEATURES = 1002;
    public static final int TYPE_DATABASE_MEMBER = 1003;
    public static final int TYPE_DATABASE_SETTING = 1004;
    public static final int TYPE_DATABASE_TITLE = 1005;
    public static final int TYPE_DATABASE_UTILS = 1006;
    public static final int TYPE_DATABASE_HELP = 1007;

    //------------------------------------>HOME<----------------------------------------------------
    public static final int HOME_TYPE_INDEXES = 5000;
    public static final int HOME_TYPE_WATCHLIST = 5001;
    public static final int HOME_TYPE_BANG_GIA_PHAI_SINH = 5002;
    public static final int HOME_TYPE_NEWS = 5003;
    public static final int HOME_TYPE_WORLD_INDEXES = 5004;
    public static final int HOME_TYPE_EVENTS = 5005;
    //---------------------------------SEARCH-------------------------------------------------------
    public static final String CATEGORY_SHARED_PREFERENCES_SEARCH_RECENT = "DATABASE_SEARCH_RECENT";
    public static final String CATEGORY_SHARED_PREFERENCES_ITEM_CK_VN = "MA_CHUNGKHOAN_VN";
    public static final String CATEGORY_SHARED_PREFERENCES_ITEM_CK_EN = "MA_CHUNGKHOAN_EN";

    public enum HOME_TYPE_ACTION {
        ACTION_ADD,
        ACTION_EDIT,
        ACTION_DETAIL,
        ACTION_ALL,
        ACTION_SHOW_POPUP,
    }

    //------------------------------------>CHART<---------------------------------------------------
    //HOME DETAIL CHART
    public static final int TYPE_CHART_ONE_DAY = 0;
    public static final int TYPE_CHART_ONE_WEEK = 1;
    public static final int TYPE_CHART_ONE_MONTH = 2;
    public static final int TYPE_CHART_THREE_MONTH = 3;
    public static final int TYPE_CHART_SIX_MONTH = 4;
    public static final int TYPE_CHART_ONE_YEAR = 5;
    public static final int TYPE_CHART_TWO_YEAR = 6;
    public static final int TYPE_CHART_ALL = 7;
    public static final String SHARED_PREFRENCES_MARKETOVERVIEW_ISCHANGE = "SHARED_PREFRENCES_MARKETOVERVIEW_ISCHANGE";
    public static final String SHARED_PREFRENCES_MARKETOVERVIEW_ISVALUE = "SHARED_PREFRENCES_MARKETOVERVIEW_ISVALUE";
    public static final int MARKETOVERVIEW_TYPE_VNINDICES = 0;
    public static final int MARKETOVERVIEW_TYPE_OTHERINDEXES = 1;

    //------------------------------------>WATCHLIST<----------------------------------------------------
    public static final String SHARED_PREFRENCES_WATCHLIST_QUOTES = "SHARED_PREFRENCES_WATCHLIST_QUOTES";//fpt,pts,gas,vnm
    public static final String SHARED_PREFRENCES_WATCHLIST_QUOTELIST = "SHARED_PREFRENCES_WATCHLIST_QUOTELIST";

    //------------------------------------>EVENTS<----------------------------------------------------
    public static final String TAB_YESTERDAY_GROUPNM = "THQ";
    public static final String TAB_TODAY_GROUPNM = "THQ";
    public static final String TAB_TOMORROW_GROUPNM = "THQ";
    public static final String TAB_THISWEEK_GROUPNM = "THQ";
    public static final String TAB_NEXTWEEK_GROUPNM = "THQ";
    //------------------------------------>NEWS<----------------------------------------------------
    public static final String SHARED_PREFERENCES_SECURITIES_PUBLIC = "FILE PUBLIC";
    // TODO: TamHV 6/28/2018
    public static final String Market_News = "Market News";
    public static final String Department_Report = "Market News";
    public static final String Company_News = "Market News";
    public static final String Analysis_Comment = "Market News";
    public static final String FPTS_News = "FPTS News";
    //------------------------------------>WORLD INDICES<-------------------------------------------

}
