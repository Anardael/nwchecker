package com.nwchecker.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "rules")
public class Rule {

    public static enum Type {
        ICM, POP
    }

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    //  delete TODO
    @Column(name = "type")
    private Type type;

    @Column(name = "content")
    private String content;

    // without language??? TODO
    @ManyToOne(/*cascade = CascadeType.PERSIST, */fetch = FetchType.LAZY)
    private Language language;

    public Rule(){
        this.type = Type.ICM;
        this.content = "N/A";
        //this.language = new Language();
    }

    @Override
    public String toString(){
        return "Rule:{" + id + ", " + type + ", " + content + "}";
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
