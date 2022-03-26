package ru.volkovan.booker.general.fields;

import com.vaadin.flow.component.combobox.ComboBox;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class AppComboBox<T> extends ComboBox<T> implements AppField {

    private String fieldName;

    public AppComboBox(int pageSize) {
        super(pageSize);
    }

    public AppComboBox() {
    }

    public AppComboBox(String label) {
        super(label);
    }

    public AppComboBox(String label, Collection<T> items) {
        super(label, items);
    }

    @SafeVarargs
    public AppComboBox(String label, T... items) {
        super(label, items);
    }
}
