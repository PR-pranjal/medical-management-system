
# Medical Management System

This repository contains code for the backend service responsible for functioning of medical management system.


## API Reference
The service comprises of following endpoints:
```
Get all clinics
Route: GET /clinics

Get all doctors by clinic:
Route: GET /clinics/{clinicId}/doctors

Get all patients by doctor:
Route: GET /doctors/{doctorId}/patients

Get all patients by clinic:
Route: GET /clinics/{clinicId}/patients

Search patient by name:
Route: GET /patients/name/{patientName}

Search patient by EMR number:
Route: GET /patients/emr-number/{emrNumber}

Get patients by age > 60 yrs:
Route: GET /patients/age/{age}

Add patient:
Route: POST /patients/add-patient

Update patient:
Route: PUT /patients/update-patient

Delete patient:
Route: DELETE /patients/delete-patient

Add new prescription:
Route: POST /prescriptions/add-prescription

Update prescription:
Route: PUT /prescriptions/update-prescription

Delete prescription:
Route: DELETE /prescriptions/delete-prescription
```


## Tech Stack
- Java 20
- Spring Boot

## Dependencies
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- lombok
- mysql-connector-j
- spring-boot-starter-test


## Run Locally

Clone the project

```bash
  git clone 
```

Go to the project directory

```bash
  cd my-project
```

Install dependencies

```bash
  gradle build
```

Start the server

```bash
  gradle bootRun
```


## Configuration Variables

To run this project, you will need to add the following configuration variables to your application.properties file

`spring.datasource.url`

`spring.datasource.username`

`spring.datasource.password`

`spring.jpa.hibernate.ddl-auto`
