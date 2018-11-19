package mobile.fpts.com.ezmibile.view.watchlist.detail.financeOverview;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemWatchlistFinancialBinding;

/**
 * Created by FIT-thuctap22 on 1/15/2018.
 */

public class EzsReportDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<EzsReportData> data;

    public EzsReportDataAdapter(List<EzsReportData> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWatchlistFinancialBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_watchlist_financial, parent, false);

        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final EzsReportData current = data.get(position);
        final MyHolder myHolder = (MyHolder) holder;
        myHolder.bind(current);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ItemWatchlistFinancialBinding binding;

        public MyHolder(ItemWatchlistFinancialBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(EzsReportData data) {
            binding.label.setText(data.getTITLE() + ":");
            binding.value.setText(data.getBALANCEY1());
//            binding.label.setText(obj.getKeyFinace());
//            binding.value.setText(obj.getValueFinace());
            binding.executePendingBindings();
        }
    }
}
