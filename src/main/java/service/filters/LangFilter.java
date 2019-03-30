package service.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import service.Cons;

public class LangFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getParameter(Cons.CUR_LANG.getValue()) != null) {
            request.getSession().setAttribute(Cons.CUR_LANG.getValue(), request.getParameter(Cons.CUR_LANG.getValue()));
        } else {
            request.getSession().setAttribute(Cons.CUR_LANG.getValue(), Locale.getDefault().getLanguage());
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
