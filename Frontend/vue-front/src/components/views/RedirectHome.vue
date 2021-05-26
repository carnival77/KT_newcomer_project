<template lang="">
    <div class="app">
        <Header></Header>
        
        <div class="wrapper_redirect">
            <div class="inner_redirect">
                <div class="spinner_wrapper">
                    <div class="text-center d-flex justify-content-between">
                    <b-spinner
                        v-for="variant in variants"
                        :variant="variant"
                        :key="variant"
                        type="grow"
                    ></b-spinner>
                    </div>
                    
            </div>
                <h2 style="font-family: Nanum Gothic; font-weight: bold">SkyLife의 추천 서비스를 <br> 이용해 주셔서 감사합니다.</h2>
                <br>
                <h5 style="color:gray">곧 홈 화면으로 이동합니다.</h5>
            </div>
        </div>
        <Footer></Footer>
    </div>
</template>
<script>
import Header from "./Home_Header.vue";
import Footer from "./Home_Footer.vue";
export default {
  data() {
    return {
      variants: [
        // "primary",
        // "secondary",
        "danger",
        "warning",
        "success",
        "info",
        // "light",
        // "dark",
      ],
    };
  },
  components: {
    Header,
    Footer,
  },
  mounted() {
    window.scrollTo(0, 0);
    setTimeout(() => {
      this.$router.push("/");
    }, 2000);
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Mada:wght@600&family=Noto+Sans+KR:wght@300;400;500;700&display=swap");
/* .app{
        margin : auto 0;
    } */
.wrapper_redirect {
  /* margin-top:400px; */
  width: 400px;
  height: 100%;
  margin: 0 auto 0 auto;
  padding: 200px 0 200px 0;
  font-family: "Nanum Gothic", sans-serif;
  font-weight: 400;
  font-size: 14px;
}
.inner_redirect {
  text-align: center;
}
.spinner_wrapper {
  margin-bottom: 20px;
}
</style>