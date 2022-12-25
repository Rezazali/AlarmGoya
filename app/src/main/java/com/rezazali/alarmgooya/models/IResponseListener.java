package com.rezazali.alarmgooya.models;

public interface IResponseListener<T> {

    public void onSuccess(T responseMessage);

    public void onFailure(T errorResponseMessage);

}
