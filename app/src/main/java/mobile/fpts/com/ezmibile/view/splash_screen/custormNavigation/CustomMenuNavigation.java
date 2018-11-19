package mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemMainMenuBiggroupHeaderAccountBinding;
import mobile.fpts.com.ezmibile.databinding.ItemMainMenuRecyclerviewContentBinding;
import mobile.fpts.com.ezmibile.databinding.NavUserLayoutBinding;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuBigGroup;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuCategory;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.ErrorApp;
import mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation.adapter.BigGroupAdapter;
import mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation.adapter.SettingAdapter;
import mobile.fpts.com.ezmibile.view.main.IMainView;

import static android.view.LayoutInflater.from;

/**
 * Created by HoaDT  on 4/17/2018.
 */

public class CustomMenuNavigation implements INavView, AdapterView.OnItemClickListener,
        View.OnClickListener, INavView.IOnMenuClickListener {

    private Activity activity;
    private SettingAdapter settingAdapter;
    private IMainView.IMainListener listener;
    private NavUserLayoutBinding binding;
    private SharedPreferences preferences;
    private NavPresenter presenter;
    boolean isLight;
    private BigGroupAdapter listAdapter;

    public CustomMenuNavigation(Activity activity, IMainView.IMainListener listener,
                                NavUserLayoutBinding binding) {
        this.activity = activity;
        this.listener = listener;
        this.binding = binding;
        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
    }

    public void onStart() {

        Log.w("CustomMenuNavigation", "onStart: ");
        presenter = new NavPresenter(App.getInstance(), this);
        binding.listviewSetting.setOnItemClickListener(this);
        binding.linearlayoutSetting.setBackgroundColor(isLight ?
                App.getInstance().getResources().getColor(R.color.colorBackground)
                : App.getInstance().getResources().getColor(R.color.colorContentDark));
        binding.linearLayout.setBackgroundColor(isLight ?
                App.getInstance().getResources().getColor(R.color.colorHeader)
                : App.getInstance().getResources().getColor(R.color.colorHeaderDark));
        binding.expandedListview.setBackgroundColor(isLight ?
                App.getInstance().getResources().getColor(R.color.colorBackground)
                : App.getInstance().getResources().getColor(R.color.colorContentDark));
        binding.expandedListview.setChildIndicator(null);
        binding.expandedListview.setDividerHeight(0);
        binding.actionSave.setOnClickListener(this);
    }

    @Override
    public void onDisplay(SparseArray<MenuBigGroup> groups) {
        Log.w("CustomMenuNavigation", "onDisplay: ");
        binding.linearlayoutContent.setVisibility(View.VISIBLE);
        binding.linearlayoutSetting.setVisibility(View.GONE);
        listAdapter = new BigGroupAdapter(groups, this, this, isLight);
        binding.expandedListview.setAdapter(listAdapter);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            binding.expandedListview.expandGroup(i);
        }
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSetting(List<MenuCategory> categories) {
        binding.linearlayoutContent.setVisibility(View.GONE);
        binding.linearlayoutSetting.setVisibility(View.VISIBLE);
        settingAdapter = new SettingAdapter(categories);
        binding.listviewSetting.setAdapter(settingAdapter);
        settingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSetFragment(int typeFragmet) {
        listener.onReplaceFragment(typeFragmet);
    }

    @Override
    public void onDisplay() {

    }

    @Override
    public void onError(ErrorApp errorApp) {
        switch (errorApp) {
            case ERROR_CONNECT_SERVER:
                break;
            case ERROR_NETWORK:
                break;
            case NULL:
                break;
            case EXCEPTION:
                break;
            case ERROR_REQUEST_LOGIN:
                Toast.makeText(activity, "request login", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //settings
        MenuCategory category = settingAdapter.getItem(position);
        category.setFavorite(!category.isFavorite());
        ItemMainMenuRecyclerviewContentBinding contentBinding =
                ItemMainMenuRecyclerviewContentBinding.inflate(from(parent.getContext()), parent, false);
        contentBinding.imageViewIsFavorite.setVisibility(View.VISIBLE);
        contentBinding.imageViewIsFavorite.setImageResource(category.isFavorite() ?
                R.drawable.ic_user_setting_checked_true : R.drawable.ic_user_setting_checked);
        contentBinding.executePendingBindings();

        settingAdapter.setCategory(position, category.isFavorite());
        settingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        ItemMainMenuBiggroupHeaderAccountBinding accountBinding =
                ItemMainMenuBiggroupHeaderAccountBinding.inflate(from(App.getInstance()));
        if (v.getId() == accountBinding.imgSetting.getId()) {
            presenter.onSetting();
        } else if (v.getId() == binding.actionSave.getId()) {
            presenter.onUpdateNav(settingAdapter.getCategories());
        }else if(v.getId() == accountBinding.imgLogout.getId()){

        }
    }

    @Override
    public void onClickItemListener(int id, int typeGroup, int typeCategory) {
        presenter.onClickListener(id, typeGroup, typeCategory);
    }
}
