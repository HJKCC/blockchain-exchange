import { Component } from 'react';
import reqwest from 'reqwest';
import { Form, Input, Modal, Button, message } from 'antd';

import styles from './index.css';

class ResetPassword extends Component {
  state = {
    visible: false,
    confirmDirty: false
  }

  showModel = () => {
    this.setState({
      visible: true
    });
  }

  handleOk = (e) => {
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        reqwest({
          url: '/exchange-web/user/resetPassword.action',
          method: 'post',
          data: {
            oldPassword: values.oldPassword,
            newPassword: values.newPassword
          },
          type: 'json',
          success: (data) => {
            if (data.code == 1) {
              message.success(data.info, 2);
            } else if (data.code == 0) {
              message.warning(data.info, 2);
            }
            this.setState({
              visible: false
            });
          }
        })
      }
    });
  }

  handleCancel = (e) => {
    this.props.form.resetFields();
    this.setState({
      visible: false
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
    if (value && value !== form.getFieldValue('newPassword')) {
      callback('Two passwords that you enter is inconsistent!');
    } else {
      callback();
    }
  }

  //与第二次输入密码作比较
  validateToNextPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && this.state.confirmDirty) {
      form.validateFields(['confirmPassword'], { force: true });
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

    return (
      <div>
        <a href='javascript:;' className={styles.href_name} onClick={this.showModel}>{this.props.children}</a>
        <Modal
          title='重设密码'
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
        <Form {...formItemLayout} className={styles.register_form} onSubmit={this.handleSubmit}>
          <Form.Item label='输入旧密码'>
            {
              getFieldDecorator(
                'oldPassword', {rules: [{required: true, message: 'Please input your old password!'},
                                        {max: 16, message: '最多16位!'}]}
              ) (
                <Input type='password' />
              )
          }
          </Form.Item>
          <Form.Item label='输入新密码' >
            {
              getFieldDecorator(
                'newPassword', {rules: [{required: true, message: 'Please your new password!'},
                                        {max: 16, message: '最多16位!'},
                                    {validator: this.validateToNextPassword,}]}
              ) (
                <Input type='password' onBlur={this.handleConfirmBlur} />
              )
            }
          </Form.Item>
          <Form.Item label='确认新密码' >
            {
              getFieldDecorator(
                'confirmPassword', {rules: [{required: true, message: 'Please confirm your password!'},
                                        {max: 16, message: '最多16位!'},
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

export default Form.create()(ResetPassword);
