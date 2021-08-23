<template>
    <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="管理教材" name="first">
            <div class="searchbutton">
                <el-button type="primary" circle icon="el-icon-search" @click="Search"></el-button>
            </div>
            <div class="search">
                <el-input v-model="search" placeholder="请输入关键字" clearable=""></el-input>
            </div>
            <el-table
            ref="multipleTable"
            :data="searchResult"
            tooltip-effect="dark"
            style="width: 100%"
            border
            stripe
            height="441"
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
                <div class="submit"><el-button type="primary" @click="onSubmit">添 加</el-button></div>
                <div class="submit"><el-button type="success" @click="onUpdate">更 新</el-button></div>
                <div class="submit"><el-button type="danger" @click="onDelete">删 除</el-button></div>
            </div>
        </el-tab-pane>
    
        <el-dialog title="请确认" :visible.sync="dialogDeleteVisible">
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
                <el-button @click="dialogDeleteVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirm">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="添加" :visible.sync="dialogInsertVisible" width="30%" top="7vh">
            <el-form ref="insert_f" :rules="rules" :model="insert_f" label-width="80px">
                <el-form-item label="书名" prop="name">
                    <el-input v-model="insert_f.name"></el-input>
                </el-form-item>
                <el-form-item label="作者" prop="author">
                    <el-input v-model="insert_f.author"></el-input>
                </el-form-item>
                <el-form-item label="价格" prop="value">
                    <el-input v-model="insert_f.value"></el-input>
                </el-form-item>
                <el-form-item label="版本" prop="edition">
                    <el-input v-model="insert_f.edition"></el-input>
                </el-form-item>
                <el-form-item label="ISBN" prop="isbn">
                    <el-input v-model="insert_f.isbn"></el-input>
                </el-form-item>
                <el-form-item label="出版社" prop="publisher">
                    <el-input v-model="insert_f.publisher"></el-input>
                </el-form-item>
                <el-form-item label="年级" prop="grade">
                    <el-input v-model="insert_f.grade"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogInsertVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirm_1">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog :title="title" :visible.sync="dialogUpdateVisible" width="30%" top="7vh">
            <el-form ref="update_f" :rules="rules1" :model="update_f" label-width="80px">
                <el-form-item label="书名" prop="name">
                    <el-input v-model="update_f.name"></el-input>
                </el-form-item>
                <el-form-item label="作者" prop="author">
                    <el-input v-model="update_f.author"></el-input>
                </el-form-item>
                <el-form-item label="价格" prop="value">
                    <el-input v-model="update_f.value"></el-input>
                </el-form-item>
                <el-form-item label="版本" prop="edition">
                    <el-input v-model="update_f.edition"></el-input>
                </el-form-item>
                <el-form-item label="ISBN" prop="isbn">
                    <el-input v-model="update_f.isbn"></el-input>
                </el-form-item>
                <el-form-item label="出版社" prop="publisher">
                    <el-input v-model="update_f.publisher"></el-input>
                </el-form-item>
                <el-form-item label="年级" prop="grade">
                    <el-input v-model="update_f.grade"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogUpdateVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirm_2">确 定</el-button>
            </div>
        </el-dialog>
    </el-tabs>
</template>

<script>
import axios from 'axios';
export default {
    name: 'manage',
    data(){
        return {
            activeName: 'first',
            input:'',
            search:'',
            title: '更新',
            dialogDeleteVisible: false,
            dialogInsertVisible: false,
            dialogUpdateVisible: false,
            tableData: [],
            insert_f: {
                name: '',
                author: '',
                value: '',
                edition: '',
                isbn: '',
                publisher: '',
                grade:""
            },
            update_f: {
                name: '',
                author: '',
                value: '',
                edition: '',
                isbn: '',
                publisher: '',
                grade: ""
            },
            rules:{
                name:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                author:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                value:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                edition:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                isbn:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                publisher:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                grade:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ]
            },
            rules1:{
                name:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                author:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                value:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                edition:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                isbn:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                publisher:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ],
                grade:[
                    {required: true, message:'请输入', trigger: 'blur'}
                ]
            },
            multipleSelection: [],
            searchResult:[]
        }
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
            if(tab.name=='first'){
                this.Search();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
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
                console.log(item);
                if(this.check(item)){
                    this.searchResult.push(this.tableData[item]);
                }
            }
        },
        onSubmit(){
            //生成表单
            this.dialogInsertVisible=true;
        },
        onUpdate(){
            //生成表单
            this.dialogUpdateVisible=true;
        },
        onDelete(){
            this.dialogDeleteVisible=true;
        },
        confirm(){//删除的确认
            axios.delete("http://localhost:8081/admin/deletebook",{
                data: this.multipleSelection
            }).then(resp=>{
                this.tableData=resp.data;
                this.searchResult=resp.data;
            });
            this.dialogDeleteVisible=false;
            this.$message({message:"删除成功",type:"success"});
        },
        confirm_1(){//添加的确认
            this.$refs['insert_f'].validate((valid) => {
                if (valid) {
                    axios.post("http://localhost:8081/admin/addbook",this.insert_f).then(resp=>{
                        this.tableData=resp.data;
                        this.searchResult=resp.data;
                    });
                    this.insert_f.name='';
                    this.insert_f.author='';
                    this.insert_f.value='';
                    this.insert_f.edition='';
                    this.insert_f.isbn='';
                    this.insert_f.publisher='';
                    this.insert_f.grade='';
                    this.dialogInsertVisible=false;
                    this.$message({message:"添加成功",type:"success"});
                }
                else{
                    this.$message({message:"请完善书籍信息",type:"error"});
                }
            })
        },
        confirm_2(){//修改的确认
            this.$refs['update_f'].validate((valid) => {
                if (valid) {
                    axios.put("http://localhost:8081/admin/updatebook",this.update_f).then(resp=>{
                        this.tableData=resp.data;
                        this.searchResult=resp.data;
                    });
                    this.update_f.name='';
                    this.update_f.author='';
                    this.update_f.value='';
                    this.update_f.edition='';
                    this.update_f.isbn='';
                    this.update_f.publisher='';
                    this.update_f.grade='';
                    this.dialogUpdateVisible=false;
                    this.$message({message:"修改成功",type:"success"});
                }
                else{
                    this.$message({message:"请完善书籍信息",type:"error"});
                }
            })
        }

    },
    mounted(){
        axios.get("http://localhost:8081/admin/allbook").then(resp=>{
            this.tableData=resp.data;
            this.searchResult=resp.data;
        });
    }
}
</script>

<style scoped>
.submit{
    float: right;
    margin-top: 15px;
    margin-left: 10px;
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