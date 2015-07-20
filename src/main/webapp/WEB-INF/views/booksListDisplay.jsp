<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<html>
<head>
    <title>Spring MVC Hello World</title>
</head>
 
<body>
    <h2>All Books in System</h2>
 
    <table border="1">
        <tr>
            <th>Book Id</th>
            <th>Name</th>
            <th>Author</th>
            <th>Price</th>
            <th>Time</th>
        </tr>
        <!-- 循环遍历books基本 -->
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.price}</td>
                <td>${book.time}</td>
            </tr>
        </c:forEach>
    </table>
 
</body>
</html>