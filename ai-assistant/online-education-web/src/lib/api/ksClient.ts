
import yaml from 'js-yaml/dist/js-yaml'
import { NextApiRequest, NextApiResponse } from 'next'
import { IS_PLATFORM } from '../constants'
import {KS_URL,KS_URL_V2,KS_AUTH_URL,KS_APP_URL_V1,KS_API_V1,
  KS_APIS_APPS_V1,KS_BILL_URL_V1,
  KS_USER,KS_PWD} from "lib/constants";
import {post,postForm,get,delete_} from "lib/common/fetch";
import { getOrgSubByName, getOrgSubBySlug } from 'pages/api/organizations/common';
import { getRepoByName } from 'lib/repo';

var release = null

const get_jwt = async() => {
  // console.log('getting jwt from ks')
  // const headers = constructHeaders(req.headers)
  let headers = {'Content-Type': 'application/x-www-form-urlencoded'}
  const data = new URLSearchParams({
    'grant_type': 'password',
    'username': `${KS_USER}`,
    'password': `${KS_PWD}`,
    'client_id': 'kubesphere',
    'client_secret': 'kubesphere'
  })
  // const data = {'grant_type':'password',
  //   'username':'admin',
  //   'password':'1myyaksPD',
  //   'client_id':'kubesphere',
  //   'client_secret':'kubesphere'
  // }
  // const query = querystring.stringify(data);

  // console.log('ks auth url is '+`${KS_AUTH_URL}`)
  // console.log('ks auth query url is '+`${KS_AUTH_URL}?`+data)
  const r = await postForm(`${KS_AUTH_URL}`,data, { headers })
  // const r = await post(`${KS_AUTH_URL}`, {}, { headers })

  // console.log('ks auth query resp '+JSON.stringify(r))

  return r
}

const get_ws_usage = async(req: NextApiRequest, res: NextApiResponse,ws:string,
  start:string,end:string) => {
  //first get jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  const data = new URLSearchParams({
    'start': start,
    'end': end,
    'step': '3600s',
    'metrics_filter': 'meter_workspace_cpu_usage|meter_workspace_memory_usage|meter_workspace_net_bytes_transmitted|meter_workspace_net_bytes_received|meter_workspace_pvc_bytes_total',
    'resources_filter': ws
  })

  let h = {'Authorization':'Bearer '+jwt.access_token}
  let r = await get(`${KS_BILL_URL_V1}/workspaces/${ws}?`+data,{headers:h})

  return r
}

const create_ws = async(req: NextApiRequest, res: NextApiResponse,ws:any) => {
  //first get jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  // console.log('got ks jwt '+JSON.stringify(jwt))

  //then create ws
  console.log('ws name is '+ ws)
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let r = await post(`${KS_URL}/workspacetemplates`,
  {"apiVersion":"tenant.kubesphere.io/v1alpha2","kind":"WorkspaceTemplate","metadata":{"name":ws,"annotations":{"kubesphere.io/creator":"admin"}},"spec":{"template":{"spec":{"manager":"admin"},"metadata":{"annotations":{"kubesphere.io/creator":"admin"}}}}}
  ,{headers:h})

  return r
}

const rm_ws = async(req: NextApiRequest, res: NextApiResponse,ws:any) => {
  //first get jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  // console.log('got ks jwt '+JSON.stringify(jwt))

  //then rm ws
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let r = await delete_(`${KS_URL}/workspacetemplates/${ws}`,{},{headers:h})

  return r
}

const create_ns = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  // console.log('got ks jwt '+JSON.stringify(jwt))
  //then create ns
  console.log('ns is ' + ns)
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let r = await post(`${KS_URL_V2}/workspaces/${ws}/namespaces`,
  {"apiVersion":"v1","kind":"Namespace","metadata":{"name":ns,"labels":{"kubesphere.io/workspace":ws},"annotations":{"kubesphere.io/creator":"admin"}}}
  ,{headers:h})

  return r
}

