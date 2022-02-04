/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.User;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.User;
import repository.UserRepository;

/**
 *
 * @author Josue
 */
@ManagedBean(name = "userLogin")
@RequestScoped
public class LoginController implements Serializable {
    
    private String email;
    private String password;
    private User user;
    private User logado;
    private final UserRepository repository;

    public LoginController() {
        repository = new UserRepository();
    }
    
    public String login() {
        try {
            boolean loginVerified = repository.loginVerify(email, password);
            if (loginVerified) {
                HttpSession session = (HttpSession) FacesContext.
                        getCurrentInstance().getExternalContext().getSession(false);
                user = repository.findUserByEmail(email);

                session.setAttribute("user", user);
                session.setAttribute("user_email", user.getEmail());
                session.setAttribute("user_id", user.getId());
                session.setAttribute("user_name", user.getName());
                session.setAttribute("type_user", "user");
                
                logado = (User) session.getAttribute("user");
                

                session.setMaxInactiveInterval(60 * 10);

                return "/restricted/user/dashboard.xhtml";
            }
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Verifique seu login ou senha!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
//            return "returnestrito/
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO", "Erro ao efetuar login!"));
            return null;
        }

    }
    
    public String logout() {
        System.out.println("Passou Logout");
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.removeAttribute("user");
            session.removeAttribute("user_email");
            session.removeAttribute("user_id");
            session.removeAttribute("user_name");
            session.removeAttribute("type_user");
            System.out.println("Passou Logout");
            session.invalidate();
           
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO", "Logout!"));
            System.out.println("Erro Passou Logout");
        }   
        return "/auth/user/login.xhtml";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getLogado() {
        return logado;
    }

    public void setLogado(User logado) {
        this.logado = logado;
    }
    
    
    
}
