package at.fhhagenberg.swe4.campinaAsAService.gui;

import at.fhhagenberg.swe4.campinaAsAService.controller.OrderController;
import at.fhhagenberg.swe4.campinaAsAService.models.OrderViewModel;

/**
 * Master-Detail View for Order
 * 
 * @author Wolfgang
 *
 */
public class ShowOrdersView extends
		BasicMasterDetailView<OrderViewModel> {

	private static ShowOrdersView instance;

	private ShowOrdersView() {
		controller = new OrderController();
		fillTableData();
		generateDetailSection(false);
		registerEvents();
	}

	static ShowOrdersView getInstance() {
		if (instance == null) {
			instance = new ShowOrdersView();
		}
		return instance;
	}
}
