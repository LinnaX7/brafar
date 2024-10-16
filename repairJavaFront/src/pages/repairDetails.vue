<template>
  <el-container>
    <el-aside width="200px">
      <el-menu
          :default-active="activeIndex"
          mode="vertical"
          @select="handleSelect"
      >
        <el-menu-item index="1" @click="jumpRepairBlockMatch">基本块对齐</el-menu-item>
        <el-menu-item index="2" @click="jumpRepairVariableMatch">变量对齐</el-menu-item>
        <el-menu-item index="3" @click="jumpRepairFaultLocate">错误定位</el-menu-item>
        <el-menu-item index="4" @click="jumpRepairStatementMatch">修复过程</el-menu-item>
        <el-menu-item index="5" @click="jumpRepairCodeResult">修复结果</el-menu-item>
      </el-menu>
    </el-aside>
    <el-container style="height: 100%">
      <el-main>
        <el-scrollbar>
          <router-view :infos="currectInfo" />
        </el-scrollbar>
      </el-main>
      <el-footer>
        <el-pagination background layout="prev, pager, next"
                       :page-size="1"
                       :pager-count="11"
                       :total="repairResults.length"
                       hide-on-single-page
                       :current-page.sync="activeRepairResultIndex"
                       @current-change="handleCurrectChange"
                       v-if="activeIndex != '5'"
        />
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
import {getRepairResults} from "@/server/api";

