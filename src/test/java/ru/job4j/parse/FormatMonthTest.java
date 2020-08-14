package ru.job4j.parse;

import org.junit.Test;
import java.text.ParseException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FormatMonthTest {
    @Test
    public void setDate() throws ParseException {
        FormatMonth fm = new FormatMonth();
        String date = "17 окт 07, 14:42";
        assertThat(fm.format(date).toString(), is("Wed Oct 17 14:42:00 MSD 2007"));
    }
}