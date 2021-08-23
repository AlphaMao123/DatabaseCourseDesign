<template>
    <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">

        <el-tab-pane label="可定教材" name="first">
            <el-table
            ref="multipleTable"
            :data="unsubscribeTable"
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
                <div class="title"><label>总金额</label></div>
                <div class="showvalue"><el-input placeholder="0" v-model="input" readonly ></el-input></div>
                <div class="submit"><el-button type="primary" @click="onSubmit">确 定</el-button></div>
            </div>
        </el-tab-pane>

        <el-tab-pane label="已定教材" name="second">
            <el-table
            :data="subscribeTable"
            tooltip-effect="dark"
            style="width: 100%"
            border
            stripe
            height="550">
                <el-table-column prop="name" label="名称" width="250"></el-table-column>
                <el-table-column prop="author" label="作者" width="100"></el-table-column>
                <el-table-column prop="value" label="定价(￥)" width="100"></el-table-column>
                <el-table-column prop="edition" label="版本" width="100"></el-table-column>
                <el-table-column prop="grade" label="年级" width="100"></el-table-column>
                <el-table-column prop="isbn" label="ISBN" width="180"></el-table-column>
                <el-table-column prop="publisher" label="出版社" show-overflow-tooltip></el-table-column>
            </el-table>
        </el-tab-pane>

        <el-tab-pane label="教材查询" name="third">
            <div class="searchbutton">
                <el-button type="primary" circle icon="el-icon-search" @click="Search"></el-button>
            </div>
            <div class="search">
                <el-input v-model="search" placeholder="请输入关键字" clearable=""></el-input>
            </div>
            <el-table
            :data="searchResult"
            tooltip-effect="dark"
            border
            stripe
            style="width: 100%"
            height="495">
                <el-table-column prop="name" label="名称" width="250"></el-table-column>
                <el-table-column prop="author" label="作者" width="100"></el-table-column>
                <el-table-column prop="value" label="定价(￥)" width="100"></el-table-column>
                <el-table-column prop="edition" label="版本" width="100"></el-table-column>
                <el-table-column prop="grade" label="年级" width="100"></el-table-column>
                <el-table-column prop="isbn" label="ISBN" width="180"></el-table-column>
                <el-table-column prop="publisher" label="出版社" show-overflow-tooltip></el-table-column>
            </el-table>
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
                <el-button type="primary" @click="confirm">确 定</el-button>
            </div>
        </el-dialog>
    </el-tabs>
</template>

<script>
import axios from 'axios';
export default {
    name: 'subsribe',
    data(){
        return{
            activeName: 'second',
            input:'',
            search:'',
            dialogTableVisible: false,
            tableData:[],
            subscribeTable: [],
            unsubscribeTable: [],
            multipleSelection: [],
            searchResult:[]
        }
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
            if(tab.name=='third'){
                axios.get("http://localhost:8081/allbook").then(resp=>{
                    this.tableData=resp.data;
                    this.searchResult=resp.data;
                });
            }
            else if(tab.name=='first'){
                axios.get("http://localhost:8081/searchunsub").then(resp=>{
                    this.unsubscribeTable=resp.data;
                });
            }
            else if(tab.name=='second'){
                axios.get("http://localhost:8081/searchsub").then(resp=>{
                    this.subscribeTable=resp.data;
                });
            }
        },
        handleSelectionChange(val) {
            var re=0;
            for(var item in val){
                re+=parseInt(val[item].value);
            }
            console.log(re);
            this.input=re.toString();
            this.multipleSelection = val;
        },
        onSubmit(){
            this.dialogTableVisible=true;
            console.log(this.multipleSelection);
        },
        confirm(){
            axios.post("http://localhost:8081/subscribe",this.multipleSelection).then(resp=>{
                this.unsubscribeTable=resp.data;
            });
            this.dialogTableVisible=false;
            this.$message({message:"选订成功",type:"success"});
        },
        check(item){
            if(this.tableData[item].name&&!this.tableData[item].author){
                return !this.search
                ||this.tableData[item].name.toLowerCase().includes(this.search.toLowerCase());
            }
            else{
                return !this.search
                ||this.tableData[item].name.toLowerCase().includes(this.search.toLowerCase())
                ||this.tableData[item].author.toLowerCase().includes(this.search.toLowerCase());
            }  
        },
        Search(){
            //console.log(!this.search);
            //console.log(this.check(0)); 
            this.searchResult=[];
            for(var item in this.tableData){
                //console.log(item);
                if(this.check(item)){
                    this.searchResult.push(this.tableData[item]);
                }
            }
        },
    },
    mounted(){
        axios.get("http://localhost:8081/searchsub").then(resp=>{
            this.subscribeTable=resp.data
        });
    }
}
</script>

<style scoped>
.showvalue{
    margin-top: 15px;
    float: left;
}

.submit{
    float: right;
    margin-top: 15px;
    margin-left: 50px;
}

.title{
    float: left;
    margin-top: 24px;
    margin-right: 20px;
}

.search{
    float: right;
    margin-bottom: 15px;
    margin-right: 20px;
}

.searchbutton{
    float: right;
}
</style>