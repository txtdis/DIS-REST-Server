package ph.txtdis.sync;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activity.InvalidActivityException;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.AndTerm;
import javax.mail.search.DateTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ph.txtdis.exception.FailedBackupDownloadException;

@Component("backupDownloader")
public class BackupDownloader {

	@Value("${database.name}")
	private String databaseName;

	@Value("${mail.sender}")
	private String sender;

	@Value("${mail.password}")
	private String password;

	private Date date;

	public Date download(Date date) throws FailedBackupDownloadException {
		try {
			this.date = date;
			Message message = getMessage();
			saveAttachment(message);
			return message.getSentDate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FailedBackupDownloadException();
		}
	}

	private Message getMessage() throws Exception {
		Message[] messages = inbox().search(searchTerm());
		if (messages.length == 0)
			throw new InvalidActivityException();
		return messages[0];
	}

	private Folder inbox() throws Exception {
		Folder inbox = store().getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
		return inbox;
	}

	private void saveAttachment(Message message) throws Exception {
		Multipart content = (Multipart) message.getContent();
		MimeBodyPart attachment = (MimeBodyPart) content.getBodyPart(0);
		attachment.saveFile(System.getProperty("user.home") + File.separator + attachment.getFileName());
	}

	private AndTerm searchTerm() {
		return new AndTerm(searchTerms());
	}

	private SearchTerm[] searchTerms() {
		return new SearchTerm[] { unreadTerm(), sentLaterTerm(), serverBackupTerm() };
	}

	private SearchTerm sentLaterTerm() {
		return new DateTerm(DateTerm.GT, date) {

			private static final long serialVersionUID = 1971028183841474615L;

			@Override
			public boolean match(Message msg) {
				try {
					if (msg.getSentDate().compareTo(date) > 0)
						return true;
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				return false;
			}
		};
	}

	private SubjectTerm serverBackupTerm() {
		return new SubjectTerm(databaseName + ".backup");
	}

	private Session session() {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		return Session.getInstance(props, null);
	}

	private Store store() throws Exception {
		Store store = session().getStore();
		store.connect("imap.gmail.com", sender, password);
		return store;
	}

	private SearchTerm unreadTerm() {
		Flags seen = new Flags(Flags.Flag.SEEN);
		return new FlagTerm(seen, false);
	}
}
