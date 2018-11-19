package mobile.fpts.com.ezmibile.view.world_indexes;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemWorldIndecesBinding;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;

/**
 * Created by TrangDTH on 3/16/2018.
 */

public class WorldIndecesAdapter extends RecyclerView.Adapter<WorldIndecesAdapter.MyViewHolder> {
    private List<WorldIndices> list;
    ItemWorldIndecesBinding binding;

    public WorldIndecesAdapter(List<WorldIndices> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_world_indeces, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.binding.setWorldIndeces(list.get(position));
        if (Float.parseFloat(list.get(position).getChange()) < 0) {
            binding.tvValueGia.setTextColor(Color.parseColor("#ff4444"));
            binding.tvValueThaydoi.setTextColor(Color.parseColor("#ff4444"));
            binding.tvValueThaydoiPtram.setTextColor(Color.parseColor("#ff4444"));

        } else if (Float.parseFloat(list.get(position).getChange()) > 0) {
            binding.tvValueGia.setTextColor(Color.parseColor("#11a12c"));
            binding.tvValueThaydoi.setTextColor(Color.parseColor("#11a12c"));
            binding.tvValueThaydoiPtram.setTextColor(Color.parseColor("#11a12c"));

        } else {
            binding.tvValueGia.setTextColor(Color.parseColor("#f2c80d"));
            binding.tvValueThaydoi.setTextColor(Color.parseColor("#f2c80d"));
            binding.tvValueThaydoiPtram.setTextColor(Color.parseColor("#f2c80d"));
        }
        binding.tvValueChiso.setText(list.get(position).getTitle());
        binding.tvValueGia.setText(list.get(position).getPrice());
        binding.tvValueThaydoi.setText(list.get(position).getChange());
        binding.tvValueThaydoiPtram.setText(list.get(position).getChangePct());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(ItemWorldIndecesBinding binding) {
            super(binding.getRoot());
        }
    }
}
