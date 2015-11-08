package ph.txtdis.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class DateTimeUtils {

	public static String dateTimeToFileName(ZonedDateTime dateTime) {
		return dateTime == null ? "" : dateTime.format(timestampFileFormat());
	}

	public static String dateToFileName(LocalDate date) {
		return date == null ? "" : date.format(DateTimeFormatter.ofPattern("M.d.yy"));
	}

	public static StandardPBEStringEncryptor encryptor() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("I'mAdmin4txtDIS@PostgreSQL");
		return encryptor;
	}

	public static ZonedDateTime endOfDay(LocalDate date) {
		return date == null ? null : date.plusDays(1L).atStartOfDay(ZoneId.systemDefault());
	}

	public static LocalDate endOfMonth(LocalDate date) {
		return startOfMonth(date).plusMonths(1L).minusDays(1L);
	}

	public static String formatDate(LocalDate date) {
		return date == null ? "" : date.format(DateTimeFormatter.ofPattern("M/d/yy"));
	}

	public static String formatZonedDateTime(ZonedDateTime zdt) {
		return zdt == null ? "" : zdt.format(timestampFormat());
	}

	public static String getEncoded(String text) throws IOException {
		Properties props = new EncryptableProperties(encryptor());
		props.load(DateTimeUtils.class.getResourceAsStream("/config/application.properties"));
		return props.getProperty("spring.datasource." + text);
	}

	public static Date localToDate(LocalDate localDate) {
		return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static ZonedDateTime parseZonedDateTime(String zdt) {
		return zdt == null ? null : ZonedDateTime.parse(zdt, timestampFormat());
	}

	public static ZonedDateTime startOfDay(LocalDate date) {
		return date == null ? null : date.atStartOfDay(ZoneId.systemDefault());
	}

	public static LocalDate startOfMonth(LocalDate date) {
		if (date == null)
			date = LocalDate.now();
		return LocalDate.of(date.getYear(), date.getMonthValue(), 1);
	}

	public static String toFileName(ZonedDateTime dateTime) {
		return dateTime == null ? "" : dateTime.format(timestampFileFormat());
	}

	private static DateTimeFormatter timestampFileFormat() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd@hh.mma");
	}

	private static DateTimeFormatter timestampFormat() {
		return DateTimeFormatter.ofPattern("M/d/yy h:mma");
	}
}
