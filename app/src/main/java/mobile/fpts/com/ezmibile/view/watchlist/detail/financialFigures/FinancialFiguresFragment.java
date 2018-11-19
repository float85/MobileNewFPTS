package mobile.fpts.com.ezmibile.view.watchlist.detail.financialFigures;

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
import mobile.fpts.com.ezmibile.databinding.FragmentWatchlistFinancialFiguresBinding;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;

public class FinancialFiguresFragment extends FragmentApp implements IFinancialFiguresView {
    private String symbol;

    FinancialFiguresPresenter presenter;
    FragmentWatchlistFinancialFiguresBinding binding;

    public FinancialFiguresFragment() {
    }

    public static FinancialFiguresFragment newInstance(String symbol) {
        FinancialFiguresFragment fragment = new FinancialFiguresFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist_financial_figures, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FinancialFiguresPresenter(this, symbol);
    }

    @Override
    public void display(List<EzsFinanceData> data) {
        Log.w("FinancialFigFragment", "display: " + data.size());
        EzsFinanceDataAdapter adapter = new EzsFinanceDataAdapter(data);

        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
