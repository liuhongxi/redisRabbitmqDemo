package com.lhx.demo.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class job3 implements BaseJob {
	
	private static Logger _log = LoggerFactory.getLogger(job3.class);  
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.error("Job3开始执行时间:   " + new Date());  
        try {
			for(int i=0;i<30;i++) {
				System.out.println("i:  " +i);
				Thread.sleep(3000);
				 _log.error("Job3执行时间:   " + new Date()); 
			}
			 _log.error("Job3结束时间:   " + new Date());  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
