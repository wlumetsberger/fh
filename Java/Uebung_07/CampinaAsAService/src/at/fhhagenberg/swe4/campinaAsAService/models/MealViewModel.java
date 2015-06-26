package at.fhhagenberg.swe4.campinaAsAService.models;

import java.time.LocalDateTime;

import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.MealDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Meal;

/**
 * 
 * @author Wolfgang
 *
 */
public class MealViewModel implements BaseViewModel {

	private Integer id;
	@ViewProperty(name = "Name")
	private String name;
	@ViewProperty(name = "Description")
	private String description;
	@ViewProperty(name = "Catagorie", isComboBox = true, isTextField = false)
	private CatagorieViewModel catagorie;
	@ViewProperty(name = "Date From", isTextField = false)
	private LocalDateTime dateFrom;
	@ViewProperty(name = "Date To", isTextField = false)
	private LocalDateTime dateTo;
	@ViewProperty(name = "Price")
	private Double price;

	public MealViewModel() {
	}

	public MealViewModel(int mealId, String name, String description,
			CatagorieViewModel catagorie, LocalDateTime dateFrom,
			LocalDateTime dateTo, Double price) {
		super();
		this.id = mealId;
		this.name = name;
		this.description = description;
		this.catagorie = catagorie;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.price = price;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MealViewModel other = (MealViewModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer mealId) {
		this.id = mealId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CatagorieViewModel getCatagorie() {
		return catagorie;
	}

	public void setCatagorie(CatagorieViewModel catagorie) {
		this.catagorie = catagorie;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public Dao getDao() {
		return new MealDao();
	}
	@Override
	public Class getDbModell() {
		return Meal.class;
	}
}
