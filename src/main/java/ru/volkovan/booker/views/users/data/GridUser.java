package ru.volkovan.booker.views.users.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import ru.volkovan.booker.general.data.AbstractEntity;
import ru.volkovan.booker.general.data.HasId;
import ru.volkovan.booker.general.grid.AppColumn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "APP_USER")
public class GridUser extends AbstractEntity implements HasId {

    @Email
    @NotBlank
    @Length(max = 100)
    @Column(name = "EMAIL")
    @AppColumn(label = "Email")
    private String email;

    @NotBlank
    @Length(max = 50)
    @Column(name = "USERNAME")
    @AppColumn(label = "Username")
    private String username;

    @NotNull
    @Column(name = "REGISTRATION_DATE")
    @AppColumn(label = "Registration")
    private LocalDateTime registration;

    @Column(name = "LAST_ACCESS_DATE")
    @AppColumn(label = "Last access")
    private LocalDateTime lastAccess;

    @Column(name = "ACTIVE")
    @AppColumn(label = "Active", order = 0)
    private Boolean active;

    @Column(name = "ENABLED")
    @AppColumn(label = "Enabled", order = 1)
    private Boolean enabled;

    @PrePersist
    private void beforeSave() {
        this.registration = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
    }

    public GridUser(Integer id) {
        super(id);
    }
}
