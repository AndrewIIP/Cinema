package model.util;

import java.util.ResourceBundle;

public class Regexes {
    public static final String MAIL_REGEX;
    public static final String USERNAME_REGEX;
    public static final String PASSWORD_REGEX;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexes");
        MAIL_REGEX = resourceBundle.getString("mail.regexp");
        USERNAME_REGEX = resourceBundle.getString("username.regexp");
        PASSWORD_REGEX = resourceBundle.getString("password.regexp");
    }
}
