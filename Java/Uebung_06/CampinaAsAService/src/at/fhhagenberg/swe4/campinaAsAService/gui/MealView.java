package at.fhhagenberg.swe4.campinaAsAService.gui;

import at.fhhagenberg.swe4.campinaAsAService.controller.CatagorieController;
import at.fhhagenberg.swe4.campinaAsAService.controller.MealController;
import at.fhhagenberg.swe4.campinaAsAService.models.Meal;
/**
 * Master-Detail View Class for Meal;
 * @author Wolfgang
 *
 */
public class MealView extends BasicMasterDetailView<Meal> {
	private static MealView instance;

	private MealView() {
		controller = new MealController();
		fillTableData();
		generateDetailSection(true);
		registerEvents();

	}

	public static MealView getInstance() {
		if (instance == null) {
			instance = new MealView();
		}
		return instance;
	}
}
