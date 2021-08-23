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
                    <el-menu-item index="/home/1"><i class="el-icon-success"></i>订购教材</el-menu-item>
                    <el-menu-item index="/home/2"><i class="el-icon-error"></i>退订教材</el-menu-item>
                    <el-menu-item index="/home/3"><i class="el-icon-user-solid"></i>个人信息</el-menu-item>
                    <el-menu-item index="/home/4"><i class="el-icon-phone"></i>反馈</el-menu-item>
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
    name: 'home',
    data(){
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

.el-icon-user-solid{
    color: #fff;
}

.el-icon-success{
    color: #fff;
}

.el-icon-error{
    color: #fff;
}

.el-icon-phone{
    color: #fff;
}

.el-icon-back{
    color: #fff;
}
</style>