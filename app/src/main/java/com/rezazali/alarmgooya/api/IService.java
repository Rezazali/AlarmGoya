package com.rezazali.alarmgooya.api;

import com.rezazali.alarmgooya.models.App;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IService {


    @GET("getCurrentVersion.php")
    Call<App> getDownload();

}
