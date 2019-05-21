**Назначение**

Программа предлагает пользователю проверять вводимые им слова на русском языке на соответствие лексикону прозаиков и поэтов XIX-XX веков. Словарь, содержащий слова из их лексикона, взят [из просторов Интернета](http://www.speakrus.ru/dict/).

**Описание работы**
* пользователь вводит слово
* проверяется наличие его в словаре. Сложность по времени - О(1), дополнительная память - О(1)
* пользователю сообщается о корректности введённого слова
* если слово некорректно, ему ищутся варианты на замену: для каждого элемента словаря находится расстояние Дамерау-Левенштейна до введённого слова, выбирается некоторое количество (по умолчанию 5 штук) слов с наименьшим расстоянием - они и предлагаются пользователю. 
Сложность по времени - О(N * M * L), где N - длина введённого слова, M - среднее количество букв в словах из словаря, L - количество слов в словаре; дополнительная память - О(min{N, M})
* найденные слова предлагаются пользователю
* цикл повторяется

**Особенности реализации**

Используется модификация алгоритма, считающего расстояние Дамерау-Левенштейна, затрачивающая O(min{M, N}) дополнительной памяти вместо стандартного О(M * N).

Исходный код написан таким образом, чтобы можно было без труда добавить в него:
* поддержку нескольких словарей
* поддержку словарей из разных источников и разных форматов
* другие алгоритмы, проверяющие слова на корректность и предлагающие им замену
* другие способы взаимодействия с пользователем
