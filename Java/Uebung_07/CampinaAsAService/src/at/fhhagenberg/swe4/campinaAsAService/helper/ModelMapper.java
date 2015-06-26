package at.fhhagenberg.swe4.campinaAsAService.helper;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import at.fhhagenberg.swe4.campinaAsAService.models.BaseViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.BaseModel;

public class ModelMapper {

	/**
	 * Map Any ViewModel to DataBaseModel
	 * @param viewModel
	 * @param rmiModelClass
	 * @return
	 */
	public static <T extends BaseModel> T mapViewModelToRmi(
			BaseViewModel viewModel, Class rmiModelClass) {

		try {
			T rmiModel = (T) rmiModelClass.newInstance();
			PropertyDescriptor[] viewModelFields = Introspector.getBeanInfo(
					rmiModelClass).getPropertyDescriptors();
			for (PropertyDescriptor property : Introspector.getBeanInfo(
					viewModel.getClass()).getPropertyDescriptors()) {
				for (PropertyDescriptor viewProp : viewModelFields) {
					if (property.getName().equals(viewProp.getName())
							&& !property.getName().equals("class")
							&& !property.getName().equals("dao")) {
						Object value = property.getReadMethod().invoke(
								viewModel);
						if (value instanceof BaseViewModel) {
							BaseModel mapValue = ModelMapper.mapViewModelToRmi(
									(BaseViewModel) value,
									((BaseViewModel) value).getDbModell());
							viewProp.getWriteMethod()
									.invoke(rmiModel, mapValue);
						} else {
							viewProp.getWriteMethod().invoke(rmiModel, value);
						}
					}
				}
			}
			return rmiModel;
		} catch (InstantiationException | IllegalAccessException
				| IntrospectionException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Map any DataBase Model to ViewModel
	 * @param rmiModel
	 * @param viewModelClass
	 * @return
	 */
	public static <T extends BaseViewModel> T mapRmiToViewModel(
			BaseModel rmiModel, Class viewModelClass) {
		try {
			T viewModel = (T) viewModelClass.newInstance();
			PropertyDescriptor[] viewModelFields = Introspector.getBeanInfo(
					viewModelClass).getPropertyDescriptors();
			for (PropertyDescriptor property : Introspector.getBeanInfo(
					rmiModel.getClass()).getPropertyDescriptors()) {
				for (PropertyDescriptor viewProp : viewModelFields) {
					if (property.getName().equals(viewProp.getName())
							&& !property.getName().equals("class")
							&& !property.getName().equals("dao")) {
						Object value = property.getReadMethod()
								.invoke(rmiModel);
						if (value instanceof BaseModel) {
							BaseViewModel modelVal = ModelMapper
									.mapRmiToViewModel(((BaseModel) value),
											((BaseModel) value).getViewModell());
							viewProp.getWriteMethod().invoke(viewModel,
									modelVal);
						} else {
							viewProp.getWriteMethod().invoke(viewModel, value);
						}

					}
				}
			}
			return viewModel;
		} catch (InstantiationException | IllegalAccessException
				| IntrospectionException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;

	}
}
