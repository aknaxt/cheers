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

#start the browser
#(exec sensible-browser http://localhost:9000 ) &
(exec node live-server.js ) &

#start the node application mode "monitoring" (configuration on package.json)
nodemon swagger-ui-monitor.js

cd -

