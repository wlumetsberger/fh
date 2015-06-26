package at.fhhagenberg.swe4.campinaAsAService.helper;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseConnections;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseFieldProperty;
import at.fhhagenberg.swe4.campinaAsAService.rmi.annotations.DataBaseTableProperty;
import at.fhhagenberg.swe4.campinaAsAService.rmi.models.BaseModel;

public class DBUtil {

	private static Connection con;
	private static final String CONNECTION_STRING = "jdbc:mysql://localhost/campinaasaservice";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = null;

	/**
	 * Returns a Connection
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		if (con == null) {
			con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME,
					PASSWORD);
		}
		return con;
	}

	/**
	 * return a list of DB Columns for a Class with DB Annotations
	 * @param clazz
	 * @return
	 */
	public static List<String> getDbFields(Class clazz) {
		List<String> result = new ArrayList();
		try {
			for (PropertyDescriptor property : Introspector.getBeanInfo(clazz)
					.getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), clazz,
								DataBaseFieldProperty.class);
				if (anno != null
						&& anno.column() != null
						&& anno.connectionType() != DataBaseConnections.OneToMany) {
					result.add(anno.column());
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Generate a SELECT Statment without Where for given Class With DB annotations
	 * @param clazz
	 * @return
	 */
	public static String generateSelect(Class clazz) {
		List<String> result = DBUtil.getDbFields(clazz);
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		builder.append("SELECT ");
		for (String field : result) {
			if (!first) {
				builder.append(",");
			}
			first = false;
			builder.append(field);
		}
		builder.append(" FROM ");
		DataBaseTableProperty table = (DataBaseTableProperty) clazz
				.getAnnotation(DataBaseTableProperty.class);
		builder.append("`");
		builder.append(table.tableName());
		builder.append("`");
		return builder.toString();

	}
	/**
	 * Set Parameter of Id fields to Where clause
	 * @param stmt
	 * @param clazz
	 * @param id
	 */
	public static void fillIdWhereWithValues(PreparedStatement stmt,
			Class clazz, Object id) {
		try {
			for (PropertyDescriptor property : Introspector.getBeanInfo(clazz)
					.getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), clazz,
								DataBaseFieldProperty.class);
				if (anno != null && anno.column() != null && anno.key()) {
					try {
						stmt.setObject(1, id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generate a Where Clause of ID fields from given DB
	 * @param clazz
	 * @return
	 */
	public static String generateIdWhere(Class clazz) {
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		builder.append(" WHERE ");
		try {
			for (PropertyDescriptor property : Introspector.getBeanInfo(clazz)
					.getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), clazz,
								DataBaseFieldProperty.class);
				if (anno != null && anno.column() != null && anno.key()) {
					if (!first) {
						builder.append(" , ");
					}
					first = false;
					builder.append(anno.column());
					builder.append(" = ? ");
				}

			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	/**
	 * Generate Update Statement for Any BaseModel Entity
	 * @param entity
	 * @return
	 */
	public static PreparedStatement updateElement(BaseModel entity) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append("`");
		builder.append(readTableName(entity));
		builder.append("`");
		builder.append(" SET ");
		try {
			boolean first = true;
			for (PropertyDescriptor property : Introspector.getBeanInfo(
					entity.getClass()).getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), entity.getClass(),
								DataBaseFieldProperty.class);
				if (anno != null
						&& anno.column() != null
						&& !anno.key()
						&& anno.connectionType() != DataBaseConnections.OneToMany) {
					if (!first) {
						builder.append(",");
					}
					builder.append("`");
					builder.append(anno.column());
					builder.append("`");
					builder.append("= ? ");
					first = false;
				}
			}
			builder.append(" WHERE ");
			first = true;
			for (PropertyDescriptor property : Introspector.getBeanInfo(
					entity.getClass()).getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), entity.getClass(),
								DataBaseFieldProperty.class);
				if (anno != null
						&& anno.column() != null
						&& anno.key()
						&& anno.connectionType() != DataBaseConnections.OneToMany) {
					if (!first) {
						builder.append(",");
					}
					builder.append(anno.column());
					builder.append("= ? ");
					first = false;
				}
			}

			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(
					builder.toString());
			int x = 1;
			// set update values
			for (PropertyDescriptor property : Introspector.getBeanInfo(
					entity.getClass()).getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), entity.getClass(),
								DataBaseFieldProperty.class);
				if (anno != null && anno.column() != null && !anno.key()) {
					if (!anno.foreign()) {
						if (property.getPropertyType() == LocalDateTime.class) {
							LocalDateTime t = (LocalDateTime) property
									.getReadMethod().invoke(entity);
							stmt.setObject(x++, java.sql.Timestamp.valueOf(t));

						} else {
							stmt.setObject(x++, property.getReadMethod()
									.invoke(entity));
						}

					} else if (anno.connectionType() == DataBaseConnections.OneToOne
							|| anno.connectionType() == DataBaseConnections.ManyToOne) {
						BaseModel relation = (BaseModel) property
								.getReadMethod().invoke(entity);
						stmt.setObject(x++, relation.getId());
					}
				}
			}
			// set where clause params
			for (PropertyDescriptor property : Introspector.getBeanInfo(
					entity.getClass()).getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), entity.getClass(),
								DataBaseFieldProperty.class);
				if (anno != null && anno.column() != null && anno.key()) {
					stmt.setObject(x++, property.getReadMethod().invoke(entity));
				}
			}
			return stmt;
		} catch (IntrospectionException | SQLException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Generate Insert Statement for any BaseModel entity
	 * @param entity
	 * @return
	 */
	public static PreparedStatement insertElement(BaseModel entity) {
		StringBuilder builder = new StringBuilder();
		try {
			builder.append("INSERT INTO ");
			builder.append("`");
			builder.append(readTableName(entity));
			builder.append("`");
			builder.append(" ( ");
			boolean first = true;
			int count = 0;
			for (PropertyDescriptor property : Introspector.getBeanInfo(
					entity.getClass()).getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), entity.getClass(),
								DataBaseFieldProperty.class);
				if (anno != null
						&& anno.column() != null
						&& !anno.generated()
						&& anno.connectionType() != DataBaseConnections.OneToMany) {
					if (!first) {
						builder.append(",");
					}
					builder.append("`");
					builder.append(anno.column());
					builder.append("`");
					first = false;
					count++;
				}
			}
			builder.append(" ) VALUES ( ");
			first = true;
			for (int i = 0; i < count; i++) {
				if (!first) {
					builder.append(",");
				}
				builder.append(" ? ");
				first = false;
			}
			builder.append(" ) ");
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(
					builder.toString());
			count = 1;
			for (PropertyDescriptor property : Introspector.getBeanInfo(
					entity.getClass()).getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), entity.getClass(),
								DataBaseFieldProperty.class);
				if (anno != null && anno.column() != null && !anno.generated()) {
					if (!anno.foreign()) {
						if (property.getPropertyType() == LocalDateTime.class) {
							LocalDateTime t = (LocalDateTime) property
									.getReadMethod().invoke(entity);
							stmt.setObject(count++,
									java.sql.Timestamp.valueOf(t));
						} else {
							stmt.setObject(count++, property.getReadMethod()
									.invoke(entity));
						}

					} else if (anno.connectionType() == DataBaseConnections.OneToOne
							|| anno.connectionType() == DataBaseConnections.ManyToOne) {
						BaseModel relation = (BaseModel) property
								.getReadMethod().invoke(entity);
						stmt.setObject(count++, relation.getId());
					}

				}
			}
			return stmt;
		} catch (IntrospectionException | SQLException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;

	}
	/**
	 * Generate Delete Statment for BaseModel entity
	 * @param entity
	 * @return
	 */
	public static PreparedStatement deleteElement(BaseModel entity) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append("`");
		builder.append(DBUtil.readTableName(entity));
		builder.append("`");
		builder.append(DBUtil.generateIdWhere(entity.getClass()));
		try {
			int count = 1;
			PreparedStatement stmt = getConnection().prepareStatement(
					builder.toString());
			for (PropertyDescriptor property : Introspector.getBeanInfo(
					entity.getClass()).getPropertyDescriptors()) {
				DataBaseFieldProperty anno = (DataBaseFieldProperty) Util
						.getAnnotation(property.getName(), entity.getClass(),
								DataBaseFieldProperty.class);
				if (anno != null && anno.column() != null && anno.key()) {
					stmt.setObject(count++,
							property.getReadMethod().invoke(entity));
				}
			}
			return stmt;
		} catch (SQLException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| IntrospectionException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Return TableName from Property of BaseModel
	 * @param entity
	 * @return
	 */
	private static String readTableName(BaseModel entity) {
		DataBaseTableProperty table = (DataBaseTableProperty) entity.getClass()
				.getAnnotation(DataBaseTableProperty.class);
		return (table.tableName());
	}
}
