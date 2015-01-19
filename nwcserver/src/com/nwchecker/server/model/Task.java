/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @Column(name = "title")
    @Pattern(regexp = "[0-9a-zA-Zа-яіїєА-ЯІЇЄ ]{0,}")
    @NotEmpty
    @Size(max = 100)
    private String title;

    @Column(name = "complexity")
    @Max(100)
    @Min(0)
    private int complexity;

    @Column(name = "rate")
    @NotNull
    @Max(100)
    @Min(0)
    private int rate;

    @Column(name = "description")
    @NotEmpty
    private String description;

    @Column(name = "inputFileName")
    @NotEmpty
    @Size(max = 60)
    private String inputFileName;

    @Column(name = "outputFileName")
    @NotEmpty
    @Size(max = 60)
    private String outputFileName;

    @Min(0)
    @Column(name = "memoryLimit")
    private int memoryLimit;

    @Min(0)
    @Column(name = "timeLimit")
    private int timeLimit;

    @Column(name = "scriptForVerification")
    private String scriptForVerification;

    @Column(name = "forumLink")
    //@Pattern(regexp="https?://.*")
    @Size(max = 500)
    private String forumLink;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private List<TaskData> inOutData;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private List<TaskTheoryLink> theoryLinks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int difficulty) {
        this.complexity = difficulty;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int maxMar) {
        this.rate = maxMar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFile) {
        this.outputFileName = outputFile;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getScriptForVerification() {
        return scriptForVerification;
    }

    public void setScriptForVerification(String scriptForVerification) {
        this.scriptForVerification = scriptForVerification;
    }

    public List<TaskData> getInOutData() {
        return inOutData;
    }

    public void setInOutData(List<TaskData> inputData) {
        this.inOutData = inputData;
    }

    public String getForumLink() {
        return forumLink;
    }

    public void setFoumLink(String foumLink) {
        this.forumLink = foumLink;
    }

    public List<TaskTheoryLink> getTheoryLinks() {
        return theoryLinks;
    }

    public void setTheoryLinks(List<TaskTheoryLink> theoryLinks) {
        this.theoryLinks = theoryLinks;
    }

}
