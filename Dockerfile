#Based on the centos image
FROM java8/payara5

COPY ./target/RxJavaExample.war ${DEPLOYMENT_DIR}
