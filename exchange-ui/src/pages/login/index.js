import { Component } from 'react';
import { Form, Icon, Input, Button, Checkbox } from 'antd';
import reqwest from 'reqwest';
import router from 'umi/router';

import styles from './index.css';

class LoginForm extends Component {
  handleSubmit = (e) => {
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        reqwest({
          url: '/exchange-web/login.action',
          method: 'post',
          data: values,
          type: 'json',
          success: function(data) {
            console.log(data);
            router.push('/');
          }
        })
      }
    });

  }

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSubmit} className={styles.login_form}>
        <Form.Item>
          {
            getFieldDecorator(
              'username', { rules: [{ required: true, message: 'Please input your username!' }] }
            ) (
               <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Username" />
            )
          }
        </Form.Item>
        <Form.Item>
          {
            getFieldDecorator(
              'password', { rules: [{ required: true, message: 'Please input your Password!' }] }
            ) (
              <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="Password" />
            )
          }
        </Form.Item>
        <Form.Item>
          {
            getFieldDecorator(
            'remember', { valuePropName: 'checked', initialValue: true, }
            )(
              <Checkbox>Remember me</Checkbox>
            )
          }
          <a className="styles.login-form-forgot" href="">Forgot password</a>
          <br/>
          <Button type="primary" onClick={this.handleSubmit} className="styles.login_form_button"> Log in </Button>
          <br/>
          <a href="/register">Register now</a>
        </Form.Item>
      </Form>
    );
  }
}

export default Form.create({ name: 'normal_login' })(LoginForm);
