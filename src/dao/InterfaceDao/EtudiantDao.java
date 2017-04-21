package dao.InterfaceDao;

import beans.EtudiantEntity;
import java.util.List;
import dao.DAOException;

/**
 * Created by Sylvain on 19/04/2017.
 */
public interface EtudiantDao {

    List<EtudiantEntity> findAll() throws DAOException;

    EtudiantEntity find( String mail) throws DAOException;

    void create(EtudiantEntity etu) throws DAOException;

}