package com.lhx.demo.service;

public interface MailService {
	/**
	 * 发送普通邮件
	 * @param to
	 * @param subject
	 * @param content
	 */
	public boolean sendSimpleMail(String to, String subject, String content);
	
	/**
	 * 发送html格式邮件
	 * @param to
	 * @param subject
	 * @param content
	 */
	public boolean sendHtmlMail(String to, String subject, String content);

	/**
	 * 发送带附件邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 */
	public boolean sendAttachmentsMail(String to, String subject, String content, String filePath);

	/**
	 * 发送正文中有静态资源（图片）的邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param rscPath
	 * @param rscId
	 */
	public boolean sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
