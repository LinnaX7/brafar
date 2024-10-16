<template>
  <el-table
      :data="infos"
      stripe
      border
      style="width: 80%;display: inline-block"
      @row-click="handleRowClick"
  >
    <el-table-column prop="id" label="序号" width="100px"/>
    <el-table-column prop="className" label="题目" />
  </el-table>
</template>

<script>
import {getProblems} from "@/server/api";

export default {
  name: "problems",
  data(){
    return{
      infos:[]
    }
  },
  created() {
    getProblems().then(res =>{
      this.infos = res.data.data
    })
  },

  methods:{
    handleRowClick(row){
      this.$router.push({
        name:"upload",
        params:{id:row.id}
      })
    }
  }
}
</script>

<style scoped>

</style>