package DAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEmail {
    private static final String EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final Pattern pattern;
    private Matcher matcher;

    public CheckEmail() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
    	CheckEmail validator = new CheckEmail();

        String email1 = "test@example.com";
        String email2 = "invalid_email";
        
        System.out.println(email1 + " is valid: " + validator.validateEmail(email1));
        System.out.println(email2 + " is valid: " + validator.validateEmail(email2));
    }
}
