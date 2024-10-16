package my.oochoo.web.service;

import my.oochoo.web.model.Reply;

import java.util.List;

public interface ReplyService {
    /**
     * 특정 댓글의 작성자인지 확인
     * @param replyId int : 특정 댓글의 일련번호
     * @param userKey int : 확안할 작성자의 일련번호
     * @return boolean : 특정 댓글의 작성자가 맞으면 true / 아니면 false
     */
    boolean checkAuthorByReplyId(int replyId, int userKey);
}
