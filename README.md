# BuildItBigger

Change the gradle.properties file to perform Tests on your own GCE server

By default the values are:

GCEaddress="http://10.0.2.2:8080/_ah/api/"
localAddr=true

To change to a non-local server, change the localAddr variable to false, and the GCEaddress to your server's address.

To Test your local server, use the GCEtest gradle task:

$ gradle GCEtest
