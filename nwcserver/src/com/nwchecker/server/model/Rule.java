package com.nwchecker.server.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "rules")
public class Rule {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    /*@JoinTable(name = "rules_languages",
            joinColumns = {
                    @JoinColumn(name = "rules_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "languages_id")})*/
    private List<Language> languageList;

    public Rule(){
        this.name = "N/A";
        this.content = "N/A";
    }

    @Override
    public String toString(){
        return "Rule:{" + id + ", " + name + ", " + content + "}";
    }

    public List<Language> getLanguages() {
        return languageList;
    }

    public void setLanguages(List<Language> languages) {
        this.languageList = languages;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
