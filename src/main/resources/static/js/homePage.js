var dt = new Date();
var month = dt.getMonth() + 1;
var day = dt.getDate();
var year = dt.getFullYear();
var cur = year + '-' + month + '-' + day;

function DateDiff(sDate1, sDate2) {    //sDate1和sDate2是2002-12-18格式
    var aDate, oDate1, oDate2, iDays
    aDate = sDate1.split("-")
    oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])    //转换为12-18-2002格式
    aDate = sDate2.split("-")
    oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])
    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24)    //把相差的毫秒数转换为天数
    return iDays
}

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
            feedbackForm: {
                content: ''
            },
            activeIndex: '1',
            dialogVisible: false,
            dialogTableVisible: false,
            ruleForm2: {
                colname: '',
                colpassword: '',
                checkPass: '',
                colstudentno: '',
                colrealname: '',
                colemail: ''
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
            fileList: [],
            DownloadList: [],
            VersionList:[
                {
                    date:'2018-02-24',
                    content:'实现对重复文件自动重命名',
                    version:'V1.11',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-24',
                    content:'实现对文件是否重命名的控制，目前设定为管理员上传的文件不会重命名，完善日志输出存储',
                    version:'V1.10',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-24',
                    content:'添加运行日志处理，频率为每天对error和info级别的日志进行文件保存，实现判断注册用户名是否已存在',
                    version:'V1.9.1',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-24',
                    content:'实现前端对学号和用户名的异步判断，核心技术为axios',
                    version:'V1.9',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-24',
                    content:'修复注册表单输入正确不会显示反馈图标的问题',
                    version:'V1.8.2',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-24',
                    content:'修复文件批量下载的错误，为压缩文件进行统一管理，批量下载不支持多线程',
                    version:'V1.8.1',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-24',
                    content:'实现文件批量下载',
                    version:'V1.8',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-23',
                    content:'实现对未交作业人员的查询和展示',
                    version:'V1.7',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-23',
                    content:'去除js对绝对地址的依赖，为日志添加排序选项',
                    version:'V1.6.1',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-23',
                    content:'实现用户仅能查看自己提交的文件',
                    version:'V1.6',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-23',
                    content:'修复用户信息修改页面数据绑定方式，改为使用axios进行数据获取和绑定',
                    version:'V1.5',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-22',
                    content:'实现后台对未交作业人员的查询',
                    version:'V1.4',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-22',
                    content:'实现用户信息修改',
                    version:'V1.3.1',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-21',
                    content:'实现用户信息修改页面session传值',
                    version:'V1.3',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-09',
                    content:'实现用户管理和作业管理数据读取',
                    version:'V1.2.1',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-09',
                    content:'实现单文件删除',
                    version:'V1.2',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-08',
                    content:'完成课程管理数据读取，修复页面加载css，js错误',
                    version:'V1.1.2',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-08',
                    content:'完成管理员主页的数据读取',
                    version:'V1.1.1',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-08',
                    content:'完成前端界面设计',
                    version:'V1.0',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-08',
                    content:'实现表单异步提交并显示消息',
                    version:'V0.15',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-07',
                    content:'实现作业上传自动创建文件夹',
                    version:'V0.14',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-07',
                    content:'实现作业获取和展示',
                    version:'V0.13',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-06',
                    content:'完成前端上传页面设计',
                    version:'V0.12',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-06',
                    content:'实现登录日志记录',
                    version:'V0.11',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-05',
                    content:'提高加密安全性',
                    version:'V0.10.1',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-05',
                    content:'实现用户密码SHA加密',
                    version:'V0.10',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-05',
                    content:'实现登录拦截器',
                    version:'V0.9',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-05',
                    content:'实现注册功能',
                    version:'V0.8',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-05',
                    content:'实现json数据绑定',
                    version:'V0.7',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-04',
                    content:'实现多文件上传，按钮vue传值（vue2.1特性）',
                    version:'V0.6',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-04',
                    content:'实现Element组件+单文件上传',
                    version:'V0.5',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-04',
                    content:'使用thymeleaf模板引擎，引入frame框架和公用css和js文件',
                    version:'V0.4',
                    user:'F嘉阳'
                },
                {
                    date:'2018-02-03',
                    content:'实现文件上传和数据库记录、Element+vue登录注册UI',
                    version:'V0.3',
                    user:'F嘉阳'
                },
                {
                    date:'2018-01-30',
                    content:'实现登录，编写错误码，实现错误码返回json',
                    version:'V0.2',
                    user:'F嘉阳'
                },
                {
                    date:'2018-01-30',
                    content:'实现数据库查询用户，获取密码，编写了单元测试类',
                    version:'V0.1',
                    user:'F嘉阳'
                }
            ],
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
            tableData2: [{
                date: '2016-05-02',
            }, {
                date: '2016-05-04',
            }, {
                date: '2016-05-01',
            }, {
                date: '2016-05-03',
            }],
            tableData3: [{
                date: '2016-05-02',
                name: '王小虎'
            }, {
                date: '2016-05-04',
                name: '王小虎'
            }, {
                date: '2016-05-01',
                name: '王小虎'
            }, {
                date: '2016-05-03',
                name: '王小虎'
            }]
        };
    },
    mounted() {
        this.$nextTick(() => {
            var that = this;
            axios.get(getRootPath_web()+'/download/findone')
                .then(function (response) {
                    console.log(response.data);
                    that.DownloadList = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
            axios.get(getRootPath_web()+'/home/findAllHomework')
                .then(function (response) {
                    console.log(response.data);
                    that.tableHomeworkData = response.data;
                    //that.limitTime = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        })
    },
    methods: {
        openNotiSuccess(title, content) {
            this.$notify({
                title: title,
                message: content,
                type: 'success'
            });
        },
        openSuccess(content) {
            this.$message({
                message: content,
                type: 'success'
            });
        },
        uploadURL(row) {
            return getRootPath_web()+"/moreUpload?courseName=" + row.coursename + "&folder=" + row.workfolder+"&rename=true";
        },
        limitTime(row) {
            return DateDiff(row.worktime.replace(/([^\s]+)\s.*/, "$1"), cur);
        },
        submitForm(formName, url) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    axios({
                        url: getRootPath_web()+'/' + url,
                        method: 'post',
                        data: {
                            content: this.$refs.content.value
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
                    })
                    console.log(this.$refs.content.value)
                    this.openNotiSuccess("成功", "反馈成功！")
                    //this.$options.methods.openNotiSuccess.bind(this)();
                    //alert('submit!');
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
        ClickToJump(targe) {
            window.location.href = getRootPath_web()+"/" + targe;
        },
        handleDownload(row) {
            /*var url = window.location.protocol+"://"+window.location.host+":"+window.location.port+"/"*/
            window.open(getRootPath_web()+"/download/dodownload?fileId=" + row.colfileid);
        },
        handleDelete(row) {
            axios({
                url: getRootPath_web()+'/home/filedelete',
                method: 'post',
                data: {
                    fileid: row.colfileid
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
            });
            this.openNotiSuccess("成功", "删除成功！");
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
        dialogClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {
                });
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
