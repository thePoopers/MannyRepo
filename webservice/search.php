<?php
session_start();
$contents = $_POST["contents"];
//load and connect to MySQL database stuff
//require("config.inc.php");

$homepage = json_decode(file_get_contents('http://isbndb.com/api/v2/json/Q0DGGAQJ/books?q=$contents'));

//echo $contents;
echo $homepage;
?>

