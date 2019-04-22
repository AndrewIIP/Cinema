package controller.command;

import model.dao.exceptions.DAOException;
import model.entity.Session;
import model.entity.User;
import model.services.SessionService;
import model.util.Cons;
import model.util.Languages;
import model.util.LogGen;
import model.util.Role;
import org.apache.log4j.Logger;
import static model.util.LogMsg.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

public class Room implements Command {
    private static Logger log = LogGen.getInstance();
    private SessionService sessionService;

    public Room(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Role> role = Optional.ofNullable(((User) request.getSession().getAttribute(Cons.SESSION_USER)).getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));

        sessionService.setDaoLocale(locale);
        String outUrlOK = "forward:/WEB-INF/" + role.orElse(Role.GUEST).toString() + "/room.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        String outUrlInvalid = "forward:/WEB-INF/common/forbidden.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        String returnPath = outUrlOK;
        String sessionIdParam = request.getParameter(Cons.SESSION_ID_PARAM);
        int sessionId;

        if (Optional.ofNullable(sessionIdParam).isPresent() && sessionIdParam.matches("[0-9]+")) {
            sessionId = Integer.parseInt(sessionIdParam);
            try {
                Session session = sessionService.getSessionById(sessionId);
                request.setAttribute(Cons.SHOW_SESSION, session);
            } catch (DAOException e) {
                e.printStackTrace(); //TODO LOG
                returnPath = outUrlInvalid;
            }
        } else {
            returnPath = outUrlInvalid;
        }

        return returnPath;
    }
}
