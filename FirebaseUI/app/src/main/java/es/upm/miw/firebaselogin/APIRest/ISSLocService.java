package es.upm.miw.firebaselogin.APIRest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ISSLocService {

    @GET("iss-now.json")
    Call<ISSLocationResponse> jsonISSLocation();
}
