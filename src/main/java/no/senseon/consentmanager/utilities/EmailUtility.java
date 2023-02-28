package no.senseon.consentmanager.utilities;

import java.util.StringTokenizer;

public class EmailUtility {
    private static char[] legalLocalPartChars = {'+', '-', '.', '_'};
    private static char[] legalDomainPartChars = {'@', '-', '.'};

    private EmailUtility() {
    }

    public static boolean validateEmailAddress(String value) {
        if (value != null && !"".equals(value)) {
            int at = 0;

            char[] input = value.toCharArray();
            for (int i = 0; i < input.length; i++) {
                if (input[i] == '@') {
                    at++;
                }
            }

            if (at != 1) {
                return false;
            }

            StringTokenizer tokens = new StringTokenizer(value, "@");
            if (tokens.countTokens() != 2) {
                return false;
            }

            char[] localpart = tokens.nextToken().toCharArray();
            if(localpart[localpart.length -1] == '.') {
                return false;
            }
            for (int i = 0; i < localpart.length; i++) {
                if (!Character.isLetterOrDigit(localpart[i]) && (!validateChars(localpart[i], legalLocalPartChars))) {
                        return false;
                }

            }
            String domain = tokens.nextToken();
            char[] domainpart = domain.toCharArray();
            if (domainpart.length < 4) {
                return false;
            }

            if (!Character.isLetterOrDigit(domainpart[0])
                    || !Character.isLetter(domainpart[domainpart.length - 1])) {
                return false;
            }

            int fullstop = 0;
            for (int i = 0; i < domainpart.length; i++) {
                if (domainpart[i] == '.') {
                    fullstop++;
                }
            }

            if (fullstop < 1) {
                return false;
            }

            if ((fullstop > 1) && domain.contains("..")) {
                return false;
            }

            for (int i = 0; i < domainpart.length; i++) {
                if (
                        !Character.isLetterOrDigit(domainpart[i]) &&
                                !validateChars(domainpart[i], legalDomainPartChars)) {
                    return false;
                }

            }
        }
        return true;
    }

    public static boolean validateChars(char c, char[] legalChars) {
        for (int i = 0; i < legalChars.length; i++) {
            if (c == legalChars[i]) {
                return true;
            }
        }
        return false;
    }

}
