let outSideThis = this;
var Main = {
    data() {
        var checkName = (rule, value, callback) => {
            let that= this;
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
                        that.errorNotify(error.message);
                    });
            }
        };
        var checkQuestion = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('问题不能为空'));
            } else {
                callback()
            }
        };
        var checkAnswer = (rule, value, callback) => {
            let that = this;
            if (!value) {
                return callback(new Error('答案不能为空'));
            } else {
                axios.get(getRootPath_web() + '/finduserque', {
                    params: {
                        name :outSideThis.findpass.colname.value,
                        question:outSideThis.findpass.question.value,
                        answer: value
                    }
                })
                    .then(function (response) {
                        console.log(response.data);
                        if (response.data === true) {
                            callback()
                        } else if (response.data === false){
                            return callback(new Error('答案错误'));
                        }else {
                            return callback(new Error(response.data.message));
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                        that.errorNotify("未知错误");
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
                            return callback(new Error('姓名与学号不匹配或该用户已注册'));
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
        var validatePass3 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm3.checkPass !== '') {
                    this.$refs.ruleForm3.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass4 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm3.colpassword) {
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
            ruleForm3: {
                colname:'',
                question: '',
                answer: '',
                colpassword: '',
                checkPass: ''
            },
            rules3: {
                colname: [
                    {required: true,validator: checkName1, trigger: 'blur'}
                ],
                question: [
                    {required: true,validator: checkQuestion, trigger: 'blur'}
                ],
                answer: [
                    {required: true,validator: checkAnswer, trigger: 'blur'}
                ],
                colpassword: [
                    {required: true, validator: validatePass3, trigger: 'blur'}
                ],
                checkPass: [
                    {required: true, validator: validatePass4, trigger: 'blur'}
                ]
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
        openNotiSuccess(title, content) {
            this.$notify({
                title: title,
                message: content,
                type: 'success'
            });
        },
        openNotiError(title, content) {
            this.$notify.error({
                title: title,
                message: content
            });
        },
        submitForm(formName, url) {
            this.$refs[formName].validate((valid) => {
                var that = this;
                if (valid) {
                    axios({
                        url: getRootPath_web()+'/' + url,
                        method: 'post',
                        data: {
                            name :outSideThis.findpass.colname.value,
                            password:outSideThis.findpass.colpassword.value,
                            question:outSideThis.findpass.question.value,
                            answer:outSideThis.findpass.answer.value,
                        },
                        transformRequest: [function (data) {
                            // Do whatever you want to transform the data
                            let ret = ''
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }],
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }
                    }).then(function (response) {
                        console.log(response.data);
                        if (response.data===true){
                            that.openNotiSuccess("成功", "修改成功,请切换至登录选项！");
                        }else if (response.data===false){
                            that.openNotiError("失败", "修改失败！");
                        }else {
                            that.openNotiError("错误", response.data.message);
                        }
                    }).catch(function (error) {
                        console.log(error);
                        that.openNotiError("错误", "服务器错误！");
                    });
                    //console.log(this.$refs.content.value)
                    //this.openNotiSuccess("成功", "修改成功！")
                    //this.$options.methods.openNotiSuccess.bind(this)();
                    //alert('submit!');
                } else {
                    console.log('error submit!!');
                    that.openNotiError("错误", "表单填写错误！");
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
        clickToSubmit(formName) {
            this.$refs[formName].validate((valid) => {
                var that = this;
                if (valid) {
                    axios({
                        url: getRootPath_web()+'/beforeLogin',
                        method: 'post',
                        data: {
                            colname :outSideThis.ruleForm1.colname.value,
                            colpassword:outSideThis.ruleForm1.colpassword.value
                        },
                        transformRequest: [function (data) {
                            // Do whatever you want to transform the data
                            let ret = '';
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }],
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }
                    }).then(function (response) {
                        console.log(response.data);
                        if (response.data===true){
                            //that.$refs[formName].submit;
                            //return true;
                            document.getElementById('ruleForm1').submit();
                        }else if (response.data===false){
                            that.openNotiError("失败", response.data.message);
                        }else {
                            that.openNotiError("错误", response.data.message);
                        }
                    }).catch(function (error) {
                        console.log(error);
                        that.openNotiError("错误", "服务器错误！");
                    });
                    //console.log(this.$refs.content.value)
                    //this.openNotiSuccess("成功", "修改成功！")
                    //this.$options.methods.openNotiSuccess.bind(this)();
                    //alert('submit!');
                } else {
                    console.log('error submit!!');
                    that.openNotiError("错误", "表单填写错误！");
                    return false;
                }
            });
        },
        clickToRegister(formName){
            this.$refs[formName].validate((valid) => {
                var that = this;
                if (valid) {
                    axios({
                        url: getRootPath_web()+'/register/doregister',
                        method: 'post',
                        data: {
                            colname :outSideThis.ruleForm2.colname.value,
                            colpassword:outSideThis.ruleForm2.colpassword.value,
                            colemail:outSideThis.ruleForm2.colemail.value,
                            colstudentno:outSideThis.ruleForm2.colstudentno.value,
                            colrealname:outSideThis.ruleForm2.colrealname.value
                        },
                        transformRequest: [function (data) {
                            // Do whatever you want to transform the data
                            let ret = '';
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }],
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }
                    }).then(function (response) {
                        console.log(response.data);
                        if (response.data===true){
                            that.openNotiSuccess("成功", "注册成功,请切换至登录选项！");
                        }else {
                            that.openNotiError("错误", response.data.message);
                        }
                    }).catch(function (error) {
                        console.log(error);
                        that.openNotiError("错误", "服务器错误！");
                    });
                    //console.log(this.$refs.content.value)
                    //this.openNotiSuccess("成功", "修改成功！")
                    //this.$options.methods.openNotiSuccess.bind(this)();
                    //alert('submit!');
                } else {
                    console.log('error submit!!');
                    that.openNotiError("错误", "表单填写错误！");
                    return false;
                }
            });
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