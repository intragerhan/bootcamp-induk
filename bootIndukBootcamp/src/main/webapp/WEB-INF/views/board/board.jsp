<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page session="true" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId == '' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId== '' ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>인덕부트캠프</title>
		<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
		<style>
			* {
				box-sizing: border-box;
				margin: 0;
				padding: 0;
				font-family: "Noto Sans KR", sans-serif;
			}
			.container {
				width: 50%;
				margin: auto;
			}
			.writing-header {
				position: relative;
				margin: 20px 0 0 0;
				padding-bottom: 10px;
				border-bottm: 1px solid #323232;
			}
			input {
				width: 100%;
				height: 35px;
				margin: 5px 0px 10px 0px;
				border: 1px solid #e9e8e8;
				padding: 8px;
				background: #f8f8f8;
				outline-color: #e6e6e6;
			}
			textarea {
				width: 100%;
				background: #f8f8f8;
				margin: 5px 0px 10px 0px;
				border: 1px solid #e9e8e8;
				resize: none;
				padding: 8px;
				outline-color: #e6e6e6;
			}
			.frm {
				width: 100%;
			}
			.btn {
				background-color: rgb(236, 236, 236);	/* Blue background */
				border: none; /* Remove borders */
				color: black;	/* White text */
				padding: 6px 12px;	/* Some padding */
				font-size: 16px;	/* Set a font size */
				cursor: pointer;	/* Mouse pointer on hover */
				border-radius: 5px;
			}
			.btn:hover {
				text-decoration: underline;
			}
		</style>
	</head>
	<body>
		<div id="menu">
			<ul>
				<li id="logo">Induk Univ.</li>
				<li><a href="<c:url value='/webapp/WEB-INF/views/'/>">Home</a></li>
				<li><a href="<c:url value='../board/list'/>">Board</a></li>
				<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
				<li><a href="<c:url value='/register/add'/>">Sign in</a></li>
				<li><a href=""><i class="fa fa-search"></i></a></li>
			</ul>
		</div>
		<script>
			let msg = "${msg}";
			if(msg == "write_err") alert("등록 중 오류가 발생했습니다.");
			if(msg == "modify_err") alert("수정 중 오류가 발생했습니다.");
		</script>
		<div class="container">
			<h2 class="writing-header">게시물 ${mode == "new" ? "쓰기" : "읽기"}</h2>
			<form action="" class="frm" id="form" method="post">
				<input type="hidden" name="bno" value="${boardDto.bno}">
				
				<input type="text" name="title" value="${boardDto.title}" placeholder="제목을 입력해주세요" ${mode == "new" ? "" : "readonly='readonly'"}>
				<textarea name="contents" rows="20" placeholder="내용을 입력해주세요" ${mode == "new" ? "" : "readonly='readonly'"}>${boardDto.contents}</textarea><br>
				
				<c:if test="${mode eq 'new'}">
					<button type="button" id="writeBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 등록</button>
				</c:if>
				<c:if test="${mode ne 'new'}">
					<button type="button" id="writeNewBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 글쓰기</button>
				</c:if>
				<c:if test="${boardDto.writer eq loginId}">
					<button type="button" id="modifyBtn" class="btn btn-modify"><i class="fa fa-edit"></i> 수정</button>
					<button type="button" id="removeBtn" class="btn btn-remove"><i class="fa fa-trash"></i> 삭제</button>
				</c:if>
				<button type="button" id="listBtn" class="btn btn-list"><i class="fa fa-bars"></i> 목록</button>
			</form>
		</div>
		<script>
			$(document).ready(function() {	// main()
			
				let formCheck = function() {
					let form = document.getElementById("form");
					if(form.title.value == "") {
						alert("제목을 입력해주세요");
						form.title.focus();
						return false;
					}
					
					if(form.contents.value == "") {
						alert("내용을 입력해주세요");
						form.contents.focus();
						return false;
					}
					return true;
				}
			
				$('#writeNewBtn').on("click", function() {
					location.href="<c:url value='/board/write'/>";
				});
			
				$('#writeBtn').on("click", function() {
					let form = $("#form");
					form.attr("action", "<c:url value='/board/write'/>");
					form.attr("method", "post");
					
					if(formCheck())
					form.submit();
				});

				
				$('#modifyBtn').on("click", function() {
					let form = $('#form');
					let isReadOnly = $("input[name=title]").attr('readonly');
					
					// 1. 읽기 상태이면 수정 상태로 변경
					if(isReadOnly == 'readonly') {
						$(".writer-header").html("게시물 수정");
						$("input[name=title]").attr('readonly', false);	// title
						$("textarea").attr('readonly', false);	// content
						$("#modifyBtn").html("<i class='fa fa-pencil'></i> 등록");
						return;
					}
					
					// 2. 수정 상태이면, 수정된 내용을 서버로 전송					
					form.attr("action", "<c:url value='/board/modify${searchCondition.queryString}'/>");
					form.attr("method", "post");
					
					if(formCheck()) {
						form.submit();						
					}
				});
				
				$('#removeBtn').on("click", function() {
					if(!confirm("정말로 삭제하시겠습니까?")) return;
					
					let form = $('#form');
					form.attr("action", "<c:url value='/board/remove${searchCondition.queryString}'/>");
					form.attr("method", "post");
					form.submit();
				});	
				
				$('#listBtn').on("click", function() {
					location.href = "<c:url value='/board/list${searchCondition.queryString}'/>";
				});
			});
		</script>
	</body>
</html>