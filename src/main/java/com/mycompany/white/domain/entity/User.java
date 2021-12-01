package com.mycompany.white.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles = new HashSet<>();

    public void addUserRole(UserRole userRole){
        this.userRoles.add(userRole);
        userRole.addUser(this);
    }

    @Builder
    public User(String email, String password, UserRole userRole) {
        this.email = email;
        this.password = password;
        addUserRole(userRole);
    }


}
