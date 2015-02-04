<?php
session_start();
//load and connect to MySQL database stuff
require("config.inc.php");
?>
		<h1>Find Book</h1> 
		<form action="search.php" method="post"> 
		    Search:<br /> 
		    <input type="text" size="64" name="contents" placeholder="Search for Title, Author, or ISBN" /> 
		    <br /><br /> 
		    <input type="submit" value="Search" /> 
		</form> 
