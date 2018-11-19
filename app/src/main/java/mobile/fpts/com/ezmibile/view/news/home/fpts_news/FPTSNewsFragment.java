package mobile.fpts.com.ezmibile.view.news.home.fpts_news;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.view.news.detail.NewsDetailFragment;
import mobile.fpts.com.ezmibile.view.news.home.INews;
import mobile.fpts.com.ezmibile.view.news.home.NewsAdapter;

// TODO: TamHV 6/28/2018
@SuppressLint("ValidFragment")
public class FPTSNewsFragment extends Fragment implements INews.View {
    private INews.Presenter presenter;
    private NewsAdapter adapter;

    //Anh xa
    private RecyclerView lvTinTuc;
    private LinearLayout ll_progressBar;

    public static FPTSNewsFragment newInstance() {
        FPTSNewsFragment fragment = new FPTSNewsFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tin_thi_truong, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvTinTuc = (RecyclerView) view.findViewById(R.id.lvTinThiTruong);
        ll_progressBar = (LinearLayout) view.findViewById(R.id.ll_progressBar);
        presenter = new FPTSNewsPresenter(this);
        presenter.getData();
    }

    @Override
    public void getDataDone(List<NewsArticle> newsList, String folder) {
        adapter = new NewsAdapter(newsList, folder, this);
        lvTinTuc.setAdapter(adapter);
        lvTinTuc.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();

        ll_progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getDataFail() {
        ll_progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "ErrorDatabase", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoad() {
        ll_progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void moveFragmentView(String id, String img, String folder) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentview, NewsDetailFragment.newInstance(id, img, Define.FPTS_News));
        transaction.addToBackStack("");
        transaction.commit();
    }
}
