package at.fhhageberg.swe4.campinaAsAService.rmi.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;









import at.fhhagenberg.swe4.campinaAsAService.helper.DBUtil;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.BaseDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.CatagorieDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.MealDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.MenuDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.OrderDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.UserDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.BaseModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Meal;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Menu;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Order;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;

public class BaseDaoTest {

	private void resetTables() throws SQLException{
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = con.prepareStatement("DELETE FROM `ORDER`");
		stmt.execute();
		stmt.close();
		stmt = con.prepareStatement("DELETE FROM MENU");
		stmt.execute();
		stmt.close();
		stmt = con.prepareStatement("DELETE FROM MEAL");
		stmt.execute();
		stmt.close();
		stmt = con.prepareStatement("DELETE FROM CATAGORIE");
		stmt.execute();
		stmt.close();
		stmt = con.prepareStatement("DELETE FROM USER");
		stmt.execute();
		stmt.close();
	}
	@Before
	public void initDB() throws SQLException{
		
		this.resetTables();
		
		User u = new User("Wolfgang","Lumetsberger", "w.lumetsberger@aon.at","passphrase", false);
		UserDao.getInstance().insert(u);
		Catagorie c = new Catagorie(null,"Vegetarisch","Vegetarische Speisen");
		CatagorieDao.getInstance().insert(c);
		Catagorie c2 = new Catagorie(null,"Fleisch","Fleisch");
		CatagorieDao.getInstance().insert(c2);
		
		List<Catagorie> catagories = CatagorieDao.getInstance().findAll();
		
		Meal m = new Meal(null,"Wiener Schnitzel","Wiener Schnitzel vom Schwein",catagories.get(1),LocalDateTime.now().minusDays(10), LocalDateTime.now().plusDays(30),15.0);
		Meal m2 = new Meal(null,"Gemuese Laibchen","Gemuese Laibchen",catagories.get(0),LocalDateTime.now().minusDays(10), LocalDateTime.now().plusDays(30),14.5);
		
		MealDao.getInstance().insert(m);
		MealDao.getInstance().insert(m2);
		
		List<Meal> meals = MealDao.getInstance().findAll();
		Menu menu = new Menu("Taegliches Menue", "Taegliches Menue", LocalDateTime.now().minusDays(10), LocalDateTime.now().plusDays(30), meals);
		MenuDao.getInstance().insert(menu);
		
		Order o1 = new Order(null, u, meals.get(0), LocalDateTime.now().minusMinutes(30), LocalDateTime.now().plusMinutes(30), "");
		OrderDao.getInstance().insert(o1);
	}
}
