import React, { Component } from "react";
import { Modal, Table, Input, Button, Popconfirm, message } from 'antd';
import { connect } from "dva";

import * as CommonUtil from '../../../utils/CommonUtil';
import Kline from '../Kline';
import TimingPrice from '../TimingPrice';
import styles from './index.css';


class ContractList extends Component {
  state = {
    selectedRowKeys: [],
    searchKey: ''
  }

  search = (value) => {
    this.setState({
      searchKey: value
    });
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
        title: '合约代码',
        dataIndex: 'contractCode',
        key: 'contractCode',
        width: 150,
        render: (text) => {
          return (
            <div title={text} className={styles.be_column_telephone}>{CommonUtil.formatNull(text)}</div>
          );
        }
      }, {
        title: '品种代码',
        dataIndex: 'symbol',
        key: 'symbol',
        width: 150,
        render: (text) => {
          return (
            <div title={text} className={styles.be_column_name}>{text}</div>
          );
        }
      }, {
        title: '合约类型',
        dataIndex: 'contractType',
        key: 'contractType',
        width: 150,
        render: (text) => {
          return (
            <div title={text} className={styles.be_column_telephone}>{CommonUtil.contractType(text)}</div>
          );
        }
      }, {
        title: '价格',
        key: 'price',
        width: 150,
        render: (record) => {
          return (
            <TimingPrice record={record}></TimingPrice>
          );
        }
      }, {
        title: '上市日期',
        dataIndex: 'createDate',
        key: 'createDate',
        width: 250,
        render: (text) => {
          return (
            <div title={text} className={styles.be_column_time}>{text}</div>
          );
        }
      }, {
        title: '交割日期',
        dataIndex: 'deliveryDate',
        key: 'deliveryDate',
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
            <Kline className={styles.be_column_edit} record={record} refrush={this.props.refrush}>
              <a href="javascript:;">k线</a>
            </Kline>
          );
        }
      }
    ];

    let filterList = this.props.list.filter(
      (item) => {
        if (item.symbol.includes(this.state.searchKey)) {
          return item;
        }
      }
    );

    const Search = Input.Search;

    return (
      <div style={{position:'relative'}}>
        <div className={styles.be_search_contract}>
            <span style={{marginRight: 10, fontWeight: 700 }}>筛选:</span>
            <Search
              placeholder="搜索"
              onSearch={this.search}
              style={{ width: 200 }}
            />
        </div>
        <div>
          <Table className={styles.be_table_contract}
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

export default connect()(ContractList);
