package mobile.fpts.com.ezmibile.view.splash_screen.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemSplashScreenIndexesLayoutBinding;
import mobile.fpts.com.ezmibile.databinding.LayoutSplashScreenHeaderSubNumber1Binding;
import mobile.fpts.com.ezmibile.databinding.LayoutSplashScreenHeaderSubNumberBinding;
import mobile.fpts.com.ezmibile.databinding.LayoutSplashScreenHeaderSubTextBinding;
import mobile.fpts.com.ezmibile.databinding.LayoutSplashScreenItemNumber1Binding;
import mobile.fpts.com.ezmibile.databinding.LayoutSplashScreenItemNumberBinding;
import mobile.fpts.com.ezmibile.databinding.LayoutSplashScreenItemheaderBinding;
import mobile.fpts.com.ezmibile.model.entity.Market;
import mobile.fpts.com.ezmibile.model.entity.market.VnIndices;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.ErrorApp;
import mobile.fpts.com.ezmibile.view.splash_screen.ISplashScreenView;
import mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation.INavView;

/**
 * Created by dinht on 2/1/2018.
 */

public class BigGroupAdapter extends BaseExpandableListAdapter implements INavView.IOnMenuClickListener {
    private SparseArray<BigGroup> sparseArray;

    private final int TYPE_CHANGE = 1;
    ISplashScreenView.IBiggroupCallBack iBiggroupCallBack;
    private SharedPreferences preferences;
    private List<View> indexesView;
    private List<View> watchListView;
    private List<View> watchListPSView;
    private List<View> newsView;
    private List<View> worldIndexView;
    private List<View> eventsView;
    private BigGroupPresenter presenter;
    private boolean isLight;

    public BigGroupAdapter(SparseArray<BigGroup> sparseArray, ISplashScreenView.IBiggroupCallBack iBiggroupCallBack) {
        this.sparseArray = sparseArray;
        this.iBiggroupCallBack = iBiggroupCallBack;
        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        watchListView = new ArrayList<>();
        watchListPSView = new ArrayList<>();
        newsView = new ArrayList<>();
        worldIndexView = new ArrayList<>();
        indexesView = new ArrayList<>();
        eventsView = new ArrayList<>();
        isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
    }

    @Override
    public int getGroupCount() {
        return sparseArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (sparseArray.get(groupPosition) != null && sparseArray.get(groupPosition).getGroupSparseArray() != null)
            return sparseArray.get(groupPosition).getGroupSparseArray().size();
        else return 0;
    }

