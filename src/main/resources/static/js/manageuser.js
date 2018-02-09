var Main = {
    data() {
        return {
            activeIndex: '1',
            userData: [
                {
                    coluserid: 53,
                    colname: "FFFF",
                    colpassword: "330399941720950163549766122324520082468573044291",
                    colemail: "123@gmail.com",
                    colstudentno: "123456",
                    colrealname: "ENMM"
                }
            ],
            multipleSelection: []
        }
    },
    mounted() {
        this.$nextTick(() => {
            var that = this;
            axios.get('http://localhost:8080/cms/home/findalluser')
                .then(function (response) {
                    console.log(response.data);
                    that.userData = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
            /*axios.get('http://localhost:8080/cms/home/findvfeedback')
                .then(function (response) {
                    console.log(response.data);
                    that.feedbackData = response.data;
                    //that.limitTime = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });*/
        })
    },
    methods: {
        togglePost(url){
            /*axios({
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
            })*/
            console.log(this.multipleSelection)
            //console.log(this.$refs.id.value);
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