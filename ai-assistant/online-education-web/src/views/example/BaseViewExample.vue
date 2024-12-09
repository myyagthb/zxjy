<template>
  <div class="app-container">
    <BaseHeader></BaseHeader>
    <div class="base-main" :style="{ height: tableHeight + 'px' }">
      表格内容<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
      1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
      <BaseFooter></BaseFooter>
    </div>
  </div>
</template>
<script setup>
import {ref, onMounted, onUnmounted, computed} from 'vue';

import BaseHeader from "@/components/BaseHeader.vue";
import BaseFooter from "@/components/BaseFooter.vue";
const isDragging = ref(false);
const leftWidth = ref(200);
const showQueryDiv = ref(true);

const tableHeight = computed(() => {
  const totalHeight = window.innerHeight;
  const buttonHeight = 30;
  const pageHeight = 50; // 调整分页的高度
  const queryHeight = showQueryDiv.value ? 100 : 0;
  return totalHeight - buttonHeight - pageHeight - queryHeight;
});

const onDrag = (e) => {
  if (isDragging.value) {
    const minWidth = 50;
    const maxWidth = window.innerWidth - 50;
    const newLeftWidth = e.clientX;
    if (newLeftWidth > minWidth && newLeftWidth < maxWidth) {
      leftWidth.value = newLeftWidth;
    }
  }
};

onMounted(() => {
  window.addEventListener("resize", onDrag);
});

onUnmounted(() => {
  window.removeEventListener("resize", onDrag);
});
</script>

<style scoped>
.app-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  overflow: hidden;
}

.base-main {
  width: 100%;
  flex: 1; /* 让表格区域能够填满剩余的空间 */
  overflow-x: auto;
  overflow-y: auto;
  background-color: #f5f5f5;
}
</style>