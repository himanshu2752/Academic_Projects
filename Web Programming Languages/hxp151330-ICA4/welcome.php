<?php 
session_start();
$name = $_SESSION['sess_user_id'];
$username = $_SESSION['sess_user_name'];
if(empty($name) or empty($username)){
	header('Location: login.html');
			exit();
}
?>

<h1>Welcome <?php echo($_SESSION['sess_user_name']);?> !! </h1>
<div>
<h2><?php echo("ID i.e Name: " . $_SESSION['sess_user_id']); ?></h2>
</br>
<h2><?php echo("userName: " . $_SESSION['sess_user_name']); ?></h2>

</div>


