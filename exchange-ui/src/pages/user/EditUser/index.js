import { Component } from 'react';
import { Form, Modal, Input } from 'antd';
import * as ReqwestUtil from '../../../utils/ReqwestUtil';
import styles from './index.css';

class EditUser extends Component {
  state = {
    visible: false
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
          url: '/exchange-web/user/modifyUser.action',
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
        sm: { span: 8 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
    };

    return (
      <div>
        <span onClick={this.showModel}>{this.props.children}</span>
        <Modal
          title='编辑用户'
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
          <Form {...formItemLayout} className={styles.be_add_user_form}>
            <Form.Item label='用户ID' className={styles.be_hide_form}>
              {
                getFieldDecorator(
                  'id', {initialValue: this.props.record.id}
                ) (
                  <Input type='text' disabled/>
                )
              }
            </Form.Item>
            <Form.Item label='有效标识' className={styles.be_hide_form}>
              {
                getFieldDecorator(
                  'isDeleted', {initialValue: this.props.record.isDeleted}
                ) (
                  <Input type='text' disabled/>
                )
              }
            </Form.Item>

            <Form.Item label='用户名'>
              {
                getFieldDecorator(
                  'name', {initialValue: this.props.record.name}
                ) (
                <Input type='text' disabled/>
                )
              }
            </Form.Item>
            <Form.Item label='用户密码'>
              {
                getFieldDecorator(
                  'password', {rules: [{required: true, message: 'Please input your password!'}],
                              initialValue: this.props.record.password}
                ) (
                  <Input type='password' />
                )
            }
            </Form.Item>
            <Form.Item label='手机号' >
              {
                getFieldDecorator(
                  'telephone', {initialValue: this.props.record.telephone}
                ) (
                  <Input type='text'/>
                )
              }
            </Form.Item>
            <Form.Item label='邮箱地址' >
              {
                getFieldDecorator(
                  'email', {initialValue: this.props.record.email}
                ) (
                  <Input type='text'/>
                )
              }
            </Form.Item>
          </Form>
        </Modal>
      </div>
    )
  }
}

export default Form.create({ name: 'editUser' })(EditUser);
