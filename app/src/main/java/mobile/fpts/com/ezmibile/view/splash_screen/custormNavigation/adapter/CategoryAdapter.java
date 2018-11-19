package mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemMainMenuRecyclerviewContentBinding;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuCategoryChild;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Language;
import mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation.INavView;

import static mobile.fpts.com.ezmibile.util.Define.TYPE_MENU_CATEGORYCHILD;

/**
 * Created by HoaDT  on 4/17/2018.
 */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MenuCategoryChild> categories;
    LayoutInflater inflater;
    ItemMainMenuRecyclerviewContentBinding contentBinding;
    INavView.IOnMenuClickListener listener;

    public CategoryAdapter(List<MenuCategoryChild> categories, Context context,
                           INavView.IOnMenuClickListener listener ) {
        inflater = LayoutInflater.from(context);
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        contentBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_main_menu_recyclerview_content, parent, false);
        return new GroupHolder(contentBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GroupHolder) holder).bind(categories.get(position));
        holder.itemView.setOnClickListener(v -> {
            listener.onClickItemListener(categories.get(position).getId(), 0, TYPE_MENU_CATEGORYCHILD);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public int getItemViewType(int position) {
        return categories.get(position).getCategoryId();
    }

    class GroupHolder extends RecyclerView.ViewHolder {
        ItemMainMenuRecyclerviewContentBinding contentBinding;
        boolean isLight = true;

        public GroupHolder(ItemMainMenuRecyclerviewContentBinding contentBinding) {
            super(contentBinding.getRoot());
            this.contentBinding = contentBinding;
            this.isLight = isLight;
        }

        public void bind(MenuCategoryChild child) {
            Define.LANGUAGE_APP language = Language.getLanguage();
            contentBinding.category.setText(language == Define.LANGUAGE_APP.LANGUAGE_EN
                    ? child.getName_en() : child.getName());
        }
    }
}
