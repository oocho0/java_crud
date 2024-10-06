package my.oochoo.jdbc;

public enum JoinType {
    INNER_JOIN("INNER JOIN"),
    LEFT_JOIN("LEFT JOIN"),
    RIGHT_JOIN("RIGHT JOIN"),
    FULL_JOIN("FULL JOIN");

    private final String joinType;

    JoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getJoinType() {
        return joinType;
    }
}
