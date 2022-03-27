package ru.volkovan.booker.general.grid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AppColumn {

    String label() default "_default_";

    int order() default 200;

    String width() default "auto";

    @Target({ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Adapter {
        Class<? extends FieldAdapter> value();
    }
}
