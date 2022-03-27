package ru.volkovan.booker.general.form;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.shared.Registration;
import ru.volkovan.booker.general.buttons.AppButtons;
import ru.volkovan.booker.general.data.HasId;
import ru.volkovan.booker.general.events.SelectNotifier;
import ru.volkovan.booker.general.events.SwitchEditorClickNotifier;
import ru.volkovan.booker.general.form.styles.AppFormClass;

import java.util.Optional;

public class EditForm<T extends HasId> extends AppForm<T>
        implements SwitchEditorClickNotifier, SelectNotifier<T> {

    private Button deleteButton;

    private Registration switchEditorClickRegistration;
    private Registration selectRegistration;

    public EditForm() {
        super.setTitle("Save");
        super.addClassName(AppFormClass.EDIT_FORM.create());
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        this.switchEditorClickRegistration = addUISwitchEditorClickListener(switchEvent -> switchVisible());
        this.selectRegistration = addUISelectListener(selectEvent -> readBean(selectEvent.getSelected()));
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        this.switchEditorClickRegistration.remove();
        this.selectRegistration.remove();
    }

    @Override
    public void readBean(T bean) {
        super.readBean(bean);
        super.setTitle(bean == null ? "Create" : "Edit");
        this.deleteButton.setVisible(bean != null);
    }

    @Override
    protected Button[] getActionButtons() {
        this.deleteButton = AppButtons.deleteButton(
                () -> Optional.ofNullable(this.formLayout.getBeanSource()));
        return new Button[]{
                AppButtons.saveButton(formLayout::getValidBean),
                this.deleteButton
        };
    }
}
