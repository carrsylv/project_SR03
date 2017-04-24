package dao.InterfaceDao;

import beans.HoraireEntity;
import dao.DAOException;

import java.util.List;

/**
 * Created by Sylvain on 24/04/2017.
 */
public interface HoraireDao {

    List<HoraireEntity> findAll() throws DAOException;

    HoraireEntity find( String jour) throws DAOException;

    void create(HoraireEntity etu) throws DAOException;

}
