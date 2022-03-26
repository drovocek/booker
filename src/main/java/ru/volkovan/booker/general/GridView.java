package ru.volkovan.booker.general;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ru.volkovan.booker.general.entity.HasId;
import ru.volkovan.booker.general.fields.AppField;
import ru.volkovan.booker.general.styles.GridViewClass;

import java.util.List;

public abstract class GridView<T extends HasId, F> extends HorizontalLayout {

    protected final FilterFormLayout<F> filterForm;
    protected final Grid<T> grid;
    protected final EditFormLayout<T> editForm;

    public GridView() {
        this.filterForm = new FilterFormLayout<>();
        this.grid = new Grid<>(getGridBeanType());
        this.editForm = new EditFormLayout<>();

        configFilterForm();
        configEditForm();

        addClassName(GridViewClass.GRID_VIEW.create());

        add(new VerticalLayout(this.filterForm, this.grid), this.editForm);

        System.out.printf("<< create class %s >> %n", this.getClass().getSimpleName());
    }

    protected void configFilterForm() {
        this.filterForm.configure(getFilterBeanType(), getFilterFields());
    }

    protected void configEditForm() {
        this.editForm.configure(getGridBeanType(), getEditFields());
    }

    protected abstract Class<T> getGridBeanType();

    protected abstract Class<F> getFilterBeanType();

    protected abstract List<AppField> getEditFields();

    protected abstract List<AppField> getFilterFields();
}
