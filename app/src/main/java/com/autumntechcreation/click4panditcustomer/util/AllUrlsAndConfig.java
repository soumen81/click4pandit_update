package com.autumntechcreation.click4panditcustomer.util;

public class AllUrlsAndConfig {
    public static String SIGNUP="/api/NonFnctnlCncrn/SignUp";

    public static String STORE_BASE_URL="https://dev-brnch-webapi-click4pandit.azurewebsites.net";//TEST
    public static String BASE_URL="https://webapi-click4pandit.azurewebsites.net";//TEST
    //public static String BASE_URL="https://prod-webapi-click4pandit.azurewebsites.net";//PRODUCTION
    public static String LOGIN="/api/NonFnctnlCncrn/Login";
    public static final String PUJATYPES = "/api/PujaCtgry/GetPujaCategories";
    public static final String PUJACATEGORIES = "/api/PujaSubCtgry/GetPujaSubCtgryByPujaCtgryId";
    public static final String PUJAPACKAGE = "/api/PujaPkg/GetPujaPkgByPujaSubCtgryId";
    public static final String LOCATIONLIST ="/api/Lcn/SrchBySubLcltyMinThreeChars";
    public static final String LANGUAGELIST ="/api/Lang/GetLangMasterList";
    public static final String NEWORDER ="/api/CustomerBkgOrder/NewOrder";
    public static final String FORGETPASSWORD ="/api/NonFnctnlCncrn/ForgotPassword";
    public static final String TRIGGERMAIL ="/api/TriggerMail/SendMailForSetNewPassWord";
    public static final String PROCEEDTOPAY ="/api/CustomerBkgOrder/ProceedToPay";
    public static final String CHANGEPASSWORD ="/api/NonFnctnlCncrn/ChangePassword";
    public static final String SENDENQUIRY ="/api/TriggerMail/SendEmailForPujaEnquiry";
    public static final String CASFREETOKEN ="/api/PaymentGateway/GetToken";
    public static final String UPDATEINVOICE ="/api/CustomerBkgOrder/UpdInvBkgAndGetInvBkgDetails";
    public static final String SENDCUSTOMERFORCUSTINVOICE="/api/TriggerMail/SendEmailForCustInv";
    public static final String GETPROFILE="/api/Profile/GetCustProfileByLogonId";
    public static final String SAVEPROFILE="/api/Profile/SaveCustProfile";
    public static final String ORDERLIST="/api/OrderSearch/SearchByStandardCriteria";
    public static final String ADDADDRESS="/api/ShippingAddress/SaveShippingAddress";
    public static final String ADDRESSLIST="/api/ShippingAddress/SearchShippingAddress";
   // public static final String SAVEPROFILEIMAGE="/api/Profile/SaveCustProfileImage";
    public static final String SAVEPROFILEIMAGE="/api/Profile/SaveCustProfileImageForAndroid";
    public static final String SENDEMAILCONTACT="/api/TriggerMail/SendEmailForContact";
    public static final String PRODUCTITEMKITLIST="/api/Product/SearchProductByStandardCriteria";
    public static final String SHOPCATEGORYLIST="/api/ProductCommon/GetProdCtgryList";
    public static final String CHECKCARTCOUNT="/api/ShoppingCart/CheckCartItemCount";
    public static final String SHOPPINGCARTITEMLIST="/api/ShoppingCart/GetProdShoppingCartItems";
    public static final String WISHLISTITEM="/api/Wishlist/GetCustWishlistItems";
    public static final String REMOVEWISHLISTITEM="/api/Wishlist/UpdateCustWishlist";
    public static final String ADDTOCARTBUYNOWFORPUJASAMAGRI="/api/ShoppingCart/UpdateProdShoppingCart";
    public static final String REMOVECARTITEMLIST="/api/ShoppingCart/UpdateProdShoppingCart";
    public static final String ADDTOWISHLIST="/api/Wishlist/UpdateCustWishlist";
    public static final String UPDATECARTITEM="/api/ShoppingCart/UpdateProdShoppingCart";
    public static final String PUJASAMAGRIDETAILS="/api/Product/GetProductByProdMasterId";
    public static final String NEWPRODORDER="/api/Checkout/NewProdOrder";
    public static final String DELIVERYADDRESS="/api/ProdOrder/UpdateAddressAndGetProdOrdSummary";




