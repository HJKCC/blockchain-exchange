import React, { Component } from "react";
import { connect } from "dva";
import UserList from './UserList';


class user extends Component {
  state = {
  }

  refrush = () => {
    this.props.dispatch({
      type: 'user/listUsers',
      name:{}
    });
  }

  render() {
    const { list } = this.props //获取下面的list
    return (
      <div>
        <UserList list={list} refrush={this.refrush}></UserList>
      </div>
    )
  }
}

// models的state变成组件的props
function mapStateToProps (state) {
    const { list } = state.user;
    return {
        list
    };
}

export default connect(mapStateToProps)(user);
