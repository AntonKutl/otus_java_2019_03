package ru.otus.homework.sessionmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SessionManagerHibernate  {

  private DatabaseSessionHibernate databaseSession;
  private final SessionFactory sessionFactory;

  public SessionManagerHibernate(SessionFactory sessionFactory) throws Exception {
    if (sessionFactory == null) {
      throw new Exception("SessionFactory is null");
    }
    this.sessionFactory = sessionFactory;
  }


  public void beginSession() throws Exception {
    try {
      databaseSession = new DatabaseSessionHibernate(sessionFactory.openSession());
    } catch (Exception e) {
      throw new Exception(e);
    }
  }


  public void commitSession() throws Exception {
    checkSessionAndTransaction();
    try {
      databaseSession.getTransaction().commit();
      databaseSession.getHibernateSession().close();
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  public void rollbackSession() throws Exception {
    checkSessionAndTransaction();
    try {
      databaseSession.getTransaction().rollback();
      databaseSession.getHibernateSession().close();
    } catch (Exception e) {
      throw new Exception(e);
    }
  }


  public void close() throws Exception {
    if (databaseSession == null) {
      return;
    }
    Session session = databaseSession.getHibernateSession();
    if (session == null || !session.isConnected()) {
      return;
    }

    Transaction transaction = databaseSession.getTransaction();
    if (transaction == null || !transaction.isActive()) {
      return;
    }

    try {
      databaseSession.close();
      databaseSession = null;
    } catch (Exception e) {
      throw new Exception(e);
    }
  }


  public DatabaseSessionHibernate getCurrentSession() throws Exception {
    checkSessionAndTransaction();
    return databaseSession;
  }

  private void checkSessionAndTransaction() throws Exception {
    if (databaseSession == null) {
      throw new Exception("DatabaseSession not opened ");
    }
    Session session = databaseSession.getHibernateSession();
    if (session == null || !session.isConnected()) {
      throw new Exception("Session not opened ");
    }

    Transaction transaction = databaseSession.getTransaction();
    if (transaction == null || !transaction.isActive()) {
      throw new Exception("Transaction not opened ");
    }
  }
}
