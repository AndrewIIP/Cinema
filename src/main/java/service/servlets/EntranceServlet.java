package service.servlets;

import service.command.Command;
import service.command.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class EntranceServlet extends HttpServlet {
    private Map<String, Command> commandsMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commandsMap.put("/login", new Login());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = commandsMap.get(req.getRequestURI());
        command.execute(req, resp);
    }
}
