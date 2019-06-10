#  Задание

### Java Developer (Billing)


###### Условие:

Базовая валюта сервиса - USD.
Доступные валюты кошельков - USD, EUR, CAD, CNY

Необходимо разработать веб-приложение простой платежной системы. Требования:

1) Каждый клиент в системе имеет один "кошелек", содержащим денежные средства в некой валюте. 

2) Сохраняется информация о имени клиента, стране и городе его регистрации, валюте кошелька и остатке средств на нем.

3) Клиенты могут делать друг другу денежные переводы в любой валюте поддерживаемой системой.

4) Сохраняется информация о всех операциях на кошельке клиента.

5) В системе существует информация о курсах валют к USD на каждый день.

6) Проект представляет из себя HTTP API, содержащее основные операции по "кошелькам" и endpoint для отчетов.

7) HTTP API должен представлять следующие интерфейсы:

    1) регистрация клиента с указанием его имени, страны, города регистрации, валюты создаваемого кошелька.
    2) зачисление денежных средств на кошелек клиента
    3) перевод денежных средств с одного кошелька на другой.
    4) загрузка котировки валюты к USD на дату

8) Отчет должен отображать историю всех операций по кошельку указанного клиента за период.

    1) Параметры: Имя клиента (обязательный параметр), Начало периода (необязательный параметр), конец периода (необязательный параметр).
    2) Должна быть предусмотрена возможность выгрузки отчета в CSV или XML формате.

***

# Result

## API

#### User API

- GET /api/users

Get all users

- GET /api/user/{id}

Get one user by id

- POST /api/user

Create user  

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

#### Event API
- GET /api/events

Get all events

- GET /api/event/{id}

Get specified event by id

- POST /api/event

Create new event

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

