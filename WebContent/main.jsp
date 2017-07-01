<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ruoruo</title>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="bootstrap.min.css">
<script src="jquery.min.js"></script>

<script src="bootstrap.min.js"></script>
<script src="main.js"></script>



</head>
<body id="mainbody">
<div class="container">
<ul class="nav nav-tabs">
	<li class="active"><a id="introTab" data-toggle="tab" href="#intro">关于作者</a></li>
	<li><a id="essayTab" data-toggle="tab" href="#essay">随笔</a></li>
	<li><a id="novelTab" data-toggle="tab" href="#novel">小说</a></li>
	<li><a id="contactTab" data-toggle="tab" href="#contact">联系作者</a></li>
</ul>
<div class="tab-content">
	<div id="intro" class="tab-pane fade in active main-tab-pane">
		我叫Ruoruo， 我是一个男孩子。 目前在University of California San Diego
		读本科， 专业Computer Science。 也许我比较独特的地方就是：任何问题我都喜欢
		思考它深层的逻辑， 而不是满足于简单的解释。 算是一个aesthete, thinker,
		和文学爱好者。 
	</div>
	<div id="essay" class="tab-pane fade main-tab-pane" >
		<div id="essay_entries" class="entries"></div>
	</div>
	<div id="novel" class="tab-pane fade if-tab-pane">
		
	</div>
	<div id="contact" class="tab-pane fade main-tab-pane ">
		
	</div>
</div>
</div>
</body>
</html>