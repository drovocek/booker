package ru.volkovan.booker.general.form;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.shared.Registration;
import ru.volkovan.booker.general.buttons.AppButtons;
import ru.volkovan.booker.general.events.SwitchFilterClickNotifier;
import ru.volkovan.booker.general.form.styles.AppFormClass;

public class FilterForm<T> extends AppForm<T> implements SwitchFilterClickNotifier {

    private Registration switchFilterClickRegistration;

    public FilterForm() {
        super.setTitle("Filter");
        super.addClassName(AppFormClass.FILTER_FORM.create());
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        this.switchFilterClickRegistration = addUISwitchFilterClickListener(switchEvent -> switchVisible());
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        this.switchFilterClickRegistration.remove();
    }

    @Override
    protected Button[] getActionButtons() {
        return new Button[]{
                AppButtons.filterButton(formLayout::getValidBean)
        };
    }
}
