<template>
  <div>

    <!-- 顶部搜索区域 -->
    <el-row class="filter-container" gutter="20">
      <el-col :span="6">
        <el-input v-model="filters.name" placeholder="请输入题库名称" />
      </el-col>
      <el-col :span="6">
        <el-select v-model="filters.status" placeholder="请选择状态" clearable>
          <el-option label="启用" value="启用" />
          <el-option label="停用" value="停用" />
        </el-select>
      </el-col>
      <el-col :span="6">
        <el-input v-model="filters.creator" placeholder="请输入创建人" />
      </el-col>
      <el-col :span="6" class="filter-buttons">
        <el-button type="primary" @click="filterData">查询</el-button>
        <el-button @click="resetFilters">重置</el-button>
      </el-col>
      <el-button type="primary" icon
          ="el-icon-plus" @click="addQuestionBank">添加题库</el-button>
    </el-row>

    <!-- 表格展示 -->
    <el-table :data="paginatedData" border style="width: 100%">
      <el-table-column type="selection" width="50" />
      <el-table-column prop="name" label="题库名称" />
      <el-table-column prop="category" label="题库分类" />
      <el-table-column prop="questions" label="试题数" />
      <el-table-column prop="creator" label="创建人" />
      <el-table-column label="状态">
        <template #default="scope">
          <el-tag :type="scope.row.status === '启用' ? 'success' : 'danger'">
            {{ scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="updated" label="更新时间" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" type="text" @click="viewDetails(scope.row)">关联题目</el-button>
          <el-button size="small" type="text" @click="editQuestionBank(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="deleteQuestionBank(scope.row)">
            删除
          </el-button>
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
  name: "QuestionBankManagement",
  setup() {
    // 已选试题
    const selectedQuestions = ref([]);

    // 可选试题
    const availableQuestions = ref([
      { questionName: "题目1", type: "单选题", status: "启用", correctAnswer: "A", analysis: "解析1" },
      { questionName: "题目2", type: "多选题", status: "启用", correctAnswer: "A,B", analysis: "解析2" },
      { questionName: "题目3", type: "判断题", status: "启用", correctAnswer: "正确", analysis: "解析3" },
    ]);

    // 分页与搜索
    const searchTerm = ref("");
    const currentPage = ref(1);
    const pageSize = ref(5);
    const totalQuestions = ref(availableQuestions.value.length);

    // 方法
    const searchQuestions = () => {
      // 模拟搜索逻辑
      availableQuestions.value = availableQuestions.value.filter((item) =>
          item.questionName.includes(searchTerm.value)
      );
    };


    const addSingleQuestion = (question) => {
      if (!selectedQuestions.value.some((q) => q.questionName === question.questionName)) {
        selectedQuestions.value.push(question);
      }
    };

    const removeSingleQuestion = (question) => {
      selectedQuestions.value = selectedQuestions.value.filter((q) => q !== question);
    };

    const removeSelectedQuestions = () => {
      selectedQuestions.value = [];
    };

    const cancelLinking = () => {
      alert("取消关联操作");
    };

    const confirmLinking = () => {
      alert("题目关联成功");
    };
    // 数据源
    const allData = ref([
      {
        name: "Java编程技能提升题库",
        category: "软件开发",
        questions: 300,
        creator: "张老师",
        status: "启用",
        updated: "2024-02-19 05:29",
      },
      {
        name: "前端开发与设计实践题库",
        category: "前端开发",
        questions: 250,
        creator: "王教授",
        status: "停用",
        updated: "2023-05-01 10:32",
      },
      // 其他模拟数据
    ]);

    // 筛选条件
    const filters = ref({
      name: "",
      status: "",
      creator: "",
    });

    // 分页参数
    const pagingParams = ref({
      currentPage: 1,
      pageSize: 5,
    });

    // 计算筛选后的数据
    const filteredData = computed(() =>
        allData.value.filter((item) => {
          const matchName = filters.value.name
              ? item.name.includes(filters.value.name)
              : true;
          const matchStatus = filters.value.status
              ? item.status === filters.value.status
              : true;
          const matchCreator = filters.value.creator
              ? item.creator.includes(filters.value.creator)
              : true;
          return matchName && matchStatus && matchCreator;
        })
    );

    // 当前分页数据
    const paginatedData = computed(() => {
      const start = (pagingParams.value.currentPage - 1) * pagingParams.value.pageSize;
      const end = pagingParams.value.currentPage * pagingParams.value.pageSize;
      return filteredData.value.slice(start, end);
    });

    // 方法
    const filterData = () => {
      pagingParams.value.currentPage = 1;
    };

    const resetFilters = () => {
      filters.value = { name: "", status: "", creator: "" };
      filterData();
    };

    const handlePageChange = (page) => {
      pagingParams.value.currentPage = page;
    };

    const addQuestionBank = () => {
      alert("添加题库");
    };

    const viewDetails = (row) => {
      alert(`查看题目: ${row.name}`);
    };

    const handlePageChange1 = (page) => {
      currentPage.value = page;
    };

    const editQuestionBank = (row) => {
      alert(`编辑题库: ${row.name}`);
    };

    const deleteQuestionBank = (row) => {
      if (confirm(`确定删除题库: ${row.name} 吗？`)) {
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
      addQuestionBank,
      viewDetails,
      editQuestionBank,
      deleteQuestionBank,
      selectedQuestions,
      availableQuestions,
      searchTerm,
      currentPage,
      pageSize,
      totalQuestions,
      searchQuestions,
      handlePageChange1,
      addSingleQuestion,
      removeSingleQuestion,
      removeSelectedQuestions,
      cancelLinking,
      confirmLinking,
    };
  },
};
</script>

<style>
.filter-container {
  margin-bottom: 20px;
}

.filter-buttons {
  display: flex;
  justify-content: flex-end;
}

.filter-buttons .el-button {
  margin-left: 10px;
}
</style>