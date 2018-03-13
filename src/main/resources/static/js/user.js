var dt = new Date();
let th = this;
/*let username = this.ruleForm2.colname.value;*/
var month = dt.getMonth()+1;
var day = dt.getDate();
var year = dt.getFullYear();
var cur = year + '-' + month + '-' + day;
function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2002-12-18格式
    var  aDate,  oDate1,  oDate2,  iDays
    aDate  =  sDate1.split("-")
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2002格式
    aDate  =  sDate2.split("-")
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
    return  iDays
}
var Main = {
    data() {
        var checkQuestion = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('问题不能为空'));
            }else {
                callback()
            }
        };
        var checkAnswer = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('答案不能为空'));
            }else {
                callback()
            }
        };
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
                            callback();
                        } else if(value!==that.ruleForm2.colname){
                            return callback(new Error('用户名已存在'));
                        }else {
                            callback();
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                        that.errorNotify(error.message);
                    });
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
        return {
            activeIndex: '2',
            ruleForm2: {
                coluserid:'',
                colname: '',
                colstudentno: '',
                colrealname: '',
                colemail: '',
                colpassword: '',
                checkPass: '',
            },
            ruleForm3: {
                coluserid:'',
                question: '',
                answer: ''
            },
            rules3: {
                question: [
                    {required: true,validator: checkQuestion, trigger: 'blur'}
                ],
                answer: [
                    {required: true,validator: checkAnswer, trigger: 'blur'}
                ]
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
            tableHomeworkData: [
                {
                    workid: 1,
                    colfileid: 56,
                    workname: "实验报告",
                    worktime: "2018-02-06 20:44:08.0",
                    colfilename: "2018 服务器装机.xlsx",
                    coursename: "信息安全",
                    workremark: "3000字以上",
                    workfolder: "第一次作业"
                },
                {
                    workid: 1,
                    colfileid: 56,
                    workname: "实验报告2",
                    worktime: "2018-02-08 20:44:08.0",
                    colfilename: "2018 服务器装机.xlsx",
                    coursename: "决策支持系统",
                    workremark: "3000字以上",
                    workfolder: "第一次作业"
                }
            ],
            NoticeList:[
                {
                    noticeid: 1,
                    adminid: 1,
                    noticeContent: "系统正式上线，Alpha测试版",
                    issueTime: "2018-2-26 11:00"
                },
                {
                    noticeid: 2,
                    adminid: 1,
                    noticeContent: "系统，测试",
                    issueTime: "2018-2-26 11:13"
                }
            ]
        }
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
        limitTime(row){
            return DateDiff(row.worktime.replace(/([^\s]+)\s.*/, "$1"),  cur);
        },
        submitForm(formName, url) {
            this.$refs[formName].validate((valid) => {
                var that = this;
                if (valid) {
                    axios({
                        url: getRootPath_web()+'/home/userUpdate',
                        method: 'post',
                        data: that.ruleForm2
                        ,
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
                            that.openNotiSuccess("成功", "修改成功，刷新页面即可查看新信息！");
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
        clickToSubmit(formName) {
            this.$refs[formName].validate((valid) => {
                var that = this;
                if (valid) {
                    axios({
                        url: getRootPath_web() + '/beforeLogin',
                        method: 'post',
                        data: {
                            colname: outSideThis.ruleForm1.colname.value,
                            colpassword: outSideThis.ruleForm1.colpassword.value
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
                        if (response.data === true) {
                            //that.$refs[formName].submit;
                            //return true;
                            document.getElementById('ruleForm1').submit();
                        } else if (response.data === false) {
                            that.openNotiError("失败", response.data.message);
                        } else {
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
        ClickToJump(targe) {
            window.location.href = getRootPath_web()+"/" + targe;
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        onSubmit() {
            console.log('submit!');
        }
    },
    mounted() {
        this.$nextTick(() => {
            var that = this;
            axios.get(getRootPath_web()+'/home/userinfo')
                .then(function (response) {
                    //console.log(response.data);
                    that.ruleForm2 = response.data;
                })
                .catch(function (error) {
                    //console.log(error);
                    that.openNotiError("错误", response.data.message);
                });
            axios.get(getRootPath_web()+'/home/findAllHomework')
                .then(function (response) {
                    console.log(response.data);
                    that.tableHomeworkData = response.data;
                    //that.limitTime = response.data;
                })
                .catch(function (error) {
                    //console.log(error);
                    that.openNotiError("错误", response.data.message);
                });
            axios.get(getRootPath_web()+'/home/findAllNotice')
                .then(function (response) {
                    console.log(response.data);
                    that.NoticeList = response.data;
                    //that.limitTime = response.data;
                })
                .catch(function (error) {
                    //console.log(error);
                    that.openNotiError("错误", response.data.message);
                });
        })
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')