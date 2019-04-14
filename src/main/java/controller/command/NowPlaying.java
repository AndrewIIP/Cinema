package controller.command;

import model.entity.Movie;
import model.entity.User;
import model.services.MovieService;
import model.util.Cons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class NowPlaying implements Command {
    MovieService movieService;

    public NowPlaying(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<Object> role = Optional.ofNullable(((User) request.getSession().getAttribute(Cons.SESSION_USER)).getRole());

        movieService.setDaoLocale(Locale.forLanguageTag((String) request.getSession().getAttribute(Cons.CUR_LANG)));
        List<Movie> moviesBank = movieService.getAllMovies();
        request.setAttribute(Cons.MOVIES_BEAN, moviesBank);

        return role.map(o -> "forward:/WEB-INF/" + o.toString() + "/movies.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString()))
                .orElse("forward:/login");
    }
}
