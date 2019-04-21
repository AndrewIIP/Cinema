package controller.command;

import model.entity.Ticket;
import model.entity.User;
import model.services.TicketService;
import model.util.Cons;
import model.util.Languages;
import model.util.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class RemoveTicket implements Command {
    private TicketService ticketService;

    public RemoveTicket(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User sessionUser = (User) request.getSession().getAttribute(Cons.SESSION_USER);
        Optional<Role> role = Optional.ofNullable((sessionUser).getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));
        ResourceBundle rsBundle = ResourceBundle.getBundle(Cons.LOCAL_RB_BASE_NAME, locale);

        ticketService.setDaoLocale(locale);
        String outUrlOK = "redirect:" + request.getContextPath() + request.getServletPath() + "/tickets" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        String outUrlInvalid = "forward:/WEB-INF/common/oper_fail.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());

        String ticketIdParam = request.getParameter(Cons.TICKET_ID);

        if (invalidInput(ticketIdParam)) {
            request.setAttribute(Cons.MESSAGE, rsBundle.getString("del.ticket.wrong.input"));
            return outUrlInvalid;
        }

        int ticketID = Integer.parseInt(ticketIdParam);
        Ticket ticketToRemove = constructRawTicket(ticketID);

        if (doesntOwnTicket(sessionUser, ticketToRemove)) {
            request.setAttribute(Cons.MESSAGE, rsBundle.getString("del.ticket.not.yours"));
            return outUrlInvalid;
        }

        ticketService.removeTicket(ticketToRemove);
        request.setAttribute(Cons.MESSAGE, rsBundle.getString("success.bought"));
        return outUrlOK;
    }

    private boolean doesntOwnTicket(User user, Ticket ticket) {
        List<Ticket> actualTickets = ticketService.getTicketsByUserId(user.getId());
        return actualTickets.stream().noneMatch(a -> a.getId() == ticket.getId());
    }

    private boolean invalidInput(String ticketID) {
        return !Optional.ofNullable(ticketID).isPresent() || !ticketID.matches("[0-9]+");
    }

    private Ticket constructRawTicket(int id) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        return ticket;
    }
}