/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Designer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Designer;
import repository.DesignerRepository;

/**
 *
 * @author Josue
 */
@ManagedBean(name = "designerLogin")
@RequestScoped
public class LoginController {
    
    private String email;
    private String password;
    private Designer designer;
    private final DesignerRepository repository;

    public LoginController() {
        repository = new DesignerRepository();
    }
    
    public String login() {
        try {

            boolean loginVerified = repository.loginVerify(email, password);
            if (loginVerified) {
                HttpSession session = (HttpSession) FacesContext.
                        getCurrentInstance().getExternalContext().getSession(false);
                designer = repository.findDesignerByEmail(email);

                session.setAttribute("designer", designer);
                session.setAttribute("designer_email", designer.getEmail());
                session.setAttribute("designer_id", designer.getId());
                session.setAttribute("type_designer", "designer");

                session.setMaxInactiveInterval(60 * 10);

                return "/restricted/designer/dashboard.xhtml";
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
        System.out.println("Pssou logout");
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.removeAttribute("designer");
            session.removeAttribute("designer_email");
            session.removeAttribute("designer_id");
            session.removeAttribute("designer");
            session.invalidate();
            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO", "Erro ao efetuar login!"));
        }
        return "/auth/designer/login.xhtml";
    }
    
    public String home(){
        return "/restricted/designer/dashboard.xhtml";
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

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }
    
}
