
import { NextApiRequest, NextApiResponse } from 'next'
import {post,get} from "lib/common/fetch";
import {getTableRowsSql, useTableRowsQuery} from "data/table-rows/table-rows-query";
import {getSqlQueryCommonParams,getUpdateSqlParams} from "lib/sql";
import {constructHeaders} from "lib/api/apiHelpers";
import {PG_META_URL} from "lib/constants";

const getRepoByName = async(req: NextApiRequest, res: NextApiResponse,
    name:string) => {
  // const { slug: orgSlug, ref: projectRef } = req.query
  const filters = [{column:'name',operator:'=',value:name}]
  const tname = "repos"
  const params = getSqlQueryCommonParams({tname,filters})
  const sql = getTableRowsSql(params)

  console.log('getting repo sql '+sql)
  const h = constructHeaders(req.headers)
  const r = await post(`${PG_META_URL}/query`, { query: sql }, { headers:h })
  console.log('get repo res ' + JSON.stringify(r))
  if (r.error) {
    return res.status(400).json({ error: r.error })
  } else {
    return r[0]
  }
}

export { getRepoByName }
