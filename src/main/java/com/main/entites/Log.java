package com.main.entites;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    private String tableName;
    private Integer recordId;
    private String change;
    private Integer updatedBy;

   
    private Date timestamp;


	public Integer getLogId() {
		return logId;
	}


	public void setLogId(Integer logId) {
		this.logId = logId;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public Integer getRecordId() {
		return recordId;
	}


	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}


	public String getChange() {
		return change;
	}


	public void setChange(String change) {
		this.change = change;
	}


	public Integer getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

   
    
}