const rm_ns = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  // console.log('got ks jwt '+JSON.stringify(jwt))
  //then create ns
  console.log('ns is ' + ns)
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let r = await delete_(`${KS_URL_V2}/workspaces/${ws}/namespaces/${ns}`,{},{headers:h})

  return r
}

const get_ws_repos = async(req: NextApiRequest, res: NextApiResponse,
  ws:string) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  //then get repos
  const data = new URLSearchParams({
    'orderBy': 'create_time',
    'paging': 'limit=10,page=1',
    'conditions': 'status=active,reverse=true',
  })
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let r = await get(`${KS_APP_URL_V1}/workspaces/${ws}/repos?`+data,{headers:h})

  return r
}

//get app charts by version id
const get_app_charts = async(req: NextApiRequest, res: NextApiResponse,
  app_id:string,vid:string) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  // console.log('got ks jwt '+JSON.stringify(jwt))
  //then get charts
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let r = await get(`${KS_APP_URL_V1}/apps/${app_id}/versions/${vid}/files`,{headers:h})

  return r
}

//get app versions
const get_app_versions = async(req: NextApiRequest, res: NextApiResponse,
  app_id:string) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  //then get versions
  const data = new URLSearchParams({
    'orderBy': 'sequence',
    'paging': 'limit=200,page=1',
    'conditions': 'status=draft|submitted|rejected|in-review|passed|active|suspended',
    'reverse': 'true'
  })
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let r = await get(`${KS_APP_URL_V1}/apps/${app_id}/versions?`+data,{headers:h})

  return r
}

//get apps in repo
const get_apps = async(req: NextApiRequest, res: NextApiResponse,
  repo:any) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  //then get repos
  const data = new URLSearchParams({
    'orderBy': 'create_time',
    'paging': 'limit=12,page=1',
    'conditions': 'status=active,repo_id='+`${repo.repo_id}`,
    'reverse': 'true'
  })
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let r = await get(`${KS_APP_URL_V1}/apps?`+data,{headers:h})

  return r
}

//get apps status
const get_apps_status = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  //then get repos
  const data = new URLSearchParams({
    'orderBy': 'status_time',
    'paging': 'limit=10,page=1',
    'conditions': 'status=creating|active|failed|deleting|upgrading|created|upgraded',
  })
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let r = await get(`${KS_APP_URL_V1}/workspaces/${ws}/namespaces/${ns}/applications?`+data,{headers:h})

  return r
}

//get app status
const get_app_status = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string,appid:string) => {
  //first get all apps status
  const r = await get_apps_status(req,res,ws,ns)

  //then filter
  const app = r.items.filter((i)=> i.cluster.app_id === appid)

  return app
}

const add_ws_repo = async(req: NextApiRequest, res: NextApiResponse,
  ws:string) => {
  let r = await getRepoByName(req,res,'hxydt.cn')
  const url = r.type+"://"+r.username+":"+r.pwd+"@"+r.url
  console.log('repo url '+url)

  //get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description }) 

  const data = {
    "type":r.type,
    "name":r.name,
    "sync_period":"5m",
    "repoType":r.repo_type,
    "visibility":"public",
    "credential":"{}",
    "providers":["kubernetes"],
    "url":url,
    "app_default_status":"active"
  }

  let h = {'Authorization':'Bearer '+jwt.access_token}
  r = await post(`${KS_APP_URL_V1}/workspaces/${ws}/repos`,data,{headers:h})

  return r
}

function get_db_host()
{
  return 'hxy-'+release+'-pg'
}

const deploy_app = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string,app:any,chart:any) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let conf = atob(chart['values.yaml'])
  conf = yaml.safeLoad(conf)
  conf = yaml.safeDump(JSON.parse(JSON.stringify(conf)), { noRefs: true })
  // console.log('app conf is ' + conf)
  release = (Math.random() + 1).toString(36).substring(6)
  const data = {
    'app_id': app.app_id,
    'conf': conf,
    //the following name key is the .Release.Name in chart,it is hxy-xxx in this case
    'name': 'hxy-'+release,
    'version_id': app.version_id,
  }
  let r = await post(`${KS_APP_URL_V1}/workspaces/${ws}/namespaces/${ns}/applications`,data,{headers:h})

  return r
}

