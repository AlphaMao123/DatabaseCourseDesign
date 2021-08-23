<template>
    <div>
        <el-container>
            <el-header>
                <el-menu
                :default-active="this.$router.path"
                class="el-menu-demo"
                mode="horizontal"
                @select="handleSelect"
                background-color="#409EFF"
                text-color="#fff"
                router
                active-text-color="#fff">
                    <el-menu-item index="/admin/1"><i class="el-icon-folder-opened"></i>管理教材</el-menu-item>
                    <el-menu-item index="/admin/2"><i class="el-icon-message-solid"></i>通知信息</el-menu-item>
                    <el-menu-item index="/home/5" class="back"><i class="el-icon-back"></i>退出</el-menu-item>
                    </el-menu>
            </el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
    name: 'admin',
    data() {
        return {
            
        }
    },
    methods: {
        handleSelect(key,keyPath){
            console.log(key,keyPath);
        }
    }
}
</script>

<style scoped>
.el-header{
    text-align: center;
    line-height: 60px;
    padding: 0%;
}


.el-menu-item.back{
    float: right;
}

.el-icon-folder-opened{
    color: #fff;
}

.el-icon-message-solid{
    color: #fff;
}

.el-icon-back{
    color: #fff;
}
</style>