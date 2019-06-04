package com.example.pruebas.APIRest;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ISSLocService {

    @GET("iss-now.json")
    Call<ISSLocationResponse> jsonISSLocation();
}
