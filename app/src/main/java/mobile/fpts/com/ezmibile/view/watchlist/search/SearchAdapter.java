package mobile.fpts.com.ezmibile.view.watchlist.search;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemWatchlistSearchRecentBinding;
import mobile.fpts.com.ezmibile.util.Define;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RecyclerViewHolder> {
    private List<String> listData;
    ItemWatchlistSearchRecentBinding binding;
    private ISearchView.IListener listener;

    public SearchAdapter(List<String> listData, ISearchView.IListener listener) {
        this.listData = listData;
        this.listener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_watchlist_search_recent, parent, false);
        View itemview = binding.getRoot();
        return new RecyclerViewHolder(itemview, binding);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (!listData.get(position).equals("")) {
            holder.binding.txtMaCk.setText(listData.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(listData.get(position));
                }
            });
//            holder.binding.img.setImageResource(R.drawable.ic_banggia_search_recent);

//            if (getWhiteBlackFormSPR()) {
//                holder.binding.txtMaCk.setTextColor(App.getInstance().getResources().getColor(R.color.black));
//                holder.binding.img.setImageResource(R.drawable.ic_banggia_search_recent);
//            } else {
//                holder.binding.txtMaCk.setTextColor(App.getInstance().getResources().getColor(R.color.white));
//                holder.binding.img.setImageResource(R.drawable.ic_banggia_search_recent_white);
//            }
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ItemWatchlistSearchRecentBinding binding;

        public RecyclerViewHolder(View itemView, ItemWatchlistSearchRecentBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }

    public boolean getWhiteBlackFormSPR() {
        SharedPreferences pre = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, App.getInstance().MODE_PRIVATE);
        boolean bWhiteBlack = pre.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);

        return bWhiteBlack;
    }
}