    //Parameter for SignUp
    public static final String FIRSTNAME="FirstName";
    public static final String LASTNAME="LastName";
    public static final String MOBILE="Mobile";
    public static final String EMAILADDRESS="EmailAddr";
    public static final String LOGINIDFORSIGNUP="LogonIdForSignUp";
    public static final String PASSWORDFORSIGNUP="PwdForSignUp";
    public static final String CONFIRMPASSWORDFORSIGNUP="ConfirmPwdSignUp";
    public static final String ENTITYTYPEABRV="EntityTypAbrv";


    //Parameter for Login
    public static final String LOGONID="LogonId";
    public static final String PASSWORD="Pwd";
    public static final String DOREMEMBERME="DoRememberMe";
    public static final String ENTITYTYPEFORLOGIN="EntityTypAbrvforLogin";
    public static final String PUJACATEGORYID="PujaCatgoryId";

    //Parameter for ChoosePackage
    public static final String PUJAPACKAGESUBCATEGORYID="PujaSubCtgryId";
    //Parameter of Location
    public static final String SUBLCITYMINTHREECHARS="SubLcltyMinThreeChars";
    //Parameter for NewOrder
    public static final String BKGSTSID="BkgStsId";
    public static final String CNCDT="CnclDt";
    public static final String CNCFLG="CnclFlg";
    public static final String CURID="CurId";
    public static final String CUSTBKGBKGDT="CustBkgBkgDt";
    public static final String CUSTBKGBKGTIMEONLY="CustBkgBkgTimeOnly";
    public static final String CUSTBKGID="CustBkgId";
    public static final String CUSTBKGLANGMASTERID="CustBkgLangMasterId";
    public static final String CUSTBKGLANGMASTERNAME="CustBkgLangMasterName";
    public static final String CUSTBKGPKGAMT="CustBkgPkgAmt";
    public static final String CUSTBKGPKGTAXAMT="CustBkgPkgTaxAmt";
    public static final String CUSTBKGPKGTOTALAMT="CustBkgPkgTotalAmt";
    public static final String CUSTBKGPUJAPKGALLSAMGRIFLG="CustBkgPujaPkgAllSamagriFlg";
    public static final String CUSTBKGPUJAPKGDESC="CustBkgPujaPkgDescription";
    public static final String CUSTBKGPUJAPKGID="CustBkgPujaPkgId";
    public static final String CUSTBKGPUJAPKGNOOFPANDIT="CustBkgPujaPkgNoOfPandit";
    public static final String CUSTBKGPUJAPKGNOTE="CustBkgPujaPkgNote";
    public static final String CUSTBKGPUJAPKGTYPEDSCR="CustBkgPujaPkgTypDscr";
    public static final String CUSTBKGPUJAPKGTYPEID="CustBkgPujaPkgTypId";
    public static final String CUSTBKGPUJASUBCTGRYDSCR="CustBkgPujaSubCtgryDscr";
    public static final String CUSTBKGPUJASUBCTGRYID="CustBkgPujaSubCtgryId";
    public static final String CUSTBKGSUBLCLTYNAME="CustBkgSubLcltyName";
    public static final String CUSTBKGSUBLCLTYID="CustBkgSublcltyId";
    public static final String CUSTMASTERID="CustMasterId";
    public static final String DELFLG="DelFlg";
    public static final String ISGUESTUSER="IsGuestUser";
    public static final String LOGONIDD="LogonId";
    public static final String ORGLSTAMP="OrglStamp";
    public static final String ORGLUSER="OrglUser";
    public static final String TAXTYPID="TaxTypId";
    public static final String UPDTSTAMP="UpdtStamp";
    public static final String UPDTUSER="UpdtUser";
    public static final String ORDERSRCTYPEID="OrdSrcTypId";

//Parameter for Forgot Password
    public static final String LOGONIDDD="LogonId";
    public static final String ENTITYTYPABRV="EntityTypAbrv";
    public static final String SETNEWPASSWORDLINK="SetNewPasswordLink";

