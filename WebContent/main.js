window.onload=function(){
	$.post(
		"mainservlet",
		{
			mAction: "getEss"
		},
		function(data, status, jqXHR ){
			$("#essay").html(data);
		}
		)
		
		
}