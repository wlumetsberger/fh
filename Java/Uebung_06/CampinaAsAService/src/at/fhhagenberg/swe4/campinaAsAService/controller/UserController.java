package at.fhhagenberg.swe4.campinaAsAService.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.DataLine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.models.User;

public class UserController extends Controller<User>{
	
	
	@Override
	public ObservableList<User> loadDataList() {
		ObservableList<User> list =  FXCollections.observableArrayList();
		list.add(new User("Wolfgang", "Lumetsberger", "wolfgang.lumetsberger@gmail.com","passphrase",false));
		list.add(new User("Max", "Mustermann", "max.mustermann@gmail.com","maxi123",true));
		return list;
	}

	@Override
	public void saveDetail() {
		if(!this.dataList.contains(detailData)){
			this.dataList.add(detailData);
		}
		detailData = new User();
		
	}
	@Override
	public Class getDataClass() {
		return User.class;
	}
	@Override
	public User newDataInstance() {
		return new User();
	}
	
}
