package at.fhhagenberg.swe4.campinaAsAService.rmi.models;

import java.time.LocalDateTime;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.MenuDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseConnections;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseFieldProperty;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseTableProperty;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.BaseDao;

/**
 * 
 * @author Wolfgang
 *
 */
@DataBaseTableProperty(tableName="Menu")
public class Menu extends BaseModel{
	
	@DataBaseFieldProperty(column="name",key=true)	
	private String name;
	@DataBaseFieldProperty(column="description")
	private String description;
	@DataBaseFieldProperty(column="dateFrom")
	private LocalDateTime dateFrom;
	@DataBaseFieldProperty(column="dateTo")
	private LocalDateTime dateTo;
	@DataBaseFieldProperty(column="meal_id", connectionType=DataBaseConnections.OneToMany, foreignClass=Meal.class)
	private List<Meal> meals;

	public Menu(String name,
			String description,
			LocalDateTime dateFrom,
			LocalDateTime dateTo,
			List<Meal> meals) {
		super();
		this.name = name;
		this.description = description;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.meals = meals;
	}

	public Menu() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		result = prime
				* result
				+ ((meals == null) ? 0 : meals
						.hashCode());
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
		Menu other = (Menu) obj;
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
		if (meals == null) {
			if (other.meals != null)
				return false;
		} else if (!meals
				.equals(other.meals))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name
				+ ", description="
				+ description + ", dateFrom="
				+ dateFrom + ", dateTo="
				+ dateTo + ", meals=" + meals
				+ "]";
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

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
	
	@Override
	public BaseDao getDao() {
		return null;
	}

}
