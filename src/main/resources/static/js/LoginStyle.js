var Main = {
    data() {
        var checkuserName = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('用户名不能为空'));
            } else {
                callback();
            }
            /*        setTimeout(() => {
                      if (!Number.isInteger(value)) {
                        callback(new Error('请输入数字值'));
                      } else {
                        if (value < 18) {
                          callback(new Error('必须年满18岁'));
                        } else {
                          callback();
                        }
                      }
                    }, 1000);*/
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm2.checkPass !== '') {
                    this.$refs.ruleForm2.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm2.pass) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            ruleForm2: {
                pass: '',
                checkPass: '',
                userName: '',
                email: ''
            },
            activeName: 'login',
            rules2: {
                pass: [
                    {validator: validatePass, trigger: 'blur'}
                ],
                checkPass: [
                    {validator: validatePass2, trigger: 'blur'}
                ],
                userName: [
                    {validator: checkuserName, trigger: 'blur'}
                ]
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        handleClick(tab, event) {
            console.log(tab, event);
        },
        showMsg(msg) {
            this.$message({
                message: msg,
                type: 'error'
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