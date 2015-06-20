package at.fhhagenberg.swe4.campinaAsAService.rmi.daos;

import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.rmi.models.Catagorie;

public class CatagorieDao extends BaseDao<Catagorie> {
	
	@Override
	public List<Catagorie> findAll() {
		System.out.println("Catagorie findAll");
		List<Catagorie> retVal = new ArrayList<Catagorie>();
		retVal.add(new Catagorie(1,"testCat1", "desc1"));
		retVal.add(new Catagorie(2,"testCat2", "desc2"));
		return retVal;
	}

}
