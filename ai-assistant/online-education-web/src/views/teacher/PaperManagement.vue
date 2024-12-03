<template>
  <div>
    <!-- 顶部筛选栏 -->
    <el-row class="filter-container" gutter="20">
      <el-col :span="6">
        <el-select v-model="filters.category" placeholder="选择分类" clearable>
          <el-option label="编程语言" value="编程语言" />
          <el-option label="计算机科学" value="计算机科学" />
          <el-option label="前端开发" value="前端开发" />
          <el-option label="数据库技术" value="数据库技术" />
          <el-option label="网络安全" value="网络安全" />
        </el-select>
      </el-col>
      <el-col :span="6">
        <el-select v-model="filters.status" placeholder="分类状态" clearable>
          <el-option label="启用" value="启用" />
          <el-option label="停用" value="停用" />
        </el-select>
      </el-col>
      <el-col :span="6">
        <el-input v-model="filters.keyword" placeholder="请输入关键字" />
      </el-col>
      <el-col :span="6" class="filter-buttons">
        <el-button type="primary" @click="filterData">查询</el-button>
        <el-button @click="resetFilters">重置</el-button>
      </el-col>
      <el-button type="primary" @click="createExamPaper">创建试卷</el-button>
    </el-row>

    <!-- 表格展示 -->
    <el-table :data="paginatedData" border style="width: 100%">
      <el-table-column prop="paperName" label="试卷名称" />
      <el-table-column prop="category" label="试卷分类" />
      <el-table-column prop="questionCount" label="试题数" />
      <el-table-column prop="totalScore" label="总分">
        <template #default="scope">
          <span :style="{ color: getScoreColor(scope.row.totalScore) }">
            {{ scope.row.totalScore }}
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="creator" label="创建人" />
      <el-table-column label="状态">
        <template #default="scope">
          <el-tag :type="scope.row.status === '启用' ? 'success' : 'danger'">
            {{ scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="updateDate" label="更新时间" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" type="text" @click="previewPaper(scope.row)">预览</el-button>
          <el-button size="small" type="text" @click="copyPaper(scope.row)">复制</el-button>
          <el-button size="small" type="text" @click="publishPaper(scope.row)">发布</el-button>
          <el-button size="small" type="text" @click="editPaper(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="deletePaper(scope.row)">删除</el-button>
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
  name: "ExamPaperManagement",
  setup() {
    // 数据源
    const allData = ref([
      {
        paperName: "Java基础卷",
        category: "编程语言",
        questionCount: 50,
        totalScore: 100,
        creator: "张老师",
        status: "启用",
        updateDate: "2024-03-10",
      },
      {
        paperName: "数据结构与算法中级卷",
        category: "计算机科学",
        questionCount: 40,
        totalScore: 80,
        creator: "王老师",
        status: "启用",
        updateDate: "2024-03-10",
      },
      {
        paperName: "Web前端开发入门卷",
        category: "前端开发",
        questionCount: 30,
        totalScore: 60,
        creator: "李老师",
        status: "启用",
        updateDate: "2024-03-10",
      },
      {
        paperName: "数据库原理与应用高级卷",
        category: "数据库技术",
        questionCount: 35,
        totalScore: 70,
        creator: "赵老师",
        status: "停用",
        updateDate: "2024-03-10",
      },
      {
        paperName: "Python编程基础卷",
        category: "编程语言",
        questionCount: 25,
        totalScore: 50,
        creator: "孙老师",
        status: "启用",
        updateDate: "2024-03-10",
      },
      {
        paperName: "网络安全基础卷",
        category: "网络安全",
        questionCount: 45,
        totalScore: 90,
        creator: "周老师",
        status: "启用",
        updateDate: "2024-03-10",
      },
    ]);

    // 筛选条件
    const filters = ref({
      category: "",
      status: "",
      keyword: "",
    });

    // 分页参数
    const pagingParams = ref({
      currentPage: 1,
      pageSize: 5,
    });

    // 筛选后的数据
    const filteredData = computed(() =>
        allData.value.filter((item) => {
          const matchCategory = filters.value.category
              ? item.category === filters.value.category
              : true;
          const matchStatus = filters.value.status
              ? item.status === filters.value.status
              : true;
          const matchKeyword = filters.value.keyword
              ? item.paperName.includes(filters.value.keyword)
              : true;
          return matchCategory && matchStatus && matchKeyword;
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
        category: "",
        status: "",
        keyword: "",
      };
      filterData();
    };

    const handlePageChange = (page) => {
      pagingParams.value.currentPage = page;
    };

    const createExamPaper = () => {
      alert("创建试卷");
    };

    const previewPaper = (row) => {
      alert(`预览试卷: ${row.paperName}`);
    };

    const copyPaper = (row) => {
      alert(`复制试卷: ${row.paperName}`);
    };

    const publishPaper = (row) => {
      alert(`发布试卷: ${row.paperName}`);
    };

    const editPaper = (row) => {
      alert(`编辑试卷: ${row.paperName}`);
    };

    const deletePaper = (row) => {
      if (confirm(`确定删除试卷: ${row.paperName} 吗？`)) {
        allData.value = allData.value.filter((item) => item !== row);
      }
    };

    const getScoreColor = (score) => {
      return score >= 80 ? "green" : score >= 60 ? "orange" : "red";
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
      createExamPaper,
      previewPaper,
      copyPaper,
      publishPaper,
      editPaper,
      deletePaper,
      getScoreColor,
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