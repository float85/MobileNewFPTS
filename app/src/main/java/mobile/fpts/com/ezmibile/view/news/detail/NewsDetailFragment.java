package mobile.fpts.com.ezmibile.view.news.detail;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.List;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragmentNewsDetailBinding;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.ErrorApp;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.view.main.MainActivity;
import mobile.fpts.com.ezmibile.view.news.detail.related_news.RelatedNewsDetailAdapter;

public class NewsDetailFragment extends FragmentApp implements INewsDetail {
    private static final String ARG_NEWSID = "ARG_NEWSID";
    private static final String ARG_SYMBOL = "ARG_SYMBOL";
    private static final String ARG_STYPE = "ARG_SYMBOL";
    private static final String ARG_BACK = "ARG_BACK";
    private FragmentNewsDetailBinding detailBinding;
    private String newsID = "";
    private String symbol = "";
    private String stype = "";
    private NewDetailPresenter presenter;

    public static NewsDetailFragment newInstance(String newsID, String symbol, String stype) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NEWSID, newsID);
        args.putString(ARG_SYMBOL, symbol);
        args.putString(ARG_STYPE, stype);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newsID = getArguments().getString(ARG_NEWSID);
            symbol = getArguments().getString(ARG_SYMBOL);
            stype = getArguments().getString(ARG_STYPE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setBackDetail();
    }

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_detail, container, false);

        return detailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setTitle(Html.fromHtml(getResources().getString(R.string.news_details)));
        presenter = new NewDetailPresenter(this, newsID, symbol, stype);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onDisplay(String title, String body, String link) {
        Log.w("NewsDetailFragment", "onDisplay: " + link);
//
        detailBinding.imgNewsDetail.setImageResource(R.drawable.icon_app);
        detailBinding.wvTitle.setText(Html.fromHtml(title));
        detailBinding.wvBody.setText(Html.fromHtml(body));

        if (link != null && link != "") {
            detailBinding.wvOpenfile.setVisibility(android.view.View.VISIBLE);
            detailBinding.wvOpenfile.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    startActivity(intent);
                }
            });
        }
        detailBinding.linearLayoutProgressBar.setVisibility(android.view.View.GONE);
        detailBinding.llDetail.setVisibility(android.view.View.VISIBLE);
        presenter.getListRelatedNews();
    }

    @Override
    public void onDisplayRelated(List<NewsArticle> newsArticles) {
        Log.w("NewsDetailFragment", "onDisplayRelated: ");
        RelatedNewsDetailAdapter adapter = new RelatedNewsDetailAdapter(newsArticles, this);
        detailBinding.relativeLayoutRelativedNews.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        detailBinding.linearLayoutProgressBar.setVisibility(android.view.View.GONE);
        detailBinding.tvTitleTinlienquan.setVisibility(android.view.View.VISIBLE);
        detailBinding.llDetail.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void onLoadError(ErrorApp error) {
        Log.w("NewsDetailFragment", "onLoadError: " + error.name());
    }

    @Override
    public void moveFragment(String id, String img) {

        this.newsID = id;
        detailBinding.llDetail.setVisibility(android.view.View.GONE);
        detailBinding.linearLayoutProgressBar.setVisibility(android.view.View.VISIBLE);

        presenter.callData(id);
        //dua len tren dau
        detailBinding.scrollviewNewsDetail.scrollTo(0, 0);
        detailBinding.scrollviewNewsDetail.getParent().requestChildFocus(
                detailBinding.scrollviewNewsDetail, detailBinding.scrollviewNewsDetail);
        detailBinding.scrollviewNewsDetail.fullScroll(ScrollView.FOCUS_UP);
        detailBinding.scrollviewNewsDetail.smoothScrollTo(0, 0);
    }
}
