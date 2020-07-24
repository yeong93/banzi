<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<style>

/* 여기어때 잘난체 */
@font-face {
	font-family: "yg-jalnan";
	src:
		url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff")
		format("woff");
	font-weight: normal;
	font-style: normal;
}

body {
	margin: 0;
	padding: 0;
}

.empty {
	width: 100%;
	height: 200px;
}

.intro {
	font-size: 30px;
	text-align: center;
	margin: 10px;
	font-weight: bolder;
	font-family: "yg-jalnan";
	color: rgb(87, 87, 87);
	margin-bottom: 0px;
}

.line-height {
	margin: auto;
	width: 100px;
	border: 1px solid grey;
	margin-bottom: 20px;
}

.divide {
	width: 100%;
	height: 40px;
}

.container {
	position: relative;
	width: 1200px;
	height: 300px;
	margin: auto;
	margin-top: 30px;
	margin-bottom: 0px;

	/* background-color: #000; */
}

.container .box {
	position: relative;
	width: calc(370px - 30px);
	height: calc(270px - 30px);
	background-color: #eee;
	float: left;
	margin: 15px;
	box-sizing: border-box;
	overflow: hidden;
	border-radius: 15px;
	border: 4px solid rgba(250, 156, 88, 0.3);
}

.container .box .icon {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	/* background: #f00; */
	transition: 0.5s;
	z-index: 1;
}

.container .box:hover .icon {
	/* top:20px; */
	/* left: calc(50% - 40px); */
	/* width: 80px; */
	/* height: 80px; */
	/* 이미지 위 폰트 적용 */
	width: 100%;
	height: 100%;
	opacity: 10%;
	border-radius: 50%;
}

.container .box .icon img {
	/* position: relative; */
	position: absolute;
	top: 50%;
	left: 50%;
	width: 100%;
	height: 100%;
	transform: translate(-50%, -50%);
	font-size: 80px;
	transition: 0.5s;
	color: #fff;
}

.container .box:hover .icon img {
	font-size: 40px;
}

.container .box .content {
	position: relative;
	top: 100%;
	height: calc(100% - 100px);
	text-align: center;
	padding: 20px;
	box-sizing: border-box;
	transition: 0.5s;
	opacity: 0;
}

.container .box:hover .content {
	top: 100px;
	opacity: 1;
}

.container .box .content h3 {
	margin: 0 0 10px;
	padding: 0;
	color: rgb(250, 156, 88);
	font-size: 30px;
	text-align: center;
	line-height: 24px;
	font-family: "yg-jalnan";
}

.container .box .content .hospital {
	font-size: 18px;
	font-weight: 550;
	color: rgb(100, 100, 100);
	line-height: 12px;
}

