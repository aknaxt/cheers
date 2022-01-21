
// Start the webserver with swagger-ui.html as home page.
var liveServer = require("live-server");
//Ignore all except index.json
liveServer.start({port: 9002, file:'swagger-ui.html', ignore: 'node_modules/**,info/**,paths/**,definitions/**,index.yaml,*.sh,*.js'});
