/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filter;


import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aluno
 */
@WebFilter(dispatcherTypes = {
    DispatcherType.REQUEST, 
    DispatcherType.FORWARD
}, 
urlPatterns = "/faces/restricted/user/*")
public class UserAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
            //Pega a sessão do usuário, caso exista
            HttpSession session = httpRequest.getSession(true);
            boolean logged = session.getAttribute("type_user") == "user";
            if (logged) {
                //Siga em frente
                chain.doFilter(request, response);
            } else {
//                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/faces/auth/user/login.xhtml");
            }
        
    }
    @Override
    public void destroy() {
        System.out.println("Terminou o filtro!");
    }
}
