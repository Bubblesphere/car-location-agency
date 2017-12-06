package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import data.Location;
import data.Reservation;
import data.Utilisateur;
import data.Vehicule;

public class LocationDao {
  /**
   * M�thode pour cr�� une R�servation
   * 
   * @param reservation
   *          r�servation � cr��.
   * @return la r�servation qui a �t� cr�� avec son id de mis � jour.
   */
  public static Location create(Location location) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "INSERT INTO Locations (vehicule_id, reservation_id, date_de_retour, "
          + "essence_manquant, retour_km, note, utilisateur_id, assurance, usure_journalier, "
          + "depart_km, estimation_reparation) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query,
          Statement.RETURN_GENERATED_KEYS);
      statement.setInt(1, location.getVehicule().getId());
      statement.setInt(2, location.getReservationId());
      statement.setObject(3, location.getDateDeRetour());
      statement.setInt(4, location.getEssenceManquant());
      statement.setInt(5, location.getRetourKm());
      statement.setString(6, location.getNoteLocation());
      statement.setInt(7, location.getUtilisateurLocation().getId());
      statement.setBoolean(8, location.isAssurance());
      statement.setBoolean(9, location.isUsureJournalier());
      statement.setInt(10, location.getDepartKm());
      statement.setFloat(11, location.getEstimationReparation());

      statement.execute();

      ResultSet keys = statement.getGeneratedKeys();
      keys.next();
      location.setLocationId(keys.getInt(1));

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return location;
  }

  /**
   * M�thode pour r�cup�rer une location
   * 
   * @param locationId
   *          id de la location � r�cup�rer.
   * @return la r�servation r�cup�rer.
   */
  public static Location retrieve(int locationId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT vehicule_id, reservation_id, date_de_retour, essence_manquant, "
          + "retour_km, note, utilisateur_id, assurance, usure_journalier, depart_km, "
          + "estimation_reparation FROM Locations WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, locationId);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Reservation reservation = ReservationDao.retrieve(resultSet.getInt("reservation_id"));
        Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
        Vehicule vehicule = VehiculeDao.retrieve(resultSet.getInt("vehicule_id"));
        return new Location(reservation.getReservationId(), reservation.getClientReservation(),
            reservation.getClasseReservation(), reservation.getStartDate(),
            reservation.getFinDate(), resultSet.getString("note"),
            reservation.getUtilisateurReservation(), locationId, vehicule, utilisateur,
            resultSet.getString("date_de_retour") != null
                ? LocalDate.parse(resultSet.getString("date_de_retour")) : null,
            resultSet.getBoolean("assurance"), resultSet.getBoolean("usure_journalier"),
            resultSet.getInt("essence_manquant"), resultSet.getInt("depart_km"),
            resultSet.getInt("retour_km"), reservation.getNoteReservation(),
            resultSet.getFloat("estimation_reparation"));
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
  public static List<Location> retrieveAll() {
    List<Location> result = new ArrayList<Location>();
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT id, vehicule_id, reservation_id, date_de_retour, essence_manquant, "
          + "retour_km, note, utilisateur_id, assurance, usure_journalier, depart_km, "
          + "estimation_reparation FROM Locations";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        Reservation reservation = ReservationDao.retrieve(resultSet.getInt("reservation_id"));
        Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
        Vehicule vehicule = VehiculeDao.retrieve(resultSet.getInt("vehicule_id"));
        Location location =  new Location(reservation.getReservationId(), reservation.getClientReservation(),
            reservation.getClasseReservation(), reservation.getStartDate(),
            reservation.getFinDate(), resultSet.getString("note"),
            reservation.getUtilisateurReservation(), resultSet.getInt("id"), vehicule, utilisateur,
            resultSet.getString("date_de_retour") != null
                ? LocalDate.parse(resultSet.getString("date_de_retour")) : null,
            resultSet.getBoolean("assurance"), resultSet.getBoolean("usure_journalier"),
            resultSet.getInt("essence_manquant"), resultSet.getInt("depart_km"),
            resultSet.getInt("retour_km"), reservation.getNoteReservation(),
            resultSet.getFloat("estimation_reparation"));

        result.add(location);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return result;
  }
  
  public static List<Location> retrieveAll(Boolean withReturns) {
	    List<Location> result = new ArrayList<Location>();
	    try (Connection connection = DataAccess.getConnection()) {

	      String query = "SELECT id, vehicule_id, reservation_id, date_de_retour, essence_manquant, "
	          + "retour_km, note, utilisateur_id, assurance, usure_journalier, depart_km, "
	          + "estimation_reparation FROM Locations ";
	      if(withReturns) {
	    	  query += "WHERE date_de_retour IS NULL";
	      }else {
	    	  query += "WHERE date_de_retour IS NOT NULL";
	      }
	      Statement statement = connection.createStatement();
	      ResultSet resultSet = statement.executeQuery(query);

	      while (resultSet.next()) {
	        Reservation reservation = ReservationDao.retrieve(resultSet.getInt("reservation_id"));
	        Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
	        Vehicule vehicule = VehiculeDao.retrieve(resultSet.getInt("vehicule_id"));
	        Location location =  new Location(reservation.getReservationId(), reservation.getClientReservation(),
	            reservation.getClasseReservation(), reservation.getStartDate(),
	            reservation.getFinDate(), resultSet.getString("note"),
	            reservation.getUtilisateurReservation(), resultSet.getInt("id"), vehicule, utilisateur,
	            resultSet.getString("date_de_retour") != null
	                ? LocalDate.parse(resultSet.getString("date_de_retour")) : null,
	            resultSet.getBoolean("assurance"), resultSet.getBoolean("usure_journalier"),
	            resultSet.getInt("essence_manquant"), resultSet.getInt("depart_km"),
	            resultSet.getInt("retour_km"), reservation.getNoteReservation(),
	            resultSet.getFloat("estimation_reparation"));

	        result.add(location);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }
	    return result;
	  }

  /**
   * M�thode pour mettre � jour une location
   * 
   * @param location
   *          la r�servation avec les nouvelle valeurs.
   * @return si la mise � jour a fonctionn�e.
   */
  public static boolean update(Location location) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "UPDATE Locations SET vehicule_id = ?, reservation_id = ?, date_de_retour = ?, essence_manquant = ?, "
          + "retour_km = ?, note = ?, utilisateur_id = ?, assurance = ?, usure_journalier = ?, depart_km = ?, "
          + "estimation_reparation = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, location.getVehicule().getId());
      statement.setInt(2, location.getReservationId());
      statement.setObject(3, location.getDateDeRetour());
      statement.setInt(4, location.getEssenceManquant());
      statement.setInt(5, location.getRetourKm());
      statement.setString(6, location.getNoteLocation());
      statement.setInt(7, location.getUtilisateurLocation().getId());
      statement.setBoolean(8, location.isAssurance());
      statement.setBoolean(9, location.isUsureJournalier());
      statement.setInt(10, location.getDepartKm());
      statement.setFloat(11, location.getEstimationReparation());
      statement.setInt(12, location.getLocationId());
      statement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * M�thode pour supprimer une location
   * 
   * @param locationId
   *          le id de la location � supprimer.
   * @return si la suppression � fonctionn�e.
   */
  public static boolean delete(int locationId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "DELETE FROM Locations WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, locationId);
      statement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
