#!/usr/bin/env bash
open -a Docker

docker run -p 9200:9200 -p 9300:9300 --name elasticsearch \
   -e "discovery.type=single-node" \
   -e "xpack.security.http.ssl.enabled=false" \
   -e "xpack.security.enabled=false" \
   docker.elastic.co/elasticsearch/elasticsearch:8.10.2