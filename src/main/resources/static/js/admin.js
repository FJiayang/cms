var Main = {
    data() {
        return {
            activeIndex: '1',
            form: {
                content: '',
            },
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
            axios.get(getRootPath_web()+'/home/findvfeedback')
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
        ClickToJump(targe){
            window.location.href=getRootPath_web()+"/" + targe;
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        onSubmit() {
            console.log('submit!');
        }
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')