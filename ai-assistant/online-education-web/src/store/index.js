import { createStore } from 'vuex'


const USER = "USER"
export default createStore({
  state: {
    user: window.SessionStorage.get(USER) || {},
  },
  getters: {
  },
  mutations: {
    setUser (state, _user) {
      state.user = _user;
      //把值放到缓存里面去
      window.SessionStorage.set(USER, _user);
    }
  },
  actions: {
  },
  modules: {
  }
})
