package com.tamkoc.product.compendium.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Gokhan Tamkoc on 08.10.2016.
 */
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private CourseStatus courseStatus;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    private String name;

    private String description;

    private Timestamp creationDate;

    private Timestamp lastUpdateDate;

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public void setCourseStatus(CourseStatus domainStatus) {
        this.courseStatus = domainStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
