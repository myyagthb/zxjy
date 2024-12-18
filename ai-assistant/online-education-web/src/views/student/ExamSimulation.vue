<template>
  <div class="exam_simulation">
    <div class="header">
      <h1>考试模拟</h1>
      <p>模拟考试·配置试卷</p>
    </div>

    <div class="question_config">
      <div v-for="(option, index) in questionOptions" :key="index" class="config_row">
        <span>{{ option.name }}</span>

        <el-button
          type="primary"
          @click="toggleEditableData(index)"
        >
          选择出题范围
        </el-button>

        <div v-if="isEditableVisible[index]" class="editable-data">
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

    <div class="divider-section">
      <div class="divider-line"></div>
      <div class="total-info">
        <span>共计：{{ totalQuestions }}道题</span>
        <span>共计：{{ totalScore }}分</span>
      </div>
      <div class="divider-line"></div>
    </div>

    <div class="additional-info">
      <div class="info-row">
        <span>考试时间：</span>
        <el-input-number
          v-model="examTime"
          :min="0"
          label="分钟"
          controls-position="right"
          class="input-number"
        />
        <span>分钟</span>
      </div>

      <div class="info-row">
        <span>及格分：</span>
        <el-input-number
          v-model="passingScore"
          :min="0"
          label="分"
          controls-position="right"
          class="input-number"
        />
        <span>分</span>
      </div>

      <div class="info-row">
        <span>答题模式：</span>
        <el-radio-group v-model="answerMode" label="分值">
          <el-radio label="show">做完后显示答案</el-radio>
          <el-radio label="hide">不显示答案</el-radio>
        </el-radio-group>
      </div>

      <div class="info-row">
        <span>说明：</span>
        <div class="instructions">
          <p>1. 请在规定时间内完成考试。</p>
          <p>2. 请确保每道题目都已作答。</p>
          <p>3. 题目一旦提交无法修改。</p>
          <p>4. 迟到10分钟以上将无法进入考试。</p>
        </div>
      </div>
    </div>

    <div class="bottom-buttons">
      <el-button type="primary" @click="showHistory">历史记录</el-button>
      <el-button type="success" @click="startExam">开始考试</el-button>
    </div>
  </div>

  <el-dialog title="考试模拟" v-model:visible="isExamPageVisible" width="80%">
    <div class="exam-page">
      <h2>答题卡</h2>
      <div class="answer-card">
        <div
          v-for="(question, index) in examQuestions"
          :key="index"
          :class="['question-block', { 'answered': question.answered, 'current': currentQuestionIndex === index }]"
          @click="goToQuestion(index)"
        >
          {{ index + 1 }}
        </div>
      </div>
      <div class="question-container">
        <h3>题目 {{ currentQuestionIndex + 1 }}</h3>
        <div v-if="currentQuestion" class="question-content">
          <p>{{ currentQuestion.content }}</p>
          <el-radio-group v-if="currentQuestion.type === 'single'" v-model="currentQuestion.answer">
            <el-radio v-for="(option, optionIndex) in currentQuestion.options" :key="optionIndex" :label="optionIndex">{{ option }}</el-radio>
          </el-radio-group>
          <el-checkbox-group v-else-if="currentQuestion.type === 'multiple'" v-model="currentQuestion.answer">
            <el-checkbox v-for="(option, optionIndex) in currentQuestion.options" :key="optionIndex" :label="optionIndex">{{ option }}</el-checkbox>
          </el-checkbox-group>
          <el-input v-else-if="currentQuestion.type === 'fill'" v-model="currentQuestion.answer"></el-input>
          <el-input v-else-if="currentQuestion.type === 'essay'" v-model="currentQuestion.answer" type="textarea"></el-input>
        </div>
      </div>
    </div>
    <template #footer>
      <el-button @click="isExamPageVisible = false">取消</el-button>
      <el-button type="primary" @click="submitExam">提交</el-button>
    </template>
  </el-dialog>
</template>


<script setup>
import { ref, computed } from 'vue'

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
  },
  {
    name: '填空题',
    ranges: [
      { name: 'E题库', count: 2, score: 4 },
      { name: 'F题库', count: 5, score: 6 },
    ],
  },
  {
    name: '问答题',
    ranges: [
      { name: 'G题库', count: 3, score: 10 },
      { name: 'H题库', count: 5, score: 15 },
    ],
  },
  {
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

// 计算总题数和总分
const totalQuestions = computed(() => {
  return questionOptions.value.reduce((total, option) => {
    return total + option.ranges.reduce((rangeTotal, range) => rangeTotal + range.count, 0)
  }, 0)
})

const totalScore = computed(() => {
  return questionOptions.value.reduce((total, option) => {
    return total + option.ranges.reduce((rangeTotal, range) => rangeTotal + range.count * range.score, 0)
  }, 0)
})

// 新增的状态
const examTime = ref(60)  // 默认考试时间 60 分钟
const passingScore = ref(60)  // 默认及格分数 60 分
const answerMode = ref('hide')  // 默认不显示答案

// 历史记录
const history = ref([])

const showHistory = () => {
  console.log('历史记录:', history.value)
}

// 开始考试
const isExamPageVisible = ref(false)
const examQuestions = ref([])
const currentQuestionIndex = ref(0)

const startExam = () => {
  // 生成考试题目
  examQuestions.value = generateQuestions()
  isExamPageVisible.value = true
}

const generateQuestions = () => {
  const questions = []
  questionOptions.value.forEach(option => {
    option.ranges.forEach(range => {
      for (let i = 0; i < range.count; i++) {
        const question = {
          content: `这是${option.name}的第${i + 1}题`,
          type: option.name === '单选题' ? 'single' : option.name === '多选题' ? 'multiple' : option.name === '填空题' ? 'fill' : option.name === '问答题' ? 'essay' : 'judge',
          options: ['选项A', '选项B', '选项C', '选项D'],
          answer: [],
          answered: false,
        }
        questions.push(question)
      }
    })
  })
  return questions
}

const goToQuestion = (index) => {
  currentQuestionIndex.value = index
}

const currentQuestion = computed(() => {
  return examQuestions.value[currentQuestionIndex.value]
})

const submitExam = () => {
  // 处理提交逻辑
  console.log('提交的答案:', examQuestions.value)
  isExamPageVisible.value = false
  history.value.push({ questions: examQuestions.value, time: new Date() })
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
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.config_row span {
  font-weight: bold;
}

.config_row button {
  margin-left: auto;
}

.editable-data {
  margin-top: 10px;
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

.divider-section {
  margin-top: 20px;
}

.divider-line {
  height: 1px;
  background-color: #ccc;
  margin: 10px 0;
}

.total-info {
  display: flex;
  justify-content: space-between;
  font-weight: bold;
}

.additional-info {
  margin-top: 20px;
}

.info-row {
  margin-bottom: 15px;
  display: flex;
  align-items: center;
}

.info-row span {
  margin-right: 10px;
}

.input-number,
.input-textarea {
  width: 150px;
}

.el-radio-group {
  display: inline-flex;
  margin-left: 10px;
}

.el-radio {
  margin-right: 20px;
}

.bottom-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.bottom-buttons button {
  width: 48%;
}

.exam-page {
  display: flex;
}

.answer-card {
  display: flex;
  flex-direction: column;
  margin-right: 20px;
}

.question-block {
  padding: 10px;
  margin-bottom: 5px;
  border: 1px solid #ccc;
  cursor: pointer;
}

.question-block.answered {
  background-color: green;
}

.question-block.current {
  background-color: orange;
}

.question-container {
  flex: 1;
}
</style>
