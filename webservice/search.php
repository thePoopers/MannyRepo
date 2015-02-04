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
//echo "Beginning of OBJECT NOTATION (NOT JSON)";
echo "\n";

$objJson = json_decode($output);

//Example of printing the title of the 2nd book found
print_r ($objJson->data[1]->title);

//if statement
//for statement
//both of these need to involve page_count, current_page, result_count

echo "\n";

//Print in object notation (non json)
//print_r ($objJson);

/*
//Used for Debugging
echo "\n";
echo "-----End of Object Notation----------";
echo "\n";
echo "Beginning of JSON";
echo "\n";
*/

//Print JSON output
echo $output;

?>

