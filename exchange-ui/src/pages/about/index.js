import { Component } from 'react';
import { Form, Icon, Input, Button, Checkbox, message } from 'antd';
import reqwest from 'reqwest';
import router from 'umi/router';
import Link from 'umi/link';

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
            console.log(data.code == 1);
            if (data.code == 1) {
              message.success(data.info, 2);
              router.push('/newIndex');
            } else if (data.code == 0) {
              message.warning(data.info, 2);
            }
          },
          error: function() {
            message.error('网络异常，请稍后再试！', 2);
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
               <Input prefix={<Icon type='user' style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder='Username' />
            )
          }
        </Form.Item>
        <Form.Item>
          {
            getFieldDecorator(
              'password', { rules: [{ required: true, message: 'Please input your Password!' }] }
            ) (
              <Input prefix={<Icon type='lock' style={{ color: 'rgba(0,0,0,.25)' }} />} type='password' placeholder='Password' />
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
          <a className='styles.login-form-forgot' href=''>Forgot password</a>
          <br/>
          <Button type='primary' onClick={this.handleSubmit} className='styles.login_form_button'> Log in </Button>
          <br/>
          <Link to='/register'>Register now</Link>
        </Form.Item>
      </Form>
    );
  }
}

export default Form.create({ name: 'normal_login' })(LoginForm);
