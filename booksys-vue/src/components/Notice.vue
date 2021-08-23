<template>
    <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="信息" name="first">
            <el-table
            :data="tableData"
            style="width: 100%"
            height="496"
            @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="50"></el-table-column>
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="描述">
                                <span>{{ props.row.text }}</span>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-table-column>
                <el-table-column label="用户" prop="user" width="100"></el-table-column>
                <el-table-column label="分类" prop="type"></el-table-column>
            </el-table>
            <div>
                <div class="submit"><el-button type="primary" @click="onSubmit">已处理</el-button></div>
            </div>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import axios from 'axios';
export default {
    name: 'notice',
    data(){
        return {
            activeName: 'first',
            tableData: [],
            multipleSelection: [],
        }
    },
    methods:{
        handleClick(tab, event) {
            console.log(tab, event);
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        onSubmit(){
            axios.delete("http://localhost:8081/admin/dealnotice",{
                data: this.multipleSelection
            }).then(resp=>{
                this.tableData=resp.data;
                this.datadeal();
            });
            this.$message({
                message: '成功!',
                type: 'success'
            });
            console.log(this.multipleSelection);
        },
        datadeal(){
            for(var item in this.tableData){
                if(this.tableData[item].type=='1'){
                    this.tableData[item].type='账号问题';
                }
                else if(this.tableData[item].type=='2'){
                    this.tableData[item].type='意见&建议';
                }
            }
        }
    },
    mounted(){
        axios.get("http://localhost:8081/admin/getnotice").then(resp=>{
            this.tableData=resp.data;
            this.datadeal();
        });
    }
}
</script>

<style scoped>
.demo-table-expand {
    font-size: 0;
}
.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}
.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
}

.submit{
    float: right;
    margin-top: 15px;
    margin-left: 50px;
}
</style>