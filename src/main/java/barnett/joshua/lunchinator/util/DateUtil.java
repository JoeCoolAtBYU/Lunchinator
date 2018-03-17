package barnett.joshua.lunchinator.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date getDefaultDateTime(){
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.AM_PM, Calendar.AM);
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 45);


        return cal.getTime();
    }
}
