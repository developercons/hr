package com.recruiting.converter;

import com.recruiting.entity.WorkingHoursScheme;
import com.recruiting.repository.WorkingHoursSchemeRepository;
import com.recruiting.utils.StringUtils;
import org.springframework.binding.convert.converters.InvalidFormatException;
import org.springframework.binding.convert.converters.StringToObject;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Marta Ginosyan
 */

public class StringToWorkingHoursSchemeConverter extends StringToObject implements Converter {

    private final WorkingHoursSchemeRepository workingHoursSchemeRepository;

    public StringToWorkingHoursSchemeConverter(WorkingHoursSchemeRepository workingHoursSchemeRepository) {
        super(WorkingHoursScheme.class);
        this.workingHoursSchemeRepository = workingHoursSchemeRepository;
    }

    @Override
    protected Object toObject(String string, Class targetClass) throws Exception {
        if (StringUtils.isNullOrEmpty(string) || new Long(string) < 1) {
            return null;
        }
        try {
            return workingHoursSchemeRepository.findOne(new Long(string));
        } catch (Exception e) {
            throw new InvalidFormatException(string, "WorkingHoursScheme", e);
        }
    }

    @Override
    protected String toString(Object object) throws Exception {
        WorkingHoursScheme workingHoursScheme = (WorkingHoursScheme) object;
        if (workingHoursScheme == null || workingHoursScheme.getId() == null) {
            return "";
        }
        return workingHoursScheme.getId()
                .toString();
    }

    @Override
    public Object convert(Object source) {
        return workingHoursSchemeRepository.findOne(new Long((String) source));
    }
}
