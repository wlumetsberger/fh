package at.fhhagenberg.swe4.campinaAsAService.gui;

import at.fhhagenberg.swe4.campinaAsAService.controller.UserController;
import at.fhhagenberg.swe4.campinaAsAService.models.UserViewModel;

/**
 * Master-Detail view for User
 * 
 * @author Wolfgang
 *
 */
public class UserView extends BasicMasterDetailView<UserViewModel> {

	private static UserView instance;

	private UserView() {
		controller = new UserController();
		fillTableData();
		generateDetailSection(true);
		registerEvents();

	}

	public static UserView getInstance() {
		if (instance == null) {
			instance = new UserView();
		}
		return instance;
	}

}
