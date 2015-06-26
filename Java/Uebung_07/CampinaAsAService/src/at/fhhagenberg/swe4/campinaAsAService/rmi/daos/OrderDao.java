package at.fhhagenberg.swe4.campinaAsAService.rmi.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import at.fhhagenberg.swe4.campinaAsAService.helper.DBUtil;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Order;

public class OrderDao extends BaseDao<Order> {

	private static OrderDao instance;

	private OrderDao() {
		super();
	}
	/**
	 * get Instance of Dao
	 * @return
	 */
	public static OrderDao getInstance() {
		if (instance == null) {
			instance = new OrderDao();
		}
		return instance;
	}
	/**
	 * find all Orders
	 */
	@Override
	public List<Order> findAll() {
		List<Order> retVal;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(
					DBUtil.generateSelect(Order.class));
			retVal = this.executeSelectStatment(stmt, Order.class);
			stmt.close();
			return retVal;
		} catch (SQLException e) {
			log.log(Level.SEVERE,"Cannot Update Statment: ", e);
		}
		return new ArrayList<Order>();
	}

}
