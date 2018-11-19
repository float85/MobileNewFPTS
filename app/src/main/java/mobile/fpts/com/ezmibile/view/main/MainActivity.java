package mobile.fpts.com.ezmibile.view.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ActivityMainBinding;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Language;
import mobile.fpts.com.ezmibile.view.chart.ChartFragment;
import mobile.fpts.com.ezmibile.view.event.event.EventsFragment;
import mobile.fpts.com.ezmibile.view.marketOverview.MarketOverviewFragment;
import mobile.fpts.com.ezmibile.view.news.home.HomeNewsFragment;
import mobile.fpts.com.ezmibile.view.splash_screen.SplashScreenFragment;
import mobile.fpts.com.ezmibile.view.splash_screen.custormMenuTab.IMenuTabClickListenter;
import mobile.fpts.com.ezmibile.view.splash_screen.custormMenuTab.MenuTab;
import mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation.CustomMenuNavigation;
import mobile.fpts.com.ezmibile.view.watchlist.detail.DetailCodeFragment;
import mobile.fpts.com.ezmibile.view.watchlist.search.CustomerAutoComleteTextView;
import mobile.fpts.com.ezmibile.view.watchlist.WatchlistFragment;
import mobile.fpts.com.ezmibile.view.world_indexes.WorldIndecesFragment;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        IMenuTabClickListenter, IMainView.IMainListener, IMainView, IMainView.ISearch {

    private ActivityMainBinding mainBinding;
    private boolean isLight = true;
    private MainPresenter presenter;
    SearchView searchView;
    SearchView.SearchAutoComplete searchAutoComplete;
    ArrayList<CustomerAutoComleteTextView> listSearch = new ArrayList<>();

    // TODO: TamHV 6/24/2018 list phần serch mã chứng khoán
    String[] listCompany;

    // TODO:HoaDT 6/20/2018 set reload activity khi back từ các fragment khác
    private static boolean isReload = true;

    public static boolean isIsReload() {
        return isReload;
    }

    public static void setIsReload(boolean isReload) {
        MainActivity.isReload = isReload;
    }

    // TODO: TamHV 7/1/2018 set reload activity khi back từ home
    public boolean isReloadHome = true;
    public boolean isReloadHomeBack = false;
    public boolean isReloadFinsh = true;

    // TODO: TamHV 6/29/2018 set Typeface
    Typeface typeface = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans);

    int position;
    int type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Language.setLanguage();
        Language.setLanguage();
        SharedPreferences preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
        if (isLight) {
            setTheme(R.style.Theme_MyLightTheme);
        } else {
            setTheme(R.style.Theme_MyDarkTheme);
        }

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mainBinding.appBarMain.toolbar);
        mainBinding.appBarMain.toolbar.setNavigationIcon(R.drawable.ic_dehaze_white_24dp);
        mainBinding.appBarMain.toolbar.setBackgroundColor(App.getInstance().getResources().getColor(R.color.colorHeaderDark));
        mainBinding.appBarMain.toolbar.setTitleTextColor(App.getInstance().getResources().getColor(R.color.colorFontDark));

        // TODO: TamHV 6/27/2018 set typeface
        mainBinding.appBarMain.toolbar.setTitleTextAppearance(this, R.style.Free_sansFontsToolbar);

        presenter = new MainPresenter(this, this, this);
        presenter.loadData();

        // TODO:TamHV 7/5/2018: đóng hẳn menu mới load fragment
        mainBinding.drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                onDrawerLayoutChange();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                onDrawerLayoutChange();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                onDrawerLayoutChange();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        FullScreencall();
    }

    @Override
    public void display() {
        initilize();
    }

    private void initilize() {
        new CustomMenuNavigation(this, this, mainBinding.navUserLayout).onStart();
        new MenuTab(mainBinding.appBarMain.contentMain.navigationBottomView, this, this)
                .onStart();
        mainBinding.appBarMain.toolbar.setNavigationOnClickListener(v ->
                mainBinding.drawerLayout.openDrawer(Gravity.START));
        setFragment(SplashScreenFragment.newInstance(true));
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO: TamHV 6/19/2018
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchViewItem = menu.findItem(R.id.search);

        // Get SearchView object.
        searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        // Get SearchView autocomplete object.
        searchAutoComplete = searchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setTextColor(Color.WHITE);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.white);

        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.gray));
        searchAutoComplete.setThreshold(1);
        searchAutoComplete.setFilters(presenter.validateEdittext());
        searchAutoComplete.setHint(R.string.hint_search_toolbar);
        searchAutoComplete.setTypeface(typeface);

        searchAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AutoCompleteTextView AutoCompleteTextViewtest = searchView.findViewById(R.id.search_src_text);

                if (AutoCompleteTextViewtest.isPerformingCompletion()) {
                    // An item has been selected from the list. Ignore.
                    return;
                }
                if (String.valueOf(s).compareToIgnoreCase("") == 0) {
                    return;
                } else {
                    try {
                        searchAutoComplete = searchView.findViewById(R.id.search_src_text);
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < listSearch.size(); i++) {
                            String strStockCode = "";

                            SharedPreferences sharedPreferences = getSharedPreferences(Define.SHARED_PREFRENCES_APP, MODE_PRIVATE);
                            String b = sharedPreferences.getString(Define.SHARED_PREFRENCES_LANGUAGE, "vi");

                            if (b.equalsIgnoreCase("vi")) {
                                strStockCode = listSearch.get(i).getStock_code() + " - " + listSearch.get(i).getName_vn();
                            } else {
                                strStockCode = listSearch.get(i).getStock_code() + " - " + listSearch.get(i).getName_en();
                            }

                            if (listSearch.get(i).getStock_code().toLowerCase().startsWith(s.toString().toLowerCase())) {
                                list.add(strStockCode);
                            }
                        }

                        listCompany = new String[list.size()];
                        list.toArray(listCompany);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(App.getInstance().getApplicationContext(),
                                R.layout.item_watchlist_search_recyclerview, listCompany);
                        searchAutoComplete.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchAutoComplete.setOnItemClickListener((parent, view, position, id) -> {
            // TODO: TamHV 6/24/2018 xử lí mã
            String[] strings = listCompany[position].split("\\s");
            searchAutoComplete.setText("");
            searchView.setIconified(true);
            setFragment(DetailCodeFragment.newInstance(strings[0], true));
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.language:
                Language.setLanguage();
                // TODO: TamHV 6/19/2018
                this.finish();
                this.startActivity(new Intent(this, this.getClass()));
                break;
            case R.id.mode_theme: {
                SharedPreferences preferences = App.getInstance()
                        .getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
                boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
                if (isLight)
                    setTheme(R.style.Theme_MyDarkTheme);
                else
                    setTheme(R.style.Theme_MyLightTheme);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean(Define.SHARED_PREFRENCES_MODE_THEME, !isLight);
                edit.apply();
                edit.commit();
                this.startActivity(new Intent(this, MainActivity.class));
                break;
            }
            case R.id.search: {
                Log.w("MainActivity", "onOptionsItemSelected: search");
//                SearchView
                break;
            }
        }
        initilize();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (isReloadHome) {
                if (isReload) {
                    setFragment(SplashScreenFragment.newInstance(false));
                } else if (isReloadFinsh) {
                    finish();
                } else {
                    super.onBackPressed();
                }

                return;
            }
        }

        if (isReloadHomeBack) {
            isReloadHome = true;
            Toast.makeText(this, R.string.toast_back_main, Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    isReloadHome = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment) {
        setBack();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(mainBinding.appBarMain.contentMain.contentview.getId(), fragment);
        ft.commit();
    }

    public void setBack() {
        isReloadFinsh = false;
        isReloadHome = true;
        isReloadHomeBack = false;
        isReload = true;
    }

    public void setBackDetail() {
        isReloadFinsh = false;
        isReloadHome = true;
        isReloadHomeBack = false;
        isReload = false;
    }

    @Override
    public void onMenuTabClick(Define.NAVIGATION_BOTTOM_VIEW_TAB tab) {
        switch (tab) {
            case OVERVIEW:
                break;
            case PROPERTY:
                break;
            case EDIT_DELETE:
                break;
            case PLACING_ORDER:
                break;
            case MONEY_TRANSFER:
                break;
            default:
                break;
        }
    }

    @Override
    public void onReplaceFragment(int typeFragmet) {
        mainBinding.drawerLayout.closeDrawer(GravityCompat.START);

        if (!searchView.isIconified()) {
            searchAutoComplete.setText("");
            searchView.setIconified(true);
        }

        type = typeFragmet;
        position = App.getPosition();
//        switch (typeFragmet) {
//            case Define.TYPE_MENU_HOME:
//                if (position != Define.TYPE_MENU_HOME) {
//                    setFragment(SplashScreenFragment.newInstance(true));
//                }
//                break;
//            case Define.TYPE_MENU_MARKET_OVERRVIEW:
//                if (position != Define.TYPE_MENU_MARKET_OVERRVIEW) {
//                    setFragment(MarketOverviewFragment.newInstance());
//                }
//                break;
//            case Define.TYPE_MENU_WATCHLIST:
//                if (position != Define.TYPE_MENU_WATCHLIST) {
//                    setFragment(WatchlistFragment.newInstance());
//                }
//                break;
//            case Define.TYPE_MENU_NEWS:
//                if (position != Define.TYPE_MENU_NEWS) {
//                    setFragment(HomeNewsFragment.newInstance());
//                }
//                break;
//            case Define.TYPE_MENU_CHART:
//                if (position != Define.TYPE_MENU_CHART) {
//                    setFragment(ChartFragment.newInstance());
//                }
//                break;
//            case Define.TYPE_MENU_EVENTS:
//                if (position != Define.TYPE_MENU_EVENTS) {
//                    setFragment(EventsFragment.newInstance());
//                }
//                break;
//            case Define.TYPE_MENU_WORLD_INDEX:
//                if (position != Define.TYPE_MENU_WORLD_INDEX) {
//                    setFragment(WorldIndecesFragment.newInstance());
//                }
//                break;
//            default:
//                break;
//        }
    }

    public void FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void setDataSearchStock(ArrayList<CustomerAutoComleteTextView> list) {
        listSearch = list;
    }

    // TODO:HoaDT 7/6/2018 drawerLayout change
    private void onDrawerLayoutChange() {
        switch (type) {
            case Define.TYPE_MENU_HOME:
                if (position != Define.TYPE_MENU_HOME) {
                    setFragment(SplashScreenFragment.newInstance(true));
                }
                break;
            case Define.TYPE_MENU_MARKET_OVERRVIEW:
                if (position != Define.TYPE_MENU_MARKET_OVERRVIEW) {
                    setFragment(MarketOverviewFragment.newInstance());
                }
                break;
            case Define.TYPE_MENU_WATCHLIST:
                if (position != Define.TYPE_MENU_WATCHLIST) {
                    setFragment(WatchlistFragment.newInstance());
                }
                break;
            case Define.TYPE_MENU_NEWS:
                if (position != Define.TYPE_MENU_NEWS) {
                    setFragment(HomeNewsFragment.newInstance());
                }
                break;
            case Define.TYPE_MENU_CHART:
                if (position != Define.TYPE_MENU_CHART) {
                    setFragment(ChartFragment.newInstance());
                }
                break;
            case Define.TYPE_MENU_EVENTS:
                if (position != Define.TYPE_MENU_EVENTS) {
                    setFragment(EventsFragment.newInstance());
                }
                break;
            case Define.TYPE_MENU_WORLD_INDEX:
                if (position != Define.TYPE_MENU_WORLD_INDEX) {
                    setFragment(WorldIndecesFragment.newInstance());
                }
                break;
            default:
                break;
        }
    }
}
