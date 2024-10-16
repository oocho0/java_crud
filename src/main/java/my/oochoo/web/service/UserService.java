package my.oochoo.web.service;

import my.oochoo.web.model.User;

import java.util.Optional;

public interface UserService {
    /**
     * 사용자 비밀번호 확인
     * @param userId String : 입력한 사용자 아이디
     * @param password String : 입력한 사용자 비밀번호
     * @return boolean : 로그인 성공 시 true / 실패 시 false
     */
    boolean login(String userId, String password);
}
