/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Entity
@Table(name = "TaskData")
public class TaskData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "task_id")
    private Task task;

    @Lob
    @Column(name = "inputData", length = 20971520)
    private byte[] inputData;

    @Lob
    @Column(name = "outputData", length = 20971520)
    private byte[] outputData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task id_task) {
        this.task = id_task;
    }

    public byte[] getInputData() {
        return inputData;
    }

    public void setInputData(byte[] inputData) {
        this.inputData = inputData;
    }

    public byte[] getOutputData() {
        return outputData;
    }

    public void setOutputData(byte[] outputData) {
        this.outputData = outputData;
    }

    public TaskData() {
    }

    public TaskData(byte[] inputData, byte[] outputData) {
        this.inputData = inputData;
        this.outputData = outputData;
    }

}
