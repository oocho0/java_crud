package my.oochoo.web.service;

import my.oochoo.jdbc.JdbcTemplate;
import my.oochoo.jdbc.SqlBuilder;
import my.oochoo.web.model.User;
import my.oochoo.web.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class UserServiceJdbcImpl implements UserService {
    private final Logger LOGGER = Logger.getLogger(UserServiceJdbcImpl.class.getName());
    private final UserRepository userRepository;
    public UserServiceJdbcImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 사용자 비밀번호 확인
     *
     * @param userId   String : 입력한 사용자 아이디
     * @param password String : 입력한 사용자 비밀번호
     * @return boolean : 로그인 성공 시 true / 실패 시 false
     */
    @Override
    public boolean login(String userId, String password) {
        User loginUser = userRepository.getUserByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        String userPassword = loginUser.getPassword();

        return userPassword.equals(password);
    }

    /**
     * 현재 로그인한 계정과 탈퇴한 계정이 같은지 확인
     *
     * @param userKey  int : 탈퇴할 계정의 일련번호
     * @param loginUserKey int : 현재 로그인한 계정의 일련번호
     * @return boolean : 현재 로그인한 계정이 탈퇴할 계정이 맞으면 true / 아니면 false
     */
    private boolean checkUserByUserKey(int userKey, int loginUserKey) {
        return userKey == loginUserKey;
    }

}
