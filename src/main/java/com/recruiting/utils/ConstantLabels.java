package com.recruiting.utils;

import com.recruiting.model.modelUtils.StringItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marta Ginosyan
 */

public class ConstantLabels {

    public static List<StringItemModel> REGISTRATION_TYPES_LIST = registrationTypes();

    private static List<StringItemModel> registrationTypes() {
        List<StringItemModel> registrationTypes = new ArrayList<>(2);
        registrationTypes.add(new StringItemModel("HR"));
        registrationTypes.add(new StringItemModel("Employee"));
        return registrationTypes;
    }


}
