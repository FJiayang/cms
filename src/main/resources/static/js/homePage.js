/*var data = "";
axios.get('http://localhost:8080/cms/download/findall')
    .then(function (response) {
        console.log(response.data);
        data = response.data;
    })
    .catch(function (error) {
        console.log(error);
    });*/
var Main = {
    data() {
        var checkName = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('用户名不能为空'));
            }
        };
        var checkNo = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('学号不能为空'));
            }
        };
        var checkRealName = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('真实姓名不能为空'));
            }
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm2.checkPass !== '') {
                    this.$refs.ruleForm2.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm2.colpassword) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            ruleForm2: {
                colname: '',
                colpassword: '',
                checkPass:'',
                colstudentno: '',
                colrealname: '',
                colemail: ''
            },
            rules2: {
                colpassword: [
                    {required: true,validator: validatePass, trigger: 'blur'}
                ],
                checkPass: [
                    {required: true,validator: validatePass2, trigger: 'blur'}
                ],
                colstudentno: [
                    {
                        required: true,
                        validator: checkNo,
                        trigger: 'blur'
                    }
                ],
                colrealname: [
                    {
                        required: true,
                        validator: checkRealName,
                        trigger: 'blur'
                    }
                ],
                colname: [
                    {required: true,validator: checkName, trigger: 'blur'}
                ],
            },
            activeName:'login',
            fileList: [{
                name: 'food.jpeg',
                url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
            }, {
                name: 'food2.jpeg',
                url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
            }],
            DownloadList: []
        };
    },
    mounted() {
        this.$nextTick(() => {
            var that = this;
            axios.get('http://localhost:8080/cms/download/findall')
                .then(function (response) {
                    console.log(response.data);
                    that.DownloadList = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        })

    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
    handleClick(row) {
        console.log(row.colfileid);
    },
    submitUpload() {
        this.$refs.upload.submit();
    },
    handleRemove(file, fileList) {
        console.log(file, fileList);
    },
    handleDownload(row) {
        /*var url = window.location.protocol+"://"+window.location.host+":"+window.location.port+"/"*/
        window.open("http://localhost:8080/cms/download/dodownload?fileId=" + row.colfileid);
    },
    handlePreview(file) {
        console.log(file);
    },
    handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleSelect(key, keyPath) {
        console.log(key, keyPath);
    },
    handleOpen(key, keyPath) {
        console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
        console.log(key, keyPath);
    },
    showMsg(msg) {
        this.$message({
            message: msg,
            type: 'success'
        });
    },
    notiSuccess(title, value) {
        this.$notify({
            title: title,
            message: value,
            type: 'success'
        });
    },
    notiWarning(title, value) {
        this.$notify({
            title: title,
            message: value,
            type: 'warning'
        });
    },

    notiInfo(title, value) {
        this.$notify.info({
            title: title,
            message: value
        });
    },

    notiError(title, value) {
        this.$notify.error({
            title: title,
            message: value
        });
    }
}
}
var Ctor = Vue.extend(Main)
var con = new Ctor().$mount('#app')
//con.showMsg('登录成功');
