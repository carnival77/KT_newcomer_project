<template lang="">
  <div id="body">
      <!-- VueSlideBar -->
      <!-- https://vuejsexamples.com/very-simple-vue-slider-bar-component/ -->

    <div class="wrapper">
      <div class="back_icon_wrapper" @click="go_back">
        <img src="../../assets/chevron-left-solid.svg" class="back_icon"/>
        <p style="margin-top: 7px; font-size: 20px">{{backPageName}}</p>
      </div>
      <p class="title_1">원하시는 <br> 월 데이터/음성 이용량을 선택해 주세요.</p>
      <div class="notice">
          <p>* 기존에 KT를 사용하고 계신가요? </p>
          <a href="https://login.kt.com/wamui/AthWeb.do?urlcd=https://my.kt.com/main.jsp" target="_blank"><p>기존요금제 조회</p></a>

      </div>
      

      <p class="title_2">음성</p>
      <div class="slideWrapper">
        <VueSlideBar
          v-model="slider_v.value"
          :min="slider_v.min"
          :max="slider_v.max"
          :value="slider_v.value"
          :lineHeight="slider_v.lineHeight"
          :processStyle="{ backgroundColor: '#ee2b33' }"
          @callbackRange="callbackRange"
        >
          <template slot="tooltip">
            <img src="../../assets/slider_tooltip.png" />
          </template>
        </VueSlideBar>
        <ul class="stepNum">
            <li class="gbv1"><span>0분</span></li>
            <li class="gbv2"><span class="t1">100</span></li>
            <li class="gbv3"><span>200</span></li>
            <li class="gbv4"><span>300</span></li>
            <li class="gbv5"><span>400</span></li>
            <li class="gbvL"><span>500분<small>이상</small></span></li>
        </ul>
        <div class="stepBtn">
            <span class="voiceA">
                <strong>{{slider_v.value}}</strong>분
            </span>
            <span class="slider-minus1 voiceBtn" @click="v_minus1">-1</span>
            <span class="slider-plus1 voiceBtn" @click="v_plus1">+1</span>
            <span class="slider-minus10 voiceBtn" @click="v_minus10">-10</span>
            <span class="slider-plus10 voiceBtn" @click="v_plus10">+10</span>
        </div>    
      </div> <!-- slideWrapper -->
      <div class="alert_wrapper">
        <ui-alert @dismiss="showAlert_v = false" type="warning" v-show="showAlert_v">
                음성 이용량을 선택해 주세요.
        </ui-alert>
      </div>
      <p class="title_2">데이터</p>
        <div class="slideWrapper">
          <VueSlideBar
            v-model="slider_d.value"
            :min="slider_d.min"
            :max="slider_d.max"
            :value="slider_d.value"
            :lineHeight="slider_d.lineHeight"
            :processStyle="{ backgroundColor: '#ee2b33' }"
            @callbackRange="callbackRange"
          >
            <template slot="tooltip">
  <img src="../../assets/slider_tooltip.png" />
</template>
          </VueSlideBar>
        <ul class="stepNum">
            <li class="gbd1"><span>0GB</span></li>
            <li class="gbd2"><span class="t1">5</span></li>
            <li class="gbd3"><span>10</span></li>
            <li class="gbd4"><span>15</span></li>
            <li class="gbd5"><span>20</span></li>
            <li class="gbdL"><span>25GB<small>이상</small></span></li>
        </ul>
       <div class="stepBtn">
            <span class="dataA">
                <strong>{{slider_d.value}}</strong>GB
            </span>
            <span class="slider-minus1 dataBtn" @click="d_minus1">-1</span>
            <span class="slider-plus1 dataBtn" @click="d_plus1">+1</span>
            <span class="slider-minus10 dataBtn" @click="d_minus10">-10</span>
            <span class="slider-plus10 dataBtn" @click="d_plus10">+10</span>
        </div>
      </div> <!-- slideWrapper -->
      <div class="alert_wrapper">
        <ui-alert @dismiss="showAlert_d = false" type="warning" v-show="showAlert_d">
                데이터 이용량을 선택해 주세요.
        </ui-alert>
      </div>
      <div class="next_btn_wrapper">
        <button class="next_btn" @click="go_next()">
          Next
        </button>
      </div> 

    </div><!--wrapper-->
  </div><!--body-->
