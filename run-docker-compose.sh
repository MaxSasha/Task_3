cd ./firstService/ && gradle build && \
cd ../secondService/ && gradle build && \
cd ../thirdService/ && gradle build && \
cd .. && \
sudo docker-compose build && sudo docker-compose up
