package service.servlets;

import model.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signin")
public class SigninAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Username = " + req.getParameter("username"));
        System.out.println("Password = " + req.getParameter("password"));

        UserDao userDao = new UserDao();
        userDao.getEntityByName(req.getParameter("username"));
    }
}
