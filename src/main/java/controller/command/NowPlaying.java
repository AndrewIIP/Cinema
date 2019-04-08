package controller.command;

import model.spec.Cons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class NowPlaying implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Object> role = Optional.ofNullable(request.getSession().getAttribute(Cons.SESSION_ROLE));

        return role.map(o -> "forward:/WEB-INF/" + o.toString() + "/movies.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString()))
                .orElse("forward:/login");
    }
}
