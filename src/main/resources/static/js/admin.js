var Main = {
    data() {
        return {
            activeIndex: '1',
            form: {
                content: '',
            },
            feedbackData:[
                {
                    content:'bug',
                    time:'2018-2-3',
                    username:'root'
                }
            ],
            logData:[
                {
                    ip:'127.0.0.1',
                    header:'bug',
                    time:'2018-2-3',
                    username:'root'
                },
                {
                    ip:'127.0.0.1',
                    header:'bug',
                    time:'2018-2-3',
                    username:'root'
                },
                {
                    ip:'127.0.0.1',
                    header:'bug',
                    time:'2018-2-3',
                    username:'root'
                },
                {
                    ip:'127.0.0.1',
                    header:'bug',
                    time:'2018-2-3',
                    username:'root'
                },
                {
                    ip:'127.0.0.1',
                    header:'bug',
                    time:'2018-2-3',
                    username:'root'
                }
            ],
        }
    },
    methods: {
        ClickToJump(targe){
            window.location.href="http://localhost:8080/cms/" + targe;
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