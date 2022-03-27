package ru.volkovan.booker.general.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import ru.volkovan.booker.general.data.AppCrudService;
import ru.volkovan.booker.general.data.HasId;
import ru.volkovan.booker.general.events.DeleteNotifier;
import ru.volkovan.booker.general.events.FilterNotifier;
import ru.volkovan.booker.general.events.SaveNotifier;
import ru.volkovan.booker.general.events.SelectPublisher;
import ru.volkovan.booker.general.fields.AppField;
import ru.volkovan.booker.general.form.EditForm;
import ru.volkovan.booker.general.form.FilterForm;
import ru.volkovan.booker.general.grid.AppGridConfigurator;
import ru.volkovan.booker.general.view.styles.GridViewClass;

import java.util.LinkedList;
import java.util.List;

public abstract class GridView<T extends HasId, F> extends HorizontalLayout
        implements FilterNotifier<F>, DeleteNotifier<T>, SaveNotifier<T>,
        SelectPublisher<T> {

    protected final FilterForm<F> filterForm;
    protected final Grid<T> grid;
    protected final EditForm<T> editForm;

    private Registration filterRegistration;
    private Registration deleteRegistration;
    private Registration saveRegistration;

    private final LinkedList<T> itemsStore = new LinkedList<>();

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

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        this.filterRegistration = addUIFilterListener(filterEvent -> {
            this.itemsStore.clear();
            this.itemsStore.addAll(getFilterService().findByFilter(filterEvent.getFilter()));
            this.grid.getDataProvider().refreshAll();
        });
        this.deleteRegistration = addUIDeleteListener(deleteEvent -> {
            getFilterService().delete(deleteEvent.getDeleted());
            this.itemsStore.remove(deleteEvent.getDeleted());
            this.grid.getDataProvider().refreshAll();
        });
        this.saveRegistration = addUISaveListener(saveEvent -> {
            T itemNew = getFilterService().save(saveEvent.getPersist());
            boolean noneSource = this.itemsStore.stream()
                    .noneMatch(item -> item.getId().equals(itemNew.getId()));

            if (noneSource) {
                this.itemsStore.offerFirst(saveEvent.getPersist());
            }

            this.grid.getDataProvider().refreshAll();
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        this.filterRegistration.remove();
        this.deleteRegistration.remove();
        this.saveRegistration.remove();
    }

    protected void configGrid() {
        AppGridConfigurator.configure(getGridBeanType(), this.grid);
        this.grid.addSelectionListener(selectEvent ->
                fireUISelectEvent(selectEvent.getFirstSelectedItem()
                        .orElse(null)));
        this.grid.setItems(this.itemsStore);
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

    protected abstract AppCrudService<T, F> getFilterService();
}
