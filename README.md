## Описание

Это консольное Java-приложение для статистической обработки JSON-данных. Оно читает массив объектов из JSON-файла и выполняет одну из трёх функций: `avg`, `max`, `values`, `bulk`.

## Стэк

- Java 24
- Maven 3.9.9

## Структура поддерживаемых данных

Данные, которые загружаются в OpenSearch, должны иметь следующую структуру:

```json
{
  "ups_adv_output_load": 50,
  "ups_adv_battery_temperature": 35,
  "@timestamp": "2024-06-15T12:00:00Z",
  "host": "host-123",
  "ups_adv_battery_run_time_remaining": 200,
  "ups_adv_output_voltage": 230
}
```
Json-файл с массивом объектов нужной структуры должен быть помещён по пути src/main/resources/testData.json

## Как запустить

Сборка JAR-файла:

```bash
mvn clean package
````

Запуск с нужной функцией:

```bash
java -jar target/app-jar-with-dependencies.jar <функция> testData.json
```

Примеры:

```bash
java -jar target/app-jar-with-dependencies.jar avg testData.json
java -jar target/app-jar-with-dependencies.jar max testData.json
java -jar target/app-jar-with-dependencies.jar values testData.json
java -jar target/app-jar-with-dependencies.jar bulk testData.json test_index
```

### Аргументы:

* `<функция>` — одна из: `avg`, `max`, `values`, `bulk`
* `testData.json` — название JSON-файла, который лежит в resources
* `test_index` - название индекса, в который будут добавляться данные. Доступен только при вызове bulk.

## Реализованные функции

### `avg`

* **Что делает**: вычисляет среднее значение по полю `ups_adv_battery_run_time_remaining`
* **Особенности**: пропускает `null` и нечисловые значения

### `max`

* **Что делает**: находит максимальное значение поля `ups_adv_output_voltage`
* **Особенности**: пропускает `null` и нечисловые значения

### `values`

* **Что делает**: выводит список уникальных значений из поля `host`
* **Особенности**: пропускает `null`

### `bulk`

* **Что делает**: парсит полную структуру, в цикле добавляет 
* **Особенности**: пропускает `null`

## Структура проекта

* `Main.java` — точка входа, логика выбора функции
* `Data.java` — модель объекта из JSON
* `DataAdapter.java` — адаптер GSON с поддержкой `null` и проверки типов

