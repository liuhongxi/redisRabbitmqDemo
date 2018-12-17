package com.lhx.demo.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class job1 implements  BaseJob {

	private static Logger _log = LoggerFactory.getLogger(job1.class);  
    
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.error("Job1开始执行时间:   " + new Date());  
        try {
			for(int i=0;i<10;i++) {
				System.out.println("i:  " +i);
				Thread.sleep(1000);
				 _log.error("Job1执行时间:   " + new Date()); 
			}
			 _log.error("Job1结束时间:   " + new Date());  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
