package com.autumntechcreation.click4panditcustomer.network;

import androidx.lifecycle.LiveData;

import com.autumntechcreation.click4panditcustomer.ui.register.RegisterResponse;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Webservice {

    @FormUrlEncoded
    @POST()
    LiveData<ApiResponse<RegisterResponse>> Register(@Url String apiname,
                                                     @Body JsonObject jsonObject

    );

}
