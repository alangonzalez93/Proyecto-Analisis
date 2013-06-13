<html>
<head>
    <title>Happy Minds Colors Picker</title>
</head>
<body>
 
<form action="/favorite_color" method="POST">
 
    <p>What is your favorite color?</p>
    <#list colors as color>
        <p><input type="radio" name="color" value="${color}"> ${color}</input></p>
    </#list>
    <input type="submit" value="Submit"/>
</form>
 
</body>
</html>
