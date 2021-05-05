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
    public static final String FORGETPASSWORD ="/api/NonFnctnlCncrn/ForgotPassword";
    public static final String TRIGGERMAIL ="/api/TriggerMail/SendMailForSetNewPassWord";
    public static final String PROCEEDTOPAY ="/api/CustomerBkgOrder/ProceedToPay";
    public static final String CHANGEPASSWORD ="/api/NonFnctnlCncrn/ChangePassword";


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

}
