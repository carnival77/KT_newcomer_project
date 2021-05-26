<template lang="">
<div>
    <Header/>
    <div id="main_cover">
        <div class="main_wrapper">
            <div class="main_title">
                요금제 추천 결과
            </div>
            <div v-if="categoryFlag[0] === true" class="single_item item_div">
                <div class="title_1">
                    <!-- <p>선택하신 기준에 맞는 요금제입니다.</p> -->
                </div>
                <b-tabs content-class="mt-6" fill 
                  active-nav-item-class="font-weight-bold">
                  <b-tab title="추천 순" active>
                <div class="single_item_inner">
                <div v-for="(tv, index) in orderedSingleTv" :key="index" class="card_cover">
                    <div class="card_inner">
                      <!-- content / price / detail -->
                        <div class="card_content">
                            <div class="title_2">
                                <div class="title_label">
                                    <span class="pick_1">BEST</span>
                                    <span class="t_info">{{tv.Channel}}</span>
                                    <div v-if="tv.Channel===234">
                                      <span class="t_info">UHD 6채널</span>
                                      <span class="t_info">Android TV</span>
                                    </div>
                                    <div v-else>
                                      <span class="t_info">HD</span>
                                    </div>
                                    <span class="label_recommend">추천</span>
                                </div>
                                {{tv.Name}}
                            </div>
                             <div class="card_item_2">
                               <ul>
                                 <li>
                                    <img src="../../assets/gift-solid.svg" class="gift_icon"/>
                                      <div class="service">
                                        {{tv.Service}}
                                      </div>
                                 </li>
                                 <li>
                                  <img src="../../assets/gift-solid.svg" class="gift_icon"/>
                                    <div class="service">
                                      GiGA WiFi 무료
                                    </div>
                                 </li>
                              </ul>
                            </div>
                        </div>
                        <!-- card_content -->
                        <div class="card_price">
                            <div class="card_price_inner">
                                <div class="c_price">
                                    월 {{tv.Price | currency}} 원
                                </div>
                                <div class="description">
                                    (부가세 포함 금액)
                                </div>
                                <div class="noti">
                                    * 셋톱박스 임대료 1,100원 별도
                                </div>
                            </div>
                        </div>
                        <div class="card_detail">
                            <div class="card_detail_inner">
                                <div class="detail_modal">
                                    <span @click="openModal(`modal_detail_tv_${tv.Id}`)">자세히 보기</span>
                                      <ui-modal :ref="`modal_detail_tv_`+tv.Id" size="auto" removeHeader>
                                          <img :src="require(`@/assets/TV/tv_${tv.Id}.png`)" />
                                      </ui-modal>
                                    <img src="../../assets/chevron-right-solid.svg" class="detail_icon"/>
                                </div>
                                <div class="order" @click="placeOrder(tv)">
                                    바로 신청하기
                                </div>
                            </div>
                        </div>
                    </div><!-- card_inner -->
                </div><!-- card_cover tv -->


                <div v-for="(internet, index) in orderedSingleInternet" :key="index" class="card_cover Internet">
                    <div class="card_inner">
                        <div class="card_content">
                            <div class="internet_title title_2">
                                <!-- content / price / detail -->
                                <div class="title_label">
                                    <span class="pick_1">BEST</span>
                                    <span class="t_info">{{internet.Speed | internetSpeed}}</span>
                                    <span class="label_recommend">추천</span>
                                </div>
                                {{internet.Name}}
                            </div>
                            <div class="card_item_2">
                               <ul>
                                 <li>
                                    <img src="../../assets/gift-solid.svg" class="gift_icon"/>
                                      <div class="service">
                                        {{internet.Service}}
                                      </div>
                                 </li>
                              </ul>
                            </div>
                        </div>
                        <!-- card_content -->
                        <div class="card_price">
                            <div class="card_price_inner">
                                <div class="c_price">
                                    월 {{internet.Price | currency}} 원
                                </div>
                                <div class="description">
                                    (부가세 포함 금액)
                                </div>
                                <div class="noti">
                                    * 셋톱박스 임대료 1,100원 별도
                                </div>
                            </div>
                        </div>
                        <div class="card_detail">
                            <div class="card_detail_inner">
                                <div class="detail_modal">
                                    <span @click="openModal(`modal_detail_internet_${internet.Id}`)">자세히 보기</span>
                                    <ui-modal :ref="`modal_detail_internet_`+internet.Id" size="auto" removeHeader>
                                        <img :src="require(`@/assets/Internet/internet_${internet.Id}.png`)" />
                                        <!-- <img src="../../assets/mobile/5G_완전무제한_충분.png"/> -->
                                    </ui-modal>
                                    <img src="../../assets/chevron-right-solid.svg" class="detail_icon"/>
                                </div>
                                <div class="order" @click="placeOrder(internet)">
                                    바로 신청하기
                                </div>
                            </div>
                        </div>
                    </div><!-- card_inner -->
                </div><!-- card_cover -->


                <div v-for="(mobile, index) in orderedSingleMobile" :key="index" class="card_cover Mobile">
                    <div class="card_inner">
                        <!-- content / price / detail -->
                        <div class="card_content">
                            <div class="mobile_title title_2">
                                <!--라벨-->
                                <div class="title_label">
                                    <span class="pick_1">BEST</span>
                                    <span class="m_data_type">{{mobile.Service}}</span>
                                    <span class="label_recommend">추천</span>
                                </div>
                                {{mobile.Name}}
                            </div>
                            <div class="card_item">
                                <ul class="list mobile_list">
                                    <li class="li_style">
                                        <div class="li_wrapper">
                                            <div style="text-align:center">
                                                <img src="../../assets/data.png"/>
                                            </div>
                                            <div class="li_txt">데이터</div>
                                            <div v-if="mobile.Data === 9999" class="li_txt">
                                              무제한
                                            </div>
                                            <div v-else class="li_txt">{{mobile.Data | mobileData}}</div>
                                        </div>
                                    </li>
                                    <li class="li_style">
                                        <div class="li_wrapper">
                                            <div style="text-align:center">
                                                <img src="../../assets/call.png"/>
                                            </div>
                                            <div class="li_txt">통화</div>
                                            <div v-if="mobile.Voice === 9999" class="li_txt">
                                              무제한
                                            </div>
                                            <div v-else class="li_txt">{{mobile.Voice}}분</div>
                                        </div>
                                    </li>
                                    <li class="li_style">
                                        <div class="li_wrapper">
                                            <div style="text-align:center">
                                                <img src="../../assets/message.png"/>
                                            </div>
                                            <div class="li_txt">메세지</div>
                                            <div v-if="mobile.Message === 9999" class="li_txt">
                                              무제한
                                            </div>
                                            <div v-else class="li_txt">{{mobile.Message}}건</div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <!-- card_item -->
                            <!-- <div class="card_item_2">
                              <img src="../../assets/gift-solid.svg" class="gift_icon"/>
                              {올레 Wi-Fi 부가서비스(8,800원) 무료}
                            </div> -->
                            <!-- card_item_2 -->
                        </div>
                        <!-- card_content -->
                        <div class="card_price">
                            <div class="card_price_inner">
                                <div class="c_price">
                                    월 {{mobile.Price | currency}} 원
                                </div>

                                <div class="description">
                                    (부가세 포함 금액)
                                </div>
                            </div>
                        </div>
                        <div class="card_detail">
                            <div class="card_detail_inner">
                                <div class="detail_modal">
                                    <span @click="openModal(`modal_detail_mobile_${mobile.Id}`)">자세히 보기</span>
                                    <ui-modal :ref="`modal_detail_mobile_` + mobile.Id" size="auto" removeHeader>
                                        <!-- <img src="../../assets/mobile/5G_완전무제한_충분.png"/> -->
                                        <img :src="require(`@/assets/Mobile/mobile_${mobile.Id}.png`)" />
                                    </ui-modal>
                                    <img src="../../assets/chevron-right-solid.svg" class="detail_icon"/>
                                </div>
                                <div class="order" @click="placeOrder(mobile)">
                                    바로 신청하기
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- card_inner -->
                </div>
                <!-- card_cover -->
                </div>
                <!-- single_item_inner -->
                </b-tab>


                <b-tab title="가격 순">
                  <div class="single_item_inner">
                <div v-for="(tv, index) in singleTv" :key="index" class="card_cover">
                    <div class="card_inner">
                      <!-- content / price / detail -->
                        <div class="card_content">
                            <div class="title_2">
                                <div class="title_label">
                                    <span class="pick_1">BEST</span>
                                    <span class="t_info">{{tv.Channel}}</span>
                                    <div v-if="tv.Channel===234">
                                      <span class="t_info">UHD 6채널</span>
                                      <span class="t_info">Android TV</span>
                                    </div>
                                    <div v-else>
                                      <span class="t_info">HD</span>
                                    </div>
                                    <span class="label_recommend">추천</span>
                                </div>
                                {{tv.Name}}
                            </div>
                             <div class="card_item_2">
                               <ul>
                                 <li>
                                    <img src="../../assets/gift-solid.svg" class="gift_icon"/>
                                      <div class="service">
                                        {{tv.Service}}
                                      </div>
                                 </li>
                                 <li>
                                  <img src="../../assets/gift-solid.svg" class="gift_icon"/>
                                    <div class="service">
                                      GiGA WiFi 무료
                                    </div>
                                 </li>
                              </ul>
                            </div>
                        </div>
                        <!-- card_content -->
                        <div class="card_price">
                            <div class="card_price_inner">
                                <div class="c_price">
                                    월 {{tv.Price | currency}} 원
                                </div>
                                <div class="description">
                                    (부가세 포함 금액)
                                </div>
                                <div class="noti">
                                    * 셋톱박스 임대료 1,100원 별도
                                </div>
                            </div>
                        </div>
                        <div class="card_detail">
                            <div class="card_detail_inner">
                                <div class="detail_modal">
                                    <span @click="openModal(`modal_detail_tv_order_${tv.Id}`)">자세히 보기</span>
                                      <ui-modal :ref="`modal_detail_tv_order_`+tv.Id" size="auto" removeHeader>
                                          <img :src="require(`@/assets/TV/tv_${tv.Id}.png`)" />
                                      </ui-modal>
                                    <img src="../../assets/chevron-right-solid.svg" class="detail_icon"/>
                                </div>
                                <div class="order" @click="placeOrder(tv)">
                                    바로 신청하기
                                </div>
                            </div>
                        </div>
                    </div><!-- card_inner -->
                </div><!-- card_cover tv -->


                <div v-for="(internet, index) in singleInternet" :key="index" class="card_cover Internet">
                    <div class="card_inner">
                        <div class="card_content">
                            <div class="internet_title title_2">
                                <!-- content / price / detail -->
                                <div class="title_label">
                                    <span class="pick_1">BEST</span>
                                    <span class="t_info">{{internet.Speed | internetSpeed}}</span>
                                    <span class="label_recommend">추천</span>
                                </div>
                                {{internet.Name}}
                            </div>
                            <div class="card_item_2">
                               <ul>
                                 <li>
                                    <img src="../../assets/gift-solid.svg" class="gift_icon"/>
                                      <div class="service">
                                        {{internet.Service}}
                                      </div>
                                 </li>
                              </ul>
                            </div>
                        </div>
                        <!-- card_content -->
                        <div class="card_price">
                            <div class="card_price_inner">
                                <div class="c_price">
                                    월 {{internet.Price | currency}} 원
                                </div>
                                <div class="description">
                                    (부가세 포함 금액)
                                </div>
                                <div class="noti">
                                    * 셋톱박스 임대료 1,100원 별도
                                </div>
                            </div>
                        </div>
                        <div class="card_detail">
                            <div class="card_detail_inner">
                                <div class="detail_modal">
                                    <span @click="openModal(`modal_detail_internet_order_${internet.Id}`)">자세히 보기</span>
                                    <ui-modal :ref="`modal_detail_internet_order_`+internet.Id" size="auto" removeHeader>
                                        <img :src="require(`@/assets/Internet/internet_${internet.Id}.png`)" />
                                    </ui-modal>
                                    <img src="../../assets/chevron-right-solid.svg" class="detail_icon"/>
                                </div>
                                <div class="order" @click="placeOrder(internet)">
                                    바로 신청하기
                                </div>
                            </div>
                        </div>
                    </div><!-- card_inner -->
                </div><!-- card_cover -->


                <div v-for="(mobile, index) in singleMobile" :key="index" class="card_cover Mobile">
                    <div class="card_inner">
                        <!-- content / price / detail -->
                        <div class="card_content">
                            <div class="mobile_title title_2">
                                <!--라벨-->
                                <div class="title_label">
                                    <span class="pick_1">BEST</span>
                                    <span class="m_data_type">{{mobile.Service}}</span>
                                    <span class="label_recommend">추천</span>
                                </div>
                                {{mobile.Name}}
                            </div>
                            <div class="card_item">
                                <ul class="list mobile_list">
                                    <li class="li_style">
                                        <div class="li_wrapper">
                                            <div style="text-align:center">
                                                <img src="../../assets/data.png"/>
                                            </div>
                                            <div class="li_txt">데이터</div>
                                            <div v-if="mobile.Data === 9999" class="li_txt">
                                              무제한
                                            </div>
                                            <div v-else class="li_txt">{{mobile.Data | mobileData}}</div>
                                        </div>
                                    </li>
                                    <li class="li_style">
                                        <div class="li_wrapper">
                                            <div style="text-align:center">
                                                <img src="../../assets/call.png"/>
                                            </div>
                                            <div class="li_txt">통화</div>
                                            <div v-if="mobile.Voice === 9999" class="li_txt">
                                              무제한
                                            </div>
                                            <div v-else class="li_txt">{{mobile.Voice}}분</div>
                                        </div>
                                    </li>
                                    <li class="li_style">
                                        <div class="li_wrapper">
                                            <div style="text-align:center">
                                                <img src="../../assets/message.png"/>
                                            </div>
                                            <div class="li_txt">메세지</div>
                                            <div v-if="mobile.Message === 9999" class="li_txt">
                                              무제한
                                            </div>
                                            <div v-else class="li_txt">{{mobile.Message}}건</div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <!-- card_item -->
                            <!-- <div class="card_item_2">
                              <img src="../../assets/gift-solid.svg" class="gift_icon"/>
                              {올레 Wi-Fi 부가서비스(8,800원) 무료}
                            </div> -->
                            <!-- card_item_2 -->
                        </div>
                        <!-- card_content -->
                        <div class="card_price">
                            <div class="card_price_inner">
                                <div class="c_price">
                                    월 {{mobile.Price | currency}} 원
                                </div>

                                <div class="description">
                                    (부가세 포함 금액)
                                </div>
                            </div>
                        </div>
                        <div class="card_detail">
                            <div class="card_detail_inner">
                                <div class="detail_modal">
                                    <span @click="openModal(`modal_detail_mobile_order_${mobile.Id}`)">자세히 보기</span>
                                    <ui-modal :ref="`modal_detail_mobile_order_` + mobile.Id" size="auto" removeHeader>
                                        <!-- <img src="../../assets/mobile/5G_완전무제한_충분.png"/> -->
                                        <img :src="require(`@/assets/Mobile/mobile_${mobile.Id}.png`)" />
                                    </ui-modal>
                                    <img src="../../assets/chevron-right-solid.svg" class="detail_icon"/>
                                </div>
                                <div class="order" @click="placeOrder(mobile)">
                                    바로 신청하기
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- card_inner -->
                </div>
                <!-- card_cover -->
                </div>
                <!-- single_item_inner -->

                </b-tab>
                </b-tabs>
            </div>
            <!-- single -->

            <div v-if="categoryFlag[1] === true" class="combined_item">
                <p class="title_1">이런 결합상품은 어떠세요?</p>
                <div class="combined_item_inner">
                <div v-for="(obj, index) in combineItems" :key="index" class="card_cover Mobile">
                    <div class="card_inner">
                        <!-- content / price / detail -->
                        <div class="card_content">
                            <div class="title_2">
                                <!--추천-->
                                <!--라벨-->
                                <div class="title_label">
                                    <span class="label_sale">결합할인</span>
                                </div>
                                {{obj.productName}}
                            </div>
                            
                            <div class="card_item">
                                <ul class="list">
                                    <li v-if="obj.item.itemFlag[0] === true" class="li_style">
                                        <div class="li_wrapper">
                                            <div class="li_icon_cover" style="text-align:center">
                                                <img class="li_icon" src="../../assets/tv-solid.svg"/>
                                            </div>
                                            <div class="li_txt">TV</div>
                                            <div class="li_txt">{{obj.item.tv.Name}}</div>
                                        </div>
                                    </li>
                                    <li v-if="obj.item.itemFlag[1] === true" class="li_style">
                                        <div class="li_wrapper">
                                            <div style="text-align:center">
                                                <img class="li_icon" src="../../assets/internet-solid.svg"/>
                                            </div>
                                            <div class="li_txt">인터넷</div>
                                            <div class="li_txt">{{obj.item.internet.Name}}</div>
                                        </div>
                                    </li>
                                    <li v-if="obj.item.itemFlag[2] === true" class="li_style">
                                        <div class="li_wrapper">
                                            <div style="text-align:center">
                                                <img class="li_icon" src="../../assets/mobile-solid.svg"/>
                                            </div>
                                            <div class="li_txt">모바일</div>
                                            <div class="li_txt">{{obj.item.mobile.Name}}</div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <!-- card_item -->
                        </div>
                        <!-- card_content -->
                        <div class="card_price">
                            <span class="icon_sale">
                                <img src="../../assets/ico_sale.png" alt="요금할인_icon">
                                <p class="rate">{{obj.saleRate}}</p>
                                </span>
                                <div class="card_price_inner">
                                    <div class="p_price">
                                        월 {{obj.prevPrice | currency}}원
                                    </div>
                                    <div class="c_price">
                                        월 {{obj.totalPrice | currency}} 원
                                    </div>
                                    <div class="description">
                                        (부가세 포함 금액)
                                    </div>
                                </div>
                            </div>
                            <div class="card_detail">
                                <div class="card_detail_inner">
                                    <div class="detail_modal">
                                        <span><a href="https://www.skylife.co.kr/combination/skyHome.do" target="_blank">자세히 보기</a></span>
                                        <!-- <span @click="openModal('modal_detail')">자세히 보기</span>
                                        <ui-modal ref="modal_detail" size="auto" removeHeader=true>sdfad </ui-modal> -->
                                        
                                        <img src="../../assets/chevron-right-solid.svg" class="detail_icon"/>
                                    </div>
                                    <div class="order" @click="placeOrder(obj)">
                                        바로 신청하기
                                    </div>
                                </div>
                            </div>
                        </div><!-- card_inner -->
                        <div class="card_notice_wrapper">
                          <img src="../../assets/notice-solid.svg" class="notice_icon">
                          <div class="card_notice_inner">
                            <ul>
                              <li>• 수신기(임대) 1,100원은 별도 부가됩니다.</li>
                              <li>• 단독설치비 25,000원, 복수/추가 설치 시 2ndTV부터는 설치비 10,000원 적용</li>
                              <li>• 인터넷 동시 설치시 설치비 16,500원</li>
                              <li>• Direct shop 단독 혜택 : sky 기가 500M, sky 기가 1G 월 2,200원 추가 할인</li>
                            </ul>
                            </div>
                        </div>
            </div><!--card_cover-->
            </div><!-- combined_item_inner -->
        </div>  <!-- combined_item -->
        </div> <!-- main_wrapper-->
    </div>
    <Footer />
    </div>
