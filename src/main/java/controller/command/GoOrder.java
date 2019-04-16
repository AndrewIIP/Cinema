package controller.command;

import model.dao.exceptions.DAOException;
import model.util.IntBean;
import model.entity.Session;
import model.entity.User;
import model.services.SessionService;
import model.util.Cons;
import model.util.Languages;
import model.util.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

public class GoOrder implements Command {
    private SessionService sessionService;

    public GoOrder(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Optional<Role> role = Optional.ofNullable(((User) request.getSession().getAttribute(Cons.SESSION_USER)).getRole());
        String localeTag = Optional.ofNullable((String)request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));

        sessionService.setDaoLocale(locale);
        String outUrlOK = "forward:/WEB-INF/" + role.orElse(Role.GUEST).toString() + "/process_ticket.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        String outUrlInvalid = "forward:/WEB-INF/common/forbidden.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        String returnPath = outUrlOK;
        String sessionIdParam = request.getParameter(Cons.SESSION_ID_PARAM);
        String placeNumber = request.getParameter(Cons.PLACE);
        int sessionIdParsed;
        IntBean placeNumParsed = new IntBean();

        if (Optional.ofNullable(sessionIdParam).isPresent() && sessionIdParam.matches("[0-9]+") &&
                Optional.ofNullable(placeNumber).isPresent() && placeNumber.matches("[0-9]+")) {
            sessionIdParsed = Integer.parseInt(sessionIdParam);
            placeNumParsed.setId(Integer.parseInt(placeNumber));
            try {
                Session session = sessionService.getSessionById(sessionIdParsed);
                request.setAttribute(Cons.SHOW_SESSION, session);
                request.setAttribute(Cons.PLACE, placeNumParsed);
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
