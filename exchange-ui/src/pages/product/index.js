import { Component } from 'react';
import { Form, Icon, Input, Button, Checkbox, message } from 'antd';
import reqwest from 'reqwest';
import router from 'umi/router';
import Link from 'umi/link';

import styles from './index.less';

class Product extends Component {  

  render() {
    const productInfo = this.props.productInfo;
    return (
      <div>
        <div className={styles.be_product}>
          <div className={styles.be_product_img}/>
          <div className={styles.be_product_name} title={productInfo.name}>{productInfo.name}</div>
          <div className={styles.be_product_price}>￥{productInfo.price}</div>
          <div className={styles.be_product_order}>
            <Button type='primary' onClick={this.props.orderProduct.bind(this, productInfo)}>购买</Button>
          </div>
        </div>
      </div>
    );
  }
}

export default Product;
