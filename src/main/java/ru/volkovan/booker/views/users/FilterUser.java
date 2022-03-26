package ru.volkovan.booker.views.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FilterUser {

    private String email;

    private String username;

    private boolean active;

    private boolean enabled;
}
