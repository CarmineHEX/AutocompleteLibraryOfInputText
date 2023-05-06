## Локальный запуск проекта

````bash
java -Xmx7m -jar AutocompleteLibraryOfInputText-1.0-SNAPSHOT.jar
````

## Описание проекта
Программа ищет данные аэропортов по вводимому пользователем названию аэропорта и фильтрам.
1. Сначала пользователь вводит фильтр в формате:
````bash
column[1]>10 & column[5]='GKA' || column[<номер колонки с 1>]<операция сравнения>...
````
2. В случае если польователь ввел фильтр корректно, его просят ввести начало имени аэропорта
3. После этого ему выходит список найденных аэропортов, их количество, а также время поиска.
Для выхода из приложения нужно ввести строку !quit
## Описание по коду
### Запись в treemap
 Для того чтобы находить значения из файла, не загружая все его содержимое в память,
 используется treemap, который хранит в качестве ключа - имя аэропортов, а значения - позиция строки в файле.
treemap используется потому что он является красно черным деревом. Из за чего поиск элементов составляет O(log n).
Также нет необходимости сортировать значения по ключу, как это надо было бы использовать для hashmap, чтобы применить бинарный поиск
### Проверка по началу слова
После того как мы получили treemap, то производится поиск элементов, которые удовлетворяют условию
### Проверка по фильтрам
1. Отобрав все имена аэропортов, после этого мы ищем строку по значению позиции строки и выписываем ее в массив 
предварительно разбив строки на составляющие.
2. После этого разбирается фильтр, который ввел пользователь
### Проверка фильтра
1. Для того чтобы разобрать фильтр допустим
````bash
column[1]>10 & column[5]='GKA'
````
Он приводится к виду нового фильтра (допустим условие 1 колонки удовлетворяет, а 5 нет для проверяемой строки аэропорта):
````bash
true & false
````
2. После этого  по блокам выражения  boolean (& ||) boolean. Пример: true & false. Определяется, конечный результат  true или false.
3. После чего производится вывод строки в случае true  или же нет в случае false
## P.S.
Пока не знаю почему, но поиск работает намного быстрее, после того, как вводишь первый запрос.

##  Требования
1. Перечитывать все строки файла при каждом поиске нельзя. В том числе читать только определенную колонку у каждой строки.
2. Создавать новые файлы или редактировать текущий нельзя. В том числе использовать СУБД.
3. Хранить весь файл в памяти нельзя. Не только в качестве массива байт, но и в структуре, которая так или иначе содержит все
   данные из файла
4. Сложность поиска меньше чем O(n), где n — число строк файла.
5. Использовать готовые библиотеки для парсинга CSV формата нельзя.