    //Parameter for Billing Address...
    public static final String CUSTPUJABILLINGADDRID="CustPujaBillingAddrId";
    public static final String UPDATESTAMP="UpdtStamp";
    public static final String UPDATEUSER="UpdtUser";
    public static final String ORGLSTAMPP="OrglStamp";
    public static final String ORGLUSERR="OrglUser";
    public static final String DELFLGG="DelFlg";
    public static final String CUSTMASTERIDD="CustMasterId";
    public static final String CUSTBKGIDD="CustBkgId";
    public static final String FIRSTNAMEE="FirstName";
    public static final String LASTNAMEE="LastName";
    public static final String ADDR1="Addr1";
    public static final String ADDR2="Addr2";
    public static final String ADDR3="Addr3";
    public static final String MOB1="Mob1";
    public static final String MOB2="Mob2";
    public static final String EMAILADDR="EmailAddr";
    public static final String CITYID="CtyId";
    public static final String CITYDESCR="CityDescr";
    public static final String STID="StId";
    public static final String STDESCR="StDescr";
    public static final String COUNTRYID="CntryId";
    public static final String POSTAL="Postal";

    public static final String ISGUESTUSERR="IsGuestUser";
    public static final String LOGONI="LogonId";
    public static final String CUSBKGIDD="CustBkgId";
    public static final String ORDERAMOUNT="OrderAmount";
    public static final String ORDERID="OrderId";

    public static final String CUSTPUJAADDINFOID="CustPujaAddlInfoId";
    public static final String UPDATSTAMP="UpdtStamp";
    public static final String UPDATUSER="UpdtUser";
    public static final String ORGLSTAP="OrglStamp";
    public static final String ORIGINALUSER="OrglUser";
    public static final String DELETEFLG="DelFlg";
    public static final String CUSTMASTERIDDDD="CustMasterId";
    public static final String CUSTBKGGID="CustBkgId";
    public static final String FIRSTTNAME="FirstName";
    public static final String LASTTNAME="LastName";
    public static final String MOBBI1="Mob1";
    public static final String MOBBI2="Mob2";
    public static final String EMAIL="EmailAddr";
    public static final String ADDDR1="Addr1";
    public static final String ADDDR2="Addr2";
    public static final String ADDDR3="Addr3";
    public static final String CITYIDD="CtyId";
    public static final String CITYDESCRR="CityDescr";
    public static final String STIDD="StId";
    public static final String STDESCRR="StDescr";
    public static final String COUNTRYIDD="CntryId";
    public static final String POSTALL="Postal";
    public static final String PUJAADDINFODESCR="PujaAddlInfoDscr";

    //Parameter for Change Password
    public static final String LOGONIDDDD="LogonId";
    public static final String OLDPASSWORD ="OldPwd";
    public static final String NEWPASSWORD ="NewPwd";
    public static final String CONFIRMPASSWORD ="ConfirmNewPwd";
    public static final String ENTITYTPEABRV ="EntityTypAbrv";

    //Parameter for Send Enquiry
    public static final String ADDRESS="Address";
    public static final String EMAILADDRR="EmailAddr";
    public static final String MOBILEE="Mobile";
    public static final String NAMEE="Name";
    public static final String PUJANAME="PujaName";
    public static final String REQUIREMENTS="Requirements";

    //Parameter for CashFree Token
    public static final String ORDERCURRENCY="orderCurrency";
    public static final String ORDERIDD="orderId";
    public static final String ORDERAMT="orderAmount";
//Parameter for Update Invoice
    public static final String LOGONIDDDDD="LogonId";
    public static final String ORDERIDDD="OrderId";

    //Parameter for SaveProfile
    public static final String CUSTMASTERIDDD="custMasterId";
    public static final String FIRSTNAMEEE="firstName";
    public static final String LASTNAMEEE="lastName";
    public static final String MOBILENOOOO="mobile";
    public static final String ALTERNATEMOBILE="alternateMobile";
    public static final String EMMAILID="emailId";
    public static final String PHHONENO="phoneNo";
    public static final String FAXNOO="faxNo";

    //Parameter for OrderListing
    public static final String LOGINID="LogonId";
    public static final String ORDERDATECRITERIA="OrderDateCriteria";
    public static final String PAGEINDEX="PageIndex";
    public static final String PAGESIZE="PageSize";

    //Parameter for Address Listing
    public static final String LOGID="LogonId";
    public static final String SHIPPINGADDRID="ShippingAddrId";

