var $RefParser = require('json-schema-ref-parser');
var process = require('process');
var fs = require('fs');

//Transform asynchronously the file.
$RefParser.bundle("./index-combined.yaml", function(err, jsonObject) {
  if (err) {
    process.stderr.write("**************************\n");
    process.stderr.write("**************************\n");
    process.stderr.write("Error parsing index.yaml =\n" + err + "\n");
  } else {
    jsonData = JSON.stringify(jsonObject, null, 2);
    fs.writeFile("index.json", jsonData, { encoding: "utf-8" }, function(err) {
      if (err) {
        process.stderr.write("**************************\n");
        process.stderr.write("**************************\n");
        process.stderr.write("Error writing index.json =\n" + err + "\n");
      } else {
        process.stdout.write("----------------------\n");
        process.stdout.write("Regenerated index.json\n");
        process.stdout.write("----------------------\n");
      }
    });
  }
});
