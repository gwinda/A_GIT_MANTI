<template>
  <div id="manage">
    <h1>{{jjjj}}</h1>
    <!--<div v-show="Chuxian">-->
      <!--ffffffffffffffffffffff-->
    <!--</div>-->
    <div class="containcc" v-show="Chuxian" >
      <div class="login-wrap2">
        <!--v-show="showLogin" -->
        <h2>Sign In Now</h2>
        <p   v-show="showTishi2">{{word2}}</p>
        <input type="text" placeholder="E-MAIL" v-model="username" >
        <input type="password" placeholder="PASSWORD" v-model="password">
        <el-button style="margin: auto;"  @click="login" type="primary" align="center" class="sumbit">管理员登录</el-button >
        <br>
        <a href = '/' >返回首页</a>
      </div>
    </div>
    <div v-show="zhuyao">
      <el-container>
        <el-aside width="200px">
          <el-row class="tac" style="color: black;">
            <el-col>
              <el-menu
                default-active="2"
                class="el-menu-vertical-demo"
                @close="handleClose">
                <el-menu-item index="1">
                  <i class="el-icon-setting"></i>
                  <span slot="title">导航一</span>
                </el-menu-item>
                <el-menu-item index="2">
                  <i class="el-icon-menu"></i>
                  <span slot="title">导航二</span>
                </el-menu-item>
                <el-menu-item index="3" disabled>
                  <i class="el-icon-document"></i>
                  <span slot="title">导航三</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <i class="el-icon-setting"></i>
                  <span slot="title">导航四</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <el-main>



          Main</el-main>



      </el-container>

      <h1 >你好{{jjjj}}</h1>

      <div style="padding-left: 20px;">
        <el-col><el-button @click="SeeAllfeedbackMsg">查看所有用户反馈信息</el-button> <el-button @click="SeeAllfeedbackMsg">查看所有用户反馈信息</el-button></el-col>
        <el-col><el-button @click="SeeAllfeedbackMsg">查看所有用户反馈信息</el-button></el-col>
        <el-col><el-button @click="SeeAllfeedbackMsg">查看所有用户反馈信息</el-button></el-col>
        <el-row> <el-button @click="SeeAllfeedbackMsg">查看所有用户反馈信息</el-button><el-button @click="SeeAllfeedbackMsg">查看所有用户反馈信息</el-button></el-row>
        <el-row> <el-button @click="SeeAllfeedbackMsg">查看所有用户反馈信息</el-button></el-row>
        <el-row> <el-button @click="SeeAllfeedbackMsg">查看所有用户反馈信息</el-button></el-row>

      <el-button @click="searchAllUsersMess">查看所有用户个人信息</el-button>
      <el-button @click="downloadSelectedGoods">下载选择数据</el-button>
      </div>
    </div>


    <!--<div class="HELLO" v-show="hahaha">-->
    <!--<div class="containcc" v-show="ABLLL" >-->
      <!--<div class="login-wrap2">-->
        <!--&lt;!&ndash;v-show="showLogin" &ndash;&gt;-->
        <!--<h2>Sign In Now</h2>-->
        <!--<p   v-show="showTishi2">{{word2}}</p>-->
        <!--<input type="text" placeholder="E-MAIL" v-model="username" >-->
        <!--<input type="password" placeholder="PASSWORD" v-model="password">-->
        <!--<el-button style="margin: auto;"  @click="login" type="primary" align="center" class="sumbit">管理员登录</el-button >-->
        <!--<br>-->
        <!--<a href = '/' >返回首页</a>-->
      <!--</div>-->
    <!--</div>-->
    <!--<div v-show="ADAll">-->
      <!--你好<h1 v-model="AAAaid"></h1>-->
      <!--<el-button @click="SeeAllfeedbackMsg">查看所有用户反馈信息</el-button>-->
      <!--<el-button @click="searchAllUsersMess">查看所有用户个人信息</el-button>-->
      <!--<el-button @click="downloadSelectedGoods">下载选择数据</el-button>-->
    <!--</div>-->
    <!--<div v-show="AllgoodsManageContent" style="width:800px;text-align: center;">-->
        <!--<el-table-->
          <!--ref="multipleTable"-->
          <!--:data="tableData3"-->
          <!--tooltip-effect="dark"-->
          <!--style="width: 100%"-->
          <!--@selection-change="handleSelectionChange">-->
          <!--<el-table-column-->
            <!--type="selection"-->
            <!--width="55">-->
          <!--</el-table-column>-->
          <!--<el-table-column-->
            <!--label="商品ID"-->
            <!--width="70">-->
            <!--<template slot-scope="scope">{{ scope.row.cid}}</template>-->
          <!--</el-table-column>-->
          <!--<el-table-column-->
            <!--label="商品链接"-->
            <!--width="250">-->
            <!--<template slot-scope="scope">{{ scope.row.cLink}}</template>-->
          <!--</el-table-column>-->
          <!--<el-table-column-->
            <!--label="商品名称"-->
            <!--show-overflow-tooltip>-->
            <!--<template slot-scope="scope">{{ scope.row.cName}}</template>-->
          <!--</el-table-column>-->
        <!--</el-table>-->
        <!--<div style="margin-top: 20px">-->
          <!--<el-button @click="toggleSelection()">取消选择</el-button>-->
          <!--<el-button @click="delSelectedGoods">删除选中数据</el-button>-->
          <!--<el-button @click="downloadSelectedGoods">下载选择数据</el-button>-->
        <!--</div>-->
      <!--</div>-->

    <!--<div v-show="SeeFeedBack">-->
      <!--<el-table-->
        <!--ref="multipleTable"-->
        <!--:data="tableData2"-->
        <!--tooltip-effect="dark"-->
        <!--style="width: 100%"-->
        <!--@selection-change="handleSelectionChange">-->
        <!--<el-table-column-->
          <!--type="selection"-->
          <!--width="55">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--label="用户ID"-->
          <!--width="70">-->
          <!--<template slot-scope="scope">{{ scope.row.uid}}</template>-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--label="提交时间"-->
          <!--width="250">-->
          <!--<template slot-scope="scope">{{ scope.row.ufcreate_time}}</template>-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--label="用户建议"-->
          <!--show-overflow-tooltip>-->
          <!--<template slot-scope="scope">{{ scope.row.user_content}}</template>-->
        <!--</el-table-column>-->
      <!--</el-table>-->
      <!--<div style="margin-top: 20px">-->
        <!--&lt;!&ndash;<el-button @click="toggleSelection([tableData3[1], tableData3[2]])">切换第二、第三行的选中状态</el-button>&ndash;&gt;-->
        <!--<el-button @click="toggleSelection()">取消选择</el-button>-->
        <!--<el-button @click="DelSelectedfeedbackMsg">删除选中数据</el-button>-->
        <!--<el-button @click="downloadSelectedGoods">下载选择数据</el-button>-->
      <!--</div>-->
    <!--</div>-->
    </div>
  <!--</div>-->


