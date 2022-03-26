package ru.volkovan.booker.general.fields;

import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppTextField extends TextField implements AppField {

    private String fieldName;

    public AppTextField() {
    }

    public AppTextField(String label) {
        super(label);
    }

    public AppTextField(String label, String placeholder) {
        super(label, placeholder);
    }

    public AppTextField(String label, String initialValue, String placeholder) {
        super(label, initialValue, placeholder);
    }

    public AppTextField(ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> listener) {
        super(listener);
    }

    public AppTextField(String label, ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> listener) {
        super(label, listener);
    }

    public AppTextField(String label, String initialValue, ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> listener) {
        super(label, initialValue, listener);
    }
}
