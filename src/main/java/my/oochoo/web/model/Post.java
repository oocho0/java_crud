package my.oochoo.web.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 게시글 도메인
 */
public class Post {
    /** 게시글 일련번호 */
    private int postId;

    /** 게시글 제목 */
    private String title;

    /** 게시글 내용 */
    private String content;

    /** 게시글 작성자 */
    private String author;

    /** 게시글 작성자의 비밀번호 */
    private String password;

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


}
