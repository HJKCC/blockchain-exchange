(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[5],{"7c/T":function(e,t,r){"use strict";r.r(t);var a=r("jehZ"),s=r.n(a),i=(r("+L6B"),r("2/Rp")),o=(r("y8nQ"),r("Vl3Y")),n=(r("5NDa"),r("5rEg")),l=r("q1tI"),m=r.n(l),p=(r("Z63m"),r("usdK")),c=r("8ws1"),d=r("D7Eh"),u=r.n(d);class h extends l["Component"]{constructor(){super(...arguments),this.state={confirmDirty:!1},this.handleSubmit=(e=>{this.props.form.validateFieldsAndScroll((e,t)=>{e||c["a"]({url:"/exchange-web/user/register.action",data:t}).then(e=>{this.setState({visible:!1}),p["a"].push("/login")})})}),this.handleConfirmBlur=(e=>{var t=e.target.value;this.setState({confirmDirty:this.state.confirmDirty||!!t})}),this.compareToFirstPassword=((e,t,r)=>{var a=this.props.form;t&&t!==a.getFieldValue("password")?r("Two passwords that you enter is inconsistent!"):r()}),this.validateToNextPassword=((e,t,r)=>{var a=this.props.form;t&&this.state.confirmDirty&&a.validateFields(["confirm"],{force:!0}),r()})}render(){var e=this.props.form.getFieldDecorator,t={labelCol:{xs:{span:24},sm:{span:8}},wrapperCol:{xs:{span:24},sm:{span:16}}},r={wrapperCol:{xs:{span:24,offset:0},sm:{span:16,offset:8}}};return m.a.createElement(o["a"],s()({},t,{className:u.a.register_form,onSubmit:this.handleSubmit}),m.a.createElement(o["a"].Item,{label:"\u7528\u6237\u540d"},e("name",{rules:[{required:!0,message:"Please input your name!"}]})(m.a.createElement(n["a"],{type:"text"}))),m.a.createElement(o["a"].Item,{label:"\u8f93\u5165\u5bc6\u7801"},e("password",{rules:[{required:!0,message:"Please input your password!"},{validator:this.validateToNextPassword}]})(m.a.createElement(n["a"],{type:"password"}))),m.a.createElement(o["a"].Item,{label:"\u786e\u8ba4\u5bc6\u7801"},e("confirm",{rules:[{required:!0,message:"Please confirm your password!"},{validator:this.compareToFirstPassword}]})(m.a.createElement(n["a"],{type:"password",onBlur:this.handleConfirmBlur}))),m.a.createElement(o["a"].Item,s()({},r,{className:u.a.register_form_button}),m.a.createElement(i["a"],{type:"primary",onClick:this.handleSubmit},"Register")))}}t["default"]=o["a"].create({name:"register"})(h)},D7Eh:function(e,t,r){e.exports={register_form:"register_form___32YOs",register_form_button:"register_form_button___2VriQ"}}}]);