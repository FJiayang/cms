var Main = {
    data() {
        return {
            NoticeForm: {
                content: ''
            },
            activeIndex: '1',
            form: {
                content: '',
            },
            fileList: [],
            feedbackData:[
                {
                    id: 68,
                    username: "root",
                    content: "Fred",
                    time: "2018-02-08 10:14:11"
                },
                {
                    id: 71,
                    username: "root",
                    content: "1234214",
                    time: "2018-02-08 10:30:38"
                }
            ],
            logData:[
                {
                    logid: 55,
                    colname: "root",
                    coltime: "2018-02-06 11:35:56.0",
                    colip: "0:0:0:0:0:0:0:1",
                    colheader: "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36"
                },
                {
                    logid: 58,
                    colname: "root",
                    coltime: "2018-02-06 11:40:41.0",
                    colip: "0:0:0:0:0:0:0:1",
                    colheader: "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36"
                }
            ]
        }
    },
    mounted() {
        this.$nextTick(() => {
            var that = this;
            axios.get(getRootPath_web()+'/home/findvlog')
                .then(function (response) {
                    console.log(response.data);
                    that.logData = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
            axios.get(getRootPath_web()+'/home/admin/findvfeedback')
                .then(function (response) {
                    console.log(response.data);
                    that.feedbackData = response.data;
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
        openNotiError(title, content) {
            this.$notify.error({
                title: title,
                message: content
            });
        },
        uploadSuccess (response, file, fileList) {
            let that = this;
            if (file.status==="success"){
                that.openNotiSuccess("成功", file.name+"上传成功！");
            }else{
                that.openNotiError("失败", file.name+"上传失败！");
            }
            /*            console.log('response', response);
                        console.log('file',file,fileList);
                        console.log('fileList',fileList);
                        console.log("信息"+file.status+"|"+file.name)*/
        },
        openSuccess(content) {
            this.$message({
                message: content,
                type: 'success'
            });
        },
        uploadURL(row) {
            return getRootPath_web()+"/home/moreUpload?rename=false";
        },
        submitUpload() {
            this.$refs.upload.submit();
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        ClickToJump(targe){
            window.location.href=getRootPath_web()+"/" + targe;
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        onSubmit() {
            console.log('submit!');
        },submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                let that = this;
                if (valid) {
                    axios({
                        url: getRootPath_web()+'/home/admin/addNotice',
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
                        if (response.data===true){
                            //that.$refs[formName].submit;
                            //return true;
                            that.openNotiSuccess("成功", "发布成功！")
                        }else if (response.data===false){
                            that.openNotiError("失败", "发布失败！");
                        }else {
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
        }
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')