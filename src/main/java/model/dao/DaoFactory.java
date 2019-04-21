package model.dao;

import model.dao.impl.JDBCDaoFactory;

import java.util.Locale;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    private Locale daoLocale = Locale.ENGLISH;

    public abstract UserDao createUserDao();

    public abstract MovieDao createMovieDao();

    public abstract SessionDao createSessionDao();

    public abstract DayDao createDayDao();

    public abstract TicketDao createTicketDao();


    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

    public Locale getDaoLocale() {
        return daoLocale;
    }

    public void setDaoLocale(Locale daoLocale) {
        this.daoLocale = daoLocale;
    }
}
