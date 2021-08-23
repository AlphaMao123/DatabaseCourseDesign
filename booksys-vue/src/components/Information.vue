<template>
    <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="个人信息" name="first">
            <div class="form">
                <el-form ref="form" :rules="rules1" :model="form" label-width="80px">
                    <el-form-item>
                        <div class="avatar"><el-avatar :size="100" :src="circleUrl"></el-avatar></div>
                    </el-form-item>
                    <el-form-item label="学号">
                        <el-input v-model="form.id" readonly></el-input>
                    </el-form-item>
                    <el-form-item label="姓名" prop="name">
                        <el-input v-model="form.name" ></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="sex">
                        <el-radio v-model="form.sex" label="1" >男</el-radio>
                        <el-radio v-model="form.sex" label="2" >女</el-radio>
                    </el-form-item>
                    <el-form-item label="年级" prop="grade">
                        <el-input v-model="form.grade" ></el-input>
                    </el-form-item>
                    <el-form-item label="电子邮箱" prop="email">
                        <el-input v-model="form.email" ></el-input>
                    </el-form-item>
                    <el-form-item >
                        <div class="submit">
                            <el-button type="primary" @click="modinform">修改信息</el-button>
                            <el-button type="primary" @click="modcypher">修改密码</el-button>
                        </div>
                    </el-form-item>
                </el-form>
            </div>
            <el-dialog title="修改密码" :visible.sync="dialogcypherVisible" width="30%">
                <el-form ref="cypher_f" :rules="rules" :model="cypher_f" label-width="80px">
                    <el-form-item label="新密码" prop="passward">
                        <el-input v-model="cypher_f.passward" show-password></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="passward_c">
                        <el-input v-model="cypher_f.passward_c" show-password></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogcypherVisible = false">取 消</el-button>
                    <el-button type="primary" @click="confirm_1">确 定</el-button>
                </div>
            </el-dialog>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import axios from 'axios';
export default {
    name: 'subsribe',
    data(){
        var validatePass = (rule, value, callback) => {
            if (value == '') {
                callback(new Error('请输入密码'));
            } 
            else {
                // if (this.cypher_f.passward !== '') {
                //     this.$refs.ruleForm.validateField('checkPass');
                // }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value == '') {
                callback(new Error('请再次输入密码'));
            } 
            else if (value !== this.cypher_f.passward) {
                callback(new Error('两次输入密码不一致!'));
            } 
            else {
                callback();
            }
        };
        return{
            activeName: 'first',
            dialogcypherVisible: false,
            circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
            form: {
                id: '',
                name: '',
                sex: '',
                grade:'',
                email: '',
            },
            cypher_f: {
                passward: '',
                passward_c: ''
            },
            rules: {
                passward: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                passward_c: [
                    { validator: validatePass2, trigger: 'blur' }
                ],
            },
            rules1: {
                name: [
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                sex: [
                    {required: true, message:'请输入', trigger: 'change'}
                ],
                grade: [
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                email: [
                    {required: true, message:'请输入', trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        },
        modinform(){
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    axios.put("http://localhost:8081/updateinform",this.form).then(resp=>{
                    this.form=resp.data
                });
                    this.$message({message: "修改成功" ,type: "success"});
                }
                else{
                    this.$message({message: "请检查提交的信息是否正确" ,type: "error"});
                }
            })

        },
        modcypher(){
            this.dialogcypherVisible=true;
        },
        confirm_1(){
            //console.log(this.cypher_f);
            this.$refs['cypher_f'].validate((valid) => {
                if (valid) {
                    axios.put("http://localhost:8081/updatecypher",this.cypher_f).then(resp=>{
                        console.log(resp.data);
                    });
                    this.$message({message: "修改成功" ,type: "success"});
                    this.cypher_f.passward='';
                    this.cypher_f.passward_c='';
                    this.dialogcypherVisible=false;
                }
            })
            
        }
    },
    mounted(){
        axios.get("http://localhost:8081/getinform").then(resp=>{
            this.form=resp.data;
        });
    }
}
</script>

<style scoped>
.form{
    margin-left: 33%;
    margin-right: 37%;
}

.el-tabs{
    height: 619px;
}

.avatar{
    margin-left: 120px;
    margin-right: 120px;
}

.submit{
    margin-left: 65px;
}
</style>