<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add New Post</title>
</head>
<body>
<a href="servlet"><h1>Show All Posts</h1></a>
<h2>or Add a New Post</h2>
<form action="servlet" method="POST">
  <label for="title">Title:</label>
  <input type="text" id="title" name="title" required>
  <br>
  <label for="author">Author:</label>
  <input type="text" id="author" name="author" required>
  <br>
  <label for="content">Content:</label>
  <br>
  <textarea id="content" name="content" rows="10" cols="50" required></textarea>
  <br>
  <button type="submit">Submit</button>
</form>
</body>
</html>
