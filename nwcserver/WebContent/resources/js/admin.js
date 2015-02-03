$(function() {
	$(".hero-unit").hide();
	
	
});
function showAdminAction(action){
	$(".hero-unit").hide();
	$("#"+action).show();	
	if (action="userList"){
		$('#userListTable').bootstrapTable({
		    url: 'userList.act',
		   
		}).on('click-row.bs.table', function (e, row, $element) {
			$.ajax({
				url:"userDetails.act",
				data:{"login":row.login},
				type : 'POST'
			}
			).done(function(data){
				$("#addeditSeller").show();
				data=JSON.parse(data);
				$("#addSellerName").val(data["userShortName"]);
				$("#addSellerLogin").val(data["login"]);
				$("#addSellerMail").val(data["email"]);
				$("#addSellerAddress").val(data["seller"]["Address"]);
				$("#addSellerMaster").val(data["seller"]["Master"]);
				$("#addSellerMasterPosition").val(data["seller"]["MasterPosition"]);
				$("#addSellerRr").val(data["seller"]["Rr"]);
				$("#addSellerMfo").val(data["seller"]["Mfo"]);
				$("#addSellertaxCertificate").val(data["seller"]["taxCertificate"]);
				$("#addSellertaxNumber").val(data["seller"]["taxNumber"]);
				$('#addSellerLogin').prop('readonly', true);
				$("#addSellerRepeatNewPassword").val("");
				$("#addSellerNewPassword").val("");
				
			}).error (function (data){
				alert("Error:"+JSON.stringify(data));
			})
			$(".hero-unit").hide();
	    });
	}
	if (action="addeditSeller"){
		$('#addSellerLogin').prop('readonly', false);
	}
}
function refreshUserListTable(){
	$('#userListTable').bootstrapTable('refresh', {
        url: 'userList.act'
    });
	  /*$('#userListTable').bootstrapTable({
		 
	  })*/
}
function changeAdminPassword(){
	$.ajax({
		url:"changeAdminPassword.act",
		data:{"newPassword":$("#newPassword").val(),"repeatNewPassword":$("#repeatNewPassword").val()},
		type : 'POST'
	}
	).done(function(data){
		alert(data);
	}).error (function (data){
		alert("Error:"+JSON.stringify(data));
	})
}
function addSeller(){
	$.ajax({
		url:"addeditSeller.act",
		data:{"name":$("#addSellerName").val(),"login":$("#addSellerLogin").val()
			,"email":$("#addSellerMail").val(),"password":$("#addSellerNewPassword").val()
			,"repeatPassword":$("#addSellerRepeatNewPassword").val(),"address":$("#addSellerAddress").val()
			,"master":$("#addSellerMaster").val(),"masterPosition":$("#addSellerMasterPosition").val()
			,"rr":$("#addSellerRr").val(),"mfo":$("#addSellerMfo").val()
			,"taxCertificate":$("#addSellertaxCertificate").val(),"taxNumber":$("#addSellertaxNumber").val()},
		type : 'POST'
	}
	).done(function(data){
		alert(data);
	}).error (function (data){
		alert("Error:"+JSON.stringify(data));
	})
}