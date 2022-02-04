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

/**
 *
 * @author Josue
 */
public class DesignerRepository implements CRUD<Designer> {
    
    
    public boolean loginVerify(String email, String password) throws SQLException {
//        throw new UnsupportedOperationException("Implemente esse método");
        Connection connection = null;
        String sql = "SELECT * FROM designers WHERE email = ? and password = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void insert(Designer object) throws SQLException {
        Connection connection = null;
        String sql = "INSERT INTO designers (name, email, password, birthday, description, phone, city) VALUES (?,?,?,?,?,?,?)";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, object.getName());
            ps.setString(2, object.getEmail());
            ps.setString(3, object.getPassword());
            ps.setDate(4, new java.sql.Date(object.getBirthday().getTime()));
            ps.setString(5, object.getDescription());
            ps.setString(6, object.getPhone());
            ps.setString(7, object.getCity());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(int pk, Designer object) throws SQLException {
        Connection connection = null;
        String sql = "UPDATE designers SET name = ?, email = ?, password = ?, description = ?, phone = ?, city = ? WHERE id = ?";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, object.getName());
            ps.setString(2, object.getEmail());
            ps.setString(3, object.getPassword());
            ps.setString(4, object.getDescription());
            ps.setString(5, object.getPhone());
            ps.setString(6, object.getCity());
            ps.setInt(7, pk);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void delete(int pk) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Designer> findAll() throws SQLException {
        Connection connection = null;
        List<Designer> designers = new ArrayList<>();
        String sql = "SELECT id, name, email, birthday, description, city, phone FROM designers";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                designers.add(
                        new Designer(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                (Date) rs.getDate("birthday"),
                                rs.getString("description"),
                                rs.getString("city"),
                                rs.getString("phone")
                        ));
            }
            return designers;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }
    
    public List<Designer> findRandom() throws SQLException {
        Connection connection = null;
        List<Designer> designers = new ArrayList<>();
        List<Designer> designersRandom = new ArrayList<>();
        String sql = "SELECT id, name, email, birthday, description, city, phone FROM designers order by rand() limit 3";

        try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                designersRandom.add(
                        new Designer(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                (Date) rs.getDate("birthday"),
                                rs.getString("description"),
                                rs.getString("city"),
                                rs.getString("phone")
                        ));
            }
            return designersRandom;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public Designer find(int pk) throws SQLException {
        Designer designer = new Designer();
        Connection connection = null;
        String sql = "SELECT id, name, email, description, phone, city FROM designers WHERE id = ?";
        
         try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pk);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
               designer.setId(result.getInt("id"));
               designer.setName(result.getString("name"));
               designer.setEmail(result.getString("email"));
               designer.setDescription(result.getString("description"));
               designer.setPhone(result.getString("phone"));
               designer.setCity(result.getString("city"));
            }
            return designer;
           
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }
    
    public Designer findDesignerByEmail(String email) throws SQLException {
        
        Designer designer = new Designer();
        Connection connection = null;
        String sql = "SELECT id, name, email, avatar FROM designers WHERE email = ?";
        
         try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
               designer.setId(result.getInt("id"));
               designer.setName(result.getString("name"));
               designer.setEmail(result.getString("email"));
            }
            return designer;
           
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

}
