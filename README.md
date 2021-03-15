# Hall-Booking-App
Simple demo app in Java-Spring which provides APIs to requests and check bookings of halls in a hypothetical college.

## Getting Started
For now there are 6 seminar halls in your college. Hall A has a capacity of 50. Hall B has a capacity of
100. Hall C has a capacity of 200. Hall D has a capacity of 350. Hall E has a capacity of 500.
HAll F has a capacity of 1000.  
The Application has been written in Java 8 using Spring framework (2.4.2) for backend and Microsift Azure Sql database as data storage.

## Database Details and Data Schema

Database used :  Microsoft Azure Sql Database (free account)  
Database connection details:  

spring.datasource.url=******  
spring.datasource.username=*******  
spring.datasource.password=********  

Because of Microsoft Firewall security a connection request from unknown IP will be declined, The IP should be added before to connect from application  

The Data Schema used for storing requests and Halls are as follows:  

Table : HALLS -> To store Hall Details, the Program takes these details on start of Application, Any extra Hall added in future will be reflected in the application on restart  

id	hall_name	capacity  
1		A		50  
2		B		100  
3		C		200  
4		D		350  
5		E		500  
6		F		1000  

Table : HALL_BOOKING_REQUEST : to store booking request, The application queries this table and searches whether requested slot is available, if available the booking request is inserted in the table  

request_id	requestor_id	requestor_name	request_date					start_time	end_time	hall_name	req_capacity	capacity  
1			GE2313			GEORGE			2021-01-23T00:00:00.0000000		09:00:00	10:00:00		A			40				50  
2			RS001			Rahul Sahani	2021-01-25T00:00:00.0000000		10:00:00	12:00:00		D			320				350  
3			RS001			Rahul Sahani	2021-01-25T00:00:00.0000000		15:00:00	18:00:00		E			450				500  
4			RS001			Rahul Sahani	2021-01-26T00:00:00.0000000		15:00:00	18:00:00		E			450				500  
5			RS001			Rahul Sahani	2021-01-27T00:00:00.0000000		15:00:00	18:00:00		E			450				500  
6			RS001			Rahul Sahani	2021-01-26T00:00:00.0000000		10:00:00	12:00:00		E			450				500  
7			DV04			Darth Vader		2021-01-26T00:00:00.0000000		10:00:00	12:00:00		B			100				100  
8			LS101			Luke Skywalker	2021-01-26T00:00:00.0000000		10:00:00	12:00:00		C			200				200  


## Provided APIs and its Functionalities

The following APIs are provided:  

Sl No	Api end point		Description																		Request Type		Request Body (in json)  
1		/getAllBookings		Gets All the bookings from DB (does a select * from table)						Get Request			N/A  
2		/getBookings		returns all the bookings in a date range (from startDate to endDate)			Post Request		startDate,endDate  
3		/hallAvailability	returns all the Available halls for a given requested date,time and capacity)	Post Request		requestDate, startTime, endTime, reqCapacity  
4		/requestBooking		books the requested hall for the given slot if available						Post Request		requestorId, requestorName, requestDate, startTime, endTime, reqCapacity, hallName  

Application flow:

User --> App Controller (Exposed APIs) --> Service Layer --> Repository Layer --> Database   

Application Use:  

User requests for a hall for a given date, time and request capacity using the hallAvailability API , which returns all the Available Halls with Capacity >= the requested capacity  
User than can select one hall from the available list, and place a request to book the Hall for the given date, time capacity by providing the requestorId (Some ID) and requestorName along with the HallName  
If the requestor requests for a Hall other than the one provided in the available list, the app will give a message that the hall is not available else it will book the given request and store in Database  
User can use getAllBookings to get all the bookings and getBookings Api to get bookings for a date range  


## Authors

* **Rahul Sahani**