</template>
<script>
import { mapGetters, mapMutations } from "vuex";
import Header from "./Home_Header.vue";
import Footer from "./Home_Footer.vue";
import { UiModal } from "keen-ui";
// import _ from "lodash";
export default {
  components: {
    UiModal,
    Header,
    Footer,
  },
  data() {
    return {
      singleTv: [],
      singleInternet: [],
      singleMobile: [],
      combineItems: [],
      categoryFlag: [],
      orderedItems: [],
      orderedSingleTv: [],
      orderedSingleInternet: [],
      orderedSingleMobile: [],
      modalShow: [],
    };
  },
  mounted() {
    var items = this.getResultData;
    console.log("items", items);
    var singleItems = items.singleItems;
    // this.combineItems = items.combineItems;
    this.categoryFlag = items.categoryFlag;

    items.combineItems.forEach((item) => {
      var saleAccout = item.prevPrice - item.totalPrice;
      var saleRate = Math.round(saleAccout/item.prevPrice*100);
      item.saleRate = saleRate;
      item.arrayType = "price";
      this.combineItems.push(item);
    });

    singleItems.forEach((item) => {
      var idx = item.index;
      if (item.itemFlag[0] === true) {
        //tv
        item.tv.category = [true, false, false, false];
        item.tv.index = idx;
        item.tv.arrayType = "price";
        this.singleTv.push(item.tv);
      }
      if (item.itemFlag[1] === true) {
        //internet
        item.internet.category = [false, true, false, false];
        item.internet.index = idx;
        item.internet.arrayType = "price";
        this.singleInternet.push(item.internet);
      }
      if (item.itemFlag[2] === true) {
        //mobile
        item.mobile.category = [false, false, true, false];
        item.mobile.index = idx;
        item.mobile.arrayType = "price";
        this.singleMobile.push(item.mobile);
      }
    });
    console.log("singleItems", singleItems);
    console.log("combineItems", this.combineItems);

    console.log("tvlist", this.singleTv);
    console.log("internetlist", this.singleInternet);
    console.log("mobilelist", this.singleMobile);

    this.orderedItems = this.getResultWeightData.singleItems;

    this.orderedItems.forEach((item) => {
      var idx = item.index;
      if (item.itemFlag[0] === true) {
        //tv
        item.tv.category = [true, false, false, false];
        item.tv.index = idx;
        item.tv.arrayType = "weight";
        this.orderedSingleTv.push(item.tv);
      }
      if (item.itemFlag[1] === true) {
        //internet
        item.internet.category = [false, true, false, false];
        item.internet.index = idx;
        item.internet.arrayType = "weight";
        this.orderedSingleInternet.push(item.internet);
      }
      if (item.itemFlag[2] === true) {
        //mobile
        item.mobile.category = [false, false, true, false];
        item.mobile.index = idx;
        item.mobile.arrayType = "weight";
        this.orderedSingleMobile.push(item.mobile);
      }
    });
    console.log("ordered_tvlist", this.orderedSingleTv);
    console.log("ordered_internetlist", this.orderedSingleInternet);
    console.log("ordered_mobilelist", this.orderedSingleMobile);

    // for(var i = 0; i < singleItems.length + this.combineItems.length; i++){
    //   this.modalShow.push(false);
    // }
    // console.log("modalShow", this.modalShow);
  },
  methods: {
    ...mapMutations(["setOrderData"]),
    openModal(ref) {
      console.log(ref);
      console.log(this.$refs[ref]);
      this.$refs[ref][0].open();
      // this.$refs[ref].open();
    },
    closeModal(ref) {
      this.$refs[ref].close();
    },
    placeOrder(item) {
      console.log("pickedItem", item);
      this.setOrderData(item);
      this.$router.push("/order");
    },
    returnId(category, id) {
      return category + id;
    },
  },
  computed: {
    ...mapGetters(["getResultData", "getResultWeightData"]),
    // orderedTvArray() {
    //   return _.orderBy(this.singleTv, "Price", "desc");
    // },
    // orderedInternetArray() {
    //   return _.orderBy(this.singleInternet, "Price", "desc");
    // },
    // orderedMobileArray() {
    //   return _.orderBy(this.singleMobile, "Price", "desc");
    // },
    tabIndicatorColor() {
      if (this.backgroundColor === "default") {
        return "primary";
      }
      return "white";
    },
  },
  filters: {
    currency(value) {
      var num = new Number(value);
      return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
    mobileData(value) {
      return value + "GB";
    },
    internetSpeed(value) {
      if (value >= 1000) {
        return value / 1000 + "Gbps";
      }
      return value + "Mbps";
    },
    descriptionDeco(value) {},
  },
};
</script>

    <style>
#main_cover {
  margin: 30px auto 0;
  max-width: 100%;
  height: 98%;
  font-family: "Noto Sans KR", sans-serif;
  font-size: 27px;
  letter-spacing: -0.03em;
  padding-bottom: 70px;
  width: 1100px;
  /* margin-top: 30px; */
}
.main_wrapper {
  margin: 0 auto;
  font-family: "Noto Sans KR", sans-serif;
}
.main_title {
  font-size: 33px;
  font-weight: 500;
  font-family: "Noto Sans KR", sans-serif;
}
.title_label {
  font-size: 18px;
  font-family: "Noto Sans KR", sans-serif;
}
.title_label div {
  display: inline-block;
}
.title_label span {
  display: inline-block;
  height: 28px;
  margin-right: 6px;
  padding: 0 10px;
  font-size: 14px;
  line-height: 26px;
  vertical-align: top;
  /* margin-right: 5px;
            padding : 2px 6px 2px 6px; */
}
.title_label .m_data_type {
  border: 1px solid #ee2b33;
  color: #ee2b33;
}
.title_label .t_info {
  border: 1px solid black;
}
.title_label .label_recommend {
  border: 1px solid #18bda4;
  color: #18bda4;
}
.title_label .pick_1 {
  display: none;
  border: 1px solid #ee2b33;
  background-color: #ee2b33;
  color: white;
}
.title_label .label_sale {
  display: inline-block;
  border: #ee2b33 1px solid;
  color: #ee2b33;
}
.title_label .label_sale:after {
  content: "";
  display: inline-block;
  width: 6px;
  height: 13px;
  margin-left: 3px;
  background: url("https://static.skylife.co.kr/shop/pc/img/contents/blt_sale.png")
    no-repeat center top;
  vertical-align: -1px;
}
.title_1 {
}
.combined_item .title_1 {
  margin-top: 40px;
  font-size: 30px;
}
.item_div {
  display: flex;
  flex-direction: column;
  margin: 30px auto 0;
  width: 100%;
}
.item_div .single_item_inner > div:first-child {
  border: 2px solid #ee2b33;
}
.item_div .single_item_inner > div:first-child:hover {
  border: 2px solid #c72a30;
}
.item_div .single_item_inner > div:first-child .title_label .pick_1 {
  display: inline-block;
}
.single_item .single_item_inner {
  border-bottom: 1px solid #dadada;
  border-left: 1px solid #dadada;
  border-right: 1px solid #dadada;
  padding: 20px 10px 5px 10px;
  box-shadow: 3px 3px 5px #dadada;
  background-color: #f5f5f5;
}
.combined_item .combined_item_inner {
  border: 1px solid #dadada;
  padding: 20px 10px 5px 10px;
  box-shadow: 3px 3px 5px #dadada;
  background-color: #f5f5f5;
}
.card_cover {
  border: 1px solid #dadada;
  /* height: 200px; */
  padding: 30px 30px 25px;
  margin-bottom: 20px;
  background-color: white;
  /* box-shadow: 3px 3px 5px #dadada; */
}
.card_cover:hover {
  border: 1px solid #ee2b33;
}
.card_item .list {
  display: flex;
  list-style-type: none;
}
.single_item .card_item .list > li:first-child,
.combined_item .card_item .list > li:first-child {
  padding-left: 0;
}
.single_item .card_item .list > li:last-child,
.combined_item .card_item .list > li:last-child {
  padding-right: 0;
}
.single_item .card_item .list .li_style {
  /* display:table-cell; */
  /* padding-right: 150px; */
  padding-left: 75px;
  padding-right: 75px;
}
.combined_item .card_item .list .li_style {
  padding-left: 50px;
  padding-right: 50px;
}
.card_item .list .li_txt {
  text-align: center;
  font-size: 17px;
  margin: 0;
  padding: 0;
}
.card_item .list .li_style .li_wrapper .li_icon {
  width: 25px;
  height: 25px;
}
.card_item_2 {
  font-size: 16px;
  color: #656565;
}
.card_item_2 ul {
  margin: 0;
  padding: 0;
}
.card_item_2 ul li {
  position: relative;
  text-decoration: none;
  display: flex;
}
.card_item_2 .service {
  margin-left: 24px;
  /* font-size: 13px; */
  color: #656565;
}
.card_item_2 .gift_icon {
  width: 18px;
  position: absolute;
  top: 2px;
}
.ico_data {
  background: url("../../assets/data.png") no-repeat center 4px;
}
.detail_icon {
  margin-left: 3px;
  width: 15px;
}
/* Card */
.card_outer {
}
.card_inner {
  display: table;
  width: 100%;
}
.card_inner > div {
  vertical-align: middle;
}
.card_content {
  display: table-cell;
  width: 600px;
  /* margin-right:150px; */
}
.card_price {
  display: table-cell;
  position: relative;
  padding-top: 10px;
  text-align: right;
}
.card_price .icon_sale {
  position: absolute;
  left: 14px;
  top: -31px;
  width: 88px;
  height: 79px;
}

