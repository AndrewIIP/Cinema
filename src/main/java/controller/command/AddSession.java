package controller.command;

import model.entity.Session;
import model.entity.User;
import model.services.SessionService;
import model.util.Cons;
import model.util.Languages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddSession implements Command {
    private SessionService sessionService;

    public AddSession(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User curUser = (User) request.getSession().getAttribute(Cons.SESSION_USER);
        Optional<Object> role = Optional.ofNullable(curUser.getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));
        ResourceBundle rsBundle = ResourceBundle.getBundle(Cons.LOCAL_RB_BASE_NAME, locale);

        String outUrlOK = "redirect:" + request.getContextPath() + request.getServletPath() + "/showtimes" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        String outUrlInvalid = "forward:/WEB-INF/common/oper_fail.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());

        sessionService.setDaoLocale(locale);
        String dayIdParam = request.getParameter(Cons.DAY_ID_PARAMETER);
        String movieIdParam = request.getParameter(Cons.MOVIE_ID);
        String timeHours = request.getParameter(Cons.TIME_HOURS_ID);
        String timeMins = request.getParameter(Cons.TIME_MINS_ID);

        if (invalidData(dayIdParam, movieIdParam, timeHours, timeMins)) {
            request.setAttribute(Cons.MESSAGE, rsBundle.getString("wrong.data"));
            return outUrlInvalid;
        }

        int dayId = Integer.parseInt(dayIdParam);
        int movieId = Integer.parseInt(movieIdParam);
        int hours = Integer.parseInt(timeHours);
        int mins = Integer.parseInt(timeMins);

        Time sessionTime = createTime(hours, mins);
        Session session = createLazySession(dayId, movieId, sessionTime);
        sessionService.createSession(session);

        return outUrlOK;
    }

    private Time createTime(int hours, int mins) {
        return new Time((long) hours * 60 * 60 * 1000 + mins * 60 * 1000);
    }

    private Session createLazySession(int dayId, int movieId, Time time) {
        Session session = new Session();
        session.setDayID(dayId);
        session.setMovieID(movieId);
        session.setTime(time);
        return session;
    }

    private boolean invalidData(String dayID, String movID, String hours, String mins) {
        return !Optional.ofNullable(dayID).isPresent() ||
                !Optional.ofNullable(movID).isPresent() ||
                !Optional.ofNullable(hours).isPresent() ||
                !Optional.ofNullable(mins).isPresent() ||
                !dayID.matches("[0-9]+") ||
                !movID.matches("[0-9]+") ||
                !hours.matches("[0-9]+") ||
                !mins.matches("[0-9]+");
    }
}
