package data;

public class TypeParametre {
	private int TypeId;
	private String Description;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getTypeId() {
		return TypeId;
	}

	public void setTypeId(int typeId) {
		TypeId = typeId;
	}

	public TypeParametre(int typeId, String description) {
		super();
		TypeId = typeId;
		Description = description;
	}

}
