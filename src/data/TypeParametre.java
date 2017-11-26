package data;

public class TypeParametre {
  private int typeId;
  private String description;

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getTypeId() {
    return this.typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  /**
   * Constructeur pour TypeParametre
   * 
   * @param typeId
   *          id du type du type de paramètre.
   * @param description
   *          description du type de paramètre.
   */
  public TypeParametre(int typeId, String description) {
    super();
    this.typeId = typeId;
    this.description = description;
  }
  
  public TypeParametre(int typeId) {
    super();
    this.typeId = typeId;    
  }

}
