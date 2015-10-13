package ph.txtdis.sync;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ph.txtdis.exception.FailedBackupUploadException;

@Component("backupUploader")
public class BackupUploader {

	@Value("${database.name}")
	private String databaseName;

	@Value("${mail.sender}")
	private String sender;

	@Value("${mail.password}")
	private String password;

	@Value("${mail.recipients}")
	private String[] recipients;

	private String backup;

	public void upload() throws FailedBackupUploadException {
		try {
			backup = System.getProperty("user.home") + File.separator + databaseName + ".backup";
			Transport.send(getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedBackupUploadException();
		}
	}

	private InternetAddress[] getAddresses() throws Exception {
		int size = recipients.length;
		InternetAddress[] addresses = new InternetAddress[size];
		for (int i = 0; i < size; i++)
			addresses[i] = new InternetAddress(recipients[i]);
		return addresses;
	}

	private MimeBodyPart getAttachment() throws Exception {
		MimeBodyPart m = new MimeBodyPart();
		m.attachFile(backup);
		return m;
	}

	private Authenticator getAuthenticator() {
		return new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		};
	}

	private MimeMessage getMessage() throws Exception {
		MimeMessage m = new MimeMessage(getSession());
		m.setFrom(new InternetAddress(sender));
		m.setRecipients(Message.RecipientType.TO, getAddresses());
		m.setSentDate(new Date());
		m.setSubject(getServerName());
		m.setContent(getMultipart());
		return m;
	}

	private Multipart getMultipart() throws Exception {
		return new MimeMultipart(getAttachment());
	}

	private Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		return p;
	}

	private String getServerName() {
		return databaseName + ".backup";
	}

	private Session getSession() {
		return Session.getInstance(getProperties(), getAuthenticator());
	}
}
