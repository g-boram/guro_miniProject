<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>슬라이드 이미지 구현</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        /* 슬라이더 컨테이너 스타일 */
        .slider-container {
            width: 100%;
            max-width: 1400px;
            margin: 0 auto;
            overflow: hidden;
        }

        .slides {
            display: flex;
            transition: transform 0.5s ease-in-out;
        }

        .slide {
            min-width: 100%;
            box-sizing: border-box;
        }

        .slide img {
            width: 100%;
            height: auto;
        }

        /* 하단 점 스타일 */
        .dots {
            text-align: center;
            margin-top: 10px;
        }

        .dots .dot {
            display: inline-block;
            width: 10px;
            height: 10px;
            margin: 0 5px;
            background-color: #bbb;
            border-radius: 50%;
            cursor: pointer;
            transition: background-color 0.6s ease;
        }

        .dots .dot.active {
            background-color: #717171;
        }
    </style>
</head>
<body>

    <!-- 슬라이드 이미지 영역 -->
    <div class="slider-container">
        <div class="slides">
            <div class="slide"><img src="../images/slideImg/slide1.png" alt="Slide 1"></div>
            <div class="slide"><img src="../images/slideImg/slide2.png" alt="Slide 2"></div>
            <div class="slide"><img src="../images/slideImg/slide3.png" alt="Slide 3"></div>
        </div>
    </div>

    <!-- 하단 점 -->
    <div class="dots">
        <span class="dot active" data-index="0"></span>
        <span class="dot" data-index="1"></span>
        <span class="dot" data-index="2"></span>
    </div>

    <script>
        $(document).ready(function() {
            let currentIndex = 0;
            const totalSlides = $('.slides .slide').length;

            // 슬라이드 이동 함수
            function moveToSlide(index) {
                const slideWidth = $('.slider-container').width();
                $('.slides').css('transform', 'translateX(' + (-index * slideWidth) + 'px)');

                // 점 업데이트
                updateDots(index);
            }

            // 하단 점 업데이트 함수
            function updateDots(index) {
                $('.dot').removeClass('active');
                $('.dot').eq(index).addClass('active');
            }

            // 점 클릭 시 슬라이드 이동
            $('.dot').on('click', function() {
                const index = $(this).data('index');
                moveToSlide(index);
                currentIndex = index;
            });

            // 자동 슬라이드 전환
            function autoSlide() {
                setInterval(function() {
                    currentIndex = (currentIndex + 1) % totalSlides;
                    moveToSlide(currentIndex);
                }, 3000); 
            }

            // 첫 번째 슬라이드로 초기화
            moveToSlide(currentIndex);
            autoSlide(); 
        });
    </script>

</body>
</html>
