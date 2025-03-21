import { Component } from 'react';
import { Form, Modal, Input } from 'antd';
import * as ReqwestUtil from '../../../utils/ReqwestUtil';

import styles from './index.css';

class AddUser extends Component {
  state = {
    visible: false,
    id: '',
    name: '',
    password: '',
    telephone: '',
    email: '',
  }

  showModel = () => {
    this.setState({
      visible: true
    });
  }

  handleOk = (e) => {
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
            this.props.refrush();
          }
        )
      }
    });
  }

  handleCancel = (e) => {
    this.setState({
      visible: false
    });
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 6 },
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
      <div>
        <span onClick={this.showModel}>{this.props.children}</span>
        <Modal
          title='新增用户'
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
          <Form {...formItemLayout} className={styles.be_add_user_form}>
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
          </Form>
        </Modal>
      </div>
    )
  }
}

export default Form.create({ name: 'addUser' })(AddUser);
