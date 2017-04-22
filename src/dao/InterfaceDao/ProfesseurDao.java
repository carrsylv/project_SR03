package dao.InterfaceDao;
import beans.ProfesseurEntity;

import dao.DAOException;

import java.util.List;

/**
 * Created by Sylvain on 21/04/2017.
 */
public interface ProfesseurDao {
    List<ProfesseurEntity> findAll() throws DAOException;

    ProfesseurEntity find( String mail) throws DAOException;

    void create(ProfesseurEntity etu) throws DAOException;
}
