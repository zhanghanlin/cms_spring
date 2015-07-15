package com.demo.java.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.annotation.Column;
import com.demo.java.annotation.Ignore;
import com.demo.java.annotation.Table;
import com.demo.java.utils.string.StringUtils;

public class ReflectUtils {

    static final Logger LOG = LoggerFactory.getLogger(ReflectUtils.class);

    static String field2Column(Field field) {
        if (field.isAnnotationPresent(Column.class)) {
            return field.getAnnotation(Column.class).name();
        }
        return StringUtils.camelCase2DB(field.getName());
    }

    static String class2Table(Class<? extends Object> clazz) {
        if (clazz.isAnnotationPresent(Table.class)) {
            return clazz.getAnnotation(Table.class).name();
        }
        return StringUtils.camelCase2DB(clazz.getSimpleName());
    }

    static List<Field> getFields(Class<? extends Object> clazz) {
        List<Field> list = new ArrayList<Field>();
        Field fields[] = clazz.getDeclaredFields();
        list.addAll(Arrays.asList(fields));
        if (clazz.getGenericSuperclass() != null) {
            list.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        }
        return list;
    }

    public static List<Object> getBeanValue(Object t) {
        List<Object> list = new ArrayList<Object>();
        List<Field> fields = getFields(t.getClass());
        for (Field field : fields) {
            if (field.getName().toUpperCase().equals("ID")) {
                continue;
            }
            String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Method method = t.getClass().getMethod(methodName);
                if (method.isAnnotationPresent(Ignore.class)) {
                    continue;
                }
                Object o = method.invoke(t);
                if (null != o) {
                    list.add(o);
                }
            } catch (Exception e) {
                LOG.error("getSQLValues error:{}", e.getMessage(), e);
            }
        }
        return list;
    }

    public static String getInsertSQL(Object t) {
        String _table = class2Table(t.getClass());
        String strSQL = new String("INSERT INTO " + _table + " (#COLS) VALUES (#VALS)");
        StringBuffer cols = new StringBuffer("");
        StringBuffer values = new StringBuffer("");
        List<Field> fields = getFields(t.getClass());
        for (Field field : fields) {
            String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Method method = t.getClass().getMethod(methodName);
                if (method.isAnnotationPresent(Ignore.class)) {
                    continue;
                }
                Object o = method.invoke(t);
                if (null != o) {
                    cols.append(field2Column(field) + ",");
                    values.append("?,");
                }
            } catch (Exception e) {
                LOG.error("getSQLValues error:{}", e.getMessage(), e);
            }
        }
        if ((cols.length() > 1) && (values.length() > 1)) {
            cols.delete(cols.length() - 1, cols.length());
            values.delete(values.length() - 1, values.length());
            strSQL = strSQL.replace("#COLS", cols).replace("#VALS", values);
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug(strSQL);
        }
        return strSQL;
    }

    /**
     * 通过bean对象获取插入时的SQL.<br/>
     * 
     * @author zhanghanlin
     * @param t
     * @param tableName
     * @return
     * @since JDK 1.7
     */
    public static String getUpdateSQL(Object t) {
        String _table = class2Table(t.getClass());
        String strSQL = new String("UPDATE " + _table + " SET #UPDATE VERSION = VERSION + 1 WHERE ID = #ID");
        StringBuffer cols = new StringBuffer("");
        List<Field> fields = getFields(t.getClass());
        Object id = null;
        for (Field field : fields) {
            String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Method method = t.getClass().getMethod(methodName);
                Object o = method.invoke(t);
                if (null != o) {
                    if (field.getName().toUpperCase().equals("ID")) {
                        id = o;
                        continue;
                    }
                    cols.append(field2Column(field) + " = ?,");
                }
            } catch (Exception e) {
                LOG.error("getSQLValues error:{}", e.getMessage(), e);
            }
        }
        if (cols.length() > 1) {
            strSQL = strSQL.replace("#UPDATE", cols).replace("#ID", id.toString());
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug(strSQL);
        }
        return strSQL;
    }
}