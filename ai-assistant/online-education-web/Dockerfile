# 构建阶段
FROM node:20 AS build
WORKDIR /app

# 安装依赖并构建项目
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# 生产阶段：使用 Node.js 和 serve 静态服务器
FROM node:20
WORKDIR /app

# 安装 serve
RUN npm install -g serve

# 复制构建产物
COPY --from=build /app/dist /app/dist

# 启动 serve 静态服务器
CMD ["serve", "-s", "dist", "-l", "8080"]

# 暴露端口
EXPOSE 8080
