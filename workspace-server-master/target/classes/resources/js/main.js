// $(document).ready(function(){
// 	$('#search-form').submit(function(event){
// 		//stop submit the form, we will post it manually.
//         event.preventDefault();
//         fire_ajax_submit();
// 	});
// });
function fire_ajax_submit () {
	$.ajax({
		url: 'https://localhost:444/api/search65435453456454',
		success: function (response) {
			console.log(response)
		},
		error: function (error) {
			console.log(error)
		}
	})
	return false;
}
// function fire_ajax_submit(){
// 	alert('sssss');
// 	var search = {'username': 'test', 'password': 'test123'};
//     // search["username"] = $('#username').val();
//     // search["password"] = $('#password').val();
//     console.log(search)
// 	$("#btn-search").prop("disabled", true);
// 	$.ajax({
// 		type: 'POST',
// 		contentType: "application/json",
// 		url: "https://localhost:444/api/search",
// 		data: search,
// 		dataType: 'json',
// 		cache: false,
// 		timeout: 600000,
// 		success: function(data){
// 			console.log(data)
//             // var json = "<h4>Ajax Response</h4><pre>"
// 				// + JSON.stringify(data, null, 4) + "</pre>";
//             // $('#feedback').html(json);
//             // console.log("SUCCESS : ", data);
//             // $("#btn-search").prop("disabled", false);
// 		},
// 		error: function(e){
//             var json = "<h4>Ajax Response</h4><pre>"
//                 + e.responseText + "</pre>";
//             $('#feedback').html(json);
//
//             console.log("ERROR : ", e);
//             $("#btn-search").prop("disabled", false);
//
// 		}
// 	})
// }