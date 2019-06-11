import { Component } from 'react';
import { Form, Modal, Input } from 'antd';
import * as newIndexService from '../../../services/newIndex';

import echarts from 'echarts/lib/echarts'
//导入折线图
import 'echarts/lib/chart/line';  //折线图是line,饼图改为pie,柱形图改为bar
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';
import 'echarts/lib/component/legend';
import 'echarts/lib/component/markPoint';
import ReactEcharts from 'echarts-for-react';

import * as CommonUtil from '../../../utils/CommonUtil';

class Kline extends Component {
  state = {
    visible: false,
    list: ''
  }

  reflush = () => {
    let symbol = CommonUtil.getSymbol(this.props.record.symbol, this.props.record.contract_type);
    newIndexService.kline(symbol)
      .then((data) => {
        if (data.status == 'ok') {
          this.setState({
            list: ''
          });
        }
      });
  }

  showModel = () => {
    this.setState({
      visible: true
    });
    this.reflush();
  }

  componentDidMount() {
    if(this.state.visible) {
      this.reflush();
      // 定时器
      this.klineInterval = setInterval(() => {
          this.reflush();
        }, 60000);
    }
  }

  componentWillUnmount() {
    // 清除定时器
    clearInterval(this.klineInterval);
  }

  render() {
    return (
      <span>
        <span onClick={this.showModel}>{this.props.children}</span>
        <div></div>
      </span>
    );
  }
}

export default Kline;
