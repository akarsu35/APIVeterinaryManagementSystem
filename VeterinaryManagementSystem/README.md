# Veterinary Management System


## Application Structure

- Veterinary will add doctors,
- will record doctors' working days (available days),
- will be recorded as date and will record customers,
- will add customers' animals,
- will record the vaccinations applied to the animals with their dates,
- They will make appointments with doctor for animals,
- Date and time will be entered when creating an appointment,
- When making an appointment, the doctor's available days should be checked, and the date and time should be checked from the appointment records. If there is no conflict in the records, an appointment should be created.
<img width="780" alt="veterinary uml diagram" src="https://github.com/akarsu35/APIVeterinaryManagementSystem/assets/152394526/bc0ec9fc-a478-48f3-8db3-41b5c9998f34">

## End Points (URLs)
  
### Customer Endpoints
****
+ > **POST (save) || hayvan sahibi kaydetme işlemi** : localhost:8080/v1/customers
   
    ***Örnek :***
> 
    {

       "name": "Ömer",
   
        "phone": "0551170111",
   
        "mail": "Ömer35@gmail.com",
   
        "address": "osmangazi",
   
       "city": "Samsun"
   
    }

+ > **GET (getId) | İd'si girilen hayvan sabi kaydının getirilmesi** : localhost:8080/v1/customers/{id}
  
     ***Örnek : localhost:8080/v1/customers/1***

+ > **GET (cursor) | kayıtlı tüm hayvan sahilerini getirme** : localhost:8080/v1/customers

+ > **PUT (update) | girilen kaydın güncellenmesi** : localhost:8080/v1/customers

     ***Örnek :***
  
    {
  
       "id": 6,
  
       "name": "Bahar",
  
       "phone": "055117",
  
       "mail": "bahar@gmail.com",
  
       "address": "osmangazi",
  
       "city": "izmir"
    }

+ > **DELETE (delete) || Id'si girilen Hayvan sahibinin silinmesi** : localhost:8080/v1/customers/{id}

  ***Örnek : localhost:8080/v1/customers/1***

+ > **GET(findByName) || Hayvan sahibi ismine göre filtreleme** : localhost:8080/v1/customers/customerName/{customerName}
  
     ***Örnek : localhost:8080/v1/customers/customerName/akarsu***
  
+ > **GET(animals)|| İsmi girilen hayvan sahibinin sistemde kayıtlı tüm hayvan bilgilerini getirme** : localhost:8080/v1/customers/animals/{animalName}
  
     ***Örnek : localhost:8080/v1/customers/animals/cumhurAkarsu***
  

### Animal Endpoints
****
+ > **POST (Save) || Hayvan kaydetme** : localhost:8080/v1/animals
   ***Örnek :***
  
    {
  
       "name": "zeb",
  
       "species": "zebra",
  
       "breed": "scottish",
  
       "gender": "erkek",
  
       "color": "sarı",
  
       "dateOfBirth": "2021-03-05",
  
       "customer": {
  
           "id": 4
  
       }
    }

+ > **GET (getId) || Id'si girilen hayvan bilgileirnin getirme** : localhost:8080/v1/animals/{id}
    
     ***Örnek : localhost:8080/v1/animals/1***

+ > **GET(cursor) || Sistemde kayıtlı tüm hayvanları getirme** : localhost:8080/v1/animals

