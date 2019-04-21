package controller.command;

import model.entity.Day;
import model.entity.Movie;
import model.entity.User;
import model.services.DayService;
import model.services.MovieService;
import model.util.Cons;
import model.util.Languages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Showtimes implements Command {
    private DayService dayService;
    private MovieService movieService;

    public Showtimes(DayService dayService, MovieService movieService) {
        this.dayService = dayService;
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Object> role = Optional.ofNullable(((User) request.getSession().getAttribute(Cons.SESSION_USER)).getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));

        dayService.setDaoLocale(locale);
        movieService.setDaoLocale(locale);
        String dayIdParageter = request.getParameter(Cons.DAY_ID_PARAMETER);
        int dayID = 1;

        if (Optional.ofNullable(dayIdParageter).isPresent() && dayIdParageter.matches("[0-9]+")) {
            dayID = Integer.parseInt(dayIdParageter);
        }

        Day day = dayService.getDayById(dayID);
        List<Movie> moviesBank = movieService.getAllMovies();

        request.setAttribute(Cons.DAY_ID_PARAMETER, day);
        request.setAttribute(Cons.MOVIES_BEAN, moviesBank);
        request.setAttribute(Cons.SERVLET_CONTEXT, request.getServletPath());

        return role.map(o -> "forward:/WEB-INF/" + o.toString() + "/showtimes.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString()))
                .orElse("forward:/login");
    }
}