</template>
<script>
import { mapMutations, mapGetters } from "vuex";
import VueSlideBar from "vue-slide-bar";
import { UiAlert } from "keen-ui";
export default {
  computed: {
    ...mapGetters(["getButtons"]),
  },
  data() {
    return {
      btns: [],
      backIdx: -1,
      nextIdx: -1,
      PageIdx: -1,
      backPageName: "",
      rangeValue: {},
      slider_v: {
        min: 0,
        max: 500,
        value: 0,
        lineHeight: 12,
      },
      slider_d: {
        min: 0,
        max: 25,
        value: 0,
        lineHeight: 12,
      },
      showAlert_d: false,
      showAlert_v: false,
    };
  },
  mounted() {
    this.btns = this.getButtons;
    for (let i = 0; i < this.btns.length; i++) {
      if (this.btns[i].caption === "Mobile") {
        this.pageIdx = i;
        // console.log("pageIdx = " + this.pageIdx);
        break;
      }
    }
    if (this.pageIdx !== 0) {
      for (let i = this.pageIdx - 1; i >= 0; i--) {
        if (this.btns[i].state === true && this.btns[i].completed === true) {
          this.backIdx = i;
          this.backPageName = this.btns[i].name;
          // console.log("previous pageIdx = " + this.backIdx);
          // console.log("previous pageName = " + this.backPageName);
          break;
        }
      }
    }
    if (this.pageIdx !== this.btns.length) {
      for (let i = this.pageIdx + 1; i < this.btns.length; i++) {
        if (this.btns[i].state === true && this.btns[i].completed === false) {
          this.nextIdx = i;
          // console.log("next pageIdx = " + this.backIdx);
          break;
        }
      }
    }
  },
  methods: {
    ...mapMutations(["setMobileInfo", "setButtonCompleted"]),
    go_back() {
      if (this.backIdx !== 0) {
        this.setButtonCompleted(this.btns[this.backIdx].caption);
      }
      this.$router.push(`/info/${this.btns[this.backIdx].caption}`);
    },
    go_next() {
      if (this.slider_v.value === 0 || this.slider_d.value === 0) {
        if (this.slider_v.value === 0) {
          this.showAlert_v = true;
        }
        if (this.slider_d.value === 0) {
          this.showAlert_d = true;
        }
        return;
      }
      // 음성/데이터 값 store.js에 저장
      this.set_mobile_info();
      // 다음페이지로 이동
      this.go_next_page();
    },
    set_mobile_info() {
      var values = {
        v_value: this.slider_v.value,
        d_value: this.slider_d.value,
      };
      this.setMobileInfo(values);
    },
    go_next_page() {
      // 해당 페이지 completed 변경
      if (this.btns[this.pageIdx].state && !this.btns[this.pageIdx].completed) {
        this.setButtonCompleted(this.btns[this.pageIdx].caption);
      }
      // 다음페이지로 이동
      if (this.nextIdx !== -1) {
        this.$router.push(`/info/${this.btns[this.nextIdx].caption}`);
      } else {
        this.$router.push(`/info/user`);
      }
    },
    v_minus1() {
      this.slider_v.value -= 1;
    },
    v_plus1() {
      this.slider_v.value += 1;
    },
    v_minus10() {
      this.slider_v.value -= 10;
    },
    v_plus10() {
      this.slider_v.value += 10;
    },
    d_minus1() {
      this.slider_d.value -= 1;
    },
    d_plus1() {
      this.slider_d.value += 1;
    },
    d_minus10() {
      this.slider_d.value -= 10;
    },
    d_plus10() {
      this.slider_d.value += 10;
    },
    callbackRange(val) {
      this.rangeValue = val;
    },
  },
  components: {
    VueSlideBar,
    UiAlert,
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap");
* {
  margin: 0px;
  padding: 0px;
}
#body {
  margin-top: 30px;
  margin-left: auto;
  margin-right: auto;
  max-width: 100%;
  /* position: relative; */
  font-family: "Noto Sans KR", sans-serif;
  letter-spacing: -0.03em;
  font-size: 27px;
  padding-bottom: 80px;
}
.wrapper {
  /* margin-top:30px; */
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  width: 600px;
}
.title_1 {
  margin-bottom: 10px;
}
.title_2 {
  margin-top: 30px;
  font-size: 20px;
}
.notice {
  display: inline-flex;
  font-size: 15px;
  color: #656565;
}
.notice a {
  padding-left: 5px;
  color: #656565;
  text-decoration: underline;
}
img {
  width: 30px;
  height: 46px;
}
.slideWrapper {
  /* margin-left: 15px; */
  width: 98%;
  margin-bottom: 10px;
}
.stepNum {
  font-size: 0.45em;
}
.gbv1 {
  display: inline-block;
  /* font-size:0.45em; */
  margin-right: 91px;
}
.gbv2 {
  display: inline-block;
  margin-right: 99px;
}
.gbv3 {
  display: inline-block;
  margin-right: 99px;
}
.gbv4 {
  display: inline-block;
  margin-right: 99px;
}
.gbv5 {
  display: inline-block;
  margin-right: 76px;
}
.gbvL {
  display: inline-block;
}
.gbvL small {
  right: 0;
  font-size: 0.95em;
  display: block;
  line-height: 0.9em;
}
.gbvL span {
  display: inline-block;
  text-align: center;
  position: relative;
  top: 1.18em;
}

/* 데이터 */
.gbd1 {
  display: inline-block;
  /* font-size:0.45em; */
  margin-right: 92px;
}
.gbd2 {
  display: inline-block;
  margin-right: 108px;
}
.gbd3 {
  display: inline-block;
  margin-right: 106px;
}
.gbd4 {
  display: inline-block;
  margin-right: 104px;
}
.gbd5 {
  display: inline-block;
  margin-right: 82px;
}
.gbdL {
  display: inline-block;
}
.gbdL small {
  right: 0;
  font-size: 0.95em;
  display: block;
  line-height: 0.9em;
}
.gbdL span {
  display: inline-block;
  text-align: center;
  position: relative;
  top: 1.18em;
}

.stepBtn {
  margin-top: 0.8em;
  display: table;
  width: 100%;
  table-layout: fixed;
  height: 40px;
  border: 1px solid #575d69;
  background: #fff;
}
.stepBtn > * {
  display: table-cell;
  text-align: center;
  vertical-align: middle;
}
.stepBtn .voiceA,
.stepBtn .dataA {
  width: 196px;
  background: #575d69;
  color: #fff;
  font-size: 16px;
}
.stepBtn .voiceBtn,
.stepBtn .dataBtn {
  font-size: 0.4em;
  border-left: 1px solid #575d69;
  cursor: pointer;
  /* font-family: 'SpoqaHanSansRegular'; */
}
p,
ul,
ol,
li,
dl,
dt,
dd {
  list-style-image: none;
  list-style-position: outside;
  list-style-type: none;
  margin: 0;
  padding: 0;
}
.back_icon_wrapper {
  position: relative;
  display: flex;
  cursor: pointer;
  font-size: 22px;
  padding-bottom: 10px;
}
.back_icon {
  width: 15px;
}
.back_icon_wrapper p {
  margin-top: 5px;
  margin-left: 6px;
}
.next_btn_wrapper {
  position: fixed;
  bottom: 10px;
  left: inherit;
  background-color: #f6f8fb;
}
.next_btn {
  /* position: fixed;
  bottom:10px;
  left:inherit; */
  margin-top: 10px;
  border: 1px solid #ee2b33;
  font-family: "Noto Sans KR", sans-serif;
  background-color: #ee2b33;
  color: white;
  border-radius: 5px;
  width: 600px;
  height: 60px;
  font-size: 20px;
  /* margin-bottom: 20px; */
}
.next_btn:hover {
  border: 3px solid #ee2b33;
  opacity: 0.8;
}
.alert_wrapper {
  margin-top: 5px;
}
</style>
