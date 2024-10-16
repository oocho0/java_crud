package my.oochoo.web.service;

import my.oochoo.web.model.Post;

import java.util.List;

public interface PostService {
    /**
     * 특정 게시글의 작성자인지 확인
     *
     * @param postId  int : 특정 게시글의 일련번호
     * @param userKey int : 확안할 작성자의 일련번호
     * @return boolean : 특정 게시글의 작성자가 맞으면 true / 아니면 false
     */
    boolean checkAuthorByPostId(int postId, int userKey);
}