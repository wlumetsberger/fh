package at.fhhageberg.swe4.campinaAsAService.rmi.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.CatagorieDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.UserDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;

public class CatagorieDaoTest extends BaseDaoTest {
	
	/**
	 * Test FindAll for Catagories
	 */
	@Test
	public void testFindAll(){
		List<Catagorie> cat = CatagorieDao.getInstance().findAll();
		assertTrue("Catagorie.FindAll: ", cat.size() == 2);
	}
}
