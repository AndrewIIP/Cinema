package controller.filters;


import model.entity.User;
import model.util.Cons;
import model.util.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class RolesFilter implements Filter {
    Map<Role, Set<String>> ways;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ways = new HashMap<>();
        ways.put(Role.GUEST, Set.of(
                "/",
                "/login",
                "/registration",
                "/go_login",
                "/go_registration",
                "/showtimes",
                "/now_playing",
                "/room"
        ));

        ways.put(Role.USER, Set.of(
                "/",
                "/logout",
                "/showtimes",
                "/now_playing",
                "/room"
        ));

        ways.put(Role.ADMIN, Set.of(
                "/",
                "/logout",
                "/edit",
                "/showtimes",
                "/now_playing",
                "/room"
        ));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().replace(request.getContextPath(), "").replace(request.getServletPath(), "");

        if (request.getSession().getAttribute(Cons.SESSION_USER) == null) {
            request.getSession().setAttribute(Cons.SESSION_USER, User.getGuestInst());
        }
        Role requestRole = ((User) request.getSession().getAttribute(Cons.SESSION_USER)).getRole();

        if (!ways.get(requestRole).contains(path)) {
            request.getRequestDispatcher("/WEB-INF/common/forbidden.jsp").forward(request, response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
