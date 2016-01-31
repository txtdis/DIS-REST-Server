package ph.txtdis.service;

import static java.io.File.separator;
import static java.lang.System.getProperty;
import static java.util.Arrays.asList;
import static javax.mail.Folder.READ_ONLY;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.Properties;

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

import ph.txtdis.exception.FailedDownloadException;
import ph.txtdis.exception.NoNewerBackupException;

@Component("backupDownloader")
public class DownloadService {

	@Value("${database.name}")
	private String databaseName;

	@Value("${mail.sender}")
	private String sender;

	@Value("${mail.password}")
	private String password;

	private Date date;

	public Date downloadApproval(Date date) throws NoNewerBackupException, FailedDownloadException {
		this.date = date;
		return saveBackup(message("csv"));
	}

	public Date downloadBackup(Date date) throws NoNewerBackupException, FailedDownloadException {
		this.date = date;
		return saveBackup(message("backup"));
	}

	private Comparator<Message> compareSentDates() {
		return (a, b) -> {
			try {
				return a.getSentDate().compareTo(b.getSentDate());
			} catch (Exception e) {
				return 0;
			}
		};
	}

	private Folder inbox() throws MessagingException {
		Folder inbox = store().getFolder("INBOX");
		inbox.open(READ_ONLY);
		return inbox;
	}

	private Message message(String ext) throws NoNewerBackupException, FailedDownloadException {
		Message[] messages = messages(ext);
		if (messages.length == 0)
			throw new NoNewerBackupException(date);
		return asList(messages).stream().max(compareSentDates()).get();
	}

	private Message[] messages(String ext) throws FailedDownloadException {
		try {
			return inbox().search(searchTerm(ext));
		} catch (Exception e) {
			throw new FailedDownloadException();
		}
	}

	private void saveAttachment(Message m) throws IOException, MessagingException {
		Multipart c = (Multipart) m.getContent();
		MimeBodyPart a = (MimeBodyPart) c.getBodyPart(0);
		a.saveFile(getProperty("user.home") + separator + a.getFileName());
	}

	private Date saveBackup(Message m) throws FailedDownloadException {
		try {
			saveAttachment(m);
			return m.getSentDate();
		} catch (Exception e) {
			throw new FailedDownloadException();
		}
	}

	private AndTerm searchTerm(String ext) {
		return new AndTerm(searchTerms(ext));
	}

	private SearchTerm[] searchTerms(String ext) {
		return new SearchTerm[] { unreadTerm(), sentLaterTerm(), subjectTerm(ext) };
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

	private Session session() {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		return Session.getInstance(props, null);
	}

	private Store store() throws MessagingException {
		Store store = session().getStore();
		store.connect("imap.gmail.com", sender, password);
		return store;
	}

	private SubjectTerm subjectTerm(String ext) {
		return new SubjectTerm(databaseName + "." + ext);
	}

	private SearchTerm unreadTerm() {
		Flags seen = new Flags(Flags.Flag.SEEN);
		return new FlagTerm(seen, false);
	}
}
