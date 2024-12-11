<template>
  <div id="app">
    <!-- 水平顶栏菜单 -->
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
             background-color="#FFEACC"
             text-color="black"
             active-text-color="#D09039"


             >
      <el-menu-item index="1">单选题</el-menu-item>
      <el-menu-item index="2">多选题</el-menu-item>
      <el-menu-item index="3">判断题</el-menu-item>
      <el-menu-item index="4">问答题</el-menu-item>
    </el-menu>

    <!-- 动态加载的内容区域 -->
    <component :is="currentComponent"></component>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import SingleChoice from './QuestionTypeChooseMenu/SingleChoose.vue';
import MultipleChoice from './QuestionTypeChooseMenu/SingleChoose.vue';
import TrueFalse from './QuestionTypeChooseMenu/SingleChoose.vue';
import ShortAnswer from './QuestionTypeChooseMenu/SingleChoose.vue';

// 当前选中的菜单索引
const activeIndex = ref('1');

// 定义组件映射
const componentsMap = {
  1: SingleChoice,
  2: MultipleChoice,
  3: TrueFalse,
  4: ShortAnswer
};

// 根据当前选中的菜单索引计算要显示的组件
const currentComponent = computed(() => componentsMap[activeIndex.value]);

// 处理菜单选择事件
const handleSelect = (index) => {
  activeIndex.value = index;
};
</script>

<style scoped>
/* 修改选中状态下的文字颜色 */

</style>