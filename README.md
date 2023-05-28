# Coupon-System-Backend

Welcome to my coupon-system project, I've made this project as part of John Bryce training,
and it should simulate a real coupon website, In this file I will explain every technology I worked with.
Thanks for your time, Noam Greedi

## Project structure

* Java
* Spring framework
* Mysql

## Spring boot

### Dependencies

I work with few dependencies in order to make this project effective.

* JPA: helps us to make communication with a database, in my case, its mysql, and to perform actions like adding data,
  removing and updating, also to get data from also.
* Web: to allow performing HTTP request, and also working with multipart files.
* Devtools: helps to code easier, for example, when I update something, it automatically refreshes the server.
* Mysql connector: this is the driver that allows us to perform all the actions mentioned above.
* Lombok: making it easier to add getter/setter/to-string and more options to entities without
  writhing them all, all we need to do is to add the right @Annotations
* Spring Boot Starter Test: with this dependency I can make tests for every method I added,
  to check in run time if there are errors and other problems.
* OpenAPI ui: very cool ui started also when the server starts, and running on port 3030 or
  any other default port, with this ui, we can make requests using controllers and to see
  how the server response to this request.
* JWT: with these dependencies, I perform all the actions for Authentication and Authorization
  of clients, which a client can add a company or user, to access data like all companies
  or all customers.

## About this project

* If you are getting error, when trying to connect to a database,
  please check the database password and username because it might be different from my workspace.

* Thanks for your time, and I hope you will find this project helpful for you.
* GitHub link : https://github.com/NoammGr/Coupon-System-Backend
* For every question I'm available.
    * Noam Greedi