.card_price .icon_sale img{
  width: 100px;
  height: 88px;
}

.card_price .icon_sale .rate{
  position: absolute;
  left: 28px;
  top: 6px;
  font-weight: 700;
  font-size: 1em;
  color : white;
  width: 35px;
  text-align : right;
}

.card_price .card_price_inner {
  vertical-align: middle;
}
.card_price .description {
  font-size: 15px;
}
.card_price .noti {
  padding-top: 3px;
  color: #7c7a7a;
  font-size: 13px;
}
.card_price .p_price {
  font-size: 20px;
  color: #656565;
  text-decoration-line: line-through;
  text-decoration-color: #ee2b33;
}
.card_price .p_price:before {
  /* content: '';
    position: absolute;
    top: 50%;
    right: -12px;
    width: 16px;
    height: 7px;
    margin-top: -4px;
    background: url(https://static.skylife.co.kr/shop/pc/img/contents/blt_original.png) no-repeat right center; */
}
.card_price .c_price {
  color: #ee2b33;
  font-weight: 600;
}
.card_detail {
  display: table-cell;
  text-align: center;
  padding-left: 30px;
  font-size: 18px;
}
.card_detail .card_detail_inner > div {
  cursor: pointer;
}
.card_detail .card_detail_inner > div:first-child {
  margin-bottom: 5px;
}
.card_detail .detail_modal {
  border: 1px solid #cccccc;
}
.card_detail .detail_modal:hover {
  border: 1px solid #656565;
}
.card_detail .order {
  color: #ee2b33;
  border: 1px solid #ee2b33;
}
.card_detail .order:hover {
  color: #ffffff;
  background-color: #ee2b33;
}
.card_detail .detail_modal ui-modal img {
  width: 200px;
}
.card_detail .detail_modal a {
  text-decoration: none;
  color: black;
}
.card_content .title_2 {
  font-weight: 500;
  margin-bottom: 10px;
}
.card_content .title_2 .rec_icon {
  width: 60px;
  padding-bottom: 5px;
}
.card_content .card_item {
  /* margin-bottom: 10px; */
  display: table-cell;
}
.card_notice_wrapper {
  background-color: #f5f5f5;
  color: #656565;
  position: relative;
  padding: 10px 13px 1px 13px;
  font-size: 15px;
}
.card_notice_wrapper .notice_icon {
  width: 17px;
}
.card_notice_wrapper ul {
  list-style: none;
  padding-left: 0px;
  position: relative;
}
.card_notice_wrapper ul li::before {
  content: "•";
  position: absolute;
  left: 0;
  top: -1px;
}
.card_notice_wrapper ul > li {
}
.nav-tabs .nav-link {
  color: black;
  font-family: "Noto Sans KR", sans-serif;
  font-size: 25px;
}
.nav-tabs .nav-link.active {
  background-color: #f5f5f5 !important;
  font-family: "Noto Sans KR", sans-serif;
  /* border-bottom: transparent !important; */
  font-size: 25px;
}
.detail_modal .ui-modal .ui-focus-container {
  margin-top: 80px;
  margin-bottom: 16px;
}
</style>