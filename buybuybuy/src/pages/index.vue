<template>
  <div id="index">
   <div class="top_SYSTEM">
    <div class = 'title_header'> 商品监测对比系统</div>

     <!--<div id="main" style="width: 600px;height: 400px;"></div>-->

     <el-container>
       <el-header>

         <el-menu style="width: auto"
                  :default-active="activeIndex2"
                  class="el-menu-demo"
                  mode="horizontal"
                  @select="handleSelect"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b">
           <el-menu-item index="1">首页</el-menu-item>
           <el-submenu index="2">
             <template slot="title">我的工作台</template>
             <el-menu-item index="2-1">选项1</el-menu-item>
             <el-menu-item index="2-2">选项2</el-menu-item>
             <el-menu-item index="2-3">选项3</el-menu-item>
             <el-submenu index="2-4">
               <template slot="title">选项4</template>
               <el-menu-item index="2-4-1">选项1</el-menu-item>
               <el-menu-item index="2-4-2">选项2</el-menu-item>
               <el-menu-item index="2-4-3">选项3</el-menu-item>
             </el-submenu>
           </el-submenu>
           <el-menu-item index="3" disabled>消息中心</el-menu-item>
           <el-menu-item index="4" disabled>订单管理</el-menu-item>
           <div v-if="user_id">
             <el-submenu index="6"  style="float: right">
               <template slot="title">您好,{{username}}</template>
               <el-menu-item index="6-1"   @click="seemyself">个人信息</el-menu-item>
               <el-tooltip content="点击我注销" placement="top">
                  <el-menu-item index="6-2" :plain="true"  @click="signOut">注销</el-menu-item>
               </el-tooltip>
               <el-menu-item index="6-3"   @click="regAndLogin">切换账号</el-menu-item>
             </el-submenu>
           </div>
           <div v-else>
             <el-menu-item index="6" style="float: right"  @click="regAndLogin"> 登录注册 </el-menu-item>
           </div>
         </el-menu>
       </el-header>
       <el-container>
         <el-aside width="200px" v-show="myselfSee">
           <el-row class="tac">
             <el-col>
               <h1>个人信息 <i>{{user_id}}</i></h1>
               <el-menu  default-active="2" background-color="#545c64" class="el-menu-vertical-demo" active-text-color="#ffd04b" text-color="#fff">
                 <!--default-active="2"-->
                 <!--class="el-menu-vertical-demo"-->
                 <!--@open="handleOpen"-->
                 <!--@close="handleClose1"-->
                 <!--background-color="#545c64"-->
                 <!--text-color="#fff"-->
                 <!--active-text-color="#ffd04b">-->
                 <el-submenu index="1">
                   <template slot="title">
                     <i class="el-icon-menu"></i>
                     <span>个人信息管理</span>
                   </template>
                   <el-menu-item-group>
                     <!--<template slot="title">查看个人信息</template>-->
                     <el-menu-item index="1-1"> <i class="el-icon-view"></i>账号信息</el-menu-item>
                     <el-menu-item index="1-2" ><i class="el-icon-edit"></i><span @click = 'toupdatePWDContent'>修改密码</span></el-menu-item>
                   </el-menu-item-group>
                 </el-submenu>
                 <el-menu-item index="2">
                   <i class="el-icon-view"></i>
                   <span slot="title" @click = 'seemyGoods'>已订阅商品查看</span>
                 </el-menu-item>
                 <el-menu-item index="3">
                   <i class="el-icon-edit-outline"></i>
                   <span slot="title">已订阅商品管理</span>
                 </el-menu-item>
                 <el-menu-item index="4">
                   <i class="el-icon-service"></i>
                   <span slot="title">联系在线客服</span>
                 </el-menu-item>
                 <el-menu-item index="3" disabled>
                   <i class="el-icon-document"></i>
                   <span slot="title">已订阅商品管理</span>
                 </el-menu-item>
                 <el-menu-item index="5">
                   <i class="el-icon-message"></i>
                   <span slot="title"  @click = 'feedbackMsg'>信息反馈</span>
                 </el-menu-item>
               </el-menu>
             </el-col>
           </el-row>
         </el-aside>
         <el-container>
           <el-main>
             <div class="sousuo" >
               <!--<el-autocomplete placeholder="请输入内容" class="inputs" v-model="hello"></el-autocomplete>-->
               <el-input placeholder="请输入内容" class="inputs" v-model="inputLink"></el-input>
               <el-button  type="primary" round  @click="Goodsearch" >搜索</el-button><br/>
               <!--start -->
               <div style="height: 20%">
                 <el-row>
                   <el-col :span="24">
                     <div>
                       <!--start 链接搜索结果,返回商品信息-->
                       <div v-show="existgood">
                         <el-container style=" height:200px ; width:100%;">
                           <el-aside width="160px" style="height:200px ;background-color: #B3C0D1;line-height: 16px;">
                             <img  v-bind:src="[imgSrc]" style="margin:0px; padding:0px;height: 197px; width:160px;"/>
                           </el-aside>
                           <el-main style="height:200px ;width:100%;;background-color: white;">
                             <div class = "goodsList_Layout">
                               <el-row > 商品名称：{{hello}}</el-row>
                               <el-row > 商品价格：{{goodPrice}}</el-row>
                               <el-row > 商品链接：<a  v-bind:href="[''+inputLink]">{{inputLink}}</a></el-row>
                               <el-button type="primary" round   @click="UsersGoodsInsert(CId)" >订阅该商品</el-button>
                             </div>
                           </el-main>
                         </el-container>
                       </div>
                       <!--End 链接搜索结果,返回商品信息-->
                       <!--start 个人已订阅商品信息-->
                       <div v-show="selfGoodsLook">
                         <div v-for ="arr in objproject ">
                           <el-container style=" height:200px ; width:100%;">
                             <el-aside width="160px" style="height:200px ;background-color: #B3C0D1;line-height: 16px;">
                               <img  v-bind:src="[arr.cPicture]" style="margin:0px; padding:0px;height: 197px; width:160px;"/>
                             </el-aside>
                             <el-main style="height:200px ;width:100%;;background-color: white;">
                               <div class = "goodsList_Layout">
                                 <el-row > 商品名称：{{arr.cName}}</el-row>
                                 <el-row > 商品价格：{{arr.cLowestPrice}}</el-row>
                                 <el-row > <a  v-bind:href="[''+arr.cLink]" target="_blank">进入详情页</a> <el-button type="primary" round  style="float: right" @click="FindOneGoodLog(arr.cid)" >查看该商品记录</el-button></el-row>
                               </div>
                             </el-main>
                           </el-container>
                           <h4></h4>
                         </div>
                         <el-pagination
                           small
                           layout="prev, pager, next"
                           :total="50">
                         </el-pagination>
                       </div>
                       <!--End 个人已订阅商品信息-->
                       <div v-show="updatepwdContent">
                         <el-container style=" height:400px ; width:100%;">
                           <el-main style="height:200px ;width:100%;;background-color: white;">
                             旧密码：<el-input v-model="oldPass"></el-input>
                             新密码：<el-input v-model="newPass"></el-input>
                             确认新密码：<el-input v-model="newPass2"></el-input>
                             <el-button @click="updatePWD">修改</el-button>
                           </el-main>
                         </el-container>

                       </div>
                       <div v-show="tofeedback">
                         <el-container style=" height:400px ; width:100%;">
                           <el-main style="height:200px ;width:100%;;background-color: white;">
                             旧密码：<el-input v-model="userContent" ></el-input>
                             <el-button @click="feedbackMsg">修改</el-button>
                           </el-main>
                         </el-container>

                       </div>

                     </div>
                   </el-col>
                 </el-row>
                 <el-row>
                 <el-col :span="24"></el-col>
               </el-row>
               </div>
               <!--End-->
               <div class ='search-out-page'>

                 <!--start 单个商品的价格走势图-->
                 <div v-show="FindOneLog">
                   <el-breadcrumb separator="/">
                     <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                     <el-breadcrumb-item >个人消息</el-breadcrumb-item>
                     <el-breadcrumb-item >所有已订阅商品</el-breadcrumb-item>
                     <el-breadcrumb-item>商品价格走势</el-breadcrumb-item>
                   </el-breadcrumb>
                   <div id="main" style="width: 600px;height: 400px;"></div>
                 </div>
                 <!--End 单个商品的价格走势图-->
               </div>
             </div>

           </el-main>
           <el-footer>Footer</el-footer>
         </el-container>
       </el-container>
     </el-container>
   </div>
    <div>
    </div>
  </div>
