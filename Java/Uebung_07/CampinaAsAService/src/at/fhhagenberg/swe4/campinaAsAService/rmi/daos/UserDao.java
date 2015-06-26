package at.fhhagenberg.swe4.campinaAsAService.rmi.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import at.fhhagenberg.swe4.campinaAsAService.helper.DBUtil;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;

public class UserDao extends BaseDao<User> {

	private static UserDao instance;

	private UserDao() {
		super();
	}

	/**
	 * get Instance of Dao
	 * @return
	 */
	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	/**
	 * find all users
	 */
	@Override
	public List<User> findAll() {
		List<User> retVal;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(
					DBUtil.generateSelect(User.class));
			retVal = this.executeSelectStatment(stmt, User.class);
			stmt.close();
			return retVal;
		} catch (SQLException e) {
			log.log(Level.SEVERE,"Cannot Update Statment: ", e);
		}
		return new ArrayList<User>();
	}
}
