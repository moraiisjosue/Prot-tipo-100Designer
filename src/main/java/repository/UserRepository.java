/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import db.Conn;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.User;

/**
 *
 * @author Josue
 */
public class UserRepository implements CRUD<User>{
    
    public boolean loginVerify(String email, String password) throws SQLException {
//        throw new UnsupportedOperationException("Implemente esse método");
        Connection connection = null;
        String sql = "SELECT * FROM users WHERE email = ? and password = ?";

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
    public void insert(User object) throws SQLException {
        Connection connection = null;
        String sql = "INSERT INTO users (name, email, password, birthday) VALUES (?,?,?,?)";

         try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, object.getName());
            ps.setString(2, object.getEmail());
            ps.setString(3, object.getPassword());
            ps.setDate(4, new java.sql.Date(object.getBirthday().getTime()));
            ps.executeUpdate();
           
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void update(int pk, User object) throws SQLException {
        Connection connection = null;
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, birthday = ? WHERE id = ?";

         try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, object.getName());
            ps.setString(2, object.getEmail());
            ps.setString(3, object.getPassword());
            ps.setDate(4, new java.sql.Date(object.getBirthday().getTime()));
            ps.setInt(5, pk);
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
    public List<User> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User find(int pk) throws SQLException {
        User user = new User();
        Connection connection = null;
        String sql = "SELECT id, name, email FROM users WHERE id = ?";
        
         try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pk);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
               user.setId(result.getInt("id"));
               user.setName(result.getString("name"));
               user.setEmail(result.getString("email"));
            }
            return user;
           
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }
    
    public User findUserByEmail(String email) throws SQLException {
        
        User user = new User();
        Connection connection = null;
        String sql = "SELECT id, name, email, avatar FROM users WHERE email = ?";
        
         try {
            connection = Conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
               user.setId(result.getInt("id"));
               user.setName(result.getString("name"));
               user.setEmail(result.getString("email"));
               user.setAvatar(result.getString("avatar"));
            }
            return user;
           
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }
}
