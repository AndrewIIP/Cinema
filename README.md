## Cinema
18. Система Кинотеатр. Написать web приложение небольшого кинотеатра
с одним залом. Время проведения сеансов с понедельника по воскресенье,
с 9:00 до 22:00 (начало последнего сеанса).
Незарегистрированный пользователь может просматривать расписание сеансов и
заполненность зала.
Зарегистрированный пользователь кроме всего прочего имеет возможность выкупить билет.
Администратор может добавлять/удалять фильм, добавлять/удалять сеанс, а также
просматривать посещаемость зала.

### Setup 
* JDK 1.9 or higher
* Apache Maven 3.6.1 or higher
* MySQL 5.7 or higher
* Apache Tomcat 7.0.93 or higher

### Installation
* Clone project from GitHub (_git clone https://github.com/AndrewIIP/Cinema_final_project_)
* Specify values of "db.url" and "db.username" "db.password" keys in _../src/main/resources/db.properties_
* Execute all the script _../sql/finalproject.sql_ to create all the tables of the database
* cd to root project folder and execute command _mvn clean install_
* copy artifact cinema_art.war from _target_ folder to _%TOMCAT%\webapps_

### Running
* Start Tomcat server by running the script _%TOMCAT%\bin\startup_ .bat for Windows or .sh for Unix based OS.
* After server start, application will be available by URL _http://localhost:8080/cinema
* Use login _**"admin"**_ and password _**"1234"**_ to log in with administrator rights.
* Use login _**"neil"**_ and password _**"darksideofthemoon"**_ to log in with inspector rights.
* To stop server run script _%TOMCAT%\bin\shutdown_ .bat for Windows or .sh for Unix based OS.
