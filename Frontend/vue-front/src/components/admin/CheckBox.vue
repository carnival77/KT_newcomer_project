<template>
  <div style="justify-content: space-around">
    <div id="pie">
      <pie-chart
        :chartData="chartData"
        :options="chartOptions"
        style="width: 500px; height: 300px"
      ></pie-chart>

      <div class="check" style="display: flex">
        <!-- <b-form-select label="연령" v-slot="{ ariaDescribedby }"> -->
        <div align="left">
          <div style="font-family: Nanum Gothic, sans-serif; margin-top: 50px">
            연령
          </div>
          <b-form-select
            v-model="age"
            :options="options"
            name="radios-stacked"
            label="연령"
            style="
              font-size: 14px;
              margin-top: 5px;
              width: 200px;
              font-family: Nanum Gothic, sans-serif;
              font-weight: bold;
            "
          ></b-form-select>
          <!-- </b-form-select> -->

          <br /><br />
          <div></div>
          <div style="font-family: Nanum Gothic, sans-serif">가구 유형</div>
          <b-form-select
            v-model="family"
            :options="options2"
            label-field="가구 유형"
            style="
              margin-top: 5px;
              font-size: 14px;
              width: 200px;
              font-family: Nanum Gothic, sans-serif;
              font-weight: bold;
            "
          ></b-form-select>
        </div>
        <div class="btn-domain" style="margin-left: 40px; padding-top: 80px">
          <div style="display: flex">
            <button
              v-for="(btn, idx) in buttons"
              :key="idx"
              v-bind:class="[{ active: btn.state }, { hover: !btn.state }]"
              @click="setButtonState(btn.caption)"
              style="border-radius: 5px; margin-left: 3px; line-height: 10px"
            >
              {{ btn.caption }}
            </button>
          </div>
          <button
            @click="fillData()"
            style="
              width: 100%;
              border-radius: 100px;
              margin-top: 44px;
              font-weight: bold;
              backgrouhnd: #0069d9;
              font-family: Nanum Gothic, sans-serif;
              font-weight: bold;
              line-height: 10px;
            "
            class="search"
          >
            조회
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PieChart from "./PieChart.js";
export default {
  components: {
    PieChart,
  },
  data() {
    return {
      chart: [],

      myToggle: false,
      age: "20",
      family: "2",
      buttons: [
        { caption: "TV", state: true },
        { caption: "Internet", state: false },
        { caption: "Mobile", state: true },
      ],
      options: [
        { text: "20대 이하", value: "20" },
        { text: "30대", value: "30" },
        { text: "40대", value: "40" },
        { text: "50대", value: "50" },
        { text: "60대 이상", value: "60" },
      ],
      options2: [
        { text: "2인 가구", value: "2" },
        { text: "3인 가구", value: "3" },
        { text: "4인 가구", value: "4" },
        { text: "5인 이상", value: "5" },
      ],
      chartOptions: {
        hoverBorderWidth: 40,
        legend: {
          display: true,
          position: "left",
        },
        responsive: true,
        maintainAspectRatio: false,
      },
      chartData: null,
    };
  },
  computed: {
    state() {
      return this.value.length === 2;
    },
    btnStates() {
      return this.buttons.map((btn) => btn.state);
    },
  },
  mounted() {
    this.fillData();
  },
  methods: {
    setButtonState(caption) {
      const btn_state = this.buttons.find((el) => {
        if (el.caption === caption) return el;
      }).state;

      this.buttons.find((el) => {
        if (el.caption === caption) return el;
      }).state = !btn_state;
    },
    getRandomColor() {
      var letters = "56789ABCDEF";
      var color = "#";
      for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 11)];
      }
      return color;
    },
    fillData() {
      var obj = {};
      obj.age = this.age;
      obj.family = this.family;
      for (let i = 0; i < this.buttons.length; i++) {
        var element = this.buttons[i];
        if (element.caption === "TV") {
          obj.tv_select = element.state;
        } else if (element.caption === "Internet") {
          obj.itn_select = element.state;
        } else if (element.caption === "Mobile") {
          obj.mob_select = element.state;
        }
      }
      this.$Axios
        .post(`${this.$store.state.host}/adminlog/submit`, obj)
        .then((res) => {
          this.$store.state.monthData = res.data.monthData;
          this.chart = res.data.totalData;
          var labels = [];
          var data = [];
          var rgb = [];
          this.chartData = Object.assign({});
          this.chartData["labels"] = [];
          this.chartData["datasets"] = [];

          for (let i = 0; i < this.chart.length; i++) {
            labels.push(this.chart[i].pname);
            data.push(this.chart[i].cnt);
            rgb.push(this.getRandomColor());
          }

          this.chartData.labels = labels;
          this.chartData.datasets.push({
            data: data,
            backgroundColor: rgb,
          });
        })
        .catch((er) => console.error(er));
    },
  },
};
</script>
<style>
.hover {
  background: dodgerblue;
}
.active {
  background: rgb(3, 51, 100);
  /* border: 2px solid grey; */
  opacity: 0.8;
  -webkit-transform: translate(0, 5px);
}
.search {
  background-color: dodgerblue;
}
.check {
  font-size: 15px;
  font-weight: bold;
  /* margin-top: 20px; */
  /* position: right; */
}
.check1 {
  border: 1px solid #111111;
}

.check2 {
  border: 1px solid #111111;
}
#pie {
  font-family: "Nanum Gothic, sans-serif";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.btn-domain {
  /* margin-top: 40px; */
  /* width: 70px; */
  /* height: 50px; */
}
</style>