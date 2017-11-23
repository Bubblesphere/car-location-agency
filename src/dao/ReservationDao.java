package dao;

import data.Classe;
import data.Client;
import data.Reservation;
import data.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {
  /**
   * M�thode pour cr�� une R�servation
   * 
   * @param reservation
   *          r�servation � cr��.
   * @return la r�servation qui a �t� cr�� avec son id de mis � jour.
   */
  public static Reservation create(Reservation reservation) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "INSERT INTO Reservations (client_id, classe_id, "
          + "start_date, end_date, note, utilisateur_id) values (?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query,
          Statement.RETURN_GENERATED_KEYS);
      statement.setInt(1, reservation.getClientId());
      statement.setInt(2, reservation.getClasseId());
      statement.setObject(3, reservation.getStartDate());
      statement.setObject(4, reservation.getFinDate());
      statement.setString(5, reservation.getNote());
      statement.setInt(6, reservation.getUtilisateurId());

      statement.execute();

      ResultSet keys = statement.getGeneratedKeys();
      keys.next();
      reservation.setId(keys.getInt(1));

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return reservation;
  }

  /**
   * M�thode pour r�cup�rer une r�servation
   * 
   * @param vehiculeId
   *          id de la r�servation � r�cup�rer.
   * @return la r�servation r�cup�rer.
   */
  public static Reservation retrieve(int vehiculeId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT R.id, R.client_id, R.classe_id, R.start_date, R.end_date, "
          + "R.note AS reservation_note, R.utilisateur_id, C.nom AS classe_nom, "
          + "C.prix_journalier, L.prenom AS client_prenom, L.nom AS client_nom, L,adresse, "
          + "L.numero_permis, L.telephone, L.courriel, L.note AS client_note, "
          + "U.courriel AS utilisateur_courriel, U.prenom AS utilisateur_prenom, "
          + "U.nom AS utilisateur_nom, U.num_employe, U.role, U.desactive "
          + "LEFT JOIN Classes C ON C.id = R.classe_id LEFT JOIN Client L ON L.id = R.client_id "
          + "LEFT JOIN Utilisateurs U ON U.id = R.utilisateur_id  WHERE V.id = R";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, vehiculeId);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Classe classe = ClasseDao.retrieve(resultSet.getInt("classe_id"));
        Client client = ClientDao.retrieve(resultSet.getInt("client_id"));
        Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
        return new Reservation(resultSet.getInt("id"), client.getId(), client, classe.getId(),
            classe, LocalDate.parse(resultSet.getString("start_date")),
            LocalDate.parse(resultSet.getString("end_date")),
            resultSet.getString("reservation_note"), utilisateur.getId(), utilisateur);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * M�thode pour r�cup�rer toutes les r�servation
   * 
   * @return liste de toutes les r�servations.
   */
  public static List<Reservation> retrieveAll() {
    List<Reservation> result = new ArrayList<Reservation>();
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT R.id, R.client_id, R.classe_id, R.start_date, R.end_date, "
          + "R.note AS reservation_note, R.utilisateur_id, C.nom AS classe_nom, C.prix_journalier, "
          + "L.prenom AS client_prenom, L.nom AS client_nom, L,adresse, L.numero_permis, "
          + "L.telephone, L.courriel, L.note AS client_note U.courriel AS utilisateur_courriel, "
          + "U.prenom AS utilisateur_prenom, U.nom AS utilisateur_nom, U.num_employe, U.role, "
          + "U.desactive LEFT JOIN Classes C ON C.id = R.classe_id LEFT JOIN Client L ON "
          + "L.id = R.client_id LEFT JOIN Utilisateurs U ON U.id = R.utilisateur_id ";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        Classe classe = ClasseDao.retrieve(resultSet.getInt("classe_id"));
        Client client = ClientDao.retrieve(resultSet.getInt("client_id"));
        Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
        Reservation reservation = new Reservation(resultSet.getInt("id"), client.getId(), client,
            classe.getId(), classe, LocalDate.parse(resultSet.getString("start_date")),
            LocalDate.parse(resultSet.getString("end_date")),
            resultSet.getString("reservation_note"), utilisateur.getId(), utilisateur);

        result.add(reservation);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return result;
  }

  /**
   * M�thode pour mettre � jour une r�servation
   * 
   * @param reservation
   *          la r�servation avec les nouvelle valeurs.
   * @return si la mise � jour a fonctionn�e.
   */
  public static boolean update(Reservation reservation) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "UPDATE Reservations SET client_id = ?, classe_id = ?, "
          + "start_date = ?,  end_date = ?, note = ?, utilisateur_id = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, reservation.getClientId());
      statement.setInt(2, reservation.getClasseId());
      statement.setObject(3, reservation.getStartDate());
      statement.setObject(4, reservation.getFinDate());
      statement.setString(5, reservation.getNote());
      statement.setInt(6, reservation.getUtilisateurId());
      statement.setInt(7, reservation.getId());
      statement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * M�thode pour supprimer une r�servation
   * 
   * @param reservationId
   *          le id de la r�servation � supprimer.
   * @return si la suppression � fonctionn�e.
   */
  public static boolean delete(int reservationId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "DELETE FROM Reservations WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, reservationId);
      statement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}