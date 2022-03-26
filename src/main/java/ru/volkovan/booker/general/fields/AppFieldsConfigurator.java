package ru.volkovan.booker.general.fields;

import com.vaadin.flow.component.HasLabel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AppFieldsConfigurator {

    public static AppTextField configure(AppTextField field, String fieldName, String label) {
        field.setFieldName(fieldName);
        field.setLabel(label);
        return field;
    }

    public static AppCheckbox configure(AppCheckbox field, String fieldName, String label) {
        field.setFieldName(fieldName);
        field.setLabel(label);
        return field;
    }

    public static <T extends HasLabel> AppComboBox<T> configure(AppComboBox<T> field, String fieldName, String label, T[] data) {
        field.setFieldName(fieldName);
        field.setLabel(label);
        field.setItems(data);
        field.setItemLabelGenerator(T::getLabel);
        return field;
    }
}
