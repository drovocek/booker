package ru.volkovan.booker.general.grid;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import ru.volkovan.booker.general.util.AppReflectionUtil;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppGridConfigurator {

    public static <T> Grid<T> configure(Class<T> beanType, Grid<T> grid) {
        grid.setColumnReorderingAllowed(true);
        AppColumnContext.getColumnDefinitionByFieldName(beanType)
                .forEach((fieldName, definition) -> {
                    constructColumn(grid, definition)
                            .setKey(definition.fieldName())
                            .setHeader(definition.label())
                            .setSortable(true)
                            .setResizable(true);
                });
        return grid;
    }

    private static <T> Grid.Column<T> constructColumn(Grid<T> grid, AppColumnContext.ColumnDefinition definition) {
        Class<? extends FieldAdapter> adapterType = definition.adapter();
        Method getter = definition.getter();
        Class<?> returnType = getter.getReturnType();
        if (adapterType != null) {
            FieldAdapter adapter = AppReflectionUtil.getInstance(adapterType);
            return grid.addColumn(new ComponentRenderer<>(
                    t -> adapter.convert(AppReflectionUtil.invoke(definition.getter(), t))));
        } else if (returnType.equals(LocalDate.class)) {
            return grid.addColumn(
                    new LocalDateRenderer<>(
                            t -> (LocalDate) AppReflectionUtil.invoke(definition.getter(), t),
                            "dd.MM.yyyy"));
        } else if (returnType.equals(LocalDateTime.class)) {
            return grid.addColumn(
                    new LocalDateTimeRenderer<>(
                            t -> (LocalDateTime) AppReflectionUtil.invoke(definition.getter(), t),
                            "dd.MM.yyyy"));
        }
        return grid.addColumn(t -> AppReflectionUtil.invoke(definition.getter(), t));
    }
}
