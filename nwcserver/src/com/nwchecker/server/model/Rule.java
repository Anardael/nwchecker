package com.nwchecker.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "rules")
public class Rule {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "content", length = 1024)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Type type;

    // without language??? TODO
    @ManyToOne(fetch = FetchType.LAZY)
    private Language language;

    public Rule(){
        this.content = "N/A";
        //this.type = new Type();
        //this.language = new Language();
    }

    @Override
    public String toString(){
        return "Rule:{" + id + ", " + type.getName() + ", " + content + ", " + language.getTag() +"}";
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setLanguageId(int languageId){
        this.language = new Language();
        language.setId(languageId);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTypeId(int typeId){
        this.type = new Type();
        type.setId(typeId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
