package ru.otus.homework;

import ru.otus.homework.GCTest.GCTest;

import java.lang.management.ManagementFactory;

public class MainGC {
    public static void main(String... args) throws InterruptedException {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        GCTest.run();

    }
    /*Вывод:
     Сборщик муссора G1 подходит для приложений орентированных на минимальное время простоя так как Pause Young являеться
     минимальным среди сборщиков (около 10 мс), но общее время работы приложения чуть увеличиваеться. Сборщик Parallel GC
     являться самым быстрым, но время простоя может достигать  1700 ms, что не хорошо для некоторых приложений. Serial GC
     орентирован на системы с небольшими ресурсами. Время работы CMS получилось самое большое, около 10 мин что намного
      хуже G1 при чуть большем времени простоя, поэтому G1 предпочтительнее его.
    */




}
