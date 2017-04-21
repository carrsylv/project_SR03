package dao;

import beans.ProfesseurEntity;
import dao.InterfaceDao.ProfesseurDao;
import static dao.DAOUtilitaire.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sylvain on 21/04/2017.
 */
public class ProfesseurDaoImpl implements ProfesseurDao {
    private DAOFactory daoFactory;

    ProfesseurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    private static ProfesseurEntity map(ResultSet resultSet ) throws SQLException {
        ProfesseurEntity prof = new ProfesseurEntity();
        prof.setId( resultSet.getInt( "Id" ) );
        prof.setNom( resultSet.getString( "Nom" ) );
        prof.setPrenom( resultSet.getString( "Prenom" ) );
        prof.setMail( resultSet.getString( "Mail" ) );
        prof.setPhoto( resultSet.getBytes( "Photo" ) );
        return prof;
    }

    @Override
    public List<ProfesseurEntity> findAll() throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ProfesseurEntity> listProf = new ArrayList<ProfesseurEntity>();
        String SQL_SELECT_PAR_EMAIL = "SELECT * FROM professeur";

        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false);
            resultSet = preparedStatement.executeQuery();
        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while(resultSet.next()){
                listProf.add(map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return listProf;
    }

    @Override
    public ProfesseurEntity find( String mail) throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ProfesseurEntity prof = null;
        String SQL_SELECT_PAR_EMAIL = "SELECT * FROM professeur WHERE mail = ?";

        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false, mail );
            resultSet = preparedStatement.executeQuery();
        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                prof = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return prof;
    }

    @Override
    public void create(ProfesseurEntity prof) throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        String SQL_INSERT = "INSERT INTO professeur (Nom, Prenom, Mail, Photo) VALUES (?, ?, ?, ?)";
        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, prof.getNom(), prof.getPrenom(), prof.getMail(), "null");
            int statut = preparedStatement.executeUpdate();
        /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
        /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                prof.setId( valeursAutoGenerees.getInt( 1 ) );
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
