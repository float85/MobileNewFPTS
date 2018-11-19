package mobile.fpts.com.ezmibile.view.news.detail.related_news;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.custormView.TextViewFont;
import mobile.fpts.com.ezmibile.view.news.detail.INewsDetail;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by TrangDTH on 2/12/2018.
 */

public class RelatedNewsDetailAdapter extends RecyclerView.Adapter<RelatedNewsDetailAdapter.MyViewHolder> {
    private List<NewsArticle> newList;
    private INewsDetail v;

    public RelatedNewsDetailAdapter(List<NewsArticle> newList, INewsDetail v) {
        this.newList = newList;
        this.v = v;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tin_lien_quan, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(newList.get(position).getNewsTitle());

        //Check theme white
        SharedPreferences shared = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, MODE_PRIVATE);
        boolean isLight = (shared.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true));
        //Xet mau tab layout
        holder.tvTitle.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.black) :
                App.getInstance().getResources().getColor(R.color.white));

        // TODO: TamHV 6/28/2018 tách ngày 
        String date = "[" + newList.get(position).getNewsDate().split("\\s+")[0] + "]";
        holder.tvDate.setText(date);

        if (newList.get(position).getNewsImg().equals("")) {
            holder.imgNews.setImageResource(R.drawable.icon_app);
        } else {
            loadImg(holder.imgNews, newList.get(position).getNewsImg());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v.moveFragment(newList.get(position).getNewsId(), newList.get(position).getNewsImg());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    //    @BindingAdapter({"bind:imageUrl"})
    public static void loadImg(ImageView imageView, String url) {
        url = "http://www.fpts.com.vn" + url;
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
            imgNews = (ImageView) itemView.findViewById(R.id.imgNews);
            tvTitle = (TextViewFont) itemView.findViewById(R.id.tvTitle);
            tvDate = (TextViewFont) itemView.findViewById(R.id.tvDate);
        }
    }
}
