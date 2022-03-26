package ru.volkovan.booker.general;

import com.vaadin.flow.component.button.Button;
import ru.volkovan.booker.general.buttons.AppButtons;
import ru.volkovan.booker.general.entity.HasId;
import ru.volkovan.booker.general.styles.GridViewClass;

import java.util.Optional;

public class EditFormLayout<T extends HasId> extends GeneralFormLayout<T> {

    private Button deleteButton;
    private T beanSource;

    public EditFormLayout() {
        super.addClassName(GridViewClass.GRID_VIEW_EDIT_FORM.create());
    }

    public void readBean(T bean) {
        this.beanSource = bean;
        super.binder.readBean(bean);
        super.setTitle(bean.isNew() ? "Сreate" : "Edit");
        this.deleteButton.setVisible(!bean.isNew());
    }

    @Override
    protected Button[] getActionButtons() {
        this.deleteButton = AppButtons.deleteButton(() -> Optional.ofNullable(this.beanSource));
        return new Button[]{
                AppButtons.saveButton(super::getValidBean),
                this.deleteButton
        };
    }
}
