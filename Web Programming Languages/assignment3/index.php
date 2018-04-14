<!DOCTYPE html>
<?php 
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
?>
<html lang="en">
<head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
				
</head>
<body>
	<div class="container" style="margin-top:20px;">
      </div>
<div class="container">
	<div class="row">
				<h2>Welcome </h2>				
				<hr>
	</div>
	<div class="container-fluid">
  <div class="row content">	
	<div class="col-sm-9" id="div0">
	<div class="well">
        <h4 class="text-success"><strong>AJAX PHP MySQL</strong></h4>        
		<p class="text-primary">Select country,its correponding states will populate in next dropdown</p>
		<p class="text-primary">Then Select state,its correponding cities will populate in next dropdown</p>
      </div>
	<div class="row">
		<div class="col-sm-1">
		</div>
	  <div class="col-sm-8">
		<?php		 
				 $query = $db->prepare('SELECT name,location_id
				FROM location
				WHERE location_type = ?');
				$query->execute(array(0));
				$countries = $query->fetchAll();
							
				echo '<form name="types" method="post">
						<select id="selectCountry" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" name="selectCountry">
							<option value ="0" >Select Country</option> <span class="caret"></span>';
				foreach ($countries as $row){					
					
					echo "<option value = $row[location_id]>$row[name]</option> ";
					
				}			
				echo '</select>';
			
			?>
			
			
	  </div>
	</div>

  &nbsp;
  <div class="row">
		<div class="col-sm-1">
		</div>
	   <div class="col-sm-8">
			<select id="selectState" class="btn btn-default dropdown-toggle" name="selectState">					 
				<option value ="0" >Select State</option> <span class="caret"></span>			
			</select>
		</div>
   
  </div>
  &nbsp;
   <div class="row">
		<div class="col-sm-1">
		</div>
	   <div class="col-sm-8">
			<select id="cities" class="btn btn-default dropdown-toggle" name="cities">					 
				<option value ="0" >Cities</option> <span class="caret"></span>			
			</select>
		</div>
   
  </div>
  </div>
</div>
</div>
</div>
 <script src="update.js"></script>
</body>
</html>
		
  
