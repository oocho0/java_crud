package my.oochoo.web.model;

import java.sql.Date;

/**
 * 댓글 도메인
 */
public class Reply {
    /** 댓글 일련번호 */
    private int replyId;

    /** 댓글이 달린 참조된 게시글/댓글의 일련번호 */
    private int referId;

    /** 댓글 작성자 */
    private String author;

    /** 댓글 작성자의 비밀번호 */
    private String password;

    /** 댓글 내용 */
    private String content;

    /** 댓글 최초 작성 일자 */
    private Date regDate;

    /** 댓글 마지막 수정 일자 */
    private Date modDate;

    /** 댓글 사용 여부 */
    private String useYN;

    /** 댓글 달린 레벨
     * 0 : 게시글의 댓글
     * 1 : 최상위 댓글의 대댓글
     * 2 : 대댓글의 댓글 */
    private int replyLevel;

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getReferId() {
        return referId;
    }

    public void setReferId(int referId) {
        this.referId = referId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public String isUseYN() {
        return useYN;
    }

    public void setUseYN(String useYN) {
        this.useYN = useYN;
    }

    public int getReplyLevel() {
        return replyLevel;
    }

    public void setReplyLevel(int replyLevel) {
        this.replyLevel = replyLevel;
    }
}
