package at.fhhageberg.swe4.campinaAsAService.rmi.daos;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.UserDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;

public class UserDaoTest extends BaseDaoTest{

	/**
	 * Test FindAll for User
	 */
	@Test
	public void testFindAll(){
		List<User> users = UserDao.getInstance().findAll();
		assertTrue("User.FindAll: ", users.size() == 1);
	}
}
