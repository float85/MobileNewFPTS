package mobile.fpts.com.ezmibile.view.splash_screen.custormMenuTab;

import android.app.Activity;
import android.view.View;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.CustormNavigationBottomViewBinding;
import mobile.fpts.com.ezmibile.util.Define;

/**
 * Created by HoaDT  on 4/18/2018.
 */
public class MenuTab implements View.OnClickListener {
    IMenuTabClickListenter callback;
    CustormNavigationBottomViewBinding viewBinding;
    Activity activity;
//    SharedPreferences preferences;


    public MenuTab(CustormNavigationBottomViewBinding viewBinding, Activity activity,
                   IMenuTabClickListenter callback) {
        this.viewBinding = viewBinding;
        this.activity = activity;
        this.callback = callback;
//        preferences = activity.getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);

        viewBinding.tabOverview.setOnClickListener(this);
        viewBinding.tabProperty.setOnClickListener(this);
        viewBinding.tabPlacingOrder.setOnClickListener(this);
        viewBinding.tabEditDelete.setOnClickListener(this);
        viewBinding.tabMoneyTransfer.setOnClickListener(this);
//        boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);

//        viewBinding.tabOverview.setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
//        viewBinding.tabProperty.setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
//        viewBinding.tabPlacingOrder.setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
//        viewBinding.tabEditDelete.setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
//        viewBinding.tabMoneyTransfer.setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
        viewBinding.tabOverview.setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
        viewBinding.tabProperty.setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
        viewBinding.tabPlacingOrder.setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
        viewBinding.tabEditDelete.setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
        viewBinding.tabMoneyTransfer.setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
    }

    public void onStart() {
        viewBinding.linearLayout.setVisibility(View.VISIBLE);
//        boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
//        if (isLight) {
//            viewBinding.imgEditDelete.setImageResource(R.drawable.ic_menu_edit_delete);
//            viewBinding.imgOverview.setImageResource(R.drawable.ic_menu_overview);
//            viewBinding.imgPlacingOrder.setImageResource(R.drawable.ic_menu_placing_order);
//            viewBinding.imgMoneyTransfer.setImageResource(R.drawable.ic_menu_money_transfer);
//            viewBinding.imgProperty.setImageResource(R.drawable.ic_menu_property);
//        } else {
        viewBinding.imgEditDelete.setImageResource(R.drawable.ic_menu_edit_delete_dark);
        viewBinding.imgOverview.setImageResource(R.drawable.ic_menu_overview_dark);
        viewBinding.imgPlacingOrder.setImageResource(R.drawable.ic_menu_placing_order_dark);
        viewBinding.imgMoneyTransfer.setImageResource(R.drawable.ic_menu_money_transfer_dark);
        viewBinding.imgProperty.setImageResource(R.drawable.ic_menu_property_dark);
//        }
    }

    @Override
    public void onClick(View v) {
//        boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);

        int id = v.getId();
        for (int i = 0; i < viewBinding.menuTab.getChildCount(); i++)
            viewBinding.menuTab.getChildAt(i).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
        if (id == viewBinding.tabOverview.getId()) {
            viewBinding.tabOverview.setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
            callback.onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB.OVERVIEW);
        } else if (id == viewBinding.tabProperty.getId()) {
            viewBinding.tabProperty.setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
            callback.onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB.PROPERTY);
        } else if (id == viewBinding.tabPlacingOrder.getId()) {
            viewBinding.tabPlacingOrder.setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
            callback.onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB.PLACING_ORDER);
        } else if (id == viewBinding.tabEditDelete.getId()) {
            viewBinding.tabEditDelete.setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
            callback.onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB.EDIT_DELETE);
        } else if (id == viewBinding.tabMoneyTransfer.getId()) {
            viewBinding.tabMoneyTransfer.setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
            callback.onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB.MONEY_TRANSFER);
        }
//        for (int i = 0; i < viewBinding.menuTab.getChildCount(); i++)
//            viewBinding.menuTab.getChildAt(i).setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab : R.drawable.bg_item_menu_tab_dark);
//        if (id == viewBinding.tabOverview.getId()) {
//            viewBinding.tabOverview.setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
//            callback.onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB.OVERVIEW);
//        } else if (id == viewBinding.tabProperty.getId()) {
//            viewBinding.tabProperty.setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
//            callback.onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB.PROPERTY);
//        } else if (id == viewBinding.tabPlacingOrder.getId()) {
//            viewBinding.tabPlacingOrder.setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
//            callback.onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB.PLACING_ORDER);
//        } else if (id == viewBinding.tabEditDelete.getId()) {
//            viewBinding.tabEditDelete.setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
//            callback.onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB.EDIT_DELETE);
//        } else if (id == viewBinding.tabMoneyTransfer.getId()) {
//            viewBinding.tabMoneyTransfer.setBackgroundResource(isLight ? R.drawable.bg_item_menu_tab_select : R.drawable.bg_item_menu_tab_select_dark);
//            callback.onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB.MONEY_TRANSFER);
//        }
    }
}
