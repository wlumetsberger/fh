package at.fhhagenberg.swe4.campinaAsAService.rmi.daos;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import at.fhhagenberg.swe4.campinaAsAService.helper.DBUtil;
import at.fhhagenberg.swe4.campinaAsAService.helper.Util;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseConnections;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseFieldProperty;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.BaseModel;

public class BaseDao<T extends BaseModel> {

	// property to decide if any relationShip got fetched by dao too
	private boolean eager = true;

	// logger
	protected static final Logger log = Logger.getLogger(BaseDao.class.getName());
	
	protected BaseDao() {
	}

	public void setEager(boolean eager) {
		this.eager = eager;
	}
	public List<T> findAll() {
		return new ArrayList<T>();
	}

	/**
	 * Execute Update
	 * @param element
	 * @return
	 */
	public boolean update(T element) {
		try {
			PreparedStatement stmt = DBUtil.updateElement(element);
			log.info("Execute: " + stmt);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			log.log(Level.SEVERE,"Cannot Update Statment: ", e);
		}
		return false;
	}

	/**
	 * Insert elment to Database
	 * @param element
	 * @return
	 */
	public boolean insert(T element) {
		try {
			PreparedStatement stmt = DBUtil.insertElement(element);
			log.info("Execute: " + stmt);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			log.log(Level.SEVERE,"Cannot Update Statment: ", e);
		}
		return false;
	}
	/**
	 * Delete Element
	 * @param element
	 * @return
	 */
	public boolean delete(T element) {
		try {
			PreparedStatement stmt = DBUtil.deleteElement(element);
			log.info("Execute: " + stmt);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			log.log(Level.SEVERE,"Cannot Update Statment: ", e);
		}
		return false;
	}
	/**
	 * Find any Object By Id
	 * @param id
	 * @param clazz
	 * @return
	 */
	public T findById(Object id, Class clazz) {
		StringBuilder builder = new StringBuilder();
		builder.append(DBUtil.generateSelect(clazz));
		builder.append(DBUtil.generateIdWhere(clazz));
		T value = null;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(
					builder.toString());
			DBUtil.fillIdWhereWithValues(stmt, clazz, id);
			log.info("Execute: " + stmt);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				value = mapValuesBackFromResultSet(rs, clazz);
			}
			rs.close();
			stmt.close();
			return value;

		} catch (SQLException e) {
			log.log(Level.SEVERE,"Statment: ", e);
		}
		return null;
	}

	/**
	 * Execute any Select Statement and map values to List<T>
	 * @param stmt
	 * @param clazz
	 * @return
	 */
	public List<T> executeSelectStatment(PreparedStatement stmt, Class clazz) {
		List<T> retVal = new ArrayList();
		try {
			T element = (T) clazz.newInstance();
			log.info("Execute: " + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				element = mapValuesBackFromResultSet(rs, clazz);
				retVal.add(element);
			}
			rs.close();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			log.log(Level.SEVERE,"Error during Select: ", e);
		}

		return retVal;
	}
	/**
	 * Map Values from result Set back to Object T
	 * @param rs
	 * @param clazz
	 * @return
	 */
	public T mapValuesBackFromResultSet(ResultSet rs, Class clazz) {
		try {
			T element = (T) clazz.newInstance();
			for (PropertyDescriptor property : Introspector.getBeanInfo(clazz)
					.getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), clazz,
								DataBaseFieldProperty.class);
				if (anno != null && anno.column() != null) {
					if (anno.foreign() && anno.connectionType() != null
							&& this.eager) {
						// here fetch correct other class by getting dao and
						// fetch element
						if (anno.connectionType() == DataBaseConnections.OneToOne
								|| anno.connectionType() == DataBaseConnections.ManyToOne) {
							BaseModel foreign = (BaseModel) anno.foreignClass()
									.newInstance();
							BaseDao dao = foreign.getDao();
							dao.setEager(false);
							BaseModel model = dao.findById(
									rs.getObject(anno.column()),
									anno.foreignClass());
							dao.setEager(true);
							property.getWriteMethod().invoke(element, model);
						}
					} else if (!anno.foreign()) {
						if (property.getPropertyType() == LocalDateTime.class) {
							Timestamp t = (Timestamp) rs.getObject(anno
									.column());
							property.getWriteMethod().invoke(element,
									t.toLocalDateTime());

						} else {
							property.getWriteMethod().invoke(element,
									rs.getObject(anno.column()));
						}

					}

				} 
			}
			return element;
		} catch (IntrospectionException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| SQLException | InstantiationException e) {
			log.log(Level.SEVERE,"Cannot map value: ", e);
		}
		return null;

	}

}
