var Main = {
    data() {
        return {
            activeIndex: '1',
            formInline: {
                user: '',
                region: ''
            },
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
            multipleSelection: []
        }
    },
    mounted() {
        this.$nextTick(() => {
            var that = this;
            axios.get('http://localhost:8080/cms/home/findallvhomework')
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
        togglePost(url){
            axios({
                url: 'http://localhost:8080/cms/'+url,
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
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        ClickToJump(targe){
            window.location.href="http://localhost:8080/cms/" + targe;
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