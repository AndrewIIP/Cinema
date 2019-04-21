package controller.command;

import model.entity.Movie;
import model.entity.User;
import model.services.MovieService;
import model.util.Cons;
import model.util.Languages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class NowPlaying implements Command {
    private MovieService movieService;

    public NowPlaying(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Object> role = Optional.ofNullable(((User) request.getSession().getAttribute(Cons.SESSION_USER)).getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));

        movieService.setDaoLocale(locale);
        List<Movie> moviesBank = movieService.getAllMovies();
        request.setAttribute(Cons.MOVIES_BEAN, moviesBank);
        request.setAttribute(Cons.SERVLET_CONTEXT, request.getServletPath());

        return role.map(o -> "forward:/WEB-INF/" + o.toString() + "/movies.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString()))
                .orElse("forward:/login");
    }
}
