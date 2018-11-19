package mobile.fpts.com.ezmibile.view.watchlist.detail.financeOverview;


import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragmentWatchlistFinanceOverviewBinding;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.view.watchlist.detail.financialFigures.EzsFinanceData;
import mobile.fpts.com.ezmibile.view.watchlist.detail.financialFigures.EzsFinanceDataAdapter;

public class FinanceOverviewFragment extends FragmentApp implements IFinanceOverviewView {
    private String symbol;
    FragmentWatchlistFinanceOverviewBinding binding;

    FinanceOverviewPresenter presenter;

    public FinanceOverviewFragment() {
    }

    public static FinanceOverviewFragment newInstance(String symbol) {
        FinanceOverviewFragment fragment = new FinanceOverviewFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist_finance_overview, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FinanceOverviewPresenter(this, symbol);
    }

    @Override
    public void displayEzsFinance(List<EzsFinanceData> data) {
        Log.w("FinanceOverviewFragment", "displayEzsReport: " + data.size());
        EzsFinanceDataAdapter adapter = new EzsFinanceDataAdapter(data);

        binding.recyclerViewEzsFinance.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayEzsReport(List<EzsReportData> data) {
        Log.w("FinanceOverviewFragment", "displayEzsReport: " + data.size());
        EzsReportDataAdapter adapter = new EzsReportDataAdapter(data);

        binding.recyclerViewEzsReport.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
