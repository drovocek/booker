package ru.volkovan.booker.general.grid;

import com.vaadin.flow.component.Component;

public abstract class FieldAdapter<T> {

    protected FieldAdapter() {
    }

    public abstract Component convert(T fieldValue);
}