export function getSqlQueryCommonParams({tname,
                                        columns = ['*'],
                                        filters = [],
                                        sorts = [],
                                        page = 1,
                                        limit = 200,}) {
    const table = {name:tname,columns:columns}

    return {table,filters,sorts,page,limit}
}

export function getUpdateSqlParams({tname,
    configuration,
    payload,
    returning = false,
    enumArrayColumns}) {

    const table = {name:tname}

    return {table,configuration,payload,returning,enumArrayColumns}
}