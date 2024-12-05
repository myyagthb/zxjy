<template>
  <div>
    <!-- 顶部搜索栏 -->
    <el-row class="filter-container" gutter="20">
      <el-col :span="6">
        <el-input v-model="filters.questionName" placeholder="请输入题目名称" />
      </el-col>
      <el-col :span="6">
        <el-select v-model="filters.status" placeholder="请选择状态" clearable>
          <el-option label="启用" value="启用" />
          <el-option label="停用" value="停用" />
        </el-select>
      </el-col>
      <el-col :span="6">
        <el-select v-model="filters.questionType" placeholder="题目类型" clearable>
          <el-option label="单选题" value="单选题" />
          <el-option label="多选题" value="多选题" />
          <el-option label="判断题" value="判断题" />
          <el-option label="简答题" value="简答题" />
        </el-select>
      </el-col>
      <el-col :span="6" class="filter-buttons">
        <el-button type="primary" @click="filterData">查询</el-button>
        <el-button @click="resetFilters">重置</el-button>
      </el-col>
    </el-row>

    <!-- 批量操作按钮 -->
    <el-row gutter="20" style="margin-bottom: 10px;">
      <el-col :span="8">
        <el-button type="primary" @click="addQuestion">单个添加</el-button>
        <el-button @click="batchAddQuestions">批量添加</el-button>
        <el-button type="danger" @click="batchDeleteQuestions">批量删除</el-button>
      </el-col>
    </el-row>

    <!-- 题目数据表格 -->
    <el-table :data="paginatedData" border style="width: 100%">
      <el-table-column type="selection" width="50" />
      <el-table-column prop="questionName" label="题目" />
      <el-table-column prop="creator" label="创建人" />
      <el-table-column prop="createDate" label="添加时间" />
      <el-table-column prop="questionType" label="题目类型" />
      <el-table-column label="状态">
        <template #default="scope">
          <el-tag :type="scope.row.status === '启用' ? 'success' : 'danger'">
            {{ scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="updateDate" label="更新时间" />
      <el-table-column prop="correctAnswer" label="正确答案" />
      <el-table-column prop="analysis" label="答案解析" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" type="text" @click="editQuestion(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="deleteQuestion(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
        layout="prev, pager, next, ->, total"
        :current-page="pagingParams.currentPage"
        :total="filteredData.length"
        :page-size="pagingParams.pageSize"
        @current-change="handlePageChange"
        style="margin-top: 20px"
    />

    <!-- 添加题目弹窗 -->
    <el-dialog
        title="添加题目"
        v-model="addDialogVisible"
        width="800px"
        @close="resetAddForm"
    >
      <el-tabs v-model="currentTab" @tab-click="handleTabChange">
        <el-tab-pane label="单选题" name="single-choice"></el-tab-pane>
        <el-tab-pane label="多选题" name="multi-choice"></el-tab-pane>
        <el-tab-pane label="判断题" name="true-false"></el-tab-pane>
        <el-tab-pane label="问答题" name="qa"></el-tab-pane>
      </el-tabs>

      <div v-if="currentTab === 'single-choice'">
        <el-form :model="newQuestion" label-width="100px">
          <el-form-item label="题目">
            <el-input v-model="newQuestion.questionName" type="textarea" />
          </el-form-item>
          <el-form-item label="选项A">
            <el-input v-model="newQuestion.options.A" />
          </el-form-item>
          <el-form-item label="选项B">
            <el-input v-model="newQuestion.options.B" />
          </el-form-item>
          <el-form-item label="选项C">
            <el-input v-model="newQuestion.options.C" />
          </el-form-item>
          <el-form-item label="选项D">
            <el-input v-model="newQuestion.options.D" />
          </el-form-item>
          <el-form-item label="正确答案">
            <el-radio-group v-model="newQuestion.correctAnswer">
              <el-radio label="A">A</el-radio>
              <el-radio label="B">B</el-radio>
              <el-radio label="C">C</el-radio>
              <el-radio label="D">D</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="解析">
            <el-input v-model="newQuestion.analysis" type="textarea" />
          </el-form-item>
        </el-form>
      </div>

      <div v-if="currentTab === 'multi-choice'">
        <el-form :model="newQuestion" label-width="100px">
          <el-form-item label="题目">
            <el-input v-model="newQuestion.questionName" type="textarea" />
          </el-form-item>
          <el-form-item label="选项A">
            <el-input v-model="newQuestion.options.A" />
          </el-form-item>
          <el-form-item label="选项B">
            <el-input v-model="newQuestion.options.B" />
          </el-form-item>
          <el-form-item label="选项C">
            <el-input v-model="newQuestion.options.C" />
          </el-form-item>
          <el-form-item label="选项D">
            <el-input v-model="newQuestion.options.D" />
          </el-form-item>
          <el-form-item label="正确答案">
            <div>
              <el-checkbox v-model="newQuestion.selectedOptions.A">A</el-checkbox>
              <el-checkbox v-model="newQuestion.selectedOptions.B">B</el-checkbox>
              <el-checkbox v-model="newQuestion.selectedOptions.C">C</el-checkbox>
              <el-checkbox v-model="newQuestion.selectedOptions.D">D</el-checkbox>
            </div>
          </el-form-item>
          <el-form-item label="解析">
            <el-input v-model="newQuestion.analysis" type="textarea" />
          </el-form-item>
        </el-form>
      </div>

      <div v-if="currentTab === 'true-false'">
        <el-form :model="newQuestion" label-width="100px">
          <el-form-item label="题目">
            <el-input v-model="newQuestion.questionName" type="textarea" />
          </el-form-item>
          <el-form-item label="答案">
            <el-radio-group v-model="newQuestion.correctAnswer">
              <el-radio label="对">对</el-radio>
              <el-radio label="错">错</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="解析">
            <el-input v-model="newQuestion.analysis" type="textarea" />
          </el-form-item>
        </el-form>
      </div>

      <div v-if="currentTab === 'qa'">
        <el-form :model="newQuestion" label-width="100px">
          <el-form-item label="题目">
            <el-input v-model="newQuestion.questionName" type="textarea" />
          </el-form-item>
          <el-form-item label="答案">
            <el-input v-model="newQuestion.correctAnswer" type="textarea" />
          </el-form-item>
          <el-form-item label="解析">
            <el-input v-model="newQuestion.analysis" type="textarea" />
          </el-form-item>
        </el-form>
      </div>

      <div class="dialog-footer">
        <el-button @click="addDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveQuestion">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { ref, computed } from "vue";

export default {
  name: "QuestionManagement",
  setup() {
    // 数据源
    const allData = ref([
      {
        questionName: "Java中的哪个关键字用于定义一个类？",
        creator: "张老师",
        createDate: "2024-03-10",
        questionType: "单选题",
        status: "启用",
        updateDate: "2024-03-10",
        correctAnswer: "B",
        analysis: "class 关键字用于定义一个类。",
      },
      {
        questionName: "以下哪些是Java集合框架中的接口？",
        creator: "王老师",
        createDate: "2024-03-10",
        questionType: "多选题",
        status: "启用",
        updateDate: "2024-03-10",
        correctAnswer: "A, B, C, D",
        analysis: "Set、List、Map和Queue都是Java集合框架中的接口。",
      },
      {
        questionName: "在Java中，null是一个表示空值的关键字。",
        creator: "李老师",
        createDate: "2024-03-10",
        questionType: "判断题",
        status: "启用",
        updateDate: "2024-03-10",
        correctAnswer: "错误",
        analysis: "null 不是一个关键字，而是一个特殊的字面量，用来表示空值。",
      },
      {
        questionName: "请简述Java中异常处理的基本概念，并说明如何使用try-catch块处理异常。",
        creator: "赵老师",
        createDate: "2024-03-10",
        questionType: "简答题",
        status: "启用",
        updateDate: "2024-03-10",
        correctAnswer: "-",
        analysis:
            "异常处理是Java用于捕获和处理程序执行中的异常情况的结构。",
      },
      {
        questionName: "在Java中，哪个方法是用于将字符串转换为字符数组的？",
        creator: "张老师",
        createDate: "2024-03-10",
        questionType: "单选题",
        status: "启用",
        updateDate: "2024-03-10",
        correctAnswer: "toCharArray()",
        analysis: "toCharArray() 方法用于将字符串转换为字符数组。",
      },
    ]);

    // 筛选条件
    const filters = ref({
      questionName: "",
      status: "",
      questionType: "",
    });

    // 分页参数
    const pagingParams = ref({
      currentPage: 1,
      pageSize: 5,
    });

    const addDialogVisible = ref(false);
    const newQuestion = ref({
      questionName: "",
      options: { A: "", B: "", C: "", D: "" },
      correctAnswer: [], // 修改为数组，支持多选题的复选框
      analysis: "",
      selectedOptions: { A: false, B: false, C: false, D: false }, // 多选选项的布尔值
    });


    // 筛选后的数据
    const filteredData = computed(() =>
        allData.value.filter((item) => {
          const matchName = filters.value.questionName
              ? item.questionName.includes(filters.value.questionName)
              : true;
          const matchStatus = filters.value.status
              ? item.status === filters.value.status
              : true;
          const matchType = filters.value.questionType
              ? item.questionType === filters.value.questionType
              : true;
          return matchName && matchStatus && matchType;
        })
    );

    // 当前分页数据
    const paginatedData = computed(() => {
      const start =
          (pagingParams.value.currentPage - 1) * pagingParams.value.pageSize;
      const end = pagingParams.value.currentPage * pagingParams.value.pageSize;
      return filteredData.value.slice(start, end);
    });

    // 方法
    const filterData = () => {
      pagingParams.value.currentPage = 1;
    };

    const resetFilters = () => {
      filters.value = {
        questionName: "",
        status: "",
        questionType: "",
      };
      filterData();
    };

    const handlePageChange = (page) => {
      pagingParams.value.currentPage = page;
    };

    const addQuestion = () => {
      addDialogVisible.value = true;
    };

    const resetAddForm = () => {
      newQuestion.value = {
        questionName: "",
        options: { A: "", B: "", C: "", D: "" },
        correctAnswer: "",
        selectedOptions: { A: false, B: false, C: false, D: false },
        analysis: "",
      };
    };

    const saveQuestion = () => {
      const selectedOptions = Object.keys(newQuestion.value.selectedOptions).filter(
          (key) => newQuestion.value.selectedOptions[key]
      );
      console.log("Saving question:", { ...newQuestion.value, selectedOptions });
      addDialogVisible.value = false;
      resetAddForm();
    };

    const batchAddQuestions = () => {
      alert("批量添加");
    };

    const currentTab = ref("single-choice");
    const handleTabChange = (tab) => {
      resetAddForm();
      currentTab.value = tab.name;
    };

    const batchDeleteQuestions = () => {
      alert("批量删除");
    };

    const editQuestion = (row) => {
      alert(`编辑题目: ${row.questionName}`);
    };

    const deleteQuestion = (row) => {
      if (confirm(`确定删除题目: ${row.questionName} 吗？`)) {
        allData.value = allData.value.filter((item) => item !== row);
      }
    };

    return {
      allData,
      filters,
      pagingParams,
      filteredData,
      paginatedData,
      filterData,
      resetFilters,
      handlePageChange,
      addDialogVisible,
      newQuestion,
      addQuestion,
      resetAddForm,
      saveQuestion,
      batchAddQuestions,
      batchDeleteQuestions,
      editQuestion,
      deleteQuestion,
      currentTab,
      handleTabChange,
    };
  },
};
</script>

<style>
.filter-container {
  margin-bottom: 10px;
}

.filter-buttons .el-button {
  margin-right: 10px;
}
.dialog-footer {
  text-align: right;
}
</style>