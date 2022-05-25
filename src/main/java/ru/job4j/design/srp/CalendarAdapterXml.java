package ru.job4j.design.srp;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarAdapterXml extends XmlAdapter<String, Calendar> {
    private static final DateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");

    @Override
    public Calendar unmarshal(String d) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DATE_FORMAT.parse(d));
        return cal;
    }

    @Override
    public String marshal(Calendar d) {
        return DATE_FORMAT.format(d.getTime());
    }
}