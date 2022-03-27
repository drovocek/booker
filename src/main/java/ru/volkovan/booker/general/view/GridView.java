package ru.volkovan.booker.general.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import ru.volkovan.booker.general.entity.HasId;
import ru.volkovan.booker.general.fields.AppField;
import ru.volkovan.booker.general.form.EditForm;
import ru.volkovan.booker.general.form.FilterForm;
import ru.volkovan.booker.general.grid.AppGridConfigurator;
import ru.volkovan.booker.general.view.styles.GridViewClass;

import java.util.List;

public abstract class GridView<T extends HasId, F> extends HorizontalLayout {

    protected final FilterForm<F> filterForm;
    protected final Grid<T> grid;
    protected final EditForm<T> editForm;

    public GridView() {
        this.filterForm = new FilterForm<>();
        this.grid = new Grid<>();
        this.editForm = new EditForm<>();

        configFilterForm();
        configGrid();
        configEditForm();

        Div leftBlock = new Div(this.filterForm, this.grid);
        Div rightBlock = new Div(this.editForm);
        add(leftBlock, rightBlock);

        super.addClassName(GridViewClass.GRID_VIEW.create());
        leftBlock.addClassName(GridViewClass.GRID_VIEW_LEFT_BLOCK.create());
        rightBlock.addClassName(GridViewClass.GRID_VIEW_RIGHT_BLOCK.create());

        System.out.printf("<< create class %s >> %n", this.getClass().getSimpleName());
    }

    protected void configGrid() {
        AppGridConfigurator.configure(getGridBeanType(), this.grid);
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
