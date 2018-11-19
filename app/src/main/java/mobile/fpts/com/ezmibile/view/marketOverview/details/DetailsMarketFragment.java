package mobile.fpts.com.ezmibile.view.marketOverview.details;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragDetailsHomeBinding;
import mobile.fpts.com.ezmibile.databinding.LayoutIndexdetailDetailhomeVniBinding;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX30;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX_UPCOM_30;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeUpcom;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeVNI;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeItem_VNI;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeVNI30;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.util.base.ViewPagerAdapter;
import mobile.fpts.com.ezmibile.view.main.MainActivity;
import mobile.fpts.com.ezmibile.view.marketOverview.details.tablayout.chart.ChartFragment;
import mobile.fpts.com.ezmibile.view.marketOverview.details.tablayout.most_active.MostActiveFragment;
import mobile.fpts.com.ezmibile.view.marketOverview.details.tablayout.top_gainers.TopGainersFragment;
import mobile.fpts.com.ezmibile.view.marketOverview.details.tablayout.top_losers.TopLosersFragment;

public class DetailsMarketFragment extends FragmentApp implements IDetailsHome {
    FragDetailsHomeBinding binding;
    String sIndex;
    String marketName;
    String sLink;
    DetailMarketPresenter presenter;
    View view;

    public static DetailsMarketFragment newInstance(String idURL) {
        DetailsMarketFragment fragment = new DetailsMarketFragment();

        Bundle bundle = new Bundle();
        bundle.putString("idURL", idURL);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_details_home, container, false);
        App.setPosition(Define.TYPE_MENU_DETAIL_PAGE);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            marketName = getArguments().getString("idURL").toLowerCase();

