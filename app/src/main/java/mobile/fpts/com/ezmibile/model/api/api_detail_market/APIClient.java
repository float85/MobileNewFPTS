package mobile.fpts.com.ezmibile.model.api.api_detail_market;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by TrangDTH on 2/23/2018.
 */

public class APIClient {

    public Retrofit retrofit(String baseUrl) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit;
    }
}
