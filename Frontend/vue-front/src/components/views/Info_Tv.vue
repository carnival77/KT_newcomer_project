<template>
  <div id="recommand">
    <div class="wrapper">
      <div class="back_icon_wrapper" @click="go_back">
        <img src="../../assets/chevron-left-solid.svg" class="back_icon" />
        <p style="font-size: 20px">요금제 선택</p>
      </div>
      <div style="padding-bottom: 10px"></div>
      <span
        >원하는 부가서비스나 <br />
        기능을 선택해주세요.</span
      >
      <span class="overlap"> * 중복 선택 가능</span>
      <div class="list_cover">
        <ul>
          <li
            class="li_cursor"
            v-for="(info, idx) in tv_info"
            :key="idx"
            @click="setTvInfo(info.title)"
          >
            <div>
              <label for="recomm-0804-choice-1">
                <span class="t">{{ info.title }}</span>
                <span class="c">{{ info.content }}</span>
              </label>
              <input
                type="checkbox"
                :class="{ checkbox_1: !info.checked, checkbox_2: info.checked }"
              />
            </div>
          </li>
        </ul>
      </div>
      <div class="next_btn_wrapper">
        <button class="next_btn" @click="go_next()">Next</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapGetters } from "vuex";
// import Admin from './Admin.vue';
export default {
  computed: { ...mapState(["tv_info"]), ...mapGetters(["getButtons"]) },
  methods: {
    ...mapMutations([
      "setButtonState",
      "setTvInfo",
      "submitBack",
      "setButtonCompleted",
    ]),
    go_back() {
      this.$router.push(`/info`);
    },
    go_next() {
      // console.log(this.tv_info);
      const btns = this.getButtons;
      let flag = false;
      for (let i = 0; i < btns.length; i++) {
        if (btns[i].state && !btns[i].completed) {
          if (btns[i].caption === "TV") {
            this.setButtonCompleted(btns[i].caption);
            continue;
          }
          this.$router.push(`/info/${btns[i].caption}`);
          flag = true;
          break;
        }
      }
      if (flag === false) {
        // this.submitBack();
        this.$router.push("/info/user");
      }
    },
    checkAction() {
      console.log(123);
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap");
.list_cover {
  margin-bottom: 10px;
  padding: 10px 10px;
}
.li_cursor:hover {
  opacity: 0.7;
  transform: scale(1.03);
}
.next {
  border: 1px solid #ee2b33;
  text-align: end;
}
.t-cover {
  display: flex;
  justify-content: space-between;
}
.checkbox_1 {
  -webkit-appearance: none;
  appearance: none;
  width: 30px;
  height: 30px;
  border-radius: 15px;
  position: absolute;
  justify-content: right;
  box-sizing: border-box;
  background: #d6d6d6 url("../../assets/icon_check_off.png") center center
    no-repeat;
  background-size: 30px;
  right: 5%;
}
.checkbox_2 {
  -webkit-appearance: none;
  appearance: none;
  width: 30px;
  height: 30px;
  border-radius: 15px;
  position: absolute;
  justify-content: right;
  box-sizing: border-box;
  background: #d6d6d6 url("../../assets/icon_check_on2.png") center center
    no-repeat;
  background-size: 30px;
  right: 5%;
}
.c {
  font-size: 13px;
  color: #959595;
  line-height: 19px;
}
.t {
  margin-bottom: 12px;
  font-weight: 500;
  display: block;
  font-size: 15px;
}
li {
  list-style: none;
  background-color: #fff;
  padding: 18px 20px;
  margin-top: 8px;
  position: relative;
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
  /* height: 98%; */
  font-family: "Noto Sans KR", sans-serif;
  letter-spacing: -0.03em;
  font-size: 27px;
  padding-bottom: 70px;
  /* margin-top: 30px; */
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
.active {
  border: 3px solid #ee2b33;
}
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
</style>