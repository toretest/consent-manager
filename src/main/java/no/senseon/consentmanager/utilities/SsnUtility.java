package no.senseon.consentmanager.utilities;

import java.util.Date;

import static no.senseon.consentmanager.utilities.CalendarUtils.validateControlDigitsModulo11;

public class SsnUtility {

    private SsnUtility(){

    }

    public static boolean validateSsn(String ssn) {
        return validateSsn(ssn, false);
    }

    public static boolean validateSsn(String ssn, boolean allowTemporaryNumbers) {
        if (ssn != null) {
            if (ssn.length() != 11) {
                return false;
            }
            Date birthDate = CalendarUtils.birthDateFromSsn(ssn);
            char firstDigit = ssn.charAt(0);
            int[] weight1 = {3, 7, 6, 1, 8, 9, 4, 5, 2, 1, 0};
            int[] weight2 = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2, 1};
            if ((!allowTemporaryNumbers && firstDigit > '3') || birthDate == null || !validateControlDigitsModulo11(ssn, weight1)
                    || !validateControlDigitsModulo11(ssn, weight2)) {
                return false;
            }
        }
        return true;
    }


}
