package mobile.fpts.com.ezmibile.model.api;


import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.CheckDateTimeData;
import mobile.fpts.com.ezmibile.model.entity.chart.HistoryChartOtherIndex;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX30;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX_UPCOM_30;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeUpcom;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeVNI;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeVNI30;
import mobile.fpts.com.ezmibile.model.entity.detail_home.TopRealtime;
import mobile.fpts.com.ezmibile.model.entity.events.EventsApp;
import mobile.fpts.com.ezmibile.model.entity.market.Quotes2;
import mobile.fpts.com.ezmibile.model.entity.market.VnIndices;
import mobile.fpts.com.ezmibile.model.entity.news.NewsArticle;
import mobile.fpts.com.ezmibile.model.entity.stock.StockInfo;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;
import mobile.fpts.com.ezmibile.view.watchlist.detail.DetailCodeData;
import mobile.fpts.com.ezmibile.view.watchlist.detail.financeOverview.EzsReportData;
import mobile.fpts.com.ezmibile.view.watchlist.detail.financialFigures.EzsFinanceData;
import mobile.fpts.com.ezmibile.view.watchlist.detail.statistics.StatisticData;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiClient {
    //http://10.26.2.46/GateWAY/fpts/
    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=CheckDateTime
    @GET("fpts/?s=CheckDateTime")
    Observable<CheckDateTimeData> checkDateTime();

    @GET("fpts/")
    Observable<String> getString(@Query("s") String s);

    @GET("fpts/")
    Observable<String> getString(@Query("s") String s, @Query("symbol") String symbol);

    //  MarketData Overview
    //  https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=others_index&c=2&language=1
    @GET("fpts/?s=others_index")
    Observable<List<VnIndices>> getDataVnIndices(@Query("c") int c, @Query("language") int language);

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=vn_indices
    @GET("fpts/?s=vn_indices")
    Observable<List<VnIndices>> getDataVnIndices();

    //---------------------------------------------->WATCHLIST<-------------------------------------------------
    // TODO:HoaDT 6/25/2018 bảng giá
    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=quotes2&symbol=fpt,fts,gas,vnm
    @GET("fpts/?s=quotes2")
    Observable<List<Quotes2>> getListQuotes2(@Query("symbol") String symbol);

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=ezs_basic&symbol=fpt
    @GET("fpts/?s=ezs_basic")
    Observable<List<DetailCodeData>> getEzsBasic(@Query("symbol") String symbol);

    ////Kết quả hoạt động kinh doanh
    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=ezs_report&symbol=fpt
    @GET("fpts/?s=ezs_report")
    Observable<List<EzsReportData>> getListEzsReportData(@Query("symbol") String symbol);

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=ezs_finance&symbol=fpt
    @GET("fpts/?s=ezs_finance")
    Observable<List<EzsFinanceData>> getListEzsFinanceData(@Query("symbol") String symbol);

    //https://eztrade3.fpts.com.vn/GateWAYDEV/realtime/?s=fpt
    @GET("realtime/")
    Observable<List<HistoryChartOtherIndex>> getChartRealtime(@Query("s") String symbol);

    //    https://eztrade3.fpts.com.vn/GateWAYDEV/history/?s=fpt
    @GET("history/")
    Observable<List<HistoryChartOtherIndex>> getChartHistory(@Query("s") String s);

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=statistic&symbol=fpt
    @GET("fpts/?s=statistic")
    Observable<List<StatisticData>> getStatistic(@Query("symbol") String symbol);

    //---------------------------------------------->NEWS<--------------------------------------------------
    ////    https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=news2&folder=86
//          https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=new2&folder=512
    @GET("fpts/?s=news2")
    Observable<List<NewsArticle>> getListNews(@Query("folder") String folder);

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=company_news&language=1&symbol=FPT&pageindex=1&pagesize=8
    @GET("fpts/?s=company_news")
    Observable<List<NewsArticle>> getListNews_company(@Query("symbol") String symbol,
                                                      @Query("language") String language,
                                                      @Query("pagesize") String pagesize);

//     https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=news_detail&id=1084518
    @GET("fpts/?s=news_detail")
    Observable<Response<ResponseBody>> getDetailNews(@Query("id") String id);

    //---------------------------------------------->CHỈ SỐ THẾ GIỚI<-------------------------------------------------
    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=world_indices
    @GET("fpts/?s=world_indices")
    Observable<List<WorldIndices>> getWorldIndices();

    //---------------------------------------------->EVENTS<-------------------------------------------------
    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=eventbydate&language=1
    @GET("fpts/?s=eventbydate")
    Observable<List<EventsApp>> getEvents(@Query("language") String language);

    //---------------------------------------------CHART--------------------------------------------------
    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=codename2&c=0&language=1
    @GET("fpts/?s=codename2")
    Observable<List<StockInfo>> getNameCompany(@Query("c") String c, @Query("language") String language);

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=codename&c=0
    @GET("fpts/?s=codename")
    Observable<List<StockInfo>> getStockInfo(@Query("c") String c);

    // TODO: TamHV 6/27/2018 ghép code
    // TODO: TamHV 6/19/2018
    //http://gateway.fpts.com.vn/g5g/fpts/?s=realtime_index_ho
    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=realtime_index_ho
    @GET("fpts/?s=realtime_index_ho")
    Observable<List<DetailHomeVNI>> getDetailHomeVNI();

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=realtime_index_ha
    @GET("fpts/?s=realtime_index_ha")
    Observable<List<DetailHomeHNX>> getDetailHomeHNX();

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=realtime_index_up
    @GET("fpts/?s=realtime_index_up")
    Observable<List<DetailHomeUpcom>> getDetailHomeUpcom();

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=realtime_index_vni30
    @GET("fpts/?s=realtime_index_vni30")
    Observable<List<DetailHomeVNI30>> getDetailHomeVNI30();

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=realtime_index_hnx30
    @GET("fpts/?s=realtime_index_hnx30")
    Observable<List<DetailHomeHNX30>> getDetailHomeHNX30();

    @GET("fpts/")
    Observable<List<DetailHomeHNX_UPCOM_30>> getDetailHomeHNX_UPCOM_30(@Query("s") String s);

    //https://eztrade3.fpts.com.vn/GateWAYDEV/fpts/?s=top_realtime&c=1&type=2
    @GET("fpts/?s=top_realtime")
    Observable<List<TopRealtime>> getTopRealtime(@Query("c") String c, @Query("type") String type);


}