export default {
  name: "repairDetails",
  data(){
    return {
      activeIndex: "1",
      activeRepairResultIndex: 1,
      repairBlockMatchInfos:[],
      repairVariableMatchInfos:[],
      repairFaultLocateInfos:[],
      repairStatementMatchInfos:[],
      repairCodeInfos: [],
      repairTreeNodeId: 1,
      currectInfo: {
        repairBlockMatchInfos:[],
        repairVariableMatchInfos:[],
        repairFaultLocateInfos:[],
        repairStatementMatchInfos:{},
        repairCodeInfos:{},
        codeResult:{}
      },
      repairResults: [],
      requestId: null,
    }
  },
  created() {
    this.requestId = this.$route.params.id
    var requestId = new FormData()
    requestId.append("requestId", this.requestId)
    getRepairResults(requestId).then(res =>{
      this.repairResults = res.data.data
      this.setRepairBlockMatchInfos()
      this.setRepairVariableMatchInfos()
      this.setRepairFaultLocateInfos()
      this.setRepairStatementMatchInfos()
      this.setRepairCodeInfos()
      this.setCurrectInfo()
      this.jumpRepairBlockMatch()
    })
  },

  methods: {

    setRepairBlockMatchInfos(){
      this.repairResults.forEach(repairResult => {
        var wrongBlocks = repairResult.wrongBlocks
        var correctBlocks = repairResult.correctBlocks
        var blockInfos = []
        for (let i = 0; i < wrongBlocks.length; ++i) {
          var wrongBlockLength = wrongBlocks[i].codes.length
          var correctBlockLength = correctBlocks[i].codes.length
          var length = Math.max(wrongBlockLength, correctBlockLength, 1)
          for (let j = 0; j < length; ++j) {
            var blockInfo = {}
            blockInfo.wrongBlockId = i;
            blockInfo.correctBlockId = i;
            blockInfo.wrongBlockType = wrongBlocks[i].type
            blockInfo.correctBlockType = correctBlocks[i].type
            if (j < wrongBlocks[i].codes.length) {
              blockInfo.wrongBlockCode = wrongBlocks[i].codes[j]
            }
            if (j < correctBlocks[i].codes.length) {
              blockInfo.correctBlockCode = correctBlocks[i].codes[j]
            }
            blockInfos.push(blockInfo)
          }
        }
        this.repairBlockMatchInfos.push(blockInfos)
      })
    },

    setRepairVariableMatchInfos(){
      this.repairResults.forEach(repairResult => {
        var wrongVars = repairResult.wrongVars
        var correctVars = repairResult.correctVars
        var varInfos = []
        for (let i = 0; i < wrongVars.length; ++i) {
          var varInfo = {}
          varInfo.wrongVarId = i
          varInfo.correctVarId = i
          varInfo.wrongVarType = wrongVars[i].type
          varInfo.correctVarType = correctVars[i].type
          varInfo.wrongVarName = wrongVars[i].name
          varInfo.correctVarName = correctVars[i].name
          varInfo.wrongVarKind = wrongVars[i].kind
          varInfo.correctVarKind = correctVars[i].kind
          varInfo.wrongVarRange = wrongVars[i].range
          varInfo.correctVarRange = correctVars[i].range
          varInfos.push(varInfo)
        }
        this.repairVariableMatchInfos.push(varInfos)
      })
    },

    setRepairFaultLocateInfos(){
      this.repairTreeNodeId = 1
      this.repairResults.forEach(repairResult => {
        var treeNodes = repairResult.treeNodes
        var treeNodeInfos = []
        for (let i = 0; i < treeNodes.length; ++i){
          treeNodeInfos.push(this.setTreeNodeInfo(treeNodes[i]))
        }
        this.repairFaultLocateInfos.push(treeNodeInfos)
      })
    },

    setTreeNodeInfo(treeNode){
      var treeNodeInfo = {}
      treeNodeInfo.id = this.repairTreeNodeId
      this.repairTreeNodeId++
      treeNodeInfo.label = treeNode.type
      treeNodeInfo.metaIndex = treeNode.metaIndex
      treeNodeInfo.lines = treeNode.lines
      treeNodeInfo.codes = treeNode.codes
      treeNodeInfo.fault = treeNode.fault
      treeNodeInfo.children = []
      for (let i = 0; i < treeNode.childrenNodes.length; ++i){
        treeNodeInfo.children.push(this.setTreeNodeInfo(treeNode.childrenNodes[i]))
      }
      return treeNodeInfo
    },

    setRepairStatementMatchInfos(){
      this.repairResults.forEach(repairResult => {
        var statementMatchInfos = {}
        statementMatchInfos.replaceMatchs = repairResult.replaceMatchs
        statementMatchInfos.insertMatchs = repairResult.insertMatchs
        statementMatchInfos.deleteMatchs = repairResult.deleteMatchs
        statementMatchInfos.reorderMatchs = repairResult.reorderMatchs
        for (let i = 0; i < statementMatchInfos.replaceMatchs.length; ++i){
          statementMatchInfos.replaceMatchs[i].id = i
        }
        for (let i = 0; i < statementMatchInfos.insertMatchs.length; ++i){
          statementMatchInfos.insertMatchs[i].id = i
        }
        for (let i = 0; i < statementMatchInfos.deleteMatchs.length; ++i){
          statementMatchInfos.deleteMatchs[i].id = i
        }
        for (let i = 0; i < statementMatchInfos.reorderMatchs.length; ++i){
          statementMatchInfos.reorderMatchs[i].id = i
        }
        this.repairStatementMatchInfos.push(statementMatchInfos)
      })
    },

    setRepairCodeInfos(){
      this.repairResults.forEach(repairResult => {
        var codeInfos = {}
        codeInfos.wrongCode = repairResult.wrongCode
        codeInfos.correctCode = repairResult.correctCode
        codeInfos.varMatchedCode = repairResult.varMatchedCode
        codeInfos.beforeRepairingCode = repairResult.beforeRepairingCode
        codeInfos.repairedCode = repairResult.repairedCode
        this.repairCodeInfos.push(codeInfos)
      })
    },

    setCurrectInfo(){
      this.currectInfo.repairBlockMatchInfos = this.repairBlockMatchInfos[this.activeRepairResultIndex - 1]
      this.currectInfo.repairVariableMatchInfos = this.repairVariableMatchInfos[this.activeRepairResultIndex - 1]
      this.currectInfo.repairFaultLocateInfos = this.repairFaultLocateInfos[this.activeRepairResultIndex - 1]
      this.currectInfo.repairStatementMatchInfos = this.repairStatementMatchInfos[this.activeRepairResultIndex - 1]
      this.currectInfo.repairCodeInfos = this.repairCodeInfos[this.activeRepairResultIndex - 1]
      this.currectInfo.codeResult.wrongCode = this.repairCodeInfos[0].wrongCode
      this.currectInfo.codeResult.repairedCode = this.repairCodeInfos[this.repairResults.length - 1].repairedCode
    },

    handleCurrectChange(currentPage){
      this.activeRepairResultIndex = currentPage
      this.currectInfo.repairBlockMatchInfos = this.repairBlockMatchInfos[this.activeRepairResultIndex - 1]
      this.currectInfo.repairVariableMatchInfos = this.repairVariableMatchInfos[this.activeRepairResultIndex - 1]
      this.currectInfo.repairFaultLocateInfos = this.repairFaultLocateInfos[this.activeRepairResultIndex - 1]
      this.currectInfo.repairStatementMatchInfos = this.repairStatementMatchInfos[this.activeRepairResultIndex - 1]
      this.currectInfo.repairCodeInfos = this.repairCodeInfos[this.activeRepairResultIndex - 1]
    },

    jumpRepairBlockMatch(){
      this.activeIndex = "1"
      this.$router.push('/repairDetails/' + this.requestId + '/blockMatch')
    },

    jumpRepairVariableMatch(){
      this.activeIndex = "2"
      this.$router.push('/repairDetails/'+ this.requestId + '/variableMatch')
    },

    jumpRepairFaultLocate(){
      this.activeIndex = "3"
      this.$router.push('/repairDetails/' + this.requestId + '/faultLocate')
    },

    jumpRepairStatementMatch(){
      this.activeIndex = "4"
      this.$router.push('/repairDetails/' + this.requestId + '/statementMatch')
    },

    jumpRepairCodeResult(){
      this.activeIndex = "5"
      this.$router.push('/repairDetails/' + this.requestId + '/codeResult')
    },

  }
}
</script>

<style scoped>
/deep/ .el-table .cell{
  white-space: pre-line;
}
.el-pagination{
  justify-content: center;
}
</style>