<template>
  <el-container>
    <el-aside width="200px">
      <el-menu
          :default-active="activeIndex"
          mode="vertical"
          @select="handleSelect"
      >
        <el-menu-item index="1">主控制结构节点匹配</el-menu-item>
        <el-menu-item index="2">主控制结构重构对齐</el-menu-item>
        <el-menu-item index="3">非主控制结构节点匹配</el-menu-item>
        <el-menu-item index="4">控制结构重构对齐结果</el-menu-item>
      </el-menu>
    </el-aside>
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
        <el-select v-model="selectedUploadFile" :disabled="selectedProblem==''" placeholder="Select" size="large" style="width: 300px;margin-right: 50px">
          <el-option
              v-for="item in uploadFiles"
              :key="item.id"
              :label="item.name"
              :value="item.id"
          >
            <span style="float: left">{{ item.name }}</span>
            <span style="float: right;color: var(--el-text-color-secondary);font-size: 13px;">
              {{ item.id }}
            </span>
          </el-option>
        </el-select>
        <el-button type="primary" @click="getRefactorControlStructure" size="large" :disabled="selectedUploadFile==''">
          提交
        </el-button>
      </div>
      <refactor-control-structure-graph
          ref="child"
          control-structure-graph-id="controlStructureGraph"
      >
      </refactor-control-structure-graph>
    </el-main>
  </el-container>
</template>

<script>
import RefactorControlStructureGraph from "@/components/RefactorControlStructureGraph";
import {getProblems, getUploadFilesByProblemId, refactorControlStructure} from "@/server/api";
export default {
  name: "refactor",
  components: {RefactorControlStructureGraph},

  data(){
    return{
      problems: [],
      uploadFiles: [],
      selectedProblem:"",
      selectedUploadFile:"",
      activeIndex:"1",
      legalMappingGraphInfo: {},
      mainRefactorGraphInfo: {},
      localMappingGraphInfo: {},
      finalRefactorGraphInfo: {}
    }
  },


  created() {
    getProblems().then(res =>{
      this.problems = res.data.data
    })
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

  methods:{
    handleSelect(index){
      this.activeIndex = index
      switch (this.activeIndex){
        case "1":
          this.drawLegalMappingGraph()
          break
        case "2":
          this.drawMainRefactorGraph()
          break
        case "3":
          this.drawLocalMappingGraph()
          break
        case "4":
          this.drawFinalRefactorGraph()
          break
      }
    },

    getRefactorControlStructure(){
      var uploadFileId = new FormData();
      uploadFileId.append("uploadFileId", this.selectedUploadFile)
      refactorControlStructure(uploadFileId).then(res => {
        this.activeIndex = "1"
        this.getAllGraphInfo(res.data.data)
        this.drawLegalMappingGraph()
      })
    },

    getAllGraphInfo(data){
      this.legalMappingGraphInfo = this.getGraphInfo(data.wrongLegalMappingControlStructure, data.correctLegalMappingControlStructure)
      this.mainRefactorGraphInfo = this.getGraphInfo(data.wrongMainRefactorControlStructure, data.correctMainRefactorControlStructure)
      this.localMappingGraphInfo = this.getGraphInfo(data.wrongLocalMappingControlStructure, data.correctLocalMappingControlStructure)
      this.finalRefactorGraphInfo = this.getGraphInfo(data.wrongFinalRefactorControlStructure, data.correctFinalRefactorControlStructure)
    },

    getGraphInfo(controlStructure1, controlStructure2){
      var graphInfo = {
        nodes: [],
        edges: [],
        matchNodes: []
      };

      function dfsBuild(controlStructure){
        var node = {}
        node.id = controlStructure.id
        node.label = controlStructure.type
        node.type = "common"
        node.matchType = "common"
        if (controlStructure.controlStructure){
          node.type = "controlStructure"
          node.matchType = "controlStructure"
        }
        if (controlStructure.insert){
          node.type = "insert"
        }
        if (controlStructure.move){
          node.type = "move"
        }
        var matchNode = {}
        if (controlStructure.matchId !== "-1") {
          matchNode.source = node.id;
          matchNode.target = controlStructure.matchId
          if (!controlStructure.controlStructure){
            node.matchType = "local"
          }
          matchNode.matchType = node.matchType
          graphInfo.matchNodes.push(matchNode)
        }
        graphInfo.nodes.push(node)
        controlStructure.children.forEach(child => {
          var edge = {}
          edge.source = node.id
          edge.target = child.id
          graphInfo.edges.push(edge)
          dfsBuild(child)
        })
      }

      dfsBuild(controlStructure1)
      dfsBuild(controlStructure2)
      return graphInfo
    },

    drawLegalMappingGraph(){
      this.$refs.child.drawMappingGraph(this.legalMappingGraphInfo)
      this.$refs.child.drawLegalMatchPath(this.legalMappingGraphInfo.matchNodes)
    },

    drawLocalMappingGraph(){
      this.$refs.child.drawMappingGraph(this.localMappingGraphInfo)
      this.$refs.child.drawLocalMatchPath(this.localMappingGraphInfo.matchNodes)
    },

    drawMainRefactorGraph(){
      this.$refs.child.drawRefactorGraph(this.mainRefactorGraphInfo)
    },

    drawFinalRefactorGraph(){
      console.log(this.finalRefactorGraphInfo)
      this.$refs.child.drawRefactorGraph(this.finalRefactorGraphInfo)
    },

  }
}
</script>

<style scoped>

</style>