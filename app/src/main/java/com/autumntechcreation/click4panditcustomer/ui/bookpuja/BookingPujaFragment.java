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

import com.autumntechcreation.click4panditcustomer.MainActivity;
import com.autumntechcreation.click4panditcustomer.R;
import com.autumntechcreation.click4panditcustomer.databinding.FragmentBookingpujaBinding;
import com.autumntechcreation.click4panditcustomer.di.Injectable;
import com.autumntechcreation.click4panditcustomer.loader.DisplayDialog;
import com.autumntechcreation.click4panditcustomer.network.Resource;
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

    ArrayAdapter<String> mSpinLocationAdapter;
    List<String> mListLocation = new ArrayList<>();
    ArrayList<BookingLocationModel> bookingLocationModellist = new ArrayList<BookingLocationModel>();

    ArrayAdapter<String> mSpinLanguageAdapter;
    List<String> mListLanguage = new ArrayList<>();
    ArrayList<BookingLanguageModel> bookingLanguageModellist = new ArrayList<BookingLanguageModel>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBookingpujaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookingpuja, container, false);
        mFragmentBookingpujaBinding.setLifecycleOwner(this);

        return mFragmentBookingpujaBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        navController=findNavController(mView);
        ((MainActivity) getActivity()).setToolbar(false,true,false,true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBookingPujaViewModel = ViewModelProviders.of(BookingPujaFragment.this, viewModelFactory).get(BookingPujaViewModel.class);
        mFragmentBookingpujaBinding.setBookingPujaViewModel(mBookingPujaViewModel);



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
        mBookingPujaViewModel.getOnClickBookPackage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                findNavController(mView).navigate(BookingPujaFragmentDirections.actionBookingPujaFragmentToOrderSummaryFragment());
            }
        });



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
                        mFragmentBookingpujaBinding.tvBookingTime.setText(sHour + ":" + sMinute);
                    }
                }, hour, minutes, true);
        picker.show();
    }



    public void showDatePicker() {

        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int year, int month, int day_of_month) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, (month-1));
                calendar.set(Calendar.DAY_OF_MONTH, day_of_month);
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                mFragmentBookingpujaBinding.tvBookingDate.setText(sdf.format(calendar.getTime()));
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());// TODO: used to hide previous date,month and year
        calendar.add(Calendar.DAY_OF_MONTH, 6);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());// TODO: used to hide future date,month and year
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


