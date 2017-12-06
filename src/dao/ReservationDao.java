package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import data.Classe;
import data.Client;
import data.Reservation;
import data.Utilisateur;

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
      statement.setInt(1, reservation.getClientReservation().getId());
      statement.setInt(2, reservation.getClasseReservation().getId());
      statement.setObject(3, reservation.getStartDate());
      statement.setObject(4, reservation.getFinDate());
      statement.setString(5, reservation.getNoteReservation());
      statement.setInt(6, reservation.getUtilisateurReservation().getId());

      statement.execute();

      ResultSet keys = statement.getGeneratedKeys();
      keys.next();
      reservation.setReservationId(keys.getInt(1));

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return reservation;
  }

  /**
   * M�thode pour r�cup�rer une r�servation
   * 
   * @param reservationId
   *          id de la r�servation � r�cup�rer.
   * @return la r�servation r�cup�rer.
   */
  public static Reservation retrieve(int reservationId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT id, client_id, classe_id, start_date, end_date, "
          + "note AS reservation_note, utilisateur_id FROM Reservations WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, reservationId);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Classe classe = ClasseDao.retrieve(resultSet.getInt("classe_id"));
        Client client = ClientDao.retrieve(resultSet.getInt("client_id"));
        Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
        return new Reservation(resultSet.getInt("id"), client,
           classe, LocalDate.parse(resultSet.getString("start_date")),
            LocalDate.parse(resultSet.getString("end_date")),
            resultSet.getString("reservation_note"), utilisateur);
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

      String query = "SELECT id, client_id, classe_id, start_date, end_date, "
          + "note AS reservation_note, utilisateur_id FROM Reservations";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        Classe classe = ClasseDao.retrieve(resultSet.getInt("classe_id"));
        Client client = ClientDao.retrieve(resultSet.getInt("client_id"));
        Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
        Reservation reservation = new Reservation(resultSet.getInt("id"), client,
           classe, LocalDate.parse(resultSet.getString("start_date")),
            LocalDate.parse(resultSet.getString("end_date")),
            resultSet.getString("reservation_note"), utilisateur);

        result.add(reservation);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return result;
  }
  
  public static List<Reservation> retrieveAll(Boolean withLocation){
	  // false pour la liste de coter de Reservation et true pour le combobox de Location
	  List<Reservation> result = new ArrayList<Reservation>();
	    try (Connection connection = DataAccess.getConnection()) {

	      String query = "SELECT R.id, R.client_id, R.classe_id, R.start_date, R.end_date, "
	          + "R.note AS reservation_note, R.utilisateur_id FROM Reservations R "
	          + "LEFT JOIN Locations L ON R.id = L.reservation_id "
	          + "WHERE L.id IS ";
	      if(withLocation) {
	    	  query += "NOT NULL";
	      }else {
	    	  query += "NULL";
	      }
	      Statement statement = connection.createStatement();
	      ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) {
	        Classe classe = ClasseDao.retrieve(resultSet.getInt("classe_id"));
	        Client client = ClientDao.retrieve(resultSet.getInt("client_id"));
	        Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
	        Reservation reservation = new Reservation(resultSet.getInt("id"), client,
	           classe, LocalDate.parse(resultSet.getString("start_date")),
	            LocalDate.parse(resultSet.getString("end_date")),
	            resultSet.getString("reservation_note"), utilisateur);

	        result.add(reservation);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }
	    return result;
  }
  
  public static int countVehiculeFree(int classeId, LocalDate start, LocalDate end) {
	    try (Connection connection = DataAccess.getConnection()) {

	      String query = "SELECT (SELECT COUNT() as reservationsCount " + 
	      		"FROM Reservations " + 
	      		"WHERE classe_id = ? " + 
	      		"AND date(end_date) < date(?) " + 
	      		"AND date(start_date) < date(?) " + 
	      		") as vehiculesReserves, ( " + 
	      		"SELECT COUNT()" + 
	      		"FROM " + 
	      		"Vehicules v " + 
	      		"WHERE v.classe_id = ? " + 
	      		"AND v.etat = 1) as VehiculesTotal";
	      PreparedStatement statement = connection.prepareStatement(query);
	      statement.setInt(1, classeId);
	      statement.setObject(2, end);
	      statement.setObject(3, start);
	      statement.setInt(4, classeId);
	      
	      ResultSet resultSet = statement.executeQuery();

	      if (resultSet.next()) {
	        return resultSet.getInt("VehiculesTotal") - resultSet.getInt("vehiculesReserves"); 	 
	        		}
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return 0;
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
      statement.setInt(1, reservation.getClientReservation().getId());
      statement.setInt(2, reservation.getClasseReservation().getId());
      statement.setObject(3, reservation.getStartDate());
      statement.setObject(4, reservation.getFinDate());
      statement.setString(5, reservation.getNoteReservation());
      statement.setInt(6, reservation.getUtilisateurReservation().getId());
      statement.setInt(7, reservation.getReservationId());
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