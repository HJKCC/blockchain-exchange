import { Component } from 'react';
import { Form, Input, Button } from 'antd';
import reqwest from 'reqwest';
import router from 'umi/router';
import * as ReqwestUtil from '../../utils/ReqwestUtil';

import styles from './index.less';

class WSForm extends Component {
  state = {
    confirmDirty: false,
  };  

  render() {
    let host = document.location.host;
    let websocket = null;  
    //判断当前浏览器是否支持WebSocket  
    if('WebSocket' in window) {
        websocket = new WebSocket("ws://" + host + "/exchange-web/webSocketHandler");
    }
  
    //连接发生错误的回调方法   
    websocket.onerror = function() {
      alert("WebSocket连接发生错误");  
      setMessageInnerHTML("WebSocket连接发生错误"); 
    
    };
    
    //连接成功建立的回调方法
    websocket.onopen = function() {
      alert("WebSocket连接成功");
      setMessageInnerHTML("WebSocket连接成功");  
    }
    
    //接收到消息的回调方法
    websocket.onmessage = function(event) {
      alert("接收到消息的回调方法");
      alert("这是后台推送的消息："+event.data);
      // websocket.close();
      alert("webSocket已关闭！");
    }
    
    //连接关闭的回调方法
    websocket.onclose = function() {
      setMessageInnerHTML("WebSocket连接关闭");
    }
    
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function() {
      closeWebSocket();
    }
    
    //关闭WebSocket连接
    function closeWebSocket() {
      websocket.close();
    } 

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
      document.getElementById('message').innerHTML = innerHTML;
    }

    return (
      <Form>
        <a id='message'></a>
      </Form>
    );
  }
}

export default Form.create({ name: 'ws' })(WSForm);
