<template>
  <div id="header">
    <div class="container">
      <div class="head_container">
        <div class="hdr-wrap">
          <div class="gnb_wrap">
            <h1>
              <router-link to="/">
                <img
                  src="//static.skylife.co.kr/images/common/new_logo.png"
                  alt="SkyLife"
                />
              </router-link>
            </h1>
            <ul class="gnb_box">
              <li @click="setVisit">
                <router-link to="/info" class="recommend"
                  >요금제 추천<img
                    src="../../assets/new.png"
                    alt=""
                    class="new"
                /></router-link>
              </li>

              <li class="direct_box">
                <img src="../../assets/direct.png" />
                <a href="#" onclick="window.open('http://bit.ly/2OXSEe4');"
                  >Direct shop</a
                >
              </li>
              <li>
                <a href="#"> <span></span>TV</a>
                <!-- 18.05.14 -->
              </li>
              <li>
                <a href="#"> <span></span>인터넷</a>
                <!-- 18.05.14 -->
              </li>

              <li>
                <a href="#"> <span></span>모바일</a>
                <!-- 18.05.14 -->
              </li>
              <li>
                <a href="#" class="dep_menu"> <span></span>결합상품</a>
                <!-- 18.05.14 -->
              </li>
              <li>
                <a href="#" class="dep_menu">콘텐츠</a>
              </li>
              <li>
                <a href="#" class="dep_menu">고객지원</a>
              </li>
              <li v-if="id === ''">
                <a href="#" class="dep_menu">MY SKY</a>
              </li>
              <li v-if="id">
                <router-link to="/admin" class="dep_menu"> 관리자</router-link>
              </li>
            </ul>

            <div class="member" v-if="id === ''">
              <span class="btn_login">
                <router-link to="/login" id="login">로그인</router-link>
              </span>
              <span class="btn_mbs">
                <router-link>회원가입</router-link>
              </span>
              <span class="btn_all">
                <router-link to="#">전체메뉴</router-link>
              </span>
            </div>
            <div class="member" v-if="id">
              <span class="btn_account">
                <router-link to="/logout" id="login">{{
                  id["id"]
                }}</router-link></span
              >
              <span class="btn_login">
                <a @click="logout" href="/" id="logout">로그아웃</a>
              </span>
              <span class="btn_all">
                <router-link to="#">전체메뉴</router-link>
              </span>
            </div>
            <!-- <div class="member" v-if="currentUser">
              <span class="btn_login">
                <a href="/profile" id="login"
                  ><font-awesome-icon icon="user" />
                  {{ currentUser.username }}</a
                >
              </span>
              <span class="btn_mbs">
                <a href="#" @click="logOut">로그아웃</a>
              </span>
              <span class="btn_all">
                <a href="#">전체메뉴</a>
              </span>
            </div> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
    <script>
import picture from "../../assets/main.png";
import { mapMutations, mapGetters } from "vuex";
export default {
  data() {
    return { picture: picture };
  },
  computed: {
    ...mapGetters(["getHost"]),
    id() {
      return this.$store.getters.getId;
    },
  },
  methods: {
    ...mapMutations(["logout"]),
    setVisit() {
      var api_url = this.getHost;
      this.$Axios
        .get(api_url + "service/visitcnt")
        .then((res) => console.log("success"))
        .catch((er) => console.log(er));
    },
  },
};
</script>
    <style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap");
.new {
  width: 20px;
  height: 12px;
  /* margin-bottom: 20px; */
  position: absolute;
}
a {
  text-decoration: none;
}
a:hover {
  text-decoration: none;
}
.head_container {
  height: 51px;
}
.back {
  width: 1519px;
}

body {
  overflow-y: scroll;
  background-color: #fff;
  /* -webkit-text-size-adjust: 100%; */
  /* -ms-text-size-adjust: 100%; */
  color: #666;
  font-size: 14px;
  font-size: 1.4rem;
  line-height: 1.5;
  font-family: "Noto Sans SC", "Noto Sans KR", sans-serif;
}

.hdr-wrap {
  right: 0;
  box-shadow: none;
}
.hdr-wrap {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  z-index: 100;
  box-shadow: 0 0 10px rgb(0 0 0 / 20%);
}

*,
html {
  margin: 0;
  padding: 0;
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
div {
  display: block;
}
.gnb_wrap {
}
.gnb_wrap {
  position: relative;
  background-color: #fff;
}
.gnb_wrap h1 {
  position: relative;
  top: auto;
  left: auto;
}
.gnb_wrap h1 {
  position: absolute;
  top: 17px;
  left: 50px;
  font-size: 0;
}
h1 {
  display: block;
  font-size: 2em;
  margin-block-start: 0.67em;
  margin-block-end: 0.67em;
  margin-inline-start: 0;
  margin-inline-end: 0;
  font-weight: bold;
}
.gnb_wrap h1 a {
  display: block;
  height: 18px;
}
a {
  color: #666;
  text-decoration: none;
}

img {
  border: 0;
  vertical-align: middle;
}
.gnb_wrap h1 img {
  height: 27px;
}
.gnb_wrap .gnb_box {
  font-family: "Noto Sans SC", "Noto Sans KR", sans-serif;
  padding-left: 260px;
  text-align: left;
}

.gnb_wrap .gnb_box > li {
  display: inline-block;
  position: relative;
  vertical-align: middle;
  text-align: left;
}

.gnb_wrap .gnb_box .direct_box {
  padding-right: 0px;
}
.gnb_wrap .gnb_box > li.direct_box > a {
  padding-left: 0;
  font-size: 15px;
  /* text-indent: -9999em; */
}

.gnb_wrap .gnb_box > li > a {
  display: inline-block;
  padding-top: 15px;
  padding-bottom: 20px;
  padding-left: 19px;
  padding-right: 19px;
  color: #333;
  font-weight: bold;
  font-size: 18px;
  text-decoration: none;
}
ul .gnb_box {
  margin-top: 0;
}
.member {
  position: absolute;
  top: 0;
  right: 0;
  font-size: 0;
  font-family: "Noto Sans SC", "Noto Sans KR", sans-serif;
}
.member > span.btn_login > a {
  background-position: 1 1%;
}

.member > span > a {
  color: #333;
  text-decoration: none;
}

.member > span > a {
  display: inline-block;
  width: 79px;
  min-height: 61px;
  padding-top: 20px;
  padding-bottom: 5px;
  border-left: 1px solid #e8e8e8;
  font-size: 14px;
  text-align: center;
}
a:hover {
  text-decoration: none;
}
.member > span.btn_mbs > a {
  background-position: -53px 28%;
}
.member > span.btn_all > a {
  /* background-image: url(../images/common/btn_allmenu.png); */
  background-position: center 28%;
  background-repeat: no-repeat;
}
.gnb_wrap .gnb_box > li > a:hover,
.gnb_wrap .gnb_box > li.active > a,
.gnb_wrap .gnb_box > li:hover,
.gnb_wrap .gnb_box > li:hover > a {
  background-color: #333;
  color: #fff;
}
.gnb_wrap .gnb_box > li > img {
  padding-bottom: 7px;
  margin-top: 3px;
  width: 20px;
  height: 30px;
}

.member > span > a:hover {
  background-color: #e7e7e7;
  /* color: #fff; */
}
.container {
  padding: 0px;
}

.gnb_wrap .gnb_box > li .recommend {
  /* color: #ee2b33; */
  /* border-radius: 20px; */
  margin-right: 20px;
}
</style>