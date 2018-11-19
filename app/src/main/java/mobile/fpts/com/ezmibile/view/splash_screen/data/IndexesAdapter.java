package mobile.fpts.com.ezmibile.view.splash_screen.data;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemSplashScreenIndexesImgBinding;
import mobile.fpts.com.ezmibile.databinding.ItemSplashScreenIndexesItemviewBinding;
import mobile.fpts.com.ezmibile.model.entity.market.VnIndices;
import mobile.fpts.com.ezmibile.view.splash_screen.ISplashScreenView;

public class IndexesAdapter extends RecyclerView.Adapter {
    private List<VnIndices> vnIndices;
    private ISplashScreenView.IIndexesView iIndexesView;
    private ItemSplashScreenIndexesItemviewBinding binding;
    private ItemSplashScreenIndexesImgBinding imgBinding;
    private boolean isLight;

    public IndexesAdapter(List<VnIndices> marketList, boolean isLight, ISplashScreenView.IIndexesView iIndexesView) {
        this.vnIndices = marketList;
        this.iIndexesView = iIndexesView;
        this.isLight = isLight;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == -1) {
            imgBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_splash_screen_indexes_img, parent, false);
            return new IndexesHolderImg(imgBinding);
        }
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_splash_screen_indexes_itemview, parent, false);

        return new IndexesHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == vnIndices.size()) {
            // TODO:HoaDT 6/19/2018 item (image arrow) click to MarketOverviewFragment
            imgBinding.imgIcon.setImageDrawable(isLight ?
                    App.getInstance().getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_black_24dp)
                    : App.getInstance().getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_white_24dp));
            // TODO:HoaDT 6/25/2018
            imgBinding.linearLayoutImg.setOnClickListener(v -> {
                if (iIndexesView != null) {
                    iIndexesView.onIndexesItemClick(position, "");
                }
            });
            return;
        }
//        binding.linearLayout.setBackgroundResource(isLight ? R.drawable.bg_home_indexes_item : R.drawable.bg_home_indexes_item_dark);
        VnIndices indices = vnIndices.get(position);
        binding.textviewValue.setText(indices.getTotalValue());
        binding.textviewPrice.setText(indices.getIndexValue());
        binding.textviewName.setText(indices.getINDEX());
        binding.textviewChange.setText(indices.getChange() != null ? indices.getChange() : "-");
        binding.textviewChangeRatio.setText(indices.getChangePercent() != null ? indices.getChangePercent() : "-");


        binding.textviewChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                App.getInstance().getResources().getColor(R.color.greenDark));
        binding.textviewChangeRatio.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                App.getInstance().getResources().getColor(R.color.greenDark));
        binding.textviewPrice.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                App.getInstance().getResources().getColor(R.color.greenDark));
        // TODO:HoaDT 6/22/2018
        String colorCode = indices.getUpDown();
        if (colorCode == null) {
            binding.textviewChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                    App.getInstance().getResources().getColor(R.color.greenDark));
            binding.textviewChangeRatio.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                    App.getInstance().getResources().getColor(R.color.greenDark));
            binding.textviewPrice.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                    App.getInstance().getResources().getColor(R.color.greenDark));
        } else {
            switch (colorCode) {
                case "u":
                    binding.textviewChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    binding.textviewChangeRatio.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    binding.textviewPrice.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    break;
                case "d":
                    binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                    binding.textviewChangeRatio.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                    binding.textviewPrice.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                    break;
                case "c":
                    binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                    binding.textviewChangeRatio.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                    binding.textviewPrice.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                    break;
                case "r":
                    binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                    binding.textviewChangeRatio.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                    binding.textviewPrice.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                    break;
                case "f":
                    binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                    binding.textviewChangeRatio.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                    binding.textviewPrice.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                    break;
                case "o":
                    binding.textviewChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    binding.textviewChangeRatio.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    binding.textviewPrice.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    break;
                default:
                    binding.textviewChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    binding.textviewChangeRatio.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    binding.textviewPrice.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                            App.getInstance().getResources().getColor(R.color.greenDark));
                    break;
            }
        }
        // TODO:HoaDT 6/25/2018 start DetailMarketFragment
        binding.linearLayout.setOnClickListener(v -> {
            if (iIndexesView != null) {
                Log.w("IndexesAdapter", "onBindViewHolder: "
                        + indices.getINDEX() + " position:  " + position);
                iIndexesView.onIndexesItemClick(position, indices.getINDEX());
            }
        });
    }

    @Override
    public int getItemCount() {
        return vnIndices.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == vnIndices.size())
            return -1;
        return position;
    }

    private class IndexesHolder extends RecyclerView.ViewHolder {
        public IndexesHolder(ItemSplashScreenIndexesItemviewBinding binding) {
            super(binding.getRoot());
        }
    }

    private class IndexesHolderImg extends RecyclerView.ViewHolder {
        public IndexesHolderImg(ItemSplashScreenIndexesImgBinding binding) {
            super(binding.getRoot());
        }
    }
}
