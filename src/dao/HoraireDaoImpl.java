package dao;

import beans.HoraireEntity;
import dao.InterfaceDao.HoraireDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

/**
 * Created by Sylvain on 24/04/2017.
 */
public class HoraireDaoImpl implements HoraireDao {
    private DAOFactory daoFactory;

    HoraireDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    private static HoraireEntity map(ResultSet resultSet ) throws SQLException {
        HoraireEntity horaire = new HoraireEntity();
        horaire.setIdHoraire( resultSet.getInt( "IdHoraire" ) );
        horaire.setHeureDebut( resultSet.getTime( "Heure_debut" ) );
        horaire.setHeureFin( resultSet.getTime( "Heure_fin" ) );
        horaire.setJourSemaine( resultSet.getString( "Jour_semaine" ) );
        return horaire;
    }

    @Override
    public List<HoraireEntity> findAll() throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<HoraireEntity> listHor = new ArrayList<HoraireEntity>();
        String SQL_SELECT_PAR_EMAIL = "SELECT * FROM horaire";

        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false);
            resultSet = preparedStatement.executeQuery();
        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while(resultSet.next()){
                listHor.add(map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return listHor;
    }

    @Override
    public HoraireEntity find( String jour) throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HoraireEntity hor = null;
        String SQL_SELECT_PAR_EMAIL = "SELECT * FROM horaire WHERE Jour_semaine = ?";

        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false, jour );
            resultSet = preparedStatement.executeQuery();
        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                hor = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return hor;
    }

    @Override
    public void create(HoraireEntity hor) throws DAOException{
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        String SQL_INSERT = "INSERT INTO horaire (Heure_debut, Heure_fin, Jour_semaine) VALUES (?, ?, ?)";
        try {
        /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, hor.getHeureDebut(), hor.getHeureFin(), hor.getJourSemaine() );
            int statut = preparedStatement.executeUpdate();
        /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
        /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                hor.setIdHoraire( valeursAutoGenerees.getInt( 1 ) );
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