            if (marketName.equals("hnx")) {
                sIndex = "HNX";
                sLink = "realtime_index_ha";
            } else if (marketName.equals("vni")) {
                sIndex = "VNI";
                sLink = "realtime_index_ho";
            } else if (marketName.equals("upcom")) {
                sIndex = "UPCOM";
                sLink = "realtime_index_up";
            } else if (marketName.equals("vn30")) {
                sIndex = "VN30";
                sLink = "realtime_index_vni30";
            } else if (marketName.equals("hnx30")) {
                sIndex = "HNX30";
                sLink = "realtime_index_hnx30";
            } else {
                marketName = "vni";
                sIndex = "VNI";
                sLink = "realtime_index_ho";
            }

            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(sIndex);
        }

        presenter = new DetailMarketPresenter(this, sLink);
        presenter.loadDataFormServer();
        setupViewPager();

        if (sIndex.equalsIgnoreCase("VNI")) {
            binding.lnS1S2S3.setVisibility(View.VISIBLE);
        } else {
            binding.lnS1S2S3.setVisibility(View.GONE);
        }

        if (!presenter.isConnected()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle(getResources().getString(R.string.dialog_error));
            builder.setMessage(getResources().getString(R.string.dialog_error));
            builder.setCancelable(false);
//            builder.setPositiveButton("", (dialogInterface, i) -> {
//            });
            builder.setNegativeButton(getResources().getString(R.string.dialog_content) + " =>>>", (dialogInterface, i) -> {
                dialogInterface.dismiss();
                getFragmentManager().popBackStack();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        //set màu trắng đen
        if (presenter.getBooleanModeTheme()) {
            binding.lnIndexOverview.setBackgroundResource(R.color.White);
            binding.txtUpDownTitle.setTextColor(getResources().getColor(R.color.Black));
            binding.txtPutthroughTitle.setTextColor(getResources().getColor(R.color.Black));

            binding.txtValue.setTextColor(getResources().getColor(R.color.Black));
            binding.txtVolume.setTextColor(getResources().getColor(R.color.Black));

            binding.txtPutVal.setTextColor(getResources().getColor(R.color.Black));
            binding.txtPutVol.setTextColor(getResources().getColor(R.color.Black));

            //tab layout
            binding.tablayout.setTabTextColors(getResources().getColor(R.color.tabTextColor_white), getResources().getColor(R.color.black));
            binding.tablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
            binding.tablayout.setBackgroundColor(getResources().getColor(R.color.BackgroundColor_tablayout_white));
            binding.viewpager.setBackgroundColor(getResources().getColor(R.color.white));
        } else {
            binding.lnIndexOverview.setBackgroundResource(R.color.Black);
            binding.txtUpDownTitle.setTextColor(getResources().getColor(R.color.White));
            binding.txtPutthroughTitle.setTextColor(getResources().getColor(R.color.White));

            binding.txtValue.setTextColor(getResources().getColor(R.color.White));
            binding.txtVolume.setTextColor(getResources().getColor(R.color.White));

            binding.txtPutVal.setTextColor(getResources().getColor(R.color.White));
            binding.txtPutVol.setTextColor(getResources().getColor(R.color.White));

            //tab layout
            binding.tablayout.setTabTextColors(getResources().getColor(R.color.tabTextColor_black), getResources().getColor(R.color.white));
            binding.tablayout.setSelectedTabIndicatorColor(Color.WHITE);
            binding.tablayout.setBackgroundResource(R.color.BackgroundColor_tablayout_black);
            binding.viewpager.setBackgroundResource(R.color.BackgroundColor_viewpager_black);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setBack();
    }

    @Override
    public void setupViewPager() {
        FragmentManager manager = getChildFragmentManager();
        ViewPagerAdapter adapterTabLayout = new ViewPagerAdapter(manager);

        adapterTabLayout.addFragment(ChartFragment.newInstance(marketName), getResources().getString(R.string.detailhome_tablayout_chart));
        adapterTabLayout.addFragment(TopGainersFragment.newInstance(sIndex), getResources().getString(R.string.detailhome_tablayout_topgainers));
        adapterTabLayout.addFragment(TopLosersFragment.newInstance(sIndex), getResources().getString(R.string.detailhome_tablayout_toplosers));
        adapterTabLayout.addFragment(MostActiveFragment.newInstance(sIndex), getResources().getString(R.string.detailhome_tablayout_mostactive));

        binding.viewpager.setAdapter(adapterTabLayout);
        binding.viewpager.setOffscreenPageLimit(4);
        binding.tablayout.setupWithViewPager(binding.viewpager);
        binding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tablayout));
        binding.tablayout.setTabsFromPagerAdapter(adapterTabLayout);

        setCustomFont();
    }

    @Override
    public void loadDataVNI(DetailHomeVNI detailHomeVNI) {
        binding.txtIndex.setText(sIndex + ": " + detailHomeVNI.getHOMARKETINDEX());

        binding.txtVolume.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHomeVNI.getHOTOTALQTTY())));
        binding.txtChange.setText(Html.fromHtml(
                App.getInstance().getResources().getString(R.string.marketOverview_detail_txtChange,
                        detailHomeVNI.getHOCHGINDEX(), detailHomeVNI.getHOPCTINDEX())));
        binding.txtValue.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHomeVNI.getHOTOTALVALUE())));

        binding.txtUp.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeVNI.getHOADVANCES())));
        binding.txtBt.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeVNI.getHONOCHANGE())));
        binding.txtDown.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeVNI.getHODECLINES())));

        binding.txtPutVol.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVol, detailHomeVNI.getHOPTTOTALQTTY())));
        binding.txtPutVal.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVal, detailHomeVNI.getHOPTTOTALVALUE())));

        setColorUpDown(detailHomeVNI.getStrArrow(), binding.txtIndex);
        setColorUpDown(detailHomeVNI.getStrArrow(), binding.txtChange);
    }

    @Override
    public void loadDataItemVNI(DetailHomeItem_VNI detailHome, String sPhien) {
        LayoutIndexdetailDetailhomeVniBinding bindingLayout;
        View convertView = LayoutInflater.from(App.getInstance().getApplicationContext()).inflate(R.layout.layout_indexdetail_detailhome_vni, null);
        bindingLayout = DataBindingUtil.bind(convertView);

        bindingLayout.txtS1.setText(sPhien);
        bindingLayout.txtS1Index.setText(detailHome.getMARKETINDEX());

        bindingLayout.txtS1UpDow.setText(Html.fromHtml(
                App.getInstance().getResources().getString(R.string.marketOverview_detail_txtS1UpDow,
                        detailHome.getStrArrow0(), detailHome.getCHGINDEX(), detailHome.getPCTINDEX())));
        bindingLayout.txtS1Vol.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHome.getTOTALQTTY())));
        bindingLayout.txtS1Val.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHome.getTOTALVALUE())));

        binding.lnS1S2S3.addView(bindingLayout.getRoot());

        setColorUpDown(detailHome.getStrArrow0(), bindingLayout.txtS1UpDow);

        if (presenter.getBooleanModeTheme()) {
            bindingLayout.txtS1.setTextColor(getResources().getColor(R.color.Black));
            bindingLayout.txtS1Index.setTextColor(getResources().getColor(R.color.Black));
            bindingLayout.txtS1Vol.setTextColor(getResources().getColor(R.color.Black));
            bindingLayout.txtS1Val.setTextColor(getResources().getColor(R.color.Black));
        } else {
            bindingLayout.txtS1.setTextColor(getResources().getColor(R.color.White));
            bindingLayout.txtS1Index.setTextColor(getResources().getColor(R.color.White));
            bindingLayout.txtS1Vol.setTextColor(getResources().getColor(R.color.White));
            bindingLayout.txtS1Val.setTextColor(getResources().getColor(R.color.White));
        }
    }

    @Override
    public void loadDataHNX_UPCOM_30(DetailHomeHNX_UPCOM_30 detailHomeHNX_upcom_30) {
        binding.txtIndex.setText(sIndex + ": " + detailHomeHNX_upcom_30.getIndex());

        binding.txtVolume.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHomeHNX_upcom_30.getTongKL())));
        binding.txtChange.setText(Html.fromHtml(
                App.getInstance().getResources().getString(R.string.marketOverview_detail_txtChange,
                        detailHomeHNX_upcom_30.getIndexChange(), detailHomeHNX_upcom_30.getIndexChangePer())));
        binding.txtValue.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHomeHNX_upcom_30.getTongGT())));

        binding.txtUp.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeHNX_upcom_30.getSoMaTang())));
        binding.txtBt.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeHNX_upcom_30.getSoMaKhongdoi())));
        binding.txtDown.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeHNX_upcom_30.getSoMaGiam())));

