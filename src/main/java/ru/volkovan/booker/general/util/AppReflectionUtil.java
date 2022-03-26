package ru.volkovan.booker.general.util;

import lombok.SneakyThrows;

public class AppReflectionUtil {

    @SneakyThrows
    public static <T> T getInstance(Class<T> beanType) {
        return beanType.getConstructor().newInstance();
    }
}
