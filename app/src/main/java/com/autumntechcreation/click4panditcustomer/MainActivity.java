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


import android.content.Intent;
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

    ActivityMainBinding activityMainBinding;
    DrawerLayout drawerLayout;
    private MenuItem selectedItem;
    private MainViewModel mMainViewModel;
    FragmentManager fragmentManager;
    private int tabSelected = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AndroidInjection.inject(this);

        activityMainBinding.setLifecycleOwner(this);
        drawerLayout = activityMainBinding.drawerLayout;
        fragmentManager = getSupportFragmentManager();


        mMainViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel.class);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setMainViewModel(mMainViewModel);
        NavigationView navigationView=(NavigationView)findViewById(R.id.activity_home_bnView);
        View hView = navigationView.getHeaderView(0);

       // setSupportActionBar(activityMainBinding.toolbar);



        activityMainBinding.imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();

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

        selectedItem =activityMainBinding.activityHomeBnView.getMenu().getItem(0);
        selectFragment(selectedItem);


        activityMainBinding.txtMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    //drawer is open
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });



        Static.replaceFragment(R.id.nav_host_fragment, getSupportFragmentManager(), new HomeFragment(), false, false);



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
          /*  case R.id.menu_dashboard:
                tabSelected = 0;
                Static.replaceFragment(R.id.nav_host_fragment, getSupportFragmentManager(), new DashBoardFragment(), false, false);

                break;*/



            case R.id.menu_home:

                HomeFragment homeFragment=new HomeFragment();

                tabSelected = 0;
                Static.replaceFragment(R.id.nav_host_fragment, getSupportFragmentManager(), homeFragment, false, false);


                break;

            case R.id.menu_profile:
                HomeFragment homeFragment1=new HomeFragment();

                tabSelected = 1;

                Static.replaceFragment(R.id.nav_host_fragment, getSupportFragmentManager(),  homeFragment1, false, false);
                break;

            case R.id.menu_order:
                tabSelected = 2;
                HomeFragment homeFragment2=new HomeFragment();
                Static.replaceFragment(R.id.nav_host_fragment, getSupportFragmentManager(),  homeFragment2, false, false);
                break;

            case R.id.menu_settings:

                tabSelected = 3;
                HomeFragment homeFragment3=new HomeFragment();
                Static.replaceFragment(R.id.nav_host_fragment, getSupportFragmentManager(),  homeFragment3, false, false);
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
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            Static.doExitApp(MainActivity.this);
        } else {
            super.onBackPressed();
        }
    }
}
