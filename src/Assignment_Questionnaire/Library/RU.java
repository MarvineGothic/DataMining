package Assignment_Questionnaire.Library;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Reflection utility class
 */
@SuppressWarnings("all")
public class RU {

    /**
     * Typical reflection magic to get value from variable
     *
     * @param field
     * @return
     */
    public static Object getValueByName(String field, Object o) {
        Object value = null;
        try {
            value = (o.getClass().getField(field).get(o));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            value = null;
        }
        return value;
    }

    /**
     * Same magic to get a method from anonymous object, knowing its name.
     *
     * @param methodName
     * @param object
     * @return
     */
    public static Object getMethod(String methodName, Object object) {
        Class clazz = object.getClass();
        Object result = null;
        try {
            Method method = clazz.getMethod(methodName);
            result = method.invoke(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
