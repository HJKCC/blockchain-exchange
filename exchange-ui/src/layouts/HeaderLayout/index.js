import { Menu } from 'antd';
import ReactDOM from 'react-dom';
import Link from 'umi/link';

import styles from './index.css';

let MenuPath = [
  {
    url: '/newIndex',
    key: 'newIndex',
    title: '主页'
  }, {
    url: '/user',
    key: 'user',
    title: '用户管理'
  }, {
    url: '/about',
    key: 'about',
    title: '关于我们'
  }
];

function HeaderLayout({pathname}) {
  let defaultPath = '/';
  let indexPath = ['/newIndex'];
  let userPath = ['/user'];
  let aboutPath = ['/about'];

  let selectedKey = '';
  if (defaultPath == pathname) {
    selectedKey = 'newIndex';
  } else if (indexPath.indexOf(pathname) != -1) {
    selectedKey = 'newIndex';
  } else if (userPath.indexOf(pathname) != -1) {
    selectedKey = 'user';
  } else if (aboutPath.indexOf(pathname) != -1) {
    selectedKey = 'about';
  }

  let MenuDom = MenuPath.map(
    (item, index) => {
      return (
        <Menu.Item key={item.key}>
          <Link to={item.url}>{item.title}</Link>
        </Menu.Item>
      )
    }
  );

  return (
    <Menu
      mode='horizontal'
      selectedKeys={[selectedKey]}
      style={{ lineHeight: '64px' }}
    >
      {MenuDom}
    </Menu>
  );
}

export default HeaderLayout;
