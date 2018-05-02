<template>
  <div id="index" style="margin:-8px">
   <div class="top_SYSTEM">
    <div class = 'title_header'> 商品监测对比系统</div>
     <!--<div id="main" style="width: 600px;height: 400px;"></div>-->
     <el-container>
       <el-header>
         <el-menu style="width: auto"
                  :default-active="activeIndex2"
                  class="el-menu-demo"
                  mode="horizontal"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b">
           <el-menu-item index="1" @click="goToindex" >首页</el-menu-item>
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
                 <el-submenu index="1">
                   <template slot="title">
                     <i class="el-icon-menu"></i>
                     <span>个人信息管理</span>
                   </template>
                   <el-menu-item-group>
                     <!--<template slot="title">查看个人信息</template>-->
                     <el-menu-item index="1-1"> <i class="el-icon-view" ></i><span @click="seeMyMsg">账号信息</span></el-menu-item>
                     <el-menu-item index="1-2" ><i class="el-icon-edit"></i><span @click = 'toupdatePWDContent'>修改密码</span></el-menu-item>
                   </el-menu-item-group>
                 </el-submenu>
                 <el-menu-item index="2">
                   <i class="el-icon-view"></i>
                   <span slot="title" @click = 'seemyGoods'>已订阅商品查看</span>
                 </el-menu-item>
                 <el-menu-item index="3">
                   <i class="el-icon-edit-outline"></i>
                   <span slot="title" @click="togoodsManageContent">已订阅商品管理</span>
                 </el-menu-item>
                 <el-menu-item index="4">
                   <i class="el-icon-service"></i>
                   <span slot="title">联系在线客服</span>
                 </el-menu-item>
                 <el-menu-item index="3" disabled>
                   <i class="el-icon-document"></i>
                   <span slot="title"  >已订阅商品管理</span>
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
               <el-input placeholder="请输入链接地址如：http://item.jd.com/5324065.html" class="inputs" v-model="inputLink"></el-input>
               <el-button  type="primary" round  @click="Goodsearch" >搜索</el-button><br/>
               <div v-show="zhuye">
                 <el-carousel indicator-position="outside">
                   <el-carousel-item v-for="item in itemPic" :key="item">
                     <div style="height:100%;"><img  v-bind:src="[item]" style=" width: 100%; height: 100%;" /></div>
                   </el-carousel-item>
                 </el-carousel>
               </div>
               <!--start -->
               <div style="">
                 <el-row>
                   <el-col :span="24">
                     <div>
                       <!--start 链接搜索结果,返回商品信息-->
                       <div v-show="existgood">
                         <el-container style=" height:200px ; width:100%;">
                           <el-aside width="160px" style="height:200px ;background-color: #B3C0D1;line-height: 16px;">
                             <img  v-bind:src="[imgSrc]" style="margin:0; padding:0;height: 197px; width:160px;"/>
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
                       <div v-show="selfGoodsLook" style="  width:100%;">
                         <div v-for ="arr in objproject ">
                           <el-container style=" height:200px ; background-color: white;">
                             <el-aside width="160px" style="height:200px ;background-color: white;line-height: 16px;">
                               <img  v-bind:src="[arr.cPicture]" style="margin:0px; padding:0px;height: 197px; width:160px;"/>
                             </el-aside>
                             <el-main style="height:200px ;width:200px;">
                               <div class = "goodsList_Layout">
                                 <el-row style="word-wrap:break-word; word-break:break-all;"> 商品名称：{{arr.cName}}</el-row>
                                 <el-row > 商品价格：{{arr.cLowestPrice}}</el-row>
                                 <el-row > <a  v-bind:href="[''+arr.cLink]" target="_blank">进入详情页</a> <el-button type="primary" round  style="float: right" @click="FindOneGoodLog(arr.cid)" >查看该商品记录</el-button></el-row>
                               </div>
                             </el-main>
                           </el-container>
                           <h4></h4>
                         </div>
                         <el-pagination
                           background
                           :page-size="3"
                           :pager-count="listcount"
                           layout="prev, pager, next"
                           :total="listcount"@current-change="handleCurrentChange">
                         </el-pagination>
                       </div>
                       <!--End 个人已订阅商品信息-->
                       <!--个人账号信息查询界面-->
                       <div v-show="MyContentMsg"  style=" height:300px;background:rgba(171, 119, 157, 0.27);">
                         <el-container style=" height:200px ; width:80%;">
                           <el-main style="width:80%; padding-left: 20%">
                             <div v-if="objproject" style="color:white;font-family:SimHei; text-align: left;">
                             我的ID:{{objproject.uid}}<br/>
                             我的账号：{{objproject.uNumber}}<br/>
                             我的名字：{{objproject.uname}}<br/>
                             </div>
                             <el-button @click = 'toupdatePWDContent' >修改密码</el-button>
                           </el-main>
                         </el-container>
                       </div>
                       <!--个人账号信息查询 END-->
                       <!--个人密码修改界面-->
                       <div v-show="updatepwdContent">
                         <el-container style=" height:200px ; width:80%;">

                           <el-main style="height:200px; width:100%;background-color:silver;background:rgba(171, 119, 157, 0.27);border-radius:4px;">
                             <p style="font-size: larger;font-family: SimSun;font-weight:bold;color:whitesmoke;">修改密码</p>
                             <el-input v-model="oldPass" placeholder="请输入你的旧密码"></el-input>
                             <el-input v-model="newPass" placeholder="请输入你的新密码"></el-input>
                             <el-input v-model="newPass2" placeholder="请再次输入你的新密码"></el-input>
                             <el-button @click="updatePWD">修改</el-button>
                           </el-main>
                         </el-container>
                       </div>
                       <!--个人密码修改界面 END-->
                       <!--用户反馈界面-->
                       <div v-show="tofeedback">

                       </div>
                       <!--用户反馈界面 END-->
                       <!--已经订阅的商品管理界面-->
                       <div v-show="goodsManageContent" style="width:800px;text-align: center;">
                         <el-table
                           ref="multipleTable"
                           v-loading="loading"
                           :data="tableData3"
                           tooltip-effect="dark"
                           style="width: 100%"
                           @selection-change="handleSelectionChange">
                           <el-table-column
                             type="selection"
                             width="55">
                           </el-table-column>
                           <el-table-column
                             label="商品ID"
                             width="70">
                             <template slot-scope="scope">{{ scope.row.cid}}</template>
                           </el-table-column>
                           <el-table-column
                             label="商品链接"
                             width="250">
                             <template slot-scope="scope">{{ scope.row.cLink}}</template>
                           </el-table-column>
                           <el-table-column
                             label="商品名称"
                             show-overflow-tooltip>
                             <template slot-scope="scope">{{ scope.row.cName}}</template>
                           </el-table-column>
                         </el-table>
                         <div style="margin-top: 20px">
                           <!--<el-button @click="toggleSelection([tableData3[1], tableData3[2]])">切换第二、第三行的选中状态</el-button>-->
                           <el-button @click="toggleSelection()">取消选择</el-button>
                           <el-button @click="delSelectedGoods">删除选中数据</el-button>
                           <el-button @click="CompareSelectedGoods">生成对比价格图表</el-button>
                         </div>
                       </div>
                       <!--已经订阅的商品管理界面 END-->
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
                   <div id="main2" style="width: 600px;height: 400px;"></div>
                 </div>

                 <!--End 单个商品的价格走势图-->
               </div>
             </div>

           </el-main>

         </el-container>
         <!--<el-footer>Footer</el-footer>-->
       </el-container>
     </el-container>
     <div class="footer">
       <label>该系统最终解释权归BuyBuyBuy所有，侵权必究</label>
     </div>
   </div>

  </div>
