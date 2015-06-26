package at.fhhagenberg.swe4.campinaAsAService.rmi.models;

import java.time.LocalDateTime;

import at.fhhagenberg.swe4.campinaAsAService.models.OrderViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseConnections;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseFieldProperty;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseTableProperty;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.OrderDao;

/**
 * 
 * @author Wolfgang
 *
 */
@DataBaseTableProperty(tableName = "ORDER")
public class Order extends BaseModel {

	@DataBaseFieldProperty(column = "ID", key = true, generated = true)
	private Integer id;
	@DataBaseFieldProperty(foreign = true, column = "USER_ID", connectionType = DataBaseConnections.ManyToOne, foreignClass = User.class)
	private User user;
	@DataBaseFieldProperty(foreign = true, column = "MEAL_ID", connectionType = DataBaseConnections.ManyToOne, foreignClass = Meal.class)
	private Meal meal;
	@DataBaseFieldProperty(column = "ORDER_DATE")
	private LocalDateTime orderDate;
	@DataBaseFieldProperty(column = "SERVE_DATE")
	private LocalDateTime serveDate;
	@DataBaseFieldProperty(column = "ADDITIONAL_TEXT")
	private String additionalText;

	public Order() {
	}

	public Order(Integer id, User user, Meal meal, LocalDateTime orderDate,
			LocalDateTime serveDate, String additionalText) {
		super();
		this.id = id;
		this.user = user;
		this.meal = meal;
		this.orderDate = orderDate;
		this.serveDate = serveDate;
		this.additionalText = additionalText;
	}

	@Override
	public String toString() {
		return "Order [user=" + user + ", meal=" + meal + ", orderDate="
				+ "additionalText=" + additionalText + "]";
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
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer orderId) {
		this.id = orderId;
	}
	@Override
	public OrderDao getDao() {
		return OrderDao.getInstance();
	}
	@Override
	public Class getViewModell() {
		return OrderViewModel.class;
	}

}
