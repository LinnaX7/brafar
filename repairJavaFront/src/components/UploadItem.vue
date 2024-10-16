<template>
  <el-upload
      class="upload-item"
      ref="upload"
      action=""
      :auto-upload="false"
      :on-change="handleFileChange"
      :on-remove="handleFileRemove"
  >
    <template #trigger>
      <el-button type="primary" size="large">
        选择文件
      </el-button>
    </template>
    <el-button type="success" size="large" @click="submitUpload" style="margin-left: 50px">
      上传文件
    </el-button>
    <template #tip>
      <div class="el-upload__tip">
        只能上传java文件
      </div>
    </template>
  </el-upload>
</template>

<script>
import {uploadFile} from "@/server/api";
export default {
  name: "UploadItem",
  props: ["problemId"],
  data(){
    return {
      file:{}
    }
  },
  methods:{
    handleFileChange(file, fileList){
      let fileName = file.name;
      let uid = file.uid
      let pos = fileName.lastIndexOf(".");
      let lastName = fileName.substring(pos, fileName.length);
      if (lastName.toLowerCase() !== ".java") {
        this.$message.error("文件必须为.java 类型");
        for (var i = 0; i < fileList.length; i++) {
          if (fileList[i].uid == uid) {
            fileList.splice(i, 1)
          }
        }
      }
      if (fileList.length > 1) {
        fileList.splice(0, 1);
      }
      this.fileList = fileList
    },

    handleFileRemove(file, fileList){
      this.fileList = fileList
    },

    submitUpload(){
      var formData = new FormData()
      formData.append("problemId", this.problemId)
      this.fileList.forEach(file => {
        formData.append("fileList", file.raw)
      })
      uploadFile(formData).then(res => {
        this.$message.success('上传成功！')
        this.fileList = []
        this.$refs.upload.clearFiles()
      })

    }

  }
}
</script>

<style scoped>
.upload-item{
  padding: 50px;
}

/deep/ .el-upload-list{
  width: 88%;
  display: inline-block;
}

</style>