// Path src/org/example/hexlet/model/User.java
package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class User {
    private long id;
    @ToString.Include
    private String name;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }
}
