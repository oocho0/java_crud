package my.oochoo.web.repository;

import my.oochoo.jdbc.JdbcTemplate;
import my.oochoo.jdbc.SqlBuilder;
import my.oochoo.web.model.Page;
import my.oochoo.web.model.Post;
import my.oochoo.web.service.PostServiceJdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class PostRepositoryJdbcImpl implements PostRepository{
    private final Logger LOGGER = Logger.getLogger(PostRepositoryJdbcImpl.class.getName());
    private final JdbcTemplate jdbcTemplate;
    public PostRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 게시글 등록
     *
     * @param post Post : 등록할 게시글 정보
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    @Override
    public boolean registerPost(Post post) {
        Map<String, Object> postMap = post.toMap();
        String sql = new SqlBuilder().setInsertValues("post", postMap)
                .setDuplicate(postMap)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    /**
     * 게시글 수정
     *
     * @param post Post : 수정할 게시글 정보
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    @Override
    public boolean modifyPost(Post post) {
        int postId = post.getPostId();
        String sql = new SqlBuilder().setUpdate("post")
                .setSet(post.toMap())
                .setWhere("post_id", "=", postId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    /**
     * 게시글 수정
     * @param column String : 수정할 게시글 정보 키
     * @param value String : 수정할 게시글 정보 값
     * @param postId int : 수정할 게시글 일련번호
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    @Override
    public boolean modifyPost(String column, Object value, int postId) {
        String sql = new SqlBuilder().setUpdate("post")
                .setSet(column, value)
                .setWhere("post_id", "=", postId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    /**
     * 게시글 삭제
     *
     * @param postId int : 삭제할 게시글 일련번호
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    @Override
    public boolean deletePost(int postId) {
        String sql = new SqlBuilder().setUpdate("post")
                .setSet("use_yn", "N")
                .setWhere("post_id", "=", postId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.updateQuery(sql) != 0;
    }

    /**
     * 전체 게시글 조회
     * @param page Page : 페이지 정보
     *             currentPageNumber : 현재 페이지 번호
     *             pageSize : 페이지 당 게시글 수
     * @return List<Post> : 게시글 목록
     */
    @Override
    public Optional<List<Post>> getAllPostList(Page page) {
        String sql = new SqlBuilder().setSelect("*")
                .setFrom("post")
                .setWhere("use_yn", "=", "Y")
                .setOrderByDesc("regDate")
                .setLimit((page.getCurrentPageNumber() - 1) * page.getPageSize(), page.getPageSize())
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.selectQuery(sql, this::getPostList);
    }

    /**
     * 특정 게시글 하나 조회
     * @param postId int : 조회할 하나의 게시글
     * @return Post : 조회한 게시글 정보
     */
    @Override
    public Optional<Post> getPostById(int postId) {
        String sql = new SqlBuilder().setSelect("*")
                .setFrom("post")
                .setWhere("post_id", "=", postId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.selectQuery(sql, this::getPost);
    }

    /**
     * 특정 게시글의 작성자 조회
     *
     * @param postId int : 특정 게시글의 일련번호
     * @return String : 조회한 게시글의 작성자 일련번호
     */
    @Override
    public int getAuthorByPostId(int postId) {
        String sql = new SqlBuilder().setSelect("user_key")
                .setFrom("post")
                .setWhere("post_id", "=", postId)
                .build();
        LOGGER.info(sql);
        return jdbcTemplate.selectQuery(sql, resultSet -> resultSet.getInt("user_key"));
    }

    private Optional<List<Post>> getPostList(ResultSet resultSet) {
        List<Post> postList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                getPost(resultSet).ifPresentOrElse(postList::add, () -> {throw new RuntimeException("Post not found");});
            }
            return Optional.of(postList);
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<Post> getPost(ResultSet resultSet) {
        Post post = new Post();
        try {
            post.setPostId(resultSet.getInt("postId"));
            post.setUserKey(resultSet.getInt("userKey"));
            post.setTitle(resultSet.getString("title"));
            post.setContent(resultSet.getString("content"));
            post.setRegDate(resultSet.getDate("regDate"));
            post.setModDate(resultSet.getDate("modDate"));
            post.setUseYN(resultSet.getString("useYN"));
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return Optional.empty();
        }
        return Optional.of(post);
    }
}
