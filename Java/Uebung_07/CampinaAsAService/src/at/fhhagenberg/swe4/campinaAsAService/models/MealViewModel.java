package at.fhhagenberg.swe4.campinaAsAService.models;

import java.time.LocalDateTime;

import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.MealDao;

/**
 * 
 * @author Wolfgang
 *
 */
public class MealViewModel implements BaseViewModel {

	private int id;
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

	public MealViewModel(int mealId, String name,
			String description,
			CatagorieViewModel catagorie,
			LocalDateTime dateFrom,
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
		result = prime
				* result
				+ ((catagorie == null) ? 0
						: catagorie.hashCode());
		result = prime
				* result
				+ ((dateFrom == null) ? 0
						: dateFrom.hashCode());
		result = prime
				* result
				+ ((dateTo == null) ? 0
						: dateTo.hashCode());
		result = prime
				* result
				+ ((description == null) ? 0
						: description.hashCode());
		result = prime * result + id;
		result = prime
				* result
				+ ((name == null) ? 0 : name
						.hashCode());
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
		if (catagorie == null) {
			if (other.catagorie != null)
				return false;
		} else if (!catagorie
				.equals(other.catagorie))
			return false;
		if (dateFrom == null) {
			if (other.dateFrom != null)
				return false;
		} else if (!dateFrom
				.equals(other.dateFrom))
			return false;
		if (dateTo == null) {
			if (other.dateTo != null)
				return false;
		} else if (!dateTo
				.equals(other.dateTo))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description
				.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int mealId) {
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

	public void setDescription(
			String description) {
		this.description = description;
	}

	public CatagorieViewModel getCatagorie() {
		return catagorie;
	}

	public void setCatagorie(
			CatagorieViewModel catagorie) {
		this.catagorie = catagorie;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(
			LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(
			LocalDateTime dateTo) {
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
}
