package mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemMainMenuBiggroupContentBinding;
import mobile.fpts.com.ezmibile.databinding.ItemMainMenuBiggroupHeaderAccountBinding;
import mobile.fpts.com.ezmibile.databinding.ItemMainMenuBiggroupHeaderBinding;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.Group;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuBigGroup;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Language;
import mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation.INavView;

import static mobile.fpts.com.ezmibile.util.Define.TYPE_DATABASE_SETTING;
import static mobile.fpts.com.ezmibile.util.Define.TYPE_MENU_CATEGORY;


/**
 * Created by HoaDT  on 4/17/2018.
 */
public class BigGroupAdapter extends BaseExpandableListAdapter implements INavView.IOnMenuClickListener {
    private SparseArray<MenuBigGroup> sparseArray;
    //    CategoryAdapter listAdapter;
    private boolean isLight = true;
    private View.OnClickListener listener;
    private INavView.IOnMenuClickListener iOnMenuClickListener;
    private List<View> accountView = new ArrayList<>();
    private List<View> headerView = new ArrayList<>();
    private List<List<View>> childView = new ArrayList<>();

    public BigGroupAdapter(SparseArray<MenuBigGroup> sparseArray,
                           View.OnClickListener listener, INavView.IOnMenuClickListener iOnMenuClickListener,
                           boolean isLight) {
        this.sparseArray = sparseArray;
        this.listener = listener;
        this.isLight = isLight;
        this.iOnMenuClickListener = iOnMenuClickListener;
        for (int i = 0; i < sparseArray.size(); i++) {
            childView.add(new ArrayList<>());
        }
    }

    @Override
    public int getGroupCount() {
        return sparseArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return sparseArray.get(groupPosition).getGroupSparseArray().size();
    }

    @Override
    public MenuBigGroup getGroup(int groupPosition) {
        return sparseArray.get(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public Group getChild(int groupPosition, int childPosition) {
        return sparseArray.get(groupPosition).getGroupSparseArray().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        if (groupPosition == 0) {
            //ACCOUNT
            if (accountView.size() > groupPosition) {
                return accountView.get(groupPosition);
            }
            ItemMainMenuBiggroupHeaderAccountBinding accountBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.item_main_menu_biggroup_header_account, parent, false);
            accountBinding.imgSetting.setOnClickListener(listener);
            accountBinding.imgLogout.setOnClickListener(listener);
            accountView.add(0, new AccountHolder(accountBinding).itemView);
            return accountView.get(0);
//            return new AccountHolder(accountBinding).itemView;
        }
        //------------------------------------------------------
        if (headerView.size() > groupPosition) {
            return headerView.get(groupPosition - 1);
        }
        final ItemMainMenuBiggroupHeaderBinding headerBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_main_menu_biggroup_header, parent, false);
        headerBinding.header.setText(sparseArray.get(groupPosition).getTitle());
        headerView.add(new HeaderHolder(headerBinding).itemView);
        headerBinding.category.setBackgroundResource(isLight ? R.drawable.bg_textview_layout : R.drawable.bg_textview_layout_dark);
        return headerView.get(headerView.size() - 1);
//        return new HeaderHolder(headerBinding).itemView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if (childView.get(groupPosition).size() > childPosition) {
            return childView.get(groupPosition).get(childPosition);
        }
        Define.LANGUAGE_APP language = Language.getLanguage();
        final Group group = getChild(groupPosition, childPosition);
        //Settings
        if (group.getCategory().getTypeGroup() == TYPE_DATABASE_SETTING) {
        }
        //Main
        final ItemMainMenuBiggroupContentBinding contentBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_main_menu_biggroup_content, parent, false);
        contentBinding.header.setText(language == Define.LANGUAGE_APP.LANGUAGE_EN ? group.getCategory().getName_en()
                : group.getCategory().getName());
        if (group.getCategories().size() > 0) {
            contentBinding.imageviewArrow.setVisibility(View.VISIBLE);
            if (contentBinding.recyclerView.getVisibility() == View.VISIBLE)
                contentBinding.viewDivider.setVisibility(View.VISIBLE);
        }
        CategoryAdapter listviewAdapter = new CategoryAdapter(group.getCategories(), App.getInstance(), this);
        contentBinding.recyclerView.setAdapter(listviewAdapter);
        contentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(App.getInstance()));
        contentBinding.linearlayoutHeader.setOnClickListener(v -> {
            if (group.getCategories().size() > 0) {
                if (contentBinding.recyclerView.getVisibility() == View.GONE) {
                    contentBinding.imageviewArrow.setImageDrawable(App.getInstance().getResources().getDrawable(R.drawable.ic_arrow_drop_up_black_24dp));
                    contentBinding.recyclerView.setVisibility(View.VISIBLE);
                    contentBinding.viewDivider.setVisibility(View.VISIBLE);
                } else {
                    contentBinding.imageviewArrow.setImageDrawable(App.getInstance().getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp));
                    contentBinding.recyclerView.setVisibility(View.GONE);
                    contentBinding.viewDivider.setVisibility(View.GONE);
                }
            } else {
                contentBinding.imageviewArrow.setVisibility(View.GONE);
                iOnMenuClickListener.onClickItemListener(group.getCategory().getId(), group.getCategory().getTypeGroup(), TYPE_MENU_CATEGORY);
            }
        });
        childView.get(groupPosition).add(new ChildHolder(contentBinding).itemView);
        return childView.get(groupPosition).get(childView.get(groupPosition).size() - 1);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onClickItemListener(int id, int typeGroup, int typeCategory) {
        iOnMenuClickListener.onClickItemListener(id, typeGroup, typeCategory);
    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        ItemMainMenuBiggroupHeaderBinding binding;

        public HeaderHolder(ItemMainMenuBiggroupHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class ChildHolder extends RecyclerView.ViewHolder {
        ItemMainMenuBiggroupContentBinding binding;

        public ChildHolder(ItemMainMenuBiggroupContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class AccountHolder extends RecyclerView.ViewHolder {
        public AccountHolder(ItemMainMenuBiggroupHeaderAccountBinding accountBinding) {
            super(accountBinding.getRoot());
        }
    }
}
