<template>
  <!--标题*******************************************************************************************-->
  <div class="title">试题管理</div>
  <!--搜索框*******************************************************************************************-->
  <div class="homeSearch">
    <p class="selectTextName">题目名称</p>
    <el-input v-model="questionNameValue" style="width: 240px" placeholder="请输入题目名称" ref="myInput" input-class="123"/>
    <p class="selectTextStatus">题目状态</p>
    <el-input v-model="questionStatusValue" style="width: 240px" placeholder="题目状态" ref="myInput"/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <el-button color="#D09039" :style="{ color: 'white' }" type="warning" :icon="Search" @click="questionSelect">查询</el-button>
    <el-button color="#eeeeee" :icon="RefreshLeft" @click="selectReset">重置</el-button>
  </div>
  <!--增删按钮*******************************************************************************************-->
  <div class="adOperation">
    <div class="leftButton">
      <el-button color="#D09039" :style="{ color: 'white' }" type="warning" @click="addOne">单个添加</el-button>
      <el-button @click="addBatch" >批量添加</el-button>
      <el-button>批量删除</el-button>
    </div>
    <div class="rightButton">
      <el-button :style="{ color: '#D09039' }" :icon="Finished" text>
        审阅题目
      </el-button>
    </div>
  </div>
  <!--增加题目*******************************************************************************************-->
  <el-dialog v-model="addOneDialog"  width="90%">
    <div class="questionTypeChoose">
      <component :is="QuestionBankManagementView"></component>

    </div>
    <div class="ackButton">
      <div class="cancel" @click = "cancel">取消</div>
      <div class="confirm" @click = "confirm">确认</div>
    </div>
  </el-dialog>
  <!--试题详情*******************************************************************************************-->
  <div class="testDetails">
    <el-table :data="testDetailsDate" style="width: 100%">
      <el-table-column prop="question" label="题目"  width="300px"/>
      <el-table-column prop="author" label="创建人"   />
      <el-table-column prop="addTime" label="添加时间"   />

      <el-table-column prop="type" label="题目类型" >
        <template #default="scope">
          <span :class="getQuestionTypeClass(scope.row.type)">
            {{ scope.row.type }}
          </span>
        </template>
      </el-table-column>
<!--      <el-table-column prop="type" label="题目类型" >
        <template #default="scope">
        <span style="color: red" @click = getQuestionTypeClass(scope.row.questionType)>
          选择题
        </span>
        </template>
      </el-table-column>-->

      <el-table-column prop="status" label="状态"  />
      <el-table-column prop="updateTime" label="更新时间"   />
      <el-table-column prop="answer" label="正确答案"   />
      <el-table-column prop="answerAnalysis" label="答案解析"   width="180px"/>
      <el-table-column label="操作" width="150px">
        <template #default="scope" >
          <div class="tableOperation">
            <p style="color: #D09039" class="tableOperation-click" @click="handleEdit(scope.$index, scope.row)">编辑</p>
            <p style="color: #D09039" class="tableOperation-p">|</p>
            <p style="color: #808080" class="tableOperation-click" @click="handleDelete(scope.$index, scope.row)">删除</p>
          </div>
        </template>
      </el-table-column>
    </el-table>


  </div>
  <!--分页*******************************************************************************************-->
  <div class="pagination ">
    <div class="example-pagination-block">
      <div class="example-demonstration"></div>
      <el-pagination background layout="prev, pager, next" :total="50" />
    </div>
  </div>
</template>





















