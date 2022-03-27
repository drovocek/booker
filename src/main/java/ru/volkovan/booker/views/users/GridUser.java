package ru.volkovan.booker.views.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import ru.volkovan.booker.general.entity.AbstractEntity;
import ru.volkovan.booker.general.entity.HasId;
import ru.volkovan.booker.general.grid.AppColumn;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class GridUser extends AbstractEntity implements HasId {

    @Email
    @NotBlank
    @Length(max = 100)
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100, updatable = false)
    @AppColumn(label = "Email")
    private String email;

    @NotBlank
    @Length(max = 50)
    @Column(name = "USERNAME", nullable = false, unique = true, length = 50)
    @AppColumn(label = "Username")
    private String username;

    @NotBlank
    @Length(max = 100)
    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false, length = 100)
    @AppColumn(label = "Password")
    private String password;

    @NotNull
    @Column(name = "REGISTRATION_DATE", nullable = false)
    @AppColumn(label = "Registration")
    private LocalDateTime registration;

    @Column(name = "LAST_ACCESS_DATE")
    @AppColumn(label = "Last access")
    private LocalDateTime lastAccess;

    @Column(name = "ACTIVE", nullable = false)
    @AppColumn(label = "Active", order = 0)
    private Boolean active;

    @Column(name = "ENABLED", nullable = false)
    @AppColumn(label = "Enabled", order = 1)
    private Boolean enabled;

    public GridUser(Integer id) {
        super(id);
    }
}
