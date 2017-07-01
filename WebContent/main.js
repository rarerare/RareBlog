window.onload=function(){
	goToTab();
	$.post(
			"mainservlet",
			{
				mAction: "getEss"
			},
			function(data, status, jqXHR ){
				$("#essay_entries").html(data);
			}
	);
	resizeIframe();
		
}
function goToTab(){
	var url=window.location.href;
	var tabId="#"+(url.split("?"))[1].slice(4);
	
	$(tabId).tab('show');
}
function resizeIframe(){
	$(".tabFrame").each(function(){
		var frame=this;
		this.onload=function(){
			
			frame.height=frame.contentWindow.document.body.scrollHeight;
		}
	});
	
}