const gendbsecret = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string,d:any) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  let h = {'Authorization':'Bearer '+jwt.access_token}
  const data = {
    "apiVersion":"v1",
    "kind":"Secret",
    "metadata":{"namespace":ns,
    "labels":{},
    "name":"hxy-db",
    "annotations":{"kubesphere.io/creator":"admin"}},
    "type":"Opaque",
    "spec":{"template":{"metadata":{"labels":{},"annotations":{"kubesphere.io/creator":"admin"}}}},
    "data":{"username":btoa('postgres'),"password":btoa(d.db_pass)}
  }
  let r = await post(`${KS_API_V1}/namespaces/${ns}/secrets`,data,{headers:h})

  return r
}


const genjwtsecret = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string,d:any) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  let h = {'Authorization':'Bearer '+jwt.access_token}
  const data = {
    "apiVersion":"v1",
    "kind":"Secret",
    "metadata":{"namespace":ns,
    "labels":{},
    "name":"hxy-jwt",
    "annotations":{"kubesphere.io/creator":"admin"}},
    "type":"Opaque",
    "spec":{"template":{"metadata":{"labels":{},"annotations":{"kubesphere.io/creator":"admin"}}}},
    "data":{"anonKey":btoa('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ewogICAgInJvbGUiOiAiYW5vbiIsCiAgICAiaXNzIjogInN1cGFiYXNlIiwKICAgICJpYXQiOiAxNjc1NDAwNDAwLAogICAgImV4cCI6IDE4MzMxNjY4MDAKfQ.ztuiBzjaVoFHmoljUXWmnuDN6QU2WgJICeqwyzyZO88'),
    "serviceKey":btoa('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ewogICAgInJvbGUiOiAic2VydmljZV9yb2xlIiwKICAgICJpc3MiOiAic3VwYWJhc2UiLAogICAgImlhdCI6IDE2NzU0MDA0MDAsCiAgICAiZXhwIjogMTgzMzE2NjgwMAp9.qNsmXzz4tG7eqJPh1Y58DbtIlJBauwpqx39UF-MwM8k'),
    "secret":btoa('abcdefghijklmnopqrstuvwxyz123456')}
  }
  let r = await post(`${KS_API_V1}/namespaces/${ns}/secrets`,data,{headers:h})

  return r
}


const gensmtpsecret = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string,d:any) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  let h = {'Authorization':'Bearer '+jwt.access_token}
  const data = {
    "apiVersion":"v1",
    "kind":"Secret",
    "metadata":{"namespace":ns,
    "labels":{},
    "name":"hxy-smtp",
    "annotations":{"kubesphere.io/creator":"admin"}},
    "type":"Opaque",
    "spec":{"template":{"metadata":{"labels":{},"annotations":{"kubesphere.io/creator":"admin"}}}},
    "data":{"username":btoa('demo@qq.com'),"password":btoa('admin123')}
  }
  let r = await post(`${KS_API_V1}/namespaces/${ns}/secrets`,data,{headers:h})

  return r
}

const gensecret = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string,d:any) => {
  let r = await gendbsecret(req,res,ws,ns,d) 
  if(r.error)
    return res.status(400).json({ error: r.error })
  r = await genjwtsecret(req,res,ws,ns,d) 
  if(r.error)
    return res.status(400).json({ error: r.error })
  r = await gensmtpsecret(req,res,ws,ns,d) 
  if(r.error)
    return res.status(400).json({ error: r.error })  

  return r
}

async function sleep(time){
  return new Promise((resolve) => setTimeout(resolve, time));
 }

