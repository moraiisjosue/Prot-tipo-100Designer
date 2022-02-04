/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Designer;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Designer;
import model.DesignerSpecialization;
import model.Specialization;
import repository.DesignerRepository;
import repository.DesignerSpecializationRepository;
import repository.SpecializationRepository;

/**
 *
 * @author Josue
 */
@ManagedBean(name = "designerBean")
@SessionScoped
public class DesignerController implements Serializable {

    private Designer designer;
    private final DesignerRepository repository;
    private DesignerSpecialization designerSpecialization;
    private List<Specialization> specializations;
    private final DesignerSpecializationRepository designerSpecializationRepository;
    private final SpecializationRepository specializationRepository;

    public DesignerController() {
        designer = new Designer();
        designerSpecialization = new DesignerSpecialization();
        repository = new DesignerRepository();
        designerSpecializationRepository = new DesignerSpecializationRepository();
        specializationRepository = new SpecializationRepository();

        try {
            specializations = specializationRepository.findAll();
            HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(false);
            Designer loggedDesigner = (Designer) session.getAttribute("designer");
            designer = repository.find(loggedDesigner.getId());
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERRO Lista", "Oops! " + e.getMessage()));
        }
    }

    public String register() {
        try {

            repository.insert(designer);
            Designer designerdb = repository.findDesignerByEmail(designer.getEmail());
            designerSpecialization.setDesignerId(designerdb.getId());
            designerSpecializationRepository.insert(designerSpecialization);

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
            Designer loggedDesigner = (Designer) session.getAttribute("designer");
            System.out.println(loggedDesigner.getId());
            repository.update(loggedDesigner.getId(), designer);

            return "/restricted/designer/dashboard.xhtml";

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO Register", "Oops! " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }

    public DesignerSpecialization getDesignerSpecialization() {
        return designerSpecialization;
    }

    public void setDesignerSpecialization(DesignerSpecialization designerSpecialization) {
        this.designerSpecialization = designerSpecialization;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

}
