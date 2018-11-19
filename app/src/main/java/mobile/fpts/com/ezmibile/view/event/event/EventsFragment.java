package mobile.fpts.com.ezmibile.view.event.event;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragEventsBinding;
import mobile.fpts.com.ezmibile.model.entity.events.EventsApp;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;

public class EventsFragment extends FragmentApp implements IEventsView.View {
    FragEventsBinding binding;
    EventsPresenter presenter;

    // TODO: TamHV 6/27/2018  Typeface
    Typeface typeface = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans);

    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.setPosition(Define.TYPE_MENU_EVENTS);
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_events, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.lich_su_kien);

        presenter = new EventsPresenter(this);
        setupViewPager();

        //set màu sáng tối cho tablayout
        SharedPreferences preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);

        if (isLight) {
            binding.tabLayout.setTabTextColors(getResources().getColor(R.color.gray), getResources().getColor(R.color.colorFont));
            binding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.green));
            binding.tabLayout.setBackgroundColor(getResources().getColor(R.color.colorBackground));
            binding.viewpager.setBackgroundColor(getResources().getColor(R.color.colorBackground));

        } else {
            binding.tabLayout.setTabTextColors(getResources().getColor(R.color.gray), getResources().getColor(R.color.colorFontDark));
            binding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.green));
            binding.tabLayout.setBackgroundResource(R.color.colorBackgroundDark);
            binding.viewpager.setBackgroundResource(R.color.colorBackgroundDark);
        }
    }

    @Override
    public void setupViewPager() {
        new Handler().postDelayed(() -> {
            onLoad(true);
            ArrayList<EventsApp> list = presenter.getDataFormRoom();
//            boolean b = presenter.getDataBlackWhiteFormSharedPreferences();
            FragmentManager manager = getFragmentManager();
            PagerAdapter adapterTabLayout = new PagerAdapter(manager, list);


            binding.tabLayout.setTabsFromPagerAdapter(adapterTabLayout);

            binding.viewpager.setOffscreenPageLimit(3);
            binding.viewpager.setAdapter(adapterTabLayout);
            binding.tabLayout.setupWithViewPager(binding.viewpager);
            binding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
            onLoad(false);

            setCustomFont();
        }, 0);
    }

    @Override
    public void onLoad(boolean isLoad) {
        if (isLoad) {
            binding.llProgressBar.setVisibility(View.VISIBLE);
        } else {
            binding.llProgressBar.setVisibility(View.GONE);
        }
    }

    public void setCustomFont() {
        // TODO: TamHV 6/27/2018
        ViewGroup vg = (ViewGroup) binding.tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    ((TextView) tabViewChild).setTypeface(typeface);
                }
            }
        }
    }
}