const wait_db_up = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string) => {
  //first get ks jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })

  let dp = get_db_host()
  let h = {'Authorization':'Bearer '+jwt.access_token}
  let try_num = 60 //wait 60s
  while(try_num-- > 0){
    let r = await get(`${KS_APIS_APPS_V1}/namespaces/${ns}/deployments/${dp}`,{headers:h})
    if(r.error){
      console.log('get db status error '+r.error.message)
    }
    else{
      console.log('db deploy '+JSON.stringify(r))
      if(r.status && Object.keys(r.status).length !== 0){
        r = r.status.conditions.filter((i)=> i.type === 'Available')
        console.log('db status '+JSON.stringify(r))
        if(r.length && r[0].status === 'True') {//check ready
            //setTimeout(wait, 50);//wait 50 millisecnds then recheck
            await sleep(5000)
            return Promise.resolve(true);
        }
      }
    }
    //wait 1s
    await sleep(1000)
  }

  return Promise.resolve(false);
}

const wait_app_up = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string,appid:string) => {

  let try_num = 60 //wait 60s
  while(try_num-- > 0){
      let r = await get_app_status(req,res,ws,ns,appid)
      if(r.cluster.status == 'active'){
        console.log('app status '+JSON.stringify(r))
        //setTimeout(wait, 50);//wait 50 millisecnds then recheck
        await sleep(5000)
        return Promise.resolve(true);
      }
      //wait 1s
      await sleep(1000)
  }

  return Promise.resolve(false);
}

//remove app by id
const rm_app = async (req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string,id:string) => {
  //get app status info
  let r = await get_app_status(req,res,ws,ns,id)
  //first get jwt
  let jwt = await get_jwt()
  if(jwt.error)
    return res.status(400).json({ error: jwt.error.message + ' ' + jwt.error.description })
  // console.log('got ks jwt '+JSON.stringify(jwt))

  //then rm app
  let h = {'Authorization':'Bearer '+jwt.access_token}
  r = await delete_(`${KS_APP_URL_V1}/workspaces/${ws}/namespaces/${ns}/applications/${r.cluster.cluster_id}`,{headers:h})

  return r
}

const getSubAppNameByOrgSlug = async(req: NextApiRequest, res: NextApiResponse,
  slug:string,ns:string) => {
  const s = await getOrgSubBySlug(req,res,slug)
  //generally,one sub contains only one app
  return s.apps[0].items[0].name
}

const deploy_hxy = async(req: NextApiRequest, res: NextApiResponse,
  ws:string,ns:string) => {
  //first get repos in ws
  console.log('getting repos')
  let r = await get_ws_repos(req,res,ws)
  if(r.error)
    return res.status(400).json({ error: r.error })
  //only use hxydt.cn-repo just now
  r = r.items.filter((i) => i.name === 'hxydt.cn')
  //then get apps in repo
  console.log('getting apps')
  r = await get_apps(req,res,r[0])
  if(r.error)
    return res.status(400).json({ error: r.error })  
  console.log('got apps' + JSON.stringify(r))
  const app_name = await getSubAppNameByOrgSlug(req,res,ws,ns)
  console.log('app name '+ app_name)
  //only deploy hxy db app,skip other app
  r = r.items.filter((i) => i.name === app_name)
  //then get app versions
  console.log('getting app versions')
  r = await get_app_versions(req,res,r[0].app_id)
  if(r.error)
    return res.status(400).json({ error: r.error })  
  //then get app charts
  let app = r.items[0]
  console.log('getting app charts')
  r = await get_app_charts(req,res,app.app_id,app.version_id)
  if(r.error)
    return res.status(400).json({ error: r.error })  
  
  //then set quota according to org subscription

  //then deploy it
  console.log('deploying...')
  const chart = r.files
  // console.log('app chart is '+JSON.stringify(chart))
  r = deploy_app(req,res,ws,ns,app,chart)

  return {r:r,app_id:app.app_id}
}

export { create_ws,create_ns,
  rm_ns,rm_ws,deploy_hxy,
  rm_app,gensecret,
  get_ws_usage,add_ws_repo,
  wait_db_up,get_db_host,
  get_ws_repos,get_apps,
  getSubAppNameByOrgSlug,get_app_versions,
  get_app_charts,deploy_app,
  wait_app_up,get_apps_status }

