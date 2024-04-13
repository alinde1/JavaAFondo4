package chap6;

import chap3.DataAccess;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyHibernate {

    public static <T> T find(Class<T> clazz, Integer id) {

        ResultSet rs = null;
        PreparedStatement pstm = null;

        try {
            // (1) Generamos dinámicamente el código SQL
            String sql = _generarCodigoSQL(clazz);

            // (2) Preparamos la sentencia (PreparedStatement)
            pstm = _prepararSentencia(sql, id);
            rs = pstm.executeQuery();

            // (3) Generamos una instancia de T o null
            return _generarObjeto(clazz, rs);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                pstm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static <T> T _generarObjeto(Class<T> clazz, ResultSet rs) {

        try {

            T t = clazz.newInstance();

            if (rs.next()) {
                for(Field field : clazz.getDeclaredFields()) {
                    Column annColumn = field.getAnnotation(Column.class);
                    if (annColumn != null) {
                        Object value = rs.getObject(annColumn.name());
                        if (field.getAnnotation(ManyToOne.class) != null) {
                            value = find(field.getType(), (Integer) value);
                        }
                        boolean access = field.isAccessible();
                        field.setAccessible(true);
                        field.set(t, value);
                        field.setAccessible(access);
                    }
                }
            }

            return t;

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static PreparedStatement _prepararSentencia(String sql, Integer id) {

        Connection con = DataAccess.getConnection();
        PreparedStatement pstm;
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pstm;

    }

    private static <T> String _generarCodigoSQL(Class<T> clazz) {

        String select = "SELECT *";
        String from = "FROM " + getTableName(clazz);
        String where = "WHERE " + getId(clazz) + " = ?";

        return select + " " + from + " " + where;
    }

    public static <T> List<T> findAll(Class<T> clazz) {

        ArrayList<Integer> idsList = _getAllIds(clazz);
        System.out.println(idsList);

        List<T> objectList = new ArrayList<>();
        for (int id : idsList) {
            objectList.add(find(clazz, id));
        }

        return objectList;
    }

    private static <T> ArrayList<Integer> _getAllIds(Class<T> clazz) {

        String tableName = getTableName(clazz);
        String id = getId(clazz);

        String sql = "SELECT " + id + " FROM " + tableName;

        Connection con = DataAccess.getConnection();
        PreparedStatement pstm;
        ResultSet rs;
        try {
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            ArrayList<Integer> idList = new ArrayList<>();
            while (rs.next()) {
                idList.add(rs.getInt(id));
            }

            return idList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static <T> String getId(Class<T> clazz) {

        Field[] fields = clazz.getDeclaredFields();

        Column annColumn;
        Id annId;

        for (Field field : fields) {
            annColumn = field.getAnnotation(Column.class);
            annId = field.getAnnotation(Id.class);
            if (annColumn != null && annId != null) {
                return annColumn.name();
            }
        }

        return null;
    }

    private static <T> Object getIdValue(T obj) {

        Field[] fields = obj.getClass().getDeclaredFields();

        Column annColumn;
        Id annId;

        boolean access;
        for (Field field : fields) {
            annColumn = field.getAnnotation(Column.class);
            annId = field.getAnnotation(Id.class);
            if (annColumn != null && annId != null) {
                try {
                    access = field.isAccessible();
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    field.setAccessible(access);
                    return value;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }

    private static <T> void setIdValue(T obj, int id) {

        Field[] fields = obj.getClass().getDeclaredFields();

        Column annColumn;
        Id annId;

        boolean access;
        for (Field field : fields) {
            annColumn = field.getAnnotation(Column.class);
            annId = field.getAnnotation(Id.class);
            if (annColumn != null && annId != null) {
                try {
                    access = field.isAccessible();
                    field.setAccessible(true);
                    field.set(obj, id);
                    field.setAccessible(access);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static <T> String getTableName(Class<T> clazz) {
        return clazz.getAnnotation(Table.class).name();
    }

    private static <T> String getPk(Class<T> clazz) {

        String pk = "";

        for (Field f : clazz.getDeclaredFields()) {
            if ( f.getAnnotation(Id.class) != null ) {
                pk = f.getAnnotation(Column.class).name();
            }
        }

        return pk;
    }

    private static <T> String getColumnName(Class<T> clazz, String fieldName) {

        try {
            Field field = clazz.getDeclaredField(fieldName);
            return field.getAnnotation(Column.class).name();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> List<T> findBy(Class<T> clazz, String att, Object value) {

        String tableName = getTableName(clazz);

        String pk = getPk(clazz);

        String dbField = getColumnName(clazz, att);

        if (value instanceof String) {
            value = "'" + value + "'";
        }
        String sql = "SELECT " + pk + " FROM " + tableName + " WHERE " + dbField + " = " + value;

        Connection con = DataAccess.getConnection();
        PreparedStatement pstm;
        ResultSet rs;
        try {
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            ArrayList<T> objectList = new ArrayList<>();
            while (rs.next()) {
                objectList.add(find(clazz, rs.getInt(pk)));
            }

            return objectList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isManyToOne(Field field) {
        return field.getAnnotation(ManyToOne.class) != null;
    }

    private static <T> Map<String, ArrayList<String>> getColumnsAndValues(T obj) {

        Column column;
        boolean access;
        String columnName;
        Object value;

        ArrayList<String> columnsList = new ArrayList<>();
        ArrayList<String> valuesList = new ArrayList<>();

        for (Field f : obj.getClass().getDeclaredFields()) {
            column = f.getAnnotation(Column.class);
            if ( column != null ) {
                try {
                    columnName = column.name();
                    access = f.isAccessible();
                    f.setAccessible(true);
                    value = isManyToOne(f) ? getIdValue(f.get(obj)) : f.get(obj);
                    if (value instanceof String) value = "'" + value + "'";
                    if (value != null) {
                        columnsList.add(columnName);
                        valuesList.add(String.valueOf(value));
                    }
                    f.setAccessible(access);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Map<String,ArrayList<String>> map = new HashMap();
        map.put("columns", columnsList);
        map.put("values", valuesList);
        return map;
    }

    public static <T> void insert(T obj) {

        Map<String, ArrayList<String>> map = getColumnsAndValues(obj);
        ArrayList<String> columnsList = map.get("columns");
        ArrayList<String> valuesList = map.get("values");

        String sql = "";
        sql += "INSERT INTO " + getTableName(obj.getClass());
        sql += " (" + String.join(",", columnsList) + ") ";
        sql += "VALUES (" + String.join(",", valuesList) + ")";
        System.out.println(sql);

        ResultSet rs = _update(sql);
        try {
            if (rs.next()) {
                int id = rs.getInt(1);
                setIdValue(obj, id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static ResultSet _update(String sql) {

        Connection con = DataAccess.getConnection();
        PreparedStatement pstm;
        ResultSet rs;
        try {
            pstm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            int rtdo = pstm.executeUpdate();

            if (rtdo == 1) {
                rs = pstm.getGeneratedKeys();
                return rs;
            } else {
                throw new RuntimeException("Error en update");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> void update(T obj) {


        Map<String, ArrayList<String>> map = getColumnsAndValues(obj);
        ArrayList<String> columnsList = map.get("columns");
        ArrayList<String> valuesList = map.get("values");

        List<String> setString = IntStream
                .range(0, Math.min(columnsList.size(), valuesList.size()))
                .mapToObj(i -> columnsList.get(i) + " = " + valuesList.get(i)).collect(Collectors.toList());

        String sql = "";
        sql += "UPDATE " + getTableName(obj.getClass()) + " ";
        sql += "SET " + String.join(",", setString) + " ";
        sql += "WHERE " + getId(obj.getClass()) + " = " + getIdValue(obj);

        _update(sql);

    }

    public static <T> void delete(Class<T> clazz, int id) {

        String sql = "";
        sql += "DELETE FROM " + getTableName(clazz) + " ";
        sql += "WHERE " + getId(clazz) + " = " + Integer.toString(id);

        _update(sql);

    }
}
