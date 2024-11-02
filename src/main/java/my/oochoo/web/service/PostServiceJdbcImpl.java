package my.oochoo.web.service;

import my.oochoo.web.model.Page;
import my.oochoo.web.model.Post;
import my.oochoo.web.repository.PostRepository;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PostServiceJdbcImpl implements PostService {
    private final Logger LOGGER = Logger.getLogger(PostServiceJdbcImpl.class.getName());
    private final PostRepository postRepository;
    public PostServiceJdbcImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 모든 게시글 조회
     * @param params Map<String, String> : queryString
     * @return List<Post> 조회된 모든 게시글 리스트
     */
    public List<Post> getAllPostList(Map<String, String> params, Page page) {
        page.setCurrentPageNumber(Integer.parseInt(params.getOrDefault("curPage", "1")));
        page.setPageSize(Integer.parseInt(params.getOrDefault("pageSize", "10")));
        return postRepository.getAllPostList(page).orElseThrow(() -> new IllegalArgumentException("조회된 게시글이 없습니다."));
    }

    /**
     * 특정 게시글의 작성자인지 확인
     *
     * @param postId  int : 특정 게시글의 일련번호
     * @param userKey int : 현재 로그인한 계정의 일련번호
     * @return boolean : 특정 게시글의 작성자가 맞으면 true / 아니면 false
     */
    @Override
    public boolean checkAuthorByPostId(int postId, int userKey) {
        int postUserKey = postRepository.getAuthorByPostId(postId);
        return postUserKey == userKey;
    }
}