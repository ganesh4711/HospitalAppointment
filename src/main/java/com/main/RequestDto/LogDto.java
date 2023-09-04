package com.main.RequestDto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class LogDto {


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
