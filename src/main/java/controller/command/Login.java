package controller.command;

import model.dao.exceptions.DAOException;
import model.dao.UserDao;
import model.entity.User;
import model.services.UserService;
import model.services.exceptions.ServiceException;
import model.spec.Cons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Login implements Command {
    private ResourceBundle resourceBundle;
    UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        resourceBundle = ResourceBundle.getBundle(Cons.LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(Cons.CUR_LANG)));
        String usernameOrMail = request.getParameter(Cons.USERNAME_PARAM);
        String password = request.getParameter(Cons.PASSWORD_PARAM);

        if (!userService.validate(usernameOrMail, password)) {
            userService.setStatus(400, resourceBundle.getString("invalid.fillAll"), response);
            return "";
        }

        try {
            User user = userService.login(usernameOrMail);
            userService.authorize(user, password, request);
        } catch (DAOException | ServiceException e) {
            userService.setStatus(400, resourceBundle.getString("invalid.cantFind"), response);
            //TODO LOG
            e.printStackTrace();
            return "";
        }

        try {
            response.getWriter().write(
                    request.getScheme() + "://" +
                    request.getServerName() +
                    ":" + request.getServerPort() +
                    request.getContextPath() +
                    request.getServletPath() + "/" +
                            (request.getQueryString() == null ? "" : "?" + request.getQueryString())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
