package ru.volkovan.booker.views.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import ru.volkovan.booker.general.entity.AbstractEntity;
import ru.volkovan.booker.general.entity.HasId;

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
    private String email;

    @NotBlank
    @Length(max = 50)
    @Column(name = "USERNAME", nullable = false, unique = true, length = 50)
    private String username;

    @NotBlank
    @Length(max = 100)
    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @NotNull
    @Column(name = "REGISTRATION_DATE", nullable = false)
    private LocalDateTime registration;

    @Column(name = "LAST_ACCESS_DATE")
    private LocalDateTime lastAccess;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    public GridUser(Integer id) {
        super(id);
    }
}
