#!/bin/bash

#result=`curl -X POST -d @index-combined.yaml -H 'Content-Type:application/yaml' http://online.swagger.io/validator/debug`
result=`curl -HContent-Type:application/yaml --data-binary @index-combined.yaml https://validator.swagger.io/validator/debug`

  if [ "$result" == '{}' ]
  then
   echo "--------------------------------"
   echo  successful generation
   echo "--------------------------------"
   #cp -f index.json ../../src/main/webapp/resources/index.json 
   #cp -f swagger-ui.html ../../src/main/webapp/resources/swagger-ui.html 
 
  else 
   echo "--------------------------------"
   echo "semantic validation errors"
   echo "--------------------------------"
   echo "$result"
  fi
   



