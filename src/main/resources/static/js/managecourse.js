let datax = [
    {
        courseNo: 1,
        courseName: "信息安全T",
        courseTime: "2018-02-07 20:42:28.0",
        teacherusername: "FJY1",
        teacherrealname: "root1"
    }
];
var Main = {
    data() {
        return {
            restaurants: [],
            state1: '',
            state2: '',
            activeIndex: '1',
            formInline: {
                user: '',
                region: ''
            },
            tableData3: [
                {
                    courseNo: 1,
                    courseName: "信息安全T",
                    courseTime: "2018-02-07 20:42:28.0",
                    teacherusername: "FJY1",
                    teacherrealname: "root1"
                }
            ],
            multipleSelection: [],
            courseList:[
                {courseName:"root"},
                {courseName:"root2"},
            ],
            teacherList:[],
        }
    },
    mounted() {
        this.$nextTick(() => {
            var that = this;
            axios.get(getRootPath_web()+'/home/findvcourse')
                .then(function (response) {
                    console.log(response.data);
                    that.tableData3 = response.data;
                    datax=response.data;
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
                url: getRootPath_web()+'/'+url,
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
        querySearch(queryString, cb) {
            var restaurants = this.restaurants;
            var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
            // 调用 callback 返回建议列表的数据
            cb(results);
        },
        createFilter(queryString) {
            return (restaurant) => {
                return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },
        loadAll() {
            return [
                { "value": "三全鲜食（北新泾店）", "address": "长宁区新渔路144号" },
                { "value": "Hot honey 首尔炸鸡（仙霞路）", "address": "上海市长宁区淞虹路661号" },
                { "value": "南拳妈妈龙虾盖浇饭", "address": "普陀区金沙江路1699号鑫乐惠美食广场A13" }
            ];
        },
        querySearchAsync(queryString, cb) {
            var restaurants = this.restaurants;
            var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;

            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
                cb(results);
            }, 1000 * Math.random());
        },
        createStateFilter(queryString) {
            return (state) => {
                return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },
        handleSelect(item) {
            console.log(item);
        },
        ClickToJump(targe){
            window.location.href=getRootPath_web()+"/"+ targe;
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
    },
    mounted() {
        /*this.restaurants = this.loadAll();*/
        this.restaurants=this.$nextTick(() => {
            var that = this;
            axios.get(getRootPath_web()+'/home/findvcourse')
                .then(function (response) {
                    console.log(response.data);
                    that.logData = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        })
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')