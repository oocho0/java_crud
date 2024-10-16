package my.oochoo.web.repository;

import my.oochoo.web.model.Page;
import my.oochoo.web.model.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    /**
     * 댓글 등록
     * @param reply Reply : 등록할 댓글 정보
     * @return boolean : 등록 성공하면 true / 아니면 false
     */
    boolean registerReply(Reply reply);

    /**
     * 댓글 수정
     * @param reply Reply : 수정할 댓글 정보
     * @return boolean : 수정 성공하면 true / 아니면 false
     */
    boolean modifyReply(Reply reply);

    /**
     * 댓글 수정
     * @param column String : 수정할 댓글 정보 키
     * @param value Object : 수정할 댓글 정보 값
     * @param replyId int : 수정할 댓글 일련번호
     * @return boolean : 수정 성공하면 true / 아니면 false
     */
    boolean modifyReply(String column, Object value, int replyId);

    /**
     * 댓글 삭제
     * @param replyId int : 삭제할 댓글 일련번호
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    boolean deleteReply(int replyId);

    /**
     * 특정 댓글 하나 조회
     * @param replyId int : 조회할 하나의 게시글
     * @return Reply : 조회한 댓글 정보
     */
    Optional<Reply> getReplyById(int replyId);

    /**
     * 특정 댓글의 작성자 조회
     * @param replyId int : 특정 댓글의 일련번호
     * @return String : 조회한 작성자의 일련번호
     */
    int getAuthorByReplyId(int replyId);

    /**
     * 해당 게시글의 모든 댓글 조회
     * @param postId int : 해당 게시글의 일련번호
     * @return List<Reply> : 게시글의 모든 댓글 목록
     */
    Optional<List<Reply>> getAllReplyListbyPostId(int postId, Page page);
}
