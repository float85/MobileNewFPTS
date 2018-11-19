package mobile.fpts.com.ezmibile.view.news.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.custormView.TextViewFont;

/**
 * Created by TrangDTH on 2/12/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private List<NewsArticle> newList;
    private String folder;
    INews.View v;

    public NewsAdapter(List<NewsArticle> newList, String folder, INews.View v) {
        this.newList = newList;
        this.folder = folder;
        this.v = v;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tintuc_fragment, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(newList.get(position).getNewsTitle());

        String date= "[" + newList.get(position).getNewsDate().split("\\s+")[0] + "]";
        holder.tvDate.setText(date);

        if (newList.get(position).getNewsImg().equals("")) {
            holder.imgNews.setImageResource(R.drawable.icon_app);
        } else {
            loadImg(holder.imgNews, newList.get(position).getNewsImg());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.moveFragmentView(newList.get(position).getNewsId(), newList.get(position).getNewsImg(), folder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    //    @BindingAdapter({"bind:imageUrl"})
    public static void loadImg(ImageView imageView, String url) {
        url= "http://www.fpts.com.vn" + url;
        if (!url.equals("")) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNews;
        TextViewFont tvTitle, tvDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgNews= (ImageView) itemView.findViewById(R.id.imgNews);
            tvTitle= (TextViewFont) itemView.findViewById(R.id.tvTitle);
            tvDate= (TextViewFont) itemView.findViewById(R.id.tvDate);
        }
    }
}
