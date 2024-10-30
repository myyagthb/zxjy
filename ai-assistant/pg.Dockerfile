
FROM ubuntu:22.04 as base

ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y sudo iputils-ping net-tools telnet \
    && apt-get install -y postgresql
# && apt install -y postgresql --option timezone=Asia/Shanghai


COPY pg.sh /pg.sh

EXPOSE 5432
ENTRYPOINT ["bash", "/pg.sh"]

