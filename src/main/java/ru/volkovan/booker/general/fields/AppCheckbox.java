package ru.volkovan.booker.general.fields;


import com.vaadin.flow.component.checkbox.Checkbox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppCheckbox extends Checkbox implements AppField {

    private String fieldName;

    public AppCheckbox() {
    }

    public AppCheckbox(String labelText) {
        super(labelText);
    }

    public AppCheckbox(boolean initialValue) {
        super(initialValue);
    }

    public AppCheckbox(String labelText, boolean initialValue) {
        super(labelText, initialValue);
    }

    public AppCheckbox(String label, ValueChangeListener<ComponentValueChangeEvent<Checkbox, Boolean>> listener) {
        super(label, listener);
    }
}
