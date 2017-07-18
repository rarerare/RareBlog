window.onload=function(){
	$.post(
			"mainservlet",
			{
				mAction: "recordVisitor"
			}
			);
	
	$.post(
			"mainservlet",
			{
				mAction: "getEss"
			},
			function(data, status, jqXHR ){
				$("#essay_entries").html(data);
			}
	);
	$.post(
			"mainservlet",
			{
				mAction: "getEss"
			},
			function(data, status, jqXHR ){
				$("#essay_entries").html(data);
			}
	);
	$.post(
			"mainservlet",
			{
				mAction: "getNov"
			},
			function(data, status, jqXHR ){
				$("#novel_entries").html(data);
			}
	);
	goToTab();
		
}
function goToTab(){
	var url=window.location.href;
	if(url.split("?")[1]){
		var tabId="#"+(url.split("?"))[1].slice(4);
		$(tabId).tab('show');
	}
	
	
	
}

