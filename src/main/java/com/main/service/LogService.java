package com.main.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entites.Log;
import com.main.repos.LogRepository;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;

	public void logUserCreation(Integer userId) {
		Log l = new Log();
		l.setTableName("users");
		l.setRecordId(userId);
		l.setChange("created");
		l.setTimestamp(new Date()); // You can use appropriate timestamp logic here
		l.setUpdatedBy(userId);
		logRepository.save(l);
	}
	public void logUserDelete(Integer userId) {
        Log l = new Log();
        l.setTableName("users");
        l.setRecordId(userId);
        l.setChange("Deactivated");
        l.setTimestamp(new Date()); // You can use appropriate timestamp logic here
       l.setUpdatedBy(userId);

        logRepository.save(l);
    }
	public void logUserDelete(Integer userId, Integer dltBy) {
        Log l = new Log();
        l.setTableName("users");
        l.setRecordId(userId);
        l.setChange("Deactivate");
        l.setTimestamp(new Date()); // You can use appropriate timestamp logic here
       l.setUpdatedBy(dltBy);

        logRepository.save(l);
    }
}
