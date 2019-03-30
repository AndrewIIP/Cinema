package service.command;

import model.dao.DAOException;
import model.dao.UserDao;
import service.Cons;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Login implements Command {
    private String usernameRegex = Cons.USERNAME_REGEX.getValue();
    private String mailRegex = Cons.MAIL_REGEX.getValue();
    private ResourceBundle resourceBundle;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        resourceBundle = ResourceBundle.getBundle(Cons.LOCAL_RB_BASE_NAME.getValue(),
                Locale.forLanguageTag((String)request.getSession().getAttribute(Cons.CUR_LANG.getValue())));
        String usernameOrMail = request.getParameter(Cons.LOGIN_USERNAME_PARAM.getValue()).trim();
        String password = request.getParameter(Cons.LOGIN_PASSWORD_PARAM.getValue()).trim();

        if (isValid(usernameOrMail, password, response)){
            if (usernameOrMail.matches(mailRegex)){
                try {
                    response.getWriter().write(new UserDao().getEntityByEmail(usernameOrMail).getEmail());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DAOException e) {
                    setStatus(400, resourceBundle.getString("invalid.cantFind"), response);
                    e.printStackTrace();
                }
            } else if (usernameOrMail.matches(usernameRegex)){
                try {
                    response.getWriter().write(new UserDao().getEntityByUsername(usernameOrMail).getUsername());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DAOException e) {
                    setStatus(400, resourceBundle.getString("invalid.cantFind"), response);
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isValid(String usernameOrMail, String password, HttpServletResponse response){
        boolean validity = false;

        if(!usernameOrMail.isEmpty() && !password.isEmpty()){
            if(usernameOrMail.matches(mailRegex) || usernameOrMail.matches(usernameRegex)) {
                validity = true;
            } else {
                setStatus(400, resourceBundle.getString("invalid.cantFind"), response);
            }
        } else {
            setStatus(400, resourceBundle.getString("invalid.fillAll"), response);
        }
        return validity;
    }

    private void setStatus(int status, String msg, HttpServletResponse response){
        response.setStatus(status);
        try {
            response.getWriter().write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
