package mobile.fpts.com.ezmibile.view.watchlist.detail.statistics;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.databinding.FragmentWatchlistStatisticsBinding;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.model.entity.market.Quotes2;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;


public class StatisticsFragment extends FragmentApp implements IStatisticsView {
    private String symbol;
    private FragmentWatchlistStatisticsBinding binding;
    private SharedPreferences preferences;
    private StatisticsPresenter presenter;
    private boolean isLight;

    public StatisticsFragment() {
    }

    public static StatisticsFragment newInstance(String symbol) {
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        args.putString("symbol", symbol);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            symbol = getArguments().getString("symbol");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist_statistics, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
        presenter = new StatisticsPresenter(symbol, this);
        binding.linearLayoutHeaderOwnership.setBackgroundColor(isLight ?
                App.getInstance().getResources().getColor(R.color.colorHeader) :
                App.getInstance().getResources().getColor(R.color.colorHeaderDark));
        binding.linearLayoutHeaderPlacingOrder.setBackgroundColor(isLight ?
                App.getInstance().getResources().getColor(R.color.colorHeader) :
                App.getInstance().getResources().getColor(R.color.colorHeaderDark));
        binding.linearLayoutHeaderPrice.setBackgroundColor(isLight ?
                App.getInstance().getResources().getColor(R.color.colorHeader) :
                App.getInstance().getResources().getColor(R.color.colorHeaderDark));

        binding.imageviewDetailOwnership.setImageResource(isLight ? R.drawable.ic_keyboard_arrow_right_black_24dp :
                R.drawable.ic_keyboard_arrow_right_white_24dp);
        binding.imageviewDetailPlacingOrder.setImageResource(isLight ? R.drawable.ic_keyboard_arrow_right_black_24dp :
                R.drawable.ic_keyboard_arrow_right_white_24dp);
        binding.imageviewDetailPrice.setImageResource(isLight ? R.drawable.ic_keyboard_arrow_right_black_24dp :
                R.drawable.ic_keyboard_arrow_right_white_24dp);
    }

    // TODO:HoaDT 7/5/2018 hiển thị phần thông  tin chung
    @Override
    public void display(StatisticData data) {
        Log.w("StatisticsFragment", "display: " + data.getColor() + "  " +
                data.getColorLow() + "  " + data.getColorHigh());
        binding.textviewPrice.setText(data.getbASICPRICE());
        binding.textviewVolumn.setText(data.gettOTALTRADINGVALUE());
        binding.textviewQty.setText(data.gettOTALTRADINGQTTY());
        binding.textviewCeiling.setText(data.getcEILINGPRICE());
        binding.textviewRef.setText(data.getaVERAGEPRICE());
        binding.textviewFloor.setText(data.getfLOORPRICE());
        binding.textviewOpen.setText(data.getoPENPRICE());
        binding.textviewHighest.setText(data.gethIGHESTPRICE());
        binding.textviewLowest.setText(data.getlOWESTPRICE());
        binding.textviewSell.setText(data.getfOREIGNSELLQTTY());
        binding.textviewBuy.setText(data.getfOREIGNBUYQTTY());
        binding.textviewChange.setText(data.getcHANGEVALUE() + "(" + data.getcHANGEPERCENT() + " %" + ")");

        switch (data.getColor()) {
            case "b":///trắng
                binding.imgIcon.setVisibility(View.GONE);
                binding.textviewChange.setText("");
                break;
            case "c"://tím
                binding.imgIcon.setImageResource(R.drawable.ic_up_violet);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case "u":///green
                binding.imgIcon.setImageResource(R.drawable.ic_up_green);
                binding.textviewChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case "d"://đỏ
                binding.imgIcon.setImageResource(R.drawable.ic_down_red);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case "r"://vàng
                binding.imgIcon.setImageResource(R.drawable.ic_no_change);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case "f"://blue
                binding.imgIcon.setImageResource(R.drawable.ic_down_blue);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
            default:
                binding.imgIcon.setImageResource(R.drawable.ic_down_blue);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
        switch (data.getColorHigh()) {
            case "b":///trắng
                break;
            case "c"://tím
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case "u":///green
                binding.textviewHighest.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case "d"://đỏ
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case "r"://vàng
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case "f"://blue
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
            default:
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
        switch (data.getColorLow()) {
            case "b":///trắng
                break;
            case "c"://tím
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case "u":///green
                binding.textviewLowest.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case "d"://đỏ
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case "r"://vàng
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case "f"://blue
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
            default:
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
    }

    // TODO:HoaDT 7/5/2018 link quotes2
    @Override
    public void display(Quotes2 data, Define.TYPE_CHANGE_APP colorOpen,
                        Define.TYPE_CHANGE_APP colorHighest, Define.TYPE_CHANGE_APP colorLowest) {
        Log.w("StatisticsFragment", "display: " + data.getUpDown());
        binding.textviewPrice.setText(data.getMatchPrice());
        binding.textviewCeiling.setText(data.getCeiling());
        binding.textviewRef.setText(data.getRefPrice());
        binding.textviewFloor.setText(data.getFloor());
        binding.textviewOpen.setText(data.getOpenPrice());
        binding.textviewHighest.setText(data.getHighestPrice());
        binding.textviewLowest.setText(data.getLowestPrice());
        binding.textviewSell.setText(data.getForeignSellQtty());
        binding.textviewBuy.setText(data.getForeignBuyQtty());
        binding.textviewChange.setText(data.getChangePrice() + "(" + " %" + ")");

        switch (data.getUpDown()) {
            case "b":///trắng
                binding.imgIcon.setVisibility(View.GONE);
                binding.textviewChange.setText("");
                break;
            case "c"://tím
                binding.imgIcon.setImageResource(R.drawable.ic_up_violet);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case "u":///green
                binding.imgIcon.setImageResource(R.drawable.ic_up_green);
                binding.textviewChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case "d"://đỏ
                binding.imgIcon.setImageResource(R.drawable.ic_down_red);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case "r"://vàng
                binding.imgIcon.setImageResource(R.drawable.ic_no_change);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case "f"://blue
                binding.imgIcon.setImageResource(R.drawable.ic_down_blue);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
            default:
                binding.imgIcon.setImageResource(R.drawable.ic_down_blue);
                binding.textviewChange.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
        switch (colorOpen) {
            case UP_CEILING:
                binding.textviewOpen.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case UP:
                binding.textviewOpen.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case NO_CHANGE:
                binding.textviewOpen.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case DOWN:
                binding.textviewOpen.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case DOWN_FLOOR:
                binding.textviewOpen.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
        switch (colorHighest) {
            case UP_CEILING:
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case UP:
                binding.textviewHighest.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case NO_CHANGE:
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case DOWN:
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case DOWN_FLOOR:
                binding.textviewHighest.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
        switch (colorLowest) {
            case UP_CEILING:
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                break;
            case UP:
                binding.textviewLowest.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                        App.getInstance().getResources().getColor(R.color.greenDark));
                break;
            case NO_CHANGE:
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                break;
            case DOWN:
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                break;
            case DOWN_FLOOR:
                binding.textviewLowest.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                break;
        }
    }
}
