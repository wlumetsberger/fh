package at.fhhagenberg.swe4.campinaAsAService.models;

import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.dao.CatagorieDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;

/**
 * 
 * @author Wolfgang
 *
 */
public class CatagorieViewModel implements BaseViewModel {

	@ViewProperty(name = "ID", editable = false)
	private Integer id;
	@ViewProperty(name = "Catagorie")
	private String catagorieName;
	@ViewProperty(name = "Description")
	private String descirption;

	public CatagorieViewModel() {
	}

	public CatagorieViewModel(int id, String catagorieName, String descirption) {
		super();
		this.catagorieName = catagorieName;
		this.descirption = descirption;
		this.id = id;
	}

	@Override
	public String toString() {
		return catagorieName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			return false;
		}
		CatagorieViewModel other = (CatagorieViewModel) obj;
		if (!this.getId().equals(other.getId())) {
			return false;
		}

		return true;
	}

	public String getCatagorieName() {
		return catagorieName;
	}

	public void setCatagorieName(String catagorieName) {
		this.catagorieName = catagorieName;
	}

	public String getDescirption() {
		return descirption;
	}

	public void setDescirption(String descirption) {
		this.descirption = descirption;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Create instance of Dao
	 */
	public CatagorieDao getDao() {
		return new CatagorieDao();
	}
	@Override
	public Class getDbModell() {
		return Catagorie.class;
	}

}
