/**
 * 真旅项目一次性重新签合同
 */
var completeSuccess = false;
$(function(){
	var isRead = $("#isRead").val();
	$(".finaLeadCheck .checkbox").click(function () {
		$(this).toggleClass("active");
		if($(this).hasClass("active") && isRead == "true"){
			$(".commonBtn-link").removeClass("disable-btn");
		}else{
			$(".commonBtn-link").addClass("disable-btn");
		}
	}); 
	
	//初始化操作结果弹窗
	tipPopWindow = new PopWindow({
		title: "提示",
		width: 420,
		reload: true,
		template: "#tipBox_template"
	}).init();
	
	//提示弹窗确认按钮
    $("body").on("click", "#tip-box-window .confirm-link", function(e){
    	if(completeSuccess)
    		window.location.href = encodeURI(basepath + "/welcome");
    	else
    		tipPopWindow.close();
    });
    
    Signature.onLoadSignatureSoftware();
    
	var supervisionAccountNo = $("#supervisionAccountNo").val();
	var contractId = $("#contractId").val();
	//阅读合同
	$("#readContract").click(function(){
		window.location.href = encodeURI(basepath + "/reguide/readContract");
    });
	//下载合同
	$("#downloadContract").click(function(){
        window.location.href = encodeURI(basepath + "/reguide/downloadContract");
	});
	//完成签署合同
	$("#completeContract").click(function(){
		if(isRead != "true"){
			showAlertWin("请先阅读合同！");
			return;
		}
		if(!$(".finaLeadCheck .checkbox").hasClass("active")){
			showAlertWin("请同意合同！");
			return;
		}
		
		$.ajax({
	        type: 'POST',  
	        url: basepath + '/reguide/getContractData',
	        success:function(data){
	        	tipPopWindow.open().center();
	        	$(".k-window-actions").hide()
	        	
	    		if(data.status=='SUCCESS'){
	    			$("#tip-box-window .dlg-del-row a").hide();
	    			$("#tip-box-window .notice-content").text("合 同 签 名 中 ...");
	    			Signature.setDNFilter(companyName);
					var signature = Signature.signMessageOnClick(data.data, "Attach");
					$.ajax({
				        type: 'POST',  
				        url: basepath + '/reguide/completeContract',
				        data: {
				        	signData: signature
				        },
				        success:function(data){
				        	$("#tip-box-window .dlg-del-row a").show();
				    		if(data.status=='SUCCESS'){
				    			completeSuccess = true;
								$("#tip-box-window .notice-content").text(data.message);
							}else{
								completeSuccess = false;
								$("#tip-box-window .notice-content").text(data.message);
							}
				        }
				    });
				}else{
					$("#tip-box-window .notice-content").text(data.message);
				}
	        }
	    });
		
	});
});