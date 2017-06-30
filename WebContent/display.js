window.onload=function(){
	var url=window.location.href;
	var qStr=(url.split("?"))[1];
	$.post(
			"mainservlet?mAction=getContent&"+qStr,
			function(data){
				$("#articleDiv").html(data);
			}
	)
	$("#commentSubmitButt").click(
			function(e){
				e.preventDefault();
			}
			)
}