import { Component } from 'react';
import { Button, Card } from 'antd';

import styles from './index.css';
import * as newIndexService from '../../services/newIndex';

//下面是按需加载
import echarts from 'echarts/lib/echarts'
//导入折线图
import 'echarts/lib/chart/line';  //折线图是line,饼图改为pie,柱形图改为bar
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';
import 'echarts/lib/component/legend';
import 'echarts/lib/component/markPoint';
import ReactEcharts from 'echarts-for-react';

class test extends Component {
  state = {
    // 数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)
    data: [
        ['2013/1/24', 2320.26,2320.26,2287.3,2362.94],
        ['2013/1/25', 2300,2291.3,2288.26,2308.38],
        ['2013/1/28', 2295.35,2346.5,2295.35,2346.92],
        ['2013/1/29', 2347.22,2358.98,2337.35,2363.8],
        ['2013/1/30', 2360.75,2382.48,2347.89,2383.76],
        ['2013/1/31', 2383.43,2385.42,2371.23,2391.82],
        ['2013/2/1', 2377.41,2419.02,2369.57,2421.15],
        ['2013/2/4', 2425.92,2428.15,2417.58,2440.38],
        ['2013/2/5', 2411,2433.13,2403.3,2437.42],
        ['2013/2/6', 2432.68,2434.48,2427.7,2441.73],
        ['2013/2/7', 2430.69,2418.53,2394.22,2433.89],
        ['2013/2/8', 2416.62,2432.4,2414.4,2443.03],
        ['2013/2/18', 2441.91,2421.56,2415.43,2444.8],
        ['2013/2/19', 2420.26,2382.91,2373.53,2427.07],
        ['2013/2/20', 2383.49,2397.18,2370.61,2397.94],
        ['2013/2/21', 2378.82,2325.95,2309.17,2378.82]
    ]
  }

  //注册
  handleSubmit = (e) => {
    newIndexService.merged('BTC_CW')
      .then((data) => {
        console.log(data);
        if (data.status == 'ok') {
          console.log(data.status);
        }
      });
  }

  splitData = (rawData) => {
      var categoryData = [];
      var values = []
      for (var i = 0; i < rawData.length; i++) {
          categoryData.push(rawData[i].splice(0, 1)[0]);
          values.push(rawData[i])
      }
      return {
          categoryData: categoryData,
          values: values
      };
  }

  calculateMA = (dayCount, data0) => {
      var result = [];
      for (var i = 0, len = data0.values.length; i < len; i++) {
          if (i < dayCount) {
              result.push('-');
              continue;
          }
          var sum = 0;
          for (var j = 0; j < dayCount; j++) {
              sum += data0.values[i - j][1];
          }
          result.push(sum / dayCount);
      }
      return result;
  }



  render(){
    let data0 = this.splitData(this.state.data);
    let options = {
      //图表标题
      title: {
          text: '合约指数',
          left: 50
      },
      //提示框组件
      tooltip: {
          trigger: 'axis',  //触发类型
          axisPointer: {
              type: 'cross'
          }
      },
      //可选图例类型
      legend: {
          data: ['日K', 'MA7', 'MA30']
      },
      //图表相对位置
      grid: {
          left: '10%',
          right: '10%',
          bottom: '15%'
      },
      // x轴配置
      xAxis: {
          type: 'category',
          data: data0.categoryData,
          scale: true,
          boundaryGap : false,
          axisLine: {onZero: false},
          splitLine: {show: false},
          splitNumber: 20,
          min: 'dataMin',
          max: 'dataMax'
      },
      yAxis: {
          type: 'value',
          scale: true,
          splitArea: {
              show: true
          }
      },
      dataZoom: [
          {
              type: 'inside',
              start: 50,
              end: 100
          },
          {
              show: true,
              type: 'slider',
              y: '90%',
              start: 50,
              end: 100
          }
      ],
      series: [
          {
              name: '日K',
              type: 'candlestick',
              data: data0.values,
              itemStyle: {
                  normal: {
                      color: '#ec0000',
                      color0: '#00da3c',
                      borderColor: '#8A0000',
                      borderColor0: '#008F28'
                  }
              },
              markPoint: {
                  label: {
                      normal: {
                          formatter: function (param) {
                              return param != null ? Math.round(param.value) : '';
                          }
                      }
                  },
                  data: [
                      {
                          name: '标点',
                          coord: ['2013/5/31', 2300],
                          value: 2300,
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
                  ],
                  tooltip: {
                      formatter: function (param) {
                          return param.name + '<br>' + (param.data.coord || '');
                      }
                  }
              },
              markLine: {
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
              data: this.calculateMA(7, data0),
              smooth: true,
              lineStyle: {
                  normal: {opacity: 0.5}
              }
          },
          {
              name: 'MA30',
              type: 'line',
              data: this.calculateMA(30, data0),
              smooth: true,
              lineStyle: {
                  normal: {opacity: 0.5}
              }
          },
      ]
    }

    return(
      <div>
        <Card title="折线图表之一">
          <ReactEcharts option={options} theme="Imooc"  style={{height:'400px'}}/>
        </Card>

      </div>
    )
  }
}

export default test;
