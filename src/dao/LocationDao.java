package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import data.Location;
import data.Paiement;
import data.Reservation;
import data.Utilisateur;
import data.Vehicule;

public class LocationDao {
    /**
     * Methode pour creer une Reservation
     *
     * @param location location a creer.
     * @return la reservation qui a ete creee avec son id de mis a jour.
     */
    public static Location create(Location location) {
        try (Connection connection = DataAccess.getConnection()) {

            String query = "INSERT INTO Locations (vehicule_id, reservation_id, date_de_retour, "
                    + "essence_manquant, retour_km, note, utilisateur_id, assurance, usure_journalier, "
                    + "depart_km, estimation_reparation) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            location.setId(keys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return location;
    }

    /**
     * Methode pour recuperer une location
     *
     * @param locationId id de la location a recuperer.
     * @return la reservation recuperer.
     */
    public static Location retrieve(int locationId) {
        try (Connection connection = DataAccess.getConnection()) {

            String query = "SELECT * FROM Locations WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, locationId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Reservation reservation = ReservationDao.retrieve(resultSet.getInt("reservation_id"));
                Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
                Vehicule vehicule = VehiculeDao.retrieve(resultSet.getInt("vehicule_id"));
                Location location = new Location(reservation.getReservationId(), reservation.getClientReservation(),
                        reservation.getClasseReservation(), reservation.getStartDate(), reservation.getFinDate(),
                        resultSet.getString("note"), reservation.getUtilisateurReservation(), locationId, vehicule,
                        utilisateur,
                        resultSet.getString("date_de_retour") != null
                                ? LocalDateTime.parse(resultSet.getString("date_de_retour"))
                                : null,
                        resultSet.getBoolean("assurance"), resultSet.getBoolean("usure_journalier"),
                        resultSet.getInt("essence_manquant"), resultSet.getInt("depart_km"),
                        resultSet.getInt("retour_km"), reservation.getNoteReservation(),
                        resultSet.getFloat("estimation_reparation"),
                        resultSet.getString("note_retour") != null ? resultSet.getString("note_retour") : "");
                location.setPaiements(retrievePaiement(locationId));
                return location;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retour tous les Paiements d'une location
     * @param locationId
     * @return
     */
    private static List<Paiement> retrievePaiement(int locationId) {
        List<Paiement> result = new ArrayList<Paiement>();
        try (Connection connection = DataAccess.getConnection()) {

            String query = "SELECT id, montant, note, methode FROM Paiements WHERE location_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, locationId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Paiement paiement = new Paiement(resultSet.getInt("id"), locationId, resultSet.getFloat("montant"),
                        resultSet.getInt("methode"), resultSet.getString("note"));

                result.add(paiement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * Methode pour recuperer toutes les reservation
     *
     * @return liste de toutes les reservations.
     */
    public static List<Location> retrieveAll() {
        List<Location> result = new ArrayList<Location>();
        try (Connection connection = DataAccess.getConnection()) {

            String query = "SELECT * FROM Locations";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Reservation reservation = ReservationDao.retrieve(resultSet.getInt("reservation_id"));
                Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
                Vehicule vehicule = VehiculeDao.retrieve(resultSet.getInt("vehicule_id"));
                Location location = new Location(reservation.getReservationId(), reservation.getClientReservation(),
                        reservation.getClasseReservation(), reservation.getStartDate(), reservation.getFinDate(),
                        resultSet.getString("note"), reservation.getUtilisateurReservation(), resultSet.getInt("id"),
                        vehicule, utilisateur,
                        resultSet.getString("date_de_retour") != null
                                ? LocalDateTime.parse(resultSet.getString("date_de_retour"))
                                : null,
                        resultSet.getBoolean("assurance"), resultSet.getBoolean("usure_journalier"),
                        resultSet.getInt("essence_manquant"), resultSet.getInt("depart_km"),
                        resultSet.getInt("retour_km"), reservation.getNoteReservation(),
                        resultSet.getFloat("estimation_reparation"),
                        resultSet.getString("note_retour") != null ? resultSet.getString("note_retour") : "");
                location.setPaiements(retrievePaiement(location.getId()));
                result.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * Retourne toutes les Locations, filtrees par celles completees ou non
     * @param withReturns
     * @return
     */
    public static List<Location> retrieveAll(Boolean withReturns) {
        List<Location> result = new ArrayList<Location>();
        try (Connection connection = DataAccess.getConnection()) {

            String query = "SELECT * FROM Locations ";
            if (withReturns) {
                query += "WHERE date_de_retour IS NULL";
            } else {
                query += "WHERE date_de_retour IS NOT NULL";
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Reservation reservation = ReservationDao.retrieve(resultSet.getInt("reservation_id"));
                Utilisateur utilisateur = UtilisateurDao.retrieve(resultSet.getInt("utilisateur_id"));
                Vehicule vehicule = VehiculeDao.retrieve(resultSet.getInt("vehicule_id"));
                Location location = new Location(reservation.getReservationId(), reservation.getClientReservation(),
                        reservation.getClasseReservation(), reservation.getStartDate(), reservation.getFinDate(),
                        resultSet.getString("note"), reservation.getUtilisateurReservation(), resultSet.getInt("id"),
                        vehicule, utilisateur,
                        resultSet.getString("date_de_retour") != null
                                ? LocalDateTime.parse(resultSet.getString("date_de_retour"))
                                : null,
                        resultSet.getBoolean("assurance"), resultSet.getBoolean("usure_journalier"),
                        resultSet.getInt("essence_manquant"), resultSet.getInt("depart_km"),
                        resultSet.getInt("retour_km"), reservation.getNoteReservation(),
                        resultSet.getFloat("estimation_reparation"),
                        resultSet.getString("note_retour") != null ? resultSet.getString("note_retour") : "");

                result.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * @param locationId
     * @return Liste des paiements effectués pour la location passée en argument
     */
    public static List<Paiement> retrievePaiements(int locationId) {
        List<Paiement> result = new ArrayList<Paiement>();
        try (Connection connection = DataAccess.getConnection()) {

            String query = "SELECT P.id, P.location_id, P.montant, P.methode, P.note " + "FROM Paiements P "
                    + "WHERE P.location_id IS ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, locationId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Paiement reservation = new Paiement(resultSet.getInt("id"), resultSet.getInt("location_id"),
                        resultSet.getInt("montant"), resultSet.getInt("methode"), resultSet.getString("note"));

                result.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * Methode pour mettre a jour une location
     *
     * @param location la reservation avec les nouvelle valeurs.
     * @return si la mise a jour a fonctionnee.
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
            statement.setInt(12, location.getId());
            statement.execute();

            updatePaiements(location);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Ajoute un paiement pour une location
     * @param location location ciblee par la methode
     * @return
     */
    private static Location updatePaiements(Location location) {
        List<Paiement> savedPaiements = retrievePaiement(location.getId());
        for (int i = 0; i < location.getPaiements().size(); i++) {
            Paiement paiement = location.getPaiements().get(i);
            if (!savedPaiements.contains(paiement)) {
                try (Connection connection = DataAccess.getConnection()) {

                    String query = "INSERT INTO Paiements (location_id, montant, methode, note) values (?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                    statement.setFloat(1, location.getId());
                    statement.setFloat(2, paiement.getMontant());
                    statement.setInt(3, paiement.getMethode());
                    statement.setString(4, paiement.getNote());

                    statement.execute();

                    ResultSet keys = statement.getGeneratedKeys();
                    keys.next();
                    paiement.setId(keys.getInt(1));
                    location.getPaiements().set(i, paiement);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return location;
    }

    /**
     * Methode pour supprimer une location
     *
     * @param locationId le id de la location a supprimer.
     * @return si la suppression a fonctionnee.
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
