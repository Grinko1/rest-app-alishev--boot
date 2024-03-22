public class Marks {
/*
*  --SELECT * FROM Person JOIN Address ON Person.id = Address.id
*  - Inner join (JOIN) - соединяет только по условию, строки без совпадения не попадают в таблицу
*
*
*  --Select * From Person LEFT JOIN Address ON Person.id = Address.id
*  - Left join - соединяет также по условию, все значения из родительской таблицы попадаю в новую таблицу, но строки без совпадения из дочерней таблицы добавляются становяться null
* ( все Person с адрессами и без (null) )
*
*   --SELECT * FROM Person RIGHT JOIN Address ON Person.id = Address.id
*  - Right join - также как и left обьединяет, но в таблицу попадают все значения из дочерней таблицы, не подходящие из родительской становятся null
* (все Address с person  и без (null) )
*
* Right join используется редко так как тоже самое можно получить с помощью Left LOIN
*  --SELECT * FROM Person RIGHT JOIN Address ON Person.id = Address.id  == SELECT * FROM Address LEFT JOIN Person On Address.id = Person.id
*
*
* CROSS JOIN ( ? возможно используется для прогнозов и гиппотетически возможных данных ??)
* -  обьединяет строку из родительской таблицы с КАЖДОЙ строкой из дочерней таблицы даже не относящиеся к этой строке и так каждую родильскую сроку
*   SELECT * FROM PERSON CROSS JOIN Address;
*
*
* */
}
