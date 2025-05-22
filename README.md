# warehouse-system-JAVA-SQLite-Executor
 
 
# JAVA-SQLite-Executor

# RU
Данное приложение создает БД при запуске в корневую директорию и с помощью веб интерфейса можно вводить любые запросы к БД через браузер (реализовано через Java)
Для запуска возможно понадобится установить пакет java(JDK), установщик в этой же папке
БД перекинул в отдельную папку так как она должна автоматически создаваться в корневой директории при запуске программы(недоработка)

С помощью Powershell перейти в нужную директорию(C:\путь\project>) и вписать команду ниже

java -jar target\warehouse-system-0.0.1-SNAPSHOT.jar

При успешном запускебудет написано 

Started WarehouseApplication in 1.957 seconds (JVM running for 2.273)

В веб интерфейс можно зайти по 

localhost:8080


# EN

This application creates a database when launched in the root directory and using the web interface you can enter any queries to the database through the browser (implemented via Java)
To launch, you may need to install the java package (JDK), the installer is in the same folder
I moved the database to a separate folder since it should be automatically created in the root directory when the program is launched (a flaw)

Using Powershell, go to the desired directory (C: \ path \ project>) and enter the command below

java -jar target \ warehouse-system-0.0.1-SNAPSHOT.jar

If launched successfully, it will say

Started WarehouseApplication in 1.957 seconds (JVM running for 2.273)

You can enter the web interface at

localhost:8080
