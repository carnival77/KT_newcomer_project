<template>
  <div id="recommand">
    <div class="wrapper">
      <div class="back_icon_wrapper" @click="go_back()">
        <img src="../../assets/chevron-left-solid.svg" class="back_icon" />
        <p style="font-size: 20px">{{ backPageName }}</p>
      </div>
      <div style="padding-bottom: 30px"></div>
      <span
        >고객님의 <br />
        정보를 입력해주세요.
      </span>

      <b-form-select
        v-model="age"
        :options="ages"
        class="mt-5"
        size="lg"
      ></b-form-select>

      <div class="alert_wrapper">
        <ui-alert
          @dismiss="showAlert_age = false"
          type="warning"
          v-show="showAlert_age"
        >
          연령대를 선택해주세요.
        </ui-alert>
      </div>

      <b-form-select
        v-model="f_member"
        :options="f_members"
        class="mt-5"
        size="lg"
      ></b-form-select>

      <div class="alert_wrapper">
        <ui-alert
          @dismiss="showAlert_fmember = false"
          type="warning"
          v-show="showAlert_fmember"
        >
          가족 구성원을 선택해주세요.
        </ui-alert>
      </div>
      <div class="next_btn_wrapper">
        <button class="next_btn" @click="go_next()">Next</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapGetters } from "vuex";
import axios from "axios";
import { UiAlert } from "keen-ui";
// import VueAxios from "vue-axios";

export default {
  data() {
    return {
      btns: [],
      backIdx: -1,
      backPageName: "",
      PageIdx: -1,
      age: null,
      ages: [
        { value: null, text: "연령대를 선택해주세요." },
        { value: 20, text: "20대 이하" },
        { value: 30, text: "30대" },
        { value: 40, text: "40대" },
        { value: 50, text: "50대" },
        { value: 60, text: "60대 이상" },
      ],

      f_member: null,
      f_members: [
        { value: null, text: "가족 구성원을 선택해주세요." },
        { value: 1, text: "1인 가족" },
        { value: 2, text: "2인 가족" },
        { value: 3, text: "3인 가족" },
        { value: 4, text: "4인 가족" },
        { value: 5, text: "5인 이상 가족" },
      ],
      showAlert_age: false,
      showAlert_fmember: false,
    };
  },
  mounted() {
    this.btns = this.getButtons;
    for (let i = 0; i < this.btns.length; i++) {
      if (this.btns[i].caption === "User") {
        this.pageIdx = i;
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
  },
  computed: {
    ...mapGetters(["getStates", "getButtons", "getHost"]),
  },
  methods: {
    ...mapMutations([
      "setUserInfo",
      "setButtonCompleted",
      "setBackData",
      "setInit",
      "setResultData",
      "setWeightResultData",
    ]),
    go_back() {
      if (this.backIdx !== -1) {
        this.setButtonCompleted(this.btns[this.backIdx].caption);
        this.$router.push(`/info/${this.btns[this.backIdx].caption}`);
      }
    },
    async go_next() {
      if (this.age === null || this.f_member === null) {
        if (this.age === null) {
          this.showAlert_age = true;
        }
        if (this.f_member === null) {
          this.showAlert_fmember = true;
        }
        return;
      }

      const info = { age: this.age, f_member: this.f_member };
      this.setUserInfo(info);

      const state = this.getStates;
      // console.log(state);
      var tv_flag = false;

      let items = [];
      if (state.buttons[1].state) tv_flag = true;

      let tv_ser = [];
      for (let i = 0; i < state.tv_info.length; i++) {
        if (state.tv_info[i].checked) tv_ser.push(state.tv_info[i].backdata);
      }

      let submit_info = {
        age: state.user_info.age,
        family: state.user_info.f_member,
        tv_due: tv_flag === true ? 3 : 0,
        tv_ser: tv_ser,
        itn_speed: Number(state.internet_info.speed),
        mob_data: state.mobile_info.d_value,
        mob_voice: state.mobile_info.v_value,
      };

      console.log(submit_info);

      var api_url = this.getHost;
      // let api_url = "";
      axios
        .post(api_url + "service/submit", submit_info)
        .then((res) => res.data)
        .then((data) => this.setBackData(data))
        .then((res1) => this.setResultData())
        .then((res2) => this.setWeightResultData())
        .then((res3) => this.setInit())
        .then((res4) => this.$router.push("/result"))
        .catch((er) => console.error(er));
      // this.setInit();
      // console.log(this.getStates);
    },
    checkAction() {
      console.log(123);
    },
  },
  components: {
    UiAlert,
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap");
.custom-select-lg {
  font-size: 1rem;
}
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
  height: 98%;
  font-family: "Noto Sans KR", sans-serif;
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
  width: 15px;
}
.back_icon_wrapper p {
  margin-top: 0;
  margin-left: 6px;
}
.alert_wrapper {
  margin-top: 10px;
}
</style>