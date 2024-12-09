<template>
  <div class="base-footer">
    <div class="footer-list">
      <div class="footer-item">
        <ul>
          <li>
            <img :src="require('@/assets/images/base/logo-icon.png')" style="width: 20%;height: 20%;margin-left: -2%;"/>
          </li>
          <li>
            <img :src="require('@/assets/images/base/email-icon.png')" class="footer-item-icon">
            1234@qq.com
          </li>
          <li>
            <img :src="require('@/assets/images/base/phone-icon.png')" class="footer-item-icon">
            +12300000000
          </li>
          <li>
            <img :src="require('@/assets/images/base/location-icon.png')" class="footer-item-icon">
            山东省
          </li>
        </ul>
      </div>
      <div class="footer-item">
        <ul>
          <li><h3>网站首页</h3></li>
          <li>高质量服务</li>
          <li>我们的课程</li>
          <li>我们的见证</li>
          <li>常见问题</li>
        </ul>
      </div>
      <div class="footer-item">
        <ul>
          <li><h3>关于我们</h3></li>
          <li>公司简介</li>
          <li>我们的成就</li>
          <li>我们的目标</li>
        </ul>
      </div>
      <div class="footer-item">
        <ul>
          <li><h3>社交媒体</h3></li>
          <li>
            <div class="social_div">
              <img :src="require('@/assets/images/base/weibo-icon.png')">
            </div>
            <div class="social_div">
              <img :src="require('@/assets/images/base/weixin-icon.png')">
            </div>
            <div class="social_div">
              <img :src="require('@/assets/images/base/douyin-icon.png')">
            </div>
          </li>
        </ul>
      </div>
    </div>
    <p>@2024 /1234@gmail.com/. All rights reserved.</p>
  </div>
</template>

<script>

</script>

<style scoped>
.base-footer {
  width: 100%;
  height: 35%; /* 设置分页区域的固定高度 */
  background-color: #ffffff;
}

.footer-list{
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}

.footer-item{
  width: 100%;
  height: 200px;
  display: flex;
  align-items: self-start;
  justify-content: center;
}
.footer-item>ul>li{
  display: flex;
  align-items: center;
  justify-content: left;
  list-style-type: none;
  text-align: left;
  text-decoration: none;
  margin-top: 2%;
}
.footer-item-icon{
  width: 5%;
  height: 5%;
  margin-right: 2%;
}

.social_div{
  width: 40px;
  height: 40px;
  background-color: #f7f7f8;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 10%;
}
.social_div>img{
  width: 60%;height: 60%;
}
</style>