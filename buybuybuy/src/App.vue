<template>
  <div id="app">
    <!--<el-button type="primary" round @click="testClick">hello</el-button>-->
    <!--<input v-model="hello"/>-->
    <!--<input v-model="hello"/>-->
    <!--<img src="./assets/logo.png">-->

    <router-view/>
  </div>

</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      hello: 'hello world'
    }
  },
  methods: {
    testClick () {
      let that = this
      this.$axios.get('https://localhost:444/api/search65435453456454')
        .then((response) => {
          this.hello = response.data.content.test
        })
    }
  }
}
</script>

<style>
/*#app {*/
  /*font-family: 'Avenir', Helvetica, Arial, sans-serif;*/
  /*-webkit-font-smoothing: antialiased;*/
  /*-moz-osx-font-smoothing: grayscale;*/
  /*text-align: center;*/
  /*color: #2c3e50;*/
  /*margin-top: 60px;*/
/*}*/
</style>
