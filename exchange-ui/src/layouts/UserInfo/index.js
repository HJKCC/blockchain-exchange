import { Component } from 'react';
import reqwest from 'reqwest';
import { Modal, Button, message } from 'antd';

import styles from './index.css';

class UserInfo extends Component {
  state = {
    visible: false,
    id: '',
    name: '',
    password: '',
    createdTime: '',
    modifiedTime: ''
  }

  showModel = () => {
    reqwest({
      url: '/exchange-web/user/getCurrentUser.action',
      method: 'post',
      type: 'json',
      success: (data) => {
        if (data.code == 1) {
          let user = data.object;
          this.setState({
            visible: true,
            id: user.id,
            name: user.name,
            password: user.password,
            createdTime: user.createdTime,
            modifiedTime: user.modifiedTime
          });
        } else if (data.code == 0) {
          message.warning(data.info, 2);
        }
      },
      error: () => {
        message.error('网络异常，请稍后再试！', 2);
      }
    })
  }

  handleOk = (e) => {
    this.setState({
      visible: false
    });
  }

  handleCancel = (e) => {
    this.setState({
      visible: false
    });
  }

  render() {
    return (
      <div>
        <a href='javascript:;' className={styles.href_name} onClick={this.showModel}>{this.props.children}</a>
        <Modal
          title='当前用户信息'
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
          <div className={styles.Model_content}>
            <div className={styles.Model_rows}>
              <div className={styles.name}>用户ID:</div>
              <div className={styles.value}>{this.state.id}</div>
            </div>
            <div className={styles.Model_rows}>
              <div className={styles.name}>用户姓名:</div>
              <div className={styles.value}>{this.state.name}</div>
            </div>
            <div className={styles.Model_rows}>
              <div className={styles.name}>创建时间:</div>
              <div className={styles.value}>{this.state.createdTime}</div>
            </div>
            <div className={styles.Model_rows}>
              <div className={styles.name}>创建时间:</div>
              <div className={styles.value}>{this.state.modifiedTime}</div>
            </div>
          </div>
        </Modal>
      </div>
    )
  }
}

export default UserInfo;