//        binding.txtUp.setText("▲" + detailHomeHNX_upcom_30.getSoMaTang());
//        binding.txtBt.setText("■" + detailHomeHNX_upcom_30.getSoMaKhongdoi());
//        binding.txtDown.setText("▼" + detailHomeHNX_upcom_30.getSoMaGiam());

        binding.txtPutVol.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVol, detailHomeHNX_upcom_30.getKLKhopGDTTCP())));
        binding.txtPutVal.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVal, detailHomeHNX_upcom_30.getGTKhopGDTTCP())));

        setColorUpDown(detailHomeHNX_upcom_30.getStrArrow(), binding.txtIndex);
        setColorUpDown(detailHomeHNX_upcom_30.getStrArrow(), binding.txtChange);
    }

    @Override
    public void loadDataHNX(DetailHomeHNX detailHomeHNX) {
        binding.txtIndex.setText(sIndex + ": " + detailHomeHNX.getIndex());

        binding.txtVolume.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHomeHNX.getTotalQty())));
        binding.txtChange.setText(Html.fromHtml(
                App.getInstance().getResources().getString(R.string.marketOverview_detail_txtChange,
                        detailHomeHNX.getIndexChange(), detailHomeHNX.getIndexChangePer())));
        binding.txtValue.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHomeHNX.getTotalVal())));

        binding.txtUp.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeHNX.getCountInc())));
        binding.txtBt.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeHNX.getCountEqual())));
        binding.txtDown.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeHNX.getCountDown())));

        binding.txtPutVol.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVol, detailHomeHNX.getKLKhop())));
        binding.txtPutVal.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVal, detailHomeHNX.getGTKhop())));

        setColorUpDown(detailHomeHNX.getStrArrow(), binding.txtIndex);
        setColorUpDown(detailHomeHNX.getStrArrow(), binding.txtChange);
    }

    @Override
    public void loadDataUPCOM(DetailHomeUpcom detailHomeUpcom) {
        binding.txtIndex.setText(sIndex + ": " + detailHomeUpcom.getIndex());

        binding.txtVolume.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHomeUpcom.getTongKL())));
        binding.txtChange.setText(Html.fromHtml(
                App.getInstance().getResources().getString(R.string.marketOverview_detail_txtChange,
                        detailHomeUpcom.getIndexChange(), detailHomeUpcom.getIndexChangePer())));
        binding.txtValue.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHomeUpcom.getTongGT())));

        binding.txtUp.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeUpcom.getSoMaTang())));
        binding.txtBt.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeUpcom.getSoMaKhongdoi())));
        binding.txtDown.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeUpcom.getSoMaGiam())));

        binding.txtPutVol.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVol, detailHomeUpcom.getKLKhopGDTTCP())));
        binding.txtPutVal.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVal, detailHomeUpcom.getGTKhopGDTTCP())));

        setColorUpDown(detailHomeUpcom.getStrArrow(), binding.txtIndex);
        setColorUpDown(detailHomeUpcom.getStrArrow(), binding.txtChange);
    }

    @Override
    public void loadDataVNI30(DetailHomeVNI30 detailHomeVNI30) {
        binding.txtIndex.setText(sIndex + ": " + detailHomeVNI30.getIndex());

        binding.txtVolume.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHomeVNI30.getTongKL())));
        binding.txtChange.setText(Html.fromHtml(
                App.getInstance().getResources().getString(R.string.marketOverview_detail_txtChange,
                        detailHomeVNI30.getIndexChange(), detailHomeVNI30.getIndexChangePer())));
        binding.txtValue.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHomeVNI30.getTongGT())));

        binding.txtUp.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeVNI30.getSomaTang())));
        binding.txtBt.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeVNI30.getSomaKhongdoi())));
        binding.txtDown.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeVNI30.getSomaGiam())));

        binding.txtPutVol.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVol, "0")));
        binding.txtPutVal.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVal, "0")));

        setColorUpDown(detailHomeVNI30.getStrArrow(), binding.txtIndex);
        setColorUpDown(detailHomeVNI30.getStrArrow(), binding.txtChange);
    }

    @Override
    public void loadDataHNX_30(DetailHomeHNX30 detailHomeHNX30) {
        binding.txtIndex.setText(sIndex + ": " + detailHomeHNX30.getIndex());

        binding.txtVolume.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHomeHNX30.getTongKL())));
        binding.txtChange.setText(Html.fromHtml(
                App.getInstance().getResources().getString(R.string.marketOverview_detail_txtChange,
                        detailHomeHNX30.getIndexChange(), detailHomeHNX30.getIndexChangePer())));
        binding.txtValue.setText(Html.fromHtml(App.getInstance().getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHomeHNX30.getTongGT())));

        binding.txtUp.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeHNX30.getSomaTang())));
        binding.txtBt.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeHNX30.getSomaKhongdoi())));
        binding.txtDown.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeHNX30.getSomaGiam())));

        binding.txtPutVol.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVol, "0")));
        binding.txtPutVal.setText(Html.fromHtml(App.getInstance().getResources().getString(R.string.marketOverview_detail_txtPutVal, "0")));

        setColorUpDown(detailHomeHNX30.getStrArrow(), binding.txtIndex);
        setColorUpDown(detailHomeHNX30.getStrArrow(), binding.txtChange);
    }

    @Override
    public void onload(boolean b) {
        if (b) {
            binding.llProgressBar.setVisibility(View.VISIBLE);
        } else {
            binding.llProgressBar.setVisibility(View.GONE);
        }

    }

    private void setColorUpDown(String s, TextView textView) {
        if (s.equals("▲")) {
            textView.setTextColor(getResources().getColor(R.color.green));
        }

        if (s.equals("■")) {
            textView.setTextColor(getResources().getColor(R.color.orange));
        }

        if (s.equals("▼")) {
            textView.setTextColor(getResources().getColor(R.color.red));
        }
    }

    public void setCustomFont() {

        ViewGroup vg = (ViewGroup) binding.tablayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    //Put your font in assests folder
                    //assign name of the font here (Must be case sensitive)
                    Typeface typeface = ResourcesCompat.getFont(App.getInstance(), R.font.free_sans_bold);
                    ((TextView) tabViewChild).setTypeface(typeface, Typeface.NORMAL);
                    ((TextView) tabViewChild).setTextSize(R.dimen._11sdp);
                }
            }
        }
    }
}
