<?php 
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "countrylist";
/////////////////////////////////

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
define('DBHOST','localhost');
define('DBUSER','root');
define('DBPASS','');
define('DBNAME','countrylist');
define('serverIP','localhost');
//////////////////////////////

try {

	//create PDO connection
	$db = new PDO("mysql:host=".DBHOST.";dbname=".DBNAME, DBUSER, DBPASS);
	$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

} catch(PDOException $e) {
	//show error
    echo '<p class="bg-danger">'.$e->getMessage().'</p>';
    exit;
}

if(isset($_POST['myData'])){ 
$obj = $_POST['myData'];
$output = array();
$stateName = array();
$loc_id = array();
$date = date('Y-m-d H:i:s');

try {

$location_id = $obj["location_id"];
$type = intval($obj["type"]);

$rowcount = 0 ;
	
$sql = "select location_id,`name` from location where `parent_id` = $location_id and location_type = $type";
if ($result=mysqli_query($conn,$sql)){
	$rowcount=mysqli_num_rows($result);
}
if($rowcount > 0){
	 $sql = $db->prepare('select location_id,name from location where parent_id = ? and location_type = ?');
				$sql->execute(array($location_id,$type));
				$states = $sql->fetchAll();
				foreach ($states as $row){
					$output[] = array("location_id"=> $row['location_id'],
									 "name"=>$row['name']);
					
				}
}
    }
catch(PDOException $e)
    {
    $output = $sql . "<br>" . $e->getMessage();
    }
echo json_encode($output);		
}	
?>
