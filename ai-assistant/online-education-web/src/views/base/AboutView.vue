<template>
  <div class="about-us">
    <div class="header">
      <h1>关于我们</h1>
      <p>欢迎来到我们的平台，我们热衷于让个人掌握设计和开发的世界。我们提供广泛的在线课程，旨在为学习者提供在不断发展的数字环境中取得成功所需的技能和知识。</p>
    </div>
    <hr class="divider" />
    <h2>我们的成就</h2>
    <p>我们对卓越的承诺使我们在旅程中取得了重要的里程碑。以下是我们的一些显著成就：</p>
    <div class="containers">
      <div class="container">
        <div class="icon crown"></div>
        <h2>数千人信赖</h2>
        <p>我们已经成功地为数千名学生提供服务，帮助他们释放潜力。我们已成功地为数千名学生提供服务，帮助他们释放潜力，实现职业目标。</p>
      </div>
      <div class="container">
        <div class="icon medal"></div>
        <h2>我们的获奖课程</h2>
        <p>我们的课程因其质量、内容深度和有效的教学方法而受到赞誉。内容质量、内容深度和有效的教学方法在业界获得认可和赞誉。</p>
      </div>
      <div class="container">
        <div class="icon feedback"></div>
        <h2>积极的学生反馈</h2>
        <p>我们为学生们的积极反馈感到骄傲，他们赞赏我们课程材料的实用性和相关性。我们的他们赞赏我们课程材料的实用性和相关性。</p>
      </div>
      <div class="container">
        <div class="icon pal"></div>
        <h2>行业合作伙伴</h2>
        <p>我们与行业领导者建立了强有力的合作伙伴关系，使我们能够为学生提供最新的工具和技术，我们与行业领导者建立了强有力的合作伙伴关系。</p>
      </div>
    </div>

    <h2>我们的目标</h2>
    <p>我们的目标是让来自各种背景的个人在设计和开发的世界中茁壮成长。我们认为，教育应该是可获得的和变革性的，使学习者能够追求自己的激情并产生有意义的影响。
      通过精心制作的课程，我们的目标是</p>
    <div class="containers">
      <div class="container">
        <div class="icon quality"></div>
        <h2>增长用户数量和多样性</h2>
        <p>制定策略，通过市场营销、广告宣传和推荐奖励等方式吸引更多用户注册平台。</p>
      </div>
      <div class="container">
        <div class="icon learn"></div>
        <h2>优化在线学习体验</h2>
        <p>与专业教育工作者合作，设计和开发与学习目标相匹配的内容，确保内容具有权威性和实用性。</p>
      </div>
      <div class="container">
        <div class="icon strategypal"></div>
        <h2>建立战略伙伴关系</h2>
        <p>与大学、学院和其他教育机构建立合作关系，提供在线学位课程、专业认证和学分转移等服务。</p>
      </div>
      <div class="container">
        <div class="icon dev"></div>
        <h2>引领在线教育发展</h2>
        <p>关注教育技术的最新发展，如人工智能、虚拟现实、区块链等，探索如何将其应用于在线教育中，提供更丰富的学习体验。</p>
      </div>
    </div>

    <div class="banner-content">
      <div class="banner-text">
        <h1>让我们一起塑造数字创新的未来</h1>
        <p>加入我们这个激动人心的学习之旅，释放您在设计和开发方面的潜力。</p>
      </div>
      <div class="image-container">
        <img src="../../assets/images/about/background.png" alt="background"/>
      </div>
      <button class="join-button">立即加入</button>
    </div>
  </div>
</template>

<style scoped>
.about-us {
  padding: 20px;
  background-color: #f9f9f9;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header h1 {
  font-size: 2em;
  margin: 0; /* 去除默认的 margin */
}

.header p {
  font-size: 1em;
  margin: 0;
  color: #666;
  max-width: 50%;
}

.divider {
  border: 0;
  height: 1px;
  background-color: rgba(0, 0, 0, 0.1); /* 淡色线条 */
  margin: 40px 0; /* 上下 margin */
}

.containers {
  display: flex;
  flex-wrap: wrap; /* 允许换行 */
  justify-content: space-between;
  margin-top: 20px;
}

.container {
  flex: 1 1 calc(40% - 20px); /* 每行两个元素，减去 margin */
  margin: 10px;
  padding: 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}


.icon {
  width: 40px;
  height: 40px;
  margin-bottom: 10px;
}

.crown {
  background: url('@/assets/images/about/crown-icon.png') no-repeat center center;
  background-size: contain;
}

.medal {
  background: url('@/assets/images/about/medal-icon.png') no-repeat center center;
  background-size: contain;
}

.feedback {
  background: url('@/assets/images/about/feedback-icon.png') no-repeat center center;
  background-size: contain;
}

.pal {
  background: url('@/assets/images/about/pal-icon.png') no-repeat center center;
  background-size: contain;
}

.quality {
  background: url('@/assets/images/about/quality-icon.png') no-repeat center center;
  background-size: contain;
}

.learn {
  background: url('@/assets/images/about/learn-icon.png') no-repeat center center;
  background-size: contain;
}

.strategypal {
  background: url('@/assets/images/about/strategypal-icon.png') no-repeat center center;
  background-size: contain;
}

.dev {
  background: url('@/assets/images/about/dev-icon.png') no-repeat center center;
  background-size: contain;
}

h2 {
  font-size: 1.5em;
  margin: 20px 0;
  text-align: left;

}

p {
  font-size: 0.9em;
  color: #666;
  max-width: 100%;
  text-align: left;
}

.banner-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: white;
  padding: 0 40px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 90%; /* 调整内容的宽度 */
  max-width: 1200px; /* 限制最大宽度 */
  margin: 20px 10px;
}

.banner-text {
  max-width: 70%;
}

.banner-text h1 {
  font-size: 2em;
  color: #f7931e;
  margin: 0 0 10px 0;
}

.banner-text p {
  font-size: 1em;
  color: #666;
}

.image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden; /* 确保图片不会超出容器 */
}

.image-container img {
  max-width: 100%;
  max-height: 100%;
  object-fit: cover; /* 图片适应容器 */
}

.join-button {
  background-color: #f7931e;
  color: white;
  border: none;
  border-radius: 8px;
  padding: 10px 20px;
  font-size: 1em;
  cursor: pointer;
  transition: background-color 0.3s;
}

.join-button:hover {
  background-color: #e0851b;
}

</style>
<script setup lang="ts">
</script>