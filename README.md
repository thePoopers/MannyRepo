<h1>Server Side</h1>

<h1>1.</h1>
Must edit DocumentRoot field which is found inside of the 000-default.conf (sometimes called default.conf). Change the directory of DocumentRoot to the appropriate directory your web files are hosted.

Ex: For me it was:
	- /apache2/sites-enabled/000-default.conf
	- Changed DocumentRoot to my ~/Coding/Servers/www/webservice directory
	- Save

<h1>2.</h1>
Next we must edit the apache2.conf file (It's named apache2.conf for me, might be diff for you. Google it)
Ex: for me it was:
	- /etc/apache2/apache2.conf
	- I added the following code below. (Note, the > that comes before 'Directory' on the first and last lines should be a lessthan sign instead, so flip it.

	>Directory ~/Coding/Server/www/webservice>
	 Options Indexes FollowSymLinks
	 AllowOverride None
	  Require all granted
	>/Directory>

<h1>3.</h1>
After this is done, <b>reload apache</b> and see if you can see default page.

<h1>4.</h1>
Configure conf.inc.php to connect to MYSQLDB using your information.
The fields you probably need to change are:
<b>username
password</b>
These are the username/password of your mysqldb.<br>


<h1>Android Side</h1>

Change the server location to your servers ipaddress (Ex: http://192.168.1.4/login.php) on the following files
AddComment.java
Login.java
ReadComments.java
Register.java
