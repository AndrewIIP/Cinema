package model.dao;

import java.util.Locale;

public abstract class AbstractDao {
    private Locale locale = Locale.ENGLISH;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
