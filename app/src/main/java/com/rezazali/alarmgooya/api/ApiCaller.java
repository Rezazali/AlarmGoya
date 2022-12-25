package com.rezazali.alarmgooya.api;

import com.rezazali.alarmgooya.models.App;
import com.rezazali.alarmgooya.models.IResponseListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCaller {

    IService iService;

    public ApiCaller() {

        iService = ApiClient.getClient().create(IService.class);
    }


    public void getCurrentVersion(IResponseListener listener) {

        iService.getDownload().enqueue(new Callback<App>() {
            @Override
            public void onResponse(Call<App> call, Response<App> response) {

                listener.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<App> call, Throwable t) {

                listener.onFailure(t.getMessage().toString() + " ");

            }
        });


    }


}
