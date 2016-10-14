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
}
