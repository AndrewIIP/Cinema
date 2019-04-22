package controller.command;

import model.entity.Ticket;
import model.entity.User;
import model.services.TicketService;
import model.util.Cons;
import model.util.Languages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class MyTickets implements Command {
    private TicketService ticketService;

    public MyTickets(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User curUser = (User) request.getSession().getAttribute(Cons.SESSION_USER);
        Optional<Object> role = Optional.ofNullable(curUser.getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));
        ticketService.setDaoLocale(locale);

        List<Ticket> tickets = ticketService.getTicketsByUserId(curUser.getId());
        request.setAttribute(Cons.TICKET_LIST, tickets);

        return role.map(o -> "forward:/WEB-INF/" + o.toString() + "/my_tickets.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString()))
                .orElse("forward:/login");
    }
}
