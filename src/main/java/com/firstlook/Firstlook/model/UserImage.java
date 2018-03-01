package com.firstlook.Firstlook.model;

import javax.persistence.*;

@Entity
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;
    @ManyToOne
    @JoinColumn(name="userimage_user_id")
    private User user;
    private String percentile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPercentile() {
        return percentile;
    }

    public void setPercentile(String percentile) {
        this.percentile = percentile;
    }
}

