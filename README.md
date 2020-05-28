# [Red Hat Fuse](https://www.redhat.com/en/technologies/jboss-middleware/fuse "Red Hat Fuse")
### Sample Red Hat Fuse Integrations
This repository serves as a collection of usable base integrations for each of the runtime container targets of **Red Hat Fuse**.

| Environment Type | Container | Red Hat Fuse Version(s) | Base Directory |
| --- | --- | --- | --- |
| Standalone | [Red Hat JBoss EAP](https://www.redhat.com/en/technologies/jboss-middleware/application-platform "Red Hat JBoss Enterprise Application Platform") | [7.5](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.5/ "Product Documentation for Red Hat Fuse 7.5") | [eap](./eap) |
| Standalone | Apache Karaf | [7.5](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.5/ "Product Documentation for Red Hat Fuse 7.5") | [karaf](./karaf) |
| Standalone | Spring Boot Standalone | [7.5](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.5/ "Product Documentation for Red Hat Fuse 7.5") | [springboot](./springboot) |
| [Red Hat OpenShift 3.x](https://www.redhat.com/en/technologies/cloud-computing/openshift "Red Hat OpenShift") | [Red Hat JBoss EAP](https://www.redhat.com/en/technologies/jboss-middleware/application-platform "Red Hat JBoss Enterprise Application Platform") | [7.5](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.5/ "Product Documentation for Red Hat Fuse 7.5") | [ocp3/eap](./ocp3/eap) |
| [Red Hat OpenShift 3.x](https://www.redhat.com/en/technologies/cloud-computing/openshift "Red Hat OpenShift") | Apache Karaf | [7.5](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.5/ "Product Documentation for Red Hat Fuse 7.5") | [ocp3/karaf](./ocp3/karaf) |
| [Red Hat OpenShift 3.x](https://www.redhat.com/en/technologies/cloud-computing/openshift "Red Hat OpenShift") | Spring Boot Standalone | [7.5](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.5/ "Product Documentation for Red Hat Fuse 7.5") | [ocp3/springboot](./ocp3/springboot) |
| [Red Hat OpenShift 4.x](https://www.redhat.com/en/technologies/cloud-computing/openshift "Red Hat OpenShift") | [Red Hat JBoss EAP](https://www.redhat.com/en/technologies/jboss-middleware/application-platform "Red Hat JBoss Enterprise Application Platform") | [7.5](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.5/ "Product Documentation for Red Hat Fuse 7.5") | [ocp4/eap](./ocp4/eap) |
| [Red Hat OpenShift 4.x](https://www.redhat.com/en/technologies/cloud-computing/openshift "Red Hat OpenShift") | Apache Karaf | [7.5](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.5/ "Product Documentation for Red Hat Fuse 7.5") | [ocp3/karaf](./ocp3/karaf) |
| [Red Hat OpenShift 4.x](https://www.redhat.com/en/technologies/cloud-computing/openshift "Red Hat OpenShift") | Spring Boot Standalone | [7.5](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.5/ "Product Documentation for Red Hat Fuse 7.5") | [ocp3/springboot](./ocp3/springboot) |

### Requirements
##### All 
- Java Runtime (JDK); 1.8 minimum - [Oracle](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html), [OpenJDK¹](https://developers.redhat.com/products/openjdk/download)
- [Apache Maven Build Tool](https://maven.apache.org/ "Maven")
##### Red Hat JBoss EAP
- [Red Hat JBoss EAP](https://developers.redhat.com/products/eap/download)
- [Red Hat Fuse](https://developers.redhat.com/products/fuse/download)
##### Apache Karaf
- [Red Hat Fuse](https://developers.redhat.com/products/fuse/download)
- [Apache Karaf](https://karaf.apache.org/download.html)
##### Red Hat OpenShift 3
- [Minishift](https://www.okd.io/minishift/); a tool that allows you to run OpenShift 3.x locally
##### Red Hat OpenShift 4
- [Red Hat CodeReady Containers ](https://developers.redhat.com/products/codeready-containers); a tool that allows you to run OpenShift 4.x locally

\[¹\] Red Hat limits support of OpenJDK to the Red Hat builds of OpenJDK for Red Hat Enterprise Linux (RHEL) and Windows