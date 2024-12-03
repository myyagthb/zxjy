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
      alert("单个添加");
    };

    const batchAddQuestions = () => {
      alert("批量添加");
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
      addQuestion,
      batchAddQuestions,
      batchDeleteQuestions,
      editQuestion,
      deleteQuestion,
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
</style>