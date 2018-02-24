var Main = {
    data() {
        var checkName = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('用户名不能为空'));
            }else {
                //判断用户名是否已存在
                axios.get(getRootPath_web() + '/CheckUserName', {
                    params: {
                        name: value
                    }
                })
                    .then(function (response) {
                        console.log(response.data);
                        if (response.data === true) {
                            callback()
                        } else {
                            return callback(new Error('用户名已存在'));
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                        this.errorNotify(error.message);
                    });
            }
        };
        var checkName1 = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('用户名不能为空'));
            } else {
                callback()
            }
        };
        var checkNo = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('学号不能为空'));
            } else {
                //判断是否为指定班级的合法用户
                axios.get(getRootPath_web() + '/CheckStudentNo', {
                    params: {
                        studentno: value
                    }
                })
                    .then(function (response) {
                        console.log(response.data);
                        if (response.data === true) {
                            callback()
                        } else {
                            return callback(new Error('学号非法'));
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                        this.errorNotify(error.message);
                    });
            }
        };
        var checkRealName = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('真实姓名不能为空'));
            } else {
                //判断用户名与学号是否匹配
                axios.get(getRootPath_web() + '/CheckStudent', {
                    params: {
                        realname: value,
                        studentno: this.ruleForm2.colstudentno
                    }
                })
                    .then(function (response) {
                        console.log(response.data);
                        if (response.data === false) {
                            return callback(new Error('姓名与学号不匹配'));
                        } else {
                            callback()
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                        this.errorNotify(error.message);
                    });
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
            ruleForm1: {
                colname: '',
                colpassword: '',
            },
            ruleForm2: {
                colname: '',
                colpassword: '',
                checkPass: '',
                colstudentno: '',
                colrealname: '',
                colemail: ''
            },
            rules1: {
                colpassword: [
                    {required: true,validator: validatePass, trigger: 'blur'}
                ],
                colname: [
                    {required: true,validator: checkName1, trigger: 'blur'}
                ],
            },
            rules2: {
                colpassword: [
                    {required: true, validator: validatePass, trigger: 'blur'}
                ],
                checkPass: [
                    {required: true, validator: validatePass2, trigger: 'blur'}
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
                    {required: true, validator: checkName, trigger: 'blur'}
                ],
            },
            activeName: 'login',
        };
    },
    methods: {
        errorNotify(content) {
            this.$notify.error({
                title: '错误',
                message: content
            })
        },
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
        handleClick(tab, event) {
            console.log(tab, event);
        },
        showMsg(msg) {
            this.$message({
                message: msg,
                type: 'error'
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