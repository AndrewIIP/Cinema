package controller.command;

import model.entity.User;
import model.services.UserService;
import model.util.Cons;
import model.util.LogGen;
import org.apache.log4j.Logger;
import static model.util.LogMsg.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Command {
    private static Logger log = LogGen.getInstance();
    private UserService userService;

    public Logout(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request);
        log.info(USER_LOGGED_OUT + " : " + ((User)request.getSession().getAttribute(Cons.SESSION_USER)).getUsername());

        return "redirect:" + request.getContextPath() + request.getServletPath() + "/" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}
