$(function() {
    // 회원탈퇴 버튼 클릭 시 폼 전송
    $("button#memQuitBtn").click(function () {
        $("#memQuitFrm").attr("action", "/member/memberQuitProc.jsp");
        $("#memQuitFrm").submit();
    });
    
    // 슬라이드쇼 시작
    var currentIndex = 0;
    var slides = $('#slideFrame .slide'); // 슬라이드 이미지들
    var totalSlides = slides.length; //슬라이드 개수

    // 슬라이드 보여주는 함수
    function showSlide(index) {
        slides.fadeOut();  // 모든 슬라이드를 숨깁니다.
        $(slides[index]).fadeIn();  // 현재 인덱스의 슬라이드만 보이게 합니다.
    }

    // 자동으로 3초마다 슬라이드를 전환
    setInterval(function() {
        currentIndex = (currentIndex + 1) % totalSlides;  // 다음 슬라이드로 이동
        showSlide(currentIndex);  // 슬라이드를 변경
    }, 3000);

    showSlide(currentIndex);  // 페이지 로드 시 첫 번째 슬라이드를 보여줍니다.
});
