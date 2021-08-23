<template>
    <div class="background">
        <el-container>
            <el-header></el-header>
            <el-main>
                <div class="title">
                    您好，请登录！
                </div>
                <div class="loginform">
                    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                        <el-form-item label="用户名" prop="username">
                            <el-input placeholder="学号" v-model="form.username" clearable></el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="passward">
                            <el-input placeholder="密码" v-model="form.passward" clearable show-password></el-input>
                        </el-form-item>
                        <el-form-item label="登录方式" prop="identity">
                            <el-select v-model="form.identity" placeholder="请选择">
                                <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <el-row :gutter="20">
                                <el-col :span="10"><el-button icon="el-icon-edit" circle @click="Signup"></el-button></el-col>
                                <el-col :span="11"><el-button icon="el-icon-check" circle @click="Signin"></el-button></el-col>
                            </el-row>
                        </el-form-item>
                    </el-form>
                </div>
            </el-main>
        </el-container> 
    </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'login',
  data() {
      return {
        form: {
            username: '',
            passward: '',
            identity: '',
            
        },
        options: [
                {
                    value: 'root',
                    label: '管理员'
                },
                {
                    value: 'student',
                    label: '学生'
                }
        ],
        rules:{
            username:[
                {required: true, message:'请输入', trigger: 'blur'}
            ],
            passward:[
                {required: true, message:'请输入', trigger: 'blur'}
            ],
            identity:[
                {required: true, message:'请选择', trigger: 'change'}
            ]
        }
      }
    },
    methods: {
        Signin: function(){
            //console.log(this.form);
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    if(this.form.identity=='student'){
                        axios.get('http://localhost:8081/login',{
                            params:{
                                id: this.form.username,
                                passward: this.form.passward
                            }
                        }).then(resp=>{
                            if(resp.data=="success")
                                this.$router.push('/home');
                            else if(resp.data=="passworderr")
                                this.$message({message: '密码错误',type: 'error'});
                            else if(resp.data=="noninform")
                                this.$message({message: '未找到用户信息请注册',type: 'error'});
                        });
                    }
                    else if(this.form.identity=='root'){
                        axios.get('http://localhost:8081/admin/login',{
                            params:{
                                name: this.form.username,
                                passward: this.form.passward
                            }
                        }).then(resp=>{
                            if(resp.data=="success")
                            this.$router.push('/admin');
                            else if(resp.data=="passworderr")
                                this.$message({message: '密码错误',type: 'error'});
                            else if(resp.data=="noninform")
                                this.$message({message: '未找到用户信息请注册',type: 'error'});
                        });
                    }
                }
                else{
                    this.$message({message: "请输入登录信息", type:"error"});
                }
            })
            //this.$router.push('/admin');
        },
        Signup: function(){
            this.$router.push('/register');
      }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.background{
    background:url("../assets/IMG_20210324_130934.jpg");
    width:100%;
    height:100%;
    position:fixed;
    margin: 0px;
    padding: 0px;
    background-size:100% 100%;
}

.el-main {
    text-align: center;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    margin-left: 540px;
    margin-right: 540px;
    margin-top: 130px;
    height: 360px;
    background-color: rgba(64,158,255,0.85);
    border-radius: 4px;
}

.title{
    font-family: "PingFang SC";
    font-size: 18px;
    margin-bottom: 40px;
}

.loginform{
    text-align: left;
}

.el-row{
    text-align: center;
}

</style>
