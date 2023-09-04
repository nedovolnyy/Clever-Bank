# Clever-Bank
Task for Java Trainee at Clevertec 2023

## Done
        - Спроектированна база данных и приведена к 3NF
        - Слои DataAccess и бизнес-логики для более универсального применения решения
        - Реализованна работа с конфигурационным файлом Configuration.yml
        - Написанны интеграционные тесты для DA и BL с развертыванием контейнеров в Docker
        - Написанны метод пополнения счёта и асинхронный метод для начисления процентов
        

## Possible Drawbacks/Concerns (What should reviewers look out for?)
	- Don't realized console menu.
        - Нет метода передачи средств между счетами
        - Нет метода для формирования чека
        - Нет модульных тестов

## Testing Notes (How do we know this works & doesn't break other things)
* Created automated db creation for integration tests.

## Structure
* [BusinessLogic Layer](/app/src/main/java/CleverBank/BusinessLogic/) - Contains services for each entity.
* [DataAccess Layer](/app/src/main/java/CleverBank/DataAccess/) - Contains repository for each entity.
* [Common](/app/src/main/java/Common/) - Contains entity classes and shared classes.
* [Configuration.yml](/app/src/main/resources/Configuration.yml) - Configuration file.
* [Database](/app/src/test/resources/CleverBankDB.sql) - Project database.
* [Integration Tests](/app/src/test/java/CleverBank/IntegrationTests/) for DataAccess Layer.

# How to build and run the whole solution
1. Need installed: Docker, PostgerSQL
2. Need to create and deploy a [Database](/app/src/test/resources/CleverBankDB.sql) from this solution first
3. Ok => Run

# Credentials
 Name of [Database](/app/src/test/resources/CleverBankDB.sql) is "CleverBankDB" or need to edit [Configuration.yml](/app/src/main/resources/Configuration.yml)