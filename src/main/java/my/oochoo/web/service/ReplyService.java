package my.oochoo.web.service;

import my.oochoo.web.model.Reply;

import java.util.List;
import java.util.UUID;

public interface ReplyService {
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
     * 댓글 삭제
     * @param replyId int : 삭제할 댓글 일련번호
     * @param password String : 등록시 입력한 비밀번호
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    boolean removeReply(int replyId, String password);

    /**
     * 댓글 비밀번호 확인
     * @param replyId int : 댓글 일련번호
     * @param password String : 등록시 입력한 비밀번호
     * @return boolean : 비밀번호 맞으면 true / 아니면 false
     */
    boolean checkPasswordforReply(int replyId, String password);

    /**
     * 해당 게시글의 모든 댓글 조회
     * @param postId int : 해당 게시글의 일련번호
     * @return List<Reply> : 게시글의 모든 댓글 목록
     */
    List<Reply> getAllReplybyPostId(int postId);
}
