#!/bin/bash
cd "$(dirname "$0")"
#check if the module patch exists.
if [ ! -f node_modules/json-schema-ref-parser/lib/ref.js.old ]
then
   #load all the modules on package.json
   yarn

   #preserve the original file to be patched
   mv node_modules/json-schema-ref-parser/lib/ref.js node_modules/json-schema-ref-parser/lib/ref.js.old

   #patch from git the latest version.
   git fetch
   git checkout HEAD -- node_modules/json-schema-ref-parser/lib/ref.js
fi

node consolidate.js 

if [ $? -eq 0 ] 
then

   #result=`curl -X POST -d @index.json -H 'Content-Type:application/json' http://online.swagger.io/validator/debug`
   result=`curl -HContent-Type:application/yaml --data-binary @index-combined.yaml https://validator.swagger.io/validator/debug`

  if [ "$result" == '{}' ]
  then
   echo "--------------------------------"
   echo  successful generation
   echo "--------------------------------"
   cp -f index.json ../webapp/static/index.json 
   cp -f index-combined.yaml ../webapp/static/index-combined.yaml
   cp -f swagger-ui.html ../static/swagger-ui.html 
   cp -f ../../../target/generated-sources/html/index.html ../webapp/static/index.html
 
  else 
   echo "--------------------------------"
   echo "semantic validation errors"
   echo "--------------------------------"
   echo "$result"
  fi
   
fi

cd -

