<?php 
session_start();
//$name = $_SESSION['sess_user_id'];
$servername = 'localhost';
$username = 'root';
$password = '';

$conn = new mysqli($servername, $username, $password, "pw5");



$username = $_SESSION['sess_user_name'];
$fullname = '';
$avatar = '';
$books = array();
if(empty($username)){
	header('Location: login.html');
			exit();
}
$sql = "select avatar,fullname from users where username = '$username'";
			if ($result=mysqli_query($conn,$sql)){
				$rowcount1=mysqli_num_rows($result);
				if ($rowcount1 > 0) {			
					$row = $result->fetch_assoc();
					$avatar = $row['avatar'];
					$fullname = $row['fullname'];
				
			} else {
				/* header('Location: login.html');
				exit(); */
			}
		}
$sql = "select booktitle from favoritebooks where username = '$username'";
			if ($result=mysqli_query($conn,$sql)){
				$rowcount1=mysqli_num_rows($result);
				if ($rowcount1 > 0) {			
					 while($row = $result->fetch_assoc()) {		
					$books[] = $row['booktitle'];
		 }		
				
			} else {
				/* header('Location: login.html');
				exit(); */
			}
		}
?>

<h1>Welcome <?php echo($_SESSION['sess_user_name']);?> !! </h1>

<?php 
if($avatar == ""){
	
}
else{
	?>
	<div> 
	<picture><img src="<?php echo($avatar) ?>" alt="avatar" style="width:auto;"></picture>
	</div>
	</br>
	
<?php }
echo "<div>

<h2>Full Name: " . $fullname . "</h2>

</div>";
if(!empty($books)){
	echo "<h3>Favorite Books</h3> 
	<div> 
	<ul>";
	foreach ($books as $book){			
				echo "<li value = $book>$book</li>";
	}
	echo "</ul></div>";
}?>




