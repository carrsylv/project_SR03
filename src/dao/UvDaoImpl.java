package dao;

import dao.InterfaceDao.UvDao;
import static dao.DAOUtilitaire.*;
import beans.UvEntity;
import beans.ProfesseurEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sylvain on 21/04/2017.
 */
public class UvDaoImpl implements UvDao{

    private DAOFactory daoFactory;

    UvDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    private static UvEntity map(ResultSet resultSet ) throws SQLException {
        UvEntity uv = new UvEntity();
        uv.setIdUv( resultSet.getInt( "IdUv" ) );
        uv.setIdentifiant( resultSet.getString( "Identifiant" ) );
        uv.setDescription( resultSet.getString( "Description" ) );
        return uv;
    }

    @Override
    public List<UvEntity> findAll() throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<UvEntity> listUv = new ArrayList<UvEntity>();
        List<ProfesseurEntity> listProf = new ArrayList<ProfesseurEntity>();
        String SQL_SELECT_PAR_EMAIL = "SELECT * FROM uv, professeur WHERE Responsable = IdProfesseur;";

        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false);
            resultSet = preparedStatement.executeQuery();
        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while(resultSet.next()){
                UvEntity uv = map(resultSet);
                uv.setProfesseurByResponsable(ProfesseurDaoImpl.map(resultSet));
                listUv.add(uv);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return listUv;
    }

    @Override
    public UvEntity find( String identifiant) throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UvEntity uv = null;
        String SQL_SELECT_PAR_IDENTIFIANT = "SELECT * FROM uv WHERE identifiant = ?";

        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_IDENTIFIANT, false, identifiant );
            resultSet = preparedStatement.executeQuery();
        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                uv = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return uv;
    }

    @Override
    public void create(UvEntity uv) throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        String SQL_INSERT = "INSERT INTO uv (Identifiant, Responsable, Description) VALUES (?, ?, ?)";
        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, uv.getIdentifiant(), uv.getDescription());
            int statut = preparedStatement.executeUpdate();
        /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
        /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                uv.setIdUv( valeursAutoGenerees.getInt( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }
}
