package mobile.fpts.com.ezmibile.view.marketOverview.market;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemMarketOverviewRecyclerviewMarketBinding;
import mobile.fpts.com.ezmibile.model.entity.market.VnIndices;
import mobile.fpts.com.ezmibile.util.Define;

import static mobile.fpts.com.ezmibile.App.getInstance;

/**
 * Created by HoaDT  on 4/17/2018.
 */
public class VnIndicesAdapter extends RecyclerView.Adapter {
    private List<VnIndices> vnIndicesList;
    VnIndicesAdapterPresenter presenter;
    ItemMarketOverviewRecyclerviewMarketBinding marketBinding;
    IMarket_View.IOnclickListener listener;
    SharedPreferences preferences;

    boolean isLight;
    boolean isCheck = false;

    public VnIndicesAdapter(List<VnIndices> vnIndicesList, IMarket_View.IOnclickListener listener) {
        this.vnIndicesList = vnIndicesList;
        this.listener = listener;
        presenter = new VnIndicesAdapterPresenter();
        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        marketBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_market_overview_recyclerview_market, parent, false);
        return new MarketHolder(marketBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VnIndices tem = vnIndicesList.get(position);
        ((MarketHolder) holder).bind(tem);
    }

    @Override
    public int getItemViewType(int position) {
        return vnIndicesList.get(position).getIsTypeVnIndices();
    }

    @Override
    public int getItemCount() {
        return vnIndicesList.size();
    }

    class MarketHolder extends RecyclerView.ViewHolder {
        ItemMarketOverviewRecyclerviewMarketBinding binding;

        public MarketHolder(ItemMarketOverviewRecyclerviewMarketBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(VnIndices tem) {
            switch (tem.getUpDown()) {
                case "u":
                    binding.textviewMarketChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    binding.textviewMarketLastPrice.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    break;
                case "r":
                    binding.textviewMarketChange.setTextColor(getInstance().getResources().getColor(R.color.orange));
                    binding.textviewMarketLastPrice.setTextColor(getInstance().getResources().getColor(R.color.orange));
                    break;
                case "d":
                    binding.textviewMarketChange.setTextColor(getInstance().getResources().getColor(R.color.red));
                    binding.textviewMarketLastPrice.setTextColor(getInstance().getResources().getColor(R.color.red));
                    break;
                default:
                    binding.textviewMarketChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    binding.textviewMarketLastPrice.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    break;
            }
            binding.textviewMarketName.setText(tem.getINDEX());
            binding.textviewMarketLastPrice.setText(tem.getIndexValue());
            if (preferences.getBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISCHANGE, true))
                binding.textviewMarketChange.setText(tem.getChange());
            else {
                binding.textviewMarketChange.setText(tem.getChangePercent());
            }
            if (preferences.getBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISVALUE, true))
                binding.textviewMarketValue.setText(tem.getTotalValue());
            else
                binding.textviewMarketValue.setText(tem.getTotalQtty());

            binding.contentview.setOnClickListener(v -> listener.onItemClick(tem.getINDEX()));
            isCheck = presenter.getChecked(tem.getINDEX());
            // TODO:HoaDT 6/19/2018 comment tính năng check thị trường để hiển thị thông tin ra ngoài trang home
            if (isCheck) {
                binding.imageChecked.setImageResource(isLight ? R.drawable.ic_star_black_24dp : R.drawable.ic_star_white_24dp);
            } else {
                binding.imageChecked.setImageResource(isLight ? R.drawable.ic_star_border_black_24dp : R.drawable.ic_star_border_white_24dp);
            }

            // TODO:HoaDT 6/25/2018 Click to images to save database
            binding.imageChecked.setOnClickListener(v -> {
                Log.w("MarketHolder", "bind: " + (isCheck ? "true " : " false"));
                isCheck = !presenter.getChecked(tem.getINDEX());// !isCheck;
                if (isCheck) {
                    binding.imageChecked.setImageResource(
                            isLight ? R.drawable.ic_star_black_24dp : R.drawable.ic_star_white_24dp);
                    tem.setChecked(true);
                    listener.onItemClick(tem.getINDEX(), true);
                    notifyDataSetChanged();

                } else {
                    binding.imageChecked.setImageResource(
                            isLight ? R.drawable.ic_star_border_black_24dp : R.drawable.ic_star_border_white_24dp);
                    listener.onItemClick(tem.getINDEX(), false);
                    tem.setChecked(false);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
