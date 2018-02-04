axios.get('http://localhost:8080/cms/download/findall')
    .then(function (response) {
        console.log(response);
        resolve(response.data);
    })
    .catch(function (error) {
        console.log(error);
    });
var Main = {
    data() {
        return {
            activeIndex: '1',
            activeIndex2: '1',
            fileList: [{
                name: 'food.jpeg',
                url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
            }, {
                name: 'food2.jpeg',
                url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
            }],
            DownloadList: [
                {
                    colfileid: 9,
                    coluserid: 0,
                    colip: '0:0:0:0:0:0:0:1',
                    colrealname: '234234',
                    colfilename: '10K.jpg',
                    colfilesize: '21.78KB',
                    colfilepath: 'C:\\Users\\11860\\AppData\\Local\\Temp\\tomcat-docbase.8051654935022807536.8080\\upload/10K.jpg'
                },
                {
                    colfileid: 15,
                    coluserid: 0,
                    colip: '0:0:0:0:0:0:0:1',
                    colrealname: null,
                    colfilename: '10K.jpg',
                    colfilesize: '21.78KB',
                    colfilepath: 'F:\\JAVA Workspace\\Temp\\upload/10K.jpg'
                }
            ]
        };
    },
    methods: {
        handleClick(row) {
            console.log(row.colfileid);
        },
        submitUpload() {
            this.$refs.upload.submit();
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handleDownload(row){
            /*var url = window.location.protocol+"://"+window.location.host+":"+window.location.port+"/"*/
            window.open("http://localhost:8080/cms/download/dodownload?fileId="+row.colfileid);
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
