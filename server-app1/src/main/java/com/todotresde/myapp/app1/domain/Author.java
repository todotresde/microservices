package com.todotresde.myapp.app1.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Author.
 */
@Entity
@Table(name = "author")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 10)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 10)
    @Column(name = "lastname", nullable = false)
    private String lastname;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Author name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public Author lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Author author = (Author) o;
        if (author.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), author.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Author{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", lastname='" + getLastname() + "'" +
            "}";
    }
}
