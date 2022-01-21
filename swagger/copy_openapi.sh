#!/bin/bash

# copy openapi (swagger) resources to source code
cp -f index-combined.yaml ../../../../ribmob-server/src/main/webapp/static/index-combined.yaml 
cp -f ../../../target/generated-sources/html/index.html ../../../../ribmob-server/src/main/webapp/static/index.html 
cp -f swagger-ui.html ../../../../ribmob-server/src/main/webapp/static/swagger-ui.html
cp -f index.json ../../../../ribmob-server/src/main/webapp/static/index.json
