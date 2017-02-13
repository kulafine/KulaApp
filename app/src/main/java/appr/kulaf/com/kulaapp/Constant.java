package appr.kulaf.com.kulaapp;

/**
 * Created by DAVID on 2/6/2017.
 */

public class Constant{
    public static String BaseURL = "http://192.168.40.213:81/kulapp/index.php";
    public static String LoginOP = "login";
    public static String registerOp = "register";
    public static String SuccessM = "success";
    public static String FaillureM = "failure";
    public static String Result;
    public static String Response = "result";

    public static String getFaillureM() {
        return FaillureM;
    }

    public String getBaseURL() {
        return BaseURL;
    }

    public String getLoginOP() {
        return LoginOP;
    }

    public String getRegisterOp() {
        return registerOp;
    }

    public String getSuccessM() {
        return SuccessM;
    }
}
