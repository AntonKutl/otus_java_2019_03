package otus.dbserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.dao.DAOUser;
import otus.dao.DAOUserImpl;
import otus.model.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainDB {
    private static Logger logger = LoggerFactory.getLogger(MainDB.class);

    public static void main(String[] args) {
        DAOUser daoUser = new DAOUserImpl();
        DBServer dbServer=new DBServer(daoUser);
        dbServer.go();
    }
}
