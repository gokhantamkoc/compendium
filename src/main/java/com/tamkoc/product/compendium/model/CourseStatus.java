package com.tamkoc.product.compendium.model;

import javax.persistence.*;

/**
 * @author Gokhan Tamkoc on 08.10.2016.
 */
@Entity
@Table(name = "course_status")
public class CourseStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private String description;

    public CourseStatus() { }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
