package mobile.fpts.com.ezmibile.view.watchlist.detail.foreignOwnership;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragmentWatchlistForeignOwnershipBinding;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.view.watchlist.detail.DetailCodeData;
import mobile.fpts.com.ezmibile.view.watchlist.detail.statistics.StatisticData;


public class ForeignOwnershipFragment extends FragmentApp implements IForeignOwnershipView {
    FragmentWatchlistForeignOwnershipBinding binding;
    private String symbol;
    ForeignOwnershipPresenter presenter;

    public static ForeignOwnershipFragment newInstance(String symbol) {
        ForeignOwnershipFragment fragment = new ForeignOwnershipFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist_foreign_ownership,
                container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new ForeignOwnershipPresenter(this, symbol);
    }

    @Override
    public void display(DetailCodeData detail) {
        // TODO:HoaDT 6/21/2018 lấy dữ liệu chung vào link chi tiết mã
        binding.khoiluongHienTai.setText(detail.getQty());
//        binding.khoiluongDuocMua.setText(statisticData.getrOOMTOTAL());
//        binding.khoiluongConLai.setText(detail/get);
        binding.tyleSoHuu.setText(detail.getTLSHNN());
        binding.nnMuaYTD.setText(detail.getNNMUA_YTD());
        binding.nnMuaYTD30ngay.setText(detail.getNNMUA_YTD30());
        binding.timeUpdate.setText(detail.getDate());
    }
}