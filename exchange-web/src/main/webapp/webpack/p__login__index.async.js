(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[3],{gMHI:function(e,a,r){"use strict";r.r(a);r("+L6B");var o=r("2/Rp"),t=(r("sRBo"),r("kaz8")),n=(r("y8nQ"),r("Vl3Y")),l=(r("5NDa"),r("5rEg")),s=(r("Pwec"),r("CtXQ")),m=(r("miYZ"),r("tsqr")),i=r("q1tI"),c=r.n(i),u=r("Z63m"),g=r.n(u),p=r("usdK"),d=r("mOP9"),f=r("i9FS"),_=r.n(f);class h extends i["Component"]{constructor(){super(...arguments),this.handleSubmit=(e=>{this.props.form.validateFields((e,a)=>{e||(console.log("Received values of form: ",a),g()({url:"/exchange-web/login.action",method:"post",data:a,type:"json",success:function(e){console.log(1==e.code),1==e.code?(m["a"].success(e.info,1),localStorage.loginUserName=a.username,p["a"].push("/newIndex")):0==e.code&&m["a"].warning(e.info,1)},error:function(){m["a"].error("\u7f51\u7edc\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\uff01",2)}}))})})}render(){var e=this.props.form.getFieldDecorator;return c.a.createElement(n["a"],{onSubmit:this.handleSubmit,className:_.a.login_form},c.a.createElement(n["a"].Item,null,e("username",{rules:[{required:!0,message:"Please input your username!"}]})(c.a.createElement(l["a"],{prefix:c.a.createElement(s["a"],{type:"user",style:{color:"rgba(0,0,0,.25)"}}),placeholder:"Username"}))),c.a.createElement(n["a"].Item,null,e("password",{rules:[{required:!0,message:"Please input your Password!"}]})(c.a.createElement(l["a"],{prefix:c.a.createElement(s["a"],{type:"lock",style:{color:"rgba(0,0,0,.25)"}}),type:"password",placeholder:"Password"}))),c.a.createElement(n["a"].Item,null,e("remember",{valuePropName:"checked",initialValue:!0})(c.a.createElement(t["a"],null,"Remember me")),c.a.createElement("a",{className:"styles.login-form-forgot",href:""},"Forgot password"),c.a.createElement("br",null),c.a.createElement(o["a"],{type:"primary",onClick:this.handleSubmit,className:"styles.login_form_button"}," Log in "),c.a.createElement("br",null),c.a.createElement(d["a"],{to:"/register"},"Register now")))}}a["default"]=n["a"].create({name:"normal_login"})(h)},i9FS:function(e,a,r){e.exports={login_form:"login_form___3QKEX",login_form_forgot:"login_form_forgot___EhlHZ",login_form_button:"login_form_button___3M5-y"}}}]);