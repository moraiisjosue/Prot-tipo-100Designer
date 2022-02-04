/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.User;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.User;
import repository.UserRepository;
import util.Mensagem;

/**
 *
 * @author Josue
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserController implements Serializable {

    private User user;
    private final UserRepository repository;

    public UserController() {
        user = new User();
        repository = new UserRepository();
        try {
            HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(false);
            User loggedUser = (User) session.getAttribute("user");
            user = repository.find(loggedUser.getId());
            System.out.println(loggedUser);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO Lista", "Oops! " + e.getMessage()));
        }
    }

    public String register() {
        try {
            repository.insert(user);
            return "login.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO Register", "Oops! " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public String update() {
        try {
            HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(false);
            User loggedUser = (User) session.getAttribute("user");
            System.out.println(loggedUser.getId());
            repository.update(loggedUser.getId(), user);

            return "/restricted/user/dashboard.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO Register", "Oops! " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
