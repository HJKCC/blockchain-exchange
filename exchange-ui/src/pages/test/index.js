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
        ['2013/2/6', 2132.68,2434.48,2427.7,2441.73],
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
    console.log(new URLSearchParams({symbol:'BTC_CW'}).toString());
    newIndexService.merged({symbol:'BTC_CW'})
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
      // 图表标题
      title: {
        text: '合约指数',
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
        top: 10,
        left: 'right',
        data: ['日K', 'MA7', 'MA30']
      },
      //图表相对位置
      grid: [
        {
            left: '10%',
            right: '8%',
            height: '60%'
        },
        {
            left: '10%',
            right: '8%',
            top: '85%',
            height: '20%'
        }
      ],
      // x轴配置，一般情况下单个 grid 组件最多只能放上下两个 x 轴，多于两个 x 轴需要通过配置 offset 属性防止同个位置多个 x 轴的重叠
      xAxis: [
        {
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
        {
          type: 'category',
          gridIndex: 1,
          data: data0.categoryData,
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
              data: data0.values,   // [open, close, lowest, highest]
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
          {
              name: 'Volume',
              type: 'bar',
              xAxisIndex: 1,
              yAxisIndex: 1,
              data:this.calculateMA(1, data0)
            }
      ]
    }

    return(
      <div>
        <Button onClick={this.handleSubmit}>test</Button>
        <Card title="折线图表之一">
          <ReactEcharts option={options} theme="Imooc"  style={{height:'400px'}}/>
        </Card>
      </div>
    )
  }
}

export default test;
