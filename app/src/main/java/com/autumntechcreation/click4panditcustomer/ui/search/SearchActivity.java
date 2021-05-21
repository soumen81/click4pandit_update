package com.autumntechcreation.click4panditcustomer.ui.search;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.ActivitySearchBinding;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    ActivitySearchBinding mActivitySearchBinding;
    SearchViewModel mSearchViewModel;
    String[] language ={"C","C++","Java",".NET","iPhone","Android","ASP.NET","PHP"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivitySearchBinding= DataBindingUtil.setContentView(this, R.layout.activity_search);
        AndroidInjection.inject(this);
        mSearchViewModel = ViewModelProviders.of(this,viewModelFactory).get(SearchViewModel.class);
        mActivitySearchBinding.setLifecycleOwner(this);
        mActivitySearchBinding.setSearchViewModel(mSearchViewModel);

        mActivitySearchBinding.searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);
        //Getting the instance of AutoCompleteTextView

        mActivitySearchBinding.tvAutoCompSearch.setThreshold(1);//will start working from first character
        mActivitySearchBinding.tvAutoCompSearch.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        mActivitySearchBinding.tvAutoCompSearch.setTextColor(Color.BLACK);
        mActivitySearchBinding.tvAutoCompSearch.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // create Toast with user selected value
        Toast.makeText(SearchActivity.this, "Selected Item is: \t" + item, Toast.LENGTH_LONG).show();

        // set user selected value to the TextView
        mActivitySearchBinding.tvSelectedItem.setText(item);
    }
}