    //Parameter for AddAddress..
    public static final String ADDRACTION="addrAction";
    public static final String SHIPADDRID="shippingAddrId";
    public static final String UPDATESTMP="updtStamp";
    public static final String UPDATEUSR="updtUser";
    public static final String ORGLSTMP="orglStamp";
    public static final String ORGLUSR="orglUser";
    public static final String DELFLLG="delFlg";
    public static final String CUSTMASTEID="custMasterId";
    public static final String LOID="logonId";
    public static final String FNME="firstName";
    public static final String LNME="lastName";
    public static final String ADDR11="addr1";
    public static final String ADDR22="addr2";
    public static final String ADD33="addr3";
    public static final String CITID="ctyId";
    public static final String CITDESC="cityDescr";
    public static final String STIDDD="stId";
    public static final String STDESC="stDescr";
    public static final String CNTRYID="cntryId";
    public static final String CNTRYDESC="cntryDescr";
    public static final String POST="postal";
    public static final String ISSDEFAULT="isDefault";
    public static final String SEQQNO="seqNo";
    public static final String DSPORD="dspOrd";


    //Parameter for Save Image Upload
    public static final String CUSTMASTERPROFILEIMGID="custMasterProfImgId";//custMasterProfImgId
    public static final String CUSTMASID="custMasterId";//custMasterId
    public static final String LOGGEDID="logonId";//logonId
    public static final String UPDATESTAM="updtStamp";//updtStamp
    public static final String UPDTEUSER="updtUser";//updtUser
    public static final String ORGSTAMPP="orglStamp";//orglStamp
    public static final String ORGUSERR="orglUser";//orglUser
    public static final String DELLFLG="delFlg";//delFlg
    public static final String CLOUDIMGID="cloudImgId";//cloudImgId
    public static final String ORGLFILENAME="orglFileName";//orglFileName
    public static final String CLOUDFILENAME="cloudFileName";//cloudFileName
    public static final String MIMETYPE="mimeTyp";//mimeTyp
    public static final String IMGACTION="imgAction";//imgAction
    public static final String FILEDATA="fileData";//fileData
    //Parameter for SendForContactUs
    public static final String NAME="Name";
    public static final String MOBBILE="Mobile";
    public static final String EMAILADDRRESS="EmailAddr";
    public static final String ADDRESSS="Address";
    public static final String EXPLAINYOURISSUE="ExplainYourIssue";
    public static final String KEY="key";
    public static final String VALUE="value";
    //Parameter for Product Item Kit List
    public static final String PRODUCTNAME="ProductName";
    public static final String PRODUCTMASTERID="ProdMasterId";
    public static final String DELFFLGG="DelFlg";
    public static final String PAGESSIZE="PageSize";
    public static final String PAGEIINDEX="PageIndex";
    public static final String PRODUCTIONCATEGORYMODELLIST="ProdCtgryModelList";
    public static final String PRODUCTIONTYPEMODELLIST="ProdTypModelList";
    public static final String PRODUCTIONMTRLTYPEMODELLIST="ProdMtrlTypModelList";
    public static final String PRODUCTIONRANGECRITERIAMODELLIST="PriceRangeCriteriaModelList";


    //Parameter for AddtoCartBuyNow
    public static final String ISGUESUSERR="IsGuestUser";
    public static final String LOOGONID="LogonId";
    public static final String PRODCUSTSCID="ProdCustScId";
    public static final String DELLFLGG="DelFlg";
    public static final String PRODDMASTERID="ProdMasterId";
    public static final String PRODDCUSTSCDT="ProdCustScDt";
    public static final String PRODDCUSTSCQTY="ProdCustScQty";
    public static final String PRODDCUSTSCRATE="ProdCustScRate";
    public static final String CURRID="CurId";

    //Parameter for WishList
    public static final String LOOGOONID="LogonId";
    public static final String PRODUCTCUSTWISHLISTID="ProdCustWshlstId";
    public static final String DDELFLG="DelFlg";
    public static final String PRODMASTERID="ProdMasterId";
    public static final String PRODCUSTSCWISHLISTQTY="ProdCustScWshlstQty";
    public static final String PRODCUSTSCWISHLISTRATE="ProdCustScWshlstRate";
    public static final String CURIID="CurId";
    //Parameter for PujaSamagriDetails
    public static final String PROMASTERID="ProdMasterId";
    //Parameter for NewOrder
    public static final String ISSGUESTUSER="IsGuestUser";
    public static final String LOGGGONIDD="LogonId";
    public static final String CUSTORDTYPEID="CustOrdTypId";

