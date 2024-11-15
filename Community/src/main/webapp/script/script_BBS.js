/**
 * 
 */
$(function(){
	
	
	/* 리스트 페이지 글쓰기 버튼 시작 /bbs/list.jsp */	
	$("#loginAlertBtn").click(function(){		
		alert("로그인 후 게시글을 작성하실 수 있습니다.");
		location.href="/member/login.jsp";
	});	
	
	$("#writeBtn").click(function(){		
		location.href="/bbs/write.jsp";
	});
	/* 리스트 페이지 글쓰기 버튼 끝 /bbs/list.jsp */
	
	
	/* 글쓰기 페이지 게시글 등록 시작 /bbs/write.jsp */
	$("#regBtn").click(function(){
		let subject = $("#subject").val().trim();		
		
		 if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {
			$("#writeFrm").attr("action", "/bbs/writeProc.jsp");
			$("#writeFrm").submit();
		}
	
	});	
	
	/* 글쓰기 페이지 게시글 등록 끝 /bbs/write.jsp */
	
	/* 게시글 목록으로 돌아가기 버튼 시작 /bbs/read.jsp -> /bbs/list.jsp */
	$("button#listBtn").click(function() {
		location.href="/bbs/list.jsp";
	});
	/* 게시글 목록으로 돌아가기 버튼 끝 /bbs/read.jsp -> /bbs/list.jsp */
	
	/* 답글버튼 시작 /bbs/read.jsp -> /bbs/reply.jsp */
	$("button#replyBtn").click(function() {
		let nowPage = $("#nowPage").val();
		let num = $("#num").val();
		let keyField = $("#pKeyField").val();
		let keyWord = $("pKeyWord").val();
		
		location.href = "/bbs/reply.jsp?num=" + num + "&nowPage=" + nowPage + "&keyField=" + keyField + "&keyWord=" + keyWord;
	});
	/* 답글버튼 끝 /bbs/read.jsp -> /bbs/reply.jsp */
	
	/* 답글 페이지 뒤로 돌아가기 버튼 시작 */
	$("#backBtn").click(function() {
		history.back();
	});
	/* 답글 페이지 뒤로 돌아가기 버튼 끝 */
	
	/* 답글 페이지 답글 등록 버튼 시작 */
	$("#replySbmBtn").click(function() {
		$("#replyFrm").submit();
	});
	/* 답글 페이지 답글 등록 버튼 끝 */
	
	/* 게시글 삭제버튼 시작 /bbs/read.jsp */
	$("button#delBtn").click(function(){
		
		let chkTF = confirm("게시글을 삭제하시겠습니까?");
		
		if (chkTF) {
			let nowPage = $("input#nowPage").val().trim();
			let num = $("input#num").val().trim();
					
			let p3 = $("#pKeyField").val().trim();  // p3 : keyField
		    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
		    
			let url = "/bbs/deleteProc.jsp?";
				url += "num="+num+"&nowPage="+nowPage;
				url += "&keyField="+p3;
				url += "&keyWord="+p4;
			location.href=url;
		} else {
			alert("취소하셨습니다.");	
		}
		
	});
	/* 게시글 삭제버튼 끝 /bbs/read.jsp */
	
	/* 게시글 내용 수정 페이지 이동 시작 /bbs/read.jsp => /bbs/modify.jsp */
	$(document).on("click", "button#modBtn", function(){	
			
		let num = $("input#num").val().trim();

		let nowPage = $("input#nowPage").val().trim();					
		let p3 = $("#pKeyField").val().trim();  // p3 : keyField
	    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord	
	
		let url = "/bbs/modify.jsp?";
			url += "num="+num+"&nowPage="+nowPage;
			url += "&keyField="+p3;
			url += "&keyWord="+p4;
		console.log("Redirect URL : " + url);
		location.href=url;
			
	});
	/* 게시글 내용 수정 페이지 이동 끝 /bbs/read.jsp => /bbs/modify.jsp */



	/* 글수정 페이지 게시글 수정 시작 /bbs/modifyProc.jsp */
	$("#modProcBtn").click(function(){
		let subject = $("#subject").val().trim();		
		
		 if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {
			$("#modFrm").attr("action", "/bbs/modifyProc.jsp?");
			$("#modFrm").submit();
		}
	
	});	
	
	/* 글수정 페이지 게시글 수정 끝 /bbs/modifyProc.jsp */
	
});
	
	
/* 상세내용 보기 페이지 이동 시작 /bbs/list.jsp => read.jsp */
function read(p1, p2) {
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let param = "read.jsp?num="+p1;
	     param += "&nowPage="+p2;
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4 ; 
	location.href=param;
}		
/* 상세내용 보기 페이지 이동 끝 /bbs/list.jsp => read.jsp  */


