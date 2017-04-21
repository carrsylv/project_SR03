package dao;

/**
 * Created by Sylvain on 19/04/2017.
 */
public class DAOConfigurationException extends RuntimeException {
    /*
     * Constructeurs
     */
    public DAOConfigurationException( String message ) {
        super( message );
    }

    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}