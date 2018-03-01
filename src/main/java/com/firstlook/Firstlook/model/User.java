package com.firstlook.Firstlook.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String nick;
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    private boolean gender;

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNick() {
        return nick;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location locationId) {
        this.location = location;
    }
}
