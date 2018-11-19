package mobile.fpts.com.ezmibile.model.api.api_detail_market;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    //Tin tuc
    @GET(".")
    Call<String> getStringTinTuc(@Query("s") String s, @Query("language") String language, @Query("folder") String folder,
                                 @Query("symbol") String symbol, @Query("pageindex") String pageindex,
                                 @Query("pagesize") String pagesize);

    @GET(".")
    Call<String> getStringDetailTinTuc(@Query("s") String s, @Query("id") String id);

    //Chi so thi truong
    @GET(".")
    Call<String> getStringWorldIndeces(@Query("s") String s);

    //Bang gia
    @GET(" ")
    Call<String> getAnswers();
}
