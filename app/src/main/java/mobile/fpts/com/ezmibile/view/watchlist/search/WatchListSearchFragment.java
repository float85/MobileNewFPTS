package mobile.fpts.com.ezmibile.view.watchlist.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.TextWatcher;
import android.text.Editable;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ContentMainBinding;
import mobile.fpts.com.ezmibile.databinding.FragmentWatchlistSearchBinding;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Utils;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.view.watchlist.detail.DetailCodeFragment;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class WatchListSearchFragment extends FragmentApp implements ISearchView {
    FragmentWatchlistSearchBinding binding;
    WatchListSearchPresenter presenter;
    private ContentMainBinding mainBinding;
    SearchAdapter adapter;
    View view;
    AutoCompleteTextView autoCompleteTextViewtest1;
    String[] listCompany;

    public static WatchListSearchFragment newInstance() {
        WatchListSearchFragment fragment = new WatchListSearchFragment();
        Bundle bundle = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.setPosition(Define.TYPE_MENU_SEARCH);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist_search, container, false);
        mainBinding = DataBindingUtil.inflate(inflater, R.layout.content_main, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        loadDataOfAutoComleteTextView();
        presenter = new WatchListSearchPresenter(this);

        if (presenter.getWhiteBlackFormSPR()) {
            binding.linearLayout.setBackgroundResource(R.color.white);
            binding.edMaCk.setHintTextColor(getResources().getColor(R.color.autoTV_hint_white));
            binding.llSearch.setBackgroundResource(R.color.background_white);

            binding.text.setTextColor(getResources().getColor(R.color.black));
        } else {
            binding.linearLayout.setBackgroundResource(R.color.black);
            binding.edMaCk.setHintTextColor(getResources().getColor(R.color.autoTV_hint_black));
            binding.llSearch.setBackgroundResource(R.color.background_black);

            binding.text.setTextColor(getResources().getColor(R.color.white));
        }

        autoCompleteTextViewtest1 = view.findViewById(R.id.edMaCk);
        presenter.validateEdittext();
        presenter.loadData();
//        loadDataOfAutoComleteTextView();
        binding.btnBack.setOnClickListener(view1 -> getFragmentManager().popBackStack());
    }

    @Override
    public void loadDataOfAutoComleteTextView(ArrayList<CustomerAutoComleteTextView> arrayList) {
        binding.edMaCk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AutoCompleteTextView AutoCompleteTextViewtest = view.findViewById(R.id.edMaCk);
                if (AutoCompleteTextViewtest.isPerformingCompletion()) {
                    // An item has been selected from the list. Ignore.
                    return;
                }
                if (String.valueOf(s).compareToIgnoreCase("") == 0) {
                    return;
                } else {
                    try {
                        autoCompleteTextViewtest1.setThreshold(1);
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < arrayList.size(); i++) {
                            String strStockCode = arrayList.get(i).getStock_code() + " - " + arrayList.get(i).getName_vn();

                            if (arrayList.get(i).getStock_code().toLowerCase().startsWith(s.toString().toLowerCase())) {
                                list.add(strStockCode);
                            }
                        }

                        listCompany = new String[list.size()];
                        list.toArray(listCompany);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(App.getInstance().getApplicationContext(),
                                R.layout.item_watchlist_search_recyclerview, listCompany);
                        autoCompleteTextViewtest1.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        autoCompleteTextViewtest1.setOnItemClickListener((parent, view, position, id) -> {
            binding.edMaCk.setText("");

            presenter.saveNoteSearchRecent(listCompany[position],
                    Define.CATEGORY_SHARED_PREFERENCES_ITEM_CK_VN);
            presenter.saveNoteSearchRecent(listCompany[position],
                    Define.CATEGORY_SHARED_PREFERENCES_ITEM_CK_EN);

            // TODO:HoaDT 7/5/2018 thêm 1 mã vào bảng giá
            presenter.insertCode(listCompany[position]);
            Utils.hideKeyboard(getActivity());
            getFragmentManager().popBackStack();

        });
    }

    @Override
    public void validateEdittext(InputFilter[] inputFilter) {
        binding.edMaCk.setFilters(inputFilter);
    }

    @Override
    public void onDisplay(List<String> list) {
        adapter = new SearchAdapter(list, new IListener() {
            @Override
            public void onClick(String symbol) {
                setFragment(DetailCodeFragment.newInstance(symbol, false));
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerSeachRecent.setLayoutManager(layoutManager);
        binding.recyclerSeachRecent.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setFragment(FragmentApp fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(mainBinding.contentview.getId(), fragment)
                .addToBackStack("")
                .commit();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
