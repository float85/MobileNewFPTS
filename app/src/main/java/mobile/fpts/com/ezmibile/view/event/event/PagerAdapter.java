package mobile.fpts.com.ezmibile.view.event.event;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.model.entity.events.EventsApp;
import mobile.fpts.com.ezmibile.view.event.tablayout.NextWeekEventsFragment;
import mobile.fpts.com.ezmibile.view.event.tablayout.ThisWeekEventsFragment;
import mobile.fpts.com.ezmibile.view.event.tablayout.TodayEventsFragment;
import mobile.fpts.com.ezmibile.view.event.tablayout.TomorrowEventsFragment;
import mobile.fpts.com.ezmibile.view.event.tablayout.YesterdayEventsFragment;

/**
 * Created by dinht on 2/9/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<EventsApp> eventsAppList;

    public PagerAdapter(FragmentManager fm, ArrayList<EventsApp> eventsAppList) {
        super(fm);
        this.eventsAppList = eventsAppList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = YesterdayEventsFragment.newInstance(eventsAppList);
                break;
            case 1:
                frag = TodayEventsFragment.newInstance(eventsAppList);
                break;
            case 2:
                frag = TomorrowEventsFragment.newInstance(eventsAppList);
                break;
            case 3:
                frag = ThisWeekEventsFragment.newInstance(eventsAppList);
                break;
            case 4:
                frag = NextWeekEventsFragment.newInstance(eventsAppList);
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = App.getInstance().getString(R.string.events_yesterday);
                break;
            case 1:
                title = App.getInstance().getString(R.string.events_today);
                break;
            case 2:
                title = App.getInstance().getString(R.string.events_tomorrow);
                break;
            case 3:
                title = App.getInstance().getString(R.string.events_thisweek);
                break;
            case 4:
                title = App.getInstance().getString(R.string.events_nextweek);
                break;
        }
        return title;
    }
}
