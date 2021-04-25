package com.autumntechcreation.click4panditcustomer.network;

import androidx.lifecycle.LiveData;

import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingLanguageModel;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingLocationModel;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageListModel;
import com.autumntechcreation.click4panditcustomer.ui.home.PujaCategoryModel;
import com.autumntechcreation.click4panditcustomer.ui.home.PujaTypesModel;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginResponse;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterResponse;
import com.autumntechcreation.click4panditcustomer.util.AllUrlsAndConfig;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Webservice {

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<RegisterResponse>> Register(@Url String apiname,
                                                     @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<LoginResponse>> Login(@Url String apiname,
                                               @Body JsonObject jsonObject

    );



    @GET()
    LiveData<ApiResponse<List<PujaTypesModel>>> getPujaTypesList(
            @Url String apiname

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<PujaCategoryModel>>> getPujaCategoryList(@Url String apiname,
                                                                 @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<ChoosePackageListModel>>> getPujaPackageList(@Url String apiname,
                                                                           @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<BookingLocationModel>>> getLocationList(@Url String apiname,
                                                                      @Body JsonObject jsonObject

    );

    @GET()
    LiveData<ApiResponse<List<BookingLanguageModel>>> getLanguageList(@Url String apiname

    );
}
