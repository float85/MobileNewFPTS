package mobile.fpts.com.ezmibile.view.news.home.market_news;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ContentMainBinding;
import mobile.fpts.com.ezmibile.databinding.FragmentTinThiTruongBinding;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.view.news.detail.NewsDetailFragment;
import mobile.fpts.com.ezmibile.view.news.home.INews;
import mobile.fpts.com.ezmibile.view.news.home.NewsAdapter;
// TODO: TamHV 6/28/2018

@SuppressLint("ValidFragment")
public class MarketNewsFragment extends Fragment implements INews.View {
    private INews.Presenter presenter;
    private NewsAdapter adapter;
    FragmentTinThiTruongBinding binding;

    public static MarketNewsFragment newInstance() {
        MarketNewsFragment fragment = new MarketNewsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tin_thi_truong, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.w("MarketNewsFragment", "onViewCreated: ");
        binding.llProgressBar.setVisibility(View.VISIBLE);
        presenter = new MarketNewsPresenter(this);

        presenter.getData();
    }

    @Override
    public void getDataDone(List<NewsArticle> newsList, String folder) {
        adapter = new NewsAdapter(newsList, folder, this);
        binding.lvTinThiTruong.setAdapter(adapter);
        binding.lvTinThiTruong.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();

        binding.llProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void getDataFail() {
        binding.llProgressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "ErrorDatabase", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoad() {
        binding.llProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void moveFragmentView(String id, String img, String folder) {
        ContentMainBinding mainBinding = ContentMainBinding.inflate(LayoutInflater.from(getContext()));

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.contentview, NewsDetailFragment.newInstance(id, img, Define.Market_News));

        transaction.addToBackStack("");
        transaction.commit();
    }
}
