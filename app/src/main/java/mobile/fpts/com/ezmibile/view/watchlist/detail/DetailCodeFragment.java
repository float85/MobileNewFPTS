package mobile.fpts.com.ezmibile.view.watchlist.detail;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragmentWatchlistDetailBinding;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.util.base.ViewPagerAdapter;
import mobile.fpts.com.ezmibile.view.main.MainActivity;
import mobile.fpts.com.ezmibile.view.watchlist.detail.analysis.AnalysisFragment;
import mobile.fpts.com.ezmibile.view.watchlist.detail.financeOverview.FinanceOverviewFragment;
import mobile.fpts.com.ezmibile.view.watchlist.detail.financialFigures.FinancialFiguresFragment;
import mobile.fpts.com.ezmibile.view.watchlist.detail.foreignOwnership.ForeignOwnershipFragment;
import mobile.fpts.com.ezmibile.view.watchlist.detail.statistics.StatisticsFragment;
import mobile.fpts.com.ezmibile.view.watchlist.detail.trading.TradingFragment;


public class DetailCodeFragment extends FragmentApp implements IDetailView {
    private String symbol;
    private boolean bBack;
    FragmentWatchlistDetailBinding detailBinding;
    private ViewPagerAdapter adapter;
    SharedPreferences preferences;
    DetailCodePresenter presenter;

    public static DetailCodeFragment newInstance(String symbol , boolean back) {
        Bundle args = new Bundle();
        args.putString("symbol", symbol);
        args.putBoolean("back", back);
        DetailCodeFragment fragment = new DetailCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            symbol = getArguments().getString("symbol");
            bBack = getArguments().getBoolean("back");
        }
        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.setPosition(Define.TYPE_MENU_DETAIL_PAGE);
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist_detail, container, false);
        return detailBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = (MainActivity) getActivity();
        if (bBack) {
            mainActivity.setBack();
        } else {
            mainActivity.setBackDetail();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new DetailCodePresenter(this, symbol);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(symbol + " " + presenter.getNameCompany());

        setupViewPager(detailBinding.viewpager);
        detailBinding.tablayout.setupWithViewPager(detailBinding.viewpager);
        detailBinding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(detailBinding.tablayout));

        detailBinding.tablayout.setTabTextColors(App.getInstance().getResources().getColor(R.color.while_nhat),
                App.getInstance().getResources().getColor(R.color.colorFont));
        if (preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true)) {
            //Xet mau tab layout
            detailBinding.tablayout.setTabTextColors(App.getInstance().getResources().getColor(R.color.while_nhat),
                    App.getInstance().getResources().getColor(R.color.colorFont));
        } else {
            detailBinding.tablayout.setTabTextColors(App.getInstance().getResources().getColor(R.color.while_nhat),
                    App.getInstance().getResources().getColor(R.color.colorFontDark));
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());//(fragmentActivity.getSupportFragmentManager(), symbol);
        adapter.addFragment(TradingFragment.newInstance(symbol), getString(R.string.trading));
        adapter.addFragment(StatisticsFragment.newInstance(symbol), getString(R.string.statistic));
        adapter.addFragment(ForeignOwnershipFragment.newInstance(symbol), getString(R.string.foreign_ownership));
        adapter.addFragment(AnalysisFragment.newInstance(symbol), getString(R.string.watchlist_analysis));
        adapter.addFragment(FinanceOverviewFragment.newInstance(symbol), getString(R.string.finance_overview));
        adapter.addFragment(FinancialFiguresFragment.newInstance(symbol), getString(R.string.financial_figures));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
