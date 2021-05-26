<template>
  <section class="dashboard" style="">
    <div class="page-header">
      <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white mr-2">
          <i class="mdi mdi-home"></i>
        </span>
        Dashboard
      </h3>
      <nav aria-label="breadcrumb">
        <ul class="breadcrumb"></ul>
      </nav>
    </div>
    <div class="row">
      <div class="col-md-4 stretch-card grid-margin">
        <div class="card bg-gradient-danger card-img-holder text-white">
          <div class="card-body">
            <img
              src="../../assets/images/dashboard/circle.svg"
              class="card-img-absolute"
              alt="circle-image"
            />
            <h4
              class="font-weight-bold mb-3"
              style="font-family: Nanum Gothic, sans-serif"
            >
              주간 판매액
              <i class="mdi mdi-chart-line mdi-24px float-right"></i>
            </h4>
            <h2
              class="mb-5"
              style="
                font-weight: bold;
                font-family: Nanum Gothic, sans-serif;
                font-size: 35px;
              "
            >
              &#8361;
              {{ this.comma(this.$store.state.monthData.weekTotalPrice) }}
            </h2>
            <h6 class="card-text">
              {{
                this.go_letter(
                  this.$store.state.monthData.weekPrevPricePer,
                  "Price"
                )
              }}
            </h6>
          </div>
        </div>
      </div>
      <div class="col-md-4 stretch-card grid-margin">
        <div class="card bg-gradient-info card-img-holder text-white">
          <div class="card-body">
            <img
              src="../../assets/images/dashboard/circle.svg"
              class="card-img-absolute"
              alt="circle-image"
            />
            <h4
              class="font-weight-bold mb-3"
              style="font-family: Nanum Gothic, sans-serif; font-weight: bold"
            >
              주간 주문수
              <i class="mdi mdi-bookmark-outline mdi-24px float-right"></i>
            </h4>
            <h2
              class="mb-5"
              style="
                font-weight: bold;
                font-family: Nanum Gothic, sans-serif;
                font-size: 35px;
              "
            >
              {{ this.comma(this.$store.state.monthData.weekTotalOrder) }}
            </h2>
            <h6 class="card-text">
              {{
                this.go_letter(
                  this.$store.state.monthData.weekPrevOrderPer,
                  "Order"
                )
              }}
            </h6>
          </div>
        </div>
      </div>
      <div class="col-md-4 stretch-card grid-margin">
        <div class="card bg-gradient-success card-img-holder text-white">
          <div class="card-body">
            <img
              src="../../assets/images/dashboard/circle.svg"
              class="card-img-absolute"
              alt="circle-image"
            />
            <h4
              class="font-weight-bold mb-3"
              style="font-family: Nanum Gothic, sans-serif"
            >
              일일 방문자수
              <i class="mdi mdi-diamond mdi-24px float-right"></i>
            </h4>
            <h2
              class="mb-5"
              style="
                font-weight: bold;
                font-family: Nanum Gothic, sans-serif;
                font-size: 35px;
              "
            >
              {{ cnt }} 명
            </h2>
            <h6 class="card-text">{{ new Date().toLocaleString() }} 기준</h6>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-7 grid-margin stretch-card">
        <div class="card">
          <div class="card-body">
            <div class="clearfix">
              <h4
                class="card-title float-left"
                style="
                  color: darkgrey;
                  font-weight: bold;
                  font-family: Nanum Gothic, sans-serif;
                  font-size: 25px;
                "
              >
                최근 6개월 상품별 가입자 수
              </h4>
              <div
                id="visit-sale-chart-legend"
                class="rounded-legend legend-horizontal legend-top-right float-right"
              >
                <ul>
                  <li>
                    <span class="legend-dots" style="background: #9a55ff"></span
                    >Mobile
                  </li>
                  <li>
                    <span class="legend-dots" style="background: #fe7096"></span
                    >TV
                  </li>
                  <li>
                    <span class="legend-dots" style="background: #6c99fa"></span
                    >Internet
                  </li>
                </ul>
              </div>
            </div>
            <visitAndSalesStatitics
              class="mt-1"
              :height="300"
            ></visitAndSalesStatitics>
          </div>
        </div>
      </div>
      <div class="col-md-5 grid-margin stretch-card">
        <div class="card">
          <div class="card-body">
            <h4
              class="card-title"
              style="
                color: darkgrey;
                font-weight: bold;
                font-family: Nanum Gothic, sans-serif;
                font-size: 25px;
              "
            >
              유형별 가입자수
            </h4>
            <CheckBox></CheckBox>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import visitAndSalesStatitics from "../../components/charts/widgets/visitAndSalesStatitics";
import CheckBox from "@/components/admin/CheckBox";
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "dashboard",
  components: {
    // trafficSourceChart,
    visitAndSalesStatitics,
    CheckBox,
  },
  data() {
    return {
      time1: null,
      cnt: 0,
    };
  },
  methods: {
    comma(num) {
      var len, point, str;

      num = num + "";
      point = num.length % 3;
      len = num.length;

      str = num.substring(0, point);
      while (point < len) {
        if (str != "") str += ",";
        str += num.substring(point, point + 3);
        point += 3;
      }
      return str;
    },
    go_letter(num, prod) {
      if (num === 987654321) return "No data from last week exists";
      else if (num === 0) {
        return `Same as last week`;
      } else {
        if (num < 0) return `Decreased by ${num}%`;
        else return `Increased by ${num}%`;
      }
    },
  },
  mounted() {
    var api_url = this.getHost;
    this.$Axios
      .get(api_url + "adminlog/visitcnt")
      .then((res) => (this.cnt = res.data))
      .catch((er) => console.log(er));
  },
  computed: {
    ...mapGetters(["getHost"]),
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap");
</style>