</template>

<script>
    export default {
      name: "manage",
      data() {
        return {
          jjjj:'ddddddddddddd',
          Chuxian:false,
          showTishi2:'',
          username:'',
          password:'',
          word2:'',
          zhuyao:true,
        }
      },
      mounted() {
        this.hahaha=true
      },
      methods: {
        login() {
          let that = this;
          //var filter = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
          if (this.username === "" || this.password === "") {
            alert("请输入账号或密码")
          } else if (this.password.length < 6 || this.password.length > 20) {
            this.word = "密码不符合规范，请重试"
            this.showTishi = true
            this.password = ''
          } else {
            this.$axios.post('https:localhost:888/api/AdministratorLoginCheck', {
              aNumber: this.username,
              aPassword: this.password
            }).then((res) => {
              console.log(res.data)
              //this.word = res.data.content.outputMess
              /*接口的传值是(-1,该用户不存在),(0,密码错误)，同时还会检测管理员账号的值*/
              if (res.data.content.outputMess === '-1') {
                //alert('该用户不存在')
                this.word2 = "该用户不存在"
                this.showTishi2 = true
              } else if (res.data.content.outputMess === '0') {
                this.word2 = "密码输入错误"
                this.showTishi2 = true
              } else if (res.data.content.outputMess === '-2') {
                this.word2 = "登录失败，请检查网络连接或重试"
              } else {
                alert("密码正确")
                this.word2 = "密码正确"
                this.showTishi2 = true
                this.Chuxian = false
                this.zhuyao = true
                this.jjjj=  ""+res.data.content.outputMess
              }
            })
          }
        },
        handleSelectionChange(val) {
          this.multipleSelection = val;
        },
        toggleSelection(rows) {
          if (rows) {
            rows.forEach(row => {
              this.$refs.multipleTable.toggleRowSelection(row);
            });
          } else {
            this.$refs.multipleTable.clearSelection();
          }
        },
        downloadSelectedGoods(){

        },
        SeeAllfeedbackMsg(){

        },
        DelSelectedfeedbackMsg(){

        },
        searchAllUsersMess(){

        },
      }
    }
