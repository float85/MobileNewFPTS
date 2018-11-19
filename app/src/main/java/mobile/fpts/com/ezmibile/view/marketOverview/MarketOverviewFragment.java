package mobile.fpts.com.ezmibile.view.marketOverview;


import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragmentMarketOverviewBinding;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.util.base.ViewPagerAdapter;
import mobile.fpts.com.ezmibile.view.main.MainActivity;
import mobile.fpts.com.ezmibile.view.marketOverview.market.hnx.HNXFragment;
import mobile.fpts.com.ezmibile.view.marketOverview.market.hsx.HSXFragment;

/**
 * Created by HoaDT  on 4/17/2018.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MarketOverviewFragment extends FragmentApp implements IMarketOverviewView {

    FragmentMarketOverviewBinding overviewBinding;
    private ViewPagerAdapter adapter;
    IMarketOverviewView iView;
    FragmentManager manager;
    SharedPreferences preferences;

    public static MarketOverviewFragment newInstance() {
        MarketOverviewFragment fragment = new MarketOverviewFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setBack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.setPosition(Define.TYPE_MENU_MARKET_OVERRVIEW);
        overviewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_market_overview, container, false);
        return overviewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.market_overview);
        iView = this;
        getActivity().runOnUiThread(() -> new MarketOverviewPresenter(iView));
        manager = getActivity().getFragmentManager();//getSupportFragmentManager();

        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
        overviewBinding.viewpager.setBackgroundResource(isLight ? R.color.colorBackground : R.color.colorBackgroundDark);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(HSXFragment.newInstance(), Define.STOCK_ORDER_EXCHANGE_HSX);
        adapter.addFragment(HNXFragment.newInstance(), Define.STOCK_ORDER_EXCHANGE_HNX);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onLoading() {
    }

    @Override
    public void onDisplay() {
        setupViewPager(overviewBinding.viewpager);
        overviewBinding.tabLayout.setupWithViewPager(overviewBinding.viewpager);
        overviewBinding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(overviewBinding.tabLayout));
        adapter.notifyDataSetChanged();
    }
}
