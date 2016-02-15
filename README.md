# SolvegenTest

Реализовать web-serviсe. Который хранит данные о книгах в виде.
<?xml version="1.0"?>
<catalog>
   <book id="bk101">
      <author>Gambardella, Matthew</author>
      <title>XML Developer's Guide</title>
      <genre>Computer</genre>
      <price>44.95</price>
      <publish_date>2000-10-01</publish_date>
      <description>An in-depth look at creating applications 
      with XML.</description>
   </book>
   <book id="bk102">
      <author>Ralls, Kim</author>
      <title>Midnight Rain</title>
      <genre>Fantasy</genre>
      <price>5.95</price>
      <publish_date>2000-12-16</publish_date>
      <description>A former architect battles corporate zombies, 
      an evil sorceress, and her own childhood to become queen 
      of the world.</description>
   </book>
</catalog>

Web-service должен выполнять следующие задачи
1. Добавление новых книг
2. Удаление книг
3. Обновление аттрибутов книг

У сервиса должен быть один метод /changeBook , который принимает запрос в виде XML (в дальнейшем Request XML):

<?xml version="1.0"?>
<catalog>
<book id="bk103">
      <author>Corets, Eva</author>
      <title>Maeve Ascendant</title>
      <genre>Fantasy</genre>
      <price>5.95</price>
      <publish_date>2000-11-17</publish_date>
      <description>After the collapse of a nanotechnology 
      society in England, the young survivors lay the 
      foundation for a new society.</description>
   </book>
</catalog>

По входящему запросу сервис должен обновить, добавить или удалить записи из  Main XML используя следующий алгоритм: 
1. Если id книги в Request XML такой же как и в Main XML, тогда мы должны обновить запись в Main XML.
2. Если книга с id из Request XML не может быть найдена в Main XML, тогда мы должны добавить новую книгу в Main XML.
3. Если книга из Request XML содержит только id то мы должны удалить книгу из  Main XML.

Ответом нашего сервера должна быть новая XML, т.е. ту что мы получили в результате работы алгоритмов.
Если на web-service пришел пустой xml, тогда мы должны просто вернуть в ответе Main XML.
