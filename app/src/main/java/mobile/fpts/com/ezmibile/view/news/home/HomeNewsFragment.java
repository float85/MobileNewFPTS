package mobile.fpts.com.ezmibile.view.news.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragmentHomeNewsBinding;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.ViewPagerAdapter;
//import mobile.fpts.com.ezmibile.view.news.home.analysis_comment.AnalysisCommentFragment;
import mobile.fpts.com.ezmibile.view.main.MainActivity;
import mobile.fpts.com.ezmibile.view.news.home.analysis_comment.AnalysisCommentFragment;
import mobile.fpts.com.ezmibile.view.news.home.company_news.CompanyFragment;
import mobile.fpts.com.ezmibile.view.news.home.department_report.DepartmentReportFragment;
import mobile.fpts.com.ezmibile.view.news.home.fpts_news.FPTSNewsFragment;
import mobile.fpts.com.ezmibile.view.news.home.market_news.MarketNewsFragment;

/**
 * Created by TrangDTH on 2/22/2018.
 */
public class HomeNewsFragment extends Fragment implements INews.HomeNewsView {
    private ViewPagerAdapter viewPagerAdapter;
    private HomeNewsPresenter presenter;
    private List<NewsArticle> newsList;
    private FragmentHomeNewsBinding binding;

    public static HomeNewsFragment newInstance() {
        HomeNewsFragment fragment = new HomeNewsFragment();
        App.setPosition(Define.TYPE_MENU_NEWS);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_news, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.news);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomeNewsPresenter(this);
        binding.llProgressBar.setVisibility(View.VISIBLE);
        // TODO: TamHV 6/28/2018
//        presenter.getData();
        //Check theme black
        SharedPreferences shared = getActivity().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        boolean isLight = shared.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
        if (isLight) {
            //Xet mau tab layout
            binding.tabs.setTabTextColors(getResources().getColor(R.color.while_nhat), getResources().getColor(R.color.colorFont));
        } else {
            binding.tabs.setTabTextColors(getResources().getColor(R.color.while_nhat), getResources().getColor(R.color.colorFontDark));
        }

        getDataSuccess();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setBack();
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(MarketNewsFragment.newInstance(), getString(R.string.market_news));
        viewPagerAdapter.addFragment(DepartmentReportFragment.newInstance(), getString(R.string.department_report));
        viewPagerAdapter.addFragment(CompanyFragment.newInstance(), getString(R.string.concern));
        viewPagerAdapter.addFragment(AnalysisCommentFragment.newInstance(), getString(R.string.analysis_comment));
        viewPagerAdapter.addFragment(FPTSNewsFragment.newInstance(), getString(R.string.FPTS_news));
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void getDataSuccess() {
        Log.w("HomeNewsFragment", "getDataSuccess: ");
        setupViewPager(binding.viewPager);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
        binding.llProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void getDataFail() {
        Log.w("HomeNewsFragment", "getDataFail: ");
        Toast.makeText(getContext(), "Data Fail", Toast.LENGTH_SHORT).show();
        binding.llProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void getConnectServerFail() {
        Log.w("HomeNewsFragment", "getConnectServerFail: ");
        Toast.makeText(getContext(), "Connect Server Fail", Toast.LENGTH_SHORT).show();
        binding.llProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void getConnectNetworkFail() {
        Log.w("HomeNewsFragment", "getConnectNetworkFail: ");
//        Toast.makeText(getContext(), "Connect Network Fail", Toast.LENGTH_SHORT).show();
        binding.llProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoad(boolean b) {
        Log.w("HomeNewsFragment", "onLoad: ");
        if (b) {
            binding.llProgressBar.setVisibility(View.VISIBLE);
        } else {
            binding.llProgressBar.setVisibility(View.GONE);
        }
    }
}
