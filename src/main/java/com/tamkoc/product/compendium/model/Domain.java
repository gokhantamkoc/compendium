package com.tamkoc.product.compendium.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Gokhan Tamkoc on 08.10.2016.
 */

@Entity
@Table(name="domain")
public class Domain {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "domain_status_id")
    private DomainStatus domainStatus;

    @Column(length = 500)
    @NotEmpty(message = "Can not be empty!")
    @Size(max = 500, message = "Can have 500 max characters!")
    private String name;

    @Column(length = 500)
    @NotEmpty(message = "Can not be empty!")
    @Size(max = 500, message = "Can have 500 max characters!")
    private String description;

    private Timestamp creationDate;

    private Timestamp lastUpdateDate;

    public Domain() {
        this.domainStatus = new DomainStatus();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DomainStatus getDomainStatus() {
        return domainStatus;
    }

    public void setDomainStatus(DomainStatus domainStatus) {
        this.domainStatus = domainStatus;
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
