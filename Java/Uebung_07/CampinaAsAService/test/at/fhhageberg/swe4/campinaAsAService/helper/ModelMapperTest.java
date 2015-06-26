package at.fhhageberg.swe4.campinaAsAService.helper;



import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import at.fhhagenberg.swe4.campinaAsAService.dao.OrderDao;
import at.fhhagenberg.swe4.campinaAsAService.gui.CatagorieView;
import at.fhhagenberg.swe4.campinaAsAService.helper.ModelMapper;
import at.fhhagenberg.swe4.campinaAsAService.models.CatagorieViewModel;
import at.fhhagenberg.swe4.campinaAsAService.models.MealViewModel;
import at.fhhagenberg.swe4.campinaAsAService.models.OrderViewModel;
import at.fhhagenberg.swe4.campinaAsAService.models.UserViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Meal;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.User;

public class ModelMapperTest {

	@Test
	public void testModelMapper_01(){
		Catagorie model = new Catagorie(1, "Test", "Description");
		CatagorieViewModel viewModel;
		viewModel = ModelMapper.mapRmiToViewModel(model, CatagorieViewModel.class);
		
		Catagorie mapBack = ModelMapper.mapViewModelToRmi(viewModel, Catagorie.class);
		
		assertTrue(model.equals(mapBack));
		
		Meal m = new Meal(1, "meal1", "mealDesc1", model, LocalDateTime.now(), LocalDateTime.now(), 10.0);
		MealViewModel view;
		view = ModelMapper.mapRmiToViewModel(m, MealViewModel.class);
		
		Meal mealBack = ModelMapper.mapViewModelToRmi(view, Meal.class);
		assertTrue(m.equals(mealBack));
		
	}
	@Test
	public void testModelMapper_02(){
		Catagorie model = new Catagorie(1, "Test", "Description");
		Meal m = new Meal(1, "meal1", "mealDesc1", model, LocalDateTime.now(), LocalDateTime.now(), 10.0);
		MealViewModel view;
		view = ModelMapper.mapRmiToViewModel(m, MealViewModel.class);
		Meal mealBack = ModelMapper.mapViewModelToRmi(view, Meal.class);
		assertTrue(m.equals(mealBack));
		
	}
}
