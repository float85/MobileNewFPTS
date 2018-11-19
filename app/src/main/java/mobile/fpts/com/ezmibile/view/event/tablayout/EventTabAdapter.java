package mobile.fpts.com.ezmibile.view.event.tablayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.ItemEventsTablayoutBinding;
import mobile.fpts.com.ezmibile.model.entity.events.EventsApp;
import mobile.fpts.com.ezmibile.util.Define;

public class EventTabAdapter extends RecyclerView.Adapter<EventTabAdapter.RecyclerViewHolder> {
    private ArrayList<EventsApp> listData;
    ItemEventsTablayoutBinding binding;
    boolean bDate;

    public EventTabAdapter(ArrayList<EventsApp> listData, boolean bDate ) {
        this.listData = listData;
        this.bDate = bDate;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_events_tablayout, parent, false);
        View itemview = binding.getRoot();
        return new RecyclerViewHolder(itemview, binding);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        // TODO: TamHV 6/27/2018 cần sửa lại 
        binding.txtStockCode.setText(listData.get(position).getStockCode());
        binding.txtContent.setText(listData.get(position).getContent());
//        binding.txtGroupNm.setText(listData.get(position).getGroupNm());

        if (bDate) {
            holder.binding.txtDate.setVisibility(View.VISIBLE);
            //xử lí ngày giờ
            String[] sDate = listData.get(position).getDate1().split("\\s");
            binding.txtDate.setText("[" + sDate[0] + "]");
        } else {
            holder.binding.txtDate.setVisibility(View.GONE);
        }

        //set màu sáng tối cho item tablayout
        SharedPreferences preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
        boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
        if (isLight) {
            binding.constraint.setBackgroundColor(App.getInstance().getResources().getColor(R.color.colorBackground));

            binding.txtStockCode.setTextColor(App.getInstance().getResources().getColor(R.color.colorFont));
            binding.txtContent.setTextColor(App.getInstance().getResources().getColor(R.color.colorFont));
//            binding.txtGroupNm.setTextColor(App.getInstance().getResources().getColor(R.color.colorFont));
            binding.view.setBackgroundColor(App.getInstance().getResources().getColor(R.color.gray));
        } else {
            binding.constraint.setBackgroundColor(App.getInstance().getResources().getColor(R.color.colorBackgroundDark));
            binding.txtStockCode.setTextColor(App.getInstance().getResources().getColor(R.color.colorFontDark));
            binding.txtContent.setTextColor(App.getInstance().getResources().getColor(R.color.colorFontDark));
//            binding.txtGroupNm.setTextColor(App.getInstance().getResources().getColor(R.color.colorFontDark));

            binding.view.setBackgroundColor(App.getInstance().getResources().getColor(R.color.gray));


        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ItemEventsTablayoutBinding binding;

        public RecyclerViewHolder(View itemView, ItemEventsTablayoutBinding binding) {
            super(itemView);

            this.binding = binding;
        }
    }
}
