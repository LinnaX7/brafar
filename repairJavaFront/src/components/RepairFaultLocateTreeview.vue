<template>
  <div>
  <el-tree
      :data="infos.repairFaultLocateInfos"
      class="repair-fault-locate-tree-view"
      :props="{ class: selectClass }"
  >
    <template v-slot:default="{ node, data }">
          <span style="float: left">{{ node.label }}</span>
          <span v-if="data.metaIndex != -1 && data.label !='EMPTY_BLOCK'">
            <el-button type="primary" plain size="small" style="float: right" @click="viewTreeNode(data)">
              <el-icon>
                <View></View>
              </el-icon>
            </el-button>
        </span>
    </template>
  </el-tree>

  <el-drawer v-model="drawer" :with-header="false" size="40%">
    <el-card shadow="hover">
      <template #header>
        <div>
          <span>错误方法代码</span>
        </div>
      </template>
      <el-scrollbar>
        <div style="white-space: pre; text-align: left;" v-html="code">
        </div>
      </el-scrollbar>
    </el-card>
  </el-drawer>
  </div>
</template>

<script>
export default {
  name: "RepairFaultLocateTreeview",
  props: ["infos"],
  data(){
    return {
      drawer: false,
      code: "",
    }
  },

  methods:{
    viewTreeNode(data){
      this.setCode(data)
      this.drawer = true
    },

    setCode(data){
      console.log(data)
      this.code = this.infos.repairCodeInfos.varMatchedCode
      this.code = this.code.replace(/[<>"&]/g, function(match, pos, originalText){
        switch(match){
          case "<": return "&lt;";
          case ">":return "&gt;";
          case "&":return "&amp;";
          case "\"":return "&quot;";
        }
      })
      console.log(this.code)
      let codeLines = this.code.trim().split('\r\n');
      this.code = ""
      for (let i = 0; i < data.lines.length; ++i) {
        var newCodes = data.codes[i].replace(/[<>"&]/g, function (match, pos, originalText) {
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
        let newCodeLines = newCodes.split('\r\n')
        newCodes = newCodeLines[newCodeLines.length - 1]
        console.log(codeLines[data.lines[i] - 1])
        if (data.fault) {
          codeLines[data.lines[i] - 1] = codeLines[data.lines[i] - 1].replace(newCodes, "<span style='background: #ff8080;'>" + newCodes + "</span>")
        }
        else {
          codeLines[data.lines[i] - 1] = codeLines[data.lines[i] - 1].replace(newCodes, "<span style='background: #F7DC6F;'>" + newCodes + "</span>")
        }
      }
      for (let i = 0; i < codeLines.length; ++i){
        this.code += codeLines[i] + '\n'
      }
    },

  selectClass(data, node) {
  if (data.fault) {
    return 'fault'
  }
  return null
}
  }
}

</script>

<style scoped>
.repair-fault-locate-tree-view{
  margin-left: 150px;
  margin-right: 150px;
}
/deep/.el-tree-node__label{
  width: 100%;
}

/deep/.fault > .el-tree-node__content{
  color: red;
}
</style>