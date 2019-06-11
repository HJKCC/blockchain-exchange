import React, { Component } from "react";
import { Modal, Table, Input, Button, Popconfirm, message } from 'antd';
import { connect } from "dva";

import * as CommonUtil from '../../../utils/CommonUtil'
import AddUser from '../AddUser';
import EditUser from '../EditUser';
import styles from './index.css';


class UserList extends Component {
  state = {
    selectedRowKeys: [],
    searchKey: ''
  }

  search = (value) => {
    this.setState({
      searchKey: value
    });
  }

  handleDelete = (value) => {
    Modal.confirm({
      title: '删除用户',
      content: '确认要删除所选用户吗?',
      onOk: () => {
        this.removeUsers(value);
      },
      onCancel: () => {
        console.log('Cancel');
      }
    });
  };

  removeUsers = (value) => {
    if (value == '') {
      message.warn('请先选择用户后，再进行操作');
      return 0;
    }
    if (typeof(value) != 'number') {
      value = value.join(',');
    } else {
      value = value.toString();
    }
    this.props.dispatch({
      type: 'user/removeUsers',
      ids: value
    }).then(
      (data) => {
        if (data.code == 1) {
          message.success(data.info, 1);
          this.setState({
            selectedRowKeys: []
          });
          this.props.refrush();
        }
      }
    )
  }

  render() {
    const rowSelection = {
      selectedRowKeys: this.state.selectedRowKeys,
      onChange: (selectedRowKeys) => {
        this.setState({
          selectedRowKeys: selectedRowKeys
        });
      }
    };

    const columns = [
      {
        title: '用户名',
        dataIndex: 'name',
        key: 'name',
        width: 150,
        render: (text) => {
          return (
            <div title={text} className={styles.be_column_name}>{text}</div>
          );
        }
      }, {
        title: '手机号',
        dataIndex: 'telephone',
        key: 'telephone',
        width: 150,
        render: (text) => {
          return (
            <div title={text} className={styles.be_column_telephone}>{CommonUtil.formatNull(text)}</div>
          );
        }
      }, {
        title: '邮箱地址',
        dataIndex: 'email',
        key: 'email',
        width: 250,
        render: (text) => {
          return (
            <div title={text} className={styles.be_column_email}>{CommonUtil.formatNull(text)}</div>
          );
        }
      }, {
        title: '创建时间',
        dataIndex: 'createdTime',
        key: 'createdTime',
        width: 250,
        render: (text) => {
          return (
            <div title={text} className={styles.be_column_time}>{text}</div>
          );
        }
      }, {
        title: '修改时间',
        dataIndex: 'modifiedTime',
        key: 'modifiedTime',
        width: 250,
        render: (text) => {
          return (
            <div title={text} className={styles.be_column_time}>{text}</div>
          );
        }
      }, {
        title: '操作',
        key: 'operations',
        render: (record) => {
          return (
            <div className={styles.be_column_operations}>
              <EditUser className={styles.be_column_edit} record={record} refrush={this.props.refrush}>
                <a href="javascript:;">edit</a>
              </EditUser>
              <div className={styles.be_column_delete}>
                <a href="javascript:void(0)" onClick={this.handleDelete.bind(this,record.id)}>delete</a>
              </div>
            </div>
          );
        }
      }
    ];

    let filterList = this.props.list.filter(
      (item) => {
        if (item.name.includes(this.state.searchKey)) {
          return item;
        }
      }
    );

    const Search = Input.Search;

    return (
      <div style={{position:'relative'}}>
        <div className={styles.be_search_user}>
            <span style={{marginRight: 10, fontWeight: 700 }}>筛选:</span>
            <Search
              placeholder="搜索用户名"
              onSearch={this.search}
              style={{ width: 200 }}
            />
        </div>
        <div className={styles.be_add_user}>
          <AddUser refrush={this.props.refrush}>
            <Button type='primary'>新增用户</Button>
          </AddUser>
        </div>
        <div className={styles.be_del_user}>
          <Button type='primary' onClick={this.handleDelete.bind(this, this.state.selectedRowKeys)}>删除用户</Button>
        </div>
        <div>
          <Table className={styles.be_table_user}
            columns={columns}
            rowKey='id'
            rowSelection={rowSelection}
            dataSource={filterList}
            scroll={filterList.length > 10 ? {y : 450}: {y : false}}
          />
        </div>
      </div>
    );
  }
}

export default connect()(UserList);
