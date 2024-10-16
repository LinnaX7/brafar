<template>
  <div>
    <div>
    <el-steps :active="activeIndex" align-center finish-status="success">
      <el-step title="替代语句" @click="jumpReplace"/>
      <el-step title="插入语句" @click="jumpInsert"/>
      <el-step title="删除语句" @click="jumpDelete"/>
      <el-step title="重排序语句" @click="jumpReorder"/>
      <el-step title="语句修复结果" @click="jumpResult"/>
    </el-steps>
    </div>

    <div>
      <el-table
          v-if="activeIndex===1"
          :data="infos.repairStatementMatchInfos.replaceMatchs"
          stripe
          border
          @row-click="handleReplaceMatchTableRowClick"
      >
        <el-table-column prop="id" width="50" label="Id"/>
        <el-table-column prop="codeA" label="错误方法语句"/>
        <el-table-column prop="codeB" label="正确方法语句"/>
      </el-table>

      <el-table
          v-if="activeIndex===2"
          :data="infos.repairStatementMatchInfos.insertMatchs"
          stripe
          border
          @row-click="handleInsertMatchTableRowClick"
      >
        <el-table-column prop="id" width="50" label="Id"/>
        <el-table-column prop="codeA" label="正确方法语句"/>
      </el-table>

      <el-table
          v-if="activeIndex===3"
          :data="infos.repairStatementMatchInfos.deleteMatchs"
          stripe
          border
          @row-click="handleDeleteMatchTableRowClick"
      >
        <el-table-column prop="id" width="50" label="Id"/>
        <el-table-column prop="codeA" label="错误方法语句"/>
      </el-table>

      <el-table
          v-if="activeIndex===4"
          :data="infos.repairStatementMatchInfos.reorderMatchs"
          stripe
          border
      >
        <el-table-column prop="id" width="50" label="Id"/>
        <el-table-column prop="codeA" label="错误方法语句"/>
      </el-table>

      <code-diff
          v-if="activeIndex===5"
          class="code-diff"
          :old-string="infos.repairCodeInfos.beforeRepairingCode"
          :new-string="infos.repairCodeInfos.repairedCode"
          context="1000"
          :syncScroll=true
          :renderNothingWhenEmpty=true
          outputFormat="side-by-side"
          :isShowNoChange=true
      />
    </div>


    <el-drawer v-model="replaceDrawer" :with-header="false" size="80%">
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

    <el-drawer v-model="insertDrawer" :with-header="false" size="80%">
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

    <el-drawer v-model="deleteDrawer" :with-header="false" size="80%">
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
import {CodeDiff} from 'v-code-diff'
export default {
  name: "RepairStatementMatchTable",
  props: ["infos"],
  components: {CodeDiff},
  data(){
    return{
      activeIndex: 1,
      replaceDrawer: false,
      insertDrawer: false,
      deleteDrawer: false,
      wrongCode: "",
      correctCode: "",
    }
  },
  methods:{
    jumpReplace(){
      this.activeIndex = 1
    },

    jumpInsert(){
      this.activeIndex = 2
    },

    jumpDelete(){
      this.activeIndex = 3
    },

    jumpReorder(){
      this.activeIndex = 4
    },

    jumpResult(){
      this.activeIndex = 5
    },

    handleReplaceMatchTableRowClick(row){
      this.setReplaceMatchTableCode(row.id)
      this.replaceDrawer = true
    },

    handleInsertMatchTableRowClick(row){
      this.setInsertMatchTableCode(row.id)
      this.insertDrawer = true
    },

    handleDeleteMatchTableRowClick(row){
      this.setDeleteMatchTableCode(row.id)
      this.deleteDrawer = true
    },

    setReplaceMatchTableCode(i){
      this.wrongCode = this.infos.repairCodeInfos.beforeRepairingCode
      this.wrongCode = this.wrongCode.replace(/[<>"&]/g, function(match, pos, originalText){
        switch(match){
          case "<": return "&lt;";
          case ">":return "&gt;";
          case "&":return "&amp;";
          case "\"":return "&quot;";
        }
      })
      let wrongCodeLines = this.wrongCode.trim().split('\r\n');
      let wrongStatementLine = this.infos.repairStatementMatchInfos.replaceMatchs[i].rangeA.begin.line - 1
      let wrongStatementCode = this.infos.repairStatementMatchInfos.replaceMatchs[i].codeA.replace(/[<>"&]/g, function (match, pos, originalText) {
        switch (match) {
          case "<":
            return "&lt;";
          case ">":
            return "&gt;";
          case "&":
            return "&amp;";
          case "\"":
            return "&quot;";
        }
      })
      let wrongStatementCodeLines = wrongStatementCode.split('\r\n')
      wrongStatementCode = wrongStatementCodeLines[wrongStatementCodeLines.length - 1]
      this.wrongCode = ""
      wrongCodeLines[wrongStatementLine] = wrongCodeLines[wrongStatementLine].replace(wrongStatementCode, "<span style='background: #F7DC6F;'>"+ wrongStatementCode +"</span>")
      for (let i = 0; i < wrongCodeLines.length; ++i){
        this.wrongCode += wrongCodeLines[i] + '\n'
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
      let correctCodeLines = this.correctCode.trim().split('\r\n');
      let correctStatementLine = this.infos.repairStatementMatchInfos.replaceMatchs[i].rangeB.begin.line - 1
      let correctStatementCode = this.infos.repairStatementMatchInfos.replaceMatchs[i].codeB.replace(/[<>"&]/g, function (match, pos, originalText) {
        switch (match) {
          case "<":
            return "&lt;";
          case ">":
            return "&gt;";
          case "&":
            return "&amp;";
          case "\"":
            return "&quot;";
        }
      })
      let correctStatementCodeLines = correctStatementCode.split('\r\n')
      correctStatementCode = correctStatementCodeLines[correctStatementCodeLines.length - 1]
      this.correctCode = ""
      correctCodeLines[correctStatementLine] = correctCodeLines[correctStatementLine].replace(correctStatementCode, "<span style='background: #F7DC6F;'>"+ correctStatementCode +"</span>")
      for (let i = 0; i < correctCodeLines.length; ++i){
        this.correctCode += correctCodeLines[i] + '\n'
      }
    },

    setInsertMatchTableCode(i){
      this.wrongCode = this.infos.repairCodeInfos.beforeRepairingCode
      this.wrongCode = this.wrongCode.replace(/[<>"&]/g, function(match, pos, originalText){
        switch(match){
          case "<": return "&lt;";
          case ">":return "&gt;";
          case "&":return "&amp;";
          case "\"":return "&quot;";
        }
      })

      this.correctCode = this.infos.repairCodeInfos.correctCode
      this.correctCode = this.correctCode.replace(/[<>"&]/g, function(match, pos, originalText){
        switch(match){
          case "<": return "&lt;";
          case ">":return "&gt;";
          case "&":return "&amp;";
          case "\"":return "&quot;";
        }
      })
      let correctCodeLines = this.correctCode.trim().split('\r\n');
      let correctStatementLine = this.infos.repairStatementMatchInfos.insertMatchs[i].rangeA.begin.line - 1
      let correctStatementCode = this.infos.repairStatementMatchInfos.insertMatchs[i].codeA.replace(/[<>"&]/g, function (match, pos, originalText) {
        switch (match) {
          case "<":
            return "&lt;";
          case ">":
            return "&gt;";
          case "&":
            return "&amp;";
          case "\"":
            return "&quot;";
        }
      })
      let correctStatementCodeLines = correctStatementCode.split('\r\n')
      correctStatementCode = correctStatementCodeLines[correctStatementCodeLines.length - 1]
      this.correctCode = ""
      correctCodeLines[correctStatementLine] = correctCodeLines[correctStatementLine].replace(correctStatementCode, "<span style='background: #82E0AA;'>"+ correctStatementCode +"</span>")
      for (let i = 0; i < correctCodeLines.length; ++i){
        this.correctCode += correctCodeLines[i] + '\n'
      }
    },

    setDeleteMatchTableCode(i){
      this.wrongCode = this.infos.repairCodeInfos.beforeRepairingCode
      this.wrongCode = this.wrongCode.replace(/[<>"&]/g, function(match, pos, originalText){
        switch(match){
          case "<": return "&lt;";
          case ">":return "&gt;";
          case "&":return "&amp;";
          case "\"":return "&quot;";
        }
      })
      let wrongCodeLines = this.wrongCode.trim().split('\r\n');
      let wrongStatementLine = this.infos.repairStatementMatchInfos.deleteMatchs[i].rangeA.begin.line - 1
      let wrongStatementCode = this.infos.repairStatementMatchInfos.deleteMatchs[i].codeA.replace(/[<>"&]/g, function (match, pos, originalText) {
        switch (match) {
          case "<":
            return "&lt;";
          case ">":
            return "&gt;";
          case "&":
            return "&amp;";
          case "\"":
            return "&quot;";
        }
      })
      let wrongStatementCodeLines = wrongStatementCode.split('\r\n')
      wrongStatementCode = wrongStatementCodeLines[wrongStatementCodeLines.length - 1]
      this.wrongCode = ""
      wrongCodeLines[wrongStatementLine] = wrongCodeLines[wrongStatementLine].replace(wrongStatementCode, "<span style='background: #ff8080;'>"+ wrongStatementCode +"</span>")
      for (let i = 0; i < wrongCodeLines.length; ++i){
        this.wrongCode += wrongCodeLines[i] + '\n'
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
    },

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



.code-diff ::-webkit-scrollbar {
  -webkit-appearance: none;
  width: 5px;
  height: 8px;
}

.code-diff ::-webkit-scrollbar-thumb {
  cursor: pointer;
  border-radius: 10px;
  background: rgba(0, 0, 0, 0.15);
  transition: color 0.2s ease;
}
</style>