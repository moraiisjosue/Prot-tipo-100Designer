/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import db.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.DesignerSpecialization;

/**
 *
 * @author Josue
 */
public class DesignerSpecializationRepository implements CRUD<DesignerSpecialization> {

    @Override
    public void insert(DesignerSpecialization object) throws SQLException {
        Connection connection = null;
        String sql = "INSERT INTO designer_specializations(designer_id, specialization_id) VALUES (?,?)";

         try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, object.getDesignerId());
            ps.setInt(2, object.getSpecialziationId());
            ps.executeUpdate();       
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(int pk, DesignerSpecialization object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int pk) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DesignerSpecialization> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DesignerSpecialization find(int pk) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
