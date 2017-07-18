
window.onload=function(){
	$.post(
			"mainservlet",
			{
				mAction: "recordVisitor"
			}
			);
	var url=window.location.href;
	var qStr=(url.split("?"))[1];
	$.post(
			"mainservlet?mAction=getComments&"+qStr,
			function(data){
				$("#prevComment").html(data);
			}
	)
	var tzo=(new Date()).getTimezoneOffset();
	$.post(
			"mainservlet?mAction=getContent&"+qStr,
			function(data){
				$("#articleDiv").html(data);
			}
	)
	getComments();
	$("#commentSubmitButt").click(
			function(e){
				e.preventDefault();
				var commentText=document.getElementById("commentText").value;
				
				$.post(
						"mainservlet?mAction=comment&"+qStr,
						{
							commentText: commentText,
							tzo:tzo
						},
						function(){
							location.reload();
						}
						
				)
				
			}
			)
}
function getComments(){
	
}