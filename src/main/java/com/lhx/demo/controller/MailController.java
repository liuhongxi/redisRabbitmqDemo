package com.lhx.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhx.demo.service.MailService;
import net.sf.json.JSONObject;
@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailService;
	
	@GetMapping(value="sendSimpleMail")
	public JSONObject sendSimpleMail(String sendTo, String subject, String content) {
		JSONObject result = new JSONObject();
		boolean flag = mailService.sendSimpleMail(sendTo, subject, content);
		if (flag) {
			result.put("info", "邮件发送成功！");
		}else {
			result.put("info", "邮件发送失败！");
		}
		return result;	
	}
	
	@GetMapping(value="sendHtmlMail")
//	public JSONObject sendHtmlMail(String sendTo, String subject, String content) {
	public JSONObject sendHtmlMail(String sendTo, String subject) {
		JSONObject result = new JSONObject();
		 String content = "<html>\n" +
	                "<body>\n" +
	                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
	                "</body>\n" +
	                "</html>";
		boolean flag = mailService.sendHtmlMail(sendTo, subject, content);
		if (flag) {
			result.put("info", "邮件发送成功！");
		}else {
			result.put("info", "邮件发送失败！");
		}
		return result;	
	}
	
	@GetMapping(value="sendAttachmentsMail")
//	public JSONObject sendAttachmentsMail(String to, String subject, String content, String filePath) {	
	public JSONObject sendAttachmentsMail(String to, String subject, String content) {	
		JSONObject result = new JSONObject();
		String filePath = "D:\\测试哦哦哦.docx";
		boolean flag = mailService.sendAttachmentsMail(to,  subject,  content,  filePath);
		if (flag) {
			result.put("info", "邮件发送成功！");
		}else {
			result.put("info", "邮件发送失败！");
		}
		return result;	
	}
	
	@GetMapping(value="sendInlineResourceMail")
//	public JSONObject sendSimpleMail(String to, String subject, String content, String rscPath, String rscId) {
	public JSONObject sendSimpleMail(String to, String subject, String content, String rscId) {
		String  rscPath = "C:\\Users\\Administrator\\vue_test\\src\\images\\login.jpg";
		JSONObject result = new JSONObject();
		boolean flag = mailService.sendInlineResourceMail(to,  subject,  content,  rscPath,  rscId);
		if (flag) {
			result.put("info", "邮件发送成功！");
		}else {
			result.put("info", "邮件发送失败！");
		}
		return result;
		
	}
}
