package at.fhhagenberg.swe4.campinaAsAService.controller;

import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.models.BaseModel;
import at.fhhagenberg.swe4.campinaAsAService.models.User;
/**
 * 
 * @author Wolfgang
 *
 * @param <T>
 */
public abstract class Controller<T extends BaseModel> {

	protected T detailData;
	protected ObservableList<T> dataList;
	
	public Controller() {
		detailData = newDataInstance();
		dataList = loadDataList();
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
		Dao<T> d = detailData.getDao();
	    d.save(detailData);
		detailData = newDataInstance();
		
	}
	public void deleteDetail(){
		if(this.detailData != null && this.dataList.contains(this.detailData)){
			this.dataList.remove(this.detailData);
		}
		Dao d = detailData.getDao();
	    d.remove(detailData);
	}
	
}
