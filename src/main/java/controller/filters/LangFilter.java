package controller.filters;

import model.spec.Cons;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LangFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getParameter(Cons.CUR_LANG) != null) {
            request.getSession().setAttribute(Cons.CUR_LANG, request.getParameter(Cons.CUR_LANG));
        } else {
            request.getSession().setAttribute(Cons.CUR_LANG, Locale.getDefault().getLanguage());
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
