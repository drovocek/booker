package ru.volkovan.booker.general.buttons;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import lombok.experimental.UtilityClass;
import ru.volkovan.booker.general.entity.HasId;
import ru.volkovan.booker.general.events.DeleteEvent;
import ru.volkovan.booker.general.events.FilterEvent;
import ru.volkovan.booker.general.events.SaveEvent;
import ru.volkovan.booker.general.styles.ButtonClass;

import java.util.Optional;
import java.util.function.Supplier;

@UtilityClass
public class AppButtons {

    public static <T> Button filterButton(Supplier<Optional<T>> beanOptSupplier) {
        Button filterButton = new Button("ok");
        filterButton.setIcon(VaadinIcon.FILTER.create());
        filterButton.addClickListener(clickEvent ->
                beanOptSupplier.get().ifPresent(bean ->
                        ComponentUtil.fireEvent(UI.getCurrent(),
                                new FilterEvent<>(filterButton, bean))));
        filterButton.addClassName(ButtonClass.FILTER_BUTTON.create());
        filterButton.removeThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        return filterButton;
    }

    public static <T> Button saveButton(Supplier<Optional<T>> beanOptSupplier) {
        Button saveButton = new Button("Save");
        saveButton.setIcon(VaadinIcon.DISC.create());
        saveButton.addClickListener(clickEvent ->
                beanOptSupplier.get().ifPresent(bean ->
                        ComponentUtil.fireEvent(UI.getCurrent(),
                                new SaveEvent<>(saveButton, bean))));
        saveButton.addClassName(ButtonClass.SAVE_BUTTON.create());
        saveButton.removeThemeVariants(ButtonVariant.LUMO_SUCCESS);
        return saveButton;
    }

    public static <T extends HasId> Button deleteButton(Supplier<Optional<T>> beanOptSupplier) {
        Button deleteButton = new Button("Delete");
        deleteButton.setIcon(VaadinIcon.TRASH.create());
        deleteButton.addClickListener(clickEvent ->
                beanOptSupplier.get().ifPresent(bean ->
                        ComponentUtil.fireEvent(UI.getCurrent(),
                                new DeleteEvent<>(deleteButton, bean))));
        deleteButton.addClassName(ButtonClass.DELETE_BUTTON.create());
        return deleteButton;
    }

    public static Button button(String text, VaadinIcon icon, Runnable onClick, ButtonVariant... buttonVariants) {
        Button button = new Button(text);
        button.setIcon(icon.create());
        button.addClickListener(clickEvent -> onClick.run());
        button.removeThemeVariants(buttonVariants);
        return button;
    }
}
