var Main = {
    data() {
        return {
            activeIndex: '1',
            formInline: {
                user: '',
                region: ''
            },
            form: {
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
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
                }
            ],
        }
    },
    methods: {
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