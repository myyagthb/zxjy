import { createStore } from 'vuex'


const USER = "USER"
const TAGLIST = []
const CATEGORYLIST = []
export default createStore({
  state: {
    user: window.SessionStorage.get(USER) || {},
    tag_list: window.SessionStorage.get(TAGLIST) || [],
    categorylist: window.SessionStorage.get(CATEGORYLIST) || []
  },
  getters: {
  },
  mutations: {
    setUser (state, _user) {
      state.user = _user;
      //把值放到缓存里面去
      window.SessionStorage.set(USER, _user);
    },
    setTAGLIST (state,_taglist){
      state.tag_list = _taglist
      ///把值放到缓存里面去
      window.SessionStorage.set(TAGLIST, _taglist);
    },
    setCATEGORYLIST(state,_categorylist){
      state.categorylist = _categorylist
      ///把值放到缓存里面去
      window.SessionStorage.set(CATEGORYLIST, _categorylist);
    }
  },
  actions: {
  },
  modules: {
  }
})
