package dao;

import dao.InterfaceDao.EtudiantDao;
import beans.EtudiantEntity;

import static dao.DAOUtilitaire.*; // permet d'inclure les méthodes Utilitaire

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;


/**
 * Created by Sylvain on 19/04/2017.
 */

public class EtudiantDaoImpl implements EtudiantDao {

    private DAOFactory daoFactory;

    EtudiantDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    private static EtudiantEntity map( ResultSet resultSet ) throws SQLException {
        EtudiantEntity etudiant = new EtudiantEntity();
        etudiant.setIdEtudiant( resultSet.getInt( "IdEtudiant" ) );
        etudiant.setNom( resultSet.getString( "Nom" ) );
        etudiant.setPrenom( resultSet.getString( "Prenom" ) );
        etudiant.setMail( resultSet.getString( "Mail" ) );
        etudiant.setPhoto( resultSet.getBytes( "Photo" ) );
        etudiant.setCursus( resultSet.getString( "Cursus" ) );
        etudiant.setNiveau( resultSet.getString( "Niveau" ) );
        return etudiant;
    }

    @Override
    public List<EtudiantEntity> findAll() throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<EtudiantEntity> listEtu = new ArrayList<EtudiantEntity>();
        String SQL_SELECT_PAR_EMAIL = "SELECT * FROM etudiant";

        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false);
            resultSet = preparedStatement.executeQuery();
        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while(resultSet.next()){
                listEtu.add(map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return listEtu;
    }

    @Override
    public EtudiantEntity find( String mail) throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        EtudiantEntity etu = null;
        String SQL_SELECT_PAR_EMAIL = "SELECT * FROM etudiant WHERE mail = ?";

        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false, mail );
            resultSet = preparedStatement.executeQuery();
        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                etu = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return etu;
    }

    @Override
    public void create(EtudiantEntity etu) throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        String SQL_INSERT = "INSERT INTO etudiant (Nom, Prenom, Mail, Photo, Semestre, Cursus, Niveau) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, etu.getNom(), etu.getPrenom(), etu.getMail(), "null", etu.getSemestre(), etu.getCursus(), etu.getNiveau() );
            int statut = preparedStatement.executeUpdate();
        /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
        /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                etu.setIdEtudiant( valeursAutoGenerees.getInt( 1 ) );
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
