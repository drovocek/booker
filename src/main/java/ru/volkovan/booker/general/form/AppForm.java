package ru.volkovan.booker.general.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import lombok.Getter;
import ru.volkovan.booker.general.fields.AppField;
import ru.volkovan.booker.general.form.styles.AppFormClass;

import java.util.List;

public abstract class AppForm<T> extends Div {

    @Getter
    protected final AppFormLayout<T> formLayout;
    protected final Div title;
    protected final Div content;
    protected final Div actionPanel;

    public AppForm() {
        this.formLayout = new AppFormLayout<>();
        this.title = new Div();
        this.content = new Div(this.formLayout);
        this.actionPanel = new Div();

        super.add(this.title, this.content, this.actionPanel);
        this.actionPanel.add(getActionButtons());
        config();
    }

    protected void config() {
        this.addClassName(AppFormClass.APP_FORM.create());
        this.title.addClassName(AppFormClass.FORM_TITLE.create());
        this.content.addClassName(AppFormClass.FORM_CONTENT.create());
        this.actionPanel.addClassName(AppFormClass.FORM_ACTION_PANEL.create());
    }

    protected void switchVisible() {
        super.setVisible(!super.isVisible());
    }

    public void readBean(T bean) {
        this.formLayout.binder.readBean(bean);
    }

    public void configure(Class<T> beanType, List<AppField> fields) {
        this.formLayout.configure(beanType, fields);
    }

    @Override
    public void setTitle(String text) {
        this.title.setText(text);
    }

    protected abstract Button[] getActionButtons();
}
