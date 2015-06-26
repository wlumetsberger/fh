package at.fhhagenberg.swe4.campinaAsAService.rmi.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import at.fhhagenberg.swe4.campinaAsAService.helper.DBUtil;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Menu;

public class MenuDao extends BaseDao<Menu> {

	private static MenuDao instance;

	private MenuDao() {
		super();
	}
	/**
	 * get Instance of Menu
	 * @return
	 */
	public static MenuDao getInstance() {
		if (instance == null) {
			instance = new MenuDao();
		}
		return instance;
	}
	/**
	 * find all Menus
	 */
	@Override
	public List<Menu> findAll() {
		List<Menu> retVal;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(
					DBUtil.generateSelect(Menu.class));
			retVal = this.executeSelectStatment(stmt, Catagorie.class);
			stmt.close();
			return retVal;
		} catch (SQLException e) {
			log.log(Level.SEVERE,"Cannot Update Statment: ", e);
		}
		return new ArrayList<Menu>();
	}
}
