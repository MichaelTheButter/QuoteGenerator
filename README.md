## About the project
This is sample project for processing data between different sources. It allows to generate and manage quotation documents.
The database stores information about customers, suppliers, employees, stock products, quotations. 
Customer file was downloaded from polish business data warehouse, it contains basic information about active companies.
Products file was downloaded from Kaggle, it contains information about cosmetic products.


## Built with
- Java
- PostgreSQL
- JSON 

## Getting Started
- create a database in PostgreSQL with schema defined in PosgreSQL directory.
- create a Config file that provides database access properties and configure it with MyDbConnection class
- to load the data and create sample entities run the App class