+ > **PUT(update) || hayvan bilgileirinin güncellenmesi** : localhost:8080/v1/animals

     ***Örnek :***
    
        {
    
           "id": 16,
    
           "name": "akarsu",
    
           "species": "kedi",
    
           "breed": "scottish fold",
    
           "gender": "erkek",
    
           "color": "sarımsı",
    
           "dateOfBirth": "2024-03-05",
    
           "customer":{
    
             "id":5
    
           }

+ > **DELETE (delete) || Id'si girilen hayvan bilgilerinin silinmesi** : localhost:8080/v1/animals/{id}

+ > **GET(findByName) || Hayvan ismine göre filtreleme** :localhost:8080/v1/animals/animalName/{animalName}

+ > **GET(vaccines) || Hayvan ismine göre aşı bilgilerini getirme** : localhost:8080/v1/animals/vaccineName/{animal name}


  ### Vaccine Endpoints
  ****

+ > **POST(Save) || Aşı kaydetme** :localhost:8080/v1/vaccines

    ***Örnek :***

        {
    
          "name":"kuduz",
  
          "code":33,
    
          "protectionStartDate":"2024-09-03",
    
          "protectionFinishDate":"2024-10-02",
    
          "animal":{
    
              "id":16
    
          }
        }

+ > **GET(getId) || Id'si girilen aşı bilgilerini geitrme** : localhost:8080/v1/vaccines/{id}

+ > **PUT(update) || Aşının bilgilerinin güncellenmesi** : localhost:8080/v1/vaccines
       
  ***Örnek :***
          {
      
             "id":2,
      
             "name":"iç ve dış parazit",
      
             "code":3333,
      
             "protectionStartDate":"2024-01-05",
      
             "protectionFinishDate":"2024-08-01",
      
             "animal":{
      
                 "id":19
      
             }
          }

+ > **DELETE(delete) || Id'si girilen aşının kaydının sillinmesi** : localhost:8080/v1/vaccines/{id}

+ > **GET(cursor) || Sisteme kayıtlı tüm aşı bilgilerini getirme** :localhost:8080/v1/vaccines

+ > **GET(vaccineDate)|| girilen tarih aralığına göre aşı arama** : localhost:8080/v1/vaccines/date/{startDate & endDate}

    ***Örnek : localhost:8080/v1/vaccines/date?startDate=2024-06-06&endDate=2025-06-12***


### Doctor Endpoints
****

+ > **POST(save) || Doctor kaydetme** : localhost:8080/v1/doctors

      {
  
         "name":"Alper",
  
         "phone":"05551111111",
  
         "mail":"alper@gmail.com",
  
         "address":"Şirinyer",
  
         "city":"izmir"
  
      }

+ > **GET(getId) || Id'si girilen doctor bilgilerini getirme** : localhost:8080/v1/doctors/{id}

+ > **PUT(update) || Doktor bilgilerinin güncellenmesi** : localhost:8080/v1/doctors
  
        {
  
           "id": 3,
  
           "name": "cumhur",
  
           "phone": "555555666",
  
           "mail": "cumhur@gmail.com",
  
           "address": "bayraklı",
  
           "city": "izmir"
        }

+ > **DELETE(delete) || Id'si igirlen doktor kaydının silinmesi** : localhost:8080/v1/doctors/{id}

+ > **GET(cursor) || kayıtlı doktor bilgilerinin getirme** : localhost:8080/v1/customers



### Avaibledate Endpoints
****

+ > **POST(save) || doktorun müsait günlerinin kaydı** : localhost:8080/v1/avaibledates

      {
  
         "availableDate": "2028-03-02",
  
         "doctor": {
  
             "id":3
  
         }
      }

+ > **GET(getId) || Id'si girilen müsait gün bilgilerini getirme** : localhost:8080/v1/avaibledates/{id}

+ > **GET(cursor) || Sistemde kayıtlı bütün müsait günleri getirme** : localhost:8080/v1/avaibledates

+ > **PUT(update) || Müsait günün bilgilerinin güncellenmesi** : localhost:8080/v1/avaibledates

      {
  
         "id":2,
  
         "availableDate": "2025-03-01",
  
         "doctor": {
  
             "id": 3
  
         }
      }

+ > **DELETE(delete) || Id'si girilen müsait günün silinmesi** : localhost:8080/v1/avaibledates/{id}


### Appointment Endpoints
****

+ > **POST(save) || Randevu bilgilerinin kaydetme** : localhost:8080/v1/appointments

      {
  
         "date":"2024-03-03T11:00:00",
  
         "animal":{
  
             "id":20
      
         },
  
         "doctor":{
  
             "id":5
  
         }
      }

+ > **GET(getId) || Id'si girilen randevu bilgilerini getirme** : localhost:8080/v1/appointments/{id}

+ > **GET(cursor) || Sistemde kayıtlı tüm randevu bilgilerini getirme** : localhost:8080/v1/appointments

+ > **PUT(update) || Randevu güncelleme** : localhost:8080/v1/appointments

      {
  
         "id":2,
  
         "date":"2025-12-12T12:00:00",
  
         "animal":{
  
             "id":21
      
         },
  
         "doctor":{
  
             "id":1
  
         }
      }

+ > **DELETE(delete) || Id'si girilen randvu bilgilerinin silinmesi** : localhost:8080/v1/appointments/{id}

+ > **GET(getByAnimalIdAndDate) || girilen tarih aralığına ve hayvan id sine göre randevuları getirme** : localhost:8080/v1/appointments/getByAnimalIdAndAppointmentDate/{animalId & starDate & endDate}

    ***Örnek :***

   http://localhost:8080/v1/appointments/getByAnimalIdAndAppointmentDate?animalId=21&starDate=2025-12-11T00%3A00%3A00&endDate=2025-12-15T00%3A00%3A00

+ > **GET (getByDoctorIdAndAppointmentDate) || Girilen tarih aralığına ve doctor id sine göre randevuları getirme** : localhost:8080/v1/appointments/getByDoctorIdAndAppointmentDate/{doctorId & starDate & endDate}

    ***Örnek :***

    http://localhost:8080/v1/appointments/getByDoctorIdAndAppointmentDate?doctorId=1&starDate=2025-12-11T00%3A00%3A00&endDate=2025-12-15T00%3A00%3A00











  















