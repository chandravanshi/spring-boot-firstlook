package com.firstlook.Firstlook.model;

import javax.persistence.*;

@Entity
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="source_user")
    User sourceUSer;
    @ManyToOne
    @JoinColumn(name="user_rating_target_user_Image")
    UserImage targetUSerImage;
    boolean rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRate() {
        return rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    public User getSourceUSer() {
        return sourceUSer;
    }

    public void setSourceUSer(User sourceUSer) {
        this.sourceUSer = sourceUSer;
    }

    public UserImage getTargetUSerImage() {
        return targetUSerImage;
    }

    public void setTargetUSerImage(UserImage targetUSerImage) {
        this.targetUSerImage = targetUSerImage;
    }
}
