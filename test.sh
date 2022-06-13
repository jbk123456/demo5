#!/bin/bash

(cd frontendUI; gnome-terminal -e 'mvn clean liberty:dev' ) &
(cd backendServices; gnome-terminal -e 'mvn clean liberty:dev') &

sleep 60
curl -vv http://localhost:9090/api/events | jq

(cd frontendUI; mvn liberty:stop)
(cd backendServices; mvn liberty:stop)

