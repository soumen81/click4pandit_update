package com.autumntechcreation.click4panditcustomer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.autumntechcreation.click4panditcustomer.databinding.ActivityMainBinding;
import com.autumntechcreation.click4panditcustomer.network.ConnectivityReceiver;
import com.autumntechcreation.click4panditcustomer.sharedpref.SharedPrefsHelper;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragment;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginActivity;
import com.autumntechcreation.click4panditcustomer.ui.search.SearchActivity;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        HasSupportFragmentInjector, ConnectivityReceiver.ConnectivityReceiverListener {


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    private NavHostFragment host = NavHostFragment.create(R.navigation.nav_graph);
    NavController navController;
    ActivityMainBinding activityMainBinding;
    DrawerLayout drawerLayout;
    private MenuItem selectedItem;
    private MainViewModel mMainViewModel;
    FragmentManager fragmentManager;
    private int tabSelected = 0;
    boolean isHomeFragment=true;
    SharedPrefsHelper mSharedPrefsHelper;
    private ProgressDialog mProgressDialog;
    SharedPreferences sp;
    TextView userName,txtInitial,email;
    String TAG="Soumen";
    Bundle bundle;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AndroidInjection.inject(this);

        activityMainBinding.setLifecycleOwner(this);
        drawerLayout = activityMainBinding.drawerLayout;
        fragmentManager = getSupportFragmentManager();


        mMainViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel.class);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setMainViewModel(mMainViewModel);
        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_home_bnView);
        View hView = navigationView.getHeaderView(0);

        userName=hView.findViewById(R.id.txt_Email);
        txtInitial=hView.findViewById(R.id.tvInitial);
        email= hView.findViewById(R.id.txtname);
        userName.setText("Hi"+" "+mMainViewModel.getFirstName());
        email.setText(mMainViewModel.getEmail());

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);

        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, host).setPrimaryNavigationFragment(host).commit();

        sp = getSharedPreferences("login",MODE_PRIVATE);

        activityMainBinding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this, SearchActivity.class);
                startActivity(in);
            }
        });

        activityMainBinding.imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment);
                navController.navigate(R.id.homeFragmentFragment);
            }
        });


        activityMainBinding.activityHomeBnView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectFragment(menuItem);
                drawerLayout.closeDrawer(GravityCompat.END);
                return true;
            }

        });
        activityMainBinding.imgvwWhiteback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        selectedItem = activityMainBinding.activityHomeBnView.getMenu().getItem(0);
        selectFragment(selectedItem);


        activityMainBinding.txtMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    //drawer is open
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "API Response : ");
        //Prints all extras. Replace with app logic.
        if (data != null) {

             bundle = data.getExtras();

        }
        Log.e("SOUMMMMMEN","onActivityResult");
    }
    public Bundle returnPaymentDetails(){

        return bundle;
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void selectFragment(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.menu_home:

                tabSelected = 0;
                NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                navHostFragment.getNavController().navigate(R.id.homeFragmentFragment);
               // isHomeFragment=true;

                break;

            case R.id.menu_profile:

                tabSelected = 1;
                NavHostFragment navHostFragmentProfile = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                navHostFragmentProfile.getNavController().navigate(R.id.profileFragment);
               // isHomeFragment=false;

                break;

            case R.id.menu_order:
                tabSelected = 2;
                NavHostFragment navHostFragmentOrders= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                navHostFragmentOrders.getNavController().navigate(R.id.orderFragment);
               // isHomeFragment=false;

                break;

            case R.id.menu_settings:
                tabSelected = 3;
                NavHostFragment navHostFragmentSettings= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                navHostFragmentSettings.getNavController().navigate(R.id.settingsFragment);
                //isHomeFragment=false;

                break;
                case R.id.menu_changepassword:
                tabSelected = 4;
                NavHostFragment navHostFragmentChangePassword= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                    navHostFragmentChangePassword.getNavController().navigate(R.id.changePasswordAcitivity);
                //isHomeFragment=false;

                break;
                case R.id.menu_addressbook:
                tabSelected = 5;
                NavHostFragment navHostAddressBook= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                    navHostAddressBook.getNavController().navigate(R.id.addressFragment);
                //isHomeFragment=false;

                break;
                case R.id.menu_shop:
                tabSelected = 6;
                NavHostFragment navHostShop= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                    navHostShop.getNavController().navigate(R.id.shopFragment);
                //isHomeFragment=false;

                break;
                case R.id.menu_signout:
                    /*tabSelected = 4;
                    NavHostFragment navHostFragmentSignout= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                    navHostFragmentSignout.getNavController().navigate(R.id.signOutFragment);
                   // isHomeFragment=false;

                break;*/

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                    sweetAlertDialog.setTitleText("Logout");
                    sweetAlertDialog.setContentText("Do you want to logout?");
                    sweetAlertDialog.setConfirmText("Yes");
                    sweetAlertDialog.setCancelText("No");
                    sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {


                            // mProfileViewModel.deleteData();
                            sweetAlertDialog.dismissWithAnimation();
                            mProgressDialog.show();

                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    final Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //add your code here
                                            mProgressDialog.dismiss();

                                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                                            // intent.putExtra("finish", true); // if you are checking for this in your other Activities
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
                                            sp.edit().putBoolean("logged",false).apply();
                                            finish();

                                        }
                                    }, 1000);

                                }
                            });


                        }
                    });
                    sweetAlertDialog.show();
                    break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        boolean menuReturn;


        switch (menuItem.getItemId()) {

            default:
                menuReturn = super.onOptionsItemSelected(menuItem);
                break;
        }


        // Close the navigation drawer
        drawerLayout.closeDrawer(GravityCompat.END);
        return menuReturn;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Fragment currentFragment = MainActivity.this.getSupportFragmentManager().findFragmentById(R.id.fragment);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        int id=navController.getCurrentDestination().getId();
        if(id==R.id.homeFragmentFragment ){
            selectedPosition(0);
            Static.doExitApp(MainActivity.this);
        }else if(id==R.id.choosePackageFragment ){
            selectedPosition(1);

        }else if(id==R.id.bookingPujaFragment ){
            selectedPosition(2);

        }else if(id==R.id.orderSummaryFragment ){
            selectedPosition(3);

        } else if(id==R.id.billingDetailsFragment ){
            selectedPosition(4);

        }else if(id==R.id.differentPujaLocationFragment ) {
            selectedPosition(5);

        }

       /* if (getFragmentManager().getBackStackEntryCount() > 0) {

            getFragmentManager().popBackStack();
        }

        else {

            super.onBackPressed();
        }*/
      /*  NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);

        } else if (navController.popBackStack()) {

            Log.e("navController", navController.navigateUp() + "");
        } else if (getFragmentManager().getBackStackEntryCount() > 0) {

            getFragmentManager().popBackStack();
        } else {
            // super.onBackPressed();
            Static.doExitApp(MainActivity.this);
        }*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Static.deleteCache(MainActivity.this);
    }

    public  void setToolbar(Boolean isMenu, Boolean isBack, Boolean isLockDrawer, Boolean backGround) {
        if (isMenu) {
            activityMainBinding.txtMenu.setVisibility(View.VISIBLE);

        } else {
            activityMainBinding.txtMenu.setVisibility(View.GONE);
        }
        if (isBack) {
            activityMainBinding.imgvwWhiteback.setVisibility(View.VISIBLE);

        } else {
            activityMainBinding.imgvwWhiteback.setVisibility(View.GONE);

        }
        if (isLockDrawer) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            drawerLayout.closeDrawer(GravityCompat.END);
        }
        if(backGround){
            activityMainBinding.toolbar.setBackgroundColor(Color.parseColor("#d23e34"));
            activityMainBinding.imgvwWhiteback.setImageResource(R.drawable.ic_whiteback);
            activityMainBinding.header.setImageResource(R.drawable.ic_whitelogo);
            activityMainBinding.rlHeader.setBackgroundColor(Color.parseColor("#D1453E"));
            activityMainBinding.header.setBackgroundColor(Color.parseColor("#D1453E"));
            activityMainBinding.imgvwWhiteback.setBackgroundColor(Color.parseColor("#D1453E"));
            activityMainBinding.imgSearch.setImageResource(R.drawable.ic_search);
            activityMainBinding.imgSearch.setBackgroundColor(Color.parseColor("#D1453E"));
            activityMainBinding.imgCart.setImageResource(R.drawable.cart);
            activityMainBinding.imgCart.setBackgroundColor(Color.parseColor("#D1453E"));
        }else{
            activityMainBinding.toolbar.setBackgroundColor(Color.parseColor("#FFFFFF"));
            activityMainBinding.rlHeader.setBackgroundColor(Color.parseColor("#FFFFFF"));
            activityMainBinding.header.setBackgroundColor(Color.parseColor("#FFFFFF"));
            activityMainBinding.imgvwWhiteback.setBackgroundColor(Color.parseColor("#FFFFFF"));
            activityMainBinding.imgvwWhiteback.setImageResource(R.drawable.ic_back);
            activityMainBinding.imgSearch.setImageResource(R.drawable.ic_loupe);
            activityMainBinding.imgSearch.setBackgroundColor(Color.parseColor("#FFFFFF"));
            activityMainBinding.imgCart.setImageResource(R.drawable.cart_black);
            activityMainBinding.imgCart.setBackgroundColor(Color.parseColor("#FFFFFF"));
            activityMainBinding.header.setImageResource(R.drawable.ic_black_logo);
        }


    }

    private void selectedPosition(int pos){
        NavigationView navigationView = (NavigationView) findViewById(R.id.activity_home_bnView);
        for (int i = 0; i >=navigationView.getMenu().size(); i++) {
            navigationView.getMenu().getItem(pos).setChecked(false);

        }
        navigationView.getMenu().getItem(pos).setChecked(true);
    }


}