</script>

<style scoped>

  .containcc h2 {
    color: #8b5c7e;
    font-size: 29px;
    letter-spacing: 2px;
    text-transform: uppercase;
    padding-bottom: 15px;
    text-align: center;
  }

  .containcc h6 {
    font-size: 16px;
    float: right;
    width: 37%;
    color: #fff;
    letter-spacing: 1px;
    margin-top: 8px;
    text-decoration:underline;
  }
  .containcc a{
    color:white;
    line-height: 44px;



  }
  .sumbit{
    padding: 12px 38px;
    font-size: 18px;
    text-transform: uppercase;
    letter-spacing: 2px;
    background: #f0bcb4;
    color: white;
    border: none;
    outline: none;
    display: table;
    cursor: pointer;
    margin: 45px auto 31px;
    transition: 0.5s all;
    -webkit-transition: 0.5s all;
    -o-transition: 0.5s all;
    -moz-transition: 0.5s all;
    -ms-transition: 0.5s all;
  }
  .sumbit:hover {
    background:#8b5c7e;
    transition:0.5s all;
    -webkit-transition:0.5s all;
    -o-transition:0.5s all;
    -moz-transition:0.5s all;
    -ms-transition:0.5s all;
  }
  .containcc a:hover{
    color:#f0bcb4;
    transition:0.5s all;
    -webkit-transition:0.5s all;
    -o-transition:0.5s all;
    -moz-transition:0.5s all;
    -ms-transition:0.5s all;
  }
  body{
    background-image: url("../assets/bg.jpg") ;
    position:fixed;
    top: 0;
    left: 0;
    width:100%;
    height:100%;
    min-width: 1000px;
    z-index:-10;
    zoom: 1;
    background-color: #fff;
    background-repeat: no-repeat;
    background-size: cover;
    -webkit-background-size: cover;
    -o-background-size: cover;
    background-position: center 0;
  }
  .containcc{
    background:rgba(171, 119, 157, 0.27);
    margin-top: 10%;
    margin-left:35%;
    filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr='#4c000000', EndColorStr='#4c000000');
    width:500px;
    height:450px;
    border: darkcyan ;

  }
  .login-wrap2 { padding-left:2%;padding-top:8%;text-align:center;}
  input{display:block; width:300px; height:40px; line-height:20px; margin:0 auto; margin-bottom: 10px; outline:none; border:1px solid #888; padding:10px; box-sizing:border-box;}
  p{color: seashell;}
  button{display:block; width:250px; height:40px;  margin:0 auto; border:none; background-color:#3a8ee6; color:#fff; font-size:16px; }
  /*span{*/
    /*cursor:pointer;*/
    /*color: white*/
  /*}*/
  .tac{
    /*width: 100vw;*/
    height: 100vw;
  }
  span:hover{
    color:#f0bcb4;
    transition:0.5s all;
    -webkit-transition:0.5s all;
    -o-transition:0.5s all;
    -moz-transition:0.5s all;
    -ms-transition:0.5s all;
  }
   .el-header, .el-footer {
     background-color: #B3C0D1;
     color: #333;
     text-align: center;
     line-height: 60px;
   }

  .el-aside {
    /*background-color: #D3DCE6;*/
    color: black;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

</style>
