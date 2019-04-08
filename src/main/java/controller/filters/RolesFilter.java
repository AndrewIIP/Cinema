package controller.filters;


import model.spec.Cons;
import model.spec.Role;

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
                "/now_playing"
        ));

        ways.put(Role.USER, Set.of(
                "/",
                "/logout",
                "/showtimes",
                "/now_playing"
        ));

        ways.put(Role.ADMIN, Set.of(
                "/",
                "/logout",
                "/edit",
                "/showtimes",
                "/now_playing"
        ));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println(request.getRequestURI());
        String path = request.getRequestURI().replace(request.getContextPath(), "").replace(request.getServletPath(), "");

        System.out.println(path);

        if (request.getSession().getAttribute(Cons.SESSION_ROLE) == null) {
            request.getSession().setAttribute(Cons.SESSION_ROLE, Role.GUEST);
            request.getSession().setAttribute(Cons.SESSION_USERNAME, Role.GUEST.getString());
        }
        Role requestRole = (Role)request.getSession().getAttribute(Cons.SESSION_ROLE);

        if (!ways.get(requestRole).contains(path)) {
            request.getRequestDispatcher("/WEB-INF/common/forbidden.jsp").forward(request,response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
