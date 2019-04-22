package controller.command;

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
import java.util.ResourceBundle;

public class RemoveSession implements Command {
    private static Logger log = LogGen.getInstance();
    private SessionService sessionService;

    public RemoveSession(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User sessionUser = (User) request.getSession().getAttribute(Cons.SESSION_USER);
        Optional<Role> role = Optional.ofNullable((sessionUser).getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));
        ResourceBundle rsBundle = ResourceBundle.getBundle(Cons.LOCAL_RB_BASE_NAME, locale);

        sessionService.setDaoLocale(locale);
        String outUrlOK = "redirect:" + request.getContextPath() + request.getServletPath() + "/showtimes" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        String outUrlInvalid = "forward:/WEB-INF/common/oper_fail.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());

        String sessionIdParam = request.getParameter(Cons.SESSION_ID_PARAM);
        int sessionId;

        if (invalidInput(sessionIdParam)) {
            request.setAttribute(Cons.MESSAGE, rsBundle.getString("del.ticket.wrong.input"));
            return outUrlInvalid;
        } else {
            sessionId = Integer.parseInt(sessionIdParam);
        }

        sessionService.removeSessionById(sessionId);
        return outUrlOK;
    }

    private boolean invalidInput(String ticketID) {
        return !Optional.ofNullable(ticketID).isPresent() || !ticketID.matches("[0-9]+");
    }
}
