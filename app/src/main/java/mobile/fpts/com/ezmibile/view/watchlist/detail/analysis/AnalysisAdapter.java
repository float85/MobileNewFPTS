package mobile.fpts.com.ezmibile.view.watchlist.detail.analysis;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.databinding.ItemWatchlistNewsBinding;
import mobile.fpts.com.ezmibile.R;

public class AnalysisAdapter extends RecyclerView.Adapter {
    List<AnalysisData> dataList;

    public AnalysisAdapter(List<AnalysisData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWatchlistNewsBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_watchlist_news, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyHolder) holder).bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ItemWatchlistNewsBinding binding;

        public MyHolder(ItemWatchlistNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(AnalysisData data) {
            binding.imgMessage.setVisibility(View.VISIBLE);
            binding.textviewMessage.setVisibility(View.VISIBLE);
            binding.textviewTitle.setText(data.getTitleAnalysis());
            binding.textviewDate.setText(data.getTimeAnalysis() + " |");
            binding.textviewSource.setText(data.getSourceAnalysis() + " |");
            binding.squareImageviewIcon.setImageDrawable(App.getInstance().getResources().getDrawable(R.drawable.icon_app));
        }
    }
}
