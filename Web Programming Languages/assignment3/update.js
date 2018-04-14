
	$('#selectCountry').change(function(){
		var CountryID = $(this).val();
		$("#selectState").empty();
		$("#cities").empty();
		var postData = 
					{
						"location_id":CountryID,
						"type":1
					}
					$.ajax({
					type: "POST",
					dataType: "json",
					url: "update.php",
					data: {'myData':postData},
					success:function(response){
						console.log(response);
						var len = response.length;
						
						$("#selectState").append("<option value='0'>Select State</option>");
						for( var i = 0; i<len; i++){
							var id = response[i]['location_id'];
							var name = response[i]['name'];
							$("#selectState").append("<option value='"+id+"'>"+name+"</option>");

						}
					}						
					,
					error: function(e){
						console.log(e.message);
					}
				});
	});
	
	$('#selectState').change(function(){
		var StateID = $(this).val();
		$("#cities").empty();
		var postData = 
					{
						"location_id":StateID,
						"type":2
					}
					$.ajax({
					type: "POST",
					dataType: "json",
					url: "update.php",
					data: {'myData':postData},
					success:function(response){
						console.log(response);
						var len = response.length;
						
						$("#cities").append("<option value='0'>Select City</option>");
						for( var i = 0; i<len; i++){
							var id = response[i]['location_id'];
							var name = response[i]['name'];
							
							$("#cities").append("<option value='"+id+"'>"+name+"</option>");

						}
					},
					error: function(e){
						console.log(e.message);
					}
				});
	});
	
	/* var myHelp ={buildDropdown: function(result, dropdown, myMessage)
    {
        dropdown.html('');
        dropdown.append('<option value="">' + myMessage + '</option>');
        if(result != '')
        {            
            $.each(result, function(k, v) {
                dropdown.append('<option value="' + v.location_id + '">' + v.name + '</option>');
            });
        }
    }
} */
