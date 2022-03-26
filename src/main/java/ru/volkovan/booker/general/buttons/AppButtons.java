package ru.volkovan.booker.general.buttons;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import lombok.experimental.UtilityClass;
import ru.volkovan.booker.general.buttons.styles.ButtonClass;
import ru.volkovan.booker.general.entity.HasId;
import ru.volkovan.booker.general.events.*;

import java.util.Optional;
import java.util.function.Supplier;

@UtilityClass
public class AppButtons {

    public static Button button(String text, VaadinIcon icon, Runnable onClick, ButtonVariant... buttonVariants) {
        Button button = new Button(text);
        button.setIcon(icon.create());
        button.addClickListener(clickEvent -> onClick.run());
        button.addThemeVariants(buttonVariants);
        return button;
    }

    public static <T> Button filterButton(Supplier<Optional<T>> beanOptSupplier) {
        Button button = new Button("ok");
        button.setIcon(VaadinIcon.ROCKET.create());
        button.addClickListener(clickEvent ->
                beanOptSupplier.get().ifPresent(bean ->
                        ComponentUtil.fireEvent(UI.getCurrent(),
                                new FilterEvent<>(button, bean))));
        button.addClassName(ButtonClass.FILTER_BUTTON.create());
        button.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        button.getElement().setAttribute("title", "Filter");
        return button;
    }

    public static <T> Button saveButton(Supplier<Optional<T>> beanOptSupplier) {
        Button button = new Button("Save");
        button.setIcon(VaadinIcon.DISC.create());
        button.addClickListener(clickEvent ->
                beanOptSupplier.get().ifPresent(bean ->
                        ComponentUtil.fireEvent(UI.getCurrent(),
                                new SaveEvent<>(button, bean))));
        button.addClassName(ButtonClass.SAVE_BUTTON.create());
        button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        button.getElement().setAttribute("title", "Save");
        return button;
    }

    public static <T extends HasId> Button deleteButton(Supplier<Optional<T>> beanOptSupplier) {
        Button button = new Button("Delete");
        button.setIcon(VaadinIcon.TRASH.create());
        button.addClickListener(clickEvent ->
                beanOptSupplier.get().ifPresent(bean ->
                        ComponentUtil.fireEvent(UI.getCurrent(),
                                new DeleteEvent<>(button, bean))));
        button.addClassName(ButtonClass.DELETE_BUTTON.create());
        button.addThemeVariants(ButtonVariant.LUMO_ERROR);
        button.getElement().setAttribute("title", "Delete");
        return button;
    }

    public static Button switchFilterButton() {
        Button button = new Button();
        button.setIcon(VaadinIcon.FILTER.create());
        button.addClickListener(clickEvent ->
                ComponentUtil.fireEvent(UI.getCurrent(),
                        new SwitchFilterClickEvent(button)));
        button.addClassName(ButtonClass.SWITCH_FILTER_BUTTON.create());
        button.addThemeVariants(ButtonVariant.LUMO_LARGE);
        button.getElement().setAttribute("title", "Filter");
        return button;
    }

    public static Button switchEditButton() {
        Button button = new Button();
        button.setIcon(VaadinIcon.DATABASE.create());
        button.addClickListener(clickEvent ->
                ComponentUtil.fireEvent(UI.getCurrent(),
                        new SwitchEditorClickEvent(button)));
        button.addClassName(ButtonClass.SWITCH_EDIT_BUTTON.create());
        button.addThemeVariants(ButtonVariant.LUMO_LARGE);
        button.getElement().setAttribute("title", "Edit");
        return button;
    }
}
