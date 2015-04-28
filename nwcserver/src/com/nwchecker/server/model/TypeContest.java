package com.nwchecker.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type_contest")
public class TypeContest {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "typeContest")
    private List<Rule> rules;

    public TypeContest(){
        this.name = "N/A";
    }

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

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public void addRule(Rule rule){
        if(rules == null) {
            rules = new ArrayList<Rule>();
        }
        this.rules.add(rule);
    }
}
