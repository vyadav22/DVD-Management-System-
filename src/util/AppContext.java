/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import dao.DBManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class AppContext {

    public static HashMap configMap = new ReadConfig().getConfigMap() ;
    public static DBManager dao = new DBManager();
    public static final int FORM_TYPE_DVD_ADD = 0 ;
    public static final int FORM_TYPE_DVD_EDIT = 1 ;
    public static final int FORM_TYPE_DVD_VIEW = 2 ;
    public static final int FORM_TYPE_DVD_DELETE = 3 ;
    public static final int FORM_TYPE_DVD_SEARCH = 4 ;
    public static final int FORM_TYPE_DVD_RENT = 5 ;
    public static final int FORM_TYPE_DVD_RETURN = 6 ;

    public static final int FORM_TYPE_USER_ADD = 7 ;
    public static final int FORM_TYPE_USER_EDIT = 8 ;
    public static final int FORM_TYPE_USER_VIEW = 9 ;
    public static final int FORM_TYPE_USER_DELETE = 10 ;

    public static final int USER_TYPE_ADMIN = 0 ;
    public static final int USER_TYPE_CUSTOMER = 1 ;
    public static final String[] USER_TYPE_NAME = {"Admin", "Customer"};
    public static final int DVD_STATUS_AVAILABLE = 0 ;
    public static final int DVD_STATUS_NOT_AVAILABLE = 1 ;

    public static final int CUSTOMER_ID = 21 ;
    public static final int CUSTOMER_NAME = 22 ;
    public static final int DVD_ID = 23 ;
    public static final int DVD_NAME = 24 ;
    public static final int DVD_CATEGORY = 25 ;
    public static final int DVD_DIRECTOR = 26 ;
    public static final int DVD_YEAR = 27 ;
    public static final int DVD_RENT = 28 ;
    public static final int ISSUE_DATE = 29 ;
    public static final int RENTAL_ID = 30 ;
    public static int CURRENT_LOGIN = USER_TYPE_CUSTOMER ; 
    public static final String DATE_FORMAT = "MM-dd-yy HH:mm:ss" ;
    public static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    
    public static void doNothing(){
        // This method is called just to instantiate static members of this class
    }

    public static String[] listToArray(ArrayList<String> lst){
        try {
            String[] strArray = new String[lst.size()];
            for (int i = 0; i < lst.size(); i++) {
                strArray[i] = lst.get(i);
            }
            return strArray;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
   }// end method listToArray
}
