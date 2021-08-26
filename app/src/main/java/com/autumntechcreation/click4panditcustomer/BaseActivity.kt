package com.autumntechcreation.click4panditcustomer

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.autumntechcreation.click4panditcustomer.di.Injectable
import com.autumntechcreation.click4panditcustomer.util.Constants
import com.autumntechcreation.click4panditcustomer.util.FragmentAnimation
import com.autumntechcreation.click4panditcustomer.util.Utils
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener, Injectable {

    /**
     * Contains last clicked time
     */
    private var lastClickedTime: Long = 0


    /**
     * Gets the fragment manager object of activity required for fragment transaction
     *
     * This method can be customised on the need of application,in which it returns [FragmentManager]
     *
     * @return object of [android.app.FragmentManager] or [FragmentManager]
     */
    val localFragmentManager: FragmentManager
        get() = this.supportFragmentManager


    // Toast.makeText(FarvisionSalesForceBaseActivity.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
//    val isDeviceOnline: Boolean
//        get() {
//            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            val networkInfo = cm.activeNetworkInfo
//            if (networkInfo != null && networkInfo.isConnected) {
//                return true
//            } else {
//                exceptionDialog(getString(R.string.sorry_you_not_online_msg))
//                return false
//            }
//
//        }

    fun isDeviceOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            return true
        } else {
            exceptionDialog(getString(R.string.sorry_you_not_online_msg))
            return false
        }
    }

    fun getLocalFragmentManagerQc(): FragmentManager {
        return this.supportFragmentManager
    }


    // Toast.makeText(FarvisionSalesForceBaseActivity.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
    // exceptionDialog(getString(R.string.sorry_you_not_online_msg));
    val isCheckDeviceOnline: Boolean
        get() {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return if (networkInfo != null && networkInfo.isConnected) {
                true
            } else {
                false
            }

        }

    /**
     * Returns the resource id of the layout which will be used for setContentView() for the Activity
     *
     * @return resource id of the xml layout
     */
    protected abstract fun defineLayoutResource(): Int

    /**
     * Initialize the components on activity create
     */
    protected abstract fun initializeComponents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState ?: Bundle())

        setContentView(defineLayoutResource())

        AndroidInjection.inject(this)

        initializeComponents()
    }

    /**
     * Adds the Fragment into layout container
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param currentFragment             Current loaded Fragment to be hide
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param requiredAnimation           true if screen transition animation is required
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(IllegalStateException::class)
    fun addFragment(
        fragmentContainerResourceId: Int,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        requiredAnimation: Boolean,
        commitAllowingStateLoss: Boolean
    ): Boolean {
        if (currentFragment == null || nextFragment == null) {
            return false
        }
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (requiredAnimation) {
            FragmentAnimation.setDefaultFragmentAnimation(fragmentTransaction)
        }

        fragmentTransaction.add(
            fragmentContainerResourceId,
            nextFragment,
            nextFragment.javaClass.simpleName
        )
        //fragmentTransaction.addToBackStack(nextFragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(currentFragment.javaClass.simpleName)

        val parentFragment = currentFragment.parentFragment
        fragmentTransaction.hide(parentFragment ?: currentFragment)

        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitAllowingStateLoss()
        }

        return true
    }

    /**
     * Replaces the Fragment into layout container
     *
     * @param fragmentContainerResourceId Resource id of the layout in which Fragment will be added
     * @param fragmentManager             FRAGMENT MANGER
     * @param nextFragment                New Fragment to be loaded into fragmentContainerResourceId
     * @param requiredAnimation           true if screen transition animation is required
     * @param commitAllowingStateLoss     true if commitAllowingStateLoss is needed
     * @return true if new Fragment added successfully into container, false otherwise
     * @throws IllegalStateException Exception if Fragment transaction is invalid
     */
    @Throws(IllegalStateException::class)
    fun replaceFragment(
        fragmentContainerResourceId: Int,
        fragmentManager: FragmentManager?,
        nextFragment: Fragment?,
        requiredAnimation: Boolean,
        commitAllowingStateLoss: Boolean
    ): Boolean {
        if (nextFragment == null || fragmentManager == null) {
            return false
        }
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (requiredAnimation) {
            FragmentAnimation.setDefaultFragmentAnimation(fragmentTransaction)
        }
        fragmentTransaction.replace(
            fragmentContainerResourceId,
            nextFragment,
            nextFragment.javaClass.simpleName
        )

        if (!commitAllowingStateLoss) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitAllowingStateLoss()
        }

        return true
    }

    fun exceptionDialog(msg: String) {
        //        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
        //                BaseActivity.this);
        //        alertDialogBuilder.setTitle("FarvisionSalesForce");
        //        alertDialogBuilder
        //                .setMessage("" + msg)
        //                .setCancelable(false)
        //                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        //                    public void onClick(DialogInterface dialog, int id) {
        //                        dialog.dismiss();
        //                        //openSettings();
        //                    }
        //                });
        //        AlertDialog alertDialog = alertDialogBuilder.create();
        //        alertDialog.show();

        Utils.showSnackBarInternet(this@BaseActivity, window.decorView.rootView, msg)

    }

    fun onSuccessActivity() {
        val handler = Handler()
        handler.postDelayed({
            finish()
            //Do something after 100ms
        }, 1000)
    }


    override fun onClick(v: View) {
        Utils.hideSoftKeyBoard(this@BaseActivity, v)
        /*
         * Logic to Prevent the Launch of the Fragment Twice if User makes
         * the Tap(Click) very Fast.
         */
        if (SystemClock.elapsedRealtime() - lastClickedTime < Constants.MAX_CLICK_INTERVAL) {

            return
        }
        lastClickedTime = SystemClock.elapsedRealtime()

        when (v.id) {

        }
    }
}





