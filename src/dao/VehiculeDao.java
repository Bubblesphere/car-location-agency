package dao;

import data.Classe;
import data.Vehicule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehiculeDao {

  /**
   * Méthode pour créé un véhicule
   * 
   * @param vehicule
   *          véhicule à créé.
   * @return le véhicule qui a été créé avec son id de mis à jour.
   */
  public static Vehicule create(Vehicule vehicule) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "INSERT INTO Vehicules (classe_id, fabriquant, "
          + "marque, annee, kilometrage, etat, plaque, desactive, "
          + "capacite_essence, note) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query,
          Statement.RETURN_GENERATED_KEYS);
      statement.setInt(1, vehicule.getVClasse().getId());
      statement.setString(2, vehicule.getFabricant());
      statement.setString(3, vehicule.getMarque());
      statement.setInt(4, vehicule.getAnnee());
      statement.setInt(5, vehicule.getKilometrage());
      statement.setInt(6, vehicule.getEtat());
      statement.setString(7, vehicule.getPlaque());
      statement.setBoolean(8, vehicule.getDesactive());
      statement.setString(9, vehicule.getNote());
      statement.execute();

      ResultSet keys = statement.getGeneratedKeys();
      keys.next();
      vehicule.setId(keys.getInt(1));

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return vehicule;
  }

  /**
   * Méthode pour récupérer un véhicule
   * 
   * @param vehiculeId
   *          id du véhicule à récupérer.
   * @return le véhicule récupérer.
   */
  public static Vehicule retrieve(int vehiculeId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT V.id, V.classe_id, V.fabriquant, V.marque, "
          + "V.annee, V.kilometrage, V.etat, V.plaque, V.desactive, "
          + "V.capacite_essence, V.note , C.nom AS nom_classe, "
          + "C.prix_journalier AS prix_classe FROM Vehicules V "
          + "LEFT JOIN Classes C ON V.classe_id = C.id WHERE V.id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, vehiculeId);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        Classe classe = ClasseDao.retrieve(resultSet.getInt("classe_id"));
        Vehicule vehicule = new Vehicule(resultSet.getInt("id"), classe.getId(), classe,
            resultSet.getString("fabriquant"), resultSet.getString("marque"),
            resultSet.getInt("annee"), resultSet.getInt("kilometrage"), resultSet.getInt("etat"),
            resultSet.getString("plaque"), resultSet.getBoolean("desactive"),
            resultSet.getInt("capacite_essence"), resultSet.getString("note"));
        return vehicule;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Méthode pour récupérer toutes les véhicules
   * 
   * @return liste de toutes les véhicules.
   */
  public static List<Vehicule> retrieveAll() {
    List<Vehicule> result = new ArrayList<Vehicule>();
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT V.id, V.classe_id, V.fabriquant, V.marque, "
          + "V.annee, V.kilometrage, V.etat, V.plaque, V.desactive, "
          + "V.capacite_essence, V.note , C.nom AS nom_classe, "
          + "C.prix_journalier AS prix_classe FROM Vehicules V "
          + "LEFT JOIN Classes C ON C.id = V.classe_id";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        Classe classe = ClasseDao.retrieve(resultSet.getInt("classe_id"));
        Vehicule vehicule = new Vehicule(resultSet.getInt("id"), classe.getId(), classe,
            resultSet.getString("fabriquant"), resultSet.getString("marque"),
            resultSet.getInt("annee"), resultSet.getInt("kilometrage"), resultSet.getInt("etat"),
            resultSet.getString("plaque"), resultSet.getBoolean("desactive"),
            resultSet.getInt("capacite_essence"), resultSet.getString("note"));

        result.add(vehicule);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return result;
  }

  /**
   * Méthode pour mettre à jour un véhicule
   * 
   * @param vehicule
   *          le véhicule avec les nouvelle valeurs.
   * @return si la mise à jour a fonctionnée.
   */
  public static boolean update(Vehicule vehicule) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "UPDATE Vehicules SET classe_id = ?, fabriquant = ?, "
          + "marque = ?, annee = ?, kilometrage = ?, etat = ?, plaque = ?, "
          + "desactive = ?, capacite_essence = ?, note = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, vehicule.getVClasse().getId());
      statement.setString(2, vehicule.getFabricant());
      statement.setString(3, vehicule.getMarque());
      statement.setInt(4, vehicule.getAnnee());
      statement.setInt(5, vehicule.getKilometrage());
      statement.setInt(6, vehicule.getEtat());
      statement.setString(7, vehicule.getPlaque());
      statement.setBoolean(8, vehicule.getDesactive());
      statement.setInt(9, vehicule.getCapaciteEssence());
      statement.setString(10, vehicule.getNote());
      statement.setInt(11, vehicule.getId());
      statement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Méthode pour supprimer un véhicule
   * 
   * @param vehiculeId
   *          le id du véhicule à supprimer.
   * @return si la suppression à fonctionnée.
   */
  public static boolean delete(int vehiculeId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "DELETE FROM Vehicules WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, vehiculeId);
      statement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}