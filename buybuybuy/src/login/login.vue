<template>
  <div id="loginandregister">
    <div class="contain">
    <div class="login-wrap" v-show="showLogin">
      <h2>Sign In Now</h2>
      <p   v-show="showTishi">{{word}}</p>
      <input type="text" placeholder="E-MAIL" v-model="username" >
      <input type="password" placeholder="PASSWORD" v-model="password">
      <el-checkbox class ='checkbox' type="checkbox">Remember Me  &nbsp;  &nbsp;  &nbsp;  &nbsp;&nbsp;    </el-checkbox>
      <a  v-on:click="TofindPass" >找回密码</a>
      <br/>
      <el-button style="margin: auto;"  @click="login" type="primary" align="center" class="sumbit">登录</el-button >
      <br>
      <span v-on:click="ToRegister" >没有账号？马上注册</span><br/>
      <a href = '/' >返回首页</a>
    </div>

    <div class="register-wrap" v-show="showRegister">
      <h2>REGISTER NOW </h2>
      <p v-show="showTishi">{{word}}</p>
      <input type="text" placeholder="请输入用户名" v-model="newUsername">
      <input type="text" placeholder="请输入邮箱 eg. 111@mm.com" v-model="newEmail">
      <input type="password" placeholder="请输入密码(6-20位)" v-model="newPassword">
      <el-button  v-on:click="register" type="primary" class="sumbit" >注册</el-button >
      <span v-on:click="ToLogin" >已有账号？马上登录</span><br/>
      <a href = '/' >返回首页</a>
    </div>
     <div class="return-wrap" v-show="returnwrap">
        <h2>FIND PASSWORD</h2>
        <p  v-show="showTishi">{{word}}</p>
        <input type="text" placeholder="E-MAIL" v-model="E_MAIL" >
        <br/>
        <el-button style="margin: auto;"  @click="login" type="primary" align="center" class="sumbit">找回密码</el-button >
        <br/>
       &nbsp;  <span v-on:click="ToRegister" >没有账号？马上注册</span> &nbsp; &nbsp;
       <span v-on:click="ToLogin" >已有账号？马上登录</span><br/>
       <a href = '/' >返回首页</a>
      </div>
    </div>
  </div>
</template>

