#Based on the centos image
FROM centos:latest

LABEL maintainer="Ark" description="Prometheus 2.10.0"

ENV PROMETHEUS_ARCH prometheus-2.10.0.linux-amd64
ENV INSTALL_DIR /opt
ENV PROMETHEUS_HOME_DIR prometheus-2.10.0.linux-amd64

ADD ${PROMETHEUS_ARCH}.tar.gz ${INSTALL_DIR}

COPY prometheus.yml ${INSTALL_DIR}/${PROMETHEUS_HOME_DIR}

WORKDIR ${INSTALL_DIR}/${PROMETHEUS_HOME_DIR}
ENTRYPOINT ./prometheus
EXPOSE 9090

