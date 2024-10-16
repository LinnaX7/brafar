<template>
  <el-container>
    <el-main>
      <div>
        <el-select v-model="selectedProblem" placeholder="Select" size="large">
          <el-option
              v-for="item in problems"
              :key="item.id"
              :label="item.className"
              :value="item.id"
          >
            <span style="float: left">{{ item.className }}</span>
            <span style="float: right;color: var(--el-text-color-secondary);font-size: 13px;">
              {{ item.id }}
            </span>
          </el-option>
        </el-select>
        <el-select v-model="selectedUploadFile" :disabled="selectedProblem==''" placeholder="Select" size="large"
                   style="width: 300px;margin-right: 50px">
          <el-option
              v-for="item in uploadFiles"
              :key="item.id"
              :label="item.name"
              :value="item.id"
              :disabled="!item.refactored"
          >
            <span style="float: left">{{ item.name }}</span>
            <span style="float: right;color: var(--el-text-color-secondary);font-size: 13px;">
              {{ item.id }}
            </span>
          </el-option>
        </el-select>
        <el-button type="primary" @click="createRepairRequest" size="large" :disabled="selectedUploadFile==''">
          提交
        </el-button>
      </div>
      <div>
        <el-scrollbar>
          <el-table
              :data="requests"
              style="width: 80%; display: inline-block;margin-top: 30px"
              @row-click="handleRowClick"
              border
              stripe
          >
            <el-table-column prop="id" label="序号" width="100px"/>
            <el-table-column prop="problemId" label="题目序号" width="100px"/>
            <el-table-column prop="problemName" label="题目"/>
            <el-table-column prop="uploadFileId" label="文件序号" width="100px"/>
            <el-table-column prop="uploadFileName" label="文件名"/>
            <el-table-column prop="timeStamp" label="时间戳"/>
            <el-table-column prop="repairTime" label="耗时(s)" width="100px"/>
            <el-table-column prop="status" label="状态" width="100px">
              <template #default="scope">
                <el-tag
                    :type="scope.row.status === 'repairing' ? '' : 'success'"
                >
                  {{ scope.row.status }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="result" label="修复结果" width="100px">
              <template #default="scope">
                <el-tag
                    :type="returnTagType(scope.row.result)"
                >
                  {{ returnTagValue(scope.row.result) }}
                </el-tag>
              </template>
            </el-table-column>
          </el-table>
        </el-scrollbar>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import {getProblems, getUploadFilesByProblemId, createRepairRequest, getRequests} from "@/server/api";

export default {
  name: "repair",
  data() {
    return {
      problems: [],
      uploadFiles: [],
      requests: [],
      selectedProblem:"",
      selectedUploadFile:"",
    }
  },
  created() {
    getProblems().then(res =>{
      this.problems = res.data.data
    })

    getRequests().then(res =>{
      this.requests = res.data.data
    })

    setInterval(() =>{
      getRequests().then(res =>{
        this.requests = res.data.data
      })
    }, 30000);

  },


  watch:{
    selectedProblem(newValue, oldValue){
      this.selectedUploadFile=""
      var problemId = new FormData()
      problemId.append("problemId", newValue)
      getUploadFilesByProblemId(problemId).then(res => {
        this.uploadFiles = res.data.data
      })
    }
  },
  methods: {
    createRepairRequest(){
      var uploadFileId = new FormData();
      uploadFileId.append("uploadFileId", this.selectedUploadFile)
      createRepairRequest(uploadFileId).then(res =>{
        getRequests().then(res =>{
          this.requests = res.data.data
        })
      })
    },

    handleRowClick(row){
      console.log(row)
      if (row.status === 'repairing'){
        this.$message('正在修复！')
      }
      if (row.status === 'finished') {
        this.$router.push({
          name: "repairDetails",
          params: {id: row.id}
        })
      }
    },

    returnTagType(result){
      if (result === 0){
        return 'danger'
      }
      else if (result === 1){
        return 'success'
      }
      else {
        return ''
      }
    },

    returnTagValue(result){
      if (result === 0){
        return 'false'
      }
      else if (result === 1){
        return 'true'
      }
      else {
        return 'unkown'
      }
    }

  }
}
</script>

<style scoped>

</style>