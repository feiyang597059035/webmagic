package com.carwel.webmagic.util;

import org.apache.commons.collections.CollectionUtils;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

import java.util.List;


public class DataConvertUtils<DO, DTO> {


    public static<DO, DTO> DO convertDTO2DO(DTO src, DO target) {

        BeanUtils.copyProperties(src, target);
        return target;

    }

    public static <DO, DTO> DTO convertDO2DTO(DO src, Class<DTO> dtoClass) {
        try {
            DTO dto = dtoClass.newInstance();
            BeanUtils.copyProperties(src, dto);
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static<DO, DTO> DTO convertDO2DTO(DO src, DTO target) {
    		if(src == null) {
			return target;
		}
    		
        if (src.getClass().isArray() && target.getClass().isArray()) {

        }
        BeanUtils.copyProperties(src, target);
        return target;

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void convertDOList2DTOList(List origin, List target, Class targetClazz) {
        if (CollectionUtils.isNotEmpty(origin)) {
            List list = (List) origin;
            for (Object info : list) {
                try {
                    Object dto = targetClazz.newInstance();
                    BeanUtils.copyProperties(info, dto);
                    target.add(dto);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static<T> List<T> assermblyString2List(String target, String splitChar, T type) {
        List<T> result = new ArrayList<T>();
        if (StringUtils.isBlank(target)) {
            return null;
        }

        String[] targetArrays = StringUtils.split(target, splitChar);
        if (targetArrays == null || targetArrays.length == 0) {
            return null;
        }


        for (String targetArray : targetArrays) {
            if (type instanceof String) {
                T t = (T) targetArray;
                result.add(t);
            } else if (type instanceof Long) {
                Long longValue = Long.parseLong(targetArray);
                T t = (T) longValue;
                result.add(t);
            }

        }

        return result;

    }
}
