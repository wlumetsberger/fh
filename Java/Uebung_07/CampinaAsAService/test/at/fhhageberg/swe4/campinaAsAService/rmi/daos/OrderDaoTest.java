package at.fhhageberg.swe4.campinaAsAService.rmi.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.OrderDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.UserDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Order;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;

public class OrderDaoTest extends BaseDaoTest {

	/**
	 * Test FindAll for User
	 */
	@Test
	public void testFindAll(){
		List<Order> orders = OrderDao.getInstance().findAll();
		assertTrue("Order.FindAll: ", orders.size() == 1);
	}
}
