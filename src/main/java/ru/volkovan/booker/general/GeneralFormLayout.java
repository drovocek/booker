package ru.volkovan.booker.general;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import ru.volkovan.booker.general.fields.AppField;
import ru.volkovan.booker.general.styles.GridViewClass;
import ru.volkovan.booker.general.util.AppReflectionUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public abstract class GeneralFormLayout<T> extends FormLayout {

    protected final Div tittle;
    protected Class<T> beanType;
    protected Binder<T> binder;

    protected int responsiveWidthStep = 180;
    protected int startResponsiveWidth = 360;

    public GeneralFormLayout() {
        this.tittle = new Div();
        this.tittle.addClassName(GridViewClass.GRID_VIEW_FORM_TITLE.create());
        super.add(this.tittle);
        super.setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("320px", 2)
        );
    }

    protected void setTitle(String text) {
        this.tittle.setText(text);
    }

    public void configure(Class<T> beanType, List<AppField> fields) {
        this.beanType = beanType;
        this.binder = new BeanValidationBinder<>(this.beanType);
        fields.forEach(field -> {
            this.binder.forField((HasValue) field).bind(field.getFieldName());
            super.add((Component) field);
        });
        super.add(getActionButtons());
        IntStream.range(1, fields.size() - 2)
                .forEach(i -> super.setResponsiveSteps(
                        new ResponsiveStep(startResponsiveWidth + i * responsiveWidthStep + "px", i + 2)));
    }

    public boolean isValid() {
        return this.binder.isValid();
    }

    public void clear() {
        this.binder.readBean(AppReflectionUtil.getInstance(this.beanType));
    }

    public Optional<T> getValidBean() {
        T instance = AppReflectionUtil.getInstance(this.beanType);
        if (this.binder.writeBeanIfValid(instance)) {
            return Optional.of(instance);
        }
        return Optional.empty();
    }

    protected abstract Button[] getActionButtons();
}
