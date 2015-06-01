package at.fhhagenberg.swe4.campinaAsAService.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.models.User;

public class UserController {
	
	private ObservableList<User> users;
	private User detailUser;
	
	public UserController() {
		users = loadUsers();
	}
	
	/**
	 * used to load Users e.g. form DB or File
	 * @return
	 */
	public ObservableList<User> loadUsers(){
		ObservableList<User> list =  FXCollections.observableArrayList();
		list.add(new User("Wolfgang", "Lumetsberger", "wolfgang.lumetsberger@gmail.com","passphrase",false));
		list.add(new User("Max", "Mustermann", "max.mustermann@gmail.com","maxi123",true));
		return list;
	}
	
	/**
	 * Save action 
	 */
	public void saveUser(){
		this.users.add(detailUser);
		detailUser = null;
	}
	
	public User getDetailUser() {
		return detailUser;
	}
	public void setDetailUser(User detailUser) {
		this.detailUser = detailUser;
	}
	public ObservableList<User> getUsers() {
		return users;
	}
	public void setUsers(ObservableList<User> users) {
		this.users = users;
	}
}
