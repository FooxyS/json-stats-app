## Описание

Это консольное Java-приложение для статистической обработки JSON-данных. Оно читает массив объектов из JSON-файла и выполняет одну из трёх функций: `avg`, `max`, `values`.

## Как запустить

Сборка JAR-файла:

```bash
mvn package
````

Запуск с нужной функцией:

```bash
java -jar app_name.jar <функция> testData.json
```

Примеры:

```bash
java -jar app_name.jar avg testData.json
java -jar app_name.jar max testData.json
java -jar app_name.jar values testData.json
```

### Аргументы:

* `<функция>` — одна из: `avg`, `max`, `values`
* `testData.json` — название JSON-файла, который лежит в resources

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

## Структура проекта

* `Main.java` — точка входа, логика выбора функции
* `Data.java` — модель объекта из JSON
* `DataAdapter.java` — адаптер GSON с поддержкой `null` и проверки типов

## Пример JSON-объекта

```json
{
  "ups_adv_battery_run_time_remaining": 300000,
  "ups_adv_output_voltage": 230,
  "host": "192.168.10.8"
}
```