</template>

<script>
  import echarts from 'echarts'
    export default {
      name: "index",
      data() {
        return {
          hello: '',
          MESS: '',
          pass: '',
          activeIndex: '1',
          activeIndex2: '1',
          user_id: '',
          username: '',
          inputLink: '',
          goodPrice: '',
          showmyselfNav: false,
          imgSrc: '',
          CId: '',
          existgood: false,
          myselfSee: false,
          selfGoodsLook: false,
          FindOneLog: false,
          objproject: null,
          updatepwdContent: false,
          oldPass: '',
          newPass: '',
          newPass2: '',
          tofeedback:false,
          userContent:'',

          tableData: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }],

        }
      },
      mounted() {
        this.user_id = this.$route.query.user_id;
        this.username = this.$route.query.name;
        // if(this.$route.query.addressInfo != null){
        //   this.addressInfo = JSON.parse(this.$route.query.addressInfo);
        // }else{
        //   this.getaddressinfo();
        // }
        this.$nextTick(function () {
          this.drawPie('main')
        })
      },
      methods: {
        testClick() { //测试方法暂时无用
          let that = this
          this.$axios.post('https://localhost:444/goodsLog/search', {
            uname: this.MESS,
            uPassWord: this.pass
          })
            .then((response) => {
              this.hello = response.data.content.test
              this.MESS = response.data.content.passsss
              this.pass = response.data.content.nasme
            })
        },
        toupdatePWDContent() { //修改密码页面跳转
          if (this.uId) {
            this.selfGoodsLook = false
            this.FindOneLog = false
            this.existgood = false
            let that = this
            this.$axios.post('https://localhost:888/api/searchMessByUid', {
              uId: this.user_id,
            })
              .then((response) => {
                this.updatepwdContent = true
                //alert("" + response.data)
                console.log(response.data)
              })
          } else {
            this.$message({
              showClose: true,
              message: '未输入内容，请重试',
              type: 'warning'
            });
          }
      },
      Goodsearch() { //根据商品链接搜索商品数据
        this.FindOneLog = false
        let that = this
        if (this.inputLink) {
          this.$axios.post('https://localhost:888/goodsLog/search', {
            cLink: this.inputLink
          })
            .then((response) => {
              console.log(response.data[0])
              this.hello = response.data[0].cName
              this.goodPrice = response.data[0].cLowestPrice
              this.CId = response.data[0].cid
              this.imgSrc = response.data[0].cPicture
              this.existgood = true

            })
        } else {
          this.$message({
            showClose: true,
            message: '未输入内容，请重试',
            type: 'warning'
          });
        }
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      regAndLogin() { //跳转到登陆注册界面
        this.$router.push('/login');
      },
      UsersGoodsInsert(value2) { // 用户添加想要订阅的商品
        if (this.user_id) {
          let that = this
          this.$axios.post('https://localhost:888/api/InsertSelfGoods', {
            uId: this.user_id,
            cId: value2
          })
            .then((response) => {
              //alert("" + response.data)
              console.log(response.data.content)
              // this.hello = response.data.content.test
              // this.MESS = response.data.content.passsss
              // this.pass = response.data.content.nasme
            })
        } else {
          this.$message({
            showClose: true,
            message: '用户未登录，请登录后再进行订阅',
            type: 'error'
          });
          // alert("用户未登录，请登录后再进行订阅")
        }
      },
      seemyself() { //已登录用户查看个人信息
        this.myselfSee = true
      },
      signOut() { //注销登录
        this.myselfSee = false
        this.FindOneLog = false
        this.user_id = ''
        this.username = ''
        this.$message({
          showClose: true,
          message: '账号已成功注销'
        });
        this.$router.push('/')
      },
      updatePWD() {
        if (this.user_id) {
          if (this.newPass === this.newPass2) {
            this.$message({
              showClose: true,
              message: '确认密码与密码不一致',
              type: 'error'
            });
          } else if (this.oldPass != this.newPas) {
            this.$message({
              showClose: true,
              message: '新密码与旧密码不能一样',
              type: 'error'
            });
          } else {
            let that = this
            this.$axios.post('https://localhost:888/api/updatePwd', {
              uId: this.user_id,
              uPassWord: this.newPass,
            })
              .then((response) => {
                //alert("" + response.data)
                console.log(response.data.content)
              })
          }
        } else {
          this.$message({
            showClose: true,
            message: '用户未登录，请登录后再进行订阅',
            type: 'error'
          });
          // alert("用户未登录，请登录后再进行订阅")
        }
      },
      open() { //消息通知的一种方式，暂时无用

        const h = this.$createElement;
        this.$notify({
          title: '标题名称',
          message: h('i', {style: 'color: teal'}, '这是提示文案这是提示文案这是提示文案这是提示文案这是提示文案这是提示文案这是提示文案这是提示文案')
        });
      },
      seemyGoods() { //查看已登录用户的所有商品
        this.updatepwdContent = false
        this.FindOneLog = false
        if (this.user_id) {
          let that = this
          this.$axios.post('https://localhost:888/api/SearchSelfGoods', {
            uId: this.user_id,
          })
            .then((response) => {
              this.existgood = false
              this.selfGoodsLook = true
              console.log(response.data)
              this.objproject = response.data
            })
        } else {
          this.$message({
            showClose: true,
            message: '用户未登录，请登录后再进行订阅',
            type: 'error'
          });
        }
      },
        feedbackMsg(){//用户信息反馈（用户提出建议）
          this.FindOneLog = false
          this.selfGoodsLook = false
          this.existgood = false
          this.tofeedback = true
          if (this.user_id) {
            this.$prompt('请写下您的意见', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
              // inputErrorMessage: '邮箱格式不正确'
            }).then(({ value }) => {
              this.$axios.post('https://localhost:888/api/feedbackMsgToSystem', {
                uId: this.user_id,
                userContent: this.userContent,

              })
                .then((response) => {
                  this.$message({
                    type: 'success',
                    message: '你的邮箱是: ' + value
                  });
                })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消输入'
              });
            });
            let that = this

          } else {
            this.$message({
              showClose: true,
              message: '用户未登录，请登录后再进行订阅',
              type: 'error'
            });
          }


        },
      FindOneGoodLog(value3) {
        var myChart = echarts.init(document.getElementById('main'));
        // 显示标题，图例和空的坐标轴
        myChart.setOption({
          title: {
            text: '商品价格走势图'
          },
          tooltip: {},
          legend: {
            data: ['价格']
          },
          xAxis: {
            type: 'category',
            data: []
          },
          yAxis: {type: 'value'},
          series: [{
            name: '价格',
            type: 'line',
            data: []
          }]
        });

        if (this.user_id) {
          let that = this
          this.$axios.post('https://localhost:888/goodsLog/FindOneGoodPriceLog', {
            cid: value3  //将商品ID作为参数传给后端
          })
            .then((response) => {
              this.existgood = false
              this.selfGoodsLook = false
              this.FindOneLog = true
              let arr_price = []
              let arr_date = []
              for (let index in response.data) {//遍历数据，将价格存在array中
                arr_price.push(response.data[index].clPrice)
                arr_date.push(response.data[index].clDateTime.substring(0, 10))
              }
              console.log(response.data)
              this.objproject = response.data
              //填入数据
              myChart.setOption({
                xAxis: {
                  data: arr_date
                },
                series: [{
                  // 根据名字对应到相应的系列
                  name: '实时价格',
                  data: arr_price,
                  label: { //折线上显示价格
                    normal: {
                      show: true,
                    }
                  }
                }]
              });
              // this.hello = response.data.content.test
              // this.MESS = response.data.content.passsss
              // this.pass = response.data.content.nasme
            })
        } else {
          this.$message({
            showClose: true,
            message: '用户未登录，请登录后再进行查看',
            type: 'error'
          });
        }
      }
    }
    }

