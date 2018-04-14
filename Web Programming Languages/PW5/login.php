<?php 
session_start();
$servername = 'localhost';
$username = 'root';
$password = '';

$conn = new mysqli($servername, $username, $password, "pw5");

if($_SERVER["REQUEST_METHOD"] == "POST"){

//$name = $_POST["myname"];
$username = $_POST["username"];
$userpass = $_POST["password"];
$password = '';

	function test_input($data){
	$data = trim($data);
	$data = stripcslashes($data);
	$data = htmlspecialchars($data);
	return $data;
}

	$Err = "";	
	if (empty($username)){
		$Err = "username is required";
	}
	else{
		
		$username = test_input($username);
	}
	if (empty($userpass)){
		$Err = "password is required";
		
	}
	else{
		$userpass = test_input($userpass);
	}
	
		if($Err == ""){
			$sql = "select password from users where username = '$username'";
			if ($result=mysqli_query($conn,$sql)){
				$rowcount1=mysqli_num_rows($result);
				if ($rowcount1 > 0) {			
					$row = $result->fetch_assoc();
					$password = $row['password'];		
				
			} else {
				header('Location: login.html');
				exit();
			}
		}
		if(!password_verify($userpass,$password)){			
		header('Location: login.html');
		exit();
			
		}
		else{
		
		session_regenerate_id();	
		//$_SESSION['sess_user_id'] = $name;
		$_SESSION['sess_user_name'] = $username;
		session_write_close();
		header('Location: welcome.php');
		//exit();
		}
		}
		else{			
			header('Location: login.html');
			exit();
			
		}
		}
?>