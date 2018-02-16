package com.cash.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
@ConfigurationPropertiesBinding
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        if(StringUtils.isEmpty(source) || StringUtils.isBlank(source)){
            return null;
        }
        Date converted = null;
        try {
            System.out.println("**************StringToDateConverter****************************");
            System.out.println(source);
            System.out.println("**************StringToDateConverter****************************");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            converted = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return converted;
    }

}