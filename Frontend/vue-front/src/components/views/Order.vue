<template lang="">
    <!-- <<주의사항>> -->
    <!-- Bootstrap / Bootstrap-vue 사용 -->
    <!-- 데이터 들어가야 할 곳 찾으려면 '##' 검색 -->
    <!-- { } 로 표시된 곳에 실제 데이터 들어가야 함 -->
    <div>
        <Header/>
    <div id="body">
        <div id="title" class="container text-center">
            <h2 class="h2_type">빠른 접수 주문서 작성</h2>
        </div>

            <div class="order_info">
                <div class="order_info_inner">
                    <div class="tb_tit">
                        <h3 class="h3_type">TV / 인터넷</h3>
                    </div>
                    <div class="tb_wrap">
                        <table class="tb_form">
                            <colgroup>
                                <col style="width:140px;">
                                <col stype="width:auto;">
                                <col style="width:140px;">
                                <col stype="width:auto;">
                            </colgroup>
                            <!-- ## 실제 데이터 들어가야 할 곳 -->
                            <tbody>
                                <tr>
                                    <th scope="row">TV</th>
                                    <td v-if="itemData.category[0]===true">{{itemData.Name}}</td>
                                    <td v-else-if="itemData.category[3]===true">{{itemData.item.tv.Name}}</td>
                                    <td v-else>해당 없음</td>
                                    <th scope="row">약정</th>
                                    <td v-if="itemData.category[1]===true">{{itemData.Name}}</td>
                                    <td v-else-if="itemData.category[3]===true">{{itemData.item.internet.Name}}</td>
                                    <td v-else>해당 없음</td>
                                </tr>
                                <tr>
                                    <th scope="row">사은품</th>
                                    <td v-if="itemData.category[3]===true">30% 요금할인</td>
                                    <td v-else>해당 없음</td>
                                    <th scope="row">부가상품</th>
                                    <td v-if="itemData.category[1]===true || itemData.category[3]===true">Sky GiGA WiFi (임대료 무료)</td>
                                    <td v-else>해당 없음</td>
                                    <!-- <td v-else-if="itemData.category[0]===false && itemData.category[3]!==true">해당 없음</td> -->
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="tb_tit">
                        <h3 class="h3_type">모바일</h3>
                    </div>
                    <div class="tb_wrap">
                        <table class="tb_form">
                            <colgroup>
                                <col style="width:140px;">
                                <col stype="width:auto;">
                                <col style="width:140px;">
                                <col stype="width:auto;">
                            </colgroup>
                            <!-- ## 실제 데이터 들어가야 할 곳 -->
                            <tbody>
                                <tr>
                                    <th scope="row">가입유형</th>
                                    <td>신규가입</td>
                                    <th scope="row">요금제</th>
                                    <td v-if="itemData.category[2]===true">{{itemData.Name}}</td>
                                    <td v-else-if="itemData.category[3]===true && itemData.item.itemFlag[2]===true">{{itemData.item.mobile.Name}}</td>
                                    <td v-else>해당 없음</td>
                                </tr>
                                <tr>
                                    <th scope="row">제휴카드</th>
                                    <td>해당없음</td>
                                    <th scope="row">약정기간</th>
                                    <td>무약정</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div><!-- order_info_inner -->
                <div class="order_detail">
                        <div class="colgroup">
                            <div class="col">
                                <div class="icon_comb notice">
                                    <ul>
                                        <li>고객부담금이 발생하는 사은품의 경우 TV/인터넷 설치와 별도로 안내와 배송이 진행됩니다. (개통 후 일주일 내)</li>
                                        <li>TV 및 인터넷 설치비 36,300원 : (TV)16,500원 + (인터넷)19,800원</li>
                                        <li>TV 단독 설치비 25,000원 / 인터넷 단독 설치비 27,500원
                                            <br>
                                            ※ TV/인터넷 설치비 100% 할인 (빠른접수 한정, sky베이직 제외, 2021년 2월 28일까지)
                                        </li>
                                        <li>STB(셋톱박스) 임대료 월 1,100원</li>
                                        <li>월 납부금액은 부가서비스 등의 사용에 따라 추가금액이 합산되어 청구 될 수 있습니다.</li>
                                        <li>월 납부금액은 부가세 포함 금액입니다.</li>
                                        <li>제휴카드 할인은 월 통신요금 시뮬레이션용으로, 별도로 카드 신청이 필요합니다. 
                                            <a href="https://www.skylife.co.kr/service/fee/partnerCard.do" target="_blank">
                                                제휴카드 자세히 보기 
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- ## 실제 데이터 들어가야 할 곳 -->
                            <div class="col price">
                                <dl class="total">
                                    <dt>월 납부금액</dt>
                                    <dd>
                                        <em v-if="itemData.category[3]===true">{{itemData.totalPrice | currency}}</em>
                                        <em v-else>{{itemData.Price | currency}}</em>
                                        원
                                    </dd>
                                </dl>
                                <dl v-if="itemData.category[3]===true">
                                    <dt>총 이용료</dt>
                                    <dd>
                                        <em>{{itemData.prevPrice | currency}}</em>원
                                    </dd>
                                </dl>
                                <dl v-if="itemData.category[3]===true">
                                    <dt>총 할인금액</dt>
                                    <dd>
                                        (-)<em>{{itemData.saleAccout | currency}}</em>원
                                    </dd>
                                </dl>
                            </div>
                        </div><!-- colgroup -->
                    </div><!-- order_detail -->
            </div> <!-- order_info -->
        <div class="guide_wrap">
            <h3 class="tt">모바일 개통 안내</h3>
            <ul class="blt_list">
                <li>신규가입 건은 유심이 개통되어 발송되며, 번호이동 건은 본인 수령 확인 및 개통 안내문자 발송 후 개통이 진행됩니다.</li>
                <li>신규가입 건인데 선 개통을 원치 않으실 경우에는 해피콜 상담 시 말씀해 주시기 바랍니다.</li>
            </ul>
        </div>
        <div class="btn_wrap">
            <b-button @click="sendLogData" class="btn_type" squared>신청 완료</b-button>
        </div>
        <!-- <div id="terms">
            <div id="tv-terms"></div>
            <div id="mobile-terms"></div>
        </div> -->
        
    </div>
    <Footer/>
