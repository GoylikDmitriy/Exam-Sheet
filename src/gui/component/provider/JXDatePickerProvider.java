package gui.component.provider;

import org.jdesktop.swingx.JXDatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Provides with methods to work
 * with JXDatePicker.
 * @author Goylik D.V.
 */
public final class JXDatePickerProvider {
    private static final JXDatePicker jxDatePicker = new JXDatePicker();

    /**
     * Private constructor of JXDatePickerProvider.
     */
    private JXDatePickerProvider() {}

    /**
     * Gives the instance of JXDatePicker.
     * @return instance of JXDatePicker.
     */
    public static JXDatePicker getJXDatePicker() {
        return jxDatePicker;
    }

    /**
     * Gives date of this JXDatePicker.
     * @return date of this JXDatePicker.
     */
    public static Date getDate() {
        return jxDatePicker.getDate();
    }

    /**
     * Gives date in string format of this JXDatePicker.
     * @return date in string format of this JXDatePicker.
     */
    public static String getDateString() {
        String dateString = "";
        Date date = getDate();
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            dateString = dateFormat.format(date);
        }

        return dateString;
    }

    /**
     * Gives info on which year student is.
     * @param group number of group.
     * @return year of study.
     */
    public static int getYearOfStudy(String group) {
        int year = 0;
        int yearOfAdmission = Integer.parseInt(group.substring(group.length() - 2));
        Date date = getDate();
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String academicYearString = getAcademicYear();
            int academicYear = Integer.parseInt(academicYearString.substring(2, 4));
            year = academicYear - yearOfAdmission + 1;
        }

        return year;
    }

    /**
     * Give info on which term student is.
     * @param group number of group.
     * @return current term.
     */
    public static int getTerm(String group) {
        int year = getYearOfStudy(group);
        int term = year * 2;
        Date date = getDate();
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH);
            if (month > 7) {
                term = year * 2 - 1;
            }
        }

        return term;
    }

    /**
     * Gives current academic year if format
     * yyyy/yyyy.
     * @return current academic year.
     */
    public static String getAcademicYear() {
        String yearString = "";
        Date date = getDate();
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            if (month > 7) {
                yearString = year + "/" + (year + 1);
            }
            else {
                yearString = (year - 1) + "/" + year;
            }
        }

        return yearString;
    }
}
