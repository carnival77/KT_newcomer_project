<template>
  <div id="recommand">
    <!-- <HomeHeader></HomeHeader> -->
    <div class="wrapper">
      <div class="back_icon_wrapper" @click="go_back">
        <img src="../../assets/chevron-left-solid.svg" class="back_icon" />
        <p style="font-size: 20px">HOME</p>
      </div>
      <div style="padding-bottom: 20px"></div>
      <span>반갑습니다. <br />어떤 요금제를 추천해 드릴까요?<br /> </span>
      <span class="overlap"> * 중복 선택 가능</span>
      <div style="padding-bottom: 10px"></div>
      <div style="padding-bottom: 10px"></div>
      <div>
        <button
          v-for="(btn, idx) in buttons"
          :key="idx"
          class="feature_btn"
          v-bind:class="{ active: btn.state }"
          @click="setButtonState(btn.caption)"
          v-show="btn.caption !== `` && btn.caption !== `User`"
        >
          {{ btn.caption }}
        </button>
      </div>
      <div class="next_btn_wrapper">
        <button class="next_btn" @click="go_next()">
          조건 선택하고 요금제 추천받기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapGetters } from "vuex";
// import HomeHeader from "./Home_Header";
// import Admin from './Admin.vue';
export default {
  // components: {
  //   HomeHeader,
  // },
  // components: { Admin },
  computed: { ...mapState(["buttons"]), ...mapGetters(["getButtons"]) },
  methods: {
    ...mapMutations(["setButtonState"]),
    go_back() {
      this.$router.push(`/`);
    },
    go_next() {
      const btns = this.getButtons;
      // console.log(btns);
      // let flag = false;
      for (let i = 1; i < btns.length; i++) {
        if (btns[i].state && !btns[i].completed) {
          // console.log(btns[i].caption, btns[i].state, btns[i].completed);
          this.$router.push(`/info/${btns[i].caption}`);
          // flag = true;
          break;
        }
      }

      // console.log(flag);
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap");
.back_icon_wrapper {
  position: relative;
  display: flex;
  cursor: pointer;
  font-size: 22px;
  padding-bottom: 10px;
}
.back_icon {
  padding-top: 2px;
  width: 15px;
}
.back_icon_wrapper p {
  margin-top: 0;
  margin-left: 6px;
}
span {
  letter-spacing: -0.03em;
  margin: 0 0 0 0;
}
* {
  margin: 0px;
  padding: 0px;
}
html,
body {
  height: 100%;
  background-color: #f6f8fb !important;
}
.overlap {
  font-size: 20px;
  margin-bottom: 20px;
  color: #656565;
}
#recommand {
  margin: 30px auto 0 auto;
  max-width: 100%;
  height: 98%;
  font-family: "Noto Sans KR", sans-serif;
  font-size: 27px;
  /* margin-top:30px; */
  padding-bottom: 80px;
}
.wrapper {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  width: 600px;
}
.feature_btn:hover {
  border: 3px solid #ee2b33;
  opacity: 0.8;
}
.feature_btn {
  border: 1px solid #ee2b33;
  font-family: "Noto Sans KR", sans-serif;
  background-color: white;
  color: #ee2b33;
  border-radius: 5px;
  width: 100%;
  height: 60px;
  font-size: 20px;
  margin-bottom: 20px;
}
.next_btn_wrapper {
  position: fixed;
  bottom: 10px;
  left: inherit;
  background-color: #f6f8fb;
}
.next_btn {
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
.active {
  border: 3px solid #ee2b33;
}
</style>