package com.lhx.demo.controller;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhx.demo.job.BaseJob;

@RestController
@RequestMapping("/quartz")
public class JobController {

	private static Logger log = LoggerFactory.getLogger(JobController.class);  
	
	//加入Qulifier注解，通过名称注入bean
	@Autowired @Qualifier("Scheduler")
	private Scheduler scheduler;
	
	@PostMapping(value="/addjob")
	public void addjob(@RequestParam(value="jobClassName")String jobClassName, 
			@RequestParam(value="jobGroupName")String jobGroupName, 
			@RequestParam(value="cronExpression")String cronExpression) throws Exception
	{			
		addJob(jobClassName, jobGroupName, cronExpression);
	}
	
	
	private void addJob(String jobClassName, String jobGroupName, String cronExpression)throws Exception{
        
        // 启动调度器  
		scheduler.start(); 
		
		//构建job信息
		JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
		
		//表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
            .withSchedule(scheduleBuilder).build();
        
        try {
        	scheduler.scheduleJob(jobDetail, trigger);
            
        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败"+e);
            throw new Exception("创建定时任务失败");
        }
	}
	
	public static BaseJob getClass(String classname) throws Exception 
	{
		Class<?> class1 = Class.forName(classname);
		return (BaseJob)class1.newInstance();
	}
}
