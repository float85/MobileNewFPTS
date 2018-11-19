package mobile.fpts.com.ezmibile.view.event.tablayout;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragEventsTablayoutBinding;
import mobile.fpts.com.ezmibile.model.entity.events.EventsApp;
import mobile.fpts.com.ezmibile.util.Define;

public class ThisWeekEventsFragment extends Fragment {
    FragEventsTablayoutBinding binding;
    EventTabAdapter adapter;
    boolean bBlackWhite;
    ArrayList<mobile.fpts.com.ezmibile.model.entity.events.EventsApp> eventsAppList = new ArrayList<>();

    public static ThisWeekEventsFragment newInstance(List<EventsApp> list ) {
        ThisWeekEventsFragment fragment = new ThisWeekEventsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listDataFormRoom", (ArrayList<EventsApp>) list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            List<mobile.fpts.com.ezmibile.model.entity.events.EventsApp> events = new ArrayList<>();

            events = getArguments().getParcelableArrayList("listDataFormRoom");
            bBlackWhite = getArguments().getBoolean("bBlackWhite");

            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getGroupNm().equals(Define.TAB_THISWEEK_GROUPNM)) {
                    eventsAppList.add(new mobile.fpts.com.ezmibile.model.entity.events.EventsApp(events.get(i).getiDX(), events.get(i).getGroupNm(), events.get(i).getiD(),
                            events.get(i).getStockCode(), events.get(i).getContent(), events.get(i).getUrl(), events.get(i).getDate1()));
                }
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new EventTabAdapter(eventsAppList, true);
        binding.recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_events_tablayout, container, false);

        return binding.getRoot();
    }

}
