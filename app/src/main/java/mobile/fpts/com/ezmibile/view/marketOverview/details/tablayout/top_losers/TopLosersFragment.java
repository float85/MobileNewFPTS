package mobile.fpts.com.ezmibile.view.marketOverview.details.tablayout.top_losers;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.LayoutIndexdetailactivityTopstockheaderBinding;
import mobile.fpts.com.ezmibile.databinding.FragDetailsToplosersBinding;
import mobile.fpts.com.ezmibile.model.entity.detail_home.TopRealtime;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.view.marketOverview.details.tablayout.DataTopGainers;

public class TopLosersFragment extends FragmentApp implements ITopLosers {
    FragDetailsToplosersBinding binding;
    TopLosersPresenter presenter;
    int intIndex;
    boolean isLight = true;

    public static TopLosersFragment newInstance(String idURL) {
        TopLosersFragment fragment = new TopLosersFragment();

        Bundle bundle = new Bundle();
        bundle.putString("idURL", idURL);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_details_toplosers, container, false);

        if (getArguments() != null) {
            String strIndex = getArguments().getString("idURL");

            if (strIndex.equalsIgnoreCase("HNX")) {
                intIndex = 2;
            } else if (strIndex.equalsIgnoreCase("VNI")) {
                intIndex = 1;
            } else if (strIndex.equalsIgnoreCase("UPCOM")) {
                intIndex = 3;
            } else if (strIndex.equalsIgnoreCase("VN30")) {
                intIndex = 4;
            } else if (strIndex.equalsIgnoreCase("HNX30")) {
                intIndex = 5;
            }
        }

        presenter = new TopLosersPresenter(this::loadData, intIndex);
        presenter.loadDataformServer();

        isLight = presenter.getBooleanModeTheme();
        binding.llTablayoutColor.setBackgroundColor(isLight ? getResources().getColor(R.color.colorBackground) :
                getResources().getColor(R.color.colorBackgroundDark));
        binding.txtSymbol.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.txtOpen.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.txtClose.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.txtHigh.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.txtLow.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.txtVolume.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.txtChange.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        return binding.getRoot();
    }

    @Override
    public void loadData(TopRealtime dataTopGainers) {
        LayoutIndexdetailactivityTopstockheaderBinding bindingLayout;
        View convertView = LayoutInflater.from(App.getInstance().getApplicationContext()).inflate(R.layout.layout_indexdetailactivity_topstockheader, null);
        bindingLayout = DataBindingUtil.bind(convertView);

        bindingLayout.txtSymbol.setText(dataTopGainers.getSCode());
        bindingLayout.txtOpen.setText(dataTopGainers.getSOpen());
        bindingLayout.txtClose.setText(dataTopGainers.getSClose());
        bindingLayout.txtHigh.setText(dataTopGainers.getSHighest());
        bindingLayout.txtLow.setText(dataTopGainers.getSLowest());
        bindingLayout.txtVolume.setText(dataTopGainers.getSTotalShares());
        bindingLayout.txtChange.setText(dataTopGainers.getSChangePercent());
        binding.linear.addView(bindingLayout.getRoot());


        bindingLayout.txtOpen.setTextColor(getResources().getColor(presenter.setColorTextView(dataTopGainers, dataTopGainers.getSOpen())));

        bindingLayout.txtClose.setTextColor(getResources().getColor(presenter.setColorTextView(dataTopGainers, dataTopGainers.getSClose())));

        bindingLayout.txtHigh.setTextColor(getResources().getColor(presenter.setColorTextView(dataTopGainers, dataTopGainers.getSHighest())));

        bindingLayout.txtLow.setTextColor(getResources().getColor(presenter.setColorTextView(dataTopGainers, dataTopGainers.getSLowest())));

        bindingLayout.txtSymbol.setTextColor(getResources().getColor(R.color.blue));
        bindingLayout.txtChange.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        bindingLayout.txtVolume.setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
    }
}
