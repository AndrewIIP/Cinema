package controller.command;

import model.dao.exceptions.DAOException;
import model.entity.Ticket;
import model.services.TicketService;
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
import java.util.ResourceBundle;

public class PurchTicket implements Command {
    private TicketService ticketService;

    public PurchTicket(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User sessionUser = (User) request.getSession().getAttribute(Cons.SESSION_USER);
        Optional<Role> role = Optional.ofNullable((sessionUser).getRole());
        String localeTag = Optional.ofNullable((String)request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));
        ResourceBundle rsBundle = ResourceBundle.getBundle(Cons.LOCAL_RB_BASE_NAME, locale);

        ticketService.setDaoLocale(locale);
        String outUrlOK = "forward:/WEB-INF/common/oper_success.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        String outUrlInvalid = "forward:/WEB-INF/common/oper_fail.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());

        String placeParam = request.getParameter(Cons.PLACE);
        String sessionIdParam = request.getParameter(Cons.SESSION_ID_PARAM);

        if(invalidInput(placeParam, sessionIdParam)){
            //set attribute Bean with fail message
            request.setAttribute(Cons.MESSAGE, rsBundle.getString("wrong.data"));
            return outUrlInvalid;
        }

        Ticket ticketToSave = constructRawTicket(placeParam, sessionIdParam, sessionUser);

        try{
            ticketService.createTicket(ticketToSave);
        } catch (DAOException e) {
            e.printStackTrace(); //TODO process
            //set attribute Bean with fail message
            request.setAttribute(Cons.MESSAGE, rsBundle.getString("ticket.purch.already.bought"));
            return outUrlInvalid;
        }
        request.setAttribute(Cons.MESSAGE, rsBundle.getString("success.bought"));
        return outUrlOK;
    }

    private boolean invalidInput(String place, String session){
        return  !Optional.ofNullable(place).isPresent() ||
                !Optional.ofNullable(session).isPresent() ||
                !place.matches("[0-9]+") ||
                !session.matches("[0-9]+");

    }

    private Ticket constructRawTicket(String place, String sessionID, User user){
        Ticket ticket = new Ticket();

        int userID = user.getId();
        int sessionIDNumber = Integer.parseInt(sessionID);
        int placeNumber = Integer.parseInt(place);

        ticket.setUserID(userID);
        ticket.setSessionID(sessionIDNumber);
        ticket.setPlace(placeNumber);

        return ticket;
    }
}
