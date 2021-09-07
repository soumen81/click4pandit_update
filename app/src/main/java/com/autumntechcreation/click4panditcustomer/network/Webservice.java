package com.autumntechcreation.click4panditcustomer.network;

import androidx.lifecycle.LiveData;

import com.autumntechcreation.click4panditcustomer.ui.address.AddressListModel;
import com.autumntechcreation.click4panditcustomer.ui.addupdateremoveaddress.AddAddressModel;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.CashFreeTokenModel;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.ProceedtoPayModel;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.SendEmailForCustInvoiceModel;
import com.autumntechcreation.click4panditcustomer.ui.billingdetails.UpdatedInvoicesModel;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingLanguageModel;
import com.autumntechcreation.click4panditcustomer.ui.bookpuja.BookingLocationModel;
import com.autumntechcreation.click4panditcustomer.ui.changepassword.ChangePasswordModel;
import com.autumntechcreation.click4panditcustomer.ui.choosepackage.ChoosePackageListModel;
import com.autumntechcreation.click4panditcustomer.ui.contactus.ContactUsModel;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.ForgetPasswordModel;
import com.autumntechcreation.click4panditcustomer.ui.forgetpassword.TriggerMailModel;
import com.autumntechcreation.click4panditcustomer.ui.home.CartItemCountModel;
import com.autumntechcreation.click4panditcustomer.ui.home.PujaCategoryModel;
import com.autumntechcreation.click4panditcustomer.ui.home.PujaTypesModel;
import com.autumntechcreation.click4panditcustomer.ui.login.LoginResponse;
import com.autumntechcreation.click4panditcustomer.ui.newaddtocartlist.NewAddtoCartListModel;
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.AddWishListItemModel;
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitAddtoCartOrBuyNowModel;
import com.autumntechcreation.click4panditcustomer.ui.newpujaitemkit.NewPujaItemKitListModel;
import com.autumntechcreation.click4panditcustomer.ui.newwishlist.DeleteWishListModel;
import com.autumntechcreation.click4panditcustomer.ui.newwishlist.NewWishListItemModel;
import com.autumntechcreation.click4panditcustomer.ui.orders.OrderListModel;
import com.autumntechcreation.click4panditcustomer.ui.ordersummary.OrderSummeryModel;
import com.autumntechcreation.click4panditcustomer.ui.profile.CustomerGetProfileModel;
import com.autumntechcreation.click4panditcustomer.ui.profile.SaveCustomerprofileModel;
import com.autumntechcreation.click4panditcustomer.ui.register.RegisterResponse;
import com.autumntechcreation.click4panditcustomer.ui.sendenquiry.SendEnquiryModel;
import com.autumntechcreation.click4panditcustomer.ui.shopcategory.ShopCategoryModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Webservice {

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<RegisterResponse>> Register(@Url String apiname,
                                                     @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<LoginResponse>> Login(@Url String apiname,
                                               @Body JsonObject jsonObject

    );



    @GET()
    LiveData<ApiResponse<List<PujaTypesModel>>> getPujaTypesList(
            @Url String apiname

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<PujaCategoryModel>>> getPujaCategoryList(@Url String apiname,
                                                                 @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<ChoosePackageListModel>>> getPujaPackageList(@Url String apiname,
                                                                           @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<BookingLocationModel>>> getLocationList(@Url String apiname,
                                                                      @Body JsonObject jsonObject

    );

    @GET()
    LiveData<ApiResponse<List<BookingLanguageModel>>> getLanguageList(@Url String apiname

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<OrderSummeryModel>> NewOrder(@Url String apiname,
                                                   @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<ForgetPasswordModel>> ForgetPassword(@Url String apiname,
                                                              @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<TriggerMailModel>> TriggerMail(@Url String apiname,
                                                           @Body JsonObject jsonObject

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<ProceedtoPayModel>> ProceedtoPay(@Url String apiname,
                                                         @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<ChangePasswordModel>> ChangePassword(@Url String apiname,
                                                              @Body JsonObject jsonObject

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<SendEnquiryModel>> sendEnquiry(@Url String apiname,
                                                     @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<CashFreeTokenModel>> cashFreeToken(@Url String apiname,
                                                          @Body JsonObject jsonObject

    );

     @Headers({"Content-Type:application/json"})
     @POST()
    LiveData<ApiResponse<UpdatedInvoicesModel>> updateInvoice(@Url String apiname,
                                                              @Body JsonObject jsonObject

    );
     @Headers({"Content-Type:application/json"})
     @POST()
    LiveData<ApiResponse<SendEmailForCustInvoiceModel>> sendEmailForInvoice(@Url String apiname,
                                                                      @Body JsonObject jsonObject

    );

     @Headers({"Content-Type:application/json"})
     @POST()
    LiveData<ApiResponse<CustomerGetProfileModel>> customerGetProfile(@Url String apiname,
                                                                       @Body JsonObject jsonObject

    );

     @Headers({"Content-Type:application/json"})
     @POST()
    LiveData<ApiResponse<SaveCustomerprofileModel>> customerSaveProfile(@Url String apiname,
                                                                       @Body JsonObject jsonObject

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<OrderListModel>>> getOrderList(@Url String apiname,
                                                                    @Body JsonObject jsonObject

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<AddressListModel>>> getAddressList(@Url String apiname,
                                                               @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<AddAddressModel>> customerAddAddress(@Url String apiname,
                                                               @Body JsonObject jsonObject

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<CustomerGetProfileModel>> customerAddProfileImage(@Url String apiname,
                                                                               @Body JsonObject jsonObject



    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<ContactUsModel>> SendEmailForContactUs(@Url String apiname,
                                                                @Body JsonObject jsonObject

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<NewPujaItemKitListModel>>> getPujaPujaItemKitList(@Url String apiname,
                                                                       @Body JsonObject jsonObject

    );

    @GET()
    LiveData<ApiResponse<List<ShopCategoryModel>>> getShopCategoryList(@Url String apiname

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<CartItemCountModel>> getCartItemCount(@Url String apiname,
                                                                           @Body JsonObject jsonObject

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<NewAddtoCartListModel>>> getNewAddtoCartList(@Url String apiname,
                                                                              @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<NewWishListItemModel>>> getNewWishList(@Url String apiname,
                                                                     @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<List<NewWishListItemModel>>> getAddtoCartBuyNow(@Url String apiname,
                                                                     @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<NewPujaItemKitAddtoCartOrBuyNowModel>> getAddtoCartBuyNowForPujaSamagri(@Url String apiname,
                                                                                                 @Body JsonObject jsonObject

    );

    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<DeleteWishListModel>> getDeleteWishListItem(@Url String apiname,
                                                                     @Body JsonObject jsonObject

    );
    @Headers({"Content-Type:application/json"})
    @POST()
    LiveData<ApiResponse<AddWishListItemModel>> getAddWishListItem(@Url String apiname,
                                                                   @Body JsonObject jsonObject

    );
}