</template>

<script>
  import echarts from 'echarts'
    export default {
      name: "index",
      data() {
        return {
          loading: true,
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
          listcount:1,
          zhuye:true,
          MyContentMsg:false,
          goodsManageContent:false,
          itemPic:['http://pic1.win4000.com/pic/1/23/1acf1215148.jpg','http://ww4.sinaimg.cn/bmiddle/865cea84gw1erfi2ormusg20c806sx6p.jpg','http://ww2.sinaimg.cn/large/6d1a569dtw1eftwwwdgx5g20dw06ygz4.gif','http://img3.100bt.com/upload/ttq/20130915/1379247772938_middle.gif'],
          tableData3:[],
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
        seeMyMsg(){
          alert(this.user_id)
          if (this.user_id) {
            this.existgood = false //搜索商品结果界面
            this.selfGoodsLook =false //所有已经订阅的商品结果界面
            this.MyContentMsg =false //我的个人信息界面
            this.updatepwdContent =false  //修改密码界面
            this.tofeedback =false //用户反馈界面
            this.goodsManageContent = false //商品管理界面
            this.FindOneLog = false//商品价格记录
            this.zhuye= false //主图走马灯
            let that = this
            this.$axios.post('https://localhost:888/api/searchMessByUid', {
              uid: this.user_id,
            })
              .then((response) => {
                this.MyContentMsg = true
                this.objproject = response.data.CommoditiesEntitys
                //alert("" + response.data)
                console.log(response.data)
              })
          } else {
            this.$message({
              showClose: true,
              message: '用户没登陆',
              type: 'warning'
            });
          }
        },
        toupdatePWDContent() { //修改密码页面跳转
          this.existgood = false //搜索商品结果界面
          this.selfGoodsLook =false //所有已经订阅的商品结果界面
          this.MyContentMsg =false //我的个人信息界面
          this.updatepwdContent =true  //修改密码界面
          this.tofeedback =false //用户反馈界面
          this.goodsManageContent = false //商品管理界面
          this.FindOneLog = false//商品价格记录
          this.zhuye= false //主图走马灯
          if (this.user_id) {
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
              message: '用户没登陆',
              type: 'warning'
            });
          }
      },
      Goodsearch() { //根据商品链接搜索商品数据
        ///this.existgood = false //搜索商品结果界面
        this.selfGoodsLook =false //所有已经订阅的商品结果界面
        this.MyContentMsg =false //我的个人信息界面
        this.updatepwdContent =false  //修改密码界面
        this.tofeedback =false //用户反馈界面
        this.goodsManageContent = false //商品管理界面
        this.FindOneLog = false//商品价格记录
        this.zhuye= false
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
              this.$message({
                type: 'info',
                message:  response.data.content.outputMess
              });
              //let loadingInstance = Loading.service(options);
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
        this.existgood = false //搜索商品结果界面
        this.selfGoodsLook =false //所有已经订阅的商品结果界面
        this.MyContentMsg =false //我的个人信息界面
        this.updatepwdContent =false  //修改密码界面
        this.tofeedback =false //用户反馈界面
        this.goodsManageContent = false //商品管理界面
        this.FindOneLog = false//商品价格记录
        this.myselfSee = false //我的侧边菜单栏
        this.zhuye= false //主图走马灯
        this.user_id = ''
        this.username = ''
        this.$message({
          showClose: true,
          message: '账号已成功注销'
        });
        this.$router.push('/')
      },
      updatePWD() { //异步修改密码功能，并返回修改结果
        if (this.user_id) {
          if (this.newPass !== this.newPass2) {
            this.$message({
              showClose: true,
              message: '确认密码与密码不一致',
              type: 'error'
            });
          } else if (this.oldPass === this.newPass) {
            this.$message({
              showClose: true,
              message: '新密码与旧密码不能一样',
              type: 'error'
            });
          } else {
            let that = this
            this.$axios.post('https://localhost:888/api/updatePwd', {
              uid: this.user_id,
              uPassWord: this.newPass,
            })
              .then((response) => {
                console.log(response.data.content)
              })
          }
        } else {
          this.$message({
            showClose: true,
            message: '用户未登录',
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
        this.existgood = false //搜索商品结果界面
        this.selfGoodsLook =false //所有已经订阅的商品结果界面
        this.MyContentMsg =false //我的个人信息界面
        this.updatepwdContent =false  //修改密码界面
        this.tofeedback =false //用户反馈界面
        this.goodsManageContent = false //商品管理界面
        this.FindOneLog = false//商品价格记录
        this.zhuye= false //主图走马灯
        if (this.user_id) {
          let that = this
          this.$axios.post('https://localhost:888/api/SearchSelfGoods', {
            uid: this.user_id,
            currentpage:this.currentpage,
          })
            .then((response) => {
              this.existgood = false
              this.selfGoodsLook = true
              console.log(response.data.CommoditiesEntitys)
              this.objproject = response.data.CommoditiesEntitys
              //alert(response.data.listcounts)
              this.listcount= 0+response.data.listcounts
              //alert( this.listcount)
            })
        } else {
          this.$message({
            showClose: true,
            message: '用户未登录，请登录后再进行订阅',
            type: 'error'
          });
        }
      },
        handleCurrentChange(val){ //点击下方的页码时候操作，异步获取某页的数据并返回
          this.$axios.post('https://localhost:888/api/SearchSelfGoods', {
            uid: this.user_id,
            currentpage:val,

          })
            .then((response) => {
              console.log(response.data.CommoditiesEntitys)
              this.objproject = response.data.CommoditiesEntitys
              //alert(response.data.listcounts)
              this.listcount= 0+response.data.listcounts
              //alert( this.listcount)
            })

        },
        feedbackMsg(){//用户信息反馈（用户提出建议）
          this.existgood = false //搜索商品结果界面
          this.selfGoodsLook =false //所有已经订阅的商品结果界面
          this.MyContentMsg =false //我的个人信息界面
          this.updatepwdContent =false  //修改密码界面
          this.goodsManageContent = false //商品管理界面
          this.FindOneLog = false//商品价格记录
          this.zhuye= false //主图走马灯
          this.tofeedback = true
          if (this.user_id) {
            this.$prompt('请写下您的意见', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
            }).then(({ value }) => {
              this.$axios.post('https://localhost:888/api/feedbackMsgToSystem', {
                uId: this.user_id,
                userContent: value,

              })
                .then((response) => {
                  this.$message({
                    type: 'success',
                    message: '你的意见是: ' + value
                  });
                })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消输入'
              });
            });

          } else {
            this.$message({
              showClose: true,
              message: '用户未登录，请登录后再进行订阅',
              type: 'error'
            });
          }


        },

      FindOneGoodLog(value3) { //查看某一商品的价格走势图
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
      },

        goToindex(){
          this.$router.push('/');
        },
        //关于管理已订阅商品的方法
        toggleSelection(rows) {
          if (rows) {
            rows.forEach(row => {
              this.$refs.multipleTable.toggleRowSelection(row);
            });
          } else {
            this.$refs.multipleTable.clearSelection();
          }
        },
        handleSelectionChange(val) {
          this.multipleSelection = val;
        },
        togoodsManageContent(){ //转到商品管理界面
          this.existgood = false //搜索商品结果界面
          this.selfGoodsLook =false //所有已经订阅的商品结果界面
          this.MyContentMsg =false //我的个人信息界面
          this.updatepwdContent =false  //修改密码界面
          this.FindOneLog = false//商品价格记录
          this.zhuye= false //主图走马灯
          this.tofeedback = false
          if (this.user_id) {
            let that = this
            this.$axios.post('https://localhost:888/api/SearchSelfGoodsNoPage', {
              uid: this.user_id,
            })
              .then((response) => {
                this.loading = false
                this.goodsManageContent = true //商品管理界面
                console.log(response.data.CommoditiesEntitys)
                this.tableData3 = response.data.CommoditiesEntitys
                //alert(response.data.listcounts)
                //alert( this.listcount)
              })
          } else {
            this.$message({
              showClose: true,
              message: '用户未登录，请登录后再进行订阅',
              type: 'error'
            });
          }

        },
        delSelectedGoods(){
          console.log(this.multipleSelection)
          if (this.user_id) {
            this.$confirm('此操作将永久删除, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              let that = this
              this.$axios.post('https://localhost:888/api/delGoods', this.multipleSelection
              ).then((response) => {
                  console.log(response.data.content.outputMess)
                  this.$message({
                    showClose: true,
                    message: response.data.content.outputMess,
                    type: 'info'
                  });

                  this.togoodsManageContent()
                })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });
            });
          }else{
              this.$message({
                showClose: true,
                message: '用户未登录，请登录后再进行订阅',
                type: 'error'
              });
            }
        },
        CompareSelectedGoods(){
          this.selfGoodsLook =false //所有已经订阅的商品结果界面
          this.MyContentMsg =false //我的个人信息界面
          this.updatepwdContent =false  //修改密码界面
          this.tofeedback =false //用户反馈界面
          this.goodsManageContent = false //商品管理界面
          this.FindOneLog = true//商品价格记录
          this.zhuye= false
          alert("sss")
          console.log(this.multipleSelection)
          var myChart2 = echarts.init(document.getElementById('main2'));
          // 显示标题，图例和空的坐标轴
          var Option = {
            title: {
              text: '折线图堆叠'
            },
            tooltip: {
              trigger: 'axis'
            },
            // legend: {
            //   data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
            // },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            toolbox: {
              feature: {
                saveAsImage: {}
              }
            },
            xAxis: {
              type: 'category',
              boundaryGap: false,
              //data: ['周一','周二','周三','周四','周五','周六','周日']
            },
            yAxis: {
              type: 'value'
            },
            series: []
          };
          if (this.user_id) {
            let that = this
            this.$axios.post('https://localhost:888/goodsLog/CompareGoodsPriceLog', this.multipleSelection)
              .then((response) => {
                console.log(response.data)
                this.existgood = false
                this.selfGoodsLook = false
                let arr = [];
                let arr_date = [];
                let datavue=[]
                for (var j = 0; j < response.data.length; j++) {//json类似一个数组，所以通过循环输出里面
                  let a_price =[]
                  let kkk= response.data[j][0].cid
                  arr.push(kkk)
                  for(var k= 0 ;k<response.data[j].length;k++){
                    a_price.push( response.data[j][k].clPrice)
                    arr_date.push(response.data[j][k].clDateTime)
                  }
                  var objproject = {
                    name :  kkk,
                    type:'line',
                    stack: '总量',
                    data: a_price

                  }
                  datavue.push(objproject);
                }
                myChart2.setOption({
                  legend: {
                    data:arr,
                  },
                  xAxis: {
                    data: arr_date
                  },
                  series: datavue
                });
              })
          } else {
            this.$message({
              showClose: true,
              message: '用户未登录，请登录后再进行查看',
              type: 'error'
            });
          }

          myChart2.setOption(Option);



        }
    }
  }

