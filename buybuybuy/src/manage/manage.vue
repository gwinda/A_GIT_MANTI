<template>
  <div id="manage" style="margin: -8px;">
    <div v-show="HouTai">
      <label> 欢迎，管理员 </label>
    <header class="header">
      <el-row>
        <el-col :span="20">
          <el-menu default-active="0" class="el-menu-demo" mode="horizontal" @select="" text-color="black" >
            <el-menu-item index="1"><span @click="SeeAllfeedbackMsg" style="color: black">反馈管理</span></el-menu-item>
            <el-menu-item index="2"><span @click="searchAllUsersMess" style="color: black">会员管理</span></el-menu-item>
            <el-menu-item index="3"><span @click="seeAllGoods" style="color: black">商品管理</span></el-menu-item>
            <el-menu-item index="4">系统设置</el-menu-item>
            <el-menu-item index="5">安全退出</el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </header>
    <!--<div style="position: relative;height: 60px;width: 100%;"></div>-->

    <main>
      <!-- 左侧导航 -->
      <div class="main-left">
        <el-menu default-active="/activePublic"  text-color="black" >
          <el-menu-item index ='1' :class="{'isActive': !active}"><i class="el-icon-menu"></i><span @click="SeeAllfeedbackMsg" style="color: black">反馈管理</span></el-menu-item>
          <el-menu-item index="2"  :class="{'isActive': !active}"><i class="el-icon-menu"></i><span @click="searchAllUsersMess" style="color: black">会员管理</span></el-menu-item>
          <el-menu-item index="3"  :class="{'isActive': !active}"><i class="el-icon-menu"></i><span @click="seeAllGoods" style="color: black">商品管理</span></el-menu-item>
          <el-menu-item index="4"  :class="{'isActive': !active}"><i class="el-icon-menu"></i>系统设置</el-menu-item>
          <el-menu-item index="5"  :class="{'isActive': !active}"><i class="el-icon-menu"></i>安全退出</el-menu-item>
        </el-menu>
      </div>
      <!-- 右侧主内容区 -->
      <div class="main-right" >

        <div v-show="SeeFeedBack" style="color: black">
          <el-table
          ref="multipleTable"
          :data="tableData2"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange" >
            <el-table-column
            type="selection"
            width="55">
            </el-table-column>
            <el-table-column
            label="用户ID"
            width="70">
            <template slot-scope="scope" style="color: dodgerblue">{{ scope.row.uId}}</template>
            </el-table-column>
            <el-table-column
            label="提交时间"
            width="250">
            <template slot-scope="scope">{{ scope.row.ufCreateTime}}</template>
            </el-table-column>
            <el-table-column
            label="用户建议"
            show-overflow-tooltip>
            <template slot-scope="scope">{{ scope.row.userContent}}</template>
            </el-table-column>
          </el-table>
          <div style="margin-top: 20px">
            <!--<el-button @click="toggleSelection([tableData3[1], tableData3[2]])">切换第二、第三行的选中状态</el-button>-->
            <el-button @click="toggleSelection()">取消选择</el-button>
            <el-button @click="DelSelectedfeedbackMsg">删除选中数据</el-button>
            <el-button @click="downloadSelectedGoods">下载选择数据</el-button>
          </div>
        </div>


        <div v-show="SeeAllUsers"  style="color: black">
          <el-table
            ref="multipleTable"
            :data="usersData"
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange" >
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column
              label="用户ID"
              width="70">
              <template slot-scope="scope" style="color: dodgerblue">{{ scope.row.uid}}</template>
            </el-table-column>
            <el-table-column
              label="用户账号"
              width="250">
              <template slot-scope="scope">{{ scope.row.uNumber}}</template>
            </el-table-column>
            <el-table-column
              label="用户昵称"
              show-overflow-tooltip>
              <template slot-scope="scope">{{ scope.row.uname}}</template>
            </el-table-column>
          </el-table>
          <div style="margin-top: 20px">
            <!--<el-button @click="toggleSelection([tableData3[1], tableData3[2]])">切换第二、第三行的选中状态</el-button>-->
            <el-button @click="toggleSelection()">取消选择</el-button>
            <el-button @click="DelSelectedfeedbackMsg">删除选中数据</el-button>
            <el-button @click="downloadSelectedGoods">下载选择数据</el-button>
          </div>
        </div>

        <div v-show="AllgoodsManageContent" style="width:800px;text-align: center;">
          <el-table
          ref="multipleTable"
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
          <el-button @click="toggleSelection()">取消选择</el-button>
          <el-button @click="DelSelectedfeedbackMsg">删除选中数据</el-button>
          <el-button @click="downloadSelectedGoods">下载选择数据</el-button>
          </div>
        </div>

      </div>
    </main>
    </div>
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

    </div>


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
          active:true,
          HouTai:false,
          AllgoodsManageContent:false,
          tableData3:[],
          tableData_goods:[],
          SeeFeedBack:false,
          tableData2:[],
          SeeAllUsers:false,
          usersData:[],

        }
      },
      mounted() {
        this.hahaha=true
        this.Chuxian= true;
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
                this.HouTai = true
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
        seeAllGoods(){
          this.SeeAllUsers= false
          //this.AllgoodsManageContent =false
          this.SeeFeedBack = false
          let that = this
          if(!this.user_id){
          this.$axios.post('https://localhost:888/api/seeAllGoods',{
              uid: this.user_id,
            })
            .then((response) => {
              this.AllgoodsManageContent = true //用户反馈界面
              console.log(response.data)
              this.tableData3 = response.data.CommoditiesEntitys
            })
          }else {
            this.$message({
            showClose: true,
            message: '无权限',
            type: 'error'
          });
        }
        },
        delSelectedGoods(){
          if (!this.user_id) {
            let that = this
            this.$axios.post('https://localhost:888/api/delSelectedGoods', this.multipleSelection)
              .then((response) => {

                console.log(response.data.content.outputMess)
                //this.tableData3 = response.data.CommoditiesEntitys
                this.$message({
                  showClose: true,
                  message: response.data.content.outputMess,
                  type: 'success'
                });
              })
          } else {
            this.$message({
              showClose: true,
              message: '无权限',
              type: 'error'
            });
          }
        },
        downloadSelectedGoods(){

        },
        SeeAllfeedbackMsg(){
          this.SeeAllUsers= false
          this.AllgoodsManageContent =false
          if (!this.user_id) {
            let that = this
            this.$axios.post('https://localhost:888/api/SeeAllfeedbackMsg', {
              uid: this.user_id,
            })
              .then((response) => {
                this.SeeFeedBack = true //用户反馈界面
                this.tableData2 = response.data
                console.log(response.data)

              })
          } else {
            this.$message({
              showClose: true,
              message: '无权限',
              type: 'error'
            });
          }


        },
        DelSelectedfeedbackMsg(){
          if (!this.user_id) {
            let that = this
            this.$axios.post('https://localhost:888/api/DelSelectedfeedbackMsg', this.multipleSelection)
              .then((response) => {

                console.log(response.data)
                this.SeeAllfeedbackMsg()
                //this.tableData3 = response.data.CommoditiesEntitys
                this.$message({
                  showClose: true,
                  message: response.data.content.outputMess,
                  type: 'info'
                });
              })
          } else {
            this.$message({
              showClose: true,
              message: '无权限',
              type: 'error'
            });
          }
         // alert("DelSelectedfeedbackMsg")

        },
        searchAllUsersMess(){ //查看所有用户信息
          //this.SeeAllUsers= false
          this.AllgoodsManageContent =false
          this.SeeFeedBack = false
          if (!this.user_id) {
            let that = this
            this.$axios.post('https://localhost:888/api/searchAllUsersMess', {
              user_id :this.user_id,
            })
              .then((response) => {
                this.SeeAllUsers = true //用户反馈界面
                console.log(response.data)
                this.usersData = response.data.CommoditiesEntitys
              })
          } else {
            this.$message({
              showClose: true,
              message: '无权限',
              type: 'error'
            });
          }

        },
      }
    }
</script>

<style scoped>
  body{
    margin: 0;
    padding: 0;
  }
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
  /* 头部导航 */
  header{z-index: 1000;min-width: 1200px;transition: all 0.5s ease; border-top: solid 4px #3091F2; background-color: #fff; box-shadow: 0 2px 4px 0 rgba(0,0,0,.12),0 0 6px 0 rgba(0,0,0,.04); }
  header.header-fixed{position: fixed;top: 0;left: 0;right: 0;}
  header .el-menu-demo{padding-left: 300px!important;}
  /* 主内容区 */
  main{ display: -webkit-box; display: -ms-flexbox; display: flex; min-height: 800px; border: solid 40px #E9ECF1; background-color: #FCFCFC; }
  main .main-left{text-align: center;width: 200px;float: left;}
  main .main-right{-webkit-box-flex: 1; -ms-flex: 1; flex: 1; background-color: #fff; padding: 50px 70px; }

  main .el-menu{background-color: transparent!important;}

</style>
