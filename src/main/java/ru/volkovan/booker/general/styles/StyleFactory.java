package ru.volkovan.booker.general.styles;

import java.util.Locale;

public interface StyleFactory {

    default String create() {
        return name().toLowerCase(Locale.ENGLISH).replace('_', '-');
    }

    String name();
}
