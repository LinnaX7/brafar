<template>
  <code-diff
      class="code-diff"
      :old-string="infos.codeResult.wrongCode"
      :new-string="infos.codeResult.repairedCode"
      context="1000"
      :syncScroll=true
      :renderNothingWhenEmpty=true
      outputFormat="side-by-side"
      :isShowNoChange=true
  />
</template>

<script>
import {CodeDiff} from "v-code-diff";

export default {
  name: "RepairCodeResult",
  props: ["infos"],
  components: {CodeDiff},
}
</script>

<style scoped>
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