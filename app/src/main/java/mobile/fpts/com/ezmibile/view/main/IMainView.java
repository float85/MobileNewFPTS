package mobile.fpts.com.ezmibile.view.main;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.events.EventsApp;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;
import mobile.fpts.com.ezmibile.view.watchlist.search.CustomerAutoComleteTextView;

public interface IMainView {
    void display();

    interface IMainPresenterView {
        void LoadEvents();

        void LoadWorldIndexes();

        void LoadNews();

        void getLanguage();

        void setLanguage();

        void setEvents(List<EventsApp> eventsList);

        void setNews(List<NewsArticle> newsArticleList);

        void setWorldList(List<WorldIndices> worldIndicesList);

    }

    interface IMainListener {

        void onReplaceFragment(int typeFragmet);
    }

    interface ISearch {
        void setDataSearchStock(ArrayList<CustomerAutoComleteTextView> list);
    }
}
