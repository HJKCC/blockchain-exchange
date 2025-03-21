import { Component } from 'react';
import { message, Modal } from 'antd';
import { connect } from "dva";

import * as productOrderService from '../../services/productOrderService';
import * as payOrderService from '../../services/payOrderService';
import Product from '../product';
import styles from './index.less';
import * as FetchUtil from '../../utils/FetchUtil';
import * as CommonUtil from '../../utils/CommonUtil';
import reqwest from 'reqwest';

class About extends Component {
  state = {
  }

  refrush = () => {
    this.props.dispatch({
      type: 'product/listProducts'
    });
  }

  confirmOrder = (productInfo) => {
    if (productInfo == '') {
      message.warn('请先选择商品后，再进行操作！');
      return 0;
    }
    Modal.confirm({
      title: '购买',
      content: '确认要付款吗?',
      onOk: () => {
        this.orderProduct(productInfo);
      },
      onCancel: () => {
        console.log('Cancel');
      }
    });
  }

  orderProduct = (productInfos) => {
    var productInfoList = new Array();
    productInfoList.push(productInfos);
    productOrderService.create(productInfoList).then(
      (data) => {
        if (data.code == 1) {
          this.createPayOrder(data.object);
        } else {
          message.warn(data.info, 1);
        }
      }
    ).catch(
      (e) => {
        message.error( `网络错误，请稍后再试！`, 1);
      }
    );
  }

  createPayOrder = (productOrderInfo) => {
    payOrderService.create(productOrderInfo).then(
      (data) => {
        if (data.code == 1) {
          this.pay(data.object);
        } else {
          message.warn(data.info, 1);
        }
      }
    ).catch(
      (e) => {
        message.error( `网络错误，请稍后再试！`, 1);
      }
    )
  }

  pay = (productOrderInfo) => {
    payOrderService.pay(productOrderInfo).then(
      (data) => {
        if (data.code == 1) {
          message.success(data.info, 1);
          const div=document.createElement('divform');
          div.innerHTML = data.object;
          document.body.appendChild(div);
          document.forms[0].acceptCharset='GBK';//保持与支付宝默认编码格式一致，如果不一致将会出现：调试错误，请回到请求来源地，重新发起请求，错误代码 invalid-signature 错误原因: 验签出错，建议检查签名字符串或签名私钥与应用公钥是否匹配
          document.forms[0].setAttribute('target', '_blank');
          document.forms[0].submit()
        } else {
          message.warn(data.info, 1);
        }
      }
    ).catch(
      (e) => {
        message.error( `网络错误，请稍后再试！`, 1);
      }
    )
  }

  render() {
    const { list } = this.props //获取下面的list
    const productInfo = this.props.productInfo;
    const products = [{
      name: '商品1',
      price: '9.99'
    }, {
      name: '商品1212121212121212121212121212121',
      price: '99.99'
    }];
    var productList = list.map((item, index) => {
      return (
        <Product
          key={index}
          productInfo={item}
          orderProduct={this.orderProduct}
        />
      );
    })
    return (
      <div>
        {productList}
      </div>
    );
  }
}

// models的state变成组件的props
function mapStateToProps (state) {
    const { list } = state.product;
    return {
        list
    };
}

export default connect(mapStateToProps)(About);
