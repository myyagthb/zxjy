<template>
  <div class="home">
    <div class="common-layout">
      <el-container>
        <el-header class="el_header">
          <div class="header_top">
            <div class="header_top_content">
              <div style="background-color: #f9f1e4;">
                <img :src="require('@/assets/images/home/lighting-icon.png')" style="width: 40px;height: 40px;">
              </div>
              <div>
                <span class="header_top_content_span">快来释放</span>
                您的潜在优秀技能创造力
              </div>
            </div>
          </div>
          <div class="header_middle">
            <div style="font-size: 30px;">实时互动在线课程平台，提供完善的在线教育内容</div>
            <p>向行业专家学习，辅助信心示例文字，提高技能</p>
          </div>
          <div class="header_bottom">
            <el-button class="header_bottom_button" style="background-color: orange;color: white;">发现课程</el-button>
            <el-button class="header_bottom_button">会员价格</el-button>
          </div>
        </el-header>
        <el-main>
          <el-row>
            <el-col :span="8">
              <div class="grid-content ep-bg-purple" >
                <div class="option_card">
                  <div class="card_left">
                    <img :src="require('@/assets/images/home/book-icon.png')" class="card_img"/>
                  </div>
                  <div class="card_right">
                    <div class="card_right_top">500+</div>
                    <div class="card_right_bottom">
                      上架课程
                    </div>
                  </div>
                </div>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="grid-content ep-bg-purple-light" >
                <div class="option_card">
                  <div class="card_left">
                    <img :src="require('@/assets/images/home/member-icon.png')" class="card_img"/>
                  </div>
                  <div class="card_right">
                    <div class="card_right_top">2000+</div>
                    <div class="card_right_bottom">
                      注册学员
                    </div>
                  </div>
                </div>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="grid-content ep-bg-purple" >
                <div class="option_card">
                  <div class="card_left">
                    <img :src="require('@/assets/images/home/read-icon.png')" class="card_img"/>
                  </div>
                  <div class="card_right">
                    <div class="card_right_top">10000+</div>
                    <div class="card_right_bottom">
                      累计报名数
                    </div>
                  </div>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-main>
        <el-footer style="width: 100%;height: auto;margin-top: 5%;">
          <div style="font-weight: bold;font-size: 30px;text-align: left;">&nbsp;&nbsp;我们的课程</div>
          <div class="our_course">
            <div style="text-align: left;">
              &emsp;辅助信息文字内容示例展示，设计咨询服务是一种高效、个性化和全面的设计服务方式,可以帮助客户在设计中取得更好的成果和效
              果。客户可以通过选择提供设计咨询服务的机构或个人
            </div>
            <div>
              <el-button>查看全部</el-button>
            </div>
          </div>
        </el-footer>
      </el-container>
      <div class="course_list">
        <div class="course_item">
          <div class="course_item_top">
            <img :src="require('@/assets/images/home/course_item.jpg')" class="course_item_img">
            <div class="course_item_option">
              <div class="course_item_option_left">
                <span>4周</span>
                <span>初学者</span>
              </div>
              <div class="course_item_option_right">
                <span>张宇航老师</span>
              </div>
            </div>
          </div>
          <div class="course_item_middle">
            <div class="course_item_middle_title">
              网页设计基础
            </div>
            <div class="course_item_middle_content">
              学习网页设计的基本原理，包括HTML、CSS和响应式设计原则。培养创建视觉吸引力和用户友好网站的技能。
            </div>
          </div>
          <div class="course_item_bottom">
            <el-button class="course_item_button">立即获取</el-button>
          </div>
        </div>

        <div class="course_item">
          <div class="course_item_top">
            <img :src="require('@/assets/images/home/course_item.jpg')" class="course_item_img">
            <div class="course_item_option">
              <div class="course_item_option_left">
                <span>4周</span>
                <span>初学者</span>
              </div>
              <div class="course_item_option_right">
                <span>张宇航老师</span>
              </div>
            </div>
          </div>
          <div class="course_item_middle">
            <div class="course_item_middle_title">
              网页设计基础
            </div>
            <div class="course_item_middle_content">
              学习网页设计的基本原理，包括HTML、CSS和响应式设计原则。培养创建视觉吸引力和用户友好网站的技能。
            </div>
          </div>
          <div class="course_item_bottom">
            <el-button class="course_item_button">立即获取</el-button>
          </div>
        </div>

        <div class="course_item">
          <div class="course_item_top">
            <img :src="require('@/assets/images/home/course_item.jpg')" class="course_item_img">
            <div class="course_item_option">
              <div class="course_item_option_left">
                <span>4周</span>
                <span>初学者</span>
              </div>
              <div class="course_item_option_right">
                <span>张宇航老师</span>
              </div>
            </div>
          </div>
          <div class="course_item_middle">
            <div class="course_item_middle_title">
              网页设计基础
            </div>
            <div class="course_item_middle_content">
              学习网页设计的基本原理，包括HTML、CSS和响应式设计原则。培养创建视觉吸引力和用户友好网站的技能。
            </div>
          </div>
          <div class="course_item_bottom">
            <el-button class="course_item_button">立即获取</el-button>
          </div>
        </div>

        <div class="course_item">
          <div class="course_item_top">
            <img :src="require('@/assets/images/home/course_item.jpg')" class="course_item_img">
            <div class="course_item_option">
              <div class="course_item_option_left">
                <span>4周</span>
                <span>初学者</span>
              </div>
              <div class="course_item_option_right">
                <span>张宇航老师</span>
              </div>
            </div>
          </div>
          <div class="course_item_middle">
            <div class="course_item_middle_title">
              网页设计基础
            </div>
            <div class="course_item_middle_content">
              学习网页设计的基本原理，包括HTML、CSS和响应式设计原则。培养创建视觉吸引力和用户友好网站的技能。
            </div>
          </div>
          <div class="course_item_bottom">
            <el-button class="course_item_button">立即获取</el-button>
          </div>
        </div>

        <div class="course_item">
          <div class="course_item_top">
            <img :src="require('@/assets/images/home/course_item.jpg')" class="course_item_img">
            <div class="course_item_option">
              <div class="course_item_option_left">
                <span>4周</span>
                <span>初学者</span>
              </div>
              <div class="course_item_option_right">
                <span>张宇航老师</span>
              </div>
            </div>
          </div>
          <div class="course_item_middle">
            <div class="course_item_middle_title">
              网页设计基础
            </div>
            <div class="course_item_middle_content">
              学习网页设计的基本原理，包括HTML、CSS和响应式设计原则。培养创建视觉吸引力和用户友好网站的技能。
            </div>
          </div>
          <div class="course_item_bottom">
            <el-button class="course_item_button">立即获取</el-button>
          </div>
        </div>

        <div class="course_item">
          <div class="course_item_top">
            <img :src="require('@/assets/images/home/course_item.jpg')" class="course_item_img">
            <div class="course_item_option">
              <div class="course_item_option_left">
                <span>4周</span>
                <span>初学者</span>
              </div>
              <div class="course_item_option_right">
                <span>张宇航老师</span>
              </div>
            </div>
          </div>
          <div class="course_item_middle">
            <div class="course_item_middle_title">
              网页设计基础
            </div>
            <div class="course_item_middle_content">
              学习网页设计的基本原理，包括HTML、CSS和响应式设计原则。培养创建视觉吸引力和用户友好网站的技能。
            </div>
          </div>
          <div class="course_item_bottom">
            <el-button class="course_item_button">立即获取</el-button>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.common-layout{
  margin-top: 8%;
}


