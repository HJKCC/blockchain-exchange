import { Component } from 'react';
import { Form, Modal, Input, Card } from 'antd';
import * as newIndexService from '../../../services/newIndexService';

import echarts from 'echarts/lib/echarts'
//导入折线图
import 'echarts/lib/chart/line';  //折线图是line,饼图改为pie,柱形图改为bar
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';
import 'echarts/lib/component/legend';
import 'echarts/lib/component/markPoint';
import ReactEcharts from 'echarts-for-react';

import * as CommonUtil from '../../../utils/CommonUtil';
import styles from './index.css';

class Kline extends Component {
  state = {
    visible: false,
    xAxis: "",
    volume: "",
    kLineData: "",
    list: ''
  }

  reflush = () => {
    let symbol = CommonUtil.getSymbol(this.props.record.symbol, this.props.record.contractType);
    newIndexService.kline(symbol)
      .then((data) => {
        if (data.code == 1) {
          this.setState({
            xAxis: data.xAxis,
            volume: data.volume,
            kLineData: data.kLineData
          });
        }
      });
  }

  showModel = () => {
    this.reflush();
    this.setState({
      visible: true
    });
  }

  handleClose = (e) => {
    this.setState({
      visible: false
    });
    // 清除定时器
    clearInterval(this.klineInterval);
  }

  calculateMA = (dayCount, kLineData) => {
      var result = [];
      for (var i = 0, len = kLineData.length; i < len; i++) {
          if (i < dayCount) {
              result.push('-');
              continue;
          }
          var sum = 0;
          for (var j = 0; j < dayCount; j++) {
              sum += kLineData[i - j][1];
          }
          result.push(sum / dayCount);
      }
      return result;
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
    let klineName = this.props.record.symbol + ' ' + CommonUtil.contractType(this.props.record.contractType);
    let options = {
      // 图表标题
      title: {
        text: '',
        left: 10
      },
      // 提示浮框组件
      tooltip: {
        trigger: 'axis',  //触发类型
        axisPointer: {
            type: 'cross'
        }
      },
      //
      axisPointer: {
          link: {xAxisIndex: 'all'},
          label: {
              backgroundColor: '#777'
          }
      },
      // 可选图例类型
      legend: {
        top: '2%',
        left: 'center',
        data: ['日K', 'MA7', 'MA30']
      },
      //图表相对位置
      grid: [
        {
            left: '10%',
            right: '8%',
            top: '10%',
            height: '60%'
        },
        {
            left: '10%',
            right: '8%',
            top: '75%',
            height: '20%'
        }
      ],
      // x轴配置，一般情况下单个 grid 组件最多只能放上下两个 x 轴，多于两个 x 轴需要通过配置 offset 属性防止同个位置多个 x 轴的重叠
      xAxis: [
        {
          type: 'category',
          data: this.state.xAxis,
          scale: true,
          boundaryGap : false,
          axisLine: {onZero: false},
          splitLine: {show: false},
          splitNumber: 20,
          min: 'dataMin',
          max: 'dataMax'
        },
        {
          type: 'category',
          gridIndex: 1,
          data: this.state.xAxis,
          scale: true,
          boundaryGap : false,
          axisLine: {onZero: false},
          splitLine: {show: false},
          splitNumber: 20,
          min: 'dataMin',
          max: 'dataMax'
        },
      ],
      // y轴配置，一般情况下单个 grid 组件最多只能放左右两个 y 轴，多于两个 y 轴需要通过配置 offset 属性防止同个位置多个 Y 轴的重叠
      yAxis: [
        {
          type: 'value',
          scale: true,
          splitArea: {
              show: true
          }
        },
        {
          scale: true,
          gridIndex: 1,
          splitNumber: 2,
          axisLabel: {show: false},
          axisLine: {show: false},
          axisTick: {show: false},
          splitLine: {show: false}
        }
      ],
      // 区域缩放
      dataZoom: [
        // inside: 缩放视图，通过鼠标拖拽、鼠标滚轮、手指滑动（触屏上）来缩放或漫游坐标系。显示数据：50%-100%
        {
          type: 'inside',
          xAxisIndex: [0, 1],
          start: 50,
          end: 100
        }
      ],
      // 系列列表。每个系列通过 type 决定自己的图表类型。line：折线；bar：柱状/条形图；pie：饼图；scatter：散点（气泡）图；candlestick：k线图...
      series: [
          {
              name: '日K',
              type: 'candlestick',
              data: this.state.kLineData,   // [open, close, lowest, highest]
              itemStyle: {
                  normal: {
                      color: '#ec0000',  // 阳线颜色
                      color0: '#00da3c',  // 阴线颜色
                      borderColor: '#8A0000',  // 阳线的描边颜色
                      borderColor0: '#008F28'  // 阴线的描边颜色
                  }
              },
              markPoint: {  // 图表标注
                  label: {  // 标注的文本
                      normal: {
                          formatter: function (param) {  //标签内容格式器
                              return param != null ? Math.round(param.value) : '';
                          }
                      }
                  },
                  data: [
                      {
                          name: '标点',
                          coord: ['2013/5/31', 2100],  // 标注的坐标
                          value: 2100,
                          itemStyle: {
                              normal: {color: 'rgb(41,60,85)'}
                          }
                      },
                      {
                          name: 'highest value',
                          type: 'max',
                          valueDim: 'highest'
                      },
                      {
                          name: 'lowest value',
                          type: 'min',
                          valueDim: 'lowest'
                      },
                      {
                          name: 'average value on close',
                          type: 'average',
                          valueDim: 'close'
                      }
                  ]
              },
              markLine: {  // 图表标线
                  symbol: ['none', 'none'],
                  data: [
                      [
                          {
                              name: 'from lowest to highest',
                              type: 'min',
                              valueDim: 'lowest',
                              symbol: 'circle',
                              symbolSize: 10,
                              label: {
                                  normal: {show: false},
                                  emphasis: {show: false}
                              }
                          },
                          {
                              type: 'max',
                              valueDim: 'highest',
                              symbol: 'circle',
                              symbolSize: 10,
                              label: {
                                  normal: {show: false},
                                  emphasis: {show: false}
                              }
                          }
                      ],
                      {
                          name: 'min line on close',
                          type: 'min',
                          valueDim: 'close'
                      },
                      {
                          name: 'max line on close',
                          type: 'max',
                          valueDim: 'close'
                      }
                  ]
              }
          },
          {
              name: 'MA7',
              type: 'line',
              data: this.calculateMA(7, this.state.kLineData),
              smooth: true,
              lineStyle: {
                  normal: {opacity: 0.5}
              }
          },
          {
              name: 'MA30',
              type: 'line',
              data: this.calculateMA(30, this.state.kLineData),
              smooth: true,
              lineStyle: {
                  normal: {opacity: 0.5}
              }
          },
          {
              name: 'Volume',
              type: 'bar',
              xAxisIndex: 1,
              yAxisIndex: 1,
              data:this.state.volume
            }
      ]
    }
    return (
      <span>
        <span onClick={this.showModel}>{this.props.children}</span>
        <Modal
          title={klineName}
          visible={this.state.visible}
          onOk={this.handleClose}
          onCancel={this.handleClose}

          width='750px'
        >
          <ReactEcharts option={options} theme="Imooc" style={{height:'450px',width: '700px'}}/>
        </Modal>
      </span>
    );
  }
}

export default Kline;
