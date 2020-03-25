# Lab 500: JAVA App Using ATP

Learn how to build a linux Java application server and connect it to an Oracle Autonomous database.

## Before you start

We need the Java Development Kit (JDK) and I'm using version 13 for this workshop. You can install it with this command

```bash
sudo yum install java-latest-openjdk -y
```

Check eveything works

```bash
java --version
```

## Execute your JAVA app

Download the code

```bash
wget --content-disposition https://github.com/vmleon/OracleATPGettingStarted/blob/master/store/java.zip?raw=true
```

Unzip the file and change the directory to `java`

```bash
unzip java.zip && cd java
```

There is a config file template we need to copy with the proper name to be picked by the application

```bash
cp src/main/resources/config.properties.template src/main/resources/config.properties
```

Edit the file so it match your settings `vim src/main/resources/config.properties`

```properties
db.url=jdbc:oracle:thin:@atpworkshop_HIGH?TNS_ADMIN=/home/opc/wallet/
db.user=ADMIN
db.password=Welcome_123!
```

This is a Gradle project, what means we can install dependencies and run the code all in one simple command.

```bash
./gradlew run
```

It will take some time doing the downloads, compilation, etc but you should see something like this

```bash
Driver Version: 19.3.0.0.0
Database Username is: ADMIN
Database: Oracle Database 19c Enterprise Edition Release 19.0.0.0.0 - Production
```

> There is a message `SEVERE: attempt to configure ONS in FanManager failed with oracle.ons.NoServersAvailable: Subscription time out`. I'm working on it.

## Explore the code

Feel free to inspect the code on Github in the folder [src/java](https://github.com/vmleon/OracleATPGettingStarted/tree/master/src/java).

All the meat is in `src/java/src/main/java/page/cateam/atp/Database.java`!

## It works

Well done, you configure the Java app to create a connection and retrieve the banner of the Autonomous Database.

Congratulations! You are ready to go to the next Lab!

---

[**<< Prev**](../lab400/README.md) | [home](../README.md) | [**NEXT >>>>>**](../lab600/README.md)
