package ph.txtdis.service;

import static java.lang.System.getProperty;
import static javax.mail.Session.getInstance;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ph.txtdis.exception.FailedUploadException;

@Component("backupUploader")
public class UploadService {

	@Value("${database.name}")
	private String databaseName;

	@Value("${mail.sender}")
	private String sender;

	@Value("${mail.password}")
	private String password;

	@Value("${mail.recipients}")
	private String[] recipients;

	private String backup;

	private Date date;

	public Date uploadApproval() throws FailedUploadException {
		return uploadFile("csv");
	}

	public Date uploadBackup() throws FailedUploadException {
		return uploadFile("backup");
	}

	private InternetAddress[] addresses() throws AddressException {
		int size = recipients.length;
		InternetAddress[] a = new InternetAddress[size];
		for (int i = 0; i < size; i++)
			a[i] = new InternetAddress(recipients[i]);
		return a;
	}

	private MimeBodyPart attachment() throws IOException, MessagingException {
		MimeBodyPart m = new MimeBodyPart();
		m.attachFile(backup);
		return m;
	}

	private Authenticator authenticator() {
		return new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		};
	}

	private MimeMessage message() throws AddressException, MessagingException, IOException {
		MimeMessage m = new MimeMessage(session());
		m.setFrom(new InternetAddress(sender));
		m.setRecipients(Message.RecipientType.TO, addresses());
		m.setSentDate(date);
		m.setSubject(serverName());
		m.setContent(multipart());
		return m;
	}

	private Multipart multipart() throws MessagingException, IOException {
		return new MimeMultipart(attachment());
	}

	private Properties properties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		return p;
	}

	private String serverName() {
		return databaseName + ".backup";
	}

	private Session session() {
		return getInstance(properties(), authenticator());
	}

	private void upload() throws FailedUploadException {
		try {
			Transport.send(message());
		} catch (Exception e) {
			throw new FailedUploadException();
		}
	}

	private Date uploadFile(String ext) throws FailedUploadException {
		backup = getProperty("user.home") + File.separator + databaseName + "." + ext;
		date = new Date();
		upload();
		return date;
	}
}
