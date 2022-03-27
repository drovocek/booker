package ru.volkovan.booker.general.events;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;

import java.io.Serializable;

public interface SelectPublisher<T> extends Serializable {

    default void fireUISelectEvent(T selected) {
        ComponentUtil.fireEvent(UI.getCurrent(), new SelectEvent<>((Component) this, selected));
    }
}
