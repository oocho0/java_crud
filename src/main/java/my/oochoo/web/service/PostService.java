package my.oochoo.web.service;

import my.oochoo.web.model.Page;
import my.oochoo.web.model.Post;

import java.util.List;
import java.util.Map;

public interface PostService {
    /**
     * 특정 게시글의 작성자인지 확인
     *
     * @param postId  int : 특정 게시글의 일련번호
     * @param userKey int : 확안할 작성자의 일련번호
     * @return boolean : 특정 게시글의 작성자가 맞으면 true / 아니면 false
     */
    boolean checkAuthorByPostId(int postId, int userKey);

    /**
     * 모든 게시글 조회
     * @param params Map<String, String> : queryString
     * @return List<Post> 조회된 모든 게시글 리스트
     */
    List<Post> getAllPostList(Map<String, String> params, Page page);
}