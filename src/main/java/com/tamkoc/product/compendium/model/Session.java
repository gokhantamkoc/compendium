package com.tamkoc.product.compendium.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Gokhan Tamkoc on 18.10.2016.
 */
@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "status_id")
    private SessionStatus sessionStatus;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @NotNull
    private int orderNumber;

    @NotEmpty
    @Column(length = 400)
    private String name;

    @NotEmpty
    @Column(length = 1000)
    private String introduction;

    @NotEmpty
    @Column(length = 4000)
    private String body;

    @Column(length = 1000)
    private String summary;

    private Timestamp creationDate;

    private Timestamp lastUpdateDate;

    public Session() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @PrePersist
     public void beforeSave() {
        java.util.Date currentDate = new java.util.Date();
        this.setCreationDate(new Timestamp(currentDate.getTime()));
        this.setLastUpdateDate(new Timestamp(currentDate.getTime()));
    }

    @PreUpdate
    public void beforeUpdate() {
        java.util.Date currentDate = new java.util.Date();
        this.setLastUpdateDate(new Timestamp(currentDate.getTime()));
    }
}
