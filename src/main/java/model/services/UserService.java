package model.services;

import model.dao.UserDao;
import model.dao.exceptions.DAOException;
import model.entity.User;
import model.services.exceptions.ServiceException;
import model.spec.Cons;
import model.spec.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserService {
    private String usernameRegex = Cons.USERNAME_REGEX;
    private String emailRegex = Cons.MAIL_REGEX;
    private String passRegex = Cons.PASSWORD_REGEX;
    private UserDao userDao = new UserDao();

    public User login(String usernameOrMail) throws DAOException {
        User user = new User();
        if (usernameOrMail.matches(emailRegex)) {
            user = userDao.getEntityByEmail(usernameOrMail);
        } else if (usernameOrMail.matches(usernameRegex)) {
            user = userDao.getEntityByUsername(usernameOrMail);
        }
        return user;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().setAttribute(Cons.SESSION_ROLE, Role.GUEST);
        request.getSession().setAttribute(Cons.SESSION_USERNAME, Role.GUEST.getString());
    }

    public void authorize(User user, String password, HttpServletRequest request) throws ServiceException {

//        if(request.getServletContext().getAttribute(Cons.SESSION_USERNAME).equals(user.getUsername())){
//
//        }
        if (password.equals(user.getPassword())) {
            request.getSession().setAttribute(Cons.SESSION_ROLE, user.getRole());
            request.getSession().setAttribute(Cons.SESSION_USERNAME, user.getUsername());
            request.getServletContext().setAttribute(Cons.SESSION_ROLE, user.getRole());
            request.getServletContext().setAttribute(Cons.SESSION_USERNAME, user.getUsername());
        } else {
            throw new ServiceException("User: (username=" + user.getUsername() + ", password=" + user.getPassword() +
                    ") Couldn't authorize with password: " + password);
        }
    }

    public void register(User user) throws DAOException {
        userDao.create(user);
    }

    public String getFaulRegistrationReason(String username, String email, String pass, String confPass, ResourceBundle resBundle) {
        String faultReson = "no fault";
        if (username.isEmpty() || email.isEmpty() || pass.isEmpty() || confPass.isEmpty()){
            faultReson = resBundle.getString("invalid.fillAll");
        } else if (!username.matches(usernameRegex)) {
            faultReson = resBundle.getString("registr.bad.username");
        } else if (!email.matches(emailRegex)) {
            faultReson = resBundle.getString("registr.bad.email");
        } else if (!pass.matches(passRegex)) {
            faultReson = resBundle.getString("registr.bad.password");
        } else if (!pass.equals(confPass)) {
            faultReson = resBundle.getString("registr.confirm.pass");
        }
            return faultReson;
    }

    public void setStatus(int status, String msg, HttpServletResponse response) {
        response.setStatus(status);
        try {
            response.getWriter().write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateRegistrData(String username, String email, String pass, String confPass) {
        return username.matches(usernameRegex) && email.matches(emailRegex) && pass.equals(passRegex) && pass.equals(confPass);
    }

    public boolean validate(String usernameOrMail, String password) {
        return usernameOrMail != null && !usernameOrMail.isEmpty() && password != null && !password.isEmpty();
    }
}
