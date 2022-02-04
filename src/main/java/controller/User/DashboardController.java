/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.User;

import java.io.Serializable;
import java.util.List;
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
@ManagedBean(name = "userDashboard")
@RequestScoped
public class DashboardController implements Serializable {

    private List<Designer> designers;
    private List<Designer> designersRandom;
    private DesignerRepository designerRepository;

    public DashboardController() {
        designerRepository = new DesignerRepository();
        try {
            designers = designerRepository.findAll();
            designersRandom = designerRepository.findRandom(); 
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO Lista", "Oops! " + e.getMessage()));
        }
    }

    public List<Designer> getDesigners() {
        return designers;
    }

    public void setDesigners(List<Designer> designers) {
        this.designers = designers;
    }

    public List<Designer> getDesignersRandom() {
        return designersRandom;
    }

    public void setDesignersRandom(List<Designer> designersRandom) {
        this.designersRandom = designersRandom;
    }
}
