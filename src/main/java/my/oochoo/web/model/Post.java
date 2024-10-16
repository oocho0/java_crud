package my.oochoo.web.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 게시글 도메인
 */
public class Post {
    /** 게시글 일련번호 */
    private int postId;

    /** 게시글 등록자 */
    private int userKey;

    /** 게시글 제목 */
    private String title;

    /** 게시글 내용 */
    private String content;

    /** 게시글 최초 작성 일자 */
    private Date regDate;

    /** 게시글 마지막 수정 일자 */
    private Date modDate;

    /** 게시글 삭제 여부 */
    private String useYN;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUseYN() {
        return useYN;
    }

    public void setUseYN(String useYN) {
        this.useYN = useYN;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> postMap = new HashMap<>();
        if (this.title != null) {
            postMap.put("title", this.title);
        }
        if (this.userKey != 0) {
            postMap.put("userKey", this.userKey);
        }
        if (this.content != null) {
            postMap.put("content", this.content);
        }
        if (this.regDate != null) {
            postMap.put("regDate", this.regDate);
        }
        if (this.modDate != null) {
            postMap.put("modDate", this.modDate);
        }
        if (this.useYN != null) {
            postMap.put("useYN", this.useYN);
        }
        return postMap;
    }
}
