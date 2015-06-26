package at.fhhageberg.swe4.campinaAsAService.rmi.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.MenuDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.UserDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Menu;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;

public class MenuDaoTest extends BaseDaoTest {

	/**
	 * Test FindAll for Menu
	 */
	@Test
	public void testFindAll(){
		List<Menu> menu = MenuDao.getInstance().findAll();
		assertTrue("Menu.FindAll: ", menu.size() == 1);
	}
}
