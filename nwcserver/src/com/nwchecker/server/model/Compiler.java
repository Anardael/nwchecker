package com.nwchecker.server.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.nwchecker.server.json.JsonViews;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.List;

/**
 * <h1>Compiler Entity</h1>
 * Entity that represents some Compiler in DB.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-02-22
 */
@Entity
@Table(name = "compiler")
public class Compiler {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    @Pattern(regexp = "[0-9a-zA-Z/.'()-]{0,}")
    @NotEmpty
    @Size(max = 100)
    @JsonView(JsonViews.TaskPassView.class)
    @JsonProperty("compiler")
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "compiler")
    private List<TaskPass> taskPass;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object compiler) {
        return (this.name.equals(((Compiler) compiler).getName()));
    }
}
