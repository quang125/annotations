package com.os.annotation.test.annotations;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class NamingStrategy extends PropertyNamingStrategy {
    @Override
    public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
        if (field.getAnnotation(FSortKey.class) != null) {
            return defaultName + "$";
        } else if (field.getAnnotation(FDistKey.class) != null) {
            return defaultName + "$$";
        } else if (field.getAnnotation(FAllKey.class) != null) {
            return defaultName + "$$$";
        }
        return defaultName;
    }

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        if (method.getAnnotation(FSortKey.class) != null) {
            return defaultName + "$";
        } else if (method.getAnnotation(FDistKey.class) != null) {
            return defaultName + "$$";
        } else if (method.getAnnotation(FAllKey.class) != null) {
            return defaultName + "$$$";
        }
        return defaultName;
    }
}
