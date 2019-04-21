package model.util;

public interface Cons {
    String CUR_LANG = "curLang";
    String MAIL_REGEX = "[a-zA-Z_\\-]{1,32}@([a-zA-Z]{1,10}\\.){1,5}[a-zA-Z]{1,10}";
    String USERNAME_REGEX = "[^\\s`~!#?/@.:;]{1,32}";
    String PASSWORD_REGEX = "[^\\s]{6,32}";
    String LOCAL_RB_BASE_NAME = "lang";
    String USERNAME_PARAM = "username";
    String PASSWORD_PARAM = "password";
    String CONFIRM_PASS_PARAM = "confirmPassword";
    String EMAIL_PARAM = "email";
    String CUR_REQ_URL = "curReqURL";
    String SESSION_USER = "sessionUser";
    String CONTEXT_USERS_LIST = "usersList";
    String MOVIES_BEAN = "moviesBean";
    String DAY_ID_PARAMETER = "day";
    String SESSION_ID_PARAM = "sessionId";
    String SHOW_SESSION = "showSession";
    String PLACE = "place";
    String MESSAGE = "msg";
    String MESSAGE2 = "msg2";
    String TICKET_LIST = "tickets";
    String TICKET_ID = "ticketId";
    String MOVIE_ID = "movieId";
    String TIME_HOURS_ID = "hours";
    String TIME_MINS_ID = "mins";
    String SERVLET_CONTEXT = "servletContext";
    String MOVIE_NAME_ENG_PARAM = "nameEng";
    String MOVIE_NAME_UKR_PARAM = "nameUkr";

}
