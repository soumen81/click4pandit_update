package com.autumntechcreation.click4panditcustomer.di.module;

import android.support.annotation.NonNull;

import com.autumntechcreation.click4panditcustomer.BuildConfig;
import com.autumntechcreation.click4panditcustomer.network.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.autumntechcreation.click4panditcustomer.util.AppConstants.BASE_URL;
import static com.autumntechcreation.click4panditcustomer.util.AppConstants.CONNECT_TIMEOUT;
import static com.autumntechcreation.click4panditcustomer.util.AppConstants.READ_TIMEOUT;
import static com.autumntechcreation.click4panditcustomer.util.AppConstants.WRITE_TIMEOUT;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    HttpLoggingInterceptor provideOkHttpInterceptors() {
        return new HttpLoggingInterceptor().
                setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    /**
     * Configure OkHttpClient. This helps us override some of the default configuration. Like the
     * connection timeout.
     *
     * @return OkHttpClient
     */
    @Provides
    @Singleton
    OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofitClient(@NonNull OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) // Serialize Objects
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Set call to return {@link Observable}
                .build();
    }
}
