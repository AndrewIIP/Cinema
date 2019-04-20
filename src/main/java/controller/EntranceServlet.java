package controller;

import controller.command.*;
import model.entity.User;
import model.services.*;
import model.util.Cons;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class EntranceServlet extends HttpServlet {
    private Map<String, Command> commandsMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commandsMap.put("/", new Home());
        commandsMap.put("/login", new Login(new UserService()));
        commandsMap.put("/logout", new Logout(new UserService()));
        commandsMap.put("/registration", new Register(new UserService()));
        commandsMap.put("/go_registration", new GoRegister());
        commandsMap.put("/go_login", new GoLogin());
        commandsMap.put("/now_playing", new NowPlaying(new MovieService()));
        commandsMap.put("/showtimes", new Showtimes(new DayService()));
        commandsMap.put("/room", new Room(new SessionService()));
        commandsMap.put("/order", new PurchTicket(new TicketService()));
        commandsMap.put("/tickets", new MyTickets(new TicketService()));
        commandsMap.put("/remtick", new RemoveTicket(new TicketService()));

        getServletContext().setAttribute(Cons.CONTEXT_USERS_LIST, new LinkedList<User>());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String path = req.getPathInfo();
        req.setAttribute(Cons.CUR_REQ_URL, req.getRequestURL());
        Command command = commandsMap.get(path);
        String page = command.execute(req, resp);

        if (page.contains("redirect")) {
            resp.sendRedirect(page.replace("redirect:", ""));
        } else if (page.contains("forward")){
            req.getRequestDispatcher(page.replace("forward:", "")).forward(req, resp);
        }
    }

}
