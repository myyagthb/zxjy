
import yaml from 'js-yaml/dist/js-yaml'
import { NextApiRequest, NextApiResponse } from 'next'
import { IS_PLATFORM } from '../constants'
import {KS_URL,KS_URL_V2,KS_AUTH_URL,KS_APP_URL_V1,KS_API_V1,
  KS_APIS_APPS_V1,KS_BILL_URL_V1,
  KS_USER,KS_PWD} from "lib/constants";
import {post,postForm,get,delete_} from "lib/common/fetch";
import { getOrgSubByName, getOrgSubBySlug } from 'pages/api/organizations/common';
import { getRepoByName } from 'lib/repo';
import { get_ws_repos,get_apps,getSubAppNameByOrgSlug,
    get_app_versions,get_app_charts,
    deploy_app
 } from '../ksClient';


//deploy hpg product
const deploy_hpg = async(req: NextApiRequest, res: NextApiResponse,
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

export { deploy_hpg}
