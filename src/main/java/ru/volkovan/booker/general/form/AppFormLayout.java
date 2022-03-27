package ru.volkovan.booker.general.form;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;
import ru.volkovan.booker.general.fields.AppField;
import ru.volkovan.booker.general.form.styles.AppFormClass;
import ru.volkovan.booker.general.util.AppReflectionUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class AppFormLayout<T> extends FormLayout {

    protected Class<T> beanType;
    protected Binder<T> binder;
    @Getter
    private T beanSource;

    protected int responsiveWidthStep = 180;
    protected int startResponsiveWidth = 360;

    public AppFormLayout() {
        config();
    }

    protected void config() {
        this.addClassName(AppFormClass.FORM_LAYOUT.create());
    }

    public void configure(Class<T> beanType, List<AppField> fields) {
        this.beanType = beanType;
        this.binder = new BeanValidationBinder<>(this.beanType);
        fields.forEach(field -> {
            this.binder.forField((HasValue) field).bind(field.getFieldName());
            super.add((Component) field);
        });
        setResponsiveSteps(fields.size());
        readBean(null);
    }

    private void setResponsiveSteps(int fieldsCount) {
        super.setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("320px", 2)
        );
        IntStream.range(1, fieldsCount - 1)
                .forEach(i -> super.setResponsiveSteps(
                        new ResponsiveStep(startResponsiveWidth + i * responsiveWidthStep + "px", i + 2)));
    }

    public boolean isValid() {
        return this.binder.isValid();
    }

    public void readBean(T bean) {
        this.beanSource = bean != null ? bean : AppReflectionUtil.getInstance(this.beanType);
        this.binder.readBean(this.beanSource);
    }

    public void clear() {
        this.binder.readBean(AppReflectionUtil.getInstance(this.beanType));
    }

    public Optional<T> getValidBean() {
        if (this.binder.writeBeanIfValid(this.beanSource)) {
            return Optional.of(this.beanSource);
        }
        return Optional.empty();
    }
}
