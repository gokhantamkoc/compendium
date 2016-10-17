package com.tamkoc.product.compendium.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 14.10.2016.
 */
@Entity
@Table(name = "path")
public class Path {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "status_id")
    private PathStatus pathStatus;

    @NotNull
    @OneToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    @Column(length = 500)
    @NotEmpty(message = "Can not be empty!")
    @Size(max = 500, message = "Can have 500 max characters!")
    private String name;

    @Column(length = 500)
    @NotEmpty(message = "Can not be empty!")
    @Size(max = 500, message = "Can have 500 max characters!")
    private String description;

    @OneToMany(mappedBy="course", cascade=CascadeType.ALL)
    private transient List<Course> courseList;

    private Timestamp creationDate;

    private Timestamp lastUpdateDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PathStatus getPathStatus() {
        return pathStatus;
    }

    public void setPathStatus(PathStatus pathStatus) {
        this.pathStatus = pathStatus;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
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

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Course> getCourseList() {
        return this.courseList;
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
