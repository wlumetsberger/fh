package at.fhhagenberg.swe4.campinaAsAService.controller;

import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.models.User;

public abstract class Controller<T> {

	protected T detailData;
	protected ObservableList<T> dataList;
	
	public Controller() {
		dataList = loadDataList();
		detailData = newDataInstance();
	}
	
	public abstract ObservableList<T> loadDataList();
	public abstract Class getDataClass();
	public abstract T newDataInstance();
	
	public ObservableList<T> getDataList() {
		return dataList;
	}
	public void setDataList(ObservableList<T> dataList) {
		this.dataList = dataList;
	}
	public T getDetailData() {
		return detailData;
	}
	public void setDetailData(T detailData) {
		this.detailData = detailData;
	}
	public void saveDetail() {
		if(!this.dataList.contains(detailData)){
			this.dataList.add(detailData);
		}
		detailData = newDataInstance();
		
	}
	public void deleteDetail(){
		if(this.detailData != null && this.dataList.contains(this.detailData)){
			this.dataList.remove(this.detailData);
		}
		detailData = newDataInstance();
	}
	
}
