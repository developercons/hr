package com.recruiting.converter;

import com.recruiting.domain.TimeOffType;
import com.recruiting.service.entity.TimeOffService;
import com.recruiting.utils.StringUtils;
import org.springframework.binding.convert.converters.InvalidFormatException;
import org.springframework.binding.convert.converters.StringToObject;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Marta Ginosyan
 */

public class StringToTimeOffTypeConverter extends StringToObject implements Converter {

    private final TimeOffService service;

    public StringToTimeOffTypeConverter(TimeOffService service) {
        super(TimeOffType.class);
        this.service = service;
    }

    @Override
    protected Object toObject(String string, Class targetClass) throws Exception {
        if (StringUtils.isNullOrEmpty(string) || new Long(string) < 1) {
            return null;
        }
        try {
            return service.findById(string);
        } catch (Exception e) {
            throw new InvalidFormatException(string, "TimeOffType", e);
        }
    }

    @Override
    protected String toString(Object object) throws Exception {
        TimeOffType timeOffType = (TimeOffType) object;
        if (timeOffType == null || timeOffType.getId() == null) {
            return "";
        }
        return timeOffType.getId().toString();
    }

    @Override
    public Object convert(Object source) {
        return service.findById((String) source);
    }
}
