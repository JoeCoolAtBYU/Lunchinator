package barnett.joshua.lunchinator.util;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    private SimpleDateFormat localDateFormat;

    @Before
    public void setUp() throws Exception {
        this.localDateFormat = new SimpleDateFormat("HH:mm");
    }

    @Test
    public void testGetDefaultDateTime() {
        Date testDate = DateUtil.getDefaultDateTime();
        assertEquals("11:45", this.localDateFormat.format(testDate));
    }
}