</script>

<style scoped>
  body{
    margin:0;

  }
  .top_SYSTEM{
    background-image: url("../assets/bg.jpg");
    background-size:100% 100%;
    height:100vw;
    width:100%;

    /*margin: -8px;*/
    /*padding: 0px;*/
    position: absolute;
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

  .title_header{
    /*background-color: azure;*/
    text-align: center;
    line-height: 30px;
    color: cadetblue;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    font-size: larger;
    font-style: italic;
  }
  .el-header{
    /*background-color: #B3C0D1;*/
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-footer {
    /*background-color: #B3C0D1;*/
    color: #333;
    text-align: center;
    line-height: 60px;
    bottom: 4px;
  }
  .el-aside {
    /*height:800px;*/
    /*background-color: #D3DCE6;*/
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-main {
    /*height:2000px;*/
    /*background-color: #E9EEF3;*/
    color: #333;
    text-align: center;
    line-height: 60px;
    min-height:calc(100vw / 750 * 300);  /*屏幕自适应最小宽度*/
    /*max-height: 100vw ;*/
    overflow-y:hidden;
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

    /*height:80%;*/


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
    /*background: white;*/
    /*height:200px;*/
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
  .footer{
    height:30px;
    text-align: center;
    color: white;
    font-style:italic;
    font-size: smaller;
    margin-top:calc(100vw / 750 * 300);
    text-shadow:0 0 5px rgba(0,0,0,0.5);
    /*-webkit-filter:blur(1px);*/
    /*filter:blur(1px);*/

  }
</style>
