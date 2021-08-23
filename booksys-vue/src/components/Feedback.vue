<template>
    <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="信息反馈" name="first">
            <div class="feedback">
                <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                    <el-form-item label="问题分类" prop="type">
                        <el-select v-model="form.type" placeholder="请选择">
                            <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="问题描述" prop="text">
                        <el-input type="textarea" :rows="15" placeholder="请输入内容" v-model="form.text" maxlength="240" show-word-limit></el-input>
                    </el-form-item>
                    <el-form-item >
                        <div class="submit">
                            <el-button type="primary" @click="Submit">提 交</el-button>
                        </div>
                    </el-form-item>
                </el-form>
            </div>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import axios from 'axios';
export default {
    name: 'subsribe',
    data(){
        return{
            activeName: 'first',
            form: {
                type: '',
                text: ''
            },
             options: [
                {
                    value: '1',
                    label: '账号问题'
                },
                {
                    value: '2',
                    label: '意见&建议'
                }
            ],
            rules: {
                type:[
                    {required: true,message: "请选择反馈类型",trigger: "change"}
                ],
                text:[
                    {required: true,message: "请输入问题描述",trigger: "blur"}
                ]
            }
        }
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        },
        Submit(){
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    axios.post("http://localhost:8081/sendinform",this.form).then(resp=>{
                        console.log(resp.data);
                    });
                    this.$message({message: "提交成功",type: "success"});
                }
            })
            this.form.type='';
            this.form.text='';
        }
    }
}
</script>

<style scoped>
.el-tabs{
    height: 619px;
}

.feedback{
    margin-left: 33%;
    margin-right: 37%;
}

.submit{
    float: right;
}
</style>