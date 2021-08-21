package com.autumntechcreation.click4panditcustomer.splash;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Window;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivitySplashBinding;
import com.autumntechcreation.click4panditcustomer.intro.IntroductionActivity;
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper;
import com.autumntechcreation.click4panditcustomer.ui.dashboard.DashBoardActivity;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dagger.android.AndroidInjection;

public class SplashScreenActivity extends AppCompatActivity  {


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    SplashViewModel mSplashViewModel;
    ActivitySplashBinding mActivitySplashBinding;

    SharedPreferences sp;
    private int interval = 2000;
    private static int SPLASH_TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        AndroidInjection.inject(this);
        mSplashViewModel = ViewModelProviders.of(this,viewModelFactory).get(SplashViewModel.class);
        mActivitySplashBinding.setLifecycleOwner(this);
        mActivitySplashBinding.setSplashViewModel(mSplashViewModel);




    }

    private void startActivityRoute() {

        Handler h = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                sp = getSharedPreferences("login", MODE_PRIVATE);
                if (sp.getBoolean("logged", false)) {
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {

                    //Intent intent = new Intent(SplashScreenActivity.this, DashBoardActivity.class);
                    Intent intent = new Intent(SplashScreenActivity.this, IntroductionActivity.class);
                    startActivity(intent);
                    sp.edit().putBoolean("logged", false).apply();
                    finish();

                }
            }
        };
        h.postDelayed(r, SPLASH_TIME_OUT);


    }
    public void forceUpdate() {
        PackageManager packageManager = this.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String currentVersion = packageInfo.versionName;
        new ForceUpdateAsync(currentVersion, SplashScreenActivity.this).execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {

        private String latestVersion;
        private String currentVersion;
        private Context context;

        public ForceUpdateAsync(String currentVersion, Context context) {
            this.currentVersion = currentVersion;
            this.context = context;
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            try {
                latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + context.getPackageName() + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();
                Log.e("latestversion", "---" + latestVersion);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (latestVersion != null) {
                if (Double.parseDouble(currentVersion) >= Double.parseDouble(latestVersion))
                //if (!currentVersion.equalsIgnoreCase(latestVersion))

                {
                    startActivityRoute();

                } else {
                    showForceUpdateDialog();
                }
            } else {
                startActivityRoute();
            }
            super.onPostExecute(jsonObject);
        }

        void showForceUpdateDialog() {
            final SweetAlertDialog sweetAlertDialog1 = new SweetAlertDialog(SplashScreenActivity.this, SweetAlertDialog.BUTTON_CONFIRM);
            sweetAlertDialog1.setTitleText("Update Application");
            sweetAlertDialog1.setContentText("Are you Sure?");
            sweetAlertDialog1.setConfirmText("Yes");
            sweetAlertDialog1.setCancelText("No");
            sweetAlertDialog1.setCancelable(false);
            sweetAlertDialog1.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    interval = 10;
                    sweetAlertDialog1.dismissWithAnimation();
                    finish();

                }
            });
            sweetAlertDialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
//                    executeHandler();
                    interval = 1000;
                    sweetAlertDialog1.dismissWithAnimation();
                    final String appPackageName = getPackageName();
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }


                }
            });
            sweetAlertDialog1.show();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        forceUpdate();
    }

}