<script  setup >
/*资源导入************************************************************************************************/
import { ref } from 'vue';
import { Search, RefreshLeft,Finished} from '@element-plus/icons-vue'
import QuestionBankManagementView from "@/views/teacher/QuestionBankManagementView.vue";
/*import QuestionTypeChoose from './QuestionTypeChooseMenu/QuestionTypeChooseMenu.vue';*/
/*搜索框*************************************************************************************************/
const questionSelect = () => {
  alert('搜索');
};
// 定义响应式的变量来存储输入框的值
const questionNameValue = ref('');
const questionStatusValue = ref('');
// 方法：点击按钮时重置输入框的内容
const selectReset = () => {
  questionNameValue.value = ''; // 将输入框的值设置为空字符串
  questionStatusValue.value = ''; // 将输入框的值设置为空字符串
};
/*增删按钮************************************************************************************************/
const addBatch = () => {
  alert('批量添加');
};
/*添加题目*/
const addOneDialog = ref(false)
const addOne = () => {
  addOneDialog.value = true; // 设置为true以显示对话框
}

/*试题详情************************************************************************************************/
//题目类型颜色的变化
const getQuestionTypeClass = (questionType) => {
  if (questionType === '单选题') {
    return 'green-text';
  } else if (questionType === '判断题') {
    return 'red-text';
  }else if (questionType === '多选题'){
    return 'yellow-text';
  }else {
    return 'orange-text';
  }
};

//创建列表行对象
/*interface Question{
  question: string
  author: string
  addTime: string
  type: string
  status: string
  updateTime: string
  answer: string
  answerAnalysis: string
  operation: string
}*/
// 表格数据
const testDetailsDate = [
  {
    question: 'java中的哪一个关键字用于定义一个类？',
    author: '张老师',
    addTime: '2024-03-10',
    type: '简答题',
    status: '启用',
    updateTime: '2024-03-10',
    answer: 'B',
    answerAnalysis: 'Class关键字用于定义一个类',
  },
]
// 事件绑定
/*const handleEdit = (index: number, row: User) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: User) => {
  console.log(index, row)
}*/
/*分页************************************************************************************************/

</script>



















<style scoped>
/**************************标题**************************/
.title{
  font-size: 20px;
  font-weight: bold;
  text-align: left;
  margin: 0 0 20px 0;
}
/**************************搜索框**************************/
.homeSearch{
  color: #808080;
  display: flex;
  align-items: center; /*垂直方向上居中对齐*/
  margin: 20px 0 20px 0;
}
.selectTextName{
  margin: 0 10px 0 0;
}
.selectTextStatus{
  margin: 0 10px 0 30px;
}
/**************************增删按钮**************************/
.adOperation{
  display: flex;
}
.rightButton{
  margin-left: auto; /* 将右边的按钮推到最右侧 */
}
/**************************添加题目视图**************************/
.ackButton{
  display: flex;
  gap: 0;
}
.cancel{
  width: 80px;
  color: #D09039;
  border: 1px solid #D09039; /* 设置边框的宽度、样式和颜色 */
  border-radius: 15px;     /* 设置圆角的半径 */
  margin: 5px 5px 5px 5px;
  padding:  2px 0 2px 0;
}
.confirm{
  width: 80px;
  color: white;
  background: #D09039;
  border: 1px solid #D09039; /* 设置边框的宽度、样式和颜色 */
  border-radius: 15px;     /* 设置圆角的半径 */
  margin: 5px 5px 5px 5px;
  padding:  2px 0 2px 0;
}
.cancel:hover {
  color: black;
}

.confirm:hover {
  color: black;
}
/**************************试题详情**************************/
.testDetails{
  margin: 0 -10px 0 -10px;
}
.tableOperation{
  display: flex;
}
.tableOperation-p{
  margin: 12px 5px 0 5px;
}
.tableOperation-click{
  cursor: pointer;
}
.green-text{
  color: green;
}
.red-text{
  color: red;
}
.yellow-text{
  color: #a8924c;
}
.orange-text{
  color: #c25454;
}
/**************************分页****************************/
/* 使用position: fixed;将元素固定在视口的底部右侧 */
.pagination {
  display: flex;
  margin: 30px 0 0 0;

}
.example-pagination-block  {
  margin-left: auto;
}
</style>