</script>

<style scoped>
  .top_SYSTEM{
    background-image: url("../assets/bg.jpg");
    background-size:100% 100%;
    height:auto;
    width:100%;

    /*margin: -8px;*/
    /*padding: 0px;*/
    /*position: absolute;*/
    /*width:100%;;*/
    /*min-width: 1000px;*/
    /*z-index:-10;*/
    /*zoom: 1;*/
    /*-webkit-background-size: cover;*/
    /*-o-background-size: cover;*/
    /*background-position: center 0;*/
  }
  .tac{
    width:100%;
  }
  body{

    background: cadetblue;
    height: 100%;

  }
  .title_header{
    /*background-color: azure;*/
    text-align: center;
    line-height: 30px;
    color: cadetblue;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    font-size: larger;
    font-style: italic;
  }
  .el-header, .el-footer {
    /*background-color: #B3C0D1;*/
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    height:800px;
    /*background-color: #D3DCE6;*/
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-main {
    height:800px;
    /*background-color: #E9EEF3;*/
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  body > .el-container {
    /*margin-bottom: 40px;*/
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  .sousuo{
    position: absolute;
    left: 30%;
    text-align: center;


  }

  .inputs{
    width:500px;

  }
  /*布局格式CSS样式*/
  .el-row {
    margin-bottom: 20px;

  }
  .el-col {
    border-radius: 4px;

  }
  .bg-purple-dark {
    background: white;
    height:200px;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: white;
  }

  .goodsList_Layout{
    font-size: smaller;
    text-align: left;
    line-height: 14px
  }
</style>
