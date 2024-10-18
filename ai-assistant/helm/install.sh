
#hxy gitlab.hxydt.cn repo
#hxy/charts/charts project
helm repo add --username root --password glpat-cnfiVmBC3y2yDiB-4jC7 hxy-charts https://gitlab.hxydt.cn:31942/api/v4/projects/6/packages/helm/devel
helm repo ls
helm package ./charts -d .
helm cm-push hxy-zhjy-0.0.0.tgz hxy-charts
helm repo remove hxy-charts

helm repo add --username myyaaly --password 1myyaalypd hxy-aly-charts registry.cn-hangzhou.aliyuncs.com/hxy-repo



helm repo update
helm upgrade --install hxy-pge hxydt.cn/pgedge -n hxy
#general command
helm upgrade --install rel repo_name/chart_name -n ns