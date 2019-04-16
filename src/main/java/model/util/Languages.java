package model.util;

import java.util.Arrays;
import java.util.Optional;

public enum Languages {
    ENG("en"),
    UKR("uk");

    private String langTag;

    Languages(String langTag){
        this.langTag = langTag;
    }

    public String getLangTag(){
        return langTag;
    }

    public static String isLangOrGetDefault(String tag){
        Languages lang = Arrays.stream(Languages.values())
                                    .filter(a -> a.getLangTag().equals(tag))
                                    .findFirst()
                                    .orElse(Languages.ENG);
        return lang.getLangTag();
    }
}
