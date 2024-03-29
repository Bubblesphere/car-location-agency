package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DataAccess {

  /**
   * M�thode pour cr�� une connection � la baes de donn�es
   *
   * @return la connection � la base de donn�es.
   */
  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:DB_LocationVoiture.db");
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return connection;
  }

}