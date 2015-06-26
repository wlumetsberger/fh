package at.fhhagenberg.swe4.campinaAsAService.controller;

import javafx.collections.ObservableList;
import at.fhhagenberg.swe4.campinaAsAService.dao.Dao;
import at.fhhagenberg.swe4.campinaAsAService.models.BaseViewModel;

/**
 * 
 * @author Wolfgang
 *
 * @param <T>
 */
public abstract class Controller<T extends BaseViewModel> {

	protected T detailData;
	protected ObservableList<T> dataList;

	public Controller() {
		detailData = newDataInstance();
		dataList = loadDataList();
	}

	public abstract ObservableList<T> loadDataList();

	public abstract Class getDataClass();

	public abstract T newDataInstance();

	public void refreshDataList() {
		this.dataList = this.loadDataList();
	}
	public ObservableList<T> getDataList() {
		this.dataList = this.loadDataList();
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
		Dao d = detailData.getDao();
		d.save(detailData);
		detailData = newDataInstance();
		this.refreshDataList();
	}

	public void deleteDetail() {
		if (this.detailData != null && this.dataList.contains(this.detailData)) {
			Dao d = detailData.getDao();
			d.remove(detailData);
			this.refreshDataList();
		}

	}

}
