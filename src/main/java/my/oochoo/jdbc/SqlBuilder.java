package my.oochoo.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlBuilder {
    private String select;
    private String insert;
    private String update;
    private String from;
    private String values;
    private String set;
    private String join;
    private String duplicate;
    private String where;
    private String orderBy;
    private String limit;
    private String tableAlias;

    public SqlBuilder setSelect(String column) {
        this.select = String.format("SELECT %s", column);
        return this;
    }

    public SqlBuilder setSelect(List<String> columns) {
        this.select = String.format("SELECT %s", String.join(", ", columns));
        return this;
    }

    public SqlBuilder setSelect(Map<String, String> columns) {
        StringBuilder columnWithAlias = new StringBuilder();
        for (Map.Entry<String, String> entry : columns.entrySet()) {
            columnWithAlias.append(String.format("%s AS %s, ", entry.getKey(), entry.getValue()));
        }
        this.select = String.format("SELECT %s", columnWithAlias.substring(0, columnWithAlias.length() - 2));
        return this;
    }

    public SqlBuilder setInsert(String table) {
        this.insert = String.format("INSERT INTO %s", table);
        return this;
    }
    public SqlBuilder setInsert(String table, List<String> columns) {
        this.insert = String.format("INSERT INTO %s (%s)", table, String.join(", ", columns));
        return this;
    }

    public SqlBuilder setInsertValues(String table, Map<String, Object> values) {
        Map<String, String> valuesMap = this.stringFormat(values);
        this.insert = String.format("INSERT INTO %s (%s)", table, String.join(", ", valuesMap.keySet()));
        this.values = String.format(" VALUES (%s)", String.join(", ", valuesMap.values()));
        return this;
    }

    public SqlBuilder setUpdate(String table) {
        this.update = String.format("UPDATE %s", table);
        return this;
    }

    public SqlBuilder setFrom(String table) {
        this.from = String.format(" FROM %s", table);
        return this;
    }

    public SqlBuilder setFrom(String table, String alias) {
        this.tableAlias = alias;
        this.from = String.format(" FROM %s %s", table, alias);
        return this;
    }

    public SqlBuilder setValues(Object value) {
        if (value instanceof String) {
            value = "'" + value + "'";
        }
        if (this.values == null) {
            this.values = String.format(" VALUES (%s)", value);
        } else {
            this.values = this.values.substring(0, this.values.length() - 1);
            this.values += String.format(", %s)", value);
        }
        return this;
    }

    public SqlBuilder setValues(List<Object> values) {
        List<String> valuesList = this.stringFormat(values);
        this.values = String.format(" VALUES (%s)", String.join(", ", valuesList));
        return this;
    }

    public SqlBuilder setSet(String column, Object value) {
        if (value instanceof String) {
            value = "'" + value + "'";
        }
        if (this.set == null) {
            this.set = String.format(" SET %s = %s", column, value);
        } else {
            this.set += String.format(", %s = %s", column, value);
        }
        return this;
    }

    public SqlBuilder setSet(Map<String, Object> values) {
        Map<String, String> valuesMap = this.stringFormat(values);
        StringBuilder columnWithAlias = new StringBuilder();
        for (Map.Entry<String, String> entry : valuesMap.entrySet()) {
            columnWithAlias.append(String.format("%s = %s, ", entry.getKey(), entry.getValue()));
        }
        this.set = String.format(" SET %s", columnWithAlias.substring(0, columnWithAlias.length() - 2));
        return this;
    }

    public SqlBuilder setJoin(JoinType joinType, String referTable, String referAlias, String referColumn, String targetColumn) {
        if (this.join == null) {
            this.join = String.format(" %s %s %s ON %s.%s = %s.%s", joinType, referTable, referAlias, this.tableAlias, targetColumn, referAlias, referColumn);
        } else {
            this.join += String.format(" %s %s %s ON %s.%s = %s.%s", joinType, referTable, referAlias, this.tableAlias, targetColumn, referAlias, referColumn);
        }
        return this;
    }

    public SqlBuilder setDuplicate(String column, Object value) {
        if (value instanceof String) {
            value = "'" + value + "'";
        }
        if (this.duplicate == null) {
            this.duplicate = String.format(" ON DUPLICATE KEY UPDATE %s = %s", column, value);
        } else {
            this.duplicate += String.format(", %s = %s", column, value);
        }
        return this;
    }

    public SqlBuilder setDuplicate(Map<String, Object> values) {
        Map<String, String> valuesMap = this.stringFormat(values);
        StringBuilder columnWithAlias = new StringBuilder();
        for (Map.Entry<String, String> entry : valuesMap.entrySet()) {
            columnWithAlias.append(String.format("%s = %s, ", entry.getKey(), entry.getValue()));
        }
        this.duplicate = String.format(" ON DUPLICATE KEY UPDATE %s", columnWithAlias.substring(0, columnWithAlias.length() - 2));
        return this;
    }

    public SqlBuilder setWhere(String tableAlias, String column, String operator, Object value) {
        if (value instanceof String) {
            value = "'" + value + "'";
        }
        if (this.where == null) {
            this.where = String.format(" WHERE %s.%s %s %s", tableAlias,column, operator, value);
        } else {
            this.where += String.format(" AND %s.%s %s %s", tableAlias, column, operator, value);
        }
        return this;
    }
    public SqlBuilder setWhere(String column, String operator, Object value) {
        if (value instanceof String) {
            value = "'" + value + "'";
        }
        if (this.where == null) {
            this.where = String.format(" WHERE %s %s %s",column, operator, value);
        } else {
            this.where += String.format(" AND %s %s %s", column, operator, value);
        }
        return this;
    }
    public SqlBuilder setWhere(String tableAlias, String column, String operator, List<Object> values) {
        List<String> valuesList = this.stringFormat(values);
        if (this.where == null) {
            this.where = String.format(" WHERE %s.%s %s (%s)", tableAlias, column, operator, String.join(", ", valuesList));
        } else {
            this.where += String.format(" AND %s.%s %s (%s)", tableAlias, column, operator, String.join(", ", valuesList));
        }
        return this;
    }
    public SqlBuilder setWhere(String column, String operator, List<Object> values) {
        List<String> valuesList = this.stringFormat(values);
        if (this.where == null) {
            this.where = String.format(" WHERE %s %s (%s)", column, operator, String.join(", ", valuesList));
        } else {
            this.where += String.format(" AND %s %s (%s)", column, operator, String.join(", ", valuesList));
        }
        return this;
    }

    public SqlBuilder setOrderByDesc(String tableAlias, String column) {
        if (this.orderBy == null) {
            this.orderBy = String.format(" ORDER BY %s.%s DESC", tableAlias, column);
        } else {
            this.orderBy += String.format(", %s.%s DESC", tableAlias, column);
        }
        return this;
    }
    public SqlBuilder setOrderByDesc(String column) {
        if (this.orderBy == null) {
            this.orderBy = String.format(" ORDER BY %s DESC", column);
        } else {
            this.orderBy += String.format(", %s DESC", column);
        }
        return this;
    }

    public SqlBuilder setOrderByAsc(String tableAlias, String column) {
        if (this.orderBy == null) {
            this.orderBy = String.format(" ORDER BY %s.%s", tableAlias, column);
        } else {
            this.orderBy += String.format(", %s.%s", tableAlias, column);
        }
        return this;
    }
    public SqlBuilder setOrderByAsc(String column) {
        if (this.orderBy == null) {
            this.orderBy = String.format(" ORDER BY %s", column);
        } else {
            this.orderBy += String.format(", %s", column);
        }
        return this;
    }

    public SqlBuilder setLimitWithOffset(int limit, int offset) {
        this.limit = String.format(" LIMIT %d OFFSET %d", limit, offset);
        return this;
    }

    public SqlBuilder setLimit(int limit) {
        this.limit = String.format(" LIMIT %d", limit);
        return this;
    }

    public SqlBuilder setLimit(int offset, int limit) {
        this.limit = String.format(" LIMIT %d, %d", offset, limit);
        return this;
    }

    public String build() {
        if(this.select != null) {
            return select + from + join + where + orderBy + limit;
        }
        if(this.insert != null) {
            return insert + values + duplicate;
        }
        if(this.update != null) {
            return update + set + where;
        }
        return null;
    }

    private Map<String, String> stringFormat(Map<String, Object> values) {
        Map<String, String> valuesMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            if (entry.getValue() instanceof String) {
                String value = "'" + entry.getValue() + "'";
                valuesMap.put(entry.getKey(), value);
            } else {
                valuesMap.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return valuesMap;
    }

    private List<String> stringFormat(List<Object> values) {
        List<String> valuesList = new ArrayList<>();
        for (Object value : values) {
            if (value instanceof String) {
                String valueStr = "'" + value + "'";
                valuesList.add(valueStr);
            }
            valuesList.add(String.valueOf(value));
        }
        return valuesList;
    }
}
