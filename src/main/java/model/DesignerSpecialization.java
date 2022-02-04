/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Josue
 */
public class DesignerSpecialization {
    
    private int id;
    private int designerId;
    private int specialziationId;
    private String specializationName;

    public DesignerSpecialization() {
    }
    
    public DesignerSpecialization(int id, int designerId, int specialziationId, String specializationName) {
        this.id = id;
        this.designerId = designerId;
        this.specialziationId = specialziationId;
        this.specializationName = specializationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDesignerId() {
        return designerId;
    }

    public void setDesignerId(int designerId) {
        this.designerId = designerId;
    }

    public int getSpecialziationId() {
        return specialziationId;
    }

    public void setSpecialziationId(int specialziationId) {
        this.specialziationId = specialziationId;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }
    
    
}
