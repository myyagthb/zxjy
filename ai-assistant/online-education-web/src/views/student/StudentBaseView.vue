<template>
  <div class="student">
    <el-container >
      <el-header class="header_div">
          <div class="header_div_avatar">
            <el-avatar style="width: 100%;height: 100%;" :src="require('@/assets/images/student/teacher.jpeg')"></el-avatar>
          </div>
          <div class="header_div_info">
            <div>张三</div>
            <div>大三年级</div>
          </div>
      </el-header>
      <el-container>
        <el-aside width="300px">
          <StudentAside></StudentAside>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import StudentAside from "@/views/student/StudentAside.vue";
</script>

<style scoped>
.student{
  width: 100%;
  height: auto;
}
el-container{
  width: 100%;
  height: auto;
}

.header_div{
  width: 100%;
  height: auto;
  background-color: #f3cc9c;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.header_div_avatar{
  border: 1px solid blue;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-left: 5%;
}
.header_div_info{
  margin-left: 3%;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
</style>