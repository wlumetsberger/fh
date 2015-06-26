package at.fhhageberg.swe4.campinaAsAService.rmi.daos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.MealDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.daos.UserDao;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Meal;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;

public class MealDaoTest extends BaseDaoTest {

	/**
	 * Test FindAll for Meal
	 */
	@Test
	public void testFindAll(){
		List<Meal> meals = MealDao.getInstance().findAll();
		assertTrue("Meal.FindAll: ", meals.size() == 2);
	}
}
