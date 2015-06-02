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

/**
 * <h1>Task Data Entity</h1>
 * Entity that represents some data of some Task in DB.
 * <p>
 *
 * @author Roman Zayats
 * @version 1.0
 */
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
        
    //TODO: add ability to save data.
    @Column(name = "rate", nullable = false, columnDefinition="int default 1")
    private int rate;

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

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}
