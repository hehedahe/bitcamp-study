<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>MyList!</title>
  <link href="/css/common.css" rel="stylesheet">
</head>
<body>
<div class="container">

<div id="header">
<jsp:include page="/jsp/header.jsp"></jsp:include>
</div>

<div id="sidebar">
<jsp:include page="/jsp/sidebar.jsp"></jsp:include>
</div>

<div id="content">

<jsp:useBean id="board" type="com.eomcs.mylist.domain.Board" class="com.eomcs.mylist.domain.Board" scope="request"></jsp:useBean>
<!-- type 속성과 class 속성 값이 같은 경우, type은 생략 가능하다 -->

<h1>게시글 상세3(+ JSP 액션 태그)</h1>
<form action='update' method='post'>
번호: <input name="no" type="text" value='<%=board.getNo()%>' readonly><br>
제목*: <input name="title" type="text" value='<%=board.getTitle()%>'><br>
내용*: <textarea name="content" cols="50" rows="10"><%=board.getContent()%></textarea><br>
작성자: <span><%=board.getWriter().getName()%></span><br>
조회수: <span><%=board.getViewCount()%></span><br>
등록일: <span><%=board.getCreatedDate()%></span><br>
별표(*) 항목은 필수 입력입니다.<br>
<button>변경</button>
<button id='delete-btn' type="button">삭제</button>
<button id='cancel-btn' type="button">취소</button>
</form>
</div>

<div id="footer">
<jsp:include page="/jsp/footer.jsp"></jsp:include>
</div>

</div>

<script>
document.querySelector('#delete-btn').onclick = () => {
  location.href = 'delete?no=' + document.querySelector('input[name=no]').value;
}
document.querySelector('#cancel-btn').onclick = () => {
  location.href = 'list';
}
</script>

</body>
</html>
