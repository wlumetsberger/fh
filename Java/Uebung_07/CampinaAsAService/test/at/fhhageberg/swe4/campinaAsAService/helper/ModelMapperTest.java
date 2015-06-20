package at.fhhageberg.swe4.campinaAsAService.helper;



import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import at.fhhagenberg.swe4.campinaAsAService.dao.OrderDao;
import at.fhhagenberg.swe4.campinaAsAService.gui.CatagorieView;
import at.fhhagenberg.swe4.campinaAsAService.helper.ModelMapper;
import at.fhhagenberg.swe4.campinaAsAService.models.CatagorieViewModel;
import at.fhhagenberg.swe4.campinaAsAService.models.OrderViewModel;
import at.fhhagenberg.swe4.campinaAsAService.models.UserViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;

public class ModelMapperTest {

	@Test
	public void testModelMapper_01(){
		Catagorie model = new Catagorie(1, "Test", "Description");
		CatagorieViewModel viewModel;
		viewModel = ModelMapper.mapRmiToViewModel(model, CatagorieViewModel.class);
		System.out.println(viewModel);
		Catagorie mapBack = ModelMapper.mapViewModelToRmi(viewModel, Catagorie.class);
		System.out.println(mapBack + "id" +  mapBack.getId());
		assertTrue(model.equals(mapBack));
	}
	
	@Test
	public void testModelMapper_02(){
		List<OrderViewModel> orders = (new OrderDao()).findAll();
		UserViewModel user = new  UserViewModel("Wolfgang", "Lumetsberger", "test", "test", true);
		user.setOrders(orders);
		
		User dbModel = ModelMapper.mapViewModelToRmi(user, User.class);
		
		System.out.println(dbModel);
	}
}
