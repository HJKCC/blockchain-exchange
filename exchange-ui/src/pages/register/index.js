import { Component } from 'react';
import { Form, Input, Button } from 'antd';
import reqwest from 'reqwest';
import router from 'umi/router';
import * as ReqwestUtil from '../../utils/ReqwestUtil';

import styles from './index.css';

class RegisterForm extends Component {
  state = {
    confirmDirty: false,
  };

  //注册
  handleSubmit = (e) => {
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        ReqwestUtil.request({
          url: '/exchange-web/user/register.action',
          data: values
        }).then(
          (data) => {
            this.setState({
              visible: false
            });
            router.push('/login');
          }
        )
      }
    });
  }

  //输入值非空，修改state的confirmDirty值
  handleConfirmBlur = (e) => {
    const value = e.target.value;
    this.setState({ confirmDirty: this.state.confirmDirty || !!value });
  }

  //与第一次输入密码作比较
  compareToFirstPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && value !== form.getFieldValue('password')) {
      callback('Two passwords that you enter is inconsistent!');
    } else {
      callback();
    }
  }

  //与第二次输入密码作比较
  validateToNextPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && this.state.confirmDirty) {
      form.validateFields(['confirm'], { force: true });
    }
    callback();
  }

  render() {
    const { getFieldDecorator } = this.props.form;

    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 8 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
    };
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 16,
          offset: 8,
        },
      },
    };

    return (
      <Form {...formItemLayout} className={styles.register_form} onSubmit={this.handleSubmit}>
        <Form.Item label='用户名'>
          {
            getFieldDecorator(
              'name', {rules: [{required: true, message: 'Please input your name!'}]}
            ) (
            <Input type='text'/>
            )
          }
        </Form.Item>
        <Form.Item label='输入密码'>
          {
            getFieldDecorator(
              'password', {rules: [{required: true, message: 'Please input your password!'},
                                   {validator: this.validateToNextPassword}]}
            ) (
              <Input type='password' />
            )
        }
        </Form.Item>
        <Form.Item label='确认密码' >
          {
            getFieldDecorator(
              'confirm', {rules: [{required: true, message: 'Please confirm your password!'},
                                  {validator: this.compareToFirstPassword,}]}
            ) (
              <Input type='password' onBlur={this.handleConfirmBlur} />
            )
          }
        </Form.Item>
        <Form.Item  {...tailFormItemLayout} className={styles.register_form_button}>
          <Button type='primary' onClick={this.handleSubmit}>Register</Button>
        </Form.Item>
      </Form>
    );
  }
}

export default Form.create({ name: 'register' })(RegisterForm);
