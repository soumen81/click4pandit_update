package com.autumntechcreation.click4panditcustomer.util;

public class AllUrlsAndConfig {
    public static String SIGNUP="/api/NonFnctnlCncrn/SignUp";
    public static String BASE_URL="https://webapi-click4pandit.azurewebsites.net";
    public static String LOGIN="/api/NonFnctnlCncrn/Login";
    public static final String PUJATYPES = "/api/PujaCtgry/GetPujaCategories";
    public static final String PUJACATEGORIES = "/api/PujaSubCtgry/GetPujaSubCtgryByPujaCtgryId";
    public static final String PUJAPACKAGE = "/api/PujaPkg/GetPujaPkgByPujaSubCtgryId";
    public static final String LOCATIONLIST ="/api/Lcn/SrchBySubLcltyMinThreeChars";
    public static final String LANGUAGELIST ="/api/Lang/GetLangMasterList";
    public static final String NEWORDER ="/api/CustomerBkgOrder/NewOrder";


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


}
