package my.oochoo.web.service;

import my.oochoo.jdbc.JdbcTemplate;
import my.oochoo.jdbc.SqlBuilder;
import my.oochoo.web.model.Reply;
import my.oochoo.web.repository.ReplyRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ReplyServiceJdbcImpl implements ReplyService {
    private final Logger LOGGER = Logger.getLogger(ReplyServiceJdbcImpl.class.getName());
    private final ReplyRepository replyRepository;

    public ReplyServiceJdbcImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    /**
     * 특정 댓글의 작성자인지 확인
     *
     * @param replyId int : 특정 댓글의 일련번호
     * @param userKey int : 확안할 작성자의 일련번호
     * @return boolean : 특정 댓글의 작성자가 맞으면 true / 아니면 false
     */
    @Override
    public boolean checkAuthorByReplyId(int replyId, int userKey) {
        return false;
    }
}
