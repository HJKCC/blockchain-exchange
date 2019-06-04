import reqwest from 'reqwest';
import { Menu, Dropdown, Layout, Form, Icon, Input, Button, Checkbox, Breadcrumb, message } from 'antd';
import { Link, router } from 'umi';

import * as CommonUtil from '../utils/CommonUtil';
import HeaderLayout from './HeaderLayout';
import UserInfo from './UserInfo';
import ResetPassword from '../pages/user/ResetPassword';

import styles from './index.css';

const { Header, Content, Footer, Sider } = Layout;

function BasicLayout(props) {
  let loginUserName = localStorage.loginUserName;
  let noLogin = CommonUtil.isEmpty(loginUserName);

  function login() {
    router.push('/login');
  }

  function register() {
    router.push('/register');
  }

  function logout() {
    reqwest({
      url: '/exchange-web/logout.action',
      method: 'post',
      data: {},
      type: 'json',
      success: function(data) {
        console.log(data.code == 1);
        if (data.code == 1) {
          // localStorage.removeItem('loginUserName');
          localStorage.clear();
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

  const loginOrRegister = (
    <Form>
        <Button type='primary' className={styles.login_button} onClick={login}>登录</Button>
        <Button type='primary' onClick={register}>注册</Button>
    </Form>
  );

  const settingMenu = (
    <Menu>
      <Menu.Item>
        {loginUserName}
      </Menu.Item>
      <Menu.Item>
        <UserInfo>个人资料</UserInfo>
      </Menu.Item>
      <Menu.Item>
        <ResetPassword>重设密码</ResetPassword>
      </Menu.Item>
      <Menu.Item>
        <a rel='noopener noreferrer' onClick={logout.bind(this)} href='javascript:;'>
          注销
        </a>
      </Menu.Item>
    </Menu>
  );

  return (
    <div className={styles.normal}>

      <div className={styles.settingMenu}>
        {noLogin
            ?
          {...loginOrRegister}
            :
          <Dropdown overlay={settingMenu}>
            <Button type='primary'>
              {loginUserName} <Icon type='down' />
            </Button>
          </Dropdown>
        }
      </div>

      <Layout className={styles.MainLayout}>
         <div className={styles.logo}>BE</div>
         <Header className={styles.HeaderLayout}>
            <HeaderLayout pathname={props.location.pathname}/>
         </Header>
         <Breadcrumb style={{ margin: '2px 0' }}></Breadcrumb>
         <Content className={styles.MainContent}>
            <div>{props.children}</div>
         </Content>
         <Breadcrumb style={{ margin: '2px 0' }}></Breadcrumb>
         <Footer style={{ textAlign: 'center' }}>Blockchain-Exchange ©2019 Created by HJKCC</Footer>
      </Layout>
    </div>

  );
}

export default BasicLayout;
