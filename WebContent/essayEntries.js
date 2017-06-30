window.onload=function(){
	$.post(
			"mainservlet",
			{
				mAction: "getEss"
			},
			function(data, status, jqXHR ){
				$("#entries").html(data);
			}
	);
	
}
