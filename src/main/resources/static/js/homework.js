var Main = {
    data() {
        return {
            activeIndex: '1',
            formInline: {
                user: '',
                region: ''
            },
            tableData3: [{
                id:'1',
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                id:'2',
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                id:'3',
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                id:'4',
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                id:'5',
                date: '2016-05-08',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                id:'6',
                date: '2016-05-06',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                id:'7',
                date: '2016-05-07',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }],
            multipleSelection: []
        }
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