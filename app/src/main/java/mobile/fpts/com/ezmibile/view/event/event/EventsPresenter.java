package mobile.fpts.com.ezmibile.view.event.event;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.events.EventsApp;
import mobile.fpts.com.ezmibile.model.entity.events.EventsDB;
import mobile.fpts.com.ezmibile.util.Define;
import rx.subscriptions.CompositeSubscription;

import static android.arch.persistence.room.Room.databaseBuilder;

public class EventsPresenter {
    IEventsView.View view;
    private AppDatabase database;
    private static String CATEGORY_SHARED_PREFERENCES_ITEM_EVENTS = "CATEGORY_SHARED_PREFERENCES_ITEM_EVENTS";
    CompositeSubscription subscription = new CompositeSubscription();
    ArrayList<EventsApp> listAdapter = new ArrayList<>();

    public EventsPresenter(IEventsView.View view) {
        this.view = view;
        database = databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public ArrayList<EventsApp> getDataFormRoom() {
        List<EventsDB> listsDataRoom = database.eventDao().getAllEventsDB();
        for (int i = 0; i < listsDataRoom.size(); i++) {
            if (listsDataRoom.get(i).getEventGroupNm().equals(Define.TAB_NEXTWEEK_GROUPNM)) {
                listAdapter.add(new EventsApp(listsDataRoom.get(i).getEventId(),
                        listsDataRoom.get(i).getEventGroupNm(), listsDataRoom.get(i).getEventIDX(),
                        listsDataRoom.get(i).getEventStockCode(), listsDataRoom.get(i).getEventContent(),
                        listsDataRoom.get(i).getEventUrl(), listsDataRoom.get(i).getEventDate1()));
            }
        }
        return listAdapter;
    }

    public boolean getDataBlackWhiteFormSharedPreferences() {
        SharedPreferences pre = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP,
                App.getInstance().MODE_PRIVATE);
        return pre.getBoolean(CATEGORY_SHARED_PREFERENCES_ITEM_EVENTS, true);
    }
}
