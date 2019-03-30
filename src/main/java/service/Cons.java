package service;

public enum Cons {
    CUR_LANG("curLang"),
    MAIL_REGEX("[a-zA-Z_\\-]{1,32}@([a-zA-Z]{1,10}\\.){1,5}[a-zA-Z]{1,10}"),
    USERNAME_REGEX("[^\\s`~!#?/@.:;]{1,32}"),
    LOCAL_RB_BASE_NAME("lang"),
    LOGIN_USERNAME_PARAM("username"),
    LOGIN_PASSWORD_PARAM("password");

    private String value;
    Cons(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
