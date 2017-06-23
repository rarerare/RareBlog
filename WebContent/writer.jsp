<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title></title>
<link rel="stylesheet" href="bootstrap.min.css">
<script src="jquery.min.js"></script>

<script src="bootstrap.min.js"></script>
<script src="writer.js"></script>
<link rel="stylesheet" href="writer.css">
</head>
<body id="writerbody">
<form action="mainservlet" id="writerForm">
<input type="hidden" name="mAction" value="writer">
<input type="radio" name="category" value="essay">Essay<br>
<input type="radio" name="category" value="novel">Novel<br>
<input type="text" name="title" size="80" placeholder="Title"><br>
<textarea name="content" rows="15" cols="80"></textarea><br>
<button id="addDateButt" type="button">Add Date</button>
<p id="spaceForAddDate" hidden>
<input type='text' name='month' id="month" placeholder='MM'>
		<input type='text' name='day' id="day" placeholder='DD'>
		<input type='text' name='year' id="year" placeholder='YYYY'>
</p><br>
<button type="submit" id="submitButt">Submit</button>
</form>
</body>
</html>