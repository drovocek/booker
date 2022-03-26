package ru.volkovan.booker.general.fields;

import com.vaadin.flow.component.HasLabel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AppFields {

    public static AppTextField textField(String fieldName, String label) {
        AppTextField field = new AppTextField();
        return AppFieldsConfigurator.configure(field, fieldName, label);
    }

    public static AppCheckbox checkBox(String fieldName, String label) {
        AppCheckbox field = new AppCheckbox();
        return AppFieldsConfigurator.configure(field, fieldName, label);
    }

    public static <T extends HasLabel> AppComboBox<T> comboBox(String fieldName, String label, T[] data) {
        AppComboBox<T> field = new AppComboBox<>();
        return AppFieldsConfigurator.configure(field, fieldName, label, data);
    }
}
