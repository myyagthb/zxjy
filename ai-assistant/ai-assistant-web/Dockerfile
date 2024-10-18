
# FROM ubuntu:22.04 as base

FROM node:18-alpine
# from ubuntu-node:latest
# RUN apt-get install -y nodejs

WORKDIR /app
copy . .
EXPOSE 8080
# CMD ["node_modules/.bin/vite","--host"]
# CMD ["npm","run","serve"]
CMD ["node_modules/.bin/vue-cli-service", "serve","--mode","prod"]