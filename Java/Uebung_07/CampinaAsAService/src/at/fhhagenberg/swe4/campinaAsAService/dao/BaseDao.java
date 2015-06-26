package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.helper.ModelMapper;
import at.fhhagenberg.swe4.campinaAsAService.models.BaseViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.BaseModel;

public abstract class BaseDao<T extends BaseViewModel, E extends BaseModel>
		implements
			Dao<T, E> {

	protected abstract Class getDataBaseModelClass();
	protected abstract Class getViewModelClass();

	@Override
	public List<T> findAll() {
		List<E> elements;
		List<T> viewModel = new ArrayList<T>();
		try {
			elements = this.getServiceInterface().findAll();

			for (E c : elements) {
				T model = ModelMapper.mapRmiToViewModel(c,
						this.getViewModelClass());
				viewModel.add(model);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return viewModel;
	}

	@Override
	public boolean save(T element) {
		T checkUpdate = this.get(element);
		if (checkUpdate != null && checkUpdate.equals(element)) {
			E toUpdate = (E) ModelMapper.mapViewModelToRmi(element,
					getDataBaseModelClass());
			try {
				return this.getServiceInterface().update(toUpdate);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		} else {
			E toSave = ModelMapper.mapViewModelToRmi(element,
					getDataBaseModelClass());
			try {
				return this.getServiceInterface().insert(toSave);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public T get(T element) {
		E toFind = ModelMapper.mapViewModelToRmi(element,
				this.getDataBaseModelClass());
		try {
			E found = this.getServiceInterface().find(toFind);
			if (found != null) {
				return ModelMapper.mapRmiToViewModel(found, element.getClass());

			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean remove(T element) {
		E toRemove = ModelMapper.mapViewModelToRmi(element,
				this.getDataBaseModelClass());
		try {
			return this.getServiceInterface().delete(toRemove);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

}
