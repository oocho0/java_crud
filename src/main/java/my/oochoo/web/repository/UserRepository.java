package my.oochoo.web.repository;

import my.oochoo.web.model.User;

import java.util.Optional;

public interface UserRepository {
    /**
     * 사용자 가입
     * @param user User : 등록할 사용자 정보
     * @return boolean : 가입 성공하면 true / 아니면 false
     */
    boolean registerUser(User user);

    /**
     * 사용자 탈퇴
     *
     * @param userKey int : 탈퇴할 사용자 키
     * @return boolean : 탈퇴 성공하면 true / 아니면 false
     */
    boolean deleteUser(int userKey);

    /**
     * 사용자 정보 수정
     * @param user User : 수정할 사용자 정보
     * @return boolean : 수정 성공하면 true / 아니면 false
     */
    boolean modifyUser(User user);

    /**
     * 사용자 정보 수정
     * @param column String : 수정할 사용자 정보 키
     * @param value String : 수정할 사용자 정보 값
     * @param userKey int : 수정할 사용자 키
     * @return boolean : 수정 성공하면 true / 아니면 false
     */
    boolean modifyUser(String column, Object value, int userKey);

    /**
     * 특정 사용자 정보 조회
     * @param userKey int : 특정 사용자 키
     * @return User : 조회한 사용자 정보
     */
    Optional<User> getUserById(int userKey);

    /**
     * 특정 사용자 정보 조회
     * @param userId String : 특정 사용자 ID
     * @return User : 조회한 사용자 정보
     */
    Optional<User> getUserByUserId(String userId);

    /**
     * 특정 사용자 비밀번호 조회
     * @param userKey int : 특정 사용자 키
     * @return String : 조회한 사용자 비밀번호
     */
    String getUserPasswordById(int userKey);
}
