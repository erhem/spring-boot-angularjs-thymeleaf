package com.diputra.erhem.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

    public User() {

    }

    public User(String firstName, String lastName, String username, boolean active, Date birthOfDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.active = active;
        this.birthOfDate = birthOfDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int userId;

    @Column(nullable = false, length = 255)
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 15)
    String firstName;

    @Column(nullable = false, length = 255)
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 15)
    String lastName;

    @Column(nullable = false, length = 255)
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 15)
    String userName;

    @Column(nullable = false, length = 255)
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 15)
    String password;

    @Column(nullable = false)
    boolean active;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @Past
    @NotNull
    private Date birthOfDate;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(Date birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
