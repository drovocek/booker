package ru.volkovan.booker.general.events;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import lombok.Getter;

@Getter
public class SaveEvent<T> extends ComponentEvent<Component> {

    private final T persist;

    public SaveEvent(Component source, T persist) {
        super(source, false);
        this.persist = persist;
    }
}
