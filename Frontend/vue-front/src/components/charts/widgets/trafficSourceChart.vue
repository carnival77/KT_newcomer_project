
<script lang='js'>
import {
  Doughnut
} from 'vue-chartjs'
import axios from 'axios'
export default {
  extends: Doughnut,
  props: {
    labels: {
      type: Array,
      // default: () => ['Search Engines',  'Direct Click',  'Bookmarks Click']
    }
  },
  data () {
    return {
      gradient: null,
      gradient2: null,
      chartData: null,
      age: "20",
      family: "2",
      buttons: [
        { caption: "TV", state: true },
        { caption: "Internet", state: false },
        { caption: "Mobile", state: true },
      ],
    }
  },
  mounted () {
    this.fillData();
    
  },
  methods: {
    onClick() {

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
      // console.log("받아온 데이터 : ", obj);
      this.gradient = this.$refs.canvas.getContext('2d').createLinearGradient(50, 0, 0, 181)
    this.gradient2 = this.$refs.canvas.getContext('2d').createLinearGradient(50, 0, 0, 300)
    this.gradient3 = this.$refs.canvas.getContext('2d').createLinearGradient(50, 0, 0, 300)
    this.gradient.addColorStop(1, 'rgba(177, 148, 250, 1)')
    this.gradient2.addColorStop(0, 'rgba(6, 185, 157, 1)')
    this.gradient2.addColorStop(1, 'rgba(132, 217, 210, 1)')
    this.gradient3.addColorStop(0, 'rgba(255, 191, 150, 1)')
    this.gradient3.addColorStop(1, 'rgba(254, 112, 150, 1)')

    axios
        .post("http://localhost:8888/adminlog/submit", obj)
        .then((res) => {
          console.log(res);
          this.chart = res.data;
          var labels = [];
          var data = [];
          var rgb = [];
          this.chartData = Object.assign({});
          this.chartData["labels"] = [];
          this.chartData["datasets"] = [];
          for (let i = 0; i < this.chart.length; i++) {
            labels.push(this.chart[i].pname);
            data.push(this.chart[i].cnt);
          }
          this.chartData.labels = labels;
          this.chartData.datasets.push({
            data: data,
           backgroundColor: [
            this.gradient,
            this.gradient2,
            this.gradient3,
          ]
          });
          console.log(this.chartData)
          
    this.renderChart({
      labels: this.chartData.labels,
      datasets: this.chartData.datasets
    }, {
      responsive: true,
      animation: {
        animateScale: true,
        animateRotate: true
      },
      // legend: true
      // legend: false
      legend: {
        position: 'left'
      },
    
    })
        })
        // .catch((er) => console.error(er));
        

    }
  },
}
</script>

<style scoped lang='scss'>
.trafficSourceChart {
}
</style>