package controller.command;

import model.entity.Day;
import model.entity.Movie;
import model.entity.User;
import model.services.DayService;
import model.services.MovieService;
import model.util.Cons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Showtimes implements Command {
    DayService dayService;

    public Showtimes(DayService dayService) {
        this.dayService = dayService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Object> role = Optional.ofNullable(((User) request.getSession().getAttribute(Cons.SESSION_USER)).getRole());
        dayService.setDaoLocale(Locale.forLanguageTag((String) request.getSession().getAttribute(Cons.CUR_LANG)));
        String dayIdParageter = request.getParameter(Cons.DAY_ID_PARAMETER);
        int dayID = 1;

        if (Optional.ofNullable(dayIdParageter).isPresent() && dayIdParageter.matches("[0-9]+")) {
            dayID = Integer.parseInt(dayIdParageter);
        }

        Day day = dayService.getDayById(dayID);
        request.setAttribute(Cons.DAY_ID_PARAMETER, day);

//        Optional.ofNullable(request.getQueryString()).
//                map(a -> a.replace(Cons.DAY_ID_PARAMETER, "").replace("day=", ""))
        return role.map(o -> "forward:/WEB-INF/" + o.toString() + "/showtimes.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString()))
                .orElse("forward:/login");
    }
}