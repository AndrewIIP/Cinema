package service;

public enum Cons {
    CUR_LANG("curLang");

    private String value;
    Cons(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
