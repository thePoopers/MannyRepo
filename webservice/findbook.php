<?php
//Start a session that carries a session variable
session_start();
//load and connect to MySQL database stuff
require("config.inc.php");

//if posted data is not empty
if (!empty($_POST)){
	//if the search text is empty, the page will die.
	if(empty($_POST['contents'])){
		//Create some data that will be the JSON response
		$response["success"] = 0;
		$response["message"] = "Please enter a title, author, or ISBN.";
		
		//die will kill the page and not execute any code below
		die(json_encode($response));	
	}

	//if the page hasnt died, we call isbndb
	session_start();

	header('Content-Type: application/json');

	$contents = $_POST["contents"];

	$response["success"] = 1;
	$response["message"] = "Successfully searched!";
	echo json_encode($response);

	$output = file_get_contents('http://isbndb.com/api/v2/json/Q0DGGAQJ/books?q='.urlencode($contents).'');

}else{
?>
<!--Take the user input and store it into a session variable that gets sent to search.php-->
		<h1>Find Book</h1> 

		<form action="findbook.php" method="post"> 
		    Search:<br /> 
		    <input type="text" size="64" name="contents" placeholder="Search for Title, Author, or ISBN" /> 
		    <br /><br /> 
		    <input type="submit" value="Search" /> 
		</form> 
<?php
//echo $output;
}
echo $output;
?>
