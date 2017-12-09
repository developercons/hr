package com.recruiting.converter;

import com.recruiting.domain.TimeOffType;
import com.recruiting.service.entity.TimeOffService;
import com.recruiting.utils.StringUtils;
import org.springframework.binding.convert.converters.InvalidFormatException;
import org.springframework.binding.convert.converters.StringToObject;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Martha on 5/19/2017.
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
            return service.findById(new Long(string));
        } catch (Exception e) {
            throw new InvalidFormatException(string, "Industry", e);
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
        return service.findById(new Long((String) source));
    }
}
