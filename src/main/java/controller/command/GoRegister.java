package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class GoRegister implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/WEB-INF/guest/signup.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }
}
