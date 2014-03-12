package exercise012;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface MWST_RemoteInterface extends Remote {
    public static final int PORT = 25533;
    public static final String HOST = "localhost";
    public static final String NAME = "MWST_RemoteInterface";

    public BigDecimal calculateMwst(Collection<MWST_Article> articles) throws RemoteException;
}