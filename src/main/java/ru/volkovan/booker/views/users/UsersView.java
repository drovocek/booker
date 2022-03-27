package ru.volkovan.booker.views.users;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import ru.volkovan.booker.general.data.AppCrudService;
import ru.volkovan.booker.general.fields.AppField;
import ru.volkovan.booker.general.fields.AppFields;
import ru.volkovan.booker.general.main.MainLayout;
import ru.volkovan.booker.general.view.GridView;
import ru.volkovan.booker.views.users.data.FilterUser;
import ru.volkovan.booker.views.users.data.GridUser;
import ru.volkovan.booker.views.users.data.GridUserCrudService;

import java.util.List;

@RequiredArgsConstructor
@UIScope
@SpringComponent
@PageTitle("Users")
@Route(value = "users", layout = MainLayout.class)
public class UsersView extends GridView<GridUser, FilterUser> {

    private final GridUserCrudService userService;

    @Override
    protected Class<GridUser> getGridBeanType() {
        return GridUser.class;
    }

    @Override
    protected Class<FilterUser> getFilterBeanType() {
        return FilterUser.class;
    }

    @Override
    protected List<AppField> getEditFields() {
        return List.of(
                AppFields.textField("email", "Email"),
                AppFields.textField("username", "Username"),
                AppFields.checkBox("active", "Active"),
                AppFields.checkBox("enabled", "Enabled")
        );
    }

    @Override
    protected List<AppField> getFilterFields() {
        return List.of(
                AppFields.textField("email", "Email"),
                AppFields.textField("username", "Username"),
                AppFields.checkBox("active", "Active"),
                AppFields.checkBox("enabled", "Enabled")
        );
    }

    @Override
    protected AppCrudService<GridUser, FilterUser> getFilterService() {
        return this.userService;
    }
}
