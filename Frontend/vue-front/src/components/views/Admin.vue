<template>
  <div class="main">
    <AdminHeader></AdminHeader>
    <SideBar></SideBar>
    <CheckBox></CheckBox>
  </div>
</template>
<script>
import AdminHeader from "../admin/AdminHeader";
import SideBar from "../admin/SideBar";
import CheckBox from "../admin/CheckBox";
export default {
  components: {
    AdminHeader,
    SideBar,
    CheckBox,
  },
};
</script>
<style scoped>
.main {
  margin-left: 290px; /* Same as the width of the sidenav */
}
</style>