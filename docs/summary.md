# Отчет по итогам автоматизации

### Что было запланировано и что было сделано
Все сценарии тестов, содержащиеся в [плане](https://github.com/BudnikovaNastiya/QA-Diploma/blob/master/docs/plan.md) были проведены и автоматизированы.
Дополнительно были написаны автотесты для проверки появления предупреждений при заполнении полей некорректными значениями, т.к. для данного фукционала было
выявлено много ошибок.

### Сработавшие риски

Из-за отсутствия ТЗ и какой-либо документации сложно судить, насколько корректно были выбраны сценарии для написания автотестов.
Возникли проблемы с указанием volumes для контейнера mysql.

### Общий итог по времени

Сложно определить точное время, потраченное на автоматизацию, при этом обязательство по итоговым срокам сдачи работ также было выполнено с задержкой из-за неглубокого знания кодинга.
По факту написание тестов заняло меньше времени, чем предполагалось. При этом довольно много времени ушло на то, чтобы разобраться как необходимо запускать
приложение, и как именно нужно передавать параметры при запуске приложения и при запуске автотестов.
* Настройка тестового окружения - 8  
* Написание и отладка автотестов - 42
* Прогон тестов и заведение issue - 12
* Подготовка отчета тестирования - 8
* Доработка - 26
* Итого - 96
