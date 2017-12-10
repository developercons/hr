package com.recruiting.converter;

import com.recruiting.utils.StringUtils;
import org.springframework.binding.convert.converters.StringToObject;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marta Ginosyan
 */

public class StringToLocalDateTimeConverter extends StringToObject implements GenericConverter {

    private DateTimeFormatter formatter;

    public StringToLocalDateTimeConverter() {
        super(LocalDateTime.class);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Override
    protected Object toObject(String string, Class targetClass) throws ParseException {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(string, formatter);
            return localDateTime;
        } catch (Exception e) {
            return LocalDateTime.of(LocalDate.of(9999, 12, 30), LocalTime.now());
        }
    }

    @Override
    protected String toString(Object object) {
        LocalDateTime localDateTime = (LocalDateTime) object;
        return formatter.format(localDateTime);
    }


    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> convertiblePairs = new HashSet<>();
        convertiblePairs.add(new ConvertiblePair(String.class, LocalDateTime.class));
        convertiblePairs.add(new ConvertiblePair(LocalDateTime.class, String.class));
        return convertiblePairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.getObjectType().equals(String.class) && !StringUtils.isNullOrEmpty((String) source)) {
            try{
                return LocalDate.parse((String) source, DateTimeFormatter.ofPattern("yyyy-MMMM-dd")).atStartOfDay();
            } catch (DateTimeParseException e){
                e.printStackTrace();
                return LocalDate.parse((String) source, DateTimeFormatter.ofPattern("yyyy-MMMM-dd")).atStartOfDay();
            }
        } else if (source != null && sourceType.getObjectType().equals(LocalDateTime.class)) {
            DateTimeFormatter.ofPattern("EEEE yyyy/MMMM/dd h:mm a").format((LocalDateTime) source);
        }
        return null;
    }
}
