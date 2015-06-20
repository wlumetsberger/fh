package at.fhhagenberg.swe4.campinaAsAService.helper;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Commons;

import at.fhhagenberg.swe4.campinaAsAService.models.BaseViewModel;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.BaseModel;

public class ModelMapper {
	
	public static <T extends BaseModel> T mapViewModelToRmi(BaseViewModel viewModel, Class rmiModelClass){
		try {
			T rmiModel = (T) rmiModelClass.newInstance();
			PropertyDescriptor[] viewModelFields = Introspector.getBeanInfo(rmiModelClass).getPropertyDescriptors();
			for (PropertyDescriptor property : Introspector.getBeanInfo(viewModel.getClass()).getPropertyDescriptors()){
				for(PropertyDescriptor viewProp: viewModelFields){
					if(property.getName().equals(viewProp.getName()) && !property.getName().equals("class") && !property.getName().equals("dao")){
						Object value = property.getReadMethod().invoke(viewModel);
						viewProp.getWriteMethod().invoke(rmiModel, value);
					}
				}
			}
			return rmiModel;
		} catch (InstantiationException
				| IllegalAccessException | IntrospectionException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends BaseViewModel> T mapRmiToViewModel(BaseModel rmiModel, Class viewModelClass){
		try {
			T viewModel = (T) viewModelClass.newInstance();
			PropertyDescriptor[] viewModelFields = Introspector.getBeanInfo(viewModelClass).getPropertyDescriptors();
			for (PropertyDescriptor property : Introspector.getBeanInfo(rmiModel.getClass()).getPropertyDescriptors()){
				for(PropertyDescriptor viewProp: viewModelFields){
					if(property.getName().equals(viewProp.getName()) && !property.getName().equals("class") && !property.getName().equals("dao")){
						Object value = property.getReadMethod().invoke(rmiModel);
						System.out.println("name: "+ property.getName());
						System.out.println("value: "+ property.getName());
						viewProp.getWriteMethod().invoke(viewModel, value);
					}
				}
			}
			return viewModel;
		} catch (InstantiationException
				| IllegalAccessException | IntrospectionException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
