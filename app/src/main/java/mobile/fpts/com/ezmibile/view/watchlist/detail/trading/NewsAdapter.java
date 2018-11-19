package mobile.fpts.com.ezmibile.view.watchlist.detail.trading;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemWatchlistNewsBinding;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.Define;

public class NewsAdapter extends RecyclerView.Adapter {
    ItemWatchlistNewsBinding binding;
    List<NewsArticle> newsArticleList = new ArrayList<>();
    ITradingView.INewsListener listener;
    private String symbol = "";

    public NewsAdapter(List<NewsArticle> newsArticleList, String symbol, ITradingView.INewsListener listener) {
        this.newsArticleList = newsArticleList;
        this.symbol = symbol;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_watchlist_news, parent, false);

        return new NewsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsArticle newsArticle = newsArticleList.get(position);
        Log.w("NewsAdapter", "onBindViewHolder:  " + position + " id =  " + newsArticle.getNewsId());
        binding.textviewTitle.setText(newsArticle.getNewsTitle());
        binding.textviewDate.setText(newsArticle.getNewsDate());
        SharedPreferences preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);

        binding.textviewSource.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.blue) :
                App.getInstance().getResources().getColor(R.color.gray));
        binding.textviewDate.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.blue) :
                App.getInstance().getResources().getColor(R.color.gray));
        binding.squareImageviewIcon.setImageDrawable(App.getInstance().getResources().getDrawable(R.drawable.icon_app));
        binding.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(newsArticle.getNewsId(), symbol);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsArticleList.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder {

        public NewsHolder(ItemWatchlistNewsBinding binding) {
            super(binding.getRoot());
        }
    }
}
