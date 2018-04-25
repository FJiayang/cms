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

function displayStyle(id, type) {
    document.getElementById(id).style.display = type;
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
            iShow: true,
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
            NoticeList: [
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
            ],
            VersionList: [
                {
                    date: '2018-01-30',
                    content: '实现数据库查询用户，获取密码，编写了单元测试类',
                    version: 'V0.1',
                    user: 'F嘉阳'
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
            let that = this;
            /*setInterval(function(){
                that.getFileList();
            },1000);*/
            this.getFileList();
            axios.get(getRootPath_web() + '/home/findAllHomework')
                .then(function (response) {
                    console.log(response.data);
                    that.tableHomeworkData = response.data;
                    //that.limitTime = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
            axios.get(getRootPath_web() + '/home/findAllNotice')
                .then(function (response) {
                    console.log(response.data);
                    that.NoticeList = response.data;
                    //that.limitTime = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
            axios.get(getRootPath_web() + '/home/findallversion')
                .then(function (response) {
                    console.log(response.data);
                    that.VersionList = response.data;
                    //that.limitTime = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        })
    },
    methods: {
        getFileList() {
            let that = this;
            axios.get(getRootPath_web() + '/home/download/findone')
                .then(function (response) {
                    //console.log(response.data);
                    that.DownloadList = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                    that.openNotiError("失败", "获取文件列表失败！");
                });
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
        openSuccess(content) {
            this.$message({
                message: content,
                type: 'success'
            });
        },
        uploadURL(row) {
            return getRootPath_web() + "/home/moreUpload?courseName=" + row.coursename + "&folder=" + row.workfolder + "&workid=" + row.workid + "&rename=true";

        },
        limitTime(row) {
            return DateDiff(row.worktime.replace(/([^\s]+)\s.*/, "$1"), cur);
        },
        submitForm(formName, url) {
            this.$refs[formName].validate((valid) => {
                let that = this;
                if (valid) {
                    axios({
                        url: getRootPath_web() + '/' + url,
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
                    }).then(function (response) {
                        console.log(response.data);
                        if (response.data === true) {
                            //that.$refs[formName].submit;
                            //return true;
                            that.openNotiSuccess("成功", "反馈成功！")
                        } else if (response.data === false) {
                            that.openNotiError("失败", "反馈失败！");
                        } else {
                            that.openNotiError("错误", response.data.message);
                        }
                    }).catch(function (error) {
                        console.log(error);
                        that.openNotiError("错误", "服务器错误！");
                    });
                    //console.log(this.$refs.content.value)
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
            let that = this;
            this.$refs.upload.submit();
            this.openNotiSuccess("成功", "文件上传成功！");
            setTimeout(function () {
                that.getFileList();
            }, 1000);
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        ClickToJump(targe) {
            window.location.href = getRootPath_web() + "/" + targe;
        },
        handleDownload(row) {
            /*var url = window.location.protocol+"://"+window.location.host+":"+window.location.port+"/"*/
            window.open(getRootPath_web() + "/home/download/dodownload?fileId=" + row.colfileid);
        },
        handleDelete(row) {
            let that = this;
            axios({
                url: getRootPath_web() + '/home/filedelete',
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
            }).then(function (response) {
                console.log(response.data);
                if (response.data === true) {
                    //that.$refs[formName].submit;
                    //return true;
                    that.openNotiSuccess("成功", "删除成功！");
                    that.getFileList();
                } else if (response.data === false) {
                    that.openNotiError("失败", "删除失败!");
                } else {
                    that.openNotiError("错误", response.data.message);
                }
            }).catch(function (error) {
                console.log(error);
                that.openNotiError("错误", "服务器错误！");
            });
        },
        isShow(row) {
            let that = this;
            console.log(row.worktime + "||" + row.workid);
            if (compareTime(cur, row.worktime)) {
                console.log("true");
                return true;
                //提交时间合法
                //document.getElementById("btn-group").style.display="";
                //displayStyle("btn-show"+row.workid,"none");
                //displayStyle("btn-group"+row.workid,"");
            } else {
                console.log("false");
                return false;
                //提交时间不合法
                //displayStyle("btn-show"+row.workid,"");
                //displayStyle("btn-group"+row.workid,"none");
                //displayStyle("btn-show");
                //document.getElementById("btn-show").style.display="";
            }
        },
        handlePreview(row) {
            console.log(row);
            /* let that = this;
             console.log(row.worktime+"||"+row.workid);
            if (compareTime(cur,row.worktime)){
                 that.isShow = true;
                 console.log("Show"+that.isShow);
                 //提交时间合法
                 //document.getElementById("btn-group").style.display="";
                 //displayStyle("btn-show"+row.workid,"none");
                 //displayStyle("btn-group"+row.workid,"");
                 }
             if (!compareTime(cur,row.worktime)){
                 that.isShow = false;
                 console.log("EShow"+that.isShow);
                 //提交时间不合法
                 //displayStyle("btn-show"+row.workid,"");
                 //displayStyle("btn-group"+row.workid,"none");
                 //displayStyle("btn-show");
                 //document.getElementById("btn-show").style.display="";
             }*/
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
