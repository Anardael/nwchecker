/**
 * Created by ReaktorDTR on 26.01.2015.
 */

function acceptUserRequests() {
	var users = getids();
	$.ajax({
		url:"/NWCServer/acceptUserRequests.do",
		type:"POST",
		data: {arrayUsersUsername:users},
		success:function(data){
			alert(data);
			$("#output").append( data );
		},
		error: function() {
			console.log('fail');   // doesn't print....
			console.log(typeof data); // prints 'undefined'
			$("#output").append('fail');
		}
	});
}

function declineUserRequests() {
	var users = getids();
	$.ajax({
		url:"/NWCServer/declineUserRequests.do",
		type:"POST",
		data: {arrayUsersUsername:users},
		success:function(data){
			alert(data);
			$("#output").append( data );
		},
		error: function() {
			console.log('fail');   // doesn't print....
			console.log(typeof data); // prints 'undefined'
			$("#output").append('fail');
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