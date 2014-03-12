package exercise012;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;


/**
 * Don't forget to extend REMOTE
 */
public interface MWST_RemoteInterface extends Remote {
    public static final int PORT = 25533;
    public static final String HOST = "localhost";
    public static final String NAME = "MWST_RemoteInterface";

    //When extending remote, doesn't require function body
    public BigDecimal calculateMwst(Collection<MWST_Article> articles) throws RemoteException;
}