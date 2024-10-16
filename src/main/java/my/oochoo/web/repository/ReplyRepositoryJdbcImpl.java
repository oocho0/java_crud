package my.oochoo.web.repository;

import my.oochoo.jdbc.JdbcTemplate;
import my.oochoo.jdbc.SqlBuilder;
import my.oochoo.web.model.Page;
import my.oochoo.web.model.Reply;
import my.oochoo.web.service.ReplyServiceJdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class ReplyRepositoryJdbcImpl implements ReplyRepository{
    private final Logger LOGGER = Logger.getLogger(ReplyRepositoryJdbcImpl.class.getName());
    private final JdbcTemplate jdbcTemplate;

    public ReplyRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 댓글 등록
     *
     * @param reply Reply : 등록할 댓글 정보
     * @return boolean : 등록 성공하면 true / 아니면 false
     */
    @Override
    public boolean registerReply(Reply reply) {
        Map<String, Object> replyMap = reply.toMap();
        String sql = new SqlBuilder().setInsertValues("reply", replyMap)
                .setDuplicate(replyMap)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    /**
     * 댓글 수정
     *
     * @param reply Reply : 수정할 댓글 정보
     * @return boolean : 수정 성공하면 true / 아니면 false
     */
    @Override
    public boolean modifyReply(Reply reply) {
        int replyId = reply.getReplyId();
        String sql = new SqlBuilder().setUpdate("reply")
                .setSet(reply.toMap())
                .setWhere("reply_id", "=", replyId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    @Override
    public boolean modifyReply(String column, Object value, int replyId) {
        String sql = new SqlBuilder().setUpdate("reply")
                .setSet(column, value)
                .setWhere("reply_id", "=", replyId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    /**
     * 댓글 삭제
     *
     * @param replyId int : 삭제할 댓글 일련번호
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    @Override
    public boolean deleteReply(int replyId) {
        String sql = new SqlBuilder().setUpdate("reply")
                .setSet("use_yn", "N")
                .setWhere("reply_id", "=", replyId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    /**
     * 해당 게시글의 모든 댓글 조회
     *
     * @param postId int : 해당 게시글의 일련번호
     * @return List<Reply> : 게시글의 모든 댓글 목록
     */
    @Override
    public Optional<List<Reply>> getAllReplyListbyPostId(int postId, Page page) {
        String sql = new SqlBuilder().setSelect("*")
                .setFrom("reply")
                .setWhere("use_yn", "=", "Y")
                .setWhere("refer_id", "=", postId)
                .setOrderByDesc("regDate")
                .setLimit((page.getCurrentPageNumber() - 1) * page.getPageSize(), page.getPageSize())
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.selectQuery(sql, this::getReplyList);
    }

    /**
     * 특정 댓글 하나 조회
     *
     * @param replyId int : 조회할 하나의 게시글
     * @return Reply : 조회한 댓글 정보
     */
    @Override
    public Optional<Reply> getReplyById(int replyId) {
        String sql = new SqlBuilder().setSelect("*")
                .setFrom("reply")
                .setWhere("reply_id", "=", replyId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.selectQuery(sql, this::getReply);
    }

    /**
     * 특정 댓글의 작성자 조회
     *
     * @param replyId int : 특정 댓글의 일련번호
     * @return String : 조회한 작성자의 일련번호
     */
    @Override
    public int getAuthorByReplyId(int replyId) {
        String sql = new SqlBuilder().setSelect("user_key")
                .setFrom("reply")
                .setWhere("reply_id", "=", replyId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.selectQuery(sql, resultSet -> resultSet.getInt("user_key"));
    }

    private Optional<List<Reply>> getReplyList(ResultSet resultSet) {
        List<Reply> replyList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                this.getReply(resultSet).ifPresentOrElse(replyList::add, () -> {throw new RuntimeException("Reply not found");});
            }
            return Optional.of(replyList);
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<Reply> getReply(ResultSet resultSet) {
        Reply reply = new Reply();
        try {
            reply.setReplyId(resultSet.getInt("replyId"));
            reply.setReferId(resultSet.getInt("referId"));
            reply.setUserKey(resultSet.getInt("userKey"));
            reply.setContent(resultSet.getString("content"));
            reply.setRegDate(resultSet.getDate("regDate"));
            reply.setModDate(resultSet.getDate("modDate"));
            reply.setUseYN(resultSet.getString("useYN"));
            reply.setReplyLevel(resultSet.getInt("replyLevel"));
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return Optional.empty();
        }
        return Optional.of(reply);
    }
}
