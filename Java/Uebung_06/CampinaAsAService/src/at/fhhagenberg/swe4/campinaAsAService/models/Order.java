package at.fhhagenberg.swe4.campinaAsAService.models;


import java.time.LocalDateTime;
import java.util.Date;

import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.dao.OrderDao;

/**
 * 
 * @author Wolfgang
 *
 */
public class Order implements BaseModel{

	@ViewProperty(name="User")
	private User user;
	@ViewProperty(name="Meal")
	private Meal meal;
	@ViewProperty(name="Order Date")
	private LocalDateTime orderDate;
	@ViewProperty(name="Serve Date")
	private LocalDateTime serveDate;
	@ViewProperty(name="Additional Text")
	private String additionalText;
	
	public Order() {
	}

	public Order(User user, Meal meal, LocalDateTime orderDate, LocalDateTime serveDate,
			String additionalText) {
		super();
		this.user = user;
		this.meal = meal;
		this.orderDate = orderDate;
		this.serveDate = serveDate;
		this.additionalText = additionalText;
	}

	
	
	@Override
	public String toString() {
		return "Order [user=" + user + ", meal=" + meal + ", orderDate="
				+ "additionalText="
				+ additionalText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((additionalText == null) ? 0 : additionalText.hashCode());
		result = prime * result + ((meal == null) ? 0 : meal.hashCode());
		result = prime * result
				+ ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result
				+ ((serveDate == null) ? 0 : serveDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Order other = (Order) obj;
		if (additionalText == null) {
			if (other.additionalText != null)
				return false;
		} else if (!additionalText.equals(other.additionalText))
			return false;
		if (meal == null) {
			if (other.meal != null)
				return false;
		} else if (!meal.equals(other.meal))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (serveDate == null) {
			if (other.serveDate != null)
				return false;
		} else if (!serveDate.equals(other.serveDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getServeDate() {
		return serveDate;
	}

	public void setServeDate(LocalDateTime serveDate) {
		this.serveDate = serveDate;
	}

	public String getAdditionalText() {
		return additionalText;
	}

	public void setAdditionalText(String additionalText) {
		this.additionalText = additionalText;
	}
	
	@Override
	public Dao getDao() {
		return  new OrderDao();
	}
	
}
