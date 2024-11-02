<%@ page session="true" %>
<div class="header">
    <!-- 로고 및 홈페이지 링크 -->
    <a href="index.jsp">
        <img id="siteLogo" src="logo.png" alt="Website Logo">
    </a>

    <!-- 로그인 여부에 따라 버튼을 다르게 표시 -->
    <div class="auth-buttons">
        <c:choose>
            <c:when test="${empty user}">
                <button type="button">회원가입</button>
                <button type="button">로그인</button>
            </c:when>
            <c:otherwise>
                <button type="button">로그아웃</button>
            </c:otherwise>
        </c:choose>
    </div>
</div>
