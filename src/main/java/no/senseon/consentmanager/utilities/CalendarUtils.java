package no.senseon.consentmanager.utilities;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
    private CalendarUtils() {
    }

    public static Date birthDateFromSsn(String ssn) {
        if (ssn == null || ssn.length() != 11) {
            return null;
        }
        if ("0000".equals(ssn.substring(0, 4))) {
            int year = Integer.parseInt(ssn.substring(4, 6));
            int thisYear = CalendarUtils.getYear();
            int century = 1900;
            if (Integer.parseInt(Integer.toString(thisYear).substring(2, 4)) >= year) {
                century = 2000;
            }
            year += century;
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setLenient(false);
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            return calendar.getTime();
        }

        for (int i = 0; i < ssn.length(); i++) {
            if (!Character.isDigit(ssn.charAt(i))) {
                return null;
            }
        }
        int day = Integer.parseInt(ssn.substring(0, 2));
        if (day > 31) {
            day -= 40;
        }
        int month = Integer.parseInt(ssn.substring(2, 4));
        int yearInCentury = Integer.parseInt(ssn.substring(4, 6));
        int individualNumber = Integer.parseInt(ssn.substring(6, 9));
        int century;
        if (individualNumber >= 500 && individualNumber <= 749 && yearInCentury > 54) {
            century = 1800;
        } else if (individualNumber >= 0 && individualNumber <= 499) {
            century = 1900;
        } else if (individualNumber >= 900 && individualNumber <= 999 && yearInCentury > 39) {
            century = 1900;
        } else if (individualNumber >= 500 && individualNumber <= 999 && yearInCentury < 40) {
            century = 2000;
        } else {
            return null;
        }


        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setLenient(false);
        calendar.set(Calendar.YEAR, century + yearInCentury);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        try {
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean validateControlDigitsModulo11(String num, int[] weight) {

        if (num != null) {
            if (num.length() == weight.length) {
                int sum = 0;
                for (int i = 0; i < num.length(); i++) {
                    char ch = num.charAt(i);
                    if (!Character.isDigit(ch)) {
                        return false;
                    }
                    int digit = Character.getNumericValue(ch);
                    sum += digit * weight[i];
                }
                return ((sum % 11) == 0);
            }
        }

        return false;
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }


}
