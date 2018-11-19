package mobile.fpts.com.ezmibile.view.marketOverview.market.hsx;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ActivityMainBinding;
import mobile.fpts.com.ezmibile.databinding.FragmentMarketOverviewHsxBinding;
import mobile.fpts.com.ezmibile.model.entity.market.VnIndices;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.ErrorApp;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.view.marketOverview.details.DetailsMarketFragment;
import mobile.fpts.com.ezmibile.view.marketOverview.market.IMarket_View;
import mobile.fpts.com.ezmibile.view.marketOverview.market.VnIndicesAdapter;

/**
 * Created by HoaDT  on 4/17/2018.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class HSXFragment extends FragmentApp implements IMarket_View,
        IMarket_View.IOnclickListener, View.OnClickListener {
    private boolean isLoading = true;

    FragmentMarketOverviewHsxBinding hsxBinding;

    List<VnIndices> vnIndicesList = new ArrayList<>();
    VnIndicesAdapter adapter;
    HSXPresenter presenter;

    public static HSXFragment newInstance() {
        HSXFragment fragment = new HSXFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.setPosition(Define.TYPE_MENU_DETAIL_PAGE);
        hsxBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_market_overview_hsx, container, false);
        return hsxBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hsxBinding.linearLayoutChange.setOnClickListener(this);
        hsxBinding.linearLayoutValue.setOnClickListener(this);
        hsxBinding.textviewChange.setText(App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE)
                .getBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISCHANGE, true) ? getString(R.string.change_percent) : getString(R.string.change));

        hsxBinding.textviewValue.setText(App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE)
                .getBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISVALUE, true) ? getString(R.string.value_bil) : getString(R.string.volumn));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new HSXPresenter(this);
    }

    @Override
    public void onLoading() {
        isLoading = true;
        hsxBinding.linearlayoutProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDisplay(List<VnIndices> vnIndicesList) {
        isLoading = false;
        this.vnIndicesList = vnIndicesList;
        hsxBinding.linearlayoutProgressbar.setVisibility(View.GONE);
        hsxBinding.contentview.setVisibility(View.VISIBLE);
        if (getActivity() != null) {
            getActivity().setProgressBarIndeterminateVisibility(true);
        }
        adapter = new VnIndicesAdapter(vnIndicesList, this);
        hsxBinding.recyclerView.setAdapter(adapter);
        hsxBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDisplayReplaceFragment(String marketCode) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();//getSupportFragmentManager().beginTransaction();
        DetailsMarketFragment detailMarketFragment = DetailsMarketFragment.newInstance(marketCode);

        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(getContext()));
        transaction//.remove(this)
                .replace(mainBinding.appBarMain.contentMain.contentview.getId(), detailMarketFragment);
        transaction.addToBackStack("");
        transaction.commit();
    }

    @Override
    public void onDisplayError(ErrorApp error) {
        isLoading = false;
        switch (error) {
            case ERROR_CONNECT_SERVER:
                break;
            case ERROR_NETWORK:
                Toast.makeText(App.getInstance(), getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                break;
            case NULL:
                break;
            case EXCEPTION:
                break;
            default:
                break;
        }
//        hsxBinding.setIsProgressBarVisible(false);
    }

    @Override
    public void onItemClick(String marketName) {
        presenter.OnClickItem(marketName);
    }

    @Override
    public void onItemClick(String marketName, boolean isChecked) {
        // TODO:HoaDT 6/20/2018 Lưu chọn or bỏ chọn thị trường cần hiển thị ngoài trang home (Indices)
        presenter.OnClickItem(marketName, isChecked);
    }

    @Override
    public void onClick(View v) {
        if (isLoading)
            return;
        // TODO:HoaDT 6/20/2018 Click vào tiêu đề cột: percent or percentRatio, values or quantity
        SharedPreferences preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (v.getId() == hsxBinding.linearLayoutChange.getId()) {
            boolean isChange = preferences.getBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISCHANGE, true);
            isChange = !isChange;
            hsxBinding.textviewChange.setText(isChange ?
                    getString(R.string.change_percent) :
                    getString(R.string.change));
            editor.putBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISCHANGE, isChange);
            adapter.notifyDataSetChanged();

        } else if (v.getId() == hsxBinding.linearLayoutValue.getId()) {
            boolean isValue = preferences.getBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISVALUE, true);
            isValue = !isValue;
            hsxBinding.textviewValue.setText(isValue ? getString(R.string.value_bil) :
                    getString(R.string.volumn));
            editor.putBoolean(Define.SHARED_PREFRENCES_MARKETOVERVIEW_ISVALUE, isValue);
            adapter.notifyDataSetChanged();
        }

        editor.apply();
        editor.commit();
    }


    @Override
    public void onPause() {
        super.onPause();
        presenter.destroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}