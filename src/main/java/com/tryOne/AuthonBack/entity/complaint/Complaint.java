package com.tryOne.AuthonBack.entity.complaint;

import com.tryOne.AuthonBack.entity.security.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id")
    private int id;
    @Column(name = "active")
    private int active;
    @Column(name = "resolved")
    private int resolved;
    @Column(name = "escalated")
    private int escalated;
    @Column(name = "complaint")
    private int complaint;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "enginer_id")
    private int engineerId;
    @Column(name = "manager_id")
    private int managerId;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
            }
    )
    @JoinTable(name = "user_complaint",
            joinColumns = @JoinColumn(name = "complaint_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Collection<User> users;


    public Complaint() {
    }

    public Complaint(int id, int active, int resolved, int escalated, int complaint, int userId, int engineerId, int managerId) {
        this.id = id;
        this.active = active;
        this.resolved = resolved;
        this.escalated = escalated;
        this.complaint = complaint;
        this.userId = userId;
        this.engineerId = engineerId;
        this.managerId = managerId;
    }


    public Collection<User> getUsers() {
        return users;
    }

    //convenience method
    private void addUser(User user){

        if(this.users == null){
            this.users = new ArrayList<>();
        }
        this.users.add(user);
    }



    public int getId() {
        return id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }

    public int getEscalated() {
        return escalated;
    }

    public void setEscalated(int escalated) {
        this.escalated = escalated;
    }

    public int getComplaint() {
        return complaint;
    }

    public void setComplaint(int complaint) {
        this.complaint = complaint;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(int engineerId) {
        this.engineerId = engineerId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
