import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import VueAxios from "vue-axios";

Vue.use(Vuex);
Vue.use(VueAxios, axios);
// let api_url = "";

export const store = new Vuex.Store({
  state: {
    monthData: {},
    labels: [],
    host: "http://14.63.222.111:8084/",
    // host: "http://localhost:8888/",
    id: "",
    buttons: [
      { caption: "", name: "요금제 선택", state: true, completed: true },
      {
        caption: "TV",
        name: "TV 요금제 선택",
        state: false,
        completed: false,
      },
      {
        caption: "Internet",
        name: "인터넷 요금제 선택",
        state: false,
        completed: false,
      },
      {
        caption: "Mobile",
        name: "모바일 요금제 선택",
        state: false,
        completed: false,
      },
      {
        caption: "User",
        name: "유저 정보 입력",
        state: true,
        completed: true,
      },
    ],
    tv_info: [
      {
        title: "해당 없음",
        content: "부가서비스나 기능 없이 추천 받기",
        checked: true,
        backdata: "none",
      },
      {
        title: "실시간 스포츠 중계 시청",
        content: "SPOTV ON 실시간 스포츠 중계 채널을 시청 가능",
        checked: false,
        backdata: "spotv",
      },
      {
        title: "어린이 채널",
        content: "캐치온 / 키즈 톡톡+ 어린이 채널을 시청 가능",
        checked: false,
        backdata: "키즈",
      },
      {
        title: "왓챠 & wavve",
        content:
          "왓챠, WAVVE 원하는 OTT 서비스를 이용하여 영화, 드라마, 예능 다양하게 시청 가능",
        checked: false,
        backdata: "wavve",
      },
      {
        title: "VOD",
        content: "매월 제공되는 VOD 포인트를 통해 다양한 VOD 시청 가능",
        checked: false,
        backdata: "vod",
      },
    ],
    internet_info: {
      speed: 0,
    },
    mobile_info: {
      v_value: -1,
      d_value: -1,
    },
    user_info: {
      age: null,
      f_member: null,
    },
    back_data: [],
    result_data: {},
    result_weight_data: {},
    price_order_data: [],
    weight_order_data: [],
    order_data: {},
  },
  getters: {
    getStates(state) {
      return state;
    },
    getHost(state) {
      return state.host;
    },
    getButtons(state) {
      return state.buttons;
    },
    getTvInfo(state) {
      return state.tv_info;
    },
    getId(state) {
      return state.id;
    },
    getBackData(state) {
      return state.back_data;
    },
    getResultWeightData(state) {
      return state.result_weight_data;
    },
    getResultData(state) {
      return state.result_data;
    },
    getPriceOrderData(state) {
      return state.price_order_data;
    },
    getWeightOrderData(state) {
      return state.weight_order_data;
    },
    getOrderData(state) {
      return state.order_data;
    },
    getReco(state) {
      return state.chartData;
    },
  },
  mutations: {
    setBackData(state, backData) {
      state.back_data = backData;
      console.log("back_Data", backData);

      state.price_order_data = backData.priceResult;
      state.weight_order_data = backData.weightResult;
      console.log("price_order_data", state.price_order_data);
      console.log("weight_order_data", state.weight_order_data);

      state.price_order_data.forEach((el) => {
        el.age = state.user_info.age;
        el.family = state.user_info.f_member;
      });

      state.weight_order_data.forEach((el) => {
        el.age = state.user_info.age;
        el.family = state.user_info.f_member;
      });
      // console.log("setBack", state.back_data);
      //this.setResultData(state);
    },
    setButtonState(state, caption) {
      const btn_state = state.buttons.find((el) => {
        if (el.caption === caption) return el;
      }).state;

      state.buttons.find((el) => {
        if (el.caption === caption) return el;
      }).state = !btn_state;
    },
    setButtonCompleted(state, caption) {
      const btn_completed = state.buttons.find((el) => {
        if (el.caption === caption) return el;
      }).completed;

      state.buttons.find((el) => {
        if (el.caption === caption) return el;
      }).completed = !btn_completed;
      // console.log(!btn_completed);
    },
    setTvInfo(state, title) {
      if (title !== "해당 없음") {
        if (state.tv_info[0].checked) state.tv_info[0].checked = false;
      }
      const info_state = state.tv_info.find((el) => {
        if (el.title === title) return el;
      }).checked;

      state.tv_info.find((el) => {
        if (el.title === title) return el;
      }).checked = !info_state;

      if (title === "해당 없음") {
        state.tv_info.forEach((el) => {
          if (el.title !== "해당 없음") el.checked = false;
        });
      } else {
        let flag = false;
        state.tv_info.forEach((el) => {
          if (el.checked) {
            flag = true;
          }
        });

        if (!flag) state.tv_info[0].checked = true;
      }
    },
    setInternetInfo(state, speed) {
      state.internet_info.speed = speed;
    },
    setMobileInfo(state, values) {
      state.mobile_info.v_value = values.v_value;
      state.mobile_info.d_value = values.d_value;
    },
    setUserInfo(state, info) {
      state.user_info.age = info.age;
      state.user_info.f_member = info.f_member;
      // console.log(info);
    },
    submitBack(state) {
      console.log(state);
    },
    setResultData(state) {
      // console.log("back_data : ", state.back_data);

      state.price_order_data.forEach((el) => {
        console.log("el", el.combine);
      });

      let singleItems = [];
      let combineItems = [];
      let categoryFlag = [false, false];

      let index = 0;

      state.price_order_data.forEach((el) => {
        let comFlag = el.combine;

        let itemFlag = [];
        let length = 0;

        let tv = {};
        let internet = {};
        let mobile = {};

        for (let i = 0; i < 3; i++) {
          if (i === 0) {
            if (el.tvId !== 0 || el.tvName !== "") {
              itemFlag.push(true);
              length++;
              tv = {
                Id: el.tvId,
                Name: el.tvName,
                Price: el.tvPrice,
                Service: el.tvService,
                Quality: el.tvQuality,
                Due: el.tvDue,
                Channel: el.tvChannel,
              };
            } else itemFlag.push(false);
          } else if (i == 1) {
            if (el.itnId !== 0 || el.itnName !== "") {
              itemFlag.push(true);
              length++;
              internet = {
                Id: el.itnId,
                Name: el.itnName,
                Price: el.itnPrice,
                Service: el.itnService,
                Speed: el.itnSpeed,
              };
            } else itemFlag.push(false);
          } else {
            if (el.mobId !== 0 || el.mobName !== "") {
              itemFlag.push(true);
              length++;
              mobile = {
                Id: el.mobId,
                Name: el.mobName,
                Price: el.mobPrice,
                Service: el.mobService,
                Data: el.mobData,
                Message: el.mobMessage,
                Voice: el.mobVoice,
              };
            } else itemFlag.push(false);
          }
        }

        let singleItem = {
          itemFlag: itemFlag,
          length: length,
          productName: el.pname,
          tv: tv,
          internet: internet,
          mobile: mobile,
        };

        if (comFlag) {
          let combineItem = {
            total_service: el.total_service,
            prevPrice: el.prevPrice,
            totalPrice: el.totalPrice,
            productName: el.pname,
            item: singleItem,
            category: [false, false, false, true],
          };
          combineItem.index = index++;
          combineItems.push(combineItem);
        } else {
          singleItem.index = index++;
          singleItems.push(singleItem);
        }
      });
      if (singleItems.length !== 0) {
        categoryFlag[0] = true;
      }
      if (combineItems.length !== 0) {
        categoryFlag[1] = true;
      }
      state.result_data = {
        singleItems,
        combineItems,
        categoryFlag,
      };

      console.log("resultData", state.result_data);
    },
    setOrderData(state, item) {
      state.order_data = item;
      console.log("orderData", state.order_data);
    },
    setInit(state) {
      for (let i = 0; i < state.buttons.length; i++) {
        state.buttons[i].state = state.buttons[i].completed = false;
      }
      for (let i = 0; i < state.tv_info.length; i++) {
        state.tv_info[i].checked = false;
      }
      state.tv_info[0].checked = true;

      state.internet_info.speed = 0;
      (state.mobile_info.v_value = -1), (state.mobile_info.d_value = -1);
      (state.user_info.age = null), (state.user_info.f_member = null);
    },
    setWeightResultData(state) {
      console.log("resultweightData");

      let singleItems = [];
      let combineItems = [];
      let categoryFlag = [false, false];

      let index = 0;

      state.weight_order_data.forEach((el) => {
        let comFlag = el.combine;

        let itemFlag = [];
        let length = 0;

        let tv = {};
        let internet = {};
        let mobile = {};

        for (let i = 0; i < 3; i++) {
          if (i === 0) {
            if (el.tvId !== 0 || el.tvName !== "") {
              itemFlag.push(true);
              length++;
              tv = {
                Id: el.tvId,
                Name: el.tvName,
                Price: el.tvPrice,
                Service: el.tvService,
                Quality: el.tvQuality,
                Due: el.tvDue,
                Channel: el.tvChannel,
              };
            } else itemFlag.push(false);
          } else if (i == 1) {
            if (el.itnId !== 0 || el.itnName !== "") {
              itemFlag.push(true);
              length++;
              internet = {
                Id: el.itnId,
                Name: el.itnName,
                Price: el.itnPrice,
                Service: el.itnService,
                Speed: el.itnSpeed,
              };
            } else itemFlag.push(false);
          } else {
            if (el.mobId !== 0 || el.mobName !== "") {
              itemFlag.push(true);
              length++;
              mobile = {
                Id: el.mobId,
                Name: el.mobName,
                Price: el.mobPrice,
                Service: el.mobService,
                Data: el.mobData,
                Message: el.mobMessage,
                Voice: el.mobVoice,
              };
            } else itemFlag.push(false);
          }
        }

        let singleItem = {
          itemFlag: itemFlag,
          length: length,
          productName: el.pname,
          tv: tv,
          internet: internet,
          mobile: mobile,
        };

        if (comFlag) {
          let combineItem = {
            total_service: el.total_service,
            prevPrice: el.prevPrice,
            totalPrice: el.totalPrice,
            productName: el.pname,
            item: singleItem,
            category: [false, false, false, true],
          };
          combineItem.index = index++;
          combineItems.push(combineItem);
        } else {
          singleItem.index = index++;
          singleItems.push(singleItem);
        }
      });
      if (singleItems.length !== 0) {
        categoryFlag[0] = true;
      }
      if (combineItems.length !== 0) {
        categoryFlag[1] = true;
      }
      state.result_weight_data = {
        singleItems,
        combineItems,
        categoryFlag,
      };

      console.log("resultWeightData", state.result_weight_data);
    },
    // setReco(state, payload) {
    //   for(let i = 0; i < payload.length; i++){
    //     state.chartData.labels.push(payload[i].pname)
    //     state.chartData.datasets[0].data.push(payload[i].cnt)
    //   }
    //   console.log(state.chartData)
    // },
    loginId(state, payload) {
      state.id = payload;
    },
    logout(state) {
      if (state.id) {
        state.id = null;
        console.log("hi");
        alert("로그아웃 되었습니다.");
      }
    },
  },
  actions: {},
});
