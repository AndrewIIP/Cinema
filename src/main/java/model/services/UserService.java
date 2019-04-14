package model.services;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.exceptions.DAOException;
import model.entity.User;
import model.services.exceptions.AlreadyAuthorizedException;
import model.services.exceptions.ServiceException;
import model.util.Cons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class UserService {
    private String usernameRegex = Cons.USERNAME_REGEX;
    private String emailRegex = Cons.MAIL_REGEX;
    private String passRegex = Cons.PASSWORD_REGEX;

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public User login(String usernameOrMail) throws DAOException {
        User user = new User();

        try(UserDao dao = daoFactory.createUserDao()){
            if (usernameOrMail.matches(emailRegex)) {
                user = dao.getEntityByEmail(usernameOrMail);
            } else if (usernameOrMail.matches(usernameRegex)) {
                user = dao.getEntityByUsername(usernameOrMail);
            }
        }
        return user;
    }

    public void logout(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute(Cons.SESSION_USER);
        List contextUsers = (List) request.getServletContext().getAttribute(Cons.CONTEXT_USERS_LIST);
        contextUsers.remove(sessionUser);
        request.getSession().setAttribute(Cons.SESSION_USER, User.getGuestInst());
    }

    public void authorize(User user, String password, HttpServletRequest request) throws ServiceException, AlreadyAuthorizedException {

        if (((List) request.getServletContext().getAttribute(Cons.CONTEXT_USERS_LIST)).contains(user)) {
            throw new AlreadyAuthorizedException("Such user is already authorized in this application");
        }
        if (password.equals(user.getPassword())) {
            request.getSession().setAttribute(Cons.SESSION_USER, user);
            ((List) request.getServletContext().getAttribute(Cons.CONTEXT_USERS_LIST)).add(user);
        } else {
            throw new ServiceException("User: (username=" + user.getUsername() + ", password=" + user.getPassword() +
                    ") Couldn't authorize with password: " + password);
        }
    }

    public void register(User user) throws DAOException {
        try(UserDao dao = daoFactory.createUserDao()){
            dao.create(user);
        }
    }

    public String getFaultRegistrationReason(String username, String email, String pass, String confPass, ResourceBundle resBundle) {
        String faultReson = "no fault";
        if (username.isEmpty() || email.isEmpty() || pass.isEmpty() || confPass.isEmpty()) {
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

    public void setResponseStatus(int status, String msg, HttpServletResponse response) {
        response.setStatus(status);
        try {
            response.getWriter().write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean ifInvalidRegData(String username, String email, String pass, String confPass) {
        return !username.matches(usernameRegex) || !email.matches(emailRegex) || !pass.matches(passRegex) || !pass.equals(confPass);
    }

    public boolean validate(String usernameOrMail, String password) {
        return usernameOrMail != null && !usernameOrMail.isEmpty() && password != null && !password.isEmpty();
    }
}
