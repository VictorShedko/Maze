# План тестирования
---


# Cодержание
1 [Введение](#introduction)  
2 [Объект тестирования](#items)  
3 [Риски](#risk)  
4 [Аспекты тестирования](#features)  
5 [Подходы к тестированию](#approach)  
6 [Представление результатов](#pass)  
7 [Выводы](#conclusion)  

<a name="introduction"/>

# 1 Введение

Данный документ описывает план тестировани desktop игры Maze . Данный документ предназначен для тестировщиков. Цель тестирования: проверить соответствие приложения [требованиям](../Requirements/Reqests.md)

<a name="items"/>

# 2 Объект тестирования

В процессе тестирования предполагается проверить выполение требоаний SRS, а так же соответствие повеения приложения, завленым правилам игры.  
В качестве объектов тестирования можно выделить основные функциональные требования, требования к удобству использования, а также соответствие игровых механик [правилам игры](../Requirements/Reqests.md).  
Атрибуты качества:  
1. Функциональная пригодность:  
* функциональная полнота;  
* функциональная корректность;  
* функциональная целесообразность;  
2. Удобство использования:  
* поняность использования
* удобство управления 
* удобство восприятия
3. Игровые механики:
* соответствие повидения персонажей игровой модели
* соответствие поведения игровых обьектов правилам игры
* корректность заврешения игровго процесса

<a name="risk"/>

# 3 Риски

К рискам можно отнести:  
* болшая задержка при подключении к серверу;
* потеря пакетов с следствии нагружености соединения;


<a name="features"/>

# 4 Аспекты тестирования

В ходе тестирования планируется проверить реализацию основных аспекто работы игры приложения.  
К основным функциям можно отнести следующие пункты:  
* создание игровой комнаты;  
* присоединение к игровой комнате;  
* выход в главное меню после оканчание игры;
* корректный выход из приложения через главное меню.
* возможность сыграть еще один матч с найденым напарником

Так же в ходе тестирования следует проверить коректность работы основных игровых механик:
* возможность прятаться в "Шкафах"
* победа авнтюрист по средствам взаимодействия с игровыми обьектами
* победа минотавра по средсвам поимки авантюриста
<a name="approach"/>

# 5 Подходы к тестированию

Предполагается использовать ручное тестирование.

<a name="pass"/>

# 6 Представление результатов

Результаты тестирования представлены в документе ["Представление результатов"](../Testing/TestResults.md).

<a name="conclusion"/>

# 7 Выводы

Приложение соответствует основным функциональным требования, однако стоит отметить, что возможноти приложения могут быть заметно расширены, а некоторые игровые механики должны быть сбалансированы или переработаны.