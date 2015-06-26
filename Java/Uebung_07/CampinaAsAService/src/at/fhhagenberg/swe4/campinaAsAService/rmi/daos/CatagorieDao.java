package at.fhhagenberg.swe4.campinaAsAService.rmi.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import at.fhhagenberg.swe4.campinaAsAService.helper.DBUtil;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;

public class CatagorieDao extends BaseDao<Catagorie> {

	private static CatagorieDao instance;

	private CatagorieDao() {
		super();
	}

	/**
	 * get Instance of Dao
	 * @return
	 */
	public static CatagorieDao getInstance() {
		if (instance == null) {
			instance = new CatagorieDao();
		}
		return instance;
	}

	/**
	 * Find all Catagories
	 */
	@Override
	public List<Catagorie> findAll() {
		List<Catagorie> retVal;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(
					DBUtil.generateSelect(Catagorie.class));
			retVal = this.executeSelectStatment(stmt, Catagorie.class);
			stmt.close();
			return retVal;
		} catch (SQLException e) {
			log.log(Level.SEVERE,"Cannot execute statment: ", e);
		}
		return new ArrayList<Catagorie>();
	}

}
