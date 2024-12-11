<template>
    <div class="exam_simulation">
      <div class="header">
        <h1>考试模拟</h1>
        <p>模拟考试·配置试卷</p>
      </div>
      <div class="question_config">
        <div v-for="(option, index) in questionOptions" :key="index" class="config_row">
          <span>{{ option.name }}</span>
  
          <!-- Button to select question range -->
          <el-button
            type="primary"
            @click="toggleEditableData(index)"
          >
            选择出题范围
          </el-button>
  
          <!-- Conditional Rendering of Editable Data below the button -->
          <div v-if="isEditableVisible[index]" class="editable-data">
            <!-- Display each range (题库) below the question type -->
            <div v-for="(range, rangeIndex) in option.ranges" :key="rangeIndex" class="range-row">
              <span>{{ range.name }}</span>
              <el-input-number
                v-model="editableData[index][rangeIndex].count"
                @input="updateTotal(index, rangeIndex)"
                controls-position="right"
                :min="0"
                label="题数"
                class="input-number"
              />
              <el-input-number
                v-model="editableData[index][rangeIndex].score"
                @input="updateTotal(index, rangeIndex)"
                controls-position="right"
                :min="0"
                label="分值"
                class="input-number"
              />
              <span class="total-display">
                合计：{{ editableData[index][rangeIndex].total }} 分
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  const questionOptions = ref([
    {
      name: '单选题',
      ranges: [
        { name: 'A题库', count: 10, score: 2 },
        { name: 'B题库', count: 5, score: 4 },
      ],
    },
    {
      name: '多选题',
      ranges: [
        { name: 'C题库', count: 8, score: 3 },
        { name: 'D题库', count: 6, score: 5 },
      ],
    },{
      name: '填空题',
      ranges: [
        { name: 'E题库', count: 2, score: 4},
        { name: 'F题库', count: 5, score: 6 },
      ],
    },{
      name: '问答题',
      ranges: [
        { name: 'G题库', count: 3, score: 10 },
        { name: 'H题库', count: 5, score: 15 },
      ],
    },{
      name: '判断题',
      ranges: [
        { name: 'I题库', count: 4, score: 4 },
        { name: 'J题库', count: 6, score: 6 },
      ],
    },
  ])
  
  // 保存用户选择和编辑的数据
  const editableData = ref(
    questionOptions.value.map(option => 
      option.ranges.map(range => ({
        count: range.count,
        score: range.score,
        total: range.count * range.score,
      }))
    )
  )
  
  // 控制每个问题的编辑数据是否可见
  const isEditableVisible = ref(questionOptions.value.map(() => false))
  
  // 切换可编辑数据的显示/隐藏
  const toggleEditableData = (index) => {
    isEditableVisible.value[index] = !isEditableVisible.value[index]
  }
  
  // 更新合计分值
  const updateTotal = (index, rangeIndex) => {
    const data = editableData.value[index][rangeIndex]
    data.total = data.count * data.score
  }
  </script>
  
  <style scoped>
  .exam_simulation {
    padding: 20px;
  }
  
  .header h1 {
    text-align: center;
  }
  
  .header p {
    text-align: left;
  }
  
  .config_row {
    display: flex;
    justify-content: space-between; /* Distribute space between items */
    align-items: center; /* Vertically align items in the center */
    margin-bottom: 20px;
  }
  
  .config_row span {
    font-weight: bold;
  }
  
  .config_row button {
    margin-left: auto; /* Push button to the far right */
  }
  
  .editable-data {
    margin-top: 10px; /* Add some spacing between the button and the editable data */
    margin-left: 20px;
  }
  
  .range-row {
    margin-bottom: 10px;
    display: flex;
  }
  
  .range-row span {
    margin-right: 10px;
  }
  
  .input-number {
    width: 100px;
    margin-right: 10px;
  }
  
  .total-display {
    font-weight: bold;
  }
  </style>
  