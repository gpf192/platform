package cn.xsdzq.platform.model;

public class CategoryDTO implements Comparable<CategoryDTO> {
	private long id;
	private String title;
	private String image;
	private int categorysort;
	private String exp;
	private boolean displayFlag;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCategorysort() {
		return categorysort;
	}

	public void setCategorysort(int categorysort) {
		this.categorysort = categorysort;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public boolean getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(boolean displayFlag) {
		this.displayFlag = displayFlag;
	}

	@Override
	public int compareTo(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		if (this.categorysort > categoryDTO.getCategorysort()) {
			return 1;
		} else {
			return -1;
		}
	}

}
