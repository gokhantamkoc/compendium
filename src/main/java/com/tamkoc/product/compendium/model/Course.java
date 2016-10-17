package com.tamkoc.product.compendium.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @OneToOne
    @JoinColumn(name = "status_id")
    private CourseStatus courseStatus;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "path_id")
    private Path path;

    private String name;

    private Timestamp creationDate;

    private Timestamp lastUpdateDate;

    public Course() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
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
