package mobile.fpts.com.ezmibile.view.watchlist.detail.financialFigures;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemWatchlistFinancialBinding;
import mobile.fpts.com.ezmibile.databinding.ItemWatchlistNewsBinding;

public class EzsFinanceDataAdapter extends RecyclerView.Adapter {
    List<EzsFinanceData> ezsFinanceDataList = new ArrayList<>();

    public EzsFinanceDataAdapter(List<EzsFinanceData> ezsFinanceDataList) {
        this.ezsFinanceDataList = ezsFinanceDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWatchlistFinancialBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_watchlist_financial, parent, false);

        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MyHolder) holder).bind(ezsFinanceDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return ezsFinanceDataList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        ItemWatchlistFinancialBinding binding;

        public MyHolder(ItemWatchlistFinancialBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(EzsFinanceData data) {
            binding.label.setText(data.getTITLE() +  ":");
            binding.value.setText(data.getRATIO1());

        }
    }
}
