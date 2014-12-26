/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "maxMark")
    private int maxMark;

    @Column(name = "description")
    private String description;

    @Column(name = "inputFileName")
    private String inputFileName;

    @Column(name = "outputFileName")
    private String outputFile;

    @Column(name = "memoryLimit")
    private int memoryLimit;

    @Column(name = "timeLimit")
    private int timeLimit;

    @Column(name = "scriptForVerification")
    private String scriptForVerification;

    @Column(name = "forumLink")
    private String forumLink;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "TaskData", joinColumns = {
        @JoinColumn(name = "id_task")}, inverseJoinColumns = {
        @JoinColumn(name = "id")})
    private List<TaskData> inOutData;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "TaskTheoryLink", joinColumns = {
        @JoinColumn(name = "id_task")}, inverseJoinColumns = {
        @JoinColumn(name = "id")})
    private List<TaskTheoryLink> theoryLinks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMar(int maxMar) {
        this.maxMark = maxMar;
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

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
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