.el_header{
  width: 100%;
  height: auto;
}

.header_top{
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 2%;
}
.header_top_content{
  width: 80%;
  height: auto;
  background-color: white;
  padding: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 30px;
}

.header_top_content_span{
  color: orange;
  font-size: 30px;
  font-weight: bold;
  margin-left: 10px;
  margin-right: 10px;
}


.header_bottom{
  margin-top: 5%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 5%;
}
.header_bottom>.header_bottom_button{
  width: 12%;
  font-size: 25px;
  height: 50px;
  border-radius: 5%;
}




.option_card{
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.card_img{
  width: 60%;
  height: 60%;
}

.card_left{
  display: flex;
  align-items: center;
  justify-content: center;
}
.card_right{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-left: -5%;
}
.card_right>.card_right_top{
  font-size: 30px;

}
.card_right>.card_right_bottom{
  font-size: 20px;
  font-weight: bold;
  color: #888787;
}


.our_course{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}


.course_list{
  width: 100%;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap; /* 允许项目换行 */
  align-items: flex-start; /* 对齐方式可以根据需要调整 */
}


.course_item{
  padding: 2%;
  background-color: white;
  width: 42%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 2%;
}

.course_item:nth-last-child(1){
  margin-bottom: 10%;
}

.course_item:nth-last-child(2){
  margin-bottom: 10%;
}

.course_item_top>.course_item_img{
  width: 100%;
  height: 10%;
  border-radius: 3%;
}
.course_item_top>.course_item_option{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-evenly;

}


.course_item_option_left{
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: start;
}
.course_item_option_right{
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}


.course_item_top>.course_item_option span{
  border: 1px solid black;
  padding: 2%;
  border-radius: 5%;
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 2%;
}

.course_item_middle{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.course_item_middle>.course_item_middle_title{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: start;
  font-weight: bold;
  font-size: 20px;
  margin-top: 2%;
  margin-bottom: 2%;
}
.course_item_middle>.course_item_middle_content{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: start;
  text-align: left;
  margin-bottom: 2%;
}
.course_item_bottom{
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.course_item_bottom>.course_item_button{
  width: 100%;
  height: 40px;
  font-weight: bold;
  color: black;
  background-color: #f7f7f8;
}

</style>
