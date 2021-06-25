package com.autumntechcreation.click4panditcustomer.ui.shop;

import androidx.lifecycle.ViewModel;

import com.autumntechcreation.click4panditcustomer.ui.orders.OrderRepository;

import javax.inject.Inject;

public class ShopViewModel extends ViewModel {
    ShopRepository mShopRepository;
    @Inject
    public ShopViewModel(ShopRepository shopRepository) {
        this.mShopRepository=shopRepository;
    }
}
