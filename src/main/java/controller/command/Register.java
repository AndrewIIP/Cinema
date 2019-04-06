package controller.command;

import model.dao.exceptions.DAOException;
import model.entity.User;
import model.services.UserService;
import model.services.exceptions.ServiceException;
import model.spec.Cons;
import model.spec.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Register implements Command {
    private String usernameRegex = Cons.USERNAME_REGEX;
    private String mailRegex = Cons.MAIL_REGEX;
    private ResourceBundle resourceBundle;
    UserService userService;

    public Register(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        resourceBundle = ResourceBundle.getBundle(Cons.LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(Cons.CUR_LANG)));

        String username = request.getParameter(Cons.USERNAME_PARAM.trim());
        String mail = request.getParameter(Cons.EMAIL_PARAM.trim());
        String password = request.getParameter(Cons.PASSWORD_PARAM.trim());
        String confirmPassword = request.getParameter(Cons.CONFIRM_PASS_PARAM.trim());
        String returnPath = "";

        if (!userService.validateRegistrData(username, mail, password, confirmPassword)) {
            userService.setStatus(400, userService.getFaulRegistrationReason(username, mail, password,
                    confirmPassword, resourceBundle), response);
            return returnPath;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(mail);
        user.setPassword(password);
        user.setRole(Role.USER);

        try {
            userService.register(user);
            userService.authorize(user, password, request);
        } catch (DAOException e) {
            userService.setStatus(400, resourceBundle.getString("register.already.exists"), response);
            //TODO LOG
            e.printStackTrace();
        } catch (ServiceException e) {
            userService.setStatus(400, resourceBundle.getString("register.bad.try.later"), response);
            //TODO LOG
            e.printStackTrace();
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
