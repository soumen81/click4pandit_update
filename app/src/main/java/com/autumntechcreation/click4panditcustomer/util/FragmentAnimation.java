package com.autumntechcreation.click4panditcustomer.util;

import androidx.fragment.app.FragmentTransaction;

import com.autumntechcreation.click4panditcustomer.R;

public class FragmentAnimation {
    /**
     * Sets default Animation to {@link FragmentTransaction}
     *
     * @param transaction FragmentTransaction on which animation needs to be set
     */

    public static void setDefaultFragmentAnimation(final FragmentTransaction transaction) {
        if (transaction != null) {
            // transaction.setTransition(FragmentTransaction.TRANSIT_NONE);

            transaction.setCustomAnimations(R.anim.anim_right_in, R.anim.anim_left_out, R.anim.anim_left_in, R.anim.anim_right_out);
        }
    }
}
