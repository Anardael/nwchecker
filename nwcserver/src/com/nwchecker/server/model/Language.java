package com.nwchecker.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "tag")
    private String tag;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "languageList")
    /*@JoinTable(name = "rules_languages",
            joinColumns = {
                    @JoinColumn(name = "languages_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "rules_id")})*/
    private List<Rule> ruleList;

    public Language(){
        this.tag = "N/A";
        this.ruleList = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "Language:{" + id + ", " + tag + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Rule> getRules() {
        return ruleList;
    }

    public void setRules(List<Rule> rules) {
        this.ruleList = rules;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
