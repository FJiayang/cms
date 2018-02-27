var Main = {
    data() {
        return {
            activeIndex: '1',
            dialogTableVisible: false,
            formInline: {
                name:'',
                name2:'',
                content: '',
                folder: ''
            },
            UncommittedPersonList:[
                {
                    listid: 1,
                    colstudentno:'15251101238',
                    colrealname:'符嘉阳',
                    sex:'男'
                }
            ],
            homeworkData: [
                {
                    fileid: 56,
                    courseName: "信息安全",
                    name: "实验报告",
                    id: 1,
                    time: "2018-02-06 20:44:08.0",
                    remark: "3000字以上",
                    folder: "第一次作业"
                }
            ],
            homeworkFormData:[
                {
                    courseNo: 1,
                    courseName: "信息安全",
                    courseTime: "2018-02-06 20:42:28.0",
                    teacherusername: "FJY",
                    teacherrealname: "root"
                }
            ],
            multipleSelection: []
        }
    },
    mounted() {
        this.$nextTick(() => {
            var that = this;
            axios.get(getRootPath_web()+'/home/findallvhomework')
                .then(function (response) {
                    console.log(response.data);
                    that.homeworkData = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
            axios.get(getRootPath_web()+'/home/findallvhomework')
                .then(function (response) {
                    console.log(response.data);
                    that.homeworkData = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        })
    },
    methods: {
        findUncommitStudent(row){
            var that = this;
            this.dialogTableVisible = true;
            axios.get(getRootPath_web()+'/home/admin/findStudentInCourseFile',
                {
                    params: {
                        Folder: row.folder,
                        CourseName:row.courseName
                    }
                }
            )
                .then(function (response) {
                    console.log(response.data);
                    that.UncommittedPersonList = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        togglePost(url){
            axios({
                url: getRootPath_web()+url,
                method: 'post',
                data: {
                    id:this.multipleSelection
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
        },
        dialogClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {
                });
        },
        handleDownload(row) {
            /*var url = window.location.protocol+"://"+window.location.host+":"+window.location.port+"/"*/
            window.open(getRootPath_web()+"/download/downloadzip?courseName="
                + row.courseName+"&Folder="+row.folder);
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        ClickToJump(targe){
            window.location.href=getRootPath_web()+ "/" + targe;
        },
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
            console.log(val)
        },
        onSubmit() {
            console.log('submit!');
        }
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')