</div>

</template>
<script>
import Header from "./Home_Header.vue";
import Footer from "./Home_Footer.vue";
import axios from "axios";
import { mapGetters } from "vuex";
export default {
  name: "Register",
  components: {
    Header,
    Footer,
  },
  created() {
    window.scrollTo(0, 0);
    var itemData = this.getOrderData;
    if (itemData.category[3] === true) {
      itemData.saleAccout = itemData.prevPrice - itemData.totalPrice;
    }

    this.itemData = itemData;
  },
  data() {
    return {
      itemData: {},
    };
  },
  methods: {
    sendLogData() {
      var api_url = this.getHost;
      var logInfo = {};
      if (this.itemData.arrayType === "price") {
        logInfo = this.getPriceOrderData[this.itemData.index];
      } else if (this.itemData.arrayType === "weight") {
        logInfo = this.getWeightOrderData[this.itemData.index];
      }
      console.log("logInfo", logInfo);
      axios
        .post(api_url + "service/selection", logInfo)
        .then((res) => this.$router.push("/result_"))
        .catch((er) => console.error(er));
    },
  },
  computed: {
    ...mapGetters([
      "getOrderData",
      "getPriceOrderData",
      "getWeightOrderData",
      "getHost",
    ]),
  },
  filters: {
    currency(value) {
      var num = new Number(value);
      return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Mada:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap");
#body {
  color: #000000;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: 400;
  font-size: 14px;
  line-height: 1.5;
  letter-spacing: -0.03em;

  position: relative;
  width: 1200px;
  margin: 0 auto;
}
p,
ul,
ol,
li,
dl,
dt,
dd {
  list-style-image: none;
  list-style-position: outside;
  list-style-type: none;
  margin: 0;
  padding: 0;
}
.icon_comb::before {
  content: "";
  display: inline-block;
  background-image: url(https://static.skylife.co.kr/shop/pc/img/common/ico_common.png);
  background-repeat: no-repeat;
  overflow: hidden;
  vertical-align: middle;
}
#title {
  margin-top: 50px;
  margin-bottom: 50px;
}
.h2_type {
  font-family: "Noto Sans KR", sans-serif;
  font-weight: 700;
  font-size: 48px;
  letter-spacing: 0;
}
.h3_type {
  display: inline-block;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: 700;
  font-size: 24px;
  line-height: 1em;
  letter-spacing: 0;
}
.order_info {
  padding: 6px 6px 0;
  background-color: #ee2b33;
  margin-bottom: 30px;
}
.order_info_inner {
  padding: 36px 36px 15px 36px;
  background-color: #ffffff;
}
.order_detail {
  color: #ffffff;
}
.colgroup {
  display: table;
  width: 100%;
  table-layout: fixed;
}
.colgroup .col {
  display: table-cell;
}
.order_detail .col {
  padding: 33px 34px;
  vertical-align: top;
}
.order_detail .col .notice::before {
  position: absolute;
  top: 0;
  left: 0;
  width: 20px;
  height: 20px;
  margin-right: 8px;
  background-position: -170px -80px;
  vertical-align: -5px;
}

/* notice */
.notice {
  position: relative;
  padding-left: 28px;
  color: #fdf7d1;
  line-height: 20px;
}
.notice ul {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
  list-style-image: none;
  list-style-position: outside;
  list-style-type: none;
  margin: 0;
  padding: 0;
}
.notice li {
  font-size: 12px;
  position: relative;
  padding-left: 10px;
  list-style-image: none;
  list-style-position: outside;
  list-style-type: none;
  margin: 0;
}
.order_detail .col .notice a {
  color: inherit;
  text-decoration: underline;
}
.order_detail .col .notice li::before {
  content: "•";
  position: absolute;
  left: 0;
  top: -1px;
}

/* price */
.order_detail .price {
  width: 453px;
  padding: 30px 50px 30px 62px;
  border-left: 1px solid rgba(255, 255, 255, 0.4);
}
.order_detail .price dl.total:first-child {
  margin-top: 3px;
}
.order_detail .price .total {
  padding-bottom: 16px;
  border-bottom: 2px solid rgba(255, 255, 255, 0.5);
}
.order_detail .price dl {
  display: table;
  width: 100%;
  margin-top: 19px;
  font-size: 16px;
  line-height: 1em;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}
.order_detail .price dt,
.order_detail .price dd {
  display: table-cell;
}
.order_detail .price dd {
  text-align: right;
}
.order_detail .price dd em {
  margin-right: 1px;
  font-style: normal;
  font-family: "Mada", sans-serif;
}
.order_detail .price .total dd em {
  font-size: 36px;
  line-height: 1em;
  vertical-align: -2px;
}

/* guide */
.guide_wrap {
  margin: 30px 0 0;
  padding: 36px 40px;
  background-color: #f5f5f5;
}
.guide_wrap .tt {
  font-family: "Noto Sans KR", sans-serif;
  font-weight: 700;
  font-size: 20px;
}
.blt_list > li {
  color: #666;
  position: relative;
  padding-left: 15px;
  font-size: 15px;
  font-weight: 400;
}
.blt_list > li:before {
  content: "";
  position: absolute;
  top: 10px;
  left: 0;
  width: 4px;
  height: 4px;
  background-color: #666666;
}
.guide_wrap .blt_list > li:before {
  background-color: #666;
}

/* button */
.btn_wrap {
  margin-top: 58px;
  margin-bottom: 58px;
  text-align: center;
  display: block;
}
.btn_type {
  min-width: 280px;
  padding-left: 60px;
  padding-right: 60px;
  display: inline-block;
  line-height: 60px;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: 700;
  font-size: 24px;
  background-color: #ee2e36;
  color: #ffffff !important;
  border: none;
}

/* table */
table {
  width: 100%;
  border-collapse: seperate;
  border-spacing: 0;
  table-layout: fixed;
}
.tb_tit {
  padding-bottom: 16px;
  background-color: #ffffff;
  position: relative;
}

.tb_wrap {
  border-top: 2px solid #363636;
  background-color: #ffffff;
}
.tb_form {
  padding: 13px 0 16px;
  background-color: #ffffff;
}
.order_info .tb_form th {
  padding: 10px 0 10px;
  font-family: "Noto Sans KR", sans-serif;
  font-weight: 700;
  font-size: 20px;
  text-align: left;
}
tr {
  display: table-row;
}
.order_info .tb_wrap + .tb_tit {
  margin-top: 66px;
}
</style>