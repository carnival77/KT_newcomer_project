<template>
  <div id="setp-2" class="setp-area">
    <div class="top">
      <div class="back_icon_wrapper" @click="go_back">
        <img src="../../assets/chevron-left-solid.svg" class="back_icon" />
        <p style="font-size: 20px; margin-top: 15px; margin-left: 7px">
          {{ backPageName }}
        </p>
      </div>
      <div style="padding-bottom: 10px"></div>
      <p class="step-top-msg">
        원하시는<br />
        인터넷 속도를 선택해주세요.
      </p>
      <!-- 5G 요금제 -->
      <div class="new-range-area 5G-values" style="">
        <div class="track">
          <em
            class="bar"
            style="transform: translateX(0px); transition-duration: 0s"
          ></em>
        </div>
        <input
          type="radio"
          name="pplData"
          id="b-1"
          value="100"
          style="left: calc(20% - 10px)"
          v-model="pickedSpeed"
        />
        <label style="left: calc(20% + 0px)">100MB</label>
        <input
          type="radio"
          name="pplData"
          id="b-2"
          value="200"
          style="left: calc(40% - 10px)"
          v-model="pickedSpeed"
        />
        <label style="left: calc(40% + 0px)">200MB</label>
        <input
          type="radio"
          name="pplData"
          id="b-3"
          value="500"
          style="left: calc(60% - 10px)"
          v-model="pickedSpeed"
        />
        <label style="left: calc(60% + 0px)">500MB</label>
        <input
          type="radio"
          name="pplData"
          id="b-4"
          value="1000"
          style="left: calc(80% - 10px)"
          v-model="pickedSpeed"
        />
        <label style="left: calc(80% + 0px)">1GB</label>
      </div>
      <dl class="setp-msg 5G-values" style="">
        <dt>※ 안내</dt>
        <dd>
          <ul>
            <li v-if="pickedSpeed === '100'">
              인터넷 서핑과 같은 단순한 인터넷 활동을 하는 <br />
              2인 이하 가구 고객님께 추천
            </li>
            <li v-if="pickedSpeed === '200'">
              온라인 게임 및 WAVVE와 같은 OTT 서비스를 자주 이용하는 <br />
              3인 이하 가구 고객님께 추천
            </li>
            <li v-if="pickedSpeed === '500'">
              온라인 게임 및 WAVVE와 같은 OTT 서비스를 자주 이용하는 <br />
              4인 이상 가구 고객님께 추천
            </li>
            <li v-if="pickedSpeed === '1000'">
              대용량 파일을 자주 업로드 하거나 트래픽이 많이 발생하는 <br />
              인터넷 활동을 자주 하는 5인 이상 가구 고객님께 추천
            </li>
            <li>
              sky WiFi(월 1,100원) 무료 제공 <br />
              (3년약정 기준, 부가세 포함)
            </li>
          </ul>
        </dd>
      </dl>
      <!-- 
      <dl class="setp-msg 5G-values" style="">
        <dt>※ 안내</dt>
        <dd>
          <ul>
            <li>
              평균적으로 초고화질(4K) 영상 1시간 보는데 소요되는<br />
              데이터는 약 9GB정도 입니다.
            </li>
            <li>
              초고화질 영상, 게임 다운로드 및 업로드 활동을<br />
              많이 하신다면 1GB를 추천합니다.
            </li>
          </ul>
        </dd>
      </dl> -->
      <div class="next_btn_wrapper">
        <button class="next_btn" @click="go_next()">Next</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapGetters } from "vuex";
export default {
  data() {
    return {
      rows: 100,
      currentPage: 2,
      btns: [],
      backIdx: -1,
      nextIdx: -1,
      PageIdx: -1,

      backPageName: "",
      pickedSpeed: "100",
    };
  },
  computed: {
    ...mapGetters(["getButtons"]),
  },
  mounted() {
    this.btns = this.getButtons;

    for (let i = 0; i < this.btns.length; i++) {
      if (this.btns[i].caption === "Internet") {
        this.pageIdx = i;
        break;
      }
    }
    if (this.pageIdx !== 0) {
      for (let i = this.pageIdx - 1; i >= 0; i--) {
        // console.log(i,this.btns[i].name);
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
    ...mapMutations(["setButtonCompleted", "setInternetInfo"]),
    go_back() {
      if (this.backIdx !== 0) {
        this.setButtonCompleted(this.btns[this.backIdx].caption);
      }
      this.$router.push(`/info/${this.btns[this.backIdx].caption}`);
    },
    go_next() {
      // speed 저장
      this.set_Internet_info();
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
    set_Internet_info() {
      // console.log(this.pickedSpeed);
      this.setInternetInfo(this.pickedSpeed);
    },
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap");
#setp-2 {
  font-family: "Noto Sans KR", sans-serif;
  display: flex;
  flex-direction: column;
  margin: 0 auto;
}
.setp-area {
  display: none;
  height: calc(100% - 127px);
}
.setp-area .content {
  min-height: 100%;
}
.step-top-msg {
  position: relative;
  /* padding-top: 20px; */
  margin: 0 0 50px 0;
  font-size: 27px;
  line-height: 1.56;
  letter-spacing: -1.08px;
}
.new-range-area {
  position: relative;
  height: 52px;
  margin: 60px 0 0 0;
}
.track {
  position: relative;
  width: 100%;
  height: 20px;
  border-radius: 3px;
  outline: none;
}
.track::before {
  content: "";
  position: absolute;
  top: 50%;
  margin-top: -3px;
  width: 100%;
  height: 6px;
  border-radius: 3px;
  background-color: #ccc;
  outline: none;
}
.track em.bar {
  position: absolute;
  top: 0;
  left: 0;
  width: 20px;
  height: 20px;
  /* background: #00a2be; */
  border-radius: 50%;
  cursor: pointer;
  z-index: 20;
  -webkit-transition: transform 0.1s ease;
  -ms-transition: transform 0.1s ease;
  transition: transform 0.1s ease;
}
.new-range-area input[type="radio"] {
  /* -webkit-appearance: none; */
  /* appearance: none; */
  position: absolute;
  top: 0;
  width: 20px;
  z-index: 10;
  height: 20px;
  border: 1px solid #ccc;
  /* overflow: hidden; */
  border-radius: 10px;
  background-color: #fff;
}
.new-range-area label {
  position: absolute;
  top: 32px;
  text-align: center;
  transform: translateX(-50%);
  font-size: 14px;
  font-weight: 500;
  color: #666;
  min-width: 90px;
}
.setp-msg {
  margin-top: 40px;
  color: #666;
  font-size: 13px;
}
.setp-msg dt {
  margin-bottom: 15px;
  font-weight: 500;
  font-size: 14px;
}
.setp-msg dd ul li {
  position: relative;
  padding-left: 15px;
  margin-bottom: 10px;
  line-height: 20px;
}
.setp-msg dd ul li:before {
  content: "";
  position: absolute;
  top: 8px;
  left: 0;
  width: 4px;
  height: 4px;
  background-color: #666;
}

.setp-msg dd ul li::marker {
  content: "";
}

#sept-2 {
  width: 100%;
  height: 100%;
}

.top {
  width: 600px;
  /* height: 500px; */
  margin: 0 auto;
  margin-top: 30px;
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
.back_icon_wrapper.backback {
  margin-top: 13px;
  margin-left: 6px;
}
</style>