package mobile.fpts.com.ezmibile.view.watchlist.sort;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hoadt.mobile.sortcode.DragSortListView;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragmentWatchlistSortCodeBinding;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.base.FragmentApp;
import mobile.fpts.com.ezmibile.view.main.MainActivity;


public class SortCodeFragment extends FragmentApp implements ISortCodeView {

    FragmentWatchlistSortCodeBinding binding;
    SortCodeAdapter adapter;
    List<String> codeList = new ArrayList<>();
    SortCodePresenter presenter;

    public static SortCodeFragment newInstance() {
        SortCodeFragment fragment = new SortCodeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        App.setPosition(Define.TYPE_MENU_SORT);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist_sort_code, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        presenter = new SortCodePresenter(this);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDisplay(String[] codeList) {
        adapter = new SortCodeAdapter(codeList);
        binding.listSortCode.setAdapter(adapter);
        binding.listSortCode.setDropListener(onDrop);
        binding.listSortCode.setRemoveListener(onRemove);
        binding.listSortCode.setDragScrollProfile(ssProfile);
    }

    private DragSortListView.DropListener onDrop =
            new DragSortListView.DropListener() {
                @Override
                public void drop(int from, int to) {
                    if (from != to) {
                        String item = adapter.getItem(from);
                        adapter.remove(item);
                        adapter.insert(item, to);
                    }
                }
            };
    private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
        @Override
        public void remove(int which) {

            if (adapter.getCount() == 1) {
//                alert();
            } else {
                adapter.remove(adapter.getItem(which));
                adapter.notifyDataSetChanged();
            }
        }
    };


    private DragSortListView.DragScrollProfile ssProfile =
            new DragSortListView.DragScrollProfile() {
                @Override
                public float getSpeed(float w, long t) {
                    if (w > 0.8f) {
                        // Traverse all views in a millisecond
                        return ((float) adapter.getCount()) / 0.001f;
                    } else {
                        return 10.0f * w;
                    }
                }
            };

    private void alert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(App.getInstance());
        alertDialogBuilder.setTitle(" WatchList must have at least one company");
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
