package ru.volkovan.booker.general;

import com.vaadin.flow.component.button.Button;
import ru.volkovan.booker.general.buttons.AppButtons;
import ru.volkovan.booker.general.styles.GridViewClass;

public class FilterFormLayout<F> extends GeneralFormLayout<F> {

    public FilterFormLayout() {
        super.setTitle("Filter");
        super.addClassName(GridViewClass.GRID_VIEW_FILTER_FORM.create());
    }

    @Override
    protected Button[] getActionButtons() {
        return new Button[]{
                AppButtons.filterButton(this::getValidBean)
        };
    }
}
