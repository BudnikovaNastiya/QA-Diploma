# Дипломный проект профессии «Тестировщик»

## Документы

* [План автоматизации](https://github.com/BudnikovaNastiya/QA-Diploma/blob/master/docs/plan.md)
* [Отчет по итогам тестирования](https://github.com/BudnikovaNastiya/QA-Diploma/blob/master/docs/report.md)
* [Отчет по итогам автоматизации](https://github.com/BudnikovaNastiya/QA-Diploma/blob/master/docs/summary.md)

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Описание приложения
Приложение представляет из себя веб-сервис "Путешествие дня".

Приложение предлагает купить тур по определённой цене с помощью двух способов:

1. Обычная оплата по дебетовой карте
1. Уникальная технология: выдача кредита по данным банковской карты

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

- сервису платежей (далее - Payment Gate)
- кредитному сервису (далее - Credit Gate)

Приложение должно в собственной СУБД сохранять информацию о том, каким способом был совершён платёж и успешно ли он был совершён (при этом данные карт сохранять не допускается).

## Запуск тестов
- склонировать репозиторий `git clone`
- для запуска контейнеров с MySql, PostgreSQL и Node.js использовать команду `docker-compose up -d --build` (необходим установленный Docker);
- запуск приложения:
  - для запуска под MySQL перейти в каталог /artifacts и использовать команду
`java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar` 
   - для запуска под PostgreSQL в каталог /artifacts использовать команду
`java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar`
- запуск тестов (Allure):
  - для запуска под MySQL использовать команду из корневой директории
`gradlew -Ddb.url=jdbc:mysql://localhost:3306/app clean test` 
  - для запуска под PostgreSQL использовать команду из корневой директории
`gradlew -Ddb.url=jdbc:postgresql://localhost:5432/app clean test`
    
- для получения отчета (Allure) использовать команду `gradlew allureServe`
- Можно сгенерировать файлы отчетов командой `gradlew allureReport` (файлы будут помещены в каталог `./build/reports/allure-report)`, в каталоге `./build/reports/tests/test` находятся отчеты, сгенерированные Gradle.
- после окончания тестов завершить работу приложения (Ctrl+C), остановить контейнеры командой `docker-compose down`