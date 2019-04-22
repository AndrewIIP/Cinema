package controller.command;

import model.dao.exceptions.DAOException;
import model.entity.User;
import model.services.UserService;
import model.services.exceptions.ServiceException;
import model.util.Cons;
import model.util.LogGen;
import org.apache.log4j.Logger;
import static model.util.LogMsg.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Login implements Command {
    private static Logger log = LogGen.getInstance();
    private UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Cons.LOCAL_RB_BASE_NAME,
                Locale.forLanguageTag((String) request.getSession().getAttribute(Cons.CUR_LANG)));
        String usernameOrMail = request.getParameter(Cons.USERNAME_PARAM);
        String password = request.getParameter(Cons.PASSWORD_PARAM);

        if (!userService.validate(usernameOrMail, password)) {
            userService.setResponseStatus(400, resourceBundle.getString("invalid.fillAll"), response);
            return "";
        }

        try {
            User user = userService.login(usernameOrMail);
            userService.authorize(user, password, request);
            response.getWriter().write(
                    request.getScheme() + "://" +
                            request.getServerName() +
                            ":" + request.getServerPort() +
                            request.getContextPath() +
                            request.getServletPath() + "/" +
                            (request.getQueryString() == null ? "" : "?" + request.getQueryString())
            );
            log.info(USER_LOGGED_SUCCESSFULLY + " : " + user.toString());
        } catch (DAOException e) {
            userService.setResponseStatus(400, resourceBundle.getString("invalid.cantFind"), response);
            log.info(CANT_FIND_SUCH_USER + " Username/email = " + usernameOrMail, e);
        } catch (ServiceException e) {
            userService.setResponseStatus(400, resourceBundle.getString("invalid.cantFind"), response);
            log.warn(TRIED_TO_AUTHORIZE_VIA_BAD_PASSWORD, e);
        } catch (IOException e) {
            log.error(EXCEPTION_WRITE_RESPONSE, e);
        }
        return "";
    }
}
