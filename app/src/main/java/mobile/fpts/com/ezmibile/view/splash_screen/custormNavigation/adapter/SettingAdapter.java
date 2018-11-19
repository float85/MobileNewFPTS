package mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemMainMenuRecyclerviewContentBinding;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuCategory;

/**
 * Created by HoaDT  on 4/17/2018.
 */

public class SettingAdapter extends BaseAdapter {
    List<MenuCategory> categories;

    ItemMainMenuRecyclerviewContentBinding settingBinding;

    public List<MenuCategory> getCategories() {
        return categories;
    }

    public SettingAdapter(List<MenuCategory> categories) {
        this.categories = categories;
    }

    public void setCategory(int position, boolean isChecked) {
        categories.get(position).setFavorite(isChecked);
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public MenuCategory getItem(int position) {
        if (position >= 0 && position < categories.size())
            return categories.get(position);
        else return new MenuCategory();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return categories.get(position).getTypeGroup();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        settingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_main_menu_recyclerview_content, parent, false);

        settingBinding.executePendingBindings();

        final MenuCategory category = categories.get(position);
        settingBinding.category.setText(category.getName());
        settingBinding.imageViewIsFavorite.setVisibility(View.VISIBLE);
        settingBinding.imageViewIsFavorite.setImageResource(category.isFavorite() ?
                R.drawable.ic_user_setting_checked_true : R.drawable.ic_user_setting_checked);
//============================
//        settingBinding.setIsFavorite(category.isFavorite());

        return new SettingHolder(settingBinding).itemView;
    }

    class SettingHolder extends RecyclerView.ViewHolder {
        public SettingHolder(ItemMainMenuRecyclerviewContentBinding contentBinding) {
            super(contentBinding.getRoot());
        }
    }

}
