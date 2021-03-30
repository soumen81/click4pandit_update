package com.autumntechcreation.click4panditcustomer.ui.home;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }
}
