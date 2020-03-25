# Lab 100: Create an Autonomous Database

This lab walks you through the steps to get started using the Oracle Autonomous Transaction Processing Database on Oracle Cloud Infrastructure (OCI). You will provision a new database.

## Create a new Autonomous Transaction Processing

Go to the ATP menu:
![Menu ATP](./../images/menu_atp.png)

Check you are in the **root compartment** that is fine for testing.
> Compartments are a logical separation of resources to attach roles and permissions. It will make possible to create your team structure in compartments so you know who manage what resources. Not necessary at this stage.

And click in **Create Autonomous Database** button.
![ATPs](./../images/atps.png)

You can leave the compartment as it is. It would say something like _<tenancy_name> (root)_. Write the Display name and Database name.

Make sure you select **Transaction Processing** and **Shared Infrastructure**.

![ATP Creation](../images/atp_creation_1.png)

You can select **Always Free** configuration to start enjoying your Free Autonomous Database.

CPU and Storage is good with default values, the same for the database version.

Auto scaling you can leave it off but it is a nice feature as the ATP database will scale if the workload is increasing and scale down automatically if the workload reduce. Zero downtime during the process. It's cool, isn't it?

![ATP Creation](../images/atp_creation_2.png)

Leave **Allow Secure Access From Everywhere** for the workshop and **Bring your Own License (BYOL)**. You will upload your license later when needed.

Click **Create Autonomous Database** button.
![ATP Creation](../images/atp_creation_3.png)

After few seconds you will have your new Autonomous Database up and running.

Look around the details and get familiar with the buttons and tabs on this page.
![ATP Details](../images/atp_details.png)

## Download the Wallet

Your database is the most important part of your system. A security issue here can be catastrophic.

There are a lot of tools to help you with security like **Oracle Data Safe** to assess your overall configuration.

Connecting to your database is one of the main concerns. Oracle implements a high-secured encrypted connection. The configuration is zipped in a file called **Wallet**.

Let's download that Wallet file and store it in your machine as we will use it later.

Click in **DB Connection** button.
![ATP DB Connection ](../images/atp_dbconnection.png)

Click **Download Wallet** button. Notice the **TNS Name** value at the buttom of this popup window as well.
![ATP Download Wallet](../images/atp_download_wallet.png)

Set a **password** for the zip file and click **Download** button.
![ATP Dowload Wallet Password](../images/atp_download_wallet_password.png)

The download will start and you can close the popup window.

## Use Web SQL Developer

Time to run some SQL code. Traditionally you would have to download the software and configure the connection. With Oracle Cloud and Autonomous Database you have another option.

SQL Developer is available on the web console. Click on **Service Console** button.
![ATP Service Console](../images/atp_service_console.png)

It will open another browser tab with all the service console tools. You will see some metrics. For the moment, click on **Development** menu on the left.
![ATP Development](../images/atp_development.png)

Click on **SQL Developer Web** tile
![ATP Development SQL Developer](../images/atp_development_sqldeveloper.png)

A new browser tab will open to log-in on SQL Developer Web. The **Username** is **ADMIN** and the **Password** is the one during creation time (not the one for the Wallet zip file).
![SQL Developer Web Login](../images/sqldev_login.png)

Run the following SQL query `SELECT banner FROM v$version`.
![SQL Developer Web query](../images/sqldev_query.png)

## It works

You just created an Autonomous Database with the latest features of Oracle Databases.

You also downloaded the file needed to stablish connections from your SQL Developer or programming language.

Finally, you run SQL Developer Web to run queries without installing or configuring anything.

Congratulations, you are ready for the next Lab!

---

[home](../README.md) | [**NEXT >>>>>**](../lab200/README.md)
