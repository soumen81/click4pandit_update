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


}
