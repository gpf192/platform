package cn.xsdzq.platform.util;

import cn.xsdzq.platform.model.mall.StatusEnumDTO;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class StatusEnumUtil {

    public static <E extends Enum<E>> List<StatusEnumDTO> getStatusEnumDTO(Class<E> elementType) {
        ArrayList<StatusEnumDTO> statusEnumDTOList = new ArrayList<>();
        EnumSet<E> es = EnumSet.allOf(elementType);
        for (E next : es) {
            Class<E> declaringClass = next.getDeclaringClass();
            try {
                Method getCodeMtd = declaringClass.getMethod("getCode");
                Object code = getCodeMtd.invoke(next);
                if (!"-1".equals(String.valueOf(code))) {
                    StatusEnumDTO statusEnumDTO = new StatusEnumDTO();
                    statusEnumDTO.setCode(String.valueOf(code));

                    Method getDescMtd = declaringClass.getMethod("getDesc");
                    Object desc = getDescMtd.invoke(next);
                    statusEnumDTO.setDesc(String.valueOf(desc));
                    statusEnumDTOList.add(statusEnumDTO);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        StatusEnumDTO statusEnumDTO = new StatusEnumDTO();
        statusEnumDTO.setCode("all");
        statusEnumDTO.setDesc("全部");
        statusEnumDTOList.add(statusEnumDTO);
        return statusEnumDTOList;
    }

}
