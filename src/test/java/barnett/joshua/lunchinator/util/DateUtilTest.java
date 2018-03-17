package barnett.joshua.lunchinator.util;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    SimpleDateFormat localDateFormat;

    @Before
    public void setUp() throws Exception {
        localDateFormat = new SimpleDateFormat("HH:mm");
    }

    @Test
    public void testGetDefaultDateTime() {
        Date testDate = DateUtil.getDefaultDateTime();
        assertEquals("11:45", localDateFormat.format(testDate));
    }
}