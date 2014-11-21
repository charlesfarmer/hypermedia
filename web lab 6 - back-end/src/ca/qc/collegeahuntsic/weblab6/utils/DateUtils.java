
package ca.qc.collegeahuntsic.weblab6.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
    private static final String FORMAT_DATE = "yyyy-MM-dd";

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DateUtils.FORMAT_DATE);

    static {
        DateUtils.SIMPLE_DATE_FORMAT.setLenient(false);
    }

    private DateUtils() {
        super();
    }

    public static Timestamp timestampValue(String date) throws ParseException {
        final Date dateFormatee = DateUtils.SIMPLE_DATE_FORMAT.parse(date);
        final Timestamp timestamp = new Timestamp(dateFormatee.getTime());
        return timestamp;
    }

    public static String stringValue(Timestamp timestamp) {
        final Date date = new Date(timestamp.getTime());
        final String dateFormatee = DateUtils.SIMPLE_DATE_FORMAT.format(date);
        return dateFormatee;
    }

    /*
     * Retourne la fin de la journée précédente
     */
    public static Date getStartDate(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR,
            calendar.getActualMaximum(Calendar.HOUR));
        calendar.set(Calendar.MINUTE,
            calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,
            calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND,
            calendar.getActualMaximum(Calendar.MILLISECOND));
        calendar.add(Calendar.DATE,
            -1);
        return calendar.getTime();
    }

    /*
     * Retourne le début de la journée suivante
     */
    public static Date getEndDate(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR,
            calendar.getActualMinimum(Calendar.HOUR));
        calendar.set(Calendar.MINUTE,
            calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,
            calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND,
            calendar.getActualMinimum(Calendar.MILLISECOND));
        calendar.add(Calendar.DATE,
            1);
        return calendar.getTime();
    }
}
