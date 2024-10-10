package my.oochoo.web.service;

import my.oochoo.web.model.Post;

import java.util.List;

public interface PostService {

    /**
     * 게시글 등록
     *
     * @param post Post : 등록할 게시글 정보
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    boolean registerPost(Post post);

    /**
     * 게시글 수정
     *
     * @param post Post : 수정할 게시글 정보
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    boolean modifyPost(Post post);

    /**
     * 게시글 수정
     * @param column String : 수정할 게시글 정보 키
     * @param value String : 수정할 게시글 정보 값
     * @param postId int : 수정할 게시글 일련번호
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    boolean modifyPost(String column, Object value, int postId);

    /**
     * 게시글 삭제
     * @param postId int : 삭제할 게시글 일련번호
     * @param password String : 등록시 입력한 비밀번호
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    boolean deletePost(int postId, String password);

    /**
     * 게시글 비밀번호 확인
     * @param postId int : 해당 게시글 일련번호
     * @param password String : 등록시 입력한 비밀번호
     * @return boolean : 비밀번호 맞으면 true / 아니면 false
     */
    boolean checkPasswordforPost(int postId, String password);
    /**
     * 전체 게시글 조회
     * @param pageNumber int : 현재 페이지
     * @param pageSize int : 페이지 당 게시글 수
     * @return List<Post> : 게시글 목록
     */
    List<Post> getAllPostList(int pageNumber, int pageSize);

    /**
     * 특정 게시글 하나 조회
     * @param postId int : 조회할 하나의 게시글
     * @return Post : 조회한 게시글 정보
     */
    Post getPostById(int postId);

    /**
     * 특정 게시글의 비밀번호 조회
     * @param postId int : 조회할 하나의 비밀번호
     * @return String : 조회한 게시글의 비밀번호
     */
    String getPostPasswordById(int postId);
}
