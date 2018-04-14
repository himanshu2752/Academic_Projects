<html>
<head>
        <title>Rest Flickr</title>
		
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="CS6314 inClassAssignment_6. php sessions">
		<meta name="author" content="Himanshu Parashar">
		<meta name="REST_Flickr" content="HTML,PHP,CSS,Javascript,REST, Webservice, Flickr api">
        
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		 
	     
    </head>
<?php 

if($_SERVER["REQUEST_METHOD"] == "POST"){

$keyW = $_POST["keyW"];
if($keyW == "" || empty($keyW)){
		header('Location: search.html');
			exit();
		}
		else{						
			$api_key = '3a18b678627e4ab7fccae47611d30c9a';
			$pieces = explode(" ", $keyW);
			$tmp = "";
			$count = 0;
			echo'<body> <div class="row"><div class="col-lg-2"></div>  <div class="col-lg-8">
			  <h2>WORDS Searched</h2>';
			foreach($pieces as $str){
				if($count == 0){
					$tmp = $tmp .$str;
				}
				else{
				$tmp = $tmp ."," .$str;
				}
				echo '			
			  <h4><strong>' . $str . '</strong></h4>';

				$count ++;				
			}
			echo '</div></div> </br>';
	
			$tag = $tmp;
			$perPage = 100;
			$url = 'https://api.flickr.com/services/rest/?method=flickr.photos.search';
			$url.= '&api_key='.$api_key;
			
			$url.= '&tags='.$tag;
			$url.= '&per_page='.$perPage;
			$url.= '&format=json';
			$url.= '&nojsoncallback=1 order by date-taken desc';
			 
			$response = json_decode(file_get_contents($url));
			$photo_array = $response->photos->photo;			 
		
			 echo '<div class="row"><div class="col-lg-2"></div> <div class="col-lg-10">';
			 $colCount = 0;
			foreach($photo_array as $single_photo){
			 
			$farm_id = $single_photo->farm;
			$server_id = $single_photo->server;
			$photo_id = $single_photo->id;
			$secret_id = $single_photo->secret;
			$size = 'm';
			 
			$title = $single_photo->title;
			 
			$photo_url = 'http://farm'.$farm_id.'.staticflickr.com/'.$server_id.'/'.$photo_id.'_'.$secret_id.'_'.$size.'.'.'jpg';
			$oUrl = 'http://farm'.$farm_id.'.staticflickr.com/'.$server_id.'/'.$photo_id.'_'.$secret_id.'.jpg';
				
			echo "<img title='".$title."' src='".$oUrl."' style='width:300px;height:200px; margin-top: 10; margin-bottom: 10px;' hspace='20' onclick='window.open(this.src)'/>";
			/* if($colCount %5 == 0){
					print"</br>";
				}
			 $colCount ++; */
			}
			echo '</div></div>';
			
		}	

}
echo ' </body> 
</html>';
?>