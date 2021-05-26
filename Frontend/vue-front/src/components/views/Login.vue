<template>
  <form>
    <div class="imgcontainer">
      <img
        src="https://static.skylife.co.kr/images/common/new_logo.png"
        alt=""
      />
    </div>

    <div class="container">
      <label for="uname"><b>아이디</b></label>
      <input type="text" placeholder="아이디 입력" name="uname" required />

      <label for="psw"><b>비밀번호</b></label>
      <input type="password" placeholder="비밀번호 입력" name="psw" required />

      <div class="remember">
        <label class="custom-checkbox"
          >아이디 저장
          <input type="checkbox" />
          <span class="checkmark"></span>
        </label>
        <a href="#" class="pull-right">비밀번호 찾기</a>
      </div>

      <button type="submit">
        <b><big>로그인</big></b>
      </button>
      <div class="regit">계정이 없으신가요? <a href="#">회원가입</a></div>
    </div>
    <div class="foot-font">
      <div class="sub-title">
        <h5>비밀번호관리 안내</h5>
      </div>
      <ul>
        <li>
          스카이라이프는 회원님의 개인정보를 보호하고자 3개월 주기로 비밀번호
          변경안내를 시행하고 있습니다.
        </li>
        <li>
          생년월일, 전화번호 등 개인정보와 관련된 숫자, 연속된 숫자와 같이 쉬운
          비밀번호는 다른 사람이 쉽게 알아낼 수 있으니 사용을 자제해 주세요.
        </li>
        <li>
          이전에 사용했던 비밀번호나 타 사이트와는 다른 비밀번호를 사용 하고,
          비밀번호는 주기적으로 변경해주세요.
        </li>
      </ul>
    </div>
  </form>
</template>

<script>
</script>

<style>
/* Bordered form */
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap");

.foot-font {
  font-size: 13px;
  letter-spacing: -0.05em;
  padding: 0em;
  margin-left: 0px;
  margin-top: 10%;
  margin-right: 3%;
}

.sub-title {
  color: #666;
  font-size: px;
  line-height: 1.5;
  letter-spacing: -0.08em;
  margin-left: 10px;
  padding: 0;
  box-sizing: border-box;
  position: relative;
  margin-bottom: 10px;
  padding-right: 0;
}
.regit {
  text-align: center;
  font-size: 13px;
}
.pull-right {
  float: right;
  font-size: 13px;
  margin-top: 0px;
}
.remember {
  float: left;
  width: 100%;
  margin: 10px 0 0;
}
.custom-checkbox {
  display: block;
  position: relative;
  padding-left: 20px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 13px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  font-weight: normal;

  float: left;
}
/* Hide the browser's default checkbox */
.custom-checkbox input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}
/* Create a custom checkbox */
.custom-checkbox .checkmark {
  position: absolute;
  top: 1.3px;
  left: 0;
  height: 15px;
  width: 15px;
  background-color: #ccc;
}
/* On mouse-over, add a grey background color */
.custom-checkbox:hover input ~ .checkmark {
  background-color: #bfbcbf;
}
/* When the checkbox is checked, add a blue background */
.custom-checkbox input:checked ~ .checkmark {
  background-color: #ee2b33;
}
/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}
/* Show the checkmark when checked */
.custom-checkbox input:checked ~ .checkmark:after {
  display: block;
}
/* Style the checkmark/indicator */
.custom-checkbox .checkmark:after {
  left: 3.1px;
  top: 3px;
  width: 6.5px;
  height: 4px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}
form {
  border: 0px solid #f1f1f1;
  margin-top: 6%;
  max-width: 640px;
  margin-left: auto;
  margin-right: auto;
  font-family: "Noto Sans SC", "Noto Sans KR", sans-serif;
}

/* Full-width inputs */
input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #ee2b33;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

/* Add padding to containers */
.container {
  padding: 16px;
}

.forgot-password {
  text-align: right;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
    display: block;
    float: none;
  }
  .cancelbtn {
    width: 100%;
  }
}
</style>