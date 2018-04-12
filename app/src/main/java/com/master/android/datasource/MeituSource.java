package com.master.android.datasource;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.master.android.http.RxSchedulers;
import com.master.android.http.RxRetrofit;
import com.master.android.meitu.ImageResponse;
import com.master.android.meitu.UserResponse;

public final class MeituSource {

    private MeituSource() {
    }

    /**
     * 局部变量导致观察者失效
     */
    @Deprecated
    @SuppressLint("CheckResult")
    public static MutableLiveData<UserResponse> getUserResponse(String id) {
        MutableLiveData<UserResponse> liveData = new MutableLiveData<>();
        RxRetrofit.instance().getUserService()
                .getUserResponse(id)
                .compose(RxSchedulers.scheduler())
                .onErrorReturn(UserResponse::error)
                .subscribe(liveData::setValue);
        return liveData;
    }

    @SuppressLint("CheckResult")
    public static void
    requestUserResponse(String id, MutableLiveData<UserResponse> liveData) {
        RxRetrofit.instance()
                .getUserService()
                .getUserResponse(id)
                .compose(RxSchedulers.scheduler())
                .onErrorReturn(UserResponse::error)
                .subscribe(liveData::setValue);
    }

    @SuppressLint("CheckResult")
    public static void
    requestImageResponse(String id, Long maxTime, MutableLiveData<ImageResponse> liveData) {
        RxRetrofit.instance()
                .getUserService()
                .getImageResponse(10, id, maxTime)
                .compose(RxSchedulers.scheduler())
                .onErrorReturn(ImageResponse::error)
                .subscribe(liveData::setValue);
    }

}