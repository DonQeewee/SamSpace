package Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    public static boolean isEmailValid(String e){
        if (!regEx(e)){
            return false;
        }else{
            return true;
        }
    }

    public static boolean regEx(String txt){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(txt);
        return m.matches();
    }
}
