package controller.command;

import model.entity.User;
import model.util.Cons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class Home implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Object> role = Optional.ofNullable(((User)request.getSession().getAttribute(Cons.SESSION_USER)).getRole());

        return role.map(o -> "forward:/WEB-INF/" + o.toString() + "/index.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString()))
                .orElse("forward:/login");
    }
}