.container .box .content p {
	line-height: 17px;
	margin: 4px;
	padding: 0;
	font-size: 13px;
	color: rgb(100, 100, 100);
}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<p class="empty"></p>
	<p class="intro">수의사</p>
	<p class="line-height"></p>
	<p align="center">
		반지는 인증 절차를 통해 검증받은 수의사와 훈련가의 전문적인 Q&A와 이벤트를 통해 전문적인 지식을 제공합니다. <br>
		차별화된 반지 사이트를 통해 특별한 경험을 느껴보세요.
	</p>

	<!-- 첫 번째 줄 -->
	<div class="container">
		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사1.jpg"></img>
			</div>
			<div class="content">
				<h3>김지석</h3>
				<p class="hospital">서울 동물병원</p>
				<p>
					서울대학교 수의과대학 수의산과학 석사 졸업 <br> 강남 H 동물병원 센터장 <br>
					http://seoulanimalhos.co.kr
				</p>
			</div>
		</div>

		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사2.jpg"></img>
			</div>
			<div class="content">
				<h3>박민정</h3>
				<p class="hospital">종로새동물병원</p>
				<p>
					충남대학교 수의과대학 수의학과 졸업 <br> 종로새동물병원 수의과대학 심장센터 수의사 <br>
					http://seoulanimalhos.co.kr
				</p>
			</div>
		</div>

		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사3.jpg"></img>
			</div>
			<div class="content">
				<h3>김서하</h3>
				<p class="hospital">장안ON동물병원</p>
				<p>
					연세대학교 수의과대학 수의학과 졸업 <br> VIP동물의료센터 장안점 진료수의사 <br>
					http://vipjanganclinic.co.kr
				</p>
			</div>
		</div>
	</div>
	<!-- 끝 -->


	<!-- 두 번째 줄 -->
	<div class="container">
		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사4.jpg"></img>
			</div>
			<div class="content">
				<h3>한진호</h3>
				<p class="hospital">베스트도그병원</p>
				<p>
					전남대학교 수의과대학 졸업 <br> 現) 강남 베스트도그 부원장 http://bestwithdog.co.kr
				</p>
			</div>
		</div>

		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사5.jpg"></img>
			</div>
			<div class="content">
				<h3>심숙영</h3>
				<p class="hospital">노원N동물병원</p>
				<p>
					건국대학교 수의과대학 졸업 <br> 현 노원 24시 N동물의료센터 영상진단과장
					http://24newcarecenter.co.kr
				</p>
			</div>
		</div>

		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사1.jpg"></img>
			</div>
			<div class="content">
				<h3>김가연</h3>
				<p class="hospital">로얄메디컬동물병원</p>
				<p>
					서울대학교 수의과대학 졸업 <br> 現) 로얄 동물 메디컬센터 부원장/진료팀장
					http://royalamc.co.kr
				</p>
			</div>
		</div>
	</div>
	<!--수의사 끝 -->
	
	<!-- 두 번째 줄 -->
	<div class="container">
		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사4.jpg"></img>
			</div>
			<div class="content">
				<h3>한진호</h3>
				<p class="hospital">베스트도그병원</p>
				<p>
					전남대학교 수의과대학 졸업 <br> 現) 강남 베스트도그 부원장 http://bestwithdog.co.kr
				</p>
			</div>
		</div>


		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사1.jpg"></img>
			</div>
			<div class="content">
				<h3>김가연</h3>
				<p class="hospital">로얄메디컬동물병원</p>
				<p>
					서울대학교 수의과대학 졸업 <br> 現) 로얄 동물 메디컬센터 부원장/진료팀장
					http://royalamc.co.kr
				</p>
			</div>
		</div>
	</div>
	<!--수의사 끝 -->

	<p class="divide"></p>
	<!-- 훈련사 시작 -->
	<p class="intro">훈련사</p>
	<p class="line-height"></p>

	<!-- 첫 번째 줄 -->
	<div class="container">
		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사1.jpg"></img>
			</div>
			<div class="content">
				<h3>김지석</h3>
				<p class="hospital">서울 동물병원</p>
				<p>
					서울대학교 수의과대학 수의산과학 석사 졸업 <br> 강남 H 동물병원 센터장 <br>
					http://seoulanimalhos.co.kr
				</p>
			</div>
		</div>

		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사2.jpg"></img>
			</div>
			<div class="content">
				<h3>박민정</h3>
				<p class="hospital">종로새동물병원</p>
				<p>
					충남대학교 수의과대학 수의학과 졸업 <br> 종로새동물병원 수의과대학 심장센터 수의사 <br>
					http://seoulanimalhos.co.kr
				</p>
			</div>
		</div>

		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사3.jpg"></img>
			</div>
			<div class="content">
				<h3>김서하</h3>
				<p class="hospital">장안on동물병원</p>
				<p>
					충남대학교 수의과대학 수의학과 졸업 <br> VIP동물의료센터 장안점 진료수의사 <br>
					http://vipjanganclinic.co.kr
				</p>
			</div>
		</div>
	</div>
	<!-- 끝 -->


	<!-- 첫 번째 줄 -->
	<div class="container">
		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사4.jpg"></img>
			</div>
			<div class="content">
				<h3>한진호</h3>
				<p class="hospital">베스트도그병원</p>
				<p>
					전남대학교 수의과대학 졸업 <br> 現) 강남 베스트도그 부원장 http://bestwithdog.co.kr
				</p>
			</div>
		</div>

		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사5.jpg"></img>
			</div>
			<div class="content">
				<h3>심숙영</h3>
				<p class="hospital">노원N동물병원</p>
				<p>
					건국대학교 수의과대학 졸업 <br> 현 노원 24시 N동물의료센터 영상진단과장
					http://24newcarecenter.co.kr
				</p>
			</div>
		</div>

		<div class="box">
			<div class="icon">
				<img src="/resources/img/수의사1.jpg"></img>
			</div>
			<div class="content">
				<h3>김가연</h3>
				<p class="hospital">로얄메디컬동물병원</p>
				<p>
					서울대학교 수의과대학 졸업 <br> 現) 로얄 동물 메디컬센터 부원장/진료팀장
					http://royalamc.co.kr
				</p>
			</div>
		</div>
	</div>
	<!--훈련사 끝 -->
	<p class="divide"></p>

</body>
</html>