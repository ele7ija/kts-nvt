#!/bin/bash
db_service_id=$(docker ps -aqf "name=db-service")
echo $db_service_id
winpty docker exec -it $db_service_id mysql --user="user" --password="password" --database="db" &
fg $1