package controller.command;

import model.entity.User;
import model.services.MovieService;
import model.util.Cons;
import model.util.Languages;
import model.util.LogGen;
import model.util.Role;
import org.apache.log4j.Logger;
import static model.util.LogMsg.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class RemoveMovie implements Command {
    private static Logger log = LogGen.getInstance();
    private MovieService movieService;

    public RemoveMovie(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User sessionUser = (User) request.getSession().getAttribute(Cons.SESSION_USER);
        Optional<Role> role = Optional.ofNullable((sessionUser).getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));
        ResourceBundle rsBundle = ResourceBundle.getBundle(Cons.LOCAL_RB_BASE_NAME, locale);

        movieService.setDaoLocale(locale);
        String outUrlOK = "redirect:" + request.getContextPath() + request.getServletPath() + "/now_playing" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        String outUrlInvalid = "forward:/WEB-INF/common/oper_fail.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());

        String movIdParam = request.getParameter(Cons.MOVIE_ID);
        int movId;

        if (invalidInput(movIdParam)) {
            request.setAttribute(Cons.MESSAGE, rsBundle.getString("del.ticket.wrong.input"));
            return outUrlInvalid;
        } else {
            movId = Integer.parseInt(movIdParam);
        }

        movieService.removeMovieById(movId);
        return outUrlOK;
    }

    private boolean invalidInput(String ticketID) {
        return !Optional.ofNullable(ticketID).isPresent() || !ticketID.matches("[0-9]+");
    }
}
