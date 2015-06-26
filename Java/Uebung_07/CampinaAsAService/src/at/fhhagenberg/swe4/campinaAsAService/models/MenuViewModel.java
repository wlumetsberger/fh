package at.fhhagenberg.swe4.campinaAsAService.models;

import java.time.LocalDateTime;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.MenuDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Menu;

/**
 * 
 * @author Wolfgang
 *
 */
public class MenuViewModel implements BaseViewModel {

	@ViewProperty(name = "Name")
	private String name;
	@ViewProperty(name = "Description")
	private String description;
	@ViewProperty(name = "Date From")
	private LocalDateTime dateFrom;
	@ViewProperty(name = "Date To")
	private LocalDateTime dateTo;
	@ViewProperty(name = "MealList", showInDefaultModel = false)
	private List<MealViewModel> meals;

	public MenuViewModel(String name, String description,
			LocalDateTime dateFrom, LocalDateTime dateTo,
			List<MealViewModel> meals) {
		super();
		this.name = name;
		this.description = description;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.meals = meals;
	}

	public MenuViewModel() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		MenuViewModel other = (MenuViewModel) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", description=" + description
				+ ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", meals="
				+ meals + "]";
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

	public List<MealViewModel> getMeals() {
		return meals;
	}

	public void setMeals(List<MealViewModel> meals) {
		this.meals = meals;
	}

	@Override
	public Dao getDao() {
		return new MenuDao();
	}
	@Override
	public Class getDbModell() {
		return Menu.class;
	}
}
