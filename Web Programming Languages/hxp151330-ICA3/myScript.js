
	$(document).ready(function(){
		
		$("#inp").click(function(event){
		//$("#response tr").remove();
		$("#response").attr('hidden');
		$.ajax({
			
					dataType: "xml",
					url: "books.xml",
									
					success: function(data){
						//	alert("got it");
							$("#response").removeAttr('hidden');
						$(data).find('book').each(function(){
							var catg = $(this).attr("category");
							var title = $(this).find("title").text();
							var author = $(this).find("author").text();
							var year = $(this).find("year").text();
							var price = $(this).find("price").text();
							var authors = "";
							var authorLength = $(this).find("author").length;
							if (authorLength == 1){
							var myText = "<tr><td>"+ title + "</td>" + "<td>"+ author + "</td> " + "<td>"+ catg + "</td>"+ "<td>"+ year + "</td>" + "<td>"+ price + "</td></tr>";
							}
							else{
							var i = 0;
							$(this).find('author').each(function(){
								
								var newAuthor = $(this).text();
								if ( i == 0){
								authors = authors + newAuthor ;
								}
								else{
									if (i == authorLength){
									authors = authors + newAuthor ;
									}
									else{
									authors = authors + ", " + newAuthor ;
									}
								}
								i++;
								});
								var myText = "<tr><td>"+ title + "</td>"+ "<td>"+ authors + "</td> " + "<td>"+ catg + "</td>"+ "<td>"+ year + "</td>" + "<td>"+ price + "</td></tr>";
							
							}
							$("#response tr:last").after(myText);
							
							
						});
					},
					error: function(e){
						alert("Error making ajax request");
						console.log(e.message);
					}
		});		
		});
		
	});
