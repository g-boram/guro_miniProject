$(function() {
	$("button#memQuitBtn").click(function () {
		$("#memQuitFrm").attr("action", "/member/memberQuitProc.jsp");
		$("#memQuitFrm").submit();
	});
});