<?php
session_start();
header('Content-Type: application/json');
$contents = $_POST["contents"];

//load and connect to MySQL database stuff
//require("config.inc.php");

$homepage = file_get_contents('http://isbndb.com/api/v2/json/Q0DGGAQJ/books?q='.urlencode($contents).'');

//echo json_decode($contents);
//echo json_encode($homepage);
echo $homepage;

?>

