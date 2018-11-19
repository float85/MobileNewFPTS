package mobile.fpts.com.ezmibile.view.watchlist.detail.analysis;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragmentAnalysisBinding;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;


public class AnalysisFragment extends FragmentApp {

    FragmentAnalysisBinding binding;

    public AnalysisFragment() {
        // Required empty public constructor
    }

    public static AnalysisFragment newInstance(String symbol) {
        AnalysisFragment fragment = new AnalysisFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_analysis, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<AnalysisData> dataList = new ArrayList<>();

        dataList.add(new AnalysisData("", "Financial Exchange Stock Talk: Mark Hibben On Nvidia", "Seeking Alpha", "8 hours ago"));
        dataList.add(new AnalysisData("", "Financial Exchange Stock Talk: Mark Hibben On Nvidia", "Zacks Investment Research", "9 hours ago"));
        dataList.add(new AnalysisData("", "Financial Exchange Stock Talk: Mark Hibben On Nvidia", "Zacks Investment Research", "10 hours ago"));
        dataList.add(new AnalysisData("", "Financial Exchange Stock Talk: Mark Hibben On Nvidia", "Seeking Alpha", "11 hours ago"));
        dataList.add(new AnalysisData("", "Financial Exchange Stock Talk: Mark Hibben On Nvidia", "Seeking Alpha", "12 hours ago"));
        dataList.add(new AnalysisData("", "Financial Exchange Stock Talk: Mark Hibben On Nvidia", "Zacks Investment Research", "13 hours ago"));
        dataList.add(new AnalysisData("", "Financial Exchange Stock Talk: Mark Hibben On Nvidia", "Seeking Alpha", "14 hours ago"));
        dataList.add(new AnalysisData("", "Financial Exchange Stock Talk: Mark Hibben On Nvidia", "Seeking Alpha", "15 hours ago"));
        dataList.add(new AnalysisData("", "Financial Exchange Stock Talk: Mark Hibben On Nvidia", "Zacks Investment Research", "16 hours ago"));
        AnalysisAdapter adapter = new AnalysisAdapter(dataList);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}
