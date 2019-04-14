package controller.listeners;

import model.entity.User;
import model.util.Cons;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

public class SingleLoginListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("SESSION'S CREATED!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        User sessionUser = (User)httpSessionEvent.getSession().getAttribute(Cons.SESSION_USER);
        List contextUsers = (List)httpSessionEvent.getSession().getAttribute(Cons.CONTEXT_USERS_LIST);
        contextUsers.remove(sessionUser);
        System.out.println("SESSION'S DESTROYED!");
    }
}
