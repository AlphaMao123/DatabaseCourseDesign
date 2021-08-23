<template>
    <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">

        <el-tab-pane label="退订教材" name="first">
            <el-table
            ref="multipleTable"
            :data="tableData"
            tooltip-effect="dark"
            style="width: 100%"
            border
            stripe
            height="495"
            @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="40"></el-table-column>
                <el-table-column prop="name" label="名称" width="250"></el-table-column>
                <el-table-column prop="author" label="作者" width="100"></el-table-column>
                <el-table-column prop="value" label="定价(￥)" width="100"></el-table-column>
                <el-table-column prop="edition" label="版本" width="100"></el-table-column>
                <el-table-column prop="grade" label="年级" width="100"></el-table-column>
                <el-table-column prop="isbn" label="ISBN" width="180"></el-table-column>
                <el-table-column prop="publisher" label="出版社" show-overflow-tooltip></el-table-column>
            </el-table>
            <div>
                <div class="submit"><el-button type="danger" @click="onSubmit">退 订</el-button></div>
            </div>
        </el-tab-pane>

        <el-dialog title="请确认" :visible.sync="dialogTableVisible">
            <el-table :data="multipleSelection">
                <el-table-column prop="name" label="名称" width="250"></el-table-column>
                <el-table-column prop="author" label="作者" width="100"></el-table-column>
                <el-table-column prop="value" label="定价(￥)" width="100"></el-table-column>
                <el-table-column prop="edition" label="版本" width="100"></el-table-column>
                <el-table-column prop="grade" label="年级" width="100"></el-table-column>
                <el-table-column prop="isbn" label="ISBN" width="180"></el-table-column>
                <el-table-column prop="publisher" label="出版社" show-overflow-tooltip></el-table-column>
            </el-table>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogTableVisible = false">取 消</el-button>
                <el-button type="danger" @click="confirm">确 定</el-button>
            </div>
        </el-dialog>
    </el-tabs>
</template>

<script>
import axios from 'axios'

export default {
    name: 'subsribe',
    data(){
        return{
            activeName: 'first',
            dialogTableVisible: false,
            tableData: [],
            multipleSelection: [],
        }
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        },
        onSubmit(){
            this.dialogTableVisible=true;
            console.log(this.multipleSelection);
        },
        confirm(){
            axios.delete("http://localhost:8081/desubscribe",{
                data: this.multipleSelection
            }).then(resp=>{
                this.tableData=resp.data;
            });
            this.dialogTableVisible=false;
            this.$message({message: "退订成功",type: "success"});
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
    },
    mounted(){
        axios.get("http://localhost:8081/searchsub").then(resp=>{
            this.tableData=resp.data 
        });
    }
}
</script>

<style scoped>
.submit{
    float: right;
    margin-top: 15px;
    margin-left: 50px;
}
</style>