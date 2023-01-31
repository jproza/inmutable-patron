package ar.com.inmutable.inmutableobjects.builder.utils;

import java.util.Map;
import java.util.Collection;
import java.util.Optional;
import java.lang.reflect.Array;

public class ValidatorUtils {


    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }


    public static boolean isEmpty(final Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        }
        if (isArray(object)) {
            return Array.getLength(object) == 0;
        }
        if (object instanceof Collection<?>) {
            return ((Collection<?>) object).isEmpty();
        }
        if (object instanceof Map<?, ?>) {
            return ((Map<?, ?>) object).isEmpty();
        }
        if (object instanceof Optional<?>) {
            return !((Optional<?>) object).isPresent();
        }
        return false;
    }

    public static boolean isArray(final Object object) {
        return object != null && object.getClass().isArray();
    }



}
