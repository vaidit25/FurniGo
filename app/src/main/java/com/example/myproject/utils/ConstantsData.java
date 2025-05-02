package com.example.myproject.utils;

public class ConstantsData {


    //APIS
    public static final String SERVER_ADDRESS="http://192.168.14.84:8000";
    public static final String SERVER_ADDRESS_IMG = SERVER_ADDRESS + "/images/";
    public static final String REGISTER_METHOD="/api_register";
    public static final String LOGIN_METHOD=SERVER_ADDRESS+"/api_login";
    public static final String DATA_METHOD=SERVER_ADDRESS+"/api_data";
    public static final String ADD_ORDER_METHOD=SERVER_ADDRESS+"/api_addorder";
    public static final String GET_ORDER_METHOD=SERVER_ADDRESS+"/api_getorder";
    public static final String UPDATE_QTY_METHOD=SERVER_ADDRESS+"/api_updateqty";
    public static final String DELETE_ORDER_METHOD=SERVER_ADDRESS+"/api_removeorder";
    public static final String CONFIRM=SERVER_ADDRESS+"/api_confirmorder";
    public static final String APPLY_COUPON_METHOD=SERVER_ADDRESS+"/api_applycoupon";
    public static final String ORDER_HISTORY_METHOD=SERVER_ADDRESS+"/api_getOrderhistory";



    //OTP
    public static final String CUSTOMER_ID="C-D80DE89B817E4F7";
    public static final String AUTH_TOKEN="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDLUQ4MERFODlCODE3RTRGNyIsImlhdCI6MTc0MjgwOTEwMSwiZXhwIjoxOTAwNDg5MTAxfQ.qHn2sx71Wi4wA6wEmfx6ii1g54s5ejk95Cl_V5vC7wR_HpqD9RHHnu8rjPXAhpvkwaX2fjrBK0MbzJHkqcyTiA";

   //Shared Preferences
    public static final String SP_NAME = "shared_pref";
    public static final String SP_EMAIL = "email";
    public static final String SP_USERID = "userid";
    public static final String SP_USERNAME = "uname";
    public static final String SP_MOBNO = "mobno";
    public static final String SP_IS_LOGIN = "is_login";
    public  static  final String  KEY_PIC="key_pic";



}