    @Override
    public BigGroup getGroup(int groupPosition) {
        if (sparseArray != null)
            return sparseArray.get(groupPosition);
        else return new BigGroup(Define.HOME_TYPE_INDEXES, "", new SparseArray<>());
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public ItemHomeChild getChild(int groupPosition, int childPosition) {
        return sparseArray.get(groupPosition).getGroupSparseArray().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        if (groupPosition == 0) {
            // TODO:HoaDT 6/26/2018 Indexes
            if (indexesView.size() > groupPosition) {
                return indexesView.get(0);
            }
            indexesView = new ArrayList<>();
            final ItemSplashScreenIndexesLayoutBinding indexesLayoutBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_splash_screen_indexes_layout, parent, false);
            presenter = new BigGroupPresenter(new ISplashScreenView.IBigGroupView() {
                @Override
                public void onDisplay(List<VnIndices> marketList) {
                    Log.w("BigGroupAdapter", "onDisplay: ");
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(App.getInstance(), LinearLayoutManager.HORIZONTAL, false);
                    indexesLayoutBinding.recyclerView.setLayoutManager(linearLayoutManager);
                    // TODO:HoaDT 6/19/2018 lấy dữ liệu realtime phần tổng quan thị trường
                    indexesLayoutBinding.recyclerView.setAdapter(new IndexesAdapter(marketList, isLight,
                            new ISplashScreenView.IIndexesView() {
                                @Override
                                public void onIndexesItemClick(int position, String marketCode) {
                                    Log.w("BigGroupAdapter", "onIndexesItemClick: " + marketCode);
                                    if (position == marketList.size()) {
                                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_INDEXES,
                                                Define.HOME_TYPE_ACTION.ACTION_ALL, marketCode);
                                    } else if (iBiggroupCallBack != null) {
                                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_INDEXES,
                                                Define.HOME_TYPE_ACTION.ACTION_DETAIL, marketCode);
                                    }
                                }
                            }));
                }

                @Override
                public void onError(ErrorApp error) {
                }
            });
            indexesView.add(new IndexesHolder(indexesLayoutBinding).itemView);
            return indexesView.get(0);
        }

        final LayoutSplashScreenItemheaderBinding headerBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_splash_screen_itemheader, parent, false);
        headerBinding.txtTitleHeader.setText(getGroup(groupPosition).getTitle());
        if (isLight) {
            headerBinding.imageviewEdit.setImageResource(R.drawable.ic_edit_black_24dp);
            headerBinding.imageviewAdd.setImageResource(R.drawable.ic_add_black_24dp);
            headerBinding.imageviewDetail.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
//            headerBinding.linearLayout.setBackgroundColor(App.getInstance().getResources().getColor(R.color.colorHeader));
            headerBinding.imageviewPopup.setImageResource(R.drawable.ic_dehaze_black_24dp);
        } else {
            headerBinding.imageviewEdit.setImageResource(R.drawable.ic_edit_white_24dp);
            headerBinding.imageviewAdd.setImageResource(R.drawable.ic_add_white_24dp);
            headerBinding.imageviewDetail.setImageResource(R.drawable.ic_keyboard_arrow_right_white_24dp);
            headerBinding.imageviewPopup.setImageResource(R.drawable.ic_dehaze_white_24dp);
        }
        switch (getGroup(groupPosition).getType()) {
            case Define.HOME_TYPE_NEWS:
                headerBinding.imageviewAdd.setVisibility(View.GONE);
                headerBinding.imageviewEdit.setVisibility(View.GONE);
                headerBinding.imageviewDetail.setVisibility(View.VISIBLE);
                headerBinding.imageviewPopup.setVisibility(View.GONE);
                headerBinding.imageviewDetail.setOnClickListener(v ->
                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_NEWS, Define.HOME_TYPE_ACTION.ACTION_ALL, ""));
                break;
            case Define.HOME_TYPE_WORLD_INDEXES:
                headerBinding.imageviewAdd.setVisibility(View.GONE);
                headerBinding.imageviewEdit.setVisibility(View.GONE);
                headerBinding.imageviewDetail.setVisibility(View.VISIBLE);
                headerBinding.imageviewPopup.setVisibility(View.GONE);
                headerBinding.imageviewDetail.setOnClickListener(v ->
                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_WORLD_INDEXES, Define.HOME_TYPE_ACTION.ACTION_ALL, ""));
                break;
            case Define.HOME_TYPE_WATCHLIST:
                headerBinding.imageviewAdd.setVisibility(View.VISIBLE);
                headerBinding.imageviewEdit.setVisibility(View.VISIBLE);
                headerBinding.imageviewDetail.setVisibility(View.VISIBLE);
                headerBinding.imageviewPopup.setVisibility(View.VISIBLE);
                headerBinding.imageviewAdd.setOnClickListener(v ->
                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_WATCHLIST, Define.HOME_TYPE_ACTION.ACTION_ADD, ""));
                headerBinding.imageviewEdit.setOnClickListener(v ->
                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_WATCHLIST, Define.HOME_TYPE_ACTION.ACTION_EDIT, ""));
                headerBinding.imageviewDetail.setOnClickListener(v ->
                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_WATCHLIST, Define.HOME_TYPE_ACTION.ACTION_ALL, ""));
                headerBinding.imageviewPopup.setOnClickListener(v -> {
                            Log.w("BigGroupAdapter", "getGroupView:  click to popup up watchlist");
                            iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_WATCHLIST,
                                    Define.HOME_TYPE_ACTION.ACTION_SHOW_POPUP, "");
                        }
                );
                break;
            case Define.HOME_TYPE_BANG_GIA_PHAI_SINH:
                headerBinding.imageviewAdd.setVisibility(View.VISIBLE);
                headerBinding.imageviewEdit.setVisibility(View.VISIBLE);
                headerBinding.imageviewDetail.setVisibility(View.VISIBLE);
                headerBinding.imageviewPopup.setVisibility(View.VISIBLE);
                headerBinding.imageviewAdd.setOnClickListener(v ->
                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_BANG_GIA_PHAI_SINH,
                                Define.HOME_TYPE_ACTION.ACTION_ADD, ""));
                headerBinding.imageviewEdit.setOnClickListener(v ->
                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_BANG_GIA_PHAI_SINH,
                                Define.HOME_TYPE_ACTION.ACTION_EDIT, ""));
                headerBinding.imageviewDetail.setOnClickListener(v ->
                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_BANG_GIA_PHAI_SINH,
                                Define.HOME_TYPE_ACTION.ACTION_ALL, ""));
                headerBinding.imageviewPopup.setOnClickListener(v ->
                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_BANG_GIA_PHAI_SINH,
                                Define.HOME_TYPE_ACTION.ACTION_SHOW_POPUP, ""));
                break;
            case Define.HOME_TYPE_EVENTS:
                headerBinding.imageviewAdd.setVisibility(View.GONE);
                headerBinding.imageviewEdit.setVisibility(View.GONE);
                headerBinding.imageviewDetail.setVisibility(View.VISIBLE);
                headerBinding.imageviewPopup.setVisibility(View.GONE);
                headerBinding.imageviewDetail.setOnClickListener(v ->
                        iBiggroupCallBack.onDisplayAction(Define.HOME_TYPE_EVENTS, Define.HOME_TYPE_ACTION.ACTION_ALL, ""));
            default:
                break;
        }
        return (new HeaderHolder(headerBinding)).itemView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        ItemHomeChild itemHomeChild = sparseArray.get(groupPosition).getGroupSparseArray().get(childPosition);
        int type = itemHomeChild == null ? Define.HOME_TYPE_INDEXES : itemHomeChild.getTypeView();
        switch (type) {
            case Define.HOME_TYPE_WATCHLIST: {
                if (watchListView.size() > childPosition) {
                    return watchListView.get(childPosition);
                } else {
                    if (childPosition == 0) {
                        // TODO:HoaDT 6/26/2018 Thanh tiêu đề
                        watchListView = new ArrayList<>();
                        LayoutSplashScreenHeaderSubNumberBinding subNumerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                R.layout.layout_splash_screen_header_sub_number, parent, false);
                        ItemHeaderNumber itemHeaderNumber = (ItemHeaderNumber) itemHomeChild;
                        subNumerBinding.txtIndices.setText(itemHeaderNumber.getIndices());
                        subNumerBinding.txtLast.setText(itemHeaderNumber.getLast());
                        subNumerBinding.txtChange.setText(preferences.getBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISCHANGE, true) ?
                                itemHeaderNumber.getChangeRatio() : itemHeaderNumber.getChange());
                        subNumerBinding.txtQty.setText(itemHeaderNumber.getQty());
                        // TODO:HoaDT 6/26/2018: Click to change view  change -> changePercent
                        subNumerBinding.txtChange.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.w("BigGroupAdapter", "onClick: ");
                                if (subNumerBinding.txtChange.getText().toString()
                                        .equalsIgnoreCase(App.getInstance().getResources().getString(R.string.change) + "◥")) {
                                    subNumerBinding.txtChange.setText(App.getInstance().getResources().getString(R.string.change_percent) + "◥");
                                    preferences.edit().putBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISCHANGE, true);
                                    preferences.edit().apply();
                                    preferences.edit().commit();
                                } else {
                                    subNumerBinding.txtChange.setText(App.getInstance().getResources().getString(R.string.change) + "◥");
                                    preferences.edit().putBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISCHANGE, false);
                                    preferences.edit().apply();
                                    preferences.edit().commit();
                                }
                            }
                        });
                        watchListView.add(new SubHeaderHolder(subNumerBinding).itemView);
                        return watchListView.get(0);
                    } else {
                        // TODO:HoaDT 6/26/2018 Phần danh sách mã
                        LayoutSplashScreenItemNumberBinding numberBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                R.layout.layout_splash_screen_item_number, parent, false);
                        ItemNumber itemNumber = (ItemNumber) itemHomeChild;
                        numberBinding.txtIndices.setText(itemNumber.getIndices());
                        numberBinding.txtIndices.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                        numberBinding.txtLast.setText(itemNumber.getLast());
                        numberBinding.txtChange.setText(itemNumber.getChange());
                        numberBinding.txtQty.setText(itemNumber.getQty());
                        switch (itemNumber.getColorCode()) {
                            case "u":
                                numberBinding.txtChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                        App.getInstance().getResources().getColor(R.color.greenDark));
                                numberBinding.txtLast.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                        App.getInstance().getResources().getColor(R.color.greenDark));
                                break;
                            case "d":
                                numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                                numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                                break;
                            case "r":
                                numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                                numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                                break;
                            case "c":
                                numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                                numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                                break;
                            case "f":
                                numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                                numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                                break;
                            case "o":
                                numberBinding.txtChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                        App.getInstance().getResources().getColor(R.color.greenDark));
                                numberBinding.txtLast.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                        App.getInstance().getResources().getColor(R.color.greenDark));
                                break;
                            case "b"://không giao dịch
                                numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                                numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                            default:
                                break;
                        }
                        watchListView.add(new ChildHolder(numberBinding).itemView);
                        return watchListView.get(watchListView.size() > 0 ? watchListView.size() - 1 : 0);
                    }
                }
            }
            case Define.HOME_TYPE_BANG_GIA_PHAI_SINH: {
                if (watchListPSView.size() > childPosition)
                    return watchListPSView.get(childPosition);

                if (childPosition == 0) {
                    watchListPSView = new ArrayList<>();
                    LayoutSplashScreenHeaderSubNumber1Binding subNumerBinding =
                            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                    R.layout.layout_splash_screen_header_sub_number1, parent, false);
                    ItemHeaderNumber itemHeaderNumber = (ItemHeaderNumber) itemHomeChild;
//                        subNumerBinding.linearLayout.setBackgroundResource(isLight ? R.drawable.custom_home_header_sub : R.drawable.custom_home_header_sub_dark);
                    subNumerBinding.txtIndices.setText(itemHeaderNumber.getIndices());
                    subNumerBinding.txtLast.setText(itemHeaderNumber.getLast());
                    subNumerBinding.txtChange.setText(itemHeaderNumber.getChange());
                    subNumerBinding.txtQty.setText(itemHeaderNumber.getQty());
                    watchListPSView.add(new SubHeaderHolderPS(subNumerBinding).itemView);
                    return watchListPSView.get(0);
                } else {
                    LayoutSplashScreenItemNumber1Binding numberBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.layout_splash_screen_item_number1, parent, false);
                    ItemNumber itemNumber = (ItemNumber) itemHomeChild;
                    numberBinding.txtIndices.setText(itemNumber.getIndices());
                    numberBinding.txtIndices.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                    numberBinding.txtLast.setText(itemNumber.getLast());
                    numberBinding.txtChange.setText(itemNumber.getChange());
                    numberBinding.txtQty.setText(itemNumber.getQty());
                    switch (itemNumber.getColorCode()) {
                        case "u":
                            numberBinding.txtChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                    App.getInstance().getResources().getColor(R.color.greenDark));
                            numberBinding.txtLast.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                    App.getInstance().getResources().getColor(R.color.greenDark));
                            break;
                        case "d":
                            numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                            numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                            break;
                        case "r":
                            numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                            numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                            break;
                        case "c":
                            numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                            numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.purple));
                            break;
                        case "f":
                            numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                            numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.blue));
                            break;
                        case "o":
                            numberBinding.txtChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                    App.getInstance().getResources().getColor(R.color.greenDark));
                            numberBinding.txtLast.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                    App.getInstance().getResources().getColor(R.color.greenDark));
                            break;
                        case "b"://không giao dịch
                            numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                            numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                        default:
                            break;
                    }
                    watchListPSView.add(new ChildHolderPS(numberBinding).itemView);
                    return watchListPSView.get(watchListPSView.size() > 0 ? watchListPSView.size() - 1 : 0);
                }
            }
            case Define.HOME_TYPE_EVENTS: {
                if (eventsView.size() > childPosition) {
                    return eventsView.get(childPosition);
                } else {
                    LayoutSplashScreenHeaderSubTextBinding textBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.layout_splash_screen_header_sub_text, parent, false);
                    ItemNewsEvents newsEvents = (ItemNewsEvents) itemHomeChild;
                    textBinding.txtTextContent.setText(newsEvents.getTitle());
                    textBinding.txtDate.setText(newsEvents.getDateTime());
                    eventsView.add(new NewsEventHolder(textBinding).itemView);
                    return eventsView.get(eventsView.size() > 0 ? eventsView.size() - 1 : 0);
                }
            }
            case Define.HOME_TYPE_WORLD_INDEXES: {
                if (worldIndexView.size() > childPosition) {
                    return worldIndexView.get(childPosition);
                } else {
                    if (childPosition == 0) {
                        worldIndexView = new ArrayList<>();
                        LayoutSplashScreenHeaderSubNumberBinding subNumerBinding =
                                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_splash_screen_header_sub_number, parent, false);
                        ItemHeaderNumber itemHeaderNumber = (ItemHeaderNumber) itemHomeChild;
                        subNumerBinding.txtIndices.setText(itemHeaderNumber.getIndices());
                        subNumerBinding.txtLast.setText(itemHeaderNumber.getLast());
                        subNumerBinding.txtChange.setText(itemHeaderNumber.getChange());
                        subNumerBinding.txtQty.setText(itemHeaderNumber.getChangeRatio());
                        worldIndexView.add(new SubHeaderHolder(subNumerBinding).itemView);
                        return worldIndexView.get(0);
                    } else {
                        LayoutSplashScreenItemNumberBinding numberBinding =
                                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_splash_screen_item_number, parent, false);
                        ItemWorldIndices itemWorldIndices = (ItemWorldIndices) itemHomeChild;
                        numberBinding.txtIndices.setText(itemWorldIndices.getWorldIndices().getTitle());
                        numberBinding.txtLast.setText(itemWorldIndices.getWorldIndices().getPrice());
                        numberBinding.txtChange.setText(itemWorldIndices.getWorldIndices().getChange());
                        numberBinding.txtQty.setText(itemWorldIndices.getWorldIndices().getChangePct());
                        try {
                            double color = Double.parseDouble(itemWorldIndices.getWorldIndices().getChange().replaceAll("/+", ""));
                            if (color > 0) {
                                numberBinding.txtChange.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                        App.getInstance().getResources().getColor(R.color.greenDark));
                                numberBinding.txtLast.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                        App.getInstance().getResources().getColor(R.color.greenDark));
                                numberBinding.txtQty.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.green) :
                                        App.getInstance().getResources().getColor(R.color.greenDark));
                            } else if (color == 0) {
                                numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                                numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                                numberBinding.txtQty.setTextColor(App.getInstance().getResources().getColor(R.color.orange));
                            } else {
                                numberBinding.txtChange.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                                numberBinding.txtLast.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                                numberBinding.txtQty.setTextColor(App.getInstance().getResources().getColor(R.color.red));
                            }
                        } catch (Exception e) {
                        }
                        worldIndexView.add(new ChildHolder(numberBinding).itemView);
                        return worldIndexView.get(worldIndexView.size() - 1);
                    }
                }
            }
            case Define.HOME_TYPE_INDEXES:
            case Define.HOME_TYPE_NEWS:
            default: {
                if (newsView.size() > childPosition) {
                    return newsView.get(childPosition);
                } else {
                    LayoutSplashScreenHeaderSubTextBinding textBinding =
                            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                    R.layout.layout_splash_screen_header_sub_text, parent, false);
                    ItemNewsEvents newsEvents = (ItemNewsEvents) itemHomeChild;
                    textBinding.linearLayoutIcon.setVisibility(View.VISIBLE);//icon
                    textBinding.txtTextContent.setText(newsEvents.getTitle());
                    textBinding.txtDate.setText(newsEvents.getDateTime());
                    newsView.add(new NewsEventHolder(textBinding).itemView);
                    return newsView.get(newsView.size() > 0 ? newsView.size() - 1 : 0);
                }
            }
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClickItemListener(int id, int typeGroup, int typeCategory) {
//        iOnClickListener.onClickItemListener(id, type);
    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        LayoutSplashScreenItemheaderBinding binding;

        public HeaderHolder(LayoutSplashScreenItemheaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class ChildHolder extends RecyclerView.ViewHolder {
        LayoutSplashScreenItemNumberBinding numberBinding;

        public ChildHolder(LayoutSplashScreenItemNumberBinding numberBinding) {
            super(numberBinding.getRoot());
            this.numberBinding = numberBinding;
        }
    }

    class ChildHolderPS extends RecyclerView.ViewHolder {
        LayoutSplashScreenItemNumber1Binding numberBinding;

        public ChildHolderPS(LayoutSplashScreenItemNumber1Binding numberBinding) {
            super(numberBinding.getRoot());
            this.numberBinding = numberBinding;
        }
    }

    class NewsEventHolder extends RecyclerView.ViewHolder {
        public NewsEventHolder(LayoutSplashScreenHeaderSubTextBinding textBinding) {
            super(textBinding.getRoot());
        }
    }

    class SubHeaderHolder extends RecyclerView.ViewHolder {
        public SubHeaderHolder(LayoutSplashScreenHeaderSubNumberBinding subNumerBinding) {
            super(subNumerBinding.getRoot());
        }
    }

    class SubHeaderHolderPS extends RecyclerView.ViewHolder {
        public SubHeaderHolderPS(LayoutSplashScreenHeaderSubNumber1Binding subNumerBinding) {
            super(subNumerBinding.getRoot());
        }
    }

    class IndexesHolder extends RecyclerView.ViewHolder {

        public IndexesHolder(ItemSplashScreenIndexesLayoutBinding indexesLayoutBinding) {
            super(indexesLayoutBinding.getRoot());
        }
    }
}
