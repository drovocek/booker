package ru.volkovan.booker.general.util;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class AppReflectionUtil {

    @SneakyThrows
    public static <T> T getInstance(Class<T> beanType) {
        return beanType.getConstructor().newInstance();
    }

    public static Method getMethodByName(String name, Class<?> beanType) {
        Optional<Method> methodOpt = Arrays.stream(beanType.getDeclaredMethods())
                .filter(method -> method.getName().equals(name))
                .findFirst();

        if (methodOpt.isEmpty()) {
            throw new RuntimeException(
                    "Method with name '%s' in class %s does not exist".formatted(name, beanType));
        }

        return methodOpt.get();
    }

    @SneakyThrows
    public static Object invoke(Method method, Object source, Object... args) {
        return method.invoke(source, args);
    }
}
