# Restful web application backend for conference management
### Tech stack used
* Spring boot
* H2
* Gradle
* REST-assured

### Functionality
* Create new conference
* Cancel conference
* Add participant to conference
* Remove participant from conference
* Get conference info
* Request validation
* Authentication and authorization based on roles (USER, ADMIN)

### Running the application
Run command ```gradlew bootRun``` or run ConferenceApplication class.
All endpoints require authentication. Basic authentication is used for authentication.

### Test accounts

| Username      | Password | Role  |
| ------------- |:--------:| -----:|
| test_user     | user     | USER  |
| test_admin    | admin    | ADMIN |