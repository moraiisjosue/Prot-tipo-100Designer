package repository;

import java.sql.SQLException;
import java.util.List;
import model.Designer;

/**
 *
 * @author fabricio
 */
public interface CRUD<T> {

    public void insert(T object) throws SQLException;

    public void update(int pk, T object) throws SQLException;

    public void delete(int pk) throws SQLException;

    public List<T> findAll() throws SQLException;
    
    public T find(int pk) throws SQLException;
}