    //Parameter for ShopShipping Address
    public static final String ISGUUESTUSER="IsGuestUser";
    public static final String LGGONID="LogonId";
    public static final String PRODORDERID="ProdOrderId";
    public static final String PRODORDERAMOUNT="ProdOrderAmount";
    public static final String TAXAMOUNT="TaxAmount";
    public static final String CCURID="CurId";
    public static final String CUSTORDTYPID="CustOrdTypId";

    public static final String PRODCUSTADDLINFOID="ProdCustAddlInfoId";
    public static final String UPDATESTAMPP="UpdtStamp";
    public static final String UPDATEUSERR="UpdtUser";
    public static final String OORGLSTAMP="OrglStamp";
    public static final String OORGLUSER="OrglUser";
    public static final String DEELFLGG="DelFlg";
    public static final String CCUSTMASTERID="CustMasterId";
    public static final String PRODCUSTORDID="ProdCustOrdId";
    public static final String FNAME="FirstName";
    public static final String LNAME="LastName";
    public static final String MOBB1="Mob1";
    public static final String MOBB2="Mob2";
    public static final String EMAILADDRES="EmailAddr";
    public static final String ADDRR1="Addr1";
    public static final String ADDRR2="Addr2";
    public static final String ADDRR3="Addr3";
    public static final String CCCITYID="CtyId";
    public static final String CCITYDESCR="CityDescr";
    public static final String SSTID="StId";
    public static final String SSTDESCR="StDescr";
    public static final String CCNTRYID="CntryId";
    public static final String CCNTRYDESCR="CntryDescr";
    public static final String PPOSTAl="Postal";
    public static final String SHIPPINGADDLINFODESCR="ShippingAddlInfoDscr";
    public static final String SEQNO="SeqNo";
    public static final String DSPPORDD="DspOrd";

    public static final String PRODCUSBILLINGADDRID="ProdCustBillingAddrId";
    public static final String BILLINGADDRUPDTSTAMP="BillingAddrUpdtStamp";
    public static final String BILLINGADDRUPDTUSER="BillingAddrUpdtUser";
    public static final String BILLINGADDRORGLSTAMP="BillingAddrOrglStamp";
    public static final String BILLINGADDRORGLUSER="BillingAddrOrglUser";
    public static final String BILLINGADDRDELFLG="BillingAddrDelFlg";
    public static final String BILLINGADDRCUSTMASTERID="BillingAddrCustMasterId";
    public static final String BILLINGADDRPRODCUSTORDID="BillingAddrProdCustOrdId";
    public static final String BILLINGADDRFIRSTNAME="BillingAddrFirstName";
    public static final String BILLINGADDRLASTNAME="BillingAddrLastName";
    public static final String BILLINGADDRMOB1="BillingAddrMob1";
    public static final String BILLINGADDRMOB2="BillingAddrMob2";
    public static final String BILLINGADDREMAILADDR="BillingAddrEmailAddr";
    public static final String BILLINGADDRADDR1="BillingAddrAddr1";
    public static final String BILLINGADDRADDR2="BillingAddrAddr2";
    public static final String BILLINGADDRADDR3="BillingAddrAddr3";
    public static final String BILLINGADDRCITYID="BillingAddrCtyId";
    public static final String BILLINGADDRCITYDESCR="BillingAddrCityDescr";
    public static final String BILLINGADDRSTID="BillingAddrStId";
    public static final String BILLINGADDRSTDESCR="BillingAddrStDescr";
    public static final String BILLINGADDRCNTRYID="BillingAddrCntryId";
    public static final String BILLINGADDRCNTRYDESCR="BillingAddrCntryDescr";
    public static final String BILLINGADDRPOSTAL="BillingAddrPostal";
    public static final String BILLINGADDRSEQNO="BillingAddrSeqNo";
    public static final String BILLINGADDRDSPORD="BillingAddrDspOrd";




}
