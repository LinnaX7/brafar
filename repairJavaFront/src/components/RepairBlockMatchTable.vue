<template>
  <el-table
      :data="infos.repairBlockMatchInfos"
      :span-method="arraySpanMethod"
      stripe
      style="width: 100%" >
    <el-table-column label="错误方法基本块">
      <el-table-column prop="wrongBlockId" label="Id" width="50px"/>
      <el-table-column prop="wrongBlockType" label="Type" width="150px"/>
      <el-table-column prop="wrongBlockCode" label="Code"/>
    </el-table-column>
    <el-table-column label="正确方法基本块">
      <el-table-column prop="correctBlockId" label="Id" width="50px"/>
      <el-table-column prop="correctBlockType" label="Type" width="150px"/>
      <el-table-column prop="correctBlockCode" label="Code"/>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "RepairBlockMatchTable",
  props: ["infos"],
  data(){
    return{
    }
  },
  methods:{
    arraySpanMethod({ row, column, rowIndex, columnIndex }) {
      const arr = this.getSpan(this.infos.repairBlockMatchInfos);
      if (columnIndex === 0 || columnIndex === 1 || columnIndex == 3 || columnIndex == 4) {
        const row = arr[rowIndex].row;
        const col = arr[rowIndex].col;
        return [row, col];
      }
    },

    getSpan(list) {
      const newArr = [];
      const obj = {};
      for (let i = 0; i < list.length; i++) {
        if (i === 0) {
          obj.row = 1;
          obj.col = 1;
          newArr.push(obj);
        } else {
          if (list[i].wrongBlockId === list[i - 1].wrongBlockId) {
            newArr.push({ row: 0, col: 0 });
            const index = list.findIndex(item => {
              return item.wrongBlockId === list[i - 1].wrongBlockId;
            });
            newArr[index].row++;
          }
          else if (list[i].correctBlockId === list[i - 1].correctBlockId) {
            newArr.push({ row: 0, col: 0 });
            const index = list.findIndex(item => {
              return item.correctBlockId === list[i - 1].correctBlockId;
            });
            newArr[index].row++;
          }
          else {
            newArr.push({ row: 1, col: 1 });
          }
        }
      }
      return newArr;
    },
  }
}
</script>

<style scoped>

</style>