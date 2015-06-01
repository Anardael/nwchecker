package com.nwchecker.server.model;

import javax.persistence.*;


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
    private TypeContest typeContest;

    @ManyToOne(fetch = FetchType.LAZY)
    private Language language;

    public Rule() {
        this.content = "N/A";
    }

    @Override
    public String toString() {
        return "Rule:{" + id + ", " + content + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;
        if (getId() == rule.getId() && getContent().equals(rule.getContent())){
            return true;
        } else {
            return false;
        }
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setLanguageId(int languageId) {
        this.language = new Language();
        language.setId(languageId);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TypeContest getTypeContest() {
        return typeContest;
    }

    public void setTypeContest(TypeContest typeContest) {
        this.typeContest = typeContest;
    }

    public void setTypeId(int typeId) {
        this.typeContest = new TypeContest();
        typeContest.setId(typeId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
