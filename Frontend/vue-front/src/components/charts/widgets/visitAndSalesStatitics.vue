<script lang="js">
import {
  Bar
} from 'vue-chartjs'
export default {
  extends: Bar,
  props: {
    labels: {
      
      type: Array,
      default: () =>  ['2020.09', '2020.10', '2020.11', '2020.12','2021.01', '2021.02'],

  }},
  data () {
    return {
      gradient: null,
      gradient2: null,
      month: []
    }
  },
  mounted () {
    this.gradient = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 181)
    this.gradient2 = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 360)
    this.gradient3 = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 300)
    // this.gradient.addColorStop(0, 'rgba(218, 140, 255, 1)')
    this.gradient.addColorStop(1, 'rgba(154, 85, 255, 1)')
    // this.gradient2.addColorStop(0, 'rgba(255, 191, 150, 1)')
    this.gradient2.addColorStop(1, 'rgba(254, 112, 150, 1)')
    // this.gradient3.addColorStop(0, 'rgba(54, 215, 232, 1)')
    this.gradient3.addColorStop(1, 'rgba(108, 153, 250, 1)')
    var obj = {};
    obj.age = this.age;
    obj.family = this.family;
    this.$Axios
      .post(`${this.$store.state.host}/adminlog/submit`, obj)
      .then((res) => {
        this.month = res.data.monthData.month
        return res.data.monthData;
      })
      .then((monthData) => {
        this.renderChart({
      labels: this.labels,
      datasets: [{
        label: 'Mobile',
        data: [monthData.thrMonthMobCnt[5], monthData.thrMonthMobCnt[4], monthData.thrMonthMobCnt[3],monthData.thrMonthMobCnt[2], monthData.thrMonthMobCnt[1], monthData.thrMonthMobCnt[0]],
        borderColor: this.gradient,
        backgroundColor: this.gradient,
        borderWidth: 1,
        categoryPercentage: 0.5,
        barPercentage: 0.5
      }, {
        label: 'TV',
        data: [monthData.thrMonthTvCnt[5], monthData.thrMonthTvCnt[4], monthData.thrMonthTvCnt[3],monthData.thrMonthTvCnt[2], monthData.thrMonthTvCnt[1], monthData.thrMonthTvCnt[0]],
        // borderColor: this.gradient2,
        backgroundColor: this.gradient2,
        borderWidth: 1,
        categoryPercentage: 0.5,
        barPercentage: 0.5
      }, {
        label: 'Internet',
        data: [monthData.thrMonthItnCnt[5],monthData.thrMonthItnCnt[4],monthData.thrMonthItnCnt[3],monthData.thrMonthItnCnt[2],monthData.thrMonthItnCnt[1], monthData.thrMonthItnCnt[0]],
        // borderColor: this.gradient3,
        backgroundColor: this.gradient3,
        borderWidth: 1,
        categoryPercentage: 0.5,
        barPercentage: 0.5
      }]
    }, {
      responsive: true,
      elements: {
        point: {
          radius: 0
        }
      },
      legend: false,
      scales: {
        xAxes: [{
          ticks: {
            padding: 20,
            fontColor: "#9c9fa6",
            autoSkip: true,
          },
          gridLines: {
            display:false,
            drawBorder: false,
            color: 'rgba(0,0,0,1)',
            zeroLineColor: 'rgba(235,237,242,1)'
          },
        }],
        yAxes: [{
          ticks: {
            display: true,
            min: 0,
            stepSize: 10,
            max: 150
          },
          gridLines: {
            drawBorder: false,
            color: 'rgba(235,237,242,1)',
            zeroLineColor: 'rgba(235,237,242,1)'
          }
        }]
      }
    })
      })
  }
}
</script>

<style scoped lang="scss">
</style>