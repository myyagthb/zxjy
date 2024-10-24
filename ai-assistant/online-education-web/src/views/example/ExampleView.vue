<template>
  <div class="app-container">
    <div class="left" :style="{ width: leftWidth + 'px' }">
      左边的内容，可以很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长<br />
      1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
      1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
      1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
      1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
      1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
      1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
    </div>
    <div class="divider" @mousedown="startDragging"></div>
    <div class="right">
      <div v-if="showQueryDiv" class="right-query">搜索条件</div>
      <div class="right-button">
        <div class="right-button-left">操作按钮</div>
        <div class="right-button-right">
          <button @click="toggleQueryDiv">隐藏/展示 搜索条件</button>
        </div>
      </div>
      <div class="right-table" :style="{ height: tableHeight + 'px' }">
        表格内容<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
        1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
        1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
        1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
        1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
        1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
      </div>
      <div class="right-page">分页内容</div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue';

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

const startDragging = (e) => {
  console.log(e)
  isDragging.value = true;
  document.addEventListener("mousemove", onDrag);
  document.addEventListener("mouseup", stopDragging);
};

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

const stopDragging = () => {
  isDragging.value = false;
  document.removeEventListener("mousemove", onDrag);
  document.removeEventListener("mouseup", stopDragging);
};

const toggleQueryDiv = () => {
  showQueryDiv.value = !showQueryDiv.value;
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
  display: flex;
  height: 100vh;
  overflow: hidden;
}

.left {
  overflow-x: auto;
  overflow-y: auto;
  white-space: nowrap;
  min-width: 90px;
}

.divider {
  width: 5px;
  cursor: ew-resize;
  background-color: #ccc;
}

.right {
  display: flex;
  flex-direction: column;
  height: 100%;
  flex: 1;
}

.right-query {
  height: 100px;
  background-color: #f0f0f0;
}

.right-button {
  height: 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #ddd;
}

.right-button-left {
  margin-left: 5px;
  text-align: left;
}

.right-button-right {
  margin-right: 5px;
  text-align: right;
}

.right-table {
  flex: 1; /* 让表格区域能够填满剩余的空间 */
  overflow-x: auto;
  overflow-y: auto;
  background-color: #f5f5f5;
}

.right-page {
  height: 50px; /* 设置分页区域的固定高度 */
  background-color: #ccc;
}
</style>