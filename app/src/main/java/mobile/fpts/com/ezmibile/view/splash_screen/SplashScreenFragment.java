package mobile.fpts.com.ezmibile.view.splash_screen;


import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ActivityMainBinding;
import mobile.fpts.com.ezmibile.databinding.FragmentSplashScreenBinding;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.view.event.event.EventsFragment;
import mobile.fpts.com.ezmibile.view.main.MainActivity;
import mobile.fpts.com.ezmibile.view.marketOverview.MarketOverviewFragment;
import mobile.fpts.com.ezmibile.view.marketOverview.details.DetailsMarketFragment;
import mobile.fpts.com.ezmibile.view.news.detail.NewsDetailFragment;
import mobile.fpts.com.ezmibile.view.news.home.HomeNewsFragment;
import mobile.fpts.com.ezmibile.view.splash_screen.data.BigGroup;
import mobile.fpts.com.ezmibile.view.splash_screen.data.BigGroupAdapter;
import mobile.fpts.com.ezmibile.view.splash_screen.data.ItemHeaderNumber;
import mobile.fpts.com.ezmibile.view.splash_screen.data.ItemHomeChild;
import mobile.fpts.com.ezmibile.view.splash_screen.data.ItemNewsEvents;
import mobile.fpts.com.ezmibile.view.splash_screen.data.ItemNumber;
import mobile.fpts.com.ezmibile.view.splash_screen.dialog.WatchlistDialogFragment;
import mobile.fpts.com.ezmibile.view.watchlist.detail.DetailCodeFragment;
import mobile.fpts.com.ezmibile.view.watchlist.search.WatchListSearchFragment;
import mobile.fpts.com.ezmibile.view.watchlist.WatchlistFragment;

