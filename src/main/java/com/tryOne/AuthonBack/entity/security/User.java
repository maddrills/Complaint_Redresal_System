package com.tryOne.AuthonBack.entity.security;

import com.tryOne.AuthonBack.entity.complaint.Pincodes;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(unique = true,name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private int enabled;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE
            }
    )
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "pincode_id")
    private Pincodes pincodes;

    public User() {
    }

    public User(String username, String password, int enabled, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(String username, String password, int enabled, String phoneNumber, String email, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roles = roles;
    }

    public User( String username, String password, int enabled, String phoneNumber, String email, Collection<Role> roles, Pincodes pincodes) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roles = roles;
        this.pincodes = pincodes;
    }

    public User(int id, String username, String password, int enabled, String phoneNumber, String email, Collection<Role> roles, Pincodes pincodes) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roles = roles;
        this.pincodes = pincodes;
    }




    public Pincodes getPincodes() {
        return pincodes;
    }

    public void setPincodes(Pincodes pincodes) {
        this.pincodes = pincodes;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    /**
     * @Security
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @Security
     * */

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", firstName='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}