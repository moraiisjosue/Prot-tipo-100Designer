/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import db.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Designer;
import model.Specialization;

/**
 *
 * @author Josue
 */
public class SpecializationRepository implements CRUD<Specialization>{

    @Override
    public void insert(Specialization object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(int pk, Specialization object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int pk) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Specialization> findAll() throws SQLException {
        Connection connection = null;
        List<Specialization> specializations = new ArrayList<>();
        String sql = "SELECT id, category FROM specializations";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                specializations.add(
                        new Specialization(
                                rs.getInt("id"),
                                rs.getString("category")
                        ));
            }
            return specializations;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public Specialization find(int pk) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
