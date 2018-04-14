$(document).ready(function() {
	
	var x = document.getElementsByClassName('signup');
	$('.signup').after('</br><span id = "notification">infoMessage</span>');
	
	$('#notification').hide();
	
    //x[i].addEventListener("focus", myFunction());
	$( '.signup' ).find('input').focus(function() {
		$('#notification').removeClass("error");
		$('.ok').removeClass("ok");
		$('#notification').addClass("info");		
		$('#notification').text("infoMessage");		
		$('#notification').show();
		var username = $('#username').val();
		var password = $('#password').val();
		var email = $('#email').val();		

    });
	
	 $( '.signup' ).find('input').blur(function() {
		//$('.info').remove();
		$('#notification').hide();		
		var username = $('#username').val();
		var password = $('#password').val();
		var email = $('#email').val();		
		
		if (!$('#username').val() && !$('#password').val() && !$('#email').val()){		
				$('#notification').hide();				
		}
		else{
		if($('#username').val() && !alphanumeric(username)){
				//alert('hi');
				$('#notification').addClass("error");
				$('.error').show();				
				$('.error').text("Error");
			}
		if ($('#password').val() && password.length < 8){
			$('#notification').addClass("error");
				$('.error').show();				
				$('.error').text("Error");
		}
		if($('#email').val() && !isValidEmailAddress(email)){
			$('#notification').addClass("error");
				$('.error').show();				
				$('.error').text("Error");
		}
		if(alphanumeric(username) && isValidEmailAddress(email) && password.length >= 8){
			$('.error').removeClass("error");
			$('#notification').addClass("ok");
			$('.ok').show();				
				$('.ok').text("OK");
		}
		}
		
    });
	
	
});

function alphanumeric(inputtxt)
{ 
	var letters = "";
	if(inputtxt.match(/^[0-9a-zA-Z]+$/))
	{
	//alert('Your registration number have accepted : you can try another');
	//document.form1.text1.focus();
	return true;
	}
	else
	{
	//alert('Please input alphanumeric characters only');
	return false;
	}
}
function isValidEmailAddress(emailAddress) {
    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
    return pattern.test(emailAddress);
}
