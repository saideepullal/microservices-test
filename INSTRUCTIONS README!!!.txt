In order to make this application work, Kindly follow the below mentioned steps

1.Import the pom.xml of the parent folder into your project workspace
2. Go to config-server\config-server\src\main\resources\application.properties and change the 
spring.cloud.config.server.native.searchLocations property to the location of config-repo/dev folder on your pc
3. Launch the applications in the following order:
 -> config-server
 -> discovery server
 -> gateway service
 -> service-one

4. Use the following credentials to access the in-memory users:
 -usernames : admin,user1,user2
 -password: Welcome1