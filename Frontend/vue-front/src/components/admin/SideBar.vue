<template>
  <div>
    <div class="sidenav" id="sidenav-overlay">
      <div class="sidenav-logo-button-container">
        <router-link to="/">
          <div class="sidenav-logo-container"></div>
        </router-link>
      </div>
      <div class="mobile-open">
        <div class="open-menu" @click="toogle">menu</div>
      </div>

      <div v-show="show" class="mobile-navigation-show">
        <ul>
          <li>
            <router-link to="/">Home</router-link>
          </li>
          <li>
            <router-link to="/">about</router-link>
          </li>
          <li>
            <router-link to="/">portfolio</router-link>
          </li>
          <li>
            <router-link to="/">misc</router-link>
          </li>
          <li>
            <router-link to="/">contact</router-link>
          </li>
        </ul>
      </div>
    </div>

    <div class="routerview">
      <router-view />
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      show: true,
    };
  },
  methods: {
    toogle() {
      this.show = !this.show;
    },
  },
};
</script>


<style>
@import "https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap";

.mobile-open {
  display: none;
}

.sidenav-logo-button-container {
  padding: 0px;
  height: auto;
  display: flex;
  align-content: center;
  justify-content: center;
  padding: 20px;
}

.sidenav-logo-container {
  display: block;
  align-items: center;
  justify-content: center;
  margin: 0 auto;
  padding: 10px;
  height: 90px;
  width: 90px;
  background-color: black;
  background: url("//static.skylife.co.kr/images/common/new_logo.png");
  background-size: contain;
  padding-bottom: 10px;
  background-repeat: no-repeat;
  background-position: center;
  transition: filter 0.5s;
}

.sidenav-logo-container:hover {
  filter: drop-shadow(0 0 0.75rem rgba(255, 0, 157, 0.822));
}

.sidenav ul {
  position: relative;
  top: 10px;
  width: 100%;
  list-style-type: none;
  padding: 0px;
  margin: 0 auto;
}

.sidenav li {
  background-color: white;
  border-top: 1px solid rgb(121, 121, 121);
  transition: 0.3s;
}

.sidenav {
  height: 1100px;
  width: 290px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: white;
  overflow-x: hidden;
  padding-top: 20px;
  font-family: "Josefin Sans", sans-serif;
}

.sidenav a {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  color: #000000;
  display: block;
  font-size: 18px;
}

.sidenav a:hover {
  color: #cc912a;
}

.sidenav li:hover {
  background-color: #f0eeee;
}

@media screen and (max-width: 800px) {
  .sidenav {
    display: block;
    width: 100%;
    height: auto;

    padding: 0px;
  }
  .sidenav ul {
    display: block;
    border-bottom-style: solid;
  }
  #app {
    margin-left: -290px;
  }
  .sidenav-logo-button-container {
    padding: 0px;
  }

  .mobile-open {
    display: block;
    font-size: 22px;
    cursor: pointer;
    color: black;
    border-top-style: solid;
    border-bottom-style: solid;
    border-width: 2px;
    border-color: black;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  }
}
</style>