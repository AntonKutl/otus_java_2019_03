package otus.dbserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.dao.DAOUser;
import otus.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DBServer {
    private static Logger logger = LoggerFactory.getLogger(DBServer.class);
    private static final int PORT = 8100;
    private DAOUser daoUser;


    public DBServer(DAOUser daoUser) {
        this.daoUser = daoUser;
    }

    public void go() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {


            while (!Thread.currentThread().isInterrupted()) {
                logger.info("waiting for client connection");
                try (Socket clientSocket = serverSocket.accept()) {
                    clientHandler(clientSocket);
                }
            }
        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }

    private void clientHandler(Socket clientSocket) throws IOException {

        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {

            Object obj = in.readObject();

            if (obj.getClass() == String.class) {
                out.writeObject(daoUser.readAll());
                out.flush();
            }
            if (obj.getClass() == User.class) {
                daoUser.save(obj);
            }
        } catch (Exception ex) {

            logger.error("error", ex);
        }
    }
}
