<template>
  <el-container>
    <el-header>
      <el-menu
          :default-active="activeIndex"
          mode="horizontal"
          @select="handleSelect"
      >
        <el-menu-item index="1">上传文件</el-menu-item>
        <el-menu-item index="2">重构指导</el-menu-item>
        <el-menu-item index="3">修复指导</el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>

export default {
  name: "home",
  data(){
    return {
      activeIndex: "1"
    }
  },
  methods:{
    handleSelect(index){
      this.activeIndex = index
      switch (this.activeIndex){
        case "1":
          this.$router.push('/problems')
          break
        case "2":
          this.$router.push('/refactor')
          break
        case "3":
          this.$router.push('/repair')
          break
      }
    },
  }
}
</script>

<style scoped>
.el-container {
  height: 100%;
}

.el-header, .el-footer {
}



.el-main {

  text-align: center;
}

</style>