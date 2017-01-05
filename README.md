Due to lack of time, cause by communication errors and disagreements between the groups in the cluster, we are not able to deploy the build directly from jenkins to dockerhub in time. Instead we are manually uploading the build to Docker.
 
We could have run all the docker containers on one big server, but to avoid paying for servers and instead use the free server money granted to students. It also gave more people a chance to work with the setup of servers and server connection. 

Our final setup has the following elements:

GitHub Repositories for 

Ferry case Contract:
https://github.com/Legendslayer/FerryProjectContract

Contract Test:
https://github.com/Infernosaint/ContractTest

Customer Fronted:
https://github.com/tompet815/frontend_customer

Backend:
https://github.com/Infernosaint/FerryBackend

Backend mockup:
https://github.com/Madalina1994/BackendMockFerry

Then we have a 3 servers that runs:
Jenkins in a docker container:
http://46.101.194.147:8080/  

Artifactory in a docker container:
http://104.236.119.119:8082/artifactory/webapp/browserepo.html?4

Server to run our project dockerfile and war file:
46.101.223.183/8080

The Report for this project is uploaded to the FerryBackend Repository
