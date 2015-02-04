<?php
//Continue previous session
session_start();

//Set the json header to display nicely
header('Content-Type: application/json');

//Contents variable holds the string that was inputted by the user from findbook.php
$contents = $_POST["contents"];

//Query ISBNDB's database and store the output in the output variable
//urlencode takes spaces inputted from users and adds the necessary special escape characters
$output = file_get_contents('http://isbndb.com/api/v2/json/Q0DGGAQJ/books?q='.urlencode($contents).'');

//Spit the output
echo $output;

?>