/**
 * Created by HoaDT  on 4/17/2018.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class SplashScreenFragment extends FragmentApp implements ISplashScreenView, SwipeRefreshLayout.OnRefreshListener,
        ISplashScreenView.IBiggroupCallBack {
    private FragmentSplashScreenBinding binding;

    private SplashScreenPresenter presenter;

    private ISplashScreenView iSplashScreenView;
    private BigGroupAdapter adapter;
    private ActivityMainBinding mainBinding;

    public static SplashScreenFragment newInstance(boolean b) {
        SplashScreenFragment fragment = new SplashScreenFragment();
        Bundle args = new Bundle();
        args.putBoolean("isHome", b);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            boolean b = getArguments().getBoolean("isHome");
            MainActivity.setIsReload(b);
        }
        iSplashScreenView = this;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.isReloadHomeBack = true;
        mainActivity.isReloadHome = false;
        mainActivity.setIsReload(false);
        mainActivity.isReloadFinsh = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w("SplashScreenFragment", "onCreateView: ");
        App.setPosition(Define.TYPE_MENU_HOME);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.home);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false);
        mainBinding = ActivityMainBinding.inflate(inflater);
        binding.expandedListview.setChildIndicator(null);
        binding.expandedListview.setGroupIndicator(null);
        binding.expandedListview.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            ItemHomeChild itemHomeChild = adapter.getChild(groupPosition, childPosition);
            int type = itemHomeChild == null ? Define.HOME_TYPE_INDEXES : itemHomeChild.getTypeView();

            switch (type) {
                case Define.HOME_TYPE_WATCHLIST:
                    if (childPosition > 0) {
                        ItemNumber itemHeaderNumber = (ItemNumber) itemHomeChild;
                        Log.w("SplashScreenFragment", "onChildClick: HOME_TYPE_WATCHLIST " + itemHeaderNumber.getIndices());
                        setFragment(DetailCodeFragment.newInstance(itemHeaderNumber.getIndices(), false));
                    } else {
                        ItemHeaderNumber itemHeaderNumber = (ItemHeaderNumber) itemHomeChild;

                    }
                    break;
                case Define.HOME_TYPE_INDEXES:
                    Log.w("SplashScreenFragment", "onCreateView:HOME_TYPE_INDEXES  ");
                    break;
                case Define.HOME_TYPE_NEWS:
                    Log.w("SplashScreenFragment", "onCreateView: HOME_TYPE_NEWS ");
                    ItemNewsEvents itemNewsEvents = (ItemNewsEvents) itemHomeChild;
                    Log.w("SplashScreenFragment", "onChildClick: HOME_TYPE_NEWS " + itemNewsEvents.getTitle());
                    setFragment(NewsDetailFragment.newInstance(itemNewsEvents.getId(), itemNewsEvents.getIconlink(), Define.Analysis_Comment));
                    break;
                case Define.HOME_TYPE_EVENTS:
                    Log.w("SplashScreenFragment", "onCreateView:HOME_TYPE_EVENTS  ");
                    break;
                case Define.HOME_TYPE_BANG_GIA_PHAI_SINH:
                    Log.w("SplashScreenFragment", "onCreateView:HOME_TYPE_BANG_GIA_PHAI_SINH");
                    break;
                case Define.HOME_TYPE_WORLD_INDEXES:
                    Log.w("SplashScreenFragment", "onCreateView: HOME_TYPE_WORLD_INDEXES ");
                    break;
                default:
                    break;
            }
            return false;
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.w("SplashScreenFragment", "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        presenter = new SplashScreenPresenter(iSplashScreenView, getActivity());
    }

    @Override
    public void onRefresh() {
        presenter.onReload();
    }

    @Override
    public void onLoading() {
        Log.w("SplashScreenFragment", "onLoading: ");
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.swipeRefreshLayout.setRefreshing(false);
        binding.expandedListview.setVisibility(View.GONE);
        if (getActivity() != null) {
            getActivity().setProgressBarIndeterminateVisibility(false);
        }
    }

    @Override
    public void onDisplay(SparseArray<BigGroup> sparseArray) {
        Log.w("SplashScreenFragment", "onDisplay: ");
        adapter = new BigGroupAdapter(sparseArray, this);
        binding.expandedListview.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            binding.expandedListview.expandGroup(i);
        }
        adapter.notifyDataSetChanged();
        binding.swipeRefreshLayout.setRefreshing(false);
        binding.progressBar.setVisibility(View.GONE);
        binding.expandedListview.setVisibility(View.VISIBLE);
        if (getActivity() != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);
        }
    }

    /**
     * @param typeItem:   indexes, watchlist, news
     * @param typeAction: add, edit, detail, all
     * @param marketCode: fpt, fts, vnm
     */
    @Override
    public void onDisplayAction(int typeItem, Define.HOME_TYPE_ACTION typeAction, String marketCode) {
        Log.w("SplashScreenFragment", "onDisplayAction: : " + typeAction.name());
        switch (typeItem) {
            case Define.HOME_TYPE_INDEXES: {
                switch (typeAction) {
                    case ACTION_ALL:
                        setFragment(MarketOverviewFragment.newInstance());
                        break;
                    case ACTION_DETAIL:
                        setFragment(DetailsMarketFragment.newInstance(marketCode));
                        break;
                    default:
                        break;
                }
            }
            break;
            case Define.HOME_TYPE_NEWS:
                switch (typeAction) {
                    case ACTION_ALL:
                        setFragment(HomeNewsFragment.newInstance());
                        break;
                    default:
                        break;
                }
                break;
            case Define.HOME_TYPE_EVENTS:
                switch (typeAction) {
                    case ACTION_DETAIL:
//                        setFragment(Detail.newInstance());
                        break;
                    case ACTION_ALL:
                        setFragment(EventsFragment.newInstance());
                        break;
                    default:
                        break;
                }
                break;
            case Define.HOME_TYPE_WATCHLIST:
                Log.w("SplashScreenFragment", "onDisplayAction: HOME_TYPE_WATCHLIST");
                switch (typeAction) {
                    case ACTION_ALL:
                        setFragment(WatchlistFragment.newInstance());
                        break;
                    case ACTION_DETAIL:
                        break;
                    case ACTION_EDIT:
                        break;
                    case ACTION_ADD:
                        Log.w("SplashScreenFragment", "onDisplayAction: watchlist add");
                        setFragment(WatchListSearchFragment.newInstance());
                        break;
                    case ACTION_SHOW_POPUP:
                        // TODO:HoaDT 6/19/2018 show popup danh mục của bảng giá
                        Log.w("SplashScreenFragment", "onDisplayAction: ");
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        WatchlistDialogFragment editNameDialogFragment = WatchlistDialogFragment.newInstance();
                        editNameDialogFragment.show(fm, "watchlist_dialog");
                        break;
                    default:
                        break;
                }
                break;
            case Define.HOME_TYPE_WORLD_INDEXES:
                Log.w("SplashScreenFragment", "onDisplayAction: HOME_TYPE_WORLD_INDEXES");
                switch (typeAction) {
                    case ACTION_ALL:
                        break;
                    default:
                        break;
                }
                break;
            case Define.HOME_TYPE_BANG_GIA_PHAI_SINH:

                break;
            default:
                break;
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction//.remove(this)
                .replace(mainBinding.appBarMain.contentMain.contentview.getId(), fragment);
        transaction.addToBackStack("");
        transaction.commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null)
            presenter.onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
    }
}
