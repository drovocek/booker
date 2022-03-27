package ru.volkovan.booker.views.users;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import ru.volkovan.booker.general.view.GridView;
import ru.volkovan.booker.general.fields.AppField;
import ru.volkovan.booker.general.fields.AppFields;
import ru.volkovan.booker.general.main.MainLayout;

import java.util.List;

@UIScope
@SpringComponent
@PageTitle("Users")
@Route(value = "users", layout = MainLayout.class)
public class UsersView extends GridView<GridUser, FilterUser> {

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
}
