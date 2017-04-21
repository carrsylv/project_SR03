package dao.InterfaceDao;

import beans.UvEntity;
import dao.DAOException;

import java.util.List;

/**
 * Created by Sylvain on 21/04/2017.
 */
public interface UvDao {
    List<UvEntity> findAll() throws DAOException;

    UvEntity find( String mail) throws DAOException;

    void create(UvEntity etu) throws DAOException;
}
