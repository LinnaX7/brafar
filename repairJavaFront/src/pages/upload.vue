<template>
<div>
  <el-card
      shadow="hover"
      style="width: 80%;display: inline-block"
  >
    <template #header>
      <div>
        <span style="font-size: 20px">{{ className }}</span>
      </div>
    </template>
    <el-scrollbar>
      <div style="text-align: left;">
        <div class="upload-note">注意事项:</div>
        <div class="upload-note" style="white-space: pre" v-html="textA"/>
        <div class="upload-note" style="white-space: pre" v-html="textB"/>
      </div>
    </el-scrollbar>
  </el-card>
  <UploadItem :problem-id="problemId"></UploadItem>
</div>
</template>

<script>
import {getProblemById} from "@/server/api";
import UploadItem from "@/components/UploadItem";
export default {
  name: "upload",
  components:{UploadItem},
  data(){
    return{
      className: "",
      methodName: "",
      textA: "",
      textB: "",
      problemId: 0,
    }
  },
  created() {
    this.problemId = this.$route.params.id
    var id = new FormData()
    id.append("id", this.problemId)
    getProblemById(id).then(res =>{
      this.className = res.data.data.className
      this.methodName = res.data.data.methodName
      this.textA = "&nbsp;&nbsp;&nbsp;&nbsp;1.请上传以&nbsp;&nbsp;&nbsp;&nbsp;" + "<span style='font-weight: bold'>"+ this.className +"</span>" + "&nbsp;&nbsp;&nbsp;&nbsp;为类名的java文件"
      this.textB = "&nbsp;&nbsp;&nbsp;&nbsp;2.待修复方法名为&nbsp;&nbsp;&nbsp;&nbsp;" + "<span style='font-weight: bold'>"+ this.methodName +"</span>"
    })
  }
}
</script>

<style scoped>
.upload-note{
  padding: 15px 0;
}
</style>