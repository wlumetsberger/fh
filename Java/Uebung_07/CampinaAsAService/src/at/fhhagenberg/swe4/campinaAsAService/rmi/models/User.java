package at.fhhagenberg.swe4.campinaAsAService.rmi.models;

import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.models.UserViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseConnections;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseFieldProperty;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseTableProperty;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.UserDao;

/**
 * 
 * @author Wolfgang
 *
 */
@DataBaseTableProperty(tableName = "User")
public class User extends BaseModel {

	@DataBaseFieldProperty(column = "id", key = true)
	private String id;
	@DataBaseFieldProperty(column = "first_name")
	private String firstName;
	@DataBaseFieldProperty(column = "last_name")
	private String lastName;
	@DataBaseFieldProperty(column = "password")
	private String password;
	@DataBaseFieldProperty(column = "locked")
	private boolean locked;
	@DataBaseFieldProperty(column = "order_id", foreign = true, connectionType = DataBaseConnections.OneToMany, foreignClass = Order.class)
	private List<Order> orders;

	public User() {
	}

	public User(String firstName, String lastName, String email,
			String password, boolean locked) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = email;
		this.password = password;
		this.locked = locked;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String email) {
		this.id = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (locked ? 1231 : 1237);
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (locked != other.locked)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	@Override
	public UserDao getDao() {
		return UserDao.getInstance();
	}
	@Override
	public Class getViewModell() {
		return UserViewModel.class;
	}
}
