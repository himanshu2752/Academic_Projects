<?php 
session_start();


if($_SERVER["REQUEST_METHOD"] == "POST"){

$name = $_POST["myname"];
$username = $_POST["username"];
$password = $_POST["password"];

	function test_input($data){
	$data = trim($data);
	$data = stripcslashes($data);
	$data = htmlspecialchars($data);
	return $data;
}

	$Err = "";
	if (empty($name)){
		$Err = "Name is required";
	}
	else{
		
		$name = test_input($name);
	}
	if (empty($username)){
		$Err = "username is required";
	}
	else{
		
		$username = test_input($username);
	}
	if (empty($password)){
		$Err = "password is required";
	}
	else{
		
		$password = test_input($password);
	}
		if($Err == ""){
		session_regenerate_id();	
		$_SESSION['sess_user_id'] = $name;
		$_SESSION['sess_user_name'] = $username;
		session_write_close();
		header('Location: welcome.php');
		//exit();
		}
		else{			
			//echo($Err);			
			header('Location: login.html');
			exit();
		}	

}
?>