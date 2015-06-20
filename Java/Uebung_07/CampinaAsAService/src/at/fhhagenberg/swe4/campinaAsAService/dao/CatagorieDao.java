package at.fhhagenberg.swe4.campinaAsAService.dao;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.helper.ModelMapper;
import at.fhhagenberg.swe4.campinaAsAService.models.CatagorieViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;
import at.fhhagenberg.swe4.campinaAsAService.rmi.service.interfaces.ServiceInterface;

/**
 * 
 * @author Wolfgang
 *
 */
public class CatagorieDao extends BaseDao<CatagorieViewModel, Catagorie> {

	@Override
	public List<CatagorieViewModel> findAll() {
		List<Catagorie> elements;
		List<CatagorieViewModel> viewModel = new ArrayList<CatagorieViewModel>();
	try {
			elements = this.getServiceInterface().findAll();

			for (Catagorie c : elements) {
				CatagorieViewModel model = ModelMapper.mapRmiToViewModel(c,
						CatagorieViewModel.class);
				viewModel.add(model);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return viewModel;
	}

	@Override
	public CatagorieViewModel save(CatagorieViewModel element) {
		Catagorie toSave = ModelMapper.mapViewModelToRmi(element,
				Catagorie.class);
		try {
			return ModelMapper.mapRmiToViewModel(this.getServiceInterface()
					.insert(toSave), CatagorieViewModel.class);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CatagorieViewModel get(CatagorieViewModel element) {
		Catagorie toFind = ModelMapper.mapViewModelToRmi(element, Catagorie.class);
		return null;
	}

	@Override
	public CatagorieViewModel remove(CatagorieViewModel element) {
		Catagorie toRemove = ModelMapper.mapViewModelToRmi(element, Catagorie.class);
		try {
			this.getServiceInterface().delete(toRemove);
			return element;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
