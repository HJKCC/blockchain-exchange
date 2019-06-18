import React, { Component } from "react";
import { connect } from "dva";
import ContractList from './ContractList';


class newIndex extends Component {
  state = {
  }

  refrush = () => {
    this.props.dispatch({
      type: 'newIndex/listContracts'
    });
  }

  render() {
    const { list } = this.props //获取下面的list
    return (
      <div>
        <ContractList list={list} refrush={this.refrush}></ContractList>
      </div>
    )
  }
}

// models的state变成组件的props
function mapStateToProps (state) {
    const { list } = state.newIndex;
    return {
        list
    };
}

export default connect(mapStateToProps)(newIndex);
