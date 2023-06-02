<template>
  <div class="app-container home">
    <div class="title-photo">
      <div class="top1">
        <p class="plat" >FedAIoT Laptop Guide</p>
      </div>
      <div class="top2">
        <div class="pict"><img src="../assets/logo/logo2.png"></div>
      </div>
    </div>
    <div class="introduction">
      <div class="content">
<!--        <p style="text-align: center;font-size:26px">相关介绍</p>-->
        <p class="intro">面向智能手机和物联网设备的协同训练是近年来受到广泛关注的新型训练模式。其主要目的是在智能手机和物联网设备上同时训练模型，从而实现更高效的协同工作。这种训练方式基于分布式计算和部署技术，能够利用智能手机和物联网设备的计算能力进行并行计算，提高训练效率。 随着智能手机和物联网的普及，人们的日常生活中越来越多的数据都被存储在了这些设备上，这也使得 协同训练方式被广泛关注。
          该训练方式为多个智能手机和物联网设备提供了参与训练的机会。这些设备 都具备独立的数据集、环境和算法，通过云端平台进行数据交换和模型更新，从而实现数据共享、模型 融合等操作。 在协同训练模式中，智能手机和物联网设备各自从数据集中提取特征，然后将其传送到云端平台进行模 型更新。在训练的过程中，多个智能手机和物联网设备可以同时参与并行计算，共同完成任务，进而提高 训练效率和准确性。当前，在图像识别、自然语言处理、语音识别等领域，协同训练方式已经逐渐成为主流。 智能手机和物联网设备作为一种新兴的计算单元，具备计算性能优异、便于携带和部署的特点，这也使得 其成为协同训练的理想选择。与传统的训练方式相比，协同训练方式能够更充分地发挥这些设备的计算能 力，提供更加流畅和智能的服务体验。 总之，智能手机和物联网设备协同训练方式是一种高效、可靠的 训练模式，已经成为智能手机和物联网设备 逐渐普及的趋势。在未来，随着这些设备的不断更新和升级，我 们也将看到更多基于分布式计算和部署技术的协同训练方式应用于不同领域的典型案例，进而推动这些领域的进一步发展。</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.5"
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  .title-photo{
    .top1{
      height:80px;
      display: block;
      justify-content:center;
      align-items: center;
      text-align: center;
      background-color: #dddddd;
      .plat{
        font-size:30px;
        font-weight: 600;
        //font-family: "Adobe 黑体 Std R";
        line-height: 80px;
      }
    }
    .top2 {
      margin: 0 auto;
      text-align: center;
      img{
        width:430px;
        height: 450px;
      }
    }
  }
  .content .intro{
    margin: 20px 250px;
    line-height: 26px;
    font-size: 18px;
    font-weight: 400;
    text-indent: 2em;
  }
}

</style>

