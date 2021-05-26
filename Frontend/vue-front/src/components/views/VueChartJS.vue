<template>
  <b-container class="container">
    <b-row class="columns">
      <b-col cols="3">
        <bar-chart></bar-chart>
      </b-col>
      <b-col cols="3">
        <bubble-chart></bubble-chart>
      </b-col>
      <b-col cols="3">
        <reactive :chart-data="datacollection"></reactive>
      </b-col>
      <b-col cols="6"> <chartjs-line></chartjs-line></b-col>
      <b-col cols="6"> <chartjs-bar></chartjs-bar></b-col>
      <b-col cols="6"> <chartjs-radar></chartjs-radar></b-col>
    </b-row>
  </b-container>
</template>

<script>
import BarChart from "@/components/admin/BarChart";
import BubbleChart from "@/components/admin/BubbleChart";
import Reactive from "@/components/admin/Reactive";

export default {
  name: "VueChartJS",
  components: {
    BarChart,
    BubbleChart,
    Reactive,
  },
  data() {
    return {
      // instantiating datacollection with null
      datacollection: null,
    };
  },
  created() {
    //anytime the vue instance is created, call the fillData() function.
    this.fillData();
  },
  methods: {
    fillData() {
      this.datacollection = {
        // Data for the y-axis of the chart
        labels: [
          "January",
          "February",
          "March",
          "April",
          "May",
          "June",
          "July",
          "August",
          "September",
          "October",
          "November",
          "December",
        ],
        datasets: [
          {
            label: "Data One",
            backgroundColor: "#784583",
            // Data for the x-axis of the chart
            data: [
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
              this.getRandomInt(),
            ],
          },
        ],
      };
    },
    getRandomInt() {
      // JS function to generate numbers to be used for the chart
      return Math.floor(Math.random() * (50 - 5 + 1)) + 5;
    },
  },
};
</script>
<style>
</style>