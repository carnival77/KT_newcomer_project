<template>
  <div class="header_font">
    <div class="header1">
      <b-navbar toggleable="lg" type="dark" variant="info">
        <b-navbar-brand href="#">고객 관리</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <div v-if="id === null">HI</div>
        <b-collapse id="nav-collapse" is-nav>
          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown right>
              <!-- Using 'button-content' slot -->
              <template #button-content>
                <em v-if="id">{{ id["id"] }}</em>
              </template>
              <b-dropdown-item href="#">Profile</b-dropdown-item>
              <b-dropdown-item @click="logout" href="/" id="logout"
                >Sign Out</b-dropdown-item
              >
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapMutations } from "vuex";
export default {
  computed: {
    id() {
      return this.$store.getters.getId;
    },
  },
  methods: {
    ...mapMutations(["logout"]),
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap");

.ml-auto {
  padding: 3px;
}
.my-auto {
  width: 60px;
  height: 33px;
}
.header_font {
  font-family: "Noto Sans SC", "Noto Sans KR", sans-serif;
}
.bg-info {
  background-color: grey !important;
}
</style>