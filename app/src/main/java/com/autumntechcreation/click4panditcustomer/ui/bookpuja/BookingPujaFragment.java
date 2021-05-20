package com.autumntechcreation.click4panditcustomer.ui.bookpuja;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentBookingpujaBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageFragmentArgs;
import com.autumntechcreation.click4panditcustomer.ui.home.HomeFragment;
import com.autumntechcreation.click4panditcustomer.ui.home.PujaTypesModel;
import com.autumntechcreation.click4panditcustomer.util.Static;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;


import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static androidx.navigation.Navigation.findNavController;

public class BookingPujaFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    FragmentBookingpujaBinding mFragmentBookingpujaBinding;
    BookingPujaViewModel mBookingPujaViewModel;
    private int[]mImager={R.drawable.pandit1,R.drawable.pandit2,R.drawable.pandit3,R.drawable.pandit4,R.drawable.pandit5};
    private String[]mImagetitle=new String[]{"Pandit1,Pandit2,Pandit3,Pandit4,Pandit5"};
    private View mView;
    NavController navController;
    String procedure,pujaName,pujaAmount,pujaDesc,pujaSamagri,yajaman;
    ArrayAdapter<String> mSpinLocationAdapter;
    List<String> mListLocation = new ArrayList<>();
    ArrayList<BookingLocationModel> bookingLocationModellist = new ArrayList<BookingLocationModel>();

    ArrayAdapter<String> mSpinLanguageAdapter;
    List<String> mListLanguage = new ArrayList<>();
    ArrayList<BookingLanguageModel> bookingLanguageModellist = new ArrayList<BookingLanguageModel>();
    String locName="",pujaLang="",pujaDate="",pujaTime="",packageTypeIdDesc="",
            subcategoryName="",isAllSamagries="";
    int subCategoryId,pujPackageId,locationId,languageId,noOfPandit;
    SimpleDateFormat sdf;
     Calendar calendar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBookingpujaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookingpuja, container, false);
        mFragmentBookingpujaBinding.setLifecycleOwner(this);
        procedure= BookingPujaFragmentArgs.fromBundle(getArguments()).getProcedure();
        Log.e("Procedure",""+procedure);
        pujaName= BookingPujaFragmentArgs.fromBundle(getArguments()).getPujaName();
        Log.e("PujaName",""+pujaName);
        pujaAmount= BookingPujaFragmentArgs.fromBundle(getArguments()).getAmount();
        Log.e("PujaAmount",""+pujaAmount);
        pujaDesc= BookingPujaFragmentArgs.fromBundle(getArguments()).getPackageDesc();
        Log.e("PujaDesc",""+pujaDesc);
        pujaSamagri= BookingPujaFragmentArgs.fromBundle(getArguments()).getPujaSamagries();
        Log.e("PujaSamagries",""+pujaSamagri);
        yajaman= BookingPujaFragmentArgs.fromBundle(getArguments()).getYajaman();
        Log.e("Yajaman",""+yajaman);
        packageTypeIdDesc= BookingPujaFragmentArgs.fromBundle(getArguments()).getPackageTypeIdDesc();
        Log.e("packageTypeIdDesc",""+packageTypeIdDesc);
        subcategoryName= BookingPujaFragmentArgs.fromBundle(getArguments()).getSubCategoryName();
        Log.e("subcategoryName",""+subcategoryName);
        subCategoryId=BookingPujaFragmentArgs.fromBundle(getArguments()).getSubCategoryId();
        Log.e("SubCategoryId",""+subCategoryId);
        pujPackageId=BookingPujaFragmentArgs.fromBundle(getArguments()).getPujaPackageId();
        Log.e("pujPackageId",""+pujPackageId);
        isAllSamagries=BookingPujaFragmentArgs.fromBundle(getArguments()).getIsAllSamagries();
        Log.e("isAllSamagries",""+isAllSamagries);
        noOfPandit=BookingPujaFragmentArgs.fromBundle(getArguments()).getNoOfPandit();
        Log.e("noOfPandit",""+noOfPandit);



        return mFragmentBookingpujaBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        navController=findNavController(mView);
        try {
            ((MainActivity) getActivity()).setToolbar(false, true, false, true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBookingPujaViewModel = ViewModelProviders.of(BookingPujaFragment.this, viewModelFactory).get(BookingPujaViewModel.class);
        mFragmentBookingpujaBinding.setBookingPujaViewModel(mBookingPujaViewModel);



/*
        mFragmentBookingpujaBinding.carousal.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImager[position]);
            }
        });

        mFragmentBookingpujaBinding.carousal.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(),mImagetitle[position],Toast.LENGTH_SHORT).show();
            }
        });
        mFragmentBookingpujaBinding.carousal.setPageCount(mImager.length);

*/


        mBookingPujaViewModel.getBookingLocationList().observe(getActivity(), BookingPujaFragment.this::handleBookingLocationList);

        mSpinLocationAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, mListLocation);
        // Specify the layout to use when the list of choices appears
        mSpinLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mFragmentBookingpujaBinding.tvSpinTypeOfLocation.setAdapter(mSpinLocationAdapter);

        //For Language Spinner
        mBookingPujaViewModel.getBookingLanguageList().observe(getActivity(),BookingPujaFragment.this::handleBookingLanguage);

        mSpinLanguageAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, mListLanguage);
        // Specify the layout to use when the list of choices appears
        mSpinLanguageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mFragmentBookingpujaBinding.tvSpinTypeOfLanguage.setAdapter(mSpinLanguageAdapter);


        mFragmentBookingpujaBinding.clBookDate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                showDatePicker();

            }
        });
        mFragmentBookingpujaBinding.clBookTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });



        mFragmentBookingpujaBinding.tvSpinTypeOfLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    locationId=mBookingPujaViewModel.mBookingLocationList.getValue().data.get(position).getSubLcltyId();
                    locName = mBookingPujaViewModel.mBookingLocationList.getValue().data.get(position).getSubLcltyName();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mFragmentBookingpujaBinding.tvSpinTypeOfLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    locationId=mBookingPujaViewModel.mBookingLanguageList.getValue().data.get(position).getLangMasterId();
                    pujaLang = mBookingPujaViewModel.mBookingLanguageList.getValue().data.get(position).getLangMasterName();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mBookingPujaViewModel.getOnClickBookPackage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {

                if (mFragmentBookingpujaBinding.tvSpinTypeOfLocation.getSelectedItem().toString().trim().equalsIgnoreCase("Location")) {
                    Toast.makeText(getActivity(), "Please select the Location", Toast.LENGTH_SHORT).show();
                }else if(mFragmentBookingpujaBinding.tvSpinTypeOfLanguage.getSelectedItem().toString().trim().equalsIgnoreCase("Language")){
                    Toast.makeText(getActivity(), "Please select the Language", Toast.LENGTH_SHORT).show();
                }else  if(mFragmentBookingpujaBinding.tvBookingDate.getText().equals("Choose Date")) {
                    Toast.makeText(getActivity(), "Please select the Date", Toast.LENGTH_SHORT).show();
                }else  if(mFragmentBookingpujaBinding.tvBookingTime.getText().equals("Choose Time")) {
                    Toast.makeText(getActivity(), "Please select the Time", Toast.LENGTH_SHORT).show();
                }else {

                    BookingPujaFragmentDirections.ActionBookingPujaFragmentToOrderSummaryFragment action =
                            BookingPujaFragmentDirections.actionBookingPujaFragmentToOrderSummaryFragment();
                    action.setPujaName(pujaName);
                    action.setAmount(pujaAmount);
                    action.setPackageDesc(pujaDesc);
                    action.setProcedure(procedure);
                    action.setPujaSamagries(pujaSamagri);
                    action.setYajaman(yajaman);
                    action.setPujaLocation(locName);
                    action.setPujaLanguage(pujaLang);
                    action.setPujaDate(pujaDate);
                    action.setPujaTime(pujaTime);
                    action.setPackageTypeIdDesc(packageTypeIdDesc);
                    action.setSubCategoryName(subcategoryName);
                    action.setSubCategoryId(subCategoryId);
                    action.setPujaPackageId(pujPackageId);
                    action.setLocationId(locationId);
                    action.setLanguageId(languageId);
                    action.setIsAllSamagries(isAllSamagries);
                    action.setNoOfPandit(noOfPandit);
                    Navigation.findNavController(mView).navigate(action);
                }
            }
        });

    }

    public void showTimePicker(){
        final Calendar cldr = Calendar.getInstance();
        TimePickerDialog picker;

        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        picker = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {


                        int hour = sHour % 12;
                        if (hour == 0)
                            hour = 12;
                        mFragmentBookingpujaBinding.tvBookingTime.setText(String.format("%02d:%02d %s", hour, sMinute, sHour < 12 ? "am" : "pm"));
                        //mFragmentBookingpujaBinding.tvBookingTime.setText(sHour + ":" + sMinute);
                       // pujaTime=sHour + ":" + sMinute;
                        pujaTime=String.format("%02d:%02d", hour, sMinute);
                    }
                }, hour, minutes, true);
        picker.show();
    }



    public void showDatePicker() {

        calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int year, int month, int day_of_month) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, (month));
                calendar.set(Calendar.DAY_OF_MONTH, day_of_month);
                String myFormat = "dd/MM/yyyy";
                sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                mFragmentBookingpujaBinding.tvBookingDate.setText(sdf.format(calendar.getTime()));
                pujaDate=sdf.format(calendar.getTime());
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)-1);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());// TODO: used to hide previous date,month and year
        //calendar.add(Calendar.DAY_OF_MONTH, 3);

        //dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        dialog.show();


    }




    private void handleBookingLocationList(Resource<List<BookingLocationModel>> resource) {
                        if (resource != null) {
                            JSONObject jsonObject = null;
                            switch (resource.status) {
                                case ERROR:
                                    DisplayDialog.getInstance().dismissAlertDialog();
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Error")
                                            .setContentText("Something went wrong")
                                            .show();
                                    break;
                                case LOADING:


                                    break;
                                case SUCCESS:

                                    Log.e("handleBookingLocStatus", "SUCCESS");
                                    Log.e("handleBookingLocStatus", resource.data + "");
                                    Log.e("handleBookingLocStatus", resource.message + "");
                                    Log.e("handleBookingLocStatus", resource.status + "");


                                    if (resource.data != null) {

                                        Log.e("handleBookingLocStatus_count", resource.data.size() + "");
                                        if (resource.data.size() == 0) {
                                            DisplayDialog.getInstance().dismissAlertDialog();
                                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                                    .setTitleText("Error")
                                                    .setContentText("Something went wrong")
                                                    .show();
                                        } else {

                                            bookingLocationModellist.clear();
                                            mListLocation.clear();
                                            mListLocation.add(0,"Location");
                                            for (int i = 0; i < resource.data.size(); i++) {
                                                mListLocation.add(resource.data.get(i).getSubLcltyName());
                                                bookingLocationModellist.add(resource.data.get(i));

                                            }
                                            Log.e("LISSSST", mListLocation.size() + "");
                                            mSpinLocationAdapter.notifyDataSetChanged();
                                            DisplayDialog.getInstance().dismissAlertDialog();


                                        }

                                    }

                                    // mModuleDetailsViewModel.setUserWiseWidgetList(resource.data);


                                    break;
                                default:

                                    DisplayDialog.getInstance().dismissAlertDialog();

                                    break;
                            }
                        }
                    }


                    private void handleBookingLanguage(Resource<List<BookingLanguageModel>> resource) {
                        if (resource != null) {
                            JSONObject jsonObject = null;
                            switch (resource.status) {
                                case ERROR:
                                    DisplayDialog.getInstance().dismissAlertDialog();
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Error")
                                            .setContentText("Something went wrong")
                                            .show();
                                    break;
                                case LOADING:


                                    break;
                                case SUCCESS:

                                    Log.e("handleGetLeaveStatus", "SUCCESS");
                                    Log.e("handleGetLeaveStatus", resource.data + "");
                                    Log.e("handleGetLeaveStatus", resource.message + "");
                                    Log.e("handleGetLeaveStatus", resource.status + "");


                                    if (resource.data != null) {

                                        Log.e("handleGetLeaveStatus_count", resource.data.size() + "");
                                        if (resource.data.size() == 0) {
                                            DisplayDialog.getInstance().dismissAlertDialog();
                                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                                    .setTitleText("Error")
                                                    .setContentText("Something went wrong")
                                                    .show();
                                        } else {

                                            bookingLanguageModellist.clear();
                                            mListLanguage.clear();
                                            mListLanguage.add(0,"Language");
                                            for (int i = 0; i < resource.data.size(); i++) {
                                                mListLanguage.add(resource.data.get(i).getLangMasterName());
                                                bookingLanguageModellist.add(resource.data.get(i));

                                            }


                                            Log.e("LISSSST", mListLanguage.size() + "");
                                            mSpinLanguageAdapter.notifyDataSetChanged();
                                            DisplayDialog.getInstance().dismissAlertDialog();
                                        }
                                    }

                                    // mModuleDetailsViewModel.setUserWiseWidgetList(resource.data);


                                    break;
                                default:

                                    DisplayDialog.getInstance().dismissAlertDialog();

                                    break;
                            }
                        }
                    }
                }


