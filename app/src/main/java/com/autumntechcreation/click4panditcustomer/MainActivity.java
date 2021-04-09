package com.autumntechcreation.click4panditcustomer;

import androidx.annotation.NonNull;
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


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.autumntechcreation.click4panditcustomer.databinding.ActivityMainBinding;
import com.autumntechcreation.click4panditcustomer.network.ConnectivityReceiver;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragment;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

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


        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, host).setPrimaryNavigationFragment(host).commit();

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
                drawerLayout.closeDrawer(GravityCompat.START);
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
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    //drawer is open
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


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
                case R.id.menu_signout:
                    tabSelected = 4;
                    NavHostFragment navHostFragmentSignout= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                    navHostFragmentSignout.getNavController().navigate(R.id.signOutFragment);
                   // isHomeFragment=false;

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
        drawerLayout.closeDrawer(GravityCompat.START);
        return menuReturn;
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {

            getFragmentManager().popBackStack();
        }

        else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }


    public  void setToolbar(Boolean isMenu, Boolean isBack, Boolean isLockDrawer,Boolean backGround) {
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
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(backGround){
            activityMainBinding.toolbar.setBackgroundColor(Color.parseColor("#D1453E"));
            activityMainBinding.imgvwWhiteback.setImageResource(R.drawable.ic_whiteback);
        }else{
            activityMainBinding.toolbar.setBackgroundColor(Color.parseColor("#FFFFFF"));
            activityMainBinding.imgvwWhiteback.setImageResource(R.drawable.ic_back);
        }


    }


}
