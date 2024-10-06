package my.oochoo.jdbc;

import java.util.List;
import java.util.Map;

public class SqlBuilder {
    private String select;
    private String from;
    private String join;
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

    public SqlBuilder setFrom(String table) {
        this.from = String.format(" FROM %s", table);
        return this;
    }

    public SqlBuilder setFrom(String table, String alias) {
        this.tableAlias = alias;
        this.from = String.format(" FROM %s %s", table, alias);
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

    public SqlBuilder setWhere(String tableAlias, String column, String operator, String value) {
        if (this.where == null) {
            this.where = String.format(" WHERE %s.%s %s %s", tableAlias,column, operator, value);
        } else {
            this.where += String.format(" AND %s.%s %s %s", tableAlias, column, operator, value);
        }
        return this;
    }
    public SqlBuilder setWhere(String column, String operator, String value) {
        if (this.where == null) {
            this.where = String.format(" WHERE %s %s %s",column, operator, value);
        } else {
            this.where += String.format(" AND %s %s %s", column, operator, value);
        }
        return this;
    }
    public SqlBuilder setWhere(String tableAlias, String column, String operator, int value) {
        if (this.where == null) {
            this.where = String.format(" WHERE %s.%s %s %d", tableAlias, column, operator, value);
        } else {
            this.where += String.format(" AND %s.%s %s %d", tableAlias, column, operator, value);
        }
        return this;
    }
    public SqlBuilder setWhere(String column, String operator, int value) {
        if (this.where == null) {
            this.where = String.format(" WHERE %s %s %d", column, operator, value);
        } else {
            this.where += String.format(" AND %s %s %d", column, operator, value);
        }
        return this;
    }
    public SqlBuilder setWhere(String tableAlias, String column, String operator, List<String> values) {
        if (this.where == null) {
            this.where = String.format(" WHERE %s.%s %s (%s)", tableAlias, column, operator, String.join(", ", values));
        } else {
            this.where += String.format(" AND %s.%s %s (%s)", tableAlias, column, operator, String.join(", ", values));
        }
        return this;
    }
    public SqlBuilder setWhere(String column, String operator, List<String> values) {
        if (this.where == null) {
            this.where = String.format(" WHERE %s %s (%s)", column, operator, String.join(", ", values));
        } else {
            this.where += String.format(" AND %s %s (%s)", column, operator, String.join(", ", values));
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
        return select + from + join + where + orderBy + limit;
    }
}
