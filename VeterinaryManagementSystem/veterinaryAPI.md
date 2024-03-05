*CUSTOMER ENDPOİNT*

*POST (save)  localhost:8080/v1/customers*

*{*

`   `*"name": "Ömer",*

`   `*"phone": "0551170111",*

`   `*"mail": "Ömer35@gmail.com",*

`   `*"address": "osmangazi",*

`   `*"city": "Samsun"*

*}*


*GET (getId)  localhost:8080/v1/customers/{id}*

*GET (cursor) localhost:8080/v1/customers*

*PUT (update) localhost:8080/v1/customers*

*{*

`   `*"id": 6,*

`   `*"name": "Bahar",*

`   `*"phone": "055117",*

`   `*"mail": "bahar@gmail.com",*

`   `*"address": "osmangazi",*

`   `*"city": "izmir"*

*}*


DELETE (delete)  localhost:8080/v1/customers/{id}

GET(findByName)  localhost:8080/v1/customers/customerName/{customer name}

GET(animals) localhost:8080/v1/customers/animals/{animal name}








ANİMAL ENDPOİNT

POST (Save) localhost:8080/v1/animals

{

`   `"name": "zeb",

`   `"species": "zebra",

`   `"breed": "scottish",

`   `"gender": "erkek",

`   `"color": "sarı",

`   `"dateOfBirth": "2021-03-05",

`   `"customer": {

`       `"id": 4

`   `}

}


GET (getId) localhost:8080/v1/animals/{id}

GET(cursor) localhost:8080/v1/animals

PUT(update) localhost:8080/v1/animals

{

`   `"id": 16,

`   `"name": "akarsu",

`   `"species": "kedi",

`   `"breed": "scottish fold",

`   `"gender": "erkek",

`   `"color": "sarımsı",

`   `"dateOfBirth": "2024-03-05",

`   `"customer":{

`       `"id":5

`   `}

}

DELETE (delete) localhost:8080/v1/animals/{id}

GET(findByName)localhost:8080/v1/animals/animalName/{animal name}

GET(vaccines) localhost:8080/v1/animals/vaccineName/{animal name}





VACCİNE ENDPOİNT

POST(Save)localhost:8080/v1/vaccines

GET(getId) localhost:8080/v1/vaccines/{id}

PUT(update) localhost:8080/v1/vaccines

{

`   `"id":2,

`   `"name":"iç ve dış parazit",

`   `"code":3333,

`   `"protectionStartDate":"2024-01-05",

`   `"protectionFinishDate":"2024-08-01",

`   `"animal":{

`       `"id":19

`   `}

}


DELETE(delete) localhost:8080/v1/vaccines/{id}

GET(cursor) localhost:8080/v1/vaccines

GET(vaccineDate)localhost:8080/v1/vaccines/date?startDate=2024-06-06&endDate=2025-06-12

![](Aspose.Words.2d8e1376-9c52-4b20-95ae-59c160c2360f.001.png)







DOCTOR ENDPOİNT

POST(save) localhost:8080/v1/doctors

{

`   `"name":"Alper",

`   `"phone":"05551111111",

`   `"mail":"alper@gmail.com",

`   `"address":"Şirinyer",

`   `"city":"izmir"

}

GET(getId) localhost:8080/v1/doctors/{id}

PUT(update) localhost:8080/v1/doctors

{

`   `"id": 3,

`   `"name": "cumhur",

`   `"phone": "555555666",

`   `"mail": "cumhur@gmail.com",

`   `"address": "bayraklı",

`   `"city": "izmir"

}


DELETE(delete) localhost:8080/v1/doctors/{id}

GET(cursor) localhost:8080/v1/customers


AVAİBLEDATE ENDPOİNT

POST(save) localhost:8080/v1/avaibledates

{

`   `"availableDate": "2028-03-02",

`   `"doctor": {

`       `"id":3

`   `}

}

GET(getId) localhost:8080/v1/avaibledates/{id}

GET(cursor) localhost:8080/v1/avaibledates

PUT(update)localhost:8080/v1/avaibledates

{

`   `"id":2,

`   `"availableDate": "2025-03-01",

`   `"doctor": {

`       `"id": 3

`   `}

}

DELETE(delete)localhost:8080/v1/avaibledates/{id}


APPOİNTMENT ENDPOİNT

POST(save) localhost:8080/v1/appointments

{

`   `"date":"2024-03-03T11:00:00",

`   `"animal":{

`       `"id":20

`   `},

`   `"doctor":{

`       `"id":5

`   `}

}

GET(getId)localhost:8080/v1/appointments/{id}

GET(cursor)localhost:8080/v1/appointments

PUT(update)localhost:8080/v1/appointments

{

`   `"id":2,

`   `"date":"2025-12-12T12:00:00",

`   `"animal":{

`       `"id":21

`   `},

`   `"doctor":{

`       `"id":1

`   `}

}

DELETE(delete)localhost:8080/v1/appointments/{id}

GET(getByAnimalIdAndDate) <http://localhost:8080/v1/appointments/getByAnimalIdAndAppointmentDate?animalId=21&starDate=2025-12-11T00%3A00%3A00&endDate=2025-12-15T00%3A00%3A00>![](Aspose.Words.2d8e1376-9c52-4b20-95ae-59c160c2360f.002.png)

