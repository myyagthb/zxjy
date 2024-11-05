<template>
  <el-table :data="currentPageData" style="width: 100%">
    <el-table-column prop="index" label="序号" width="80" />
    <el-table-column prop="course" label="课程名称" />
    <el-table-column prop="views" label="浏览次数" />
    <el-table-column prop="price" label="单价（元）" />
    <el-table-column prop="sales" label="总销售（单）" />
    <el-table-column prop="revenue" label="总收入（元）" />
  </el-table>

  <el-pagination
      layout="prev, pager, next"
      :total="tableData.length"
      :page-size="pageSize"
      @current-change="handlePageChange"
      style="margin-top: 20px"
  />
</template>

<script>
import { ref, computed } from 'vue';

export default {
  setup() {
    // 表格数据
    const tableData = ref([
      { index: 1, course: '课程1', views: 1552, price: 500, sales: 120, revenue: 60000 },
      { index: 2, course: '课程2', views: 4522, price: 800, sales: 100, revenue: 80000 },
      { index: 3, course: '课程1', views: 1552, price: 500, sales: 120, revenue: 60000 },
      { index: 4, course: '课程2', views: 4522, price: 800, sales: 100, revenue: 80000 },
      { index: 5, course: '课程1', views: 1552, price: 500, sales: 120, revenue: 60000 },
      { index: 6, course: '课程2', views: 4522, price: 800, sales: 100, revenue: 80000 },
      { index: 7, course: '课程1', views: 1552, price: 500, sales: 120, revenue: 60000 },
      { index: 8, course: '课程2', views: 4522, price: 800, sales: 100, revenue: 80000 },
      { index: 9, course: '课程1', views: 1552, price: 500, sales: 120, revenue: 60000 },
      { index: 10, course: '课程2', views: 4522, price: 800, sales: 100, revenue: 80000 },
      // 更多数据...
    ]);

    // 分页配置
    const pageSize = 5; // 每页显示的行数
    const currentPage = ref(1);

    // 计算当前页数据
    const currentPageData = computed(() => {
      const start = (currentPage.value - 1) * pageSize;
      const end = start + pageSize;
      return tableData.value.slice(start, end);
    });

    // 更新当前页
    const handlePageChange = (page) => {
      currentPage.value = page;
    };

    return {
      tableData,
      currentPageData,
      pageSize,
      handlePageChange,
    };
  },
};
</script>

<style scoped>
/* 这里可以加入你的样式定义 */
</style>
