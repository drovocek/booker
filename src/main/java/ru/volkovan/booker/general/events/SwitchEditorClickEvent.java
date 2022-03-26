package ru.volkovan.booker.general.events;


import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import lombok.Getter;

@Getter
public class SwitchEditorClickEvent extends ComponentEvent<Button> {

    public SwitchEditorClickEvent(Button source) {
        super(source, false);
    }
}
