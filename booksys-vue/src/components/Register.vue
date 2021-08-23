<template>
    <div class="background">
        <el-container>
            <el-header></el-header>
            <el-main>
                <div class="title">
                    注册
                </div>
                <div class="registerform">
                    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                        <el-form-item label="用户名" prop="username">
                            <el-input placeholder="请输入内容" v-model="form.username" clearable></el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="passward">
                            <el-input placeholder="请输入内容" v-model="form.passward" clearable show-password></el-input>
                        </el-form-item>
                        <el-form-item label="确认密码" prop="passward_c">
                            <el-input placeholder="请输入内容" v-model="form.passward_c" clearable show-password></el-input>
                        </el-form-item>
                        <el-form-item label="电子邮箱" prop="email">
                            <el-input placeholder="请输入内容" v-model="form.email" clearable></el-input>
                        </el-form-item>
                        <el-form-item label="年级" prop="grade">
                            <el-select v-model="form.grade" placeholder="请选择">
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
                                <el-col :span="10"><el-button icon="el-icon-back" circle @click="Back"></el-button></el-col>
                                <el-col :span="11"><el-button icon="el-icon-check" circle @click="Next"></el-button></el-col>
                            </el-row>
                        </el-form-item>
                    </el-form>
                </div>
            </el-main>
        </el-container> 
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: 'register',
    data(){
        var validatepass = (rule,value,callback) =>{
            if (value == '') {
                callback(new Error('请输入密码'));
            } 
            else {
                callback();
            }
        };
        var validatepass2 = (rule,value,callback) =>{
            if (value == '') {
                callback(new Error('请再次输入密码'));
            } 
            else if (value !== this.form.passward) {
                callback(new Error('两次输入密码不一致!'));
            } 
            else {
                callback();
            }
        };
        return {
            form: {
                username: '',
                passward: '',
                passward_c: '',
                email: '',
                grade: '',
            },
            options: [
                {
                    value: '1',
                    label: '大一'
                },
                {
                    value: '2',
                    label: '大二'
                },
                {
                    value: '3',
                    label: '大三'
                },
                {
                    value: '4',
                    label: '大四'
                }
            ],
            rules:{
                username:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                email:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                grade:[
                    {required: true, message:'请选择', trigger: 'change'}
                ],
                passward:[
                    {validator: validatepass, trigger: 'blur'}
                ],
                passward_c:[
                    {validator: validatepass2, trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        Back: function(){
            this.$router.push("/");
        },
        Next: function(){
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    axios.post("http://localhost:8081/register",{
                        id: this.form.username,
                        passward: this.form.passward,
                        email: this.form.email,
                        grade: this.form.grade
                    }).then(resp=>{
                        if(resp.data==true){
                            this.$message({message: "注册成功",type: "success"});
                            this.$router.push("/");
                        }
                        else{
                            this.$message({message: "用户已存在",type: "error"});
                        }
                    });
                }
                else{
                    this.$message({message:"请完善注册信息", type: "error"});
                }
            })
        }
    }
}
</script>

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
    margin-top: 50px;
    height: 480px;
    background-color: rgba(64,158,255,0.85);
    border-radius: 4px;
}

.title{
    font-family: "PingFang SC";
    font-size: 18px;
    margin-bottom: 40px;
}

.registerform{
    text-align: left;
}

.el-row{
    text-align: center;
}
</style>