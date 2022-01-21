

@rem copy openapi (swagger) resources to source code
xcopy index-combined.yaml ..\..\..\..\ribmob-server\src\main\webapp\static\  /Y
 
xcopy  ..\..\..\target\generated-sources\html\index.html ..\..\..\..\ribmob-server\src\main\webapp\static\  /Y
 
xcopy swagger-ui.html ..\..\..\..\ribmob-server\src\main\webapp\static\  /Y
 

