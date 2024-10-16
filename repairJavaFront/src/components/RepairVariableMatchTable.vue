<template>
  <div>
    <el-table
        :data="infos.repairVariableMatchInfos"
        stripe
        highlight-current-row
        @row-click="handleRowClick"
    >
      <el-table-column label="错误方法变量">
        <el-table-column prop="wrongVarId" label="Id"/>
        <el-table-column prop="wrongVarType" label="Type"/>
        <el-table-column prop="wrongVarName" label="Name"/>
        <el-table-column prop="wrongVarKind" label="Kind"/>
      </el-table-column>
      <el-table-column label="正确方法变量">
        <el-table-column prop="correctVarId" label="Id"/>
        <el-table-column prop="correctVarType" label="Type"/>
        <el-table-column prop="correctVarName" label="Name"/>
        <el-table-column prop="correctVarKind" label="Kind"/>
      </el-table-column>
    </el-table>

    <el-drawer v-model="drawer" :with-header="false" size="80%">
      <el-row :gutter="10">
        <el-col :span="12">
          <el-card shadow="hover">
            <template #header>
              <div>
                <span>错误方法代码</span>
              </div>
            </template>
            <el-scrollbar>
              <div style="white-space: pre; text-align: left;" v-html="wrongCode">
              </div>
            </el-scrollbar>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card shadow="hover">
            <template #header>
              <div>
                <span>正确方法代码</span>
              </div>
            </template>
            <el-scrollbar>
              <div style="white-space: pre; text-align: left;" v-html="correctCode">
              </div>
            </el-scrollbar>
          </el-card>
        </el-col>
      </el-row>
    </el-drawer>
  </div>
</template>

<script>
export default {
  name: "RepairVariableMatchTable",
  props: ["infos"],
  data() {
    return {
      drawer: false,
      wrongCode: "",
      correctCode: "",
      currentId: ""
    }
  },

  methods:{
    setCode(i){
      this.wrongCode = this.infos.repairCodeInfos.wrongCode

      this.wrongCode = this.wrongCode.replace(/[<>"&]/g, function(match, pos, originalText){
        switch(match){
          case "<": return "&lt;";
          case ">":return "&gt;";
          case "&":return "&amp;";
          case "\"":return "&quot;";
        }
      })
      let wrongVarCodeLines = this.wrongCode.trim().split('\r\n');

      let wrongVarLine = this.infos.repairVariableMatchInfos[i].wrongVarRange.begin.line - 1
      let wrongVarColumn = this.infos.repairVariableMatchInfos[i].wrongVarRange.begin.column
      if (this.infos.repairVariableMatchInfos[i].wrongVarKind === "PARAM"){
        wrongVarColumn += this.infos.repairVariableMatchInfos[i].wrongVarType.length
      }
      let wrongVarName = this.infos.repairVariableMatchInfos[i].wrongVarName
      this.wrongCode = ""
      wrongVarCodeLines[wrongVarLine] = wrongVarCodeLines[wrongVarLine].substring(0, wrongVarColumn - 1) + wrongVarCodeLines[wrongVarLine].substring(wrongVarColumn - 1).replace(wrongVarName, "<span style='background: #F7DC6F;'>"+ wrongVarName +"</span>")

      for (let i = 0; i < wrongVarCodeLines.length; ++i){
        this.wrongCode += wrongVarCodeLines[i] + '\n'
      }
      this.correctCode = this.infos.repairCodeInfos.correctCode
      this.correctCode = this.correctCode.replace(/[<>"&]/g, function(match, pos, originalText){
        switch(match){
          case "<": return "&lt;";
          case ">":return "&gt;";
          case "&":return "&amp;";
          case "\"":return "&quot;";
        }
      })
      let correctVarCodeLines = this.correctCode.trim().split('\r\n');
      let correctVarLine = this.infos.repairVariableMatchInfos[i].correctVarRange.begin.line - 1
      let correctVarColumn = this.infos.repairVariableMatchInfos[i].correctVarRange.begin.column
      if (this.infos.repairVariableMatchInfos[i].correctVarKind === "PARAM"){
        correctVarColumn += this.infos.repairVariableMatchInfos[i].correctVarType.length
      }
      let correctVarName = this.infos.repairVariableMatchInfos[i].correctVarName
      this.correctCode = ""
      correctVarCodeLines[correctVarLine] = correctVarCodeLines[correctVarLine].substring(0, correctVarColumn - 1) + correctVarCodeLines[correctVarLine].substring(correctVarColumn - 1).replace(correctVarName, "<span style='background: #F7DC6F;'>"+ correctVarName +"</span>")
      for (let i = 0; i < correctVarCodeLines.length; ++i){
        this.correctCode += correctVarCodeLines[i] + '\n'
      }
    },

    handleRowClick(row){
        this.setCode(parseInt(row.wrongVarId))
        this.drawer = true
    }

  }
}
</script>

<style scoped>
.el-card{
  min-width: 100%;
  height: 100%;
}

/deep/ .el-card__body{
  height: 94%;
}
</style>