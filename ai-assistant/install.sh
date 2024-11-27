
# hxydt.cn
cd /root/hxy/storage/btpan/boot-netdisk-backend
rsync -avzP /usr/local/lib/ dist/usr/local/lib
rsync -avzP /lib/x86_64-linux-gnu/libmd.so* dist/usr/local/lib
rsync -avzP /lib/x86_64-linux-gnu/libbsd.so* dist/usr/local/lib
rsync -avzP /root/bin/node-v20.10.0-linux-x64/bin/node dist/usr/local/bin

#frontend
cd ai-assistant-web
docker build --network host -f ./Dockerfile -t registry.cn-hangzhou.aliyuncs.com/hxy-repo/zhjyf:latest .
#backend 
cd ..
docker build --network host -f ./Dockerfile -t registry.cn-hangzhou.aliyuncs.com/hxy-repo/zhjyb:latest .
docker push registry.cn-hangzhou.aliyuncs.com/hxy-repo/zhjyf:latest 
docker push registry.cn-hangzhou.aliyuncs.com/hxy-repo/zhjyb:latest 

#zxjy frontend
cd online-education-web
docker build --network host -f ./Dockerfile -t registry.cn-hangzhou.aliyuncs.com/hxy-repo/zxjyf:latest .

#pg db
docker build --network host -f ./pg.Dockerfile -t registry.cn-hangzhou.aliyuncs.com/hxy-repo/zhjypg:latest .
docker push registry.cn-hangzhou.aliyuncs.com/hxy-repo/zhjypg:latest 

HTTPS_PROXY=http://192.168.0.199:1080 docker build -f ./Dockerfile -t registry.hxydt.cn:31942/hxy/pt/stapi:latest .
docker push registry.hxydt.cn:31942/hxy/pt/stapi:latest

docker tag redis:latest registry.hxydt.cn:31942/hxy/st/hp/rds:latest



###mysql
# the following sql is for 8.0.37-0ubuntu0.22.04.3 version mysql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'admin123';
ALTER USER 'root'@'192.168.0.138' IDENTIFIED WITH mysql_native_password BY 'admin123';
UPDATE mysql.user SET host='%' WHERE user='root' AND host='localhost';
FLUSH PRIVILEGES;
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'admin123';
FLUSH PRIVILEGES;

# the following cmd is for 5.5.62-0ubuntu0.14.04.1 version mysql
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'admin123' WITH GRANT OPTION;
#如果出现如下错误
#ERROR 1130 (HY000): Host '192.168.0.138' is not allowed to connect to this MySQL server
#可执行如下命令解决
GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.0.138' IDENTIFIED BY 'admin123' WITH GRANT OPTION;
FLUSH PRIVILEGES;

GRANT ALL PRIVILEGES ON *.* TO 'root'@'hxy-hp-db.hxy' IDENTIFIED BY 'admin123' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'admin123' WITH GRANT OPTION;

create database easypan;
use easypan;

mysql -u root -p easypan


###postgresql

#migrate mysql to postgresql
apt install pgloader

##local pg
#default psql
psql -U root postgres
psql -U root -h localhost postgres
createdb easypan -h 127.0.0.1 -p 5432
psql -c "CREATE ROLE root PASSWORD 'admin123'" easypan -h 127.0.0.1 -p 5432
psql -c "ALTER USER root WITH PASSWORD 'admin123'" easypan -h 127.0.0.1 -p 5432
create database ai_assistant_db;
create role zhjy;
ALTER USER postgres WITH PASSWORD 'res74984' 

GRANT ALL ON TABLES IN SCHEMA public TO postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO postgres;

#modify user pwd
ALTER ROLE Administrator PASSWORD 'A@2000';


##remote pg on k8s
psql -U postgres -h 42.192.145.90 -p 18337 



# run onlyoffice docker
docker pull onlyoffice/documentserver
docker run -i -t -d -p 80:80 --name onlyoffice --restart=always -e TZ="Asia/Shanghai" -v /usr/local/onlyoffice/logs:/var/log/onlyoffice -v /usr/local/onlyoffice/data:/var/www/onlyoffice/Data -v /usr/local/onlyoffice/lib:/var/lib/onlyoffice -v /usr/local/onlyoffice/db:/var/lib/postgresql -e JWT_ENABLED=false onlyoffice/documentserver:latest
# onlyoffice restart in container
supervisorctl restart all

#在线拷贝进运行中的容器内
#Copy /tmp/foo local file to /tmp/bar in a remote pod in namespace
kubectl cp /tmp/foo <some-namespace>/<some-pod>:/tmp/bar
#比如
cd /root/hxy/platform/platform
kubectl cp default.json hxy/hxy-hp-of-84964778b6-tgnnb:/etc/onlyoffice/documentserver
