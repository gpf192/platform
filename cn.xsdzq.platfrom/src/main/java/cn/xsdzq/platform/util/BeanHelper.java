package cn.xsdzq.platform.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeanHelper {

    /**
     * @param source 要拷贝的对象
     * @return
     * @Description <p>获取到对象中属性为null的属性名  </P>
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * @param source 源对象
     * @param target 目标对象
     * @Description <p> 拷贝非空对象属性值 </P>
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static <T, E> List<E> copyList(List<T> sourceList, Class<E> clazz) {
        ArrayList<E> targetList = new ArrayList<>(sourceList.size());
        for (T source : sourceList) {
            E target;
            try {
                target = clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("");
            }
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        return targetList;
    }
}
