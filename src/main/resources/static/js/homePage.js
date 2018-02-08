var dt = new Date();
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
            fileList: [],
            DownloadList: [],
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
            axios.get('http://localhost:8080/cms/download/findall')
                .then(function (response) {
                    console.log(response.data);
                    that.DownloadList = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
            axios.get('http://localhost:8080/cms/home/findAllHomework')
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
        openNotiSuccess(title,content) {
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
        uploadURL(row){
            return "http://localhost:8080/cms/moreUpload?courseName="+row.coursename+"&folder="+row.workfolder;
        },
        limitTime(row){
            return DateDiff(row.worktime.replace(/([^\s]+)\s.*/, "$1"),  cur);
        },
        submitForm(formName,url) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    axios({
                        url: 'http://localhost:8080/cms/'+url,
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
                    this.openNotiSuccess("成功","反馈成功！")
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
    ClickToJump(targe){
        window.location.href="http://localhost:8080/cms/" + targe;
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
    dialogClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
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
