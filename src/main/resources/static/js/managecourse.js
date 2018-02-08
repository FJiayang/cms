var Main = {
    data() {
        return {
            activeIndex: '1',
            formInline: {
                user: '',
                region: ''
            },
            tableData3: [
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
            axios.get('http://localhost:8080/cms/home/findvcourse')
                .then(function (response) {
                    console.log(response.data);
                    that.tableData3 = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
            /*axios.get('http://localhost:8080/cms/home/findAllHomework')
                .then(function (response) {
                    console.log(response.data);
                    that.tableHomeworkData = response.data;
                    //that.limitTime = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });*/
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
            window.location.href="http://localhost:8080/cms/home/" + targe;
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