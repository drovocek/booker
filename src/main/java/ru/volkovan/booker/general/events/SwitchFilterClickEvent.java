package ru.volkovan.booker.general.events;


import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import lombok.Getter;

@Getter
public class SwitchFilterClickEvent extends ComponentEvent<Button> {

    public SwitchFilterClickEvent(Button source) {
        super(source, false);
    }
}
