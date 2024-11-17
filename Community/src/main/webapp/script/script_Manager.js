/**
 * 
 */
$(function(){
	
	$("#galleryWriteBtn").click(function(){		
		location.href="/manager/write.jsp?category=gallery";
	});
	$("#writeBtn").click(function(){		
		location.href="/manager/write.jsp?category=notice";
	});
	
	
	$("#regBtn").click(function(){
		let subject = $("#subject").val().trim();		
		
		 if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {
			$("#writeFrm").attr("action", "/manager/writeProc.jsp");
			$("#writeFrm").submit();
		}
	
	});	

	$("button#listBtn").click(function() {
		location.href="/manager/list.jsp?gnbParam=list";
	});

	
	$("button#delBtn").click(function(){
		
		let chkTF = confirm("게시글을 삭제하시겠습니까?");
		
		if (chkTF) {
			let nowPage = $("input#nowPage").val().trim();
			let num = $("input#num").val().trim();
					
			let p3 = $("#pKeyField").val().trim();  // p3 : keyField
		    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
		    
			let url = "/manager/deleteProc.jsp?";
				url += "num="+num+"&nowPage="+nowPage;
				url += "&keyField="+p3;
				url += "&keyWord="+p4;
			location.href=url;
		} else {
			alert("취소하셨습니다.");	
		}
		
	});

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


	$("#modProcBtn").click(function(){
		let subject = $("#subject").val().trim();		
		
		 if (subject == "") {
			alert("제목은 필수입력입니다.");
			$("#subject").focus();
		} else {
			$("#modFrm").attr("action", "/manager/modifyProc.jsp?");
			$("#modFrm").submit();
		}
	
	});	

});
	
	

function read(p1, p2) {
    let p3 = $("#pKeyField").val().trim();  // p3 : keyField
    let p4 = $("#pKeyWord").val().trim();  // p4 : keyWord
	let param = "read.jsp?num="+p1;
	     param += "&nowPage="+p2;
	     param += "&keyField="+p3;
	     param += "&keyWord="+p4 ; 
	location.href=param;
}		



