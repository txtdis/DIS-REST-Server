package ph.txtdis.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateTime {

    public static Date toUtilDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        return date == null ? null : toZdt(date).toLocalDate();
    }

    public static ZonedDateTime toZdt(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault());
    }

    public static String formatDate(LocalDate date) {
        return date == null ? "" : date.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    }

    public static String formatMonth(LocalDate date) {
        return date == null ? "" : date.format(DateTimeFormatter.ofPattern("MMM-yyyy"));
    }

    public static String formatDateTime(ZonedDateTime zdt) {
        return zdt == null ? "" : zdt.format(dateTimeFormat());
    }

    private static DateTimeFormatter dateTimeFormat() {
        return DateTimeFormatter.ofPattern("M/d/yyyy h:mma");
    }

    public static ZonedDateTime parseDateTime(String zdt) {
        return zdt == null ? null : ZonedDateTime.parse(zdt, dateTimeFormat());
    }

    public static ZonedDateTime startOfDay(LocalDate date) {
        return date == null ? null : date.atStartOfDay(ZoneId.systemDefault());
    }

    public static ZonedDateTime endOfDay(LocalDate date) {
        return date == null ? null : date.plusDays(1L).atStartOfDay(ZoneId.systemDefault()).minusMinutes(1L);
    }

    public static LocalDate startOfMonth(LocalDate date) {
        return (date == null ? LocalDate.now() : date).withDayOfMonth(1);
    }

    public static LocalDate endOfMonth(LocalDate date) {
        date = startOfMonth(date);
        return date.plusMonths(1).minusDays(1);
    }

    public static BigDecimal toBigDecimal(Double qty) {
        return qty == null ? null : new BigDecimal(qty);
    }
}