<style>
  .checkbox{
    margin-left: 1px;
    padding-left: 1px;
  }
  .contain h2 {
    color: #8b5c7e;
    font-size: 29px;
    letter-spacing: 2px;
    text-transform: uppercase;
    padding-bottom: 15px;
    text-align: center;
  }
  .w3layouts-main {
    width: 30%;
    margin:5em auto;
    background:rgba(171, 119, 157, 0.27);
    padding: 42px 35px;
  }
  /*--//header--*/
  /*--login--*/
  input.ggg{
    width: 80%;
    border: 1px solid #fff;
    outline: none;
    font-size: 14px;
    color: white;
    margin: 14px 0px;
    background: none;
  }
  .contain h6 {
    font-size: 16px;
    float: right;
    width: 37%;
    color: #fff;
    letter-spacing: 1px;
    margin-top: 8px;
    text-decoration:underline;
  }
  .contain a{
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
  .contain a:hover{
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
  .contain{
    background:rgba(171, 119, 157, 0.27);
    margin-top: 10%;
    margin-left:35%;
    filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr='#4c000000', EndColorStr='#4c000000');
    width:500px;
    height:450px;
    border: darkcyan ;

  }
  .return-wrap{ padding-left:2%;padding-top:8%;text-align:center;}
  .login-wrap { padding-left:2%;padding-top:8%;text-align:center;}
  .register-wrap{ padding-left:4%;padding-top:8%;text-align:center;}
  input{display:block; width:300px; height:40px; line-height:20px; margin:0 auto; margin-bottom: 10px; outline:none; border:1px solid #888; padding:10px; box-sizing:border-box;}
  p{color: seashell;}
  button{display:block; width:250px; height:40px; line-height: 40px; margin:0 auto; border:none; background-color:#3a8ee6; color:#fff; font-size:16px; margin-bottom:5px;}
  span{
    cursor:pointer;
    color: white
  }
  span:hover{
    color:#f0bcb4;
    transition:0.5s all;
    -webkit-transition:0.5s all;
    -o-transition:0.5s all;
    -moz-transition:0.5s all;
    -ms-transition:0.5s all;
  }

</style>

<script>
  import { setCookie,getCookie,delCookie } from '../assets/js/cookies.js'
  export default{
    name:'loginandregister',
    data(){
      return{
        showLogin: true,
        showRegister: false,
        returnwrap:false,
        showTishi: false,
        E_MAIL:'',
        word: '',
        wordTip: '',
        username: '',
        password: '',
        newUsername: '',
        newPassword: '',
        newEmail:''

      }
    },
    mounted(){
      /*页面挂载获取cookie，如果存在username的cookie，则跳转到主页，不需登录*/
      if(getCookie('username')){
        this.$router.push('/')
      }
    },
    methods:{
      login(){
        let that = this;
        var filter=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
        if(this.username ==="" || this.password === ""){

          alert("请输入邮箱或密码")
        }else if(!filter.test(this.username)){
          this.word = "邮箱格式错误，请重试"
          this.showTishi = true
          this.username = ''
          this.password = ''
        }else if(this.password.length<6||this.password.length>20){
          this.word = "密码不符合规范，请重试"
          this.showTishi = true
          this.password = ''
        }else {
          this.$axios.post('https:localhost:888/api/loginCheck', {
            uNumber: this.username,
            uPassWord: this.password
          }).then((res) => {
            console.log(res.data)
            //this.word = res.data.content.outputMess
            /*接口的传值是(-1,该用户不存在),(0,密码错误)，同时还会检测管理员账号的值*/
            if(res.data.content.outputMess === '-1'){
              //alert('该用户不存在')
              this.word = "该用户不存在"
              this.showTishi = true
            }else if(res.data.content.outputMess === '0'){
              this.word = "密码输入错误"
              this.showTishi = true
            }else if(res.data.content.outputMess === '-2'){
              this.word = "登录失败，请检查网络连接或重试"
            }else{
                this.word = "密码正确"
                this.showTishi = true
              alert(res.data.content.userId)
                this.$router.push({path:'/',query: {
                     user_id: res.data.content.userId ,
                     name: res.data.content.username,
                 }});
            }
          })
        }
      },
      ToRegister(){
        this.showRegister = true;
        this.showLogin = false;
        this.returnwrap = false;
        this.word = ""
      },
      ToLogin(){
        this.showRegister = false;
        this.showLogin = true;
        this.returnwrap = false;
        this.word = ""
      },
      TofindPass(){
        this.showRegister = false;
        this.showLogin = false;
        this.returnwrap = true;
        this.word = ""
      },
      register(){
        var filter=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
        let that = this;
        //alert(this.newUsername+this.newEmail+this.newPassword)
        if(this.newUsername =="" || this.newEmail == "" ||this.newPassword ==""){
           alert("请输入用户名或邮箱或密码")
        }else if(!filter.test(this.newEmail)){
          this.word = "邮箱格式错误，请重试"
          this.showTishi = true
          this.newEmail = ''
          this.newPassword = ''
        }else if(this.newPassword.length<6||this.newPassword.length>20){
          this.word = "密码为6至20 位，请重试"
          this.showTishi = true
          this.newPassword = ''
        }else {
          this.$axios.post('https://localhost:888/api/register', {
            uname:this.newUsername,
            uNumber: this.newEmail,
            uPassWord: this.newPassword
          }).then((res) => {
            console.log(res)
            this.word = res.data.content.outputMess
            /*接口的传值是(-1,该用户不存在),(0,密码错误)，同时还会检测管理员账号的值*/
            if(res.data.content.outputMess == '-1'){
              alert('该用户不存在')
              this.word = "该邮箱已被注册，请换个邮箱重试"
              this.showTishi = true
            }else if(res.data.content.outputMess == '0'){
              this.word = "注册失败，请检查连接或重试"
              this.showTishi = true
              }else{
                this.word = "注册成功,请登录"
                this.showTishi = true
                this.showRegister = false;
                this.showLogin = true;
            }
          })
        }
      },
    }
  }

</script>>
