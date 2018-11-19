//package mobile.fpts.com.ezmibile.model.api.api_str;
//
//
//import java.util.List;
//
//import mobile.fpts.com.ezmibile.model.entity.Market;
//import mobile.fpts.com.ezmibile.model.entity.stock.StockInfo;
//import retrofit2.Call;
//import retrofit2.http.GET;
//import retrofit2.http.Query;
//import rx.Observable;
//
//public interface ApiClient {
//
//    @GET("fpts/")
//    Observable<String> getString(@Query("s") String s);
//
//    @GET("fpts/")
//    Observable<String> getString(@Query("s") String s, @Query("symbol") String symbol);
//
//    @GET("realtime/")
//    Observable<String> getDataMarketChart_OneDay(@Query("s") String s);
//
//    //  MarketData Overview
//    //  http://gateway.fpts.com.vn/g5g/fpts/?s=others_index&c=1&language=0
//    @GET("fpts/")
//    Observable<String> getDataMarket(@Query("s") String s, @Query("c") int c, @Query("language") int language);
//
//    //WATCHLIST
//
//    //http://demo3946559.mockable.io/tongquanthitruong
//    @GET("tongquanthitruong")
//    Observable<List<Market>> getDataMarket();
//
//    @GET("fpts/")
//    Observable<String> getNewsArticle(@Query("s") String s, @Query("symbol") String symbol, @Query("pageindex") String index);
//
//    @GET("realtime/")
//    Observable<String> getChartRealtime(@Query("s") String s);
//
//    //CHART
//    //https://eztrade3.fpts.com.vn/GateWAYDEV/history/?s=fpt
//    @GET("history/")
//    Observable<String> getChartHistory(@Query("s") String s);
//
//
//    //Tin tuc
//    @GET(".")
//    Call<String> getStringTinTuc(@Query("s") String s, @Query("language") String language, @Query("folder") String folder,
//                                 @Query("symbol") String symbol, @Query("pageindex") String pageindex,
//                                 @Query("pagesize") String pagesize);
//
//    @GET(".")
//    Call<String> getStringDetailTinTuc(@Query("s") String s, @Query("id") String id);
//
//    //Chi so thi truong
//    @GET(".")
//    Call<String> getStringWorldIndeces(@Query("s") String s);
//
//    //Bang gia
//    @GET(" ")
//    Call<String> getAnswers();
//
//    @GET("fpts/")
//    Observable<String> getMarketDetail(@Query("s") String s);
//
//}
