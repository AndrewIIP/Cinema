package controller.command;

import model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class Logout implements Command {
    UserService userService;

    public Logout(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request);

        return "redirect:" + request.getServletPath() + "/" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}
