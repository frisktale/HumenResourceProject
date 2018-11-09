package com.frisk.hrs.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author frisktale
 * @date 2018/10/8
 */
public class MyDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(source);
        } catch (ParseException e) {
            try {
                return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").parse(source);
            } catch (ParseException e1) {
                try {
                    return new SimpleDateFormat("yyyy-MM-dd").parse(source);
                } catch (ParseException e2) {
                    try {
                        return new SimpleDateFormat("yyyy年MM月").parse(source);
                    } catch (ParseException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
