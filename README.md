## Данный проект представляет собой реалзиацию агрегатора вакансий.

Система запускается по расписанию. Период запуска указывается в настройках - app.properties.

Для примера выбран сайт career.habr.com. В нем есть раздел https://career.habr.com/vacancies/java_developer. С ним будет идти работа. 
Программа считывает все вакансии относящиеся к Java и записывает их в базу.

Доступ к интерфейсу будет через REST API.


Расширение.

1. В проект можно добавить новые сайты без изменения кода.

2. В проекте можно сделать параллельный парсинг сайтов.
