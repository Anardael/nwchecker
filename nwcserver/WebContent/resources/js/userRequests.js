/**
 * Created by ReaktorDTR on 26.01.2015.
 */

function acceptUserRequests() {
	var users = getids();
	$.ajax({
		url:"acceptUserRequests.do",
		type:"POST",
		data: {arrayUsersUsername:users},
		success:function(data){
			$('#usersData').bootstrapTable('refresh');
		},
		error: function() {
			console.log('fail'); 
			console.log(typeof data); 
		}
	});
}

function declineUserRequests() {
	var users = getids();
	$.ajax({
		url:"declineUserRequests.do",
		type:"POST",
		data: {arrayUsersUsername:users},
		success:function(data){
			$('#usersData').bootstrapTable('refresh');
		},
		error: function() {
			console.log('fail');   
			console.log(typeof data);
		}
	});
}

function getids() {
	var indexes = [];
	var index = 0;
	var table = $('#usersData');
	table.find('tbody tr.selected').each(function() {
		var userId = $(this).find('td:nth-child(2)').html();
		indexes[index] = userId;
		index++;
	});
	console.log(indexes);
	return indexes;

}