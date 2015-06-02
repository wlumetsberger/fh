package at.fhhagenberg.swe4.campinaAsAService.gui;

import at.fhhagenberg.swe4.campinaAsAService.controller.CatagorieController;
import at.fhhagenberg.swe4.campinaAsAService.controller.UserController;
import at.fhhagenberg.swe4.campinaAsAService.models.Catagorie;

public class CatagorieView extends BasicMasterDetailView<Catagorie>{

	private static CatagorieView instance;

	private CatagorieView() {
		controller = new CatagorieController();
		fillTableData();
		generateDetailSection(true);
		registerEvents();

	}

	public static CatagorieView getInstance() {
		if (instance == null) {
			instance = new CatagorieView();
		}
		return instance;
	}
}