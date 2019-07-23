import * as newIndexServive from '../services/newIndex';

export default {
  // 命名空间名字
  namespace: 'newIndex',

  // 数据初始化
  state: {
    list: []
  },

  // 更新页面state
  reducers: {
    // update自定义方法名
    update(state, { payload: { list }}) {
      return { ...state, list};
    },
  },

  // 与后台交互，处理数据逻辑
  effects:{
    // fetchNum方法名，payload2是传来的参数，是个对象，如果没参数可以写成{_,{call,put,select}}
    // yield put表示请求成功后的操作, 触发reducer中的方法
    *listContracts(_, { call, put }) {
      // myService是引入service层那个js的一个名字，anum是后台要求传的参数，data就是后台返回来的数据
      const res = yield call(newIndexServive.listContracts);
      console.log(res);
      yield put({
        type: 'update',
        payload: {
          list: res.rows,
        },
      })
    },

    *kline({ payload }, { call, put }) {
      // myService是引入service层那个js的一个名字，anum是后台要求传的参数，data就是后台返回来的数据
      const res = yield call(newIndexServive.kline, payload);
    },
  },

  // 订阅监听，比如我们监听路由，进入页面就触发某个方法
  subscriptions:{
    setup({ dispatch, history}) {
      return history.listen(async ({ pathname }) => {
        // 当进入testdemo这路由，就会触发fetchUser方法
        if (pathname==='/newIndex') {
          dispatch({ type: 'listContracts', name:{}})
        }
      })
    }
  }
}
