package ru.job4j.design.srp;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarAdapterJson implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");

    @Override
    public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(DATE_FORMAT.format(src.getTime()));
    }

    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(DATE_FORMAT.parse(json.getAsJsonPrimitive().getAsString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
}