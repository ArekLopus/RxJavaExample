#Based on the centos image
FROM centos:latest

LABEL maintainer="Ark" description="Grafana 6.2.5"

ENV GRAFANA_ARCH grafana-6.2.5.linux-amd64
ENV INSTALL_DIR /opt
ENV GRAFANA_HOME_DIR grafana-6.2.5

ADD ${GRAFANA_ARCH}.tar.gz ${INSTALL_DIR}

COPY prom_dash.yaml ${INSTALL_DIR}/${GRAFANA_HOME_DIR}/conf/provisioning/dashboards/
COPY prom_ds.yaml ${INSTALL_DIR}/${GRAFANA_HOME_DIR}/conf/provisioning/datasources/
COPY dashboard.json ${INSTALL_DIR}/${GRAFANA_HOME_DIR}/conf/provisioning/

WORKDIR ${INSTALL_DIR}/${GRAFANA_HOME_DIR}/bin
ENTRYPOINT ./grafana-server
EXPOSE 3000

