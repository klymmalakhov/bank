#  Bank project

### Java Developer (Billing)


###### Condition:

Create web services for payment system. 

1) Each client has onу wallet 

2) Saved client info: name, country, city, currency, balance

3) There's a possibility to transfer money between clients

4) All information about transfers are saved

5) There's possibility to make deposit 

5) HTTP API interface:

    1) Client registration
    2) Increase client balance
    3) Transfer between wallets
    
7)  Provide reports with transfer and deposit history:
    
    1) Parameters: client (mandatory), date from (not mandatory), date till (not mandatory)
    2) There's possibility to get report as CSV and XML file   
 
Base currency - USD.

***

# Result

## API

### User API
 
##### Get all users

- GET /api/users

##### Get one user by id

- GET /api/user/{id}

##### Create user 

- POST /api/user


``` 
{
    "password": "password",
    "name": "name"
    "email": "email",
    "amount": 12345,
    "currency": "USD",
    "town":"Limassol",
    "country":"Cyprus"
}
         
  ``` 
  
User and Account should be created and linked

```
{
    "id": 4,
    "password": "name",
    "name": "password",
    "email": "email",
    "town": "Limassol",
    "country": "Cyprus",
    "account": {
        "id": 3,
        "amount": 12345,
        "currency": "USD"
    }
}
```

### Event API

##### Get all events

- GET /api/events

*_parameters:_*

_userId_ - required;

_fromDate_ - is not required;

_tillDate_ - is not required;

Example request:

```
/api/events?userId=6&fromDate=2019-02-14T21:57:25.806Z&tillDate=2019-06-09T11:57:21.806Z
```

Example response:

```
[
    {
        "id": 8,
        "date": "2019-04-13T18:50:44.806Z",
        "amount": 5,
        "currency": "USD",
        "description": "for startup!",
        "source": {
            "id": 3,
            "amount": 300,
            "currency": "USD"
        },
        "destination": {
            "id": 4,
            "amount": 100,
            "currency": "USD"
        }
    }
]
```

##### Get specified event by id

- GET /api/event/{id}

##### Create new event

- POST /api/event

Example request body:

```
{
    "amount": 5,
    "currency": "USD",
    "description": "Charity",
    "source": 3,
    "destination":4
}
```

Example response body:

```
{
    "id": 16,
    "date": "2019-05-31T06:26:43.381Z",
    "amount": 5,
    "currency": "USD",
    "description": "Charity",
    "source": {
        "id": 3,
        "amount": 300,
        "currency": "USD"
    },
    "destination": {
        "id": 4,
        "amount": 100,
        "currency": "USD"
    }
}
```

## React

dependencies:  bootstrap@4.1.3 react-cookie@3.0.4 react-router-dom@4.3.1 reactstrap@6.5.0

start: Open folder _app_ and execute _yarn start_

