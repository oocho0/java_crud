package my.oochoo.web.service;

import my.oochoo.jdbc.JdbcTemplate;
import my.oochoo.jdbc.SqlBuilder;
import my.oochoo.web.model.Post;
import my.oochoo.web.repository.PostRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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