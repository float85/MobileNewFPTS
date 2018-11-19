package mobile.fpts.com.ezmibile.view.world_indexes;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.FragmentWorldIndecesBinding;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndicesDB;
import mobile.fpts.com.ezmibile.util.Define;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by TrangDTH on 3/16/2018.
 */

public class WorldIndecesFragment extends Fragment implements IWorldIndeces.View {
    private List<WorldIndicesDB> list;
    private IWorldIndeces.Presenter presenter;
    private WorldIndecesAdapter adapter;
    private FragmentWorldIndecesBinding binding;

    public static WorldIndecesFragment newInstance() {
        WorldIndecesFragment fragment = new WorldIndecesFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_world_indeces, container, false);
        App.setPosition(Define.TYPE_MENU_WORLD_INDEX);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.world_index);

        binding.llProgressWorld.setVisibility(View.VISIBLE);
        binding.llHeaderWorld.setVisibility(View.GONE);
        binding.rcvWorldIndeces.setVisibility(View.GONE);

        return binding.getRoot();
    }

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = new ArrayList<>();
        binding.tvTitleChiso.setText(getString(R.string.indexes));
        binding.tvTitleGia.setText(getString(R.string.last));
        binding.tvTitleThaydoi.setText(getString(R.string.change));
        binding.tvTitleThaydoiPtram.setText(getString(R.string.change_phan_tram));

        //Check theme black
        SharedPreferences shared = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, MODE_PRIVATE);
        boolean isLight = shared.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
        if (!isLight) {
            //Xet mau tab layout
            binding.llHeaderWorld.setBackground(getResources().getDrawable(R.color.colorHeaderDark));
            binding.tvTitleChiso.setTextColor(getResources().getColor(R.color.white));
            binding.tvTitleGia.setTextColor(getResources().getColor(R.color.white));
            binding.tvTitleThaydoi.setTextColor(getResources().getColor(R.color.white));
            binding.tvTitleThaydoiPtram.setTextColor(getResources().getColor(R.color.white));
        }
        presenter = new WorldIndecesPresenter(WorldIndecesFragment.this, list);
    }

    @Override
    public void dataDone(List<WorldIndices> worldIndecesList) {
        adapter = new WorldIndecesAdapter(worldIndecesList);
        binding.rcvWorldIndeces.setAdapter(adapter);
//        //Them dong ke giua item
//        binding.rcvWorldIndeces.addItemDecoration(new DividerItemDecoration(getContext(), 1));

        adapter.notifyDataSetChanged();

        binding.llProgressWorld.setVisibility(View.GONE);
        binding.llHeaderWorld.setVisibility(View.VISIBLE);
        binding.rcvWorldIndeces.setVisibility(View.VISIBLE);
    }

    @Override
    public void dataFail() {
        binding.llProgressWorld.setVisibility(View.GONE);
        binding.llHeaderWorld.setVisibility(View.VISIBLE);
        binding.rcvWorldIndeces.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), "Data Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoad() {
        binding.llProgressWorld.setVisibility(View.VISIBLE);
        binding.llHeaderWorld.setVisibility(View.GONE);
        binding.rcvWorldIndeces.setVisibility(View.GONE);
    }

    @Override
    public void connectFail() {
        binding.llProgressWorld.setVisibility(View.GONE);
        binding.llHeaderWorld.setVisibility(View.VISIBLE);
        binding.rcvWorldIndeces.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), "Connect Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void connectServerFail() {
        binding.llProgressWorld.setVisibility(View.GONE);
        binding.llHeaderWorld.setVisibility(View.VISIBLE);
        binding.rcvWorldIndeces.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), "Connect Server Fail", Toast.LENGTH_SHORT).show();
    }
}
