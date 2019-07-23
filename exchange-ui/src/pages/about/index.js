import { Component } from 'react';
import { Form, Icon, Input, Button, Checkbox, message } from 'antd';
import reqwest from 'reqwest';
import router from 'umi/router';
import Link from 'umi/link';

import Product from '../Product';
import styles from './index.less';

class About extends Component {
  orderProduct = (productInfo) => {

  }

  render() {
    const productInfo = this.props.productInfo;
    const products = [{
      name: '商品1',
      price: '9.99'
    }, {
      name: '商品1212121212121212121212121212121',
      price: '99.99'
    }];
    var productList = products.map((item, index) => {
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

export default About;
