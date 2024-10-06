package my.oochoo.web.service;

import my.oochoo.jdbc.JdbcTemplate;
import my.oochoo.jdbc.SqlBuilder;
import my.oochoo.web.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PostServiceJdbcImpl implements PostService {
    private final Logger LOGGER = Logger.getLogger(PostServiceJdbcImpl.class.getName());
    private final JdbcTemplate jdbcTemplate;
    public PostServiceJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 전체 게시글 조회
     * @param pageNumber int : 현재 페이지
     * @param pageSize int : 페이지 당 게시글 수
     * @return List<Post> : 게시글 목록
     */
    @Override
    public List<Post> selectAllPostList(int pageNumber, int pageSize) {
        String sql = new SqlBuilder().setSelect("*")
                                    .setFrom("POST")
                                    .setWhere("use_yn", "=", "Y")
                                    .setOrderByDesc("regDate")
                                    .setLimit((pageNumber - 1) * pageSize, pageSize).build();
        return jdbcTemplate.selectQuery(sql, this::getPostList);
    }


    /**
     * 게시글 등록
     *
     * @param post Post : 등록할 게시글 정보
     * @return boolean : 등록 성공하면 true / 아니면 false
     */
    @Override
    public boolean registerPost(Post post) {
        return false;
    }

    /**
     * 게시글 수정
     *
     * @param post Post : 수정할 게시글 정보
     * @return boolean : 수정 성공하면 true / 아니면 false
     */
    @Override
    public boolean modifyPost(Post post) {
        return false;
    }

    /**
     * 게시글 삭제
     *
     * @param postId   int : 삭제할 게시글 일련번호
     * @param password String : 등록시 입력한 비밀번호
     * @return boolean : 삭제 성공하면 true / 아니면 false
     */
    @Override
    public boolean deletePost(int postId, String password) {
        return false;
    }

    /**
     * 게시글 비밀번호 확인
     *
     * @param postId   int : 해당 게시글 일련번호
     * @param password String : 등록시 입력한 비밀번호
     * @return boolean : 비밀번호 맞으면 true / 아니면 false
     */
    @Override
    public boolean checkPasswordforPost(int postId, String password) {
        return false;
    }

    private List<Post> getPostList(ResultSet resultSet) throws SQLException {
        List<Post> postList = new ArrayList<>();
        while (resultSet.next()) {
            Post post = new Post();
            post.setPostId(resultSet.getInt("postId"));
            post.setTitle(resultSet.getString("title"));
            post.setContent(resultSet.getString("content"));
            post.setAuthor(resultSet.getString("author"));
            post.setRegDate(resultSet.getDate("regDate"));
            post.setModDate(resultSet.getDate("modDate"));
            postList.add(post);
        }
        return postList;
    }
}