package com.lhx.demo.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class job2 implements BaseJob {
	
	private static Logger _log = LoggerFactory.getLogger(job2.class);  
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.error("Job2开始执行时间:   " + new Date());  
        try {
			for(int i=0;i<10;i++) {
				System.out.println("i:  " +i);
				Thread.sleep(2000);
				 _log.error("Job2执行时间:   " + new Date()); 
			}
			 _log.error("Job2结束时间:   " + new Date());  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
