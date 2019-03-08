package com.example.bulletinboard.controller;

public interface VolleyCallBack<T> {
    void onError(Throwable t);

    void onSuccess();

    void onFailure();
}
