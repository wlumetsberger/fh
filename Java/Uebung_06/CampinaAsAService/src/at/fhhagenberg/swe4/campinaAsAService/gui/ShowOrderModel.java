package at.fhhagenberg.swe4.campinaAsAService.gui;

import java.util.Date;

import at.fhhagenberg.swe4.campinaAsAService.annotations.ViewProperty;
import at.fhhagenberg.swe4.campinaAsAService.models.Order;

public class ShowOrderModel {

	@ViewProperty(name="Menu")
	private String menu;
	@ViewProperty(name="User")
	private String user;
	@ViewProperty(name="Additional Text")
	private String additionalText;
	@ViewProperty(name="Serve at", isTextField=false)
	private Date serveDate;
	
	public ShowOrderModel() {
	}
	public ShowOrderModel(Order order){
		this.menu = order.getMeal().getName();
		this.user = order.getUser().getFirstName() +" " + order.getUser().getLastName();
		this.additionalText = order.getAdditionalText();
		this.serveDate = order.getServeDate();
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getAdditionalText() {
		return additionalText;
	}
	public void setAdditionalText(String additionalText) {
		this.additionalText = additionalText;
	}
	public Date getServeDate() {
		return serveDate;
	}
	public void setServeDate(Date serveDate) {
		this.serveDate = serveDate;
	}
	
